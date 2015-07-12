package com.hexin.core.common.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletSessionManager implements SessionManager {
	@Override
	public void setSession(Integer userId, String key, Object obj){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();  
		request.getSession().setAttribute(key, obj);
	}
	
	@Override
	public Object getSession(Integer userId, String key){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();  
		return request.getSession().getAttribute(key);
	}
	
	@Override
	public void invalidSession(Integer userId){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		request.getSession().invalidate();
	}
}
