package com.sendtomoon.mozart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sendtomoon.mozart.tools.HttpClient;
import com.sendtomoon.mozart.tools.impl.HttpClientImpl;

@Controller
public class DNSController {
	@Autowired
	HttpClient hc;

	@RequestMapping("/getdnslist")
	@ResponseBody
	public String getDNSList() {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "sso-key dL3oxj5DL3Mn_WhAknzdqwB8aTStK98moZ6:XSk7ccXquXQwek1cFcw9uQ");
		header.put("accept", "application/json");
		try {
			Map<String, String> resp = HttpClientImpl.httpsClientGet("https://api.godaddy.com/v1/domains/sendtomoon.com/records/A", header);
			return resp.get("responseMsg").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
