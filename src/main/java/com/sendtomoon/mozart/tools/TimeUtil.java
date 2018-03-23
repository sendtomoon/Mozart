package com.sendtomoon.mozart.tools;

import java.time.LocalDateTime;

public class TimeUtil {

	public static String now() {
		return LocalDateTime.now().toString().replaceAll("T", " ");
	}
}
