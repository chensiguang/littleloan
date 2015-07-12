package com.hexin.pettyLoan.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.SimpleWorkflowConfigItem;
import com.hexin.pettyLoan.system.model.SimpleWorkflowExecuteItem;

public interface SimpleWorkflowService {
	/**
	 * 根据流程名称和用户id判断该人所属的组织是否需要针对该流程进行审批
	 * @param workflowName
	 * @param userId
	 * @return
	 */
	public Boolean isNeedApprove(String workflowName, Integer userId) throws ErrorCodeException;
	/**
	 * 添加一个新的工作流执行情况
	 * @param workflowName 工作流名称
	 * @param description 工作流说明
	 * @param userId 添加新id
	 * @param invokeClass 执行的类名
	 * @param invokeMethod 执行的方法名
	 * @param invokeParams 执行的参数
	 * @return
	 */
	public SimpleWorkflowExecuteItem addWorkflowExecute(String workflowName, String description, Integer userId,
			String invokeClass, String invokeMethod, String invokeParams, String invokeParamTypes) throws ErrorCodeException;
	/**
	 * 根据id获取一个配置项
	 * @param configId
	 * @return
	 */
	public SimpleWorkflowConfigItem getOneConfig(Integer configId);
	/**
	 * 根据工作流名称和组织id获取一个配置项
	 * @param workflowName
	 * @param orgnizationId
	 * @return
	 */
	public SimpleWorkflowConfigItem getOneConfig(String workflowName, Integer orgnizationId);
	
	/**
	 * 执行审批
	 * @param executeId
	 * @param status
	 * @param approveDescription
	 * @param approveUserId
	 * @return
	 */
	public SimpleWorkflowExecuteItem approveWorkflowExecute(Integer executeId, String status, String approveDescription, Integer approveUserId) throws Exception;
	
	/**
	 * 获取一个组织的流程配置
	 * @param orgnizationId
	 * @return
	 */
	public List<SimpleWorkflowConfigItem> getSimpleWorkflowConfigListByOrgnizationId(Integer orgnizationId);
	
	/**
	 * 配置一个部门的简单流程
	 * @param list
	 * @return
	 */
	public Boolean setSimpleWorkflowConfig(List<SimpleWorkflowConfigItem> list) throws ErrorCodeException;
	
	/**
	 * 保存一个流程配置，先根据workflowName和orgnizationId读取数据，如果不存在，则新增，如果存在，则修改
	 * @param item
	 * @return
	 */
	public SimpleWorkflowConfigItem saveSimpleWorkflowItem(SimpleWorkflowConfigItem item) throws ErrorCodeException;
	
	/**
	 * 根据用户id，获取该用户当前对应的审批流程
	 * @param params
	 * @return
	 */
	public List<SimpleWorkflowExecuteItem> getWorkflowExecuteByUserId(SimpleWorkflowExecuteItem params);
	
	/**
	 * 查询符合条件的记录数量
	 * @param params
	 * @return
	 */
	public int getCount(SimpleWorkflowExecuteItem params);
}
