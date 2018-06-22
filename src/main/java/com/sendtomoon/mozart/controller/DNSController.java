package com.sendtomoon.mozart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sendtomoon.mozart.tools.impl.HttpClient;

@Controller
public class DNSController {

	@RequestMapping(path = "/getdnslist", method = RequestMethod.POST)
	@ResponseBody
	public String getDNSList() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/json");
		try {
			Map<String, String> resp = HttpClient
					.httpsClientGet("https://api.godaddy.com/v1/domains/sendtomoon.com/records/A", header);
			return resp.get("responseMsg").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(path = "/renewdns", method = RequestMethod.POST)
	@ResponseBody
	public String renewDNS(String name,String ipaddr) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		try {
			Map<String, String> resp = HttpClient.httpsClientPut("https://api.godaddy.com/v1/domains/sendtomoon.com/records/A/"+name, "[{\"data\":\""+ipaddr+"\"}]", header);
			return resp.get("responseMsg").toString();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "更新失败";
	}
	
}
