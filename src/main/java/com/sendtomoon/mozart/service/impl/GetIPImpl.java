package com.sendtomoon.mozart.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sendtomoon.mozart.base.BaseComponent;
import com.sendtomoon.mozart.dao.ErrorMongoDAO;
import com.sendtomoon.mozart.dao.StokMongoDAO;
import com.sendtomoon.mozart.entity.ErrorDTO;
import com.sendtomoon.mozart.entity.LoginInfoDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;
import com.sendtomoon.mozart.service.GetIP;
import com.sendtomoon.mozart.tools.HttpClient;
import com.sendtomoon.mozart.tools.TimeUtil;
import com.sendtomoon.mozart.tools.security.ByteUtil;
import com.sendtomoon.mozart.tools.security.RSAUtils;

@Component
public class GetIPImpl extends BaseComponent implements GetIP {

	@Value("${jsonForLogin}")
	private String jsonForLogin;

	@Value("${jsonGetWan}")
	private String jsonGetWan;

	@Value("${loginURL}")
	private String loginURL;

	@Value("${requestURL}")
	private String requestURL;

	@Value("${GoDaddy.Authorization}")
	private String Authorization;

	@Value("${GoDaddy.ssokey}")
	private String ssokey;

	@Value("${GoDaddy.ContentType}")
	private String ContentType;

	@Value("${GoDaddy.applicationjson}")
	private String applicationjson;

	@Value("${GoDaddy.renewURL}")
	private String httpsURI;

	@Value("${mail.smtp.server}")
	private String mailSmtpServer;

	@Value("${mail.username}")
	private String mailUsername;

	@Value("${mail.loginUsername}")
	private String loginUsername;

	@Value("${mail.password}")
	private String mailPassword;

	@Value("${mail.toUser}")
	private String mailToUser;

	@Value("${mail.title}")
	private String mailTitle;

	@Value("${mail.smtpPort}")
	private String smtpPort;

	@Value("${rsa.privateKey}")
	private String privateKey;

	@Value("${rsa.publicKey}")
	private String publicKey;

	@Autowired
	StokMongoDAO stokMongoDAO;
	@Autowired
	ErrorMongoDAO errorMongoDAO;
	@Autowired
	HttpClient httpClient;

	/**
	 * 登陆路由器，获取Cookie
	 */
	@Override
	public LoginInfoDTO getStok() {
		try {
			LoginInfoDTO stok = new LoginInfoDTO();
			logger.info("GetStok-request-URL:[" + loginURL + "],param:[" + jsonForLogin + "]");
			Map<String, Object> getStok = httpClient.getHttpClient(loginURL, jsonForLogin, null, null, "POST");
			logger.info("GetStok-respon:" + getStok.get("responseMsg"));
			@SuppressWarnings("unchecked")
			List<Cookie> lc = (List<Cookie>) getStok.get("cookie");
			BasicCookieStore bcs = new BasicCookieStore();
			for (Cookie cc : lc) {
				bcs.addCookie(cc);
			}
			stok = JSON.parseObject(getStok.get("responseMsg").toString(), LoginInfoDTO.class);
			stok.setCookie(bcs);
			stok.setHttpStatus((String) getStok.get("httpStatus"));
			stok.setDate(TimeUtil.now());
			stokMongoDAO.saveStok(stok);// 将获取的stok保存到MongoDB
			return stok;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("GetIPImpl-getStok:" + e.toString());
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo(e.toString());
			error.setErrorSence("GetStok");
			errorMongoDAO.saveError(error);
		}
		return null;
	}

	/**
	 * 通过stok码，请求路由器数据
	 * 
	 * @param stocPOJO
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Override
	public WanPageDTO getWanPage(CookieStore cookie) {
		try {
			logger.info("GetWanPage-request-URL:[requestURL],param:[" + jsonGetWan + "]");
			Map<String, Object> header = new HashMap<String, Object>();
			header.put("Accept", "application/json, text/plain, */*");
			Map<String, Object> getWan = httpClient.getHttpClient(requestURL, jsonGetWan, header, cookie, "POST");
			logger.info("GetWanPage-respon:" + getWan.get("responseMsg"));
			WanPageDTO wp = JSON.parseObject(getWan.get("responseMsg").toString(), WanPageDTO.class);
			wp.setDate(TimeUtil.now());
			stokMongoDAO.saveWanPage(wp);
			return wp;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("GetIPImpl-getWanPage:" + e.toString());
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo(e.toString());
			error.setErrorSence("GetWanPage");
			errorMongoDAO.saveError(error);
		}
		return null;
	}

	/**
	 * r 更新DNS地址到GoDaddy
	 */
	@Override
	public void renewDNS(String ipAddress) {
		try {
			Map<String, Object> header = new HashMap<String, Object>();
			header.put(Authorization, ssokey);
			header.put(ContentType, applicationjson);
			String requestBody = "{\"data\":\"" + ipAddress + "\"}";
			logger.info("RenewDNS-params-[URI:" + httpsURI + ";RequestBody:" + requestBody + ";Header:" + header);
			Map<String, Object> response = httpClient.getHttpsClient(httpsURI, requestBody, header, "PUT");
			logger.info("RenewDNS-result-[Status:" + response.get("status") + ";ResponseMsg:"
					+ response.get("responseMsg"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("GetIPImpl-renewDNS:" + e.toString());
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo(e.toString());
			error.setErrorSence("Send request to GoDaddy.");
			errorMongoDAO.saveError(error);
		}
	}

	/**
	 * 发送IP地址到邮箱
	 */
	@Override
	public void sendEMailForIP(String ip) {
		try {
			Email email = new SimpleEmail();
			email.setHostName(mailSmtpServer);
			email.setSmtpPort(Integer.valueOf(smtpPort));
			email.setAuthentication(loginUsername, mailPassword);
			email.setFrom(mailUsername);
			email.setSSLOnConnect(true);
			email.setSubject(mailTitle);
			email.setMsg("The new IP address is=[" + ip + "].\n" + "Time:" + TimeUtil.now());
			email.addTo(mailToUser);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			logger.error("MainServiceImpl-sendMailFromSendtomoon:" + e.toString());
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo(e.toString());
			error.setErrorSence("sendMail");
			errorMongoDAO.saveError(error);
		}
	}

	@Override
	public String encryptByPublicKey(String info) {
		try {
			return ByteUtil.toHexString(RSAUtils.encryptByPublicKey(info.getBytes(), publicKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
