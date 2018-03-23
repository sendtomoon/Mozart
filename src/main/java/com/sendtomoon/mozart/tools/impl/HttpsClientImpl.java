package com.sendtomoon.mozart.tools.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.base.BaseComponent;
import com.sendtomoon.mozart.tools.HttpsClient;

@Component
public class HttpsClientImpl extends BaseComponent implements HttpsClient {

	public void httpsClient(String ip) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		URL url = new URL("https://api.godaddy.com/v1/domains/sendtomoon.com/records/A/mozart");
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
			while ((readLine = responseReader.readLine()) != null) {
				logger.info("httpsClient-readLine:"+readLine);
			}
			logger.info("httpsClient-responseMsg:"+responseMsg);
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
