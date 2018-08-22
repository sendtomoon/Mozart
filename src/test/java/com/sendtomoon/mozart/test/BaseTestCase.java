package com.sendtomoon.mozart.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入割圆次数：");
		int n = scan.nextInt();
		double y = 1.0;
		for (int i = 0; i <= n; i++) {
			BigDecimal π = new BigDecimal(3).multiply(new BigDecimal(Math.pow(2, i)).setScale(2147483647).multiply(new BigDecimal(y)));
			System.out.println("第" + i + "次切割,为正" + (6 + 6 * i) + "边形，圆周率π≈" + π);
			y = Math.sqrt(2 - Math.sqrt(4 - y * y));
		}
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
		String str = "123456";
		String[] arr = str.split(",");
		System.err.println(arr[0]);
	}

	@Test
	public void test5() {
		try {
			String code = "32";
			String zero = "";
			if (6 != code.length()) {
				for (int i = 1; i <= 6 - code.length(); i++) {
					zero = zero + "0";
				}
				code = zero + code;
			}
			System.out.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String autoGenericCode(String code, int num) {
		String result = "";
		result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);

		return result;
	}

	@Test
	public void test6() {
		String s1 = "Programming";
		String s2 = new String("Programming");
		String s3 = "Program" + "ming";
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s1.intern());
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

	@Test
	public void test8() {
		System.err.println();
	}

}
