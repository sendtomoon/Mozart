package com.sendtomoon.mozart.tools.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.base.BaseComponent;
import com.sendtomoon.mozart.service.HttpClientSB;

@Component
public class HttpClient extends BaseComponent{
	
	static CookieStore cookieStore = new BasicCookieStore();

	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	@Override
	public Map<String, Object> getHttpClient(String url, String requestBody, Map<String, Object> header,CookieStore cookie)
			throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = null;
		Map<String, Object> respMap = new HashMap<String, Object>();
		if(null==cookie) {
			closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		}else {
			closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookie).build();
		}
		HttpPost post = new HttpPost(url);
		/** 设置代理 **/
		// HttpHost proxy = new HttpHost("127.0.0.1",1080,"http");
		// RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		// post.setConfig(config);
		/** 设置代理结束 **/
		if (null != header) {
			for (Map.Entry<String, Object> entry : header.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		StringEntity se = new StringEntity(requestBody, "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		// 发送请求
		HttpResponse httpResponse = closeableHttpClient.execute(post);
		// logger.info("http-response-info:" + httpResponse.toString());
		// 获取响应输入流
		InputStream inStream = httpResponse.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
		StringBuilder strber = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			strber.append(line);
		}
		inStream.close();
		respMap.put("httpStatus", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
		respMap.put("responseMsg", strber.toString());
		respMap.put("cookie", cookieStore.getCookies());
		return respMap;
	}

	public static Map<String, String> httpsClientPut(String url, String requestBody, Map<String, String> header)
			throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException,
			URISyntaxException {
		Map<String, String> respMap = new HashMap<String, String>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setSSLContext(createIgnoreVerifySSL());
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPut put = new HttpPut(url);
		for (Entry<String, String> entry : header.entrySet()) {
			put.setHeader(entry.getKey(), entry.getValue().toString());
		}
		StringEntity se = new StringEntity(requestBody, "UTF-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		put.setEntity(se);
		// 发送请求
		HttpResponse httpResponse = closeableHttpClient.execute(put);
		// 获取响应输入流
		InputStream inStream = httpResponse.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
		StringBuilder strber = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			strber.append(line);
		}
		inStream.close();
		respMap.put("status", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
		respMap.put("responseMsg", strber.toString());
		return respMap;
	}
	
	public static Map<String, String> httpsClientGet(String url, Map<String, String> header)
			throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException,
			URISyntaxException {
		Map<String, String> respMap = new HashMap<String, String>();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setSSLContext(createIgnoreVerifySSL());
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet put = new HttpGet(url);
		for (Entry<String, String> entry : header.entrySet()) {
			put.setHeader(entry.getKey(), entry.getValue().toString());
		}
		// 发送请求
		HttpResponse httpResponse = closeableHttpClient.execute(put);
		// 获取响应输入流
		InputStream inStream = httpResponse.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
		StringBuilder strber = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			strber.append(line);
		}
		inStream.close();
		respMap.put("status", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
		respMap.put("responseMsg", strber.toString());
		return respMap;
	}
	

}
