package com.sendtomoon.mozart.tools.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	public static void main(String[] args) {
		try {
			byte[] bt = encrypt("192.168.5.96", "5913215");
			for(int i=0;i<bt.length;i++){
                System.out.println(bt[i]);
            }
			String sec = new String(bt);
			System.out.println(sec);
			byte[] str = decrypt(bt,"5913215");
			System.out.println(str);
//			byte[] bytes = decrypt(str.getBytes("UTF-8"), "5913215");
//			String str2 = new String(bytes, "UTF-8");
//			System.out.println(str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes("UTF-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] byteContent = content.getBytes("UTF-8");
			byte[] result = cipher.doFinal(byteContent);
			return result;// 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes("UTF-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 解密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
