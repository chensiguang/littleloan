package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.PersonalFinanceItem;

public interface PersonalFinanceService {

	/**
	 * 按客户名称，类型，证件号码获取个人财务
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PersonalFinanceItem> getPersonalFinanceList(PersonalFinanceItem item) throws ErrorCodeException;
	
	/**
	 * 按客户名称，证件号码获取客户信息
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<PersonalFinanceItem> getCustomerInfoList(PersonalFinanceItem item) throws ErrorCodeException;
	
	/**
	 * 插入一条个人财务
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public PersonalFinanceItem insertPersonalFinance(PersonalFinanceItem item) throws ErrorCodeException;
	
	/**
	 * 上传文件
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean uploadFile(String fileName, String filePath) throws ErrorCodeException;

}
