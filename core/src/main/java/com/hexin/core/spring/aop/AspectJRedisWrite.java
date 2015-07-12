package com.hexin.core.spring.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hexin.core.annotation.AnnotationUtil;
import com.hexin.core.annotation.RedisRead;
import com.hexin.core.annotation.RedisWrite;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.ReflectionUtil;
import com.hexin.core.util.StringUtil;
import com.hexin.core.util.cache.ShardedRedisUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.core.util.web.WebUtil;
import com.vteba.utils.web.RequestContextHolder;

//@Aspect
public class AspectJRedisWrite {
	private final static Log logger  = LogFactory.getLog(AspectJRedisWrite.class);
	
	ShardedRedisUtil redisUtil;
	public ShardedRedisUtil getRedisUtil() {
		return redisUtil;
	}

	public void setRedisUtil(ShardedRedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

//	@AfterThrowing(pointcut="@annotation(com.hexin.core.annotation.RedisWrite)", throwing="e")
    public void readAfterThrowing(JoinPoint joinPoint, Throwable e){
		HttpServletRequest request = RequestContextHolder.getRequest();
		String message = e.getMessage();
		message += "url = " + WebUtil.getDomainUrl(request)+request.getRequestURI() +"\r\n";
		message += "params = " + WebUtil.getParameters(request) + "\r\n";
		message += "referer = " + request.getHeader("Referer") + "\r\n";
		message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
		logger.info(message);
    }
	
//	@Around("@annotation(com.hexin.core.annotation.RedisWrite)")
    public Object readAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
    	Object[] args = joinPoint.getArgs();
    	String methodName = joinPoint.getSignature().getName();
    	String type = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisWrite.class, "type").toString();
    	String keyName = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisWrite.class, "keyName").toString();
    	String fieldName = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisWrite.class, "fieldName").toString();
    	String remove = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, RedisWrite.class, "remove").toString();
    	try{
    		type = type.substring(type.lastIndexOf(".")+1);
    		switch(type){
    			case "KeyValue":{
    				result = joinPoint.proceed(args);
    				String key = StringUtil.analysisDescription(keyName, args, result);
    				if(remove.equals("No")){
    					String json = JSONUtil.toJsonString(result);
	    				redisUtil.set(key, json);
	    				System.out.println("set redis key = "+key + "; value=" + json);
    				}
    				else{
    					redisUtil.removeKey(key);
    					System.out.println("remove redis key = "+key);
    				}
    			};break;
    			case "Map":{
    				result = joinPoint.proceed(args);
    				String field = StringUtil.analysisDescription(fieldName, args, result);
    				String key = StringUtil.analysisDescription(keyName, args, result);
    				if(remove.equals("No")){
    					String json = JSONUtil.toJsonString(result);
    					redisUtil.setMap(key, field, json);
    					System.out.println("set redis key = "+key + ";field="+ field +"; value=" + json);
    				}
    				else{
    					redisUtil.removeMapField(key, field);
    					System.out.println("remove redis key = "+key +"; field=" + field);
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
