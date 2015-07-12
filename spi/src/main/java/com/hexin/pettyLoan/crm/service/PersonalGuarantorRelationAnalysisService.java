package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.PersonalGuarantorItem;
import com.hexin.pettyLoan.crm.model.PersonalWorkFriendItem;

/**
 * 个人担保人关系分析接口
 * @author chenyongzhi
 *
 */
public interface PersonalGuarantorRelationAnalysisService {
	/**
	 * 根据客户编号分析并保存客户的担保人关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取客户的担保人关系对象
	 * @param customerId
	 * @return List<PersonalGuarantorItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条客户担保人关系对象
	 * @param item
	 * @return PersonalGuarantorItem
	 * @throws ErrorCodeException
	 */
	public PersonalGuarantorItem insertPersonalGuarantorRelation(PersonalGuarantorItem item) throws ErrorCodeException;
}
