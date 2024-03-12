#Note: The openai-python library support for Azure OpenAI is in preview.
import os
import openai
openai.api_type = "azure"
openai.api_base = "https://genaihcl40123600.openai.azure.com/"

openai.api_version = "2023-09-15-preview"
openai.api_key = "14358aXXXXXXXXXXXXX6fb6f9b4ba2"

head = "UATCI4 |Gb Postoffice Partner | No transaction details available on history page This issue is specific to UATCI4 env and working fire in UATRS2. Will Fix in UATCI4 in 3.5 as for 3.3 brach is lockedEditDeletegitlabGitlab added a comment - 2 days agoReetesh Singh mentioned this issue in a merge request of Backend / wu-digital-services on branch bug/DDE-64705: UATCI4 |Gb Postoffice Partner | No transaction details available on history page This issue is specific to UATCI4 env and working fire in UATRS2. Will Fix in UATCI4 in 3.5 as for 3.3 brach is lockedEditDeletegitlabGitlab added a comment - 2 days agoReetesh Singh mentioned this issue in a merge request of Backend / wu-digital-services on branch bug/DDE-64705: UATCI4 |Gb Postoffice Partner | No transaction details available on history page This issue is specific to UATCI4 env and working fire in UATRS2. Will Fix in UATCI4 in 3.5 as for 3.3 brach is lockedEditDeletegitlabGitlab added a comment - 2 days agoReetesh Singh mentioned this issue in a merge request of Backend / wu-digital-services on branch bug/DDE-64705: UATCI4 |Gb Postoffice Partner | No transaction details available on history page This issue is specific to UATCI4 env and working fire in UATRS2. Will Fix in UATCI4 in 3.5 as for 3.3 brach is locked" 

responses = openai.Completion.create(
  engine="Model1",
  prompt=head,

#+"[https-jsse-nio-8094-exec-12] DEBUG com.wu.channelservices.cache.impl.ActiveSpaceCacheHandler traceId=65d644560dd99a7fd8864a03fe56d1e8 spanId=d8864a03fe56d1e8 S: METHOD_NAME=GwpGetTransactionDetails , ChannelSessionId=POSTOFFICE-web-8c295807-2087-4e92-a122-6ae5a050253f , ClientIp=064125239004 , ActiveSpaceCacheHandler:updateCache(); END",
  temperature=0.3,
  max_tokens=350,
  #top_p=1,
  frequency_penalty=0,
  presence_penalty=0, 
  #stream=false,
  #best_of=1,
  stop=None)

print("The summarized text is:")
print(responses)