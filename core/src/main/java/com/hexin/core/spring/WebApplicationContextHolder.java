package com.hexin.core.spring;

import org.springframework.web.context.ContextLoader;

public class WebApplicationContextHolder {

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName)
	{
		return (T)ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
	}
}
