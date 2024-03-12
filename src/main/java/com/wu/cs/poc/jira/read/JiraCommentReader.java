package com.wu.cs.poc.jira.read;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JiraCommentReader {

    public String commentReader(String dde) {
        HttpClient httpClient = HttpClients.createDefault();
        String jiraUrl = "https://jira.corpprod.awswuintranet.net/";
        String username = "W0006989";
        String password = "Qawzse@24";
        String comment = null;
        StringBuilder comments=new StringBuilder();

        try {
            String url = jiraUrl + "/rest/api/2/issue/" + dde + "/comment";
            HttpGet request = new HttpGet(url);
            String auth = username + ":" + password;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            request.setHeader("Authorization", "Basic " + encodedAuth);

            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
            	
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONArray commentsArray = new JSONObject(responseBody).getJSONArray("comments");
                if (commentsArray.length()<1) {
                System.out.println("There is not comments for the DDE:"+dde);
                }
                
                for (int i = 0; i < commentsArray.length(); i++) {
                    JSONObject commentObject = commentsArray.getJSONObject(i);
                    String commentBody = commentObject.getString("body");
                    comments.append("Comment " + (i+1) + ": " + commentBody).append("\n");
                   
                   // System.out.println("Comment " + (i+1) + ": " + commentBody);
                    
                }
                comment = comments.toString();
            } else {
                System.out.println("Unable to fetch comments");
            }
        } catch (NullPointerException ne) {
            System.out.println("There is no comments");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }
   
}
