package com.hexin.pettyLoan.common.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception { 
//		
//		String url = request.getRequestURL().toString().toLowerCase();
//		if(url.endsWith(".html")){
//			Integer authorityUserId = Integer.parseInt(request.getParameter("authorityUserId"));		
//			List<FunctionItem> functionList = menuService.queryFunctionByUser(authorityUserId);		
//			FunctionItem function = null;
//			for(FunctionItem item : functionList){
//				if(url.contains(item.getFunctionName().toLowerCase())){
//					function = item;
//				}
//			}
//			if(function == null){
//				JsonResult result = new JsonResult(-100, "没有访问权限" , null);
//				String callback = request.getParameter("callback");
//				String json = JSONUtil.toJsonpString(result, callback);
//				response.getWriter().print(json);
//				return false;
//			}
//		}
//		
		//设置不拦截的url
		//System.out.println(request.getRequestURL().toString().toLowerCase());
//		String[] filter={
//				"menufunction/selectusermenu.do"
//				//"file/fileupload.do"
//				};		
//		for(String f : filter){
//			if(request.getRequestURL().toString().toLowerCase().endsWith(f)){
//				return super.preHandle(request, response, handler);
//			}
//		}
		return super.preHandle(request, response, handler);
	}
}
