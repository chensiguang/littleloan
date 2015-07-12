package com.hexin.pettyLoan.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.common.SimpleWorkflowConstants;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.model.SimpleWorkflowConfigItem;
import com.hexin.pettyLoan.system.model.SimpleWorkflowExecuteItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.FlexkeyService;
import com.hexin.pettyLoan.system.service.SimpleWorkflowService;
import com.hexin.pettyLoan.system.service.UserinfoService;

@Service("simpleWorkflowService")
public class SimpleWorkflowServiceImpl implements SimpleWorkflowService {

	private final static Log logger  = LogFactory.getLog(SimpleWorkflowServiceImpl.class);
	@Resource(name="userinfoService")
	UserinfoService userinfoService;
	
	@Resource(name="flexkeyService")
	FlexkeyService flexkeyService;
	
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	
	@Override
	public Boolean isNeedApprove(String workflowName, Integer userId) throws ErrorCodeException{
		UserinfoItem user = userinfoService.getOneUserinfo(userId);
		SimpleWorkflowConfigItem configItem = getOneConfig(workflowName, user.getOrgnizationId());
		if(configItem == null) {//没有配置过
			try{
				configItem = new SimpleWorkflowConfigItem();
				configItem.setOrgnizationId(user.getOrgnizationId());
				configItem.setWorkflowName(workflowName);
				configItem.setResult("no");
				configItem.setCreater(userId);
				writeDao.insert(SimpleWorkflowConfigItem.NAMESPACE, configItem);
			}
			catch(ErrorCodeException ex){
				throw ex;
			}
			return false;//如果没配置，默认不需要审核
		}
		else{
			if(configItem.getResult().equals("yes"))//yes需要审核，no不需要审核
				return true;
			else
				return false;
		}
	}

	@Override
	public SimpleWorkflowConfigItem getOneConfig(Integer configId){
		SimpleWorkflowConfigItem params = new SimpleWorkflowConfigItem();
		params.setId(configId);
		return readDao.getOne(SimpleWorkflowConfigItem.NAMESPACE, params);
	}
	@Override
	public SimpleWorkflowConfigItem getOneConfig(String workflowName, Integer orgnizationId){
		SimpleWorkflowConfigItem params = new SimpleWorkflowConfigItem();
		params.setWorkflowName(workflowName);
		params.setOrgnizationId(orgnizationId);
		return readDao.getOne(SimpleWorkflowConfigItem.NAMESPACE, params);
	}
	@Override
	@Transactional
	public SimpleWorkflowExecuteItem addWorkflowExecute(String workflowName,
			String description, Integer userId, String invokeClass,
			String invokeMethod, String invokeParams, String invokeParamTypes) throws ErrorCodeException {
		UserinfoItem user = userinfoService.getOneUserinfo(userId);
		SimpleWorkflowConfigItem configItem = getOneConfig(workflowName, user.getOrgnizationId());//不可能会有不存在的情况
		SimpleWorkflowExecuteItem exeItem = new SimpleWorkflowExecuteItem();
		exeItem.setConfigId(configItem.getId());
		exeItem.setDescription(description);
		String[] roleArray = configItem.getParams().split(",");
		
		List<String> userList = new ArrayList<String>();
		for(String roleId : roleArray){
			List<UserinfoItem> uList = userinfoService.getUserinfoListByOrgnizationAndRole(user.getOrgnizationId(), roleId);
			userList = mergeUserList(userList, uList);
		}
		exeItem.setDealUser(list2String(userList));
		exeItem.setStatus("待审批");
		exeItem.setInvokeClass(invokeClass);
		exeItem.setInvokeMethod(invokeMethod);
		exeItem.setInvokeParams(invokeParams);
		exeItem.setInvokeParamTypes(invokeParamTypes);
		exeItem.setCreater(userId);
		writeDao.insert(SimpleWorkflowExecuteItem.NAMESPACE, exeItem);
		return exeItem;
	}
	
