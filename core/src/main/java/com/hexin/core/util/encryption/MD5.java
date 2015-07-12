package com.hexin.core.util.encryption;

import java.security.MessageDigest;

/**
 * MD5 message-digest algorithm 5 （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验
 * 
 *
 */
public class MD5 {
	
	/**
	 * 将字符串转加密成MD5串
	 * @param s
	 * @return
	 */
	public static String encryptMD5(String s) {
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			
			return new String(str);
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
	
	/**
	 * MD5加密
	 * @param data
	 * @return
	 */
	public static String encryptMD5(byte[] data) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data);
			
			return BASE64.encryptBASE64(md5.digest());
		} catch (Exception e) {
			// ...
		}
		
		return null;
	}
}