package com.hexin.pettyLoan.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hexin.core.annotation.AnnotationUtil;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.StringUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.core.util.web.WebUtil;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.common.aop.NeedApprove;
import com.hexin.pettyLoan.system.service.SimpleWorkflowService;
import com.vteba.utils.web.RequestContextHolder;

//@Aspect
//@Component
public class AspectJSimpleWorkflowApprove {
	private final static Log logger  = LogFactory.getLog(AspectJSimpleWorkflowApprove.class);
	
	SimpleWorkflowService simpleWorkflowService;
	
	public SimpleWorkflowService getSimpleWorkflowService() {
		return simpleWorkflowService;
	}

	public void setSimpleWorkflowService(SimpleWorkflowService simpleWorkflowService) {
		this.simpleWorkflowService = simpleWorkflowService;
	}

//	@Around("@annotation(com.hexin.pettyLoan.common.aop.NeedApprove)")
	public Object approveAround(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		try {
			HttpServletRequest request = RequestContextHolder.getRequest();
			Integer userId = Integer.parseInt(request.getParameter(Constants.AUTHORITY_USER_ID));
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String workflowName = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, NeedApprove.class, "workflowName").toString();
			String description = AnnotationUtil.getMethodAnnotationValue(joinPoint.getTarget().getClass(), methodName, NeedApprove.class, "description").toString();
			if(!simpleWorkflowService.isNeedApprove(workflowName, userId)){
				result = joinPoint.proceed(joinPoint.getArgs());
			}
			else{
				String invokeParamTypes = "";
				for(Object o : joinPoint.getArgs()){
					invokeParamTypes += o.getClass().getName()+",";
				}
				invokeParamTypes = invokeParamTypes.substring(0, invokeParamTypes.length()-1);
				simpleWorkflowService.addWorkflowExecute(workflowName, StringUtil.analysisDescription(description,joinPoint.getArgs()), userId, className, methodName, JSONUtil.toJsonString(joinPoint.getArgs()),invokeParamTypes);
			}
		} catch (Throwable e) {
			throw e;
		}
		return result;
	}
	
//	@AfterThrowing(pointcut="@annotation(com.hexin.pettyLoan.common.aop.NeedApprove)", throwing="e")
    public void readAfterThrowing(JoinPoint joinPoint, Throwable e){
		HttpServletRequest request = RequestContextHolder.getRequest();
		String message = e.getMessage();
		message += "url = " + WebUtil.getDomainUrl(request)+request.getRequestURI() +"\r\n";
		message += "params = " + WebUtil.getParameters(request) + "\r\n";
		message += "referer = " + request.getHeader("Referer") + "\r\n";
		message += "remoteAddr = " + WebUtil.getIpAddr(request) + "\r\n";
		logger.info(message);
    }
	
}
