/**
 * Copyright 2014-2015 www.goujiawang.com
 * All rights reserved.
 * 
 * @project
 * @author Flouny.Caesar
 * @version 2.0
 * @date 2014-11-26
 */
package com.hexin.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 跨域拦截
 * 
 * @author Flouny.Caesar
 *
 */
public class CrossInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		allowCORS(response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// ...
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// ...
	}
	
	/**
	 * 解决跨域
	 * @param res
	 */
	private void allowCORS(HttpServletResponse res){
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Expose-Headers", "X-My-Custom-Header, X-Another-Custom-Header");
		res.addHeader("Access-Control-Max-Age", "1728000");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		res.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept,X-Requested-With");
		res.addHeader("P3P","CP=CAO PSA OUR");
	}
}