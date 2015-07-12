package com.hexin.pettyLoan.system.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.OrgnizationItem;
import com.hexin.pettyLoan.system.model.RoleItem;

public interface FunctionService {
	/**
	 * 获取一个功能
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public FunctionItem getOneFunction(Integer id) throws ErrorCodeException;
	/**
	 * 获取符合条件的功能（可分页）
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<FunctionItem> getFunctionList(FunctionItem params) throws ErrorCodeException;
	/**
	 * 更新一个功能
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public FunctionItem updateFunction(FunctionItem item) throws ErrorCodeException;
	/**
	 * 获取符合条件的功能数量
	 * @param params
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(FunctionItem params) throws ErrorCodeException;
	/**
	 * 添加一个功能
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public FunctionItem insertFunction(FunctionItem item) throws ErrorCodeException;
	/**
	 * 删除一个功能
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteFunction(FunctionItem item) throws ErrorCodeException;
	
	/**
	 * 根据公司获取该公司类型对应的功能列表
	 * @param params
	 * @return
	 */
	public List<FunctionItem> getOrgnizationFunction(Integer orgnizationId);
	
}
