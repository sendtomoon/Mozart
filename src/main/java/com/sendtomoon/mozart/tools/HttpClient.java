package com.sendtomoon.mozart.tools;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;

public interface HttpClient {

	public Map<String,Object> getHttpClient(String url,String requestBody, Map<String, Object> header,CookieStore cookie) throws ClientProtocolException, IOException;
	
	public Map<String,Object> getHttpsClient(String url,String requestBody,Map<String,Object> header) throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException;
}
