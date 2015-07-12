package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.EnterpriseSponsorItem;

/**
 * 企业被担保人关系分析接口
 * @author chenyongzhi
 *
 */
public interface EnterpriseSponsorRelationAnalysisService {
	/**
	 * 根据客户编号分析并保存企业被担保人关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取企业被担保人关系对象
	 * @param customerId
	 * @return List<EnterpriseSponsorItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条企业被担保人关系对象
	 * @param item
	 * @return EnterpriseSponsorItem
	 * @throws ErrorCodeException
	 */
	public EnterpriseSponsorItem insertEnterpriseSponsorRelation(EnterpriseSponsorItem item) throws ErrorCodeException;
}
