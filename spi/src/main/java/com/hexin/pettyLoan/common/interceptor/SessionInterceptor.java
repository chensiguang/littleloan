package com.hexin.pettyLoan.common.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.cache.ShardedRedisUtil;
import com.hexin.pettyLoan.common.DateHandler;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	@Resource(name="shardedRedisUtil")
	ShardedRedisUtil redisUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception { 
		String[] filter={
				"user/login.do"
				//"file/fileupload.do"
				};		
		for(String f : filter){
			if(request.getRequestURL().toString().toLowerCase().endsWith(f)){
				return super.preHandle(request, response, handler);
			}
		}
		response.setHeader("Content-type", "text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("authorityUserId");
		String time = redisUtil.getMap("session", userId);
		if(time == null){
			JsonResult result = new JsonResult(-100,"你没有权限访问，请登陆！",null);
			String callback = request.getParameter("callback");
			response.getWriter().print(JSONUtil.toJsonpString(result, callback));
			return false;
		}
		Long now = DateHandler.getNow().getTime();
		Long last = Long.parseLong(time);
		if(now - last >60*1000*15){//15分钟session失效
			JsonResult result = new JsonResult(-100,"会话已超时，请先登陆！",null);
			String callback = request.getParameter("callback");
			response.getWriter().print(JSONUtil.toJsonpString(result, callback));
			return false;
		}
		
		redisUtil.setMap("session", userId, now.toString());
		return super.preHandle(request, response, handler);
	}
}
