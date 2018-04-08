package com.sendtomoon.mozart.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sendtomoon.mozart.service.GetIPFromMAC1200R;
import com.sendtomoon.mozart.tools.HttpClient;

@Component
public class GetIPFromMAC1200RImpl implements GetIPFromMAC1200R {
	
	@Autowired
	HttpClient httpClient;


	@Override
	public void getIP() {
		try {
			Map<String,Object> getStok = httpClient.getHttpClient("http://192.168.0.1/", "{\"method\":\"do\",\"login\":{\"password\":\"WrLio6Kc9TefbwK\"}}", null, null);
			Map stok = (Map) JSON.parse(getStok.get("responseMsg").toString());
			Map<String, Object> header = new HashMap<String, Object>();
			header.put("Accept", "application/json, text/plain, */*");
			header.put("Content-Type", "application/json");
			Map getIP = httpClient.getHttpClient("http://192.168.0.1/stok="+stok.get("stok").toString()+"/ds", "{\"network\":{\"name\":\"wan_status\"},\"method\":\"get\"}", header, null);
			Map ip = (Map) JSON.parse(getIP.get("responseMsg").toString());
			String requestBody = "{\"data\":\"" + ((Map<String, Object>) ((Map<String, Object>) ip.get("network")).get("wan_status")).get("ipaddr").toString() + "\"}";
			Map<String, Object> godaddyHeader = new HashMap<String, Object>();
			godaddyHeader.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
			godaddyHeader.put("Content-Type", "application/json");
			httpClient.getHttpsClient("https://api.godaddy.com/v1/domains/sendtomoon.com/records/A/mozart", requestBody, godaddyHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
