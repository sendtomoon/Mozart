package com.sendtomoon.mozart.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
// @ContextConfiguration(classes = ReNew.class)
// @TestPropertySource(locations = { "classpath:application.properties" })
public class BaseTestCase {

	@Test
	public void test1() throws ClientProtocolException, IOException {
	}

	@Test
	public void test2() throws ClientProtocolException, IOException {

	}

	@Test
	public void test3() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(null, null);
		map.put(null, null);
		map.put(null, null);

	}

	@Test
	public void test4() {
	}

	@Test
	public void test5() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test6() {

	}

	@Test
	public void test7() {
		Date now1 = new Date();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date now2 = new Date();
		System.out.println(now1.before(now2));

		Date endDate = new Date(1529596800);
		Date now = new Date();
		if (now.after(endDate)) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}

}
