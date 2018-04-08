package com.sendtomoon.mozart.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sendtomoon.mozart.ReNew;
import com.sendtomoon.mozart.entity.WanPageDTO;
import com.sendtomoon.mozart.service.GetIP;
import com.sendtomoon.mozart.service.GetIPFromMAC1200R;
import com.sendtomoon.mozart.service.MainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ReNew.class)
@TestPropertySource(locations = { "classpath:application.properties" })
public class BaseTestCase {
	@Autowired
	GetIP getIP;

	@Autowired
	MainService mainService;
	
	@Autowired
	GetIPFromMAC1200R mac1200r;

	@Test
	public void test1() throws ClientProtocolException, IOException {
		// getIP.getStok();
	}

	@Test
	public void test2() throws ClientProtocolException, IOException {
		try {
			WanPageDTO wp = getIP.getWanPage(getIP.getStok().getCookie());
			System.err.println(wp);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}

	@Test
	public void test3() {
	}

	@Test
	public void test4() {
		mainService.renewtoGoddy();
	}

	@Test
	public void test5() {
		try {
			getIP.sendEMailForIP("dsfdasfdsa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test6() {
		mac1200r.getIP();
		
	}
}
