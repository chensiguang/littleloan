package com.hexin.pettyLoan.common.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AnnotationUtil;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.RequestDeviceUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.core.util.web.WebUtil;
import com.hexin.pettyLoan.common.Constants;

//@Aspect
public class AccessLogAspect {
	private final static Log logger  = LogFactory.getLog(AccessLogAspect.class);
//	LogService logService;
//	public LogService getLogService() {
//		return logService;
//	}
//
//	public void setLogService(LogService logService) {
//		this.logService = logService;
//	}

//	@AfterReturning(pointcut="@annotation(com.hexin.core.annotation.AccessLog)", returning="result", argNames="result")
	public void doLog(JoinPoint joinPoint, Object result){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
//		HttpServletRequest request = RequestContextHolder.getRequest();
		Integer userId = 0;
		try{
			userId = Integer.parseInt(request.getParameter(Constants.AUTHORITY_USER_ID));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		String className = joinPoint.getSignature().toString();
		String methodName = joinPoint.getSignature().getName();
		String operateType = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "operateType").toString();
		String type = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "type").toString();
		String description = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "description").toString();
		Boolean isMobile = RequestDeviceUtil.isMobileDevice(request);
		String add2DB = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "add2DB").toString();
		add2DB = add2DB.substring(add2DB.lastIndexOf(".")+1);
		String source = isMobile?"PAD": "WEB";
		short operate = 4;
		operateType = operateType.substring(operateType.lastIndexOf(".")+1);
		switch(operateType){
			case "Insert":{
				operate = 1;
				description += JSONUtil.toJsonString(result);
				};break;
			case "Update":{
				operate = 2;
				description += JSONUtil.toJsonString(joinPoint.getArgs());
				};break;
			case "Delete":{
				operate = 3;
				description += JSONUtil.toJsonString(joinPoint.getArgs());
				};break;
			case "Query":{
				operate = 4;
				description += JSONUtil.toJsonString(joinPoint.getArgs());
				};break;
		}
		String version = request.getHeader("user-agent");
//		if(add2DB.equals("Yes")){	
//			LogItem item = logService.AddLog(source, type, userId, className, description, operate,WebUtil.getDomainUrl(request)+request.getRequestURI(), version);
//			String message = "item = " + JSONUtil.toJsonString(item) + "\r\n";
//			message += "url = " + WebUtil.getDomainUrl(request)+request.getRequestURI() +"\r\n";
//			message += "params = " + WebUtil.getParameters(request) + "\r\n";
//			message += "referer = " + request.getHeader("Referer") + "\r\n";
//			message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
//			logger.info(message);
//		}
//		else{
			String message = "url = " + WebUtil.getBasePath(request) +"\r\n";
			message += "params = " + WebUtil.getParameters(request) + "\r\n";
			message += "referer = " + request.getHeader("Referer") + "\r\n";
			message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
			logger.info(message);
//		}
	}
	
//	@AfterThrowing(pointcut="@annotation(com.hexin.core.annotation.AccessLog)", throwing="e")
	public void throwing(JoinPoint joinPoint, Throwable e){
		Object[] args = joinPoint.getArgs();
//		HttpServletRequest request = RequestContextHolder.getRequest();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Integer userId = 0;
		try{
			userId = Integer.parseInt(request.getParameter(Constants.AUTHORITY_USER_ID));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		String className = joinPoint.getSignature().toString();
		String methodName = joinPoint.getSignature().getName();
		String operateType = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "operateType").toString();
		operateType = operateType.substring(operateType.lastIndexOf(".")+1);
//		String type = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "type").toString();
//		String description = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, AccessLog.class, "description").toString();
		Boolean isMobile = RequestDeviceUtil.isMobileDevice(request);
		String source = isMobile?"PAD": "WEB";
		short operate = 4;
		switch(operateType){
			case "Insert":operate = 1;break;
			case "Update":operate = 2;break;
			case "Delete":operate = 3;break;
			case "Query":operate = 4;break;
		}
		String description = e.getMessage();
		String version = request.getHeader("user-agent");;
		description += "{args = " + JSONUtil.toJsonString(args) +"}" ;
//		LogItem item = logService.AddLog(source, "Error", userId, className, description, operate, WebUtil.getDomainUrl(request)+request.getRequestURI(), version);
//		String message = "item = " + JSONUtil.toJsonString(item) + "\r\n";
		String message = "";
		message += "url = " + WebUtil.getDomainUrl(request)+request.getRequestURI() +"\r\n";
		message += "params = " + WebUtil.getParameters(request) + "\r\n";
		message += "referer = " + request.getHeader("Referer") + "\r\n";
		message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
		logger.info(message);
	}
	
}
