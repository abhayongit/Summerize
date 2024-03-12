package com.wu.cs.poc.jira.read;


import java.io.FileOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class JiraSummaryReader {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        String jiraUrl = "https://jira.corpprod.awsccintranet.net/";
        String username = "XXXXXX";
        String password = "XXXXX";
        String jql="project=DDE"; /*AND status in (In Progress, PO Review, Resolved, Done) ORDER BY assignee,resolutiondate";
*/
        try {
        	
        	XSSFWorkbook workbook = new XSSFWorkbook();
        	XSSFSheet sheet = workbook.createSheet();
        	XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("DDE");
			row.createCell(1).setCellValue("Summary");
			row.createCell(2).setCellValue("Comments");
        	
        	String url = jiraUrl + "/rest/api/2/search?jql="+jql;
            HttpGet request = new HttpGet(url);
            String auth = username + ":" + password;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            request.setHeader("Authorization", "Basic " + encodedAuth);

            HttpResponse response = httpClient.execute(request);

			if (response.getStatusLine().getStatusCode() == 200) {
				String responseBody = EntityUtils.toString(response.getEntity());				
				JSONArray IssuesArray = new JSONObject(responseBody).getJSONArray("issues");

				
				for (int i = 0; i < 50; i++) {
					JSONObject issueObject = IssuesArray.getJSONObject(i);
					String DDE = issueObject.getString("key");
					JSONObject filedsObject = issueObject.getJSONObject("fields");
					String summary=filedsObject.getString("summary");
					
					

					System.out.println(DDE + " and Summary:" + summary);
				
					JiraCommentReader commentReader = new JiraCommentReader();
					String comments = commentReader.commentReader(DDE);
					XSSFRow rowDesc = sheet.createRow(i+1);
					rowDesc.createCell(0).setCellValue(DDE);
					rowDesc.createCell(1).setCellValue(summary);
					rowDesc.createCell(2).setCellValue(comments);
					System.out.println(comments);
				}				 
				String fileName = "D:/R3/JiraPoc/jiraExport.xlsx";
				FileOutputStream fos=new FileOutputStream(fileName);
				workbook.write(fos);
				fos.close();
				workbook.close();
			} else {
				System.out.println("Unable to read comments: " + response.getStatusLine().getStatusCode());
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}