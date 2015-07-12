package com.hexin.core.util.encryption;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES-Data Encryption Standard,即数据加密算法
 * DES算法的入口参数有三个:Key、Data、Mode。
 * 其中Key为8个字节共64位,是DES算法的工作密钥;
 * Data也为8个字节64位,是要被加密或被解密的数据;
 * Mode为DES的工作方式,有两种:加密或解密。
 * DES算法把64位的明文输入块变为64位的密文输出块,它所使用的密钥也是64位
 * 
 *
 */
public class DES {
	
	private static final String ALGORITHM = "DES";
	
	/**
	 * 生成密钥 
	 * 
	 * @return
	 */
	public static String initKey() {
		
		return initKey(null);
	}
	
	/**
	 * 生成密钥
	 * 
	 * @param seed
	 * @return
	 */
	public static String initKey(String seed) {
		SecureRandom secureRandom = null;
		
		if (seed != null) {
			secureRandom = new SecureRandom(BASE64.decryptBASE64(seed));
		} else {
			secureRandom = new SecureRandom();
		}
		
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(ALGORITHM);
		} catch (Exception e) {
			
			return null;
		}
		
		kg.init(secureRandom);
		
		SecretKey secretKey = kg.generateKey();
		
		return BASE64.encryptBASE64(secretKey.getEncoded());
	}
	
	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, String key) {
		try {
			Key k = toKey(BASE64.decryptBASE64(key));
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, k);
	  
	        return cipher.doFinal(data);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, String key) {
		try {
			Key k = toKey(BASE64.decryptBASE64(key));
			  
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, k);
	  
	        return cipher.doFinal(data);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 */
	private static Key toKey(byte[] key) {
		try {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			
			return keyFactory.generateSecret(dks);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String inputStr = "中华人民共和国";
        String key = initKey();
        
        System.out.println("原文：\t" + inputStr);
        System.out.println("密钥：\t" + key);
  
        byte[] inputData = inputStr.getBytes();
        inputData = encrypt(inputData, key);
  
        System.out.println("加密后:\t" + BASE64.encryptBASE64(inputData));
        System.out.println("解密后:\t" + new String(decrypt(inputData, key)));
	}
}