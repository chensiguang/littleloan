package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.PersonalFamilyMemberItem;

/**
 * 个人家庭关系分析接口
 * @author chenyongzhi
 *
 */
public interface PersonalFamilyRelationAnalysisService {
	
	/**
	 * 根据客户编号分析并保存客户的家庭关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取客户的家庭关系对象
	 * @param customerId
	 * @return List<PersonalFamilyMemberItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条客户家庭关系对象
	 * @param item
	 * @return PersonalFamilyMemberItem
	 * @throws ErrorCodeException
	 */
	public PersonalFamilyMemberItem insertFamilyRelation(PersonalFamilyMemberItem item) throws ErrorCodeException;
}
