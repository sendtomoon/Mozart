package com.sendtomoon.mozart.tools.security;

import java.io.UnsupportedEncodingException;
/**    
 * @Title: AESUtil2.java  
 * @Package com.sendtomoon.mozart.tools.security
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author  
 * @date  
 * @version V1.0    
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil2 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 密钥的种子，可以是任何形式，本质是字节数组
		String strKey = "L853093366o";
		// 密钥数据
		byte[] rawKey = getRawKey(strKey.getBytes());
		// 密码的明文
		String clearPwd = "218.59.32.145";
		// 密码加密后的密文
		byte[] encryptedByteArr = encrypt(clearPwd, rawKey);
		System.out.println(new String(encryptedByteArr,"UTF-8"));
		// 解密后的字符串
//		String decryptedPwd = decrypt(new String(encryptedByteArr).getBytes(), rawKey);
		String decryptedPwd = decrypt(encryptedByteArr, rawKey);
		System.out.println(decryptedPwd);

	}

	/**
	 * @param rawKey
	 *            密钥
	 * @param clearPwd
	 *            明文字符串
	 * @return 密文字节数组
	 */
	private static byte[] encrypt(String clearPwd, byte[] rawKey) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encypted = cipher.doFinal(clearPwd.getBytes("UTF-8"));
			return encypted;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param encrypted
	 *            密文字节数组
	 * @param rawKey
	 *            密钥
	 * @return 解密后的字符串
	 */
	private static String decrypt(byte[] encrypted, byte[] rawKey) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return new String(decrypted);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @param seed种子数据
	 * @return 密钥数据
	 */
	private static byte[] getRawKey(byte[] seed) {
		byte[] rawKey = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
			// AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			rawKey = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
		}
		return rawKey;
	}
}
