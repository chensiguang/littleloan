package com.hexin.core.util.encryption;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA 
 *
 */
public class RSA {
	
	/**
	 * 用私钥对信息生成数字签名
	 * @param data 加密数据
	 * @param privateKey 私钥
	 * @return
	 */
	public static String sign(byte[] data, String privateKey) {
		byte[] keyBytes = BASE64.decryptBASE64(privateKey); // 解密由base64编码的私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes); // 构造PKCS8EncodedKeySpec对象
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec); // 取私钥匙对象
			
			/* 用私钥对信息生成数字签名 */
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(priKey);
			signature.update(data);
			
			return BASE64.encryptBASE64(signature.sign());
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 校验数字签名
	 * @param data 加密数据
	 * @param publicKey 公钥
	 * @param sign 数字签名
	 * @return
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) {
		byte[] keyBytes = BASE64.decryptBASE64(publicKey); // 解密由base64编码的公钥
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes); // 构造X509EncodedKeySpec对象
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec); // 取公钥匙对象
			Signature signature = Signature.getInstance("MD5withRSA");
			
			signature.initVerify(pubKey);
			signature.update(data);
			
			return signature.verify(BASE64.decryptBASE64(sign)); // 验证签名是否正常
		} catch (Exception e) {
			// ...
		}
		
		return false;
	}
	
	/**
	 * 用私钥解密 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key) {
		byte[] keyBytes = BASE64.decryptBASE64(key); // 对密钥解密
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes); // 取得私钥
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			
			/* 对数据解密 */
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			return cipher.doFinal(data);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 用私钥解密
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) {
		byte[] keyBytes = BASE64.decryptBASE64(key);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		
		try {
			/* 取得公钥  */
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			
			/* 对数据解密 */
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			
			return cipher.doFinal(data);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 用公钥加密
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) {
        byte[] keyBytes = BASE64.decryptBASE64(key);  // 对公钥解密  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes); // 取得公钥 
        
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key publicKey = keyFactory.generatePublic(x509KeySpec);  
			  
	        /* 对数据加密 */ 
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	  
	        return cipher.doFinal(data);  
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 用私钥加密
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) {
		byte[] keyBytes = BASE64.decryptBASE64(key); // 对密钥解密
		
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			
			return cipher.doFinal(data);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 取得私钥
	 * @param privateKey
	 * @return
	 */
	public static String getPrivateKey(RSAPrivateKey privateKey) {
		
		return BASE64.encryptBASE64(privateKey.getEncoded());
	}
	
	/**
	 * 取得公钥
	 * @param publicKey
	 * @return
	 */
	public static String getPublicKey(RSAPublicKey publicKey) {
		
		return BASE64.encryptBASE64(publicKey.getEncoded());
	}
	
	/**
	 * 初始化密钥
	 * @return
	 */
	public static RSAKey initKey() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			
			KeyPair keyPair = keyPairGen.generateKeyPair();
			
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 公钥
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 私钥
			
			RSAKey rSAKey = new RSAKey();
			rSAKey.setPublicKey(publicKey);
			rSAKey.setPrivateKey(privateKey);
			
			return rSAKey;
		} catch (NoSuchAlgorithmException e) {
			// ...
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		
		/* 生成密码对，并写入文件 */
//		RSAKey keys = initKey();
//		OutputStream outPub = new FileOutputStream(new File("F:/mypub.key"));
//		OutputStream outpri = new FileOutputStream(new File("F:/mypri.key"));
//		outPub.write(BASE64Util.encryptBASE64(keys.getPublicKey().getEncoded()).getBytes());
//		outpri.write(BASE64Util.encryptBASE64(keys.getPrivateKey().getEncoded()).getBytes());
		
		/* 公钥加密-私钥解密 */
//		String inputStr = "中华人民共和国";
//		byte[] data = inputStr.getBytes();
		
//		BufferedReader readerPub = new BufferedReader(new InputStreamReader(new FileInputStream("F:/mypub.key")));
//		String dataStrPub = null;
//		StringBuffer bufferPub = new StringBuffer();
//		while ((dataStrPub = readerPub.readLine()) != null) {
//			bufferPub.append(dataStrPub);
//		}
//		
//		BufferedReader readerPri = new BufferedReader(new InputStreamReader(new FileInputStream("F:/mypri.key")));
//		String dataStrPri = null;
//		StringBuffer bufferPri = new StringBuffer();
//		while ((dataStrPri = readerPri.readLine()) != null) {
//			bufferPri.append(dataStrPri);
//		}
		
//		byte[] encodedData = encryptByPublicKey(data, bufferPub.toString());
//		byte[] decodedData = decryptByPrivateKey(encodedData, bufferPri.toString());
//		
//		String outputStr = new String(decodedData);
//		System.out.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
	}
}