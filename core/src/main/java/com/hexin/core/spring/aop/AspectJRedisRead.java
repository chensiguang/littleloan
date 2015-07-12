package com.hexin.core.spring.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


import com.hexin.core.annotation.AnnotationUtil;
import com.hexin.core.annotation.CacheType;
import com.hexin.core.annotation.RedisRead;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.StringUtil;
import com.hexin.core.util.cache.ShardedRedisUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.core.util.web.WebUtil;
import com.vteba.utils.web.RequestContextHolder;


//@Aspect
public class AspectJRedisRead {
	private final static Log logger  = LogFactory.getLog(AspectJRedisWrite.class);
	
	ShardedRedisUtil redisUtil;
    public ShardedRedisUtil getRedisUtil() {
		return redisUtil;
	}

	public void setRedisUtil(ShardedRedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

//	@AfterThrowing(pointcut="@annotation(com.hexin.core.annotation.RedisRead)", throwing="e")
    public void readAfterThrowing(JoinPoint joinPoint, Throwable e){
    	HttpServletRequest request = RequestContextHolder.getRequest();
		String message = e.getMessage();
		message += "url = " + WebUtil.getDomainUrl(request)+request.getRequestURI() +"\r\n";
		message += "params = " + WebUtil.getParameters(request) + "\r\n";
		message += "referer = " + request.getHeader("Referer") + "\r\n";
		message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
		logger.info(message);
    }
      
//    @Around("@annotation(com.hexin.core.annotation.RedisRead)")   //spring中Around通知  
    public Object readAround(ProceedingJoinPoint joinPoint) throws Throwable{
    	Object result = null;
    	Object[] args = joinPoint.getArgs();
    	String methodName = joinPoint.getSignature().getName();
    	String type = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisRead.class, "type").toString();
    	String keyName = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisRead.class, "keyName").toString();
    	String fieldName = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisRead.class, "fieldName").toString();
    	String valueType = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisRead.class, "valueType").toString();
    	try{
    		type = type.substring(type.lastIndexOf(".")+1);
    		switch(type){
    			case "KeyValue":{
    				String key = StringUtil.analysisDescription(keyName, args);
    				String value = redisUtil.get(key);
    				if(value==null){
    					result = joinPoint.proceed(args);
    					String json = JSONUtil.toJsonString(result);
    					redisUtil.set(key, json);
    					System.out.println("set redis key = "+key + "; value=" + json);
    				}
    				else{
    					result = JSONUtil.parseObjectOrArray(value, Class.forName(valueType));
    					System.out.println("read redis key = " + key + "; value ="+value);
    				}
    			};break;
    			case "Map":{
    				String key = StringUtil.analysisDescription(keyName, args);
    				String field = StringUtil.analysisDescription(fieldName, args);
    				String value = redisUtil.getMap(key, field);
    				if(value==null){
    					result = joinPoint.proceed(args);
    					String json = JSONUtil.toJsonString(result);
    					redisUtil.setMap(key, field, json);
    					System.out.println("set redis key = "+key + ";field="+ field +"; value=" + json);
    				}
    				else{
    					result = JSONUtil.parseObjectOrArray(value, Class.forName(valueType));
    					System.out.println("read redis key = " + key + ";field=" + field +"; value ="+value);
    				}
    			};break;
    		}
    		return result;
    	}
    	catch(Throwable ex){
    		throw ex;
    	}
    }
}
