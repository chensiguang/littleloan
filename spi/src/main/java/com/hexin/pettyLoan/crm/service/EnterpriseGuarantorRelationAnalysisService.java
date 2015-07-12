package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.EnterpriseGuarantorItem;

/**
 * 企业担保人关系分析接口
 * @author chenyongzhi
 *
 */
public interface EnterpriseGuarantorRelationAnalysisService {

	/**
	 * 根据客户编号分析并保存企业担保人关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取企业担保人关系对象
	 * @param customerId
	 * @return List<EnterpriseGuarantorItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条企业担保人关系对象
	 * @param item
	 * @return EnterpriseGuarantorItem
	 * @throws ErrorCodeException
	 */
	public EnterpriseGuarantorItem insertEnterpriseGuarantorRelation(EnterpriseGuarantorItem item) throws ErrorCodeException;
}
