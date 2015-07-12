package com.hexin.pettyLoan.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class MyFreeMarkerView extends FreeMarkerView {
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception{
		String path = request.getContextPath();
		//这种取法在nginx负载均衡下会有问题
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//		model.put("base",path);
//		model.put("basePath", basePath);
//		model.put("session", request.getSession());
//		model.put("request", request);
//		model.put("uri", request.getRequestURI());
//		model.put("url", request.getRequestURL());
		super.exposeHelpers(model, request);
	}
}
