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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * StringUtil 工具类
 *
 */
public class StringUtil extends StringUtils {
	
	public static final String CHARSET_ENCODING = "UTF-8";
	
	/**
	 * 通过字符串驻留池来进行比较
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean intern(String str1, String str2) {
		if (str1 == null) return str2 == null;
		
		str1 = str1.intern();
		
		return (str1 == str2)?true : false;
	}
	
	/**
	 * 将String aaBc 转为 aa_bc的格式
	 * 用于java bean 属性转为数据库字段名
	 * @param str
	 * @return
	 */
	public static String propertyToFieldName(String str) {
		if (isEmpty(str)) return str;
		
		if(str.charAt(0) > 'A' && str.charAt(0) < 'Z') return str;
		
		if(Character.isUpperCase(str.charAt(0))) 
			str = Character.toString(Character.toLowerCase(str.charAt(0)))+str.substring(1);
		
		for(int i= 1; i < str.length(); i++) {
			char a = str.charAt(i);
			if(a >= 'A' && a <= 'Z') {
				if(str.charAt(i-1) != '_') {
					str = str.replace(String.valueOf(a), ("_".concat(String.valueOf(a))).toLowerCase());
					i++;
				}
				
				continue ;
			}
		}
		
		return str;
	}
	
	/**
	 * 返回截取字符串
	 * @param str
	 * @param num
	 * @return
	 */
	public static String omit(String str, int num) {
		
		if (StringUtil.isBlank(str)) return null;
		if(str.length() < num) return str;
		
		return num > 0?str.substring(0, num):str;
	}
	
	/**
	 * 截断字符串，只适用中文和单字节表示字符的国家
	 * @param str
	 * @param byteLength
	 * @return
	 */
	public static String limit(String str, int byteLength) {
		if (isBlank(str)) return null;
		if (byteLength <= 0) return null;
		
		try {
			if (str.getBytes(StringUtil.CHARSET_ENCODING).length <= byteLength) return str;
		} catch (UnsupportedEncodingException e) {
			
			return null;
		}
		
		StringBuffer buff = new StringBuffer();
		int index = 0;
		char c;
		char[] arr = new char[1];
		while (byteLength > 0 && index < str.length()) {
			c = str.charAt(index);
			arr[0] = c;
			if (!isChineseString(new String(arr))) {
				byteLength--;
			} else {
				byteLength--;
				byteLength--;
			}
			
			buff.append(c);
			index ++;
		}
		
		buff.append("...");
		
		return buff.toString();
	}
	
	/**
	 * 检查给定的字符串中是否包含中文信息
	 * @param string
	 * @return
	 */
	public static boolean isChineseString(String string) {
		if(isBlank(string)) return false;
		
		String patternRange = "[\\u4E00-\\u9FA5]+";
		Pattern pattern = Pattern.compile(patternRange);
		Matcher matcher = pattern.matcher(string);
		
		return matcher.find();
	}
	
	/**
	 * 将String中Null转换成""
	 * @param string
	 * @return
	 */
	public static String nullToString(String string) {
		
		return StringUtil.isBlank(string)?"":string;
	}
	
	public static String trim0(String num) {
		int begin = 0;
	    int end = num.length() - 1;
	    
	    while (num.charAt(end) == '0')
	    	--end; 
	    
	    if (num.charAt(end) == '.') 
	    	--end;
	    
	    return begin <= end?num.substring(begin, end + 1):"0";
	}
	
	public static String trimStr(String str) {
		
		return trimToEmpty(str).replaceAll("[\\r\\n]", "");
	}
	
	public static Boolean stringListContain(String[] list,String value){
		Boolean c = false;
		for(String s : list){
			if(s.equals(value)) {
				c=true;
				break;
			}
		}
		return c;
	}
	/**
	 * 用于分析字符串说明中的 <>标记，并用args替换<br>
	 * 如descriptoin=姓名<0:name>，性别<0:sex>，表示从第0个参数中取name和sex的属性值替换对应的标签
	 * @param description
	 * @param args
	 * @return
	 */
	public static String analysisDescription(String description, Object[] args){
		Pattern p = Pattern.compile("(<[^>]*>)");
		Matcher m = p.matcher(description);
		List<String> result=new ArrayList<String>();
		while(m.find()){
			result.add(m.group());
		}
		for(String param1 : result){
			String pm = param1;
			pm = pm.replace("<", "");
			pm = pm.replace(">", "");
			String[] params = pm.split(":");
			if(params.length==1){
				Integer index = Integer.parseInt(params[0]);
				description = description.replace(param1, args[index].toString());
			}
			else{
				Integer index = Integer.parseInt(params[0]);
				String field = params[1];
				Object o = args[index];
				String value = ReflectionUtil.getFieldValue(o, field).toString();
				description = description.replace(param1, value);
			}
		}
		return description;
	}
	/**
	 * 用于分析字符串说明中的 <>标记，并用args替换<br>
	 * 如descriptoin=姓名<0:name>，性别<0:sex>，表示从第0个参数中取name和sex的属性值替换对应的标签<br>
	 * 如果包含&lt;result&gt;标签，则表示从result中取值
	 * @param description
	 * @param args
	 * @param result
	 * @return
	 */
	public static String analysisDescription(String description, Object[] args, Object result){
		Pattern p = Pattern.compile("(<[^>]*>)");
		Matcher m = p.matcher(description);
		List<String> res=new ArrayList<String>();
		while(m.find()){
			res.add(m.group());
		}
		for(String param1 : res){
			String pm = param1;
			pm = pm.replace("<", "");
			pm = pm.replace(">", "");
			String[] params = pm.split(":");
			if(!params[0].equals("result")){
				if(params.length==1){
					Integer index = Integer.parseInt(params[0]);
					description = description.replace(param1, args[index].toString());
				}
				else{
					Integer index = Integer.parseInt(params[0]);
					String field = params[1];
					Object o = args[index];
					String value = ReflectionUtil.getFieldValue(o, field).toString();
					description = description.replace(param1, value);
				}
			}
			else{
				if(params.length==1){
					description = description.replace(param1, result.toString());
				}
				else{
					String field = params[1];
					String value = ReflectionUtil.getFieldValue(result, field).toString();
					description = description.replace(param1, value);
				}
			}
		}
		return description;
	}
}