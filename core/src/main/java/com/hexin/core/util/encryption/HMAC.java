package com.hexin.core.util.encryption;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议
 * 用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。
 * 接收方利用与发送方共享的密钥进行鉴别认证等
 * 
 *
 */
public class HMAC {
	
	/**
	 * 初始化HMAC密钥
	 * @return
	 */
	public static String initMacKey() {
		
		SecretKey secretKey = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			secretKey = keyGenerator.generateKey();  
		} catch (NoSuchAlgorithmException e) {
			
			return null;
		}
		
	    return BASE64.encryptBASE64(secretKey.getEncoded());  
	}
	  
	/** 
	 * HMAC加密 
	 *  
	 * @param data 
	 * @param key 
	 * @return 
	 * @throws Exception 
	 */  
	public static byte[] encryptHMAC(byte[] data, String key) {
	  
	    SecretKey secretKey = new SecretKeySpec(BASE64.decryptBASE64(key), "HmacMD5");  
	    Mac mac = null;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
		} catch (Exception e) {
			
			return null;
		}
	  
	    return mac.doFinal(data);
	}
}