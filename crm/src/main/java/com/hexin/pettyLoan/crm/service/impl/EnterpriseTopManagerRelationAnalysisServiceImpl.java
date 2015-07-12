package com.hexin.pettyLoan.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.AbstractCustomerRelationItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.model.DepartmentInformationItem;
import com.hexin.pettyLoan.crm.model.DepartmentSeniorItem;
import com.hexin.pettyLoan.crm.model.EnterpriseTopManagerItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem.CustomerType;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.DepartmentInformationService;
import com.hexin.pettyLoan.crm.service.DepartmentSeniorService;
import com.hexin.pettyLoan.crm.service.EnterpriseTopManagerRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 企业担高管关系实现类
 * @author chenyognzhi
 *
 */
@Service("enterpriseTopManagerRelationAnalysisService")
public class EnterpriseTopManagerRelationAnalysisServiceImpl implements
		EnterpriseTopManagerRelationAnalysisService {
	
	/**
	 * 客户基本信息
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	/**
	 * 机构基本信息服务接口
	 */
	@Resource(name = "departmentInformationService")
	private DepartmentInformationService departmentInformationService;
	
	/**
	 * 机构客户高端信息服务接口
	 */
	@Resource(name = "departmentSeniorService")
	private DepartmentSeniorService departmentSeniorService;
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	
	@Override
	@AccessLog(description = "analysis enterprise top manager relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		if(null != customerId){
			try {
				//当前客户基本信息
				CustomerBasicInformationItem currentCustomer = customerBasicInformationService.getOneCustomerBasicInformation(customerId);
				//判断是否是机构客户
				if(null != currentCustomer && currentCustomer.getCustomerType().equals(CustomerType.ENTERPRISE.getCode())){
					//获取机构客户基本信息
					DepartmentInformationItem department =	departmentInformationService.getOneDepartmentInformationByCustomerId(customerId);
					//获取同一个机构不同客户列表
					List<CustomerBasicInformationItem>	customers = customerBasicInformationService.getSameDepartmentByNameAndRegNum(currentCustomer.getCustomerName(), null != department ? department.getRegNum() : "");
					for(int i=0; i<customers.size(); i++){
						CustomerBasicInformationItem customer = customers.get(i);
						DepartmentSeniorItem para = new DepartmentSeniorItem();
						para.setCustomerId(customer.getId());
						List<DepartmentSeniorItem>  seniors =	departmentSeniorService.getDepartmentSeniorList(para);
						
						for(int j=0; j<seniors.size(); j++){
							DepartmentSeniorItem senior = seniors.get(j);
							//判断是否已经存在企业高管关联关系表中
							EnterpriseTopManagerItem topPara = new EnterpriseTopManagerItem();
							topPara.setUsername(senior.getSeniorName());
							topPara.setManagerType(senior.getSeniorType());
							topPara.setWorkDepartment(senior.getDepartmentName());
							
							EnterpriseTopManagerItem topManager =	readDao.getOne(EnterpriseTopManagerItem.NAMESPACE, topPara);
							if(null == topManager){	//不存在，则添加；否则，更新高管信息
								topManager = new  EnterpriseTopManagerItem();
								topManager.setCustomerId(customerId);
								topManager.setDuty(senior.getHoldings());
								topManager.setManagerType(senior.getSeniorType());
								topManager.setUsername(senior.getSeniorName());
								topManager.setWorkDepartment(senior.getDepartmentName());
								topManager.setYearIncome(senior.getShares());
								topManager.setCreater(customerId);
								topManager.setCtime(new Date());
								topManager.setIsvalid(Short.valueOf("1"));
								//是否是系统用户(组织编号相同表示是同一个系统)
								if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
									topManager.setIsSystemUser(1);
								}else{
									topManager.setIsSystemUser(0);
								}
								
								//添加高管关联关系
								writeDao.insert(EnterpriseTopManagerItem.NAMESPACE, topManager);
							}else{
								if(1 != topManager.getIsSystemUser()){			//是否是系统用户(组织编号相同表示是同一个系统)
									if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
										topManager.setIsSystemUser(1);
									}
								}
								topManager.setDuty(senior.getHoldings());
								topManager.setManagerType(senior.getSeniorType());
								topManager.setUsername(senior.getSeniorName());
								topManager.setWorkDepartment(senior.getDepartmentName());
								topManager.setYearIncome(senior.getShares());
								topManager.setMtime(new Date());
								writeDao.update(EnterpriseTopManagerItem.NAMESPACE, topManager);
							}
						}
					}
				}
			} catch (ErrorCodeException e) {
				// TODO: handle exception
			}
		}
		
	}

	@Override
	@AccessLog(description = "query enterprise top manager relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			EnterpriseTopManagerItem para =	new EnterpriseTopManagerItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(EnterpriseTopManagerItem.NAMESPACE, para);
			return data;
		}
		
		return null;	
	}

	@Override
	@AccessLog(description = "insert enterprise top manager relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public EnterpriseTopManagerItem insertEnterpriseTopManagerRelation(
			EnterpriseTopManagerItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(EnterpriseTopManagerItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
