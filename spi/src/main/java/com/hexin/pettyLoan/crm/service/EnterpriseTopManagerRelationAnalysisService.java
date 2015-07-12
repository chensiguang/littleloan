package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.EnterpriseStockerItem;
import com.hexin.pettyLoan.crm.model.EnterpriseTopManagerItem;

/**
 * 企业高管关系分析接口
 * @author chenyongzhi
 *
 */
public interface EnterpriseTopManagerRelationAnalysisService {

	/**
	 * 根据客户编号分析并保存企业高管关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取企业高管关系对象
	 * @param customerId
	 * @return List<EnterpriseTopManagerItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条企业高管关系对象
	 * @param item
	 * @return EnterpriseTopManagerItem
	 * @throws ErrorCodeException
	 */
	public EnterpriseTopManagerItem insertEnterpriseTopManagerRelation(EnterpriseTopManagerItem item) throws ErrorCodeException;
}
