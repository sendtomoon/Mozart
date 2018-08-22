package com.sendtomoon.mozart.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.tools.HttpsUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.soap.MessageFactory;

@Component
public class DIR823GService {
	
	public void getCookie() throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put(HTTP.CONTENT_TYPE, "text/plain;charset=UTF-8");
		header.put("Referer", "http://192.168.0.1/Login.html");
		
		String url = "https://cr-input.mxpnl.net/data?_channel_id=&_partner_id=39571&_sub_id=0000&_app_version=1.0.23&_app=cs-dca";
		String req = "JTVCJTIyJTdCJTVDJTIydGltZVN0YW1wJTVDJTIyJTNBMTUzNDQ3NjQ3NjE1NCUyQyU1QyUyMnVybCU1QyUyMiUzQSU1QyUyMmh0dHAlM0ElMkYlMkYxOTIuMTY4LjAuMSUyRkxvZ2luLmh0bWwlNUMlMjIlMkMlNUMlMjJyZXF1ZXN0VHlwZSU1QyUyMiUzQSU1QyUyMm1haW4lNUMlMjIlMkMlNUMlMjJ0eXBlJTVDJTIyJTNBJTVDJTIybWFpbl9mcmFtZSU1QyUyMiUyQyU1QyUyMmV2ZW50SWQlNUMlMjIlM0ElNUMlMjIxNTM0NDc2NDc2MTU0MTk4NTQzNTQ4NSU1QyUyMiUyQyU1QyUyMmJyb3dzZXIlNUMlMjIlM0ElNUMlMjJjaHJvbWUlNUMlMjIlMkMlNUMlMjJpc19vbmxpbmUlNUMlMjIlM0F0cnVlJTJDJTVDJTIyd2luZG93TmFtZSU1QyUyMiUzQSU1QyUyMiU1QyUyMiUyQyU1QyUyMndpbmRvd1RpdGxlJTVDJTIyJTNBJTVDJTIyRElSLTgyM0clNUMlMjIlMkMlNUMlMjJkb2N1bWVudFJlZmVyZXIlNUMlMjIlM0ElNUMlMjIlNUMlMjIlN0QlMjIlNUQ=";
		HttpsUtils.post(url, header, req, null);
//		getHttpClient(url,req,null,null);
		
	}

	public void sendRequest() throws ClientProtocolException, IOException {
		String url = "http://192.168.0.1/HNAP1/";
		String req = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><Login xmlns=\"http://purenetworks.com/HNAP1/\"><Action>request</Action><Username>Admin</Username><LoginPassword></LoginPassword><Captcha></Captcha><PrivateLogin>LoginPassword</PrivateLogin></Login></soap:Body></soap:Envelope>";
		
		getHttpClient(url,req,null,null);
	}

	public static Map<String, Object> getHttpClient(String url, String requestBody, Map<String, Object> header,
			CookieStore cookie) throws ClientProtocolException, IOException {
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient closeableHttpClient = null;
		Map<String, Object> respMap = new HashMap<String, Object>();
		if (null == cookie) {
			closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		} else {
			closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookie).build();
		}
		HttpPost post = new HttpPost(url);
		if (null != header) {
			for (Map.Entry<String, Object> entry : header.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		StringEntity se = new StringEntity(requestBody, "utf-8");
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "text/html"));
		
		post.setEntity(se);
		// 发送请求
		HttpResponse httpResponse = closeableHttpClient.execute(post);
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
	
	
	public void httpsClient(String ip) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		URL url = new URL("https://cr-input.mxpnl.net/data?_channel_id=&_partner_id=39571&_sub_id=0000&_app_version=1.0.23&_app=cs-dca");
		HttpsURLConnection ssl = (HttpsURLConnection) url.openConnection();
		ssl.setSSLSocketFactory(createSSL());
		ssl.setDoOutput(true); // 需要输出
		ssl.setDoInput(true); // 需要输入
		ssl.setUseCaches(false); // 不允许缓存
		ssl.setRequestMethod("PUT"); // 设置PUT方式连接
		// 设置请求属性
		ssl.setRequestProperty("Content-Type", "application/json");
		ssl.setRequestProperty("Charset", "UTF-8");
		ssl.setRequestProperty("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		ssl.connect();

		// 建立输入流，向指向的URL传入参数
		DataOutputStream dos = new DataOutputStream(ssl.getOutputStream());
		dos.writeBytes("data=" + ip);
		dos.flush();
		dos.close();
		// 获得响应状态
		int resultCode = ssl.getResponseCode();
		String responseMsg = ssl.getResponseMessage();
		if (HttpURLConnection.HTTP_OK == resultCode) {
			String readLine = new String();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(ssl.getInputStream(), "UTF-8"));
			responseReader.close();
		}
	}

	private static SSLSocketFactory createSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] tm = new TrustManager[] { myX509TrustManager };
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, tm, null);
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		return ssf;
	}

	private static TrustManager myX509TrustManager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
	
}
