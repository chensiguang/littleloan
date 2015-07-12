package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerChatHistoryItem;

public interface CustomerChatHistoryService {

	/**
	 * 按客户名称，类型，经理和时间获取交往记录
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<CustomerChatHistoryItem> getCustomerChatHistory(CustomerChatHistoryItem item) throws ErrorCodeException;
	
	/**
	 * 插入一条聊天记录
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public CustomerChatHistoryItem insertCustomerChatHistory(CustomerChatHistoryItem item) throws ErrorCodeException;
	
	/**
	 * 修改一条聊天记录
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean updateCustomerChatHistory(CustomerChatHistoryItem item) throws ErrorCodeException;
	
	/**
	 * 删除一条聊天记录
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteCustomerChatHistory(Integer id) throws ErrorCodeException;
	
	/**
	 * 上传文件
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean uploadFile(String fileName, String filePath) throws ErrorCodeException;

	/**
	 * 获取单条交往记录
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public CustomerChatHistoryItem getOneCustomerChatHistory(Integer id) throws ErrorCodeException;
	
}
