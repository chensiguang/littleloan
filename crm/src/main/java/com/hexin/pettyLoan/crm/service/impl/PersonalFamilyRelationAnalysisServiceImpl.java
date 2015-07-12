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
import com.hexin.pettyLoan.crm.model.CustomerFamilyItem;
import com.hexin.pettyLoan.crm.model.CustomerJobItem;
import com.hexin.pettyLoan.crm.model.PersonalFamilyMemberItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerFamilyService;
import com.hexin.pettyLoan.crm.service.CustomerJobService;
import com.hexin.pettyLoan.crm.service.PersonalFamilyRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 个人家庭关系分析实现类
 * @author chenyongzhi
 *
 */
@Service("personalFamilyRelationAnalysisService")
public class PersonalFamilyRelationAnalysisServiceImpl implements
		PersonalFamilyRelationAnalysisService {
	/**
	 * 客户基本信息接口
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	/**
	 * 客户家庭成员
	 */
	@Resource(name = "customerFamilyService")
	private CustomerFamilyService customerFamilyService;
	
	/**
	 * 客户工作信息
	 */
	@Resource(name = "customerJobService")
	private CustomerJobService customerJobService; 
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}

	@Override
	@AccessLog(description = "analysis family member", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		if(null != customerId){
			try {
				//当前客户基本信息
				CustomerBasicInformationItem currentCustomer = customerBasicInformationService.getOneCustomerBasicInformation(customerId);
				if(null != currentCustomer){
					//1、根据客户家庭成员进行分析（通过客户家庭成员表进行关系分析）
					CustomerFamilyItem familyPara = new CustomerFamilyItem();
					familyPara.setCustomerId(currentCustomer.getId());
					List<CustomerFamilyItem>	familys = customerFamilyService.getCustomerFamilyList(familyPara);
					if(null != familys && familys.size() > 0){
						for(int i=0; i<familys.size(); i++){
							CustomerFamilyItem family = familys.get(i);
							addPresentFamliy(family);							//1、关联关系分析
							analysisAndSaveByFamilier(currentCustomer, family);	//2、关联关系分析
						}
					}
				}
				
			} catch (ErrorCodeException e) {
				// TODO: handle exception
			}
		}
	}
	
	/**
	 * 通过当前家庭成员分析其它客户是否与当前客户存在家庭关联关系
	 * @return
	 */
	private boolean analysisAndSaveByFamilier(CustomerBasicInformationItem currentCustomer, CustomerFamilyItem family){
		if(null != family){
			//根据证件号码、证件类型查询是否存的家庭关系的客户
			PersonalFamilyMemberItem para =	new PersonalFamilyMemberItem();
			para.setCertificateNo(family.getIdentityNo());
			para.setCertificateType(family.getIdentityType());
			para.setIsvalid(Short.valueOf("1"));
			List<PersonalFamilyMemberItem> data = readDao.query(PersonalFamilyMemberItem.NAMESPACE, para);
			if(null != data && data.size() > 0){		//家庭成员分析
				for(int i=0; i<data.size(); i++){
					PersonalFamilyMemberItem customer = data.get(i);
					if(customer.getCustomerId() != currentCustomer.getId()){	//如果属于同一个客户的家庭关联关系
						CustomerBasicInformationItem otherCustomer = customerBasicInformationService.getOneCustomerBasicInformation(customer.getCustomerId()); //查询家庭成员关联的其它客户
						//是否已经存在（根据证件类型与证件号码进行校验）
						PersonalFamilyMemberItem familyPara =	new PersonalFamilyMemberItem();
						familyPara.setCertificateNo(otherCustomer.getCertificateNo());
						familyPara.setCertificateType(otherCustomer.getCertificateType());
						familyPara.setCustomerId(currentCustomer.getId());
						familyPara.setIsvalid(Short.valueOf("1"));
						PersonalFamilyMemberItem otherFamily = readDao.getOne(PersonalFamilyMemberItem.NAMESPACE, familyPara);
						if(null == otherFamily){	//不存在，则添加
							CustomerJobItem job = customerJobService.getCustomerJobByCustomerId(otherCustomer.getId());
							PersonalFamilyMemberItem item =  new PersonalFamilyMemberItem();
							item.setAppellation("--");
							item.setCertificateNo(otherCustomer.getCertificateNo());
							item.setCertificateType(otherCustomer.getCertificateType());
							item.setCreater(1);
							item.setCtime(new Date());
							item.setCustomerId(currentCustomer.getId());
							item.setDuty(null != job ? job.getTitle() : "");
							item.setWorkUnit(null != job ? job.getCompany() : "");
							item.setYearIncome(otherCustomer.getAnnualIncome());
							if(currentCustomer.getOrganizationId() == otherCustomer.getOrganizationId()){
								item.setIsSystemUser(1);
							}
							//添加家庭关系
							writeDao.insert(PersonalFamilyMemberItem.NAMESPACE, item);
						}
					}
				}
				return true;
			}else{				//通过客户基本信息分析
				CustomerBasicInformationItem customerPara =	new CustomerBasicInformationItem();
				customerPara.setCertificateNo(family.getIdentityNo());
				customerPara.setCertificateType(family.getIdentityType());
				customerPara.setIsvalid(Short.valueOf("1"));
				List<CustomerBasicInformationItem>	customers = customerBasicInformationService.getCustomerBasicInformationList(customerPara);
				if(null != customers && customers.size() > 0){
					for (int i = 0; i < customers.size(); i++) {
						CustomerBasicInformationItem otherCustomer = customers.get(i);
						//是否已经存在（根据证件类型与证件号码进行校验）
						PersonalFamilyMemberItem familyPara =	new PersonalFamilyMemberItem();
						familyPara.setCertificateNo(otherCustomer.getCertificateNo());
						familyPara.setCertificateType(otherCustomer.getCertificateType());
						familyPara.setCustomerId(currentCustomer.getId());
						familyPara.setIsvalid(Short.valueOf("1"));
						PersonalFamilyMemberItem otherFamily = readDao.getOne(PersonalFamilyMemberItem.NAMESPACE, familyPara);
						if(null == otherFamily){	//不存在，则添加
							CustomerJobItem job = customerJobService.getCustomerJobByCustomerId(otherCustomer.getId());
							PersonalFamilyMemberItem item =  new PersonalFamilyMemberItem();
							item.setAppellation("--");
							item.setCertificateNo(otherCustomer.getCertificateNo());
							item.setCertificateType(otherCustomer.getCertificateType());
							item.setCreater(1);
							item.setCtime(new Date());
							item.setCustomerId(currentCustomer.getId());
							item.setDuty(null != job ? job.getTitle() : "");
							item.setWorkUnit(null != job ? job.getCompany() : "");
							item.setYearIncome(otherCustomer.getAnnualIncome());
							if(currentCustomer.getOrganizationId() == otherCustomer.getOrganizationId()){
								item.setIsSystemUser(1);
							}
							//添加家庭关系
							writeDao.insert(PersonalFamilyMemberItem.NAMESPACE, item);
						}else{
							//1、根据其客户家庭成员进行分析
							CustomerFamilyItem familyPara2 = new CustomerFamilyItem();
							familyPara2.setCustomerId(currentCustomer.getId());
							List<CustomerFamilyItem>	familys = customerFamilyService.getCustomerFamilyList(familyPara2);
							if(null != familys && familys.size() > 0){
								for(int m=0; m<familys.size(); m++){
									CustomerFamilyItem f = familys.get(m);
									addPresentFamliy(f);
								}
							}
						}
					}
				}
				
			}
		}
		return false;
	}
	
	/**
	 * 添加现有的家庭关系
	 * @param family
	 * @return
	 */
	private boolean addPresentFamliy(CustomerFamilyItem family){
		if(null != family){
			//是否已经存在（根据证件类型与证件号码进行校验）
			PersonalFamilyMemberItem para =	new PersonalFamilyMemberItem();
			para.setCertificateNo(family.getIdentityNo());
			para.setCertificateType(family.getIdentityType());
			para.setCustomerId(family.getCustomerId());
			para.setIsvalid(Short.valueOf("1"));
			PersonalFamilyMemberItem item = readDao.getOne(PersonalFamilyMemberItem.NAMESPACE, para);
			if(null == item){//不存在，则添加到家庭关联关系表中；存在判断是否是系统用户，进行相应的更新操作
				item = new PersonalFamilyMemberItem();
				item.setAppellation(family.getRelation());
				item.setCertificateNo(family.getIdentityNo());
				item.setCertificateType(family.getIdentityType());
				item.setCreater(family.getCustomerId());
				item.setCtime(new Date());
				item.setCustomerId(family.getCustomerId());
				item.setDuty(family.getPost());
				item.setWorkUnit(family.getCompany());
				item.setWorkDepartment(family.getDepartment());
				item.setYearIncome(family.getAnnualIncome());
				CustomerBasicInformationItem customer = customerBasicInformationService.isExistedPersonalCustomer(family.getOrganizationId(), family.getIdentityType(), family.getIdentityNo());
				if(null != customer){
					item.setIsSystemUser(1);
				}
				//添加家庭关系
				writeDao.insert(PersonalFamilyMemberItem.NAMESPACE, item);
			}else {	
				item.setAppellation(family.getRelation());
				item.setCertificateNo(family.getIdentityNo());
				item.setCertificateType(family.getIdentityType());
				item.setCustomerId(family.getCustomerId());
				item.setDuty(family.getPost());
				item.setWorkUnit(family.getCompany());
				item.setWorkDepartment(family.getDepartment());
				item.setYearIncome(family.getAnnualIncome());
				item.setMtime(new Date());
				if(1 != item.getIsSystemUser()){
					//是否是系统用户
					CustomerBasicInformationItem customer = customerBasicInformationService.isExistedPersonalCustomer(family.getOrganizationId(), family.getIdentityType(), family.getIdentityNo());
					if(null != customer){
						item.setIsSystemUser(1);
					}
					writeDao.update(PersonalFamilyMemberItem.NAMESPACE, item);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	@AccessLog(description = "query family member", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			PersonalFamilyMemberItem para =	new PersonalFamilyMemberItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(PersonalFamilyMemberItem.NAMESPACE, para);
			return data;
		}
		
		return null;
	}

	@Override
	@AccessLog(description = "insert family member", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public PersonalFamilyMemberItem insertFamilyRelation(
			PersonalFamilyMemberItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(PersonalFamilyMemberItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}
	
}
