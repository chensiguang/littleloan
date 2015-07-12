package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.PersonalWorkFriendItem;

/**
 * 个人同事关系分析接口
 * @author chenyongzhi
 *
 */
public interface PersonalWorkFriendRelationAnalysisService {
	
	/**
	 * 根据客户编号分析并保存客户的同事关联关系
	 * @param customerId
	 */
	public void analysisAndSaveRelation(Integer customerId);
	
	/**
	 * 根据客户编号获取客户的同事关系对象
	 * @param customerId
	 * @return List<PersonalWorkFriendItem>
	 * @throws ErrorCodeException
	 */
	public List<AbstractCustomerRelationItem> find(Integer customerId) throws ErrorCodeException;
	
	/**
	 * 加入一条客户同事关系对象
	 * @param item
	 * @return PersonalWorkFriendItem
	 * @throws ErrorCodeException
	 */
	public PersonalWorkFriendItem insertWorkFriendRelation(PersonalWorkFriendItem item) throws ErrorCodeException;
}
