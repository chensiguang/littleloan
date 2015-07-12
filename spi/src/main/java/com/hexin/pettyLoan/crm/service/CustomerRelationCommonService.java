package com.hexin.pettyLoan.crm.service;

import java.util.List;
import java.util.Map;

import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;

/**
 * 客户关系公共接口（包括个人、机构 的各个关系）
 * @author chenyongzhi
 *
 */
public interface CustomerRelationCommonService {

	/**
	 * 根据客户关系获取对应的记录（对应class名称作为key值，如：PersonalWorkFriendItem.class.getSimpleName()）
	 * @param customerId 客户编号
	 * @return Map<String, List<AbstractCustomerRelationItem>>
	 */
	public Map<String, List<AbstractCustomerRelationItem>> find(Integer customerId);
	
	/**
	 * 根据客户编号与类型从新对客户管理关系进行分析
	 * @param customerId
	 * @param customerType
	 */
	public void analysisCustomerRelation(Integer customerId, String customerType);
}
