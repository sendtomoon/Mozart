package com.sendtomoon.mozart.tools.security;

public class ByteUtil {

	public static char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String toHexString(byte b) {
		int tmp = b & 0xFF;
		int high = (tmp & 0xf0) / 16;
		int low = (tmp & 0x0f) % 16;
		return new String(new char[] { HEX_CHAR[high], HEX_CHAR[low] });
	}

	public static String toHexString(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(toHexString(data[i]));
		}
		return sb.toString();
	}

	public static String toHexString(byte[] data, int start, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = start; i < start + len; i++) {
			sb.append(toHexString(data[i]));
		}
		return sb.toString();
	}

	public static byte[] fromHexString(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}
