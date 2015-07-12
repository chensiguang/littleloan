package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.EnterpriseStockerItem;

/**
 * 企业股东关系分析接口
 * @author chenyongzhi
 *
 */
public interface EnterpriseStockerRelationAnalysisService {
	
	/**
	 * 根据客户编号分析并保存企业股东关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取企业股东关系对象
	 * @param customerId
	 * @return List<EnterpriseStockerItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条企业股东关系对象
	 * @param item
	 * @return EnterpriseStockerItem
	 * @throws ErrorCodeException
	 */
	public EnterpriseStockerItem insertEnterpriseStockerRelation(EnterpriseStockerItem item) throws ErrorCodeException;
}
