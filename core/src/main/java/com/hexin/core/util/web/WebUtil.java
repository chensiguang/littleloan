package com.hexin.core.util.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hexin.core.util.ReflectionUtil;

public class WebUtil {
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	public static String getDomainUrl(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort();
		return basePath;
	}

	public static String getFullURL(HttpServletRequest request, String url) {
		return getBasePath(request) + url;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getParameters(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		// 去掉最后一个空格
		if(!queryString.equals("")){
			queryString = queryString.substring(0, queryString.length() - 1);
		}
		return queryString;
	}

	public static String getRequestURI_noProjectName(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String projectName = request.getContextPath();
		return uri.replace(projectName, "");
	}

	public static String getUrlParams(Object o) {
		List<String> fields = ReflectionUtil.getFields(o);
		String params = "";
		for (String field : fields) {
			Object v = ReflectionUtil.getFieldValue(o, field);
			if (v != null) {
				params += "&" + field + "=" + v.toString();
			}
		}
		return params;
	}

	public static String getUrlParams(Object o, String[] filter) {
		List<String> fields = ReflectionUtil.getFields(o, filter);
		String params = "";
		for (String field : fields) {
			Object v = ReflectionUtil.getFieldValue(o, field);
			if (v != null) {
				params += "&" + field + "=" + v.toString();
			}
		}
		return params;
	}

	public static String getPhysicalPath(HttpServletRequest request, String url) {
		return request.getSession().getServletContext().getRealPath(url);
	}

	/**
	 * 将字符串转换为int类型
	 * 
	 * @param k
	 * @return 字符串为空则返回0
	 */
	public static int stringCastint(String k) {
		if (k.trim().length() == 0) {
			return 0;
		} else {
			try {
				return Integer.valueOf(k.trim());
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}

	public static Double stringCastDouble(String k) {
		if (k.trim().length() == 0) {
			return 0.0;
		} else {
			try {
				return Double.valueOf(k.trim());
			} catch (NumberFormatException e) {
				return 0.0;
			}
		}
	}

}
