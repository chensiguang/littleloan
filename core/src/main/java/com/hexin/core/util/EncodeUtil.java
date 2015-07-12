/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.hexin.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.web.util.JavaScriptUtils;

/**
 * 
 * @author Flouny.Caesar
 *
 */
public class EncodeUtil {
	
	private static String appEncoding = "UTF-8";
	private static String dbEncoding = "UTF-8";
	
	/**
	 * 根据数据库的字符集计算出该字符串的存储字节数
	 * @param str
	 * @return
	 */
	public static int getDBLength(String str) {
		if(StringUtil.isBlank(str))return 0;
		
		try {
			return str.getBytes(dbEncoding).length;
		} catch (UnsupportedEncodingException e) {
			
			return 0;
		}
	}
	
	/**
	 * 根据应用的字符集将字符串转化成字节数组
	 * @param str
	 * @return
	 */
	public static byte[] toBytes(String str) {
		if(StringUtil.isNotBlank(str)) {
			try {
				return str.getBytes(appEncoding);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		}
		
		return null;
	}
	
	/**
	 * 根据应用的字符集将字节数组转化成字符串
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		if (bytes != null) {
			try {
				return new String(bytes, appEncoding);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 根据应用的字符集对字符串进行URL编码
	 * @param obj
	 * @return
	 */
	public static String encodeURL(Object obj) {
		if (obj == null) return "";
		
		try {
			return URLEncoder.encode(obj.toString(), appEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * 根据应用的字符集对字符串进行URL解码
	 * @param obj
	 * @return
	 */
	public static String decodeURL(Object obj) {
		if (obj == null) return "";
		
		try {
			return URLDecoder.decode(obj.toString(), appEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * 对单、双引号中的特殊字符进行预处理，防止javascript解析出错。
	 * 当输出脚本变量时，必须预处理。例如：
	 * <script>
	 * 	var s = "<%= EncodeUtil.script(value) %>";
	 * </script>
	 * @param obj
	 * @return
	 */
	public static String script(Object obj) {
		if (obj == null) return "";
		
        return JavaScriptUtils.javaScriptEscape(obj.toString());
	}
}