	@Override
	@Transactional(rollbackFor={Exception.class})
	public SimpleWorkflowExecuteItem approveWorkflowExecute(Integer executeId, String status, String approveDescription, Integer approveUserId) throws Exception{
		SimpleWorkflowExecuteItem exeItem = readDao.getOne(SimpleWorkflowExecuteItem.NAMESPACE, new SimpleWorkflowExecuteItem(executeId));
		if(status.equals("审批不通过")){
			exeItem.setStatus(status);
			exeItem.setApproveUserId(approveUserId);
			exeItem.setApproveDescription(approveDescription);
			writeDao.update(SimpleWorkflowExecuteItem.NAMESPACE, exeItem);
		}
		else{
			exeItem.setStatus(status);
			exeItem.setApproveUserId(approveUserId);
			exeItem.setApproveDescription(approveDescription);
			writeDao.update(SimpleWorkflowExecuteItem.NAMESPACE, exeItem);
			
			try{
				invokeExeItem(exeItem);
			}
			catch(Exception ex){
				throw ex;
			}
		}
		return exeItem;
	}
	
	private Object invokeExeItem(SimpleWorkflowExecuteItem exeItem) throws Exception{
		Object o = null;
		try {
			o = Class.forName(exeItem.getInvokeClass()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method initMethod = null;
		try {
			initMethod = o.getClass().getMethod("initMethod");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}//执行初始化方法
		try {
			initMethod.invoke(o);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Method doMethod = null;
		for(Method m : o.getClass().getMethods()){
			if(m.getName().equals(exeItem.getInvokeMethod())){
				doMethod = m;
				break;
			}
		}
		if(doMethod==null) return null;
		String[] invokeParamTypeList= exeItem.getInvokeParamTypes().split(",");
		Object[] ooo = JSONUtil.parseObjectArray(exeItem.getInvokeParams());
		List<Object> objectList = new ArrayList<Object>();
		for(int i=0; i<ooo.length; i++){
			try {
				objectList.add(JSON.parseObject(JSONUtil.toJsonString(ooo[i]), Class.forName(invokeParamTypeList[i])));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		Object result = null;
		try {
			result = doMethod.invoke(o, objectList.toArray());
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			throw new Exception(e.getTargetException().getMessage());
		}
		return result;
	}
	@Override
	public List<SimpleWorkflowConfigItem> getSimpleWorkflowConfigListByOrgnizationId(Integer orgnizationId){
		SimpleWorkflowConfigItem params = new SimpleWorkflowConfigItem();
		params.setOrgnizationId(orgnizationId);
		return readDao.query(SimpleWorkflowConfigItem.NAMESPACE,"queryByOrgnizationId", params);
	}

	@Override
	@Transactional(rollbackFor={ErrorCodeException.class})
	public Boolean setSimpleWorkflowConfig(List<SimpleWorkflowConfigItem> list) throws ErrorCodeException{
		try{
			for(SimpleWorkflowConfigItem item : list){
				saveSimpleWorkflowItem(item);
			}
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
		return true;
	}
	
	@Override
	public SimpleWorkflowConfigItem saveSimpleWorkflowItem(SimpleWorkflowConfigItem item) throws ErrorCodeException{
		try{
			SimpleWorkflowConfigItem params = new SimpleWorkflowConfigItem();
			params.setWorkflowName(item.getWorkflowName());
			params.setOrgnizationId(item.getOrgnizationId());
			SimpleWorkflowConfigItem config = readDao.getOne(SimpleWorkflowConfigItem.NAMESPACE, params);
			if(config == null){
				writeDao.insert(SimpleWorkflowConfigItem.NAMESPACE, item);
			}
			else{
				item.setId(config.getId());
				writeDao.update(SimpleWorkflowConfigItem.NAMESPACE, item);
			}
			return item;
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
	}
	
	@Override
	public List<SimpleWorkflowExecuteItem> getWorkflowExecuteByUserId(SimpleWorkflowExecuteItem params){
		try{
			return readDao.page(SimpleWorkflowExecuteItem.NAMESPACE, params);
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
	}
	
	@Override
	public int getCount(SimpleWorkflowExecuteItem params){
		try{
			return readDao.count(SimpleWorkflowExecuteItem.NAMESPACE, params);
		}
		catch(ErrorCodeException ex){
			throw ex;
		}
	}
	private String list2String(List<String> idList){
		String r = ",";
		for(String s : idList){
			r += s +",";
		}
		return r;
	}
	private List<String> mergeUserList(List<String> oldList, List<UserinfoItem> newList){
		for(UserinfoItem user : newList){
			if(!oldList.contains(user.getId().toString())){
				oldList.add(user.getId().toString());
			}
		}
		return oldList;
	}
}
