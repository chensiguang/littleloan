package com.hexin.pettyLoan.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.StringUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.model.EnterpriseGuarantorItem;
import com.hexin.pettyLoan.crm.model.EnterpriseSponsorItem;
import com.hexin.pettyLoan.crm.model.EnterpriseStockerItem;
import com.hexin.pettyLoan.crm.model.EnterpriseTopManagerItem;
import com.hexin.pettyLoan.crm.model.PersonalFamilyMemberItem;
import com.hexin.pettyLoan.crm.model.PersonalGuarantorItem;
import com.hexin.pettyLoan.crm.model.PersonalSponsorItem;
import com.hexin.pettyLoan.crm.model.PersonalWorkFriendItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem.CustomerType;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerRelationCommonService;
import com.hexin.pettyLoan.crm.service.EnterpriseGuarantorRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.EnterpriseSponsorRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.EnterpriseStockerRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.EnterpriseTopManagerRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.PersonalFamilyRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.PersonalGuarantorRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.PersonalSponsorRelationAnalysisService;
import com.hexin.pettyLoan.crm.service.PersonalWorkFriendRelationAnalysisService;

/**
 * 客户关系公共实现类
 * @author chenyognzhi
 *
 */
@Service("customerRelationCommonService")
public class CustomerRelationCommonServiceImpl implements
		CustomerRelationCommonService {
	
	/**
	 * 客户基本信息接口
	 */
	@Resource(name="customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;

	/**
	 * 个人家庭关系
	 */
	@Resource(name="personalFamilyRelationAnalysisService")
	private PersonalFamilyRelationAnalysisService personalFamilyRelationAnalysisService;
	
	/**
	 * 个人担保人关系
	 */
	@Resource(name="personalGuarantorRelationAnalysisService")
	private PersonalGuarantorRelationAnalysisService personalGuarantorRelationAnalysisService;
	
	/**
	 * 个人被担保人关系
	 */
	@Resource(name="personalSponsorRelationAnalysisService")
	private PersonalSponsorRelationAnalysisService personalSponsorRelationAnalysisService;
	
	/**
	 * 个人同事关系
	 */
	@Resource(name="personalWorkFriendRelationAnalysisService")
	private PersonalWorkFriendRelationAnalysisService personalWorkFriendRelationAnalysisService;
	
	/**
	 * 企业担保人关系
	 */
	@Resource(name="enterpriseGuarantorRelationAnalysisService")
	private EnterpriseGuarantorRelationAnalysisService enterpriseGuarantorRelationAnalysisService;
	
	/**
	 * 企业被担保人关系
	 */
	@Resource(name="enterpriseSponsorRelationAnalysisService")
	private EnterpriseSponsorRelationAnalysisService enterpriseSponsorRelationAnalysisService;
	
	/**
	 * 企业股东关系
	 */
	@Resource(name="enterpriseStockerRelationAnalysisService")
	private EnterpriseStockerRelationAnalysisService enterpriseStockerRelationAnalysisService;
	
	/**
	 * 企业高管关系
	 */
	@Resource(name="enterpriseTopManagerRelationAnalysisService")
	private EnterpriseTopManagerRelationAnalysisService enterpriseTopManagerRelationAnalysisService;
	
	@Override
	@AccessLog(description="find customer relation by customerid", operateType=OperateType.Query, add2DB=Add2DB.Yes)
	public Map<String, List<AbstractCustomerRelationItem>> find(
			Integer customerId) {
		if(null != customerId){
			CustomerBasicInformationItem customer =	customerBasicInformationService.getOneCustomerBasicInformation(customerId);
			if(null == customer){
				throw new ErrorCodeException("CRM00002", ErrorCode.CRM00002);
			}
			Map<String, List<AbstractCustomerRelationItem>> data = new HashMap<String, List<AbstractCustomerRelationItem>>();
			if(StringUtil.isNotEmpty(customer.getCustomerType()) && CustomerType.PERSONAL.getCode().equals(customer.getCustomerType())){				//个人类型客户
				//获取家庭成员
				data.put(PersonalFamilyMemberItem.class.getSimpleName(), personalFamilyRelationAnalysisService.find(customerId));
				//获取同事关系
				data.put(PersonalWorkFriendItem.class.getSimpleName(), personalWorkFriendRelationAnalysisService.find(customerId));
				//获取担保人关系
				data.put(PersonalGuarantorItem.class.getSimpleName(), personalGuarantorRelationAnalysisService.find(customerId));
				//获取被担保人关系
				data.put(PersonalSponsorItem.class.getSimpleName(), personalSponsorRelationAnalysisService.find(customerId));
			}else if(StringUtil.isNotEmpty(customer.getCustomerType()) && CustomerType.ENTERPRISE.getCode().equals(customer.getCustomerType())){		//机构类型客户
				//获取企业担保人对象
				data.put(EnterpriseGuarantorItem.class.getSimpleName(), enterpriseGuarantorRelationAnalysisService.find(customerId));
				//获取企业被担保人（保证人）
				data.put(EnterpriseSponsorItem.class.getSimpleName(), enterpriseSponsorRelationAnalysisService.find(customerId));
				//获取企业股东对象
				data.put(EnterpriseStockerItem.class.getSimpleName(), enterpriseStockerRelationAnalysisService.find(customerId));
				//企业高管对象
				data.put(EnterpriseTopManagerItem.class.getSimpleName(), enterpriseTopManagerRelationAnalysisService.find(customerId));
			}
			return data;
		}
		return null;
	}

	@Override
	public void analysisCustomerRelation(Integer customerId, String customerType) {
		if(null != customerId){
			if(StringUtil.isNotEmpty(customerType) && CustomerType.PERSONAL.getCode().equals(customerType)){				//个人类型客户
				//分析家庭关系
				personalFamilyRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析同事关系
				personalWorkFriendRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析担保人关系
				personalGuarantorRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析被担保人关系
				personalSponsorRelationAnalysisService.analysisAndSaveRelation(customerId);
			}else if(StringUtil.isNotEmpty(customerType) && CustomerType.ENTERPRISE.getCode().equals(customerType)){		//机构类型客户
				//分析机构担保人关系
				enterpriseGuarantorRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析机构被担保人关系
				enterpriseSponsorRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析机构股东关系
				enterpriseStockerRelationAnalysisService.analysisAndSaveRelation(customerId);
				//分析机构高管关系
				enterpriseTopManagerRelationAnalysisService.analysisAndSaveRelation(customerId);
			}	
		}
		
	}

}
