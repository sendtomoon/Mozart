package com.sendtomoon.mozart.tools.security;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/** */
/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

	/** */
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/** */
	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	private static final int KEY_SIZE = 8192;
	static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTR3ifbEvDpE6zBRfsKZI9nZp7vWCWHBXHVvoljznKxef6sNZe054wHfvK4b9BRchUGcW5jI5HFnK+kuDiQZ+cbRJozyu+af5Dz2axjR5jcWGdqXu1y1fYhR82hoaKfH1pkXhtAmD6OVFZJY+qAQ4ys/9dXwjvDatiHPVOcOTbeQIDAQAB";
	static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANNHeJ9sS8OkTrMFF+wpkj2dmnu9YJYcFcdW+iWPOcrF5/qw1l7TnjAd+8rhv0FFyFQZxbmMjkcWcr6S4OJBn5xtEmjPK75p/kPPZrGNHmNxYZ2pe7XLV9iFHzaGhop8fWmReG0CYPo5UVklj6oBDjKz/11fCO8Nq2Ic9U5w5Nt5AgMBAAECgYEArNT7hJaHh9xiEKnq/uKm4caKOA0b9M+2tSGecZkvcoPgl7+PLXU5Iy1z62wuEwwtqzaF+5pSTS684M6wLgX2tPfd+MaxRECutdYxlmU86yD13QvPxX5Z4hlODQET1IkSjl5wbc0rp9MeEICCz6S+1/6FDBXMRXQp7K0oznBwvf0CQQDy2fTezgAXO/6TFXhF3lk2IKR5gl02ZZ63ivwQ9SJ9BuecwqCWLiDUyezWO/bkJGkfTTXrN9vQEESmKmimjrdHAkEA3rfoZ/u0kCmdxtZXntHnhr0/EX7DD+PRKh2gcjFYu3R3b7CnkLUIqbKKJZ/JNuM98JCLNej7qyGpAe2jgC63PwJAQD89MvyYtYzfqpXRfdp3C4TTPnooos17kd8pFGtltmHvQF+VEGYftWu+RmcSyASB8xAAJbxfYMoM/1DU4st63QJAHpM2x5epeSUNcO+aBcahN0EhuGAjnTnUTCjXDvYxTUK25tFkn5wKvWkyR+oAsJVPuofNZLBAZITxc79Q89b19wJADLZigrxTebMUItAOAtnuw52YLO9/MPQoOJLr7H49DJmO6/5yMe2Xcg8bDVU4X/OM60e7PGvjjDMXx59vQukOsQ==";
	// static final String
	// RSA_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCL/NwvuhWyf5znP78XRly2tIOqPJxpqOM/uxKKH9AKS6/3YstRGNVQJe8KKRUwV8p5PDPgCdmDK91MoZgrJTlNDsLjah0NJ02RwHpWg8Si7j8wz7IIwmlD4PFb/Tmeco2jg5bwZpEIg96P6q9motH83palNiu+vEO6O+g2uDa2vwIDAQAB";
	// static final String
	// RSA_PRIVATE_KEY="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIv83C+6FbJ/nOc/vxdGXLa0g6o8nGmo4z+7Eoof0ApLr/diy1EY1VAl7wopFTBXynk8M+AJ2YMr3UyhmCslOU0OwuNqHQ0nTZHAelaDxKLuPzDPsgjCaUPg8Vv9OZ5yjaODlvBmkQiD3o/qr2ai0fzelqU2K768Q7o76Da4Nra/AgMBAAECgYAZEOsAZMqzzqPyyHhYG5JIaORWnEnJpaMja0TzlKS04z6cBoWvfMndcx1tbVPM9ztbIAuN51WaZkVXDmxn9V9gCCMcC2btmLXa3kAyP4jCgGs2AJtHMRGO/yBeKkVdJFm6eVGvQc1A6vr4QE5w4uFEROIPHJdbsRm+mQAsz4dYIQJBAL9SxJbSdwpyoxFJNW8lLoB+DBycmgRUHfcSPQQalM4MbgKWnxvAMisec8CmYjOAOpdVaOsPLGFQKPLjECGn/BECQQC7T3soTioBSvvbvKXJn1hOIP1ZX705wfzSIPlv77vKcaie/ld0jwb9PRVlP4HXvq2o6e6CKaH8l3iH2j6c7JXPAkAGdSC1hn0GGUBvwOzQx4x+QcLe5Fo+cyhfwnnUvaDwI3NDGxpbsW0AD5MQmY0O9/ECUpur82MoFbCMIiXVPPpBAkBGA7qkYTtASngENeq+h8ppQdmRe8Dugv1uyhaS6ciHLp7591ZXYlMZe6iwtOFi9IpTNkYJ72f2U+Qg28pFvAVvAkEAmISBBTqeTpAEyyvjXCtfb9cDPboCLXCaleDaBqSShiI9aJtAxT8nQB30rCuiU6sQUW27sKF2BWyk8KLrZ0nmWw==";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = KEY_SIZE / 8 - 11;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = KEY_SIZE / 8;

	/** */
	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/** */
	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64Utils.encode(signature.sign());
	}

	/** */
	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64Utils.decode(sign));
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/** */
	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] myByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 用私钥解密<SPAN style="COLOR: #000000"></SPAN> * @param data 加密数据
	 * 
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] myPrivateKe(byte[] data, String key) throws Exception {
		// 对私钥解密 byte[] keyBytes = decryptBASE64(key);

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key.getBytes());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/** */
	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	/** */
	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	public static void main(String[] args) throws Exception {
		String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA0OBAG91Cq2MafTXwJNAwz7KIVNDKaPZiHuCLSI9t/UvPlUJbH6HYprE2eBXArQ1E5OXzcHO39DhYzpb53yCVO5jjYQwn0o0tZ7r41PUMl3Y5RX+qpo5v+Dr7gRV11usLGGtsYYykeyOJhJSUmSO+osFPTF/h4kla65ntq+HrugYxwXB6mH1j/oMpu0WBa8mhm5Mcmj+yIpIHuIypblLgnGWHLfdj4YsBa/2PX9LjO78E0BAB1jzU5LLJuOR8o1Prh9xwm7pwlKZ2m14CKsJgleO0M63/q0hnI20dddIEqlLW8uYuAhbvI111/HPZjok7qWNhbq1YBZrfoIY6RzqHdR6CJ5IHmGbcO7+EMUq04An5m/jmKK1HTNi8PFp54jJ58dY+bUZaBgR1DepsuoCSKjv8TrWsn12+ELgIq6+fGjUS3dg57Lt4lEH1UgU5KaVAKv5tPETCtAsv4L4MTwaTG2b8r8WHF81+yTAAt9VHJF4Y+FBSmBAWdR0Xxcv5pyxsIv/lfcfqH6EeKJPc+RDmCiAgJnH2I7ko+I/uKNFl1MmoT1401MOde+D1TUxwUc0+It27VBlgdGFNmg0Z1c0ZFHrSHVt8yd/N3dBjYSgmkz/WNvlgp/TowQOLiAhAJc3BhbeEKnVGed7L0n57iQPGGqE0uVxLKKWgLrS1NnrE+ZcCAwEAAQ==";
		String privateKey = "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";
		String ipAddr = "192.168.5.6";
		byte[] bs = ipAddr.getBytes("UTF-8");
		bs = RSAUtils.encryptByPublicKey(bs, publicKey);
		String secText = ByteUtil.toHexString(bs);
		System.out.println("secText:" + secText);
		// 上方是加密，下方对16进制的密文userInfo进行解密
		byte[] sloveBS = ByteUtil.fromHexString(secText);
		try {
			sloveBS = RSAUtils.decryptByPrivateKey(sloveBS, privateKey);
			String sloveIPAddr = new String(sloveBS, "UTF-8");
			System.out.println("sloveIPAddr:" + sloveIPAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
