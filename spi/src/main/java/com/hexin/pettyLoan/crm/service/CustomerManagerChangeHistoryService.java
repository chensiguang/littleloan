package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerManagerChangeHistoryItem;

/**
 * 客户经理变更接口
 * @author csg
 *
 */
public interface CustomerManagerChangeHistoryService {

	/**
	 * 查询客户列表
	 * @param item 
	 * @return 
	 * @throws ErrorCodeException
	 */
	public List<CustomerManagerChangeHistoryItem> getCustomerList(CustomerManagerChangeHistoryItem item) throws ErrorCodeException;
	
	/**
	 * 修改客户经理
	 * @param id 
	 * @throws ErrorCodeException
	 */
	public Boolean updateUserManager(Integer id) throws ErrorCodeException;
	
	/**
	 * 获取变更记录
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<CustomerManagerChangeHistoryItem> getChangeList(CustomerManagerChangeHistoryItem item) throws ErrorCodeException;
	
	/**
	 * 获取一条变更记录
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public CustomerManagerChangeHistoryItem getOne(CustomerManagerChangeHistoryItem item) throws ErrorCodeException;
	
	
}
