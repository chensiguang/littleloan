package com.hexin.pettyLoan.crm.scheduling;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerRelationCommonService;

/**
 * 客户关系分析定时任务
 * @author Administrator
 *
 */
@Service("expireCustomerRelationAnalysisJobTask")
public class ExpireCustomerRelationAnalysisJobTask {
	
	/**
	 * 客户基本信息
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	/**
	 * 客户关联关系
	 */
	@Resource(name = "customerRelationCommonService")
	private CustomerRelationCommonService customerRelationCommonService;
	
	/**
	 * 客户关系分析
	 */
	public void doAnalysis(){
		CustomerBasicInformationItem para = new CustomerBasicInformationItem();
		para.setIsvalid(Short.valueOf("1"));
		List<CustomerBasicInformationItem>	customers = customerBasicInformationService.getCustomerBasicInformationList(para);
		if(null != customers && customers.size() > 0){
			for(int i=0; i<customers.size(); i++){
				CustomerBasicInformationItem customer = customers.get(i);
				customerRelationCommonService.analysisCustomerRelation(customer.getId(), customer.getCustomerType());
			}
		}
				
	}
}
