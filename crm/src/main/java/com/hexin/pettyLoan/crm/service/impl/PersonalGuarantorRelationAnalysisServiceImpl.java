package com.hexin.pettyLoan.crm.service.impl;

import java.util.ArrayList;
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
import com.hexin.pettyLoan.crm.model.PersonalGuarantorItem;
import com.hexin.pettyLoan.crm.model.PersonalWorkFriendItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.PersonalGuarantorRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 个人担保人关系实现类
 * @author chenyognzhi
 *
 */
@Service("personalGuarantorRelationAnalysisService")
public class PersonalGuarantorRelationAnalysisServiceImpl implements
		PersonalGuarantorRelationAnalysisService {
	/**
	 * 客户基本信息
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	@AccessLog(description = "analysis personal guarantor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		if(null != customerId){
			try {
				//当前客户基本信息
				CustomerBasicInformationItem currentCustomer = customerBasicInformationService.getOneCustomerBasicInformation(customerId);
				if(null != currentCustomer){
					//担保人信息情况
					List guarantors = new ArrayList<>();
					if(null != guarantors){
						for(int i=0; i<guarantors.size(); i++){
							guarantors.get(i);
							//关联客户（相同单位）
							CustomerBasicInformationItem customer = customerBasicInformationService.getOneCustomerBasicInformation(1);
							if(null != customer && customer.getId() != customerId){		//同一个客户不做同事关联关系处理
								//判断当前客户是否已经做同事关联关系处理
								PersonalGuarantorItem para = new PersonalGuarantorItem();
								para.setCustomerId(customerId);
								para.setCertificateType(customer.getCertificateType());
								para.setCertificateNo(customer.getCertificateNo());
								int count = readDao.count(PersonalGuarantorItem.NAMESPACE, para);
								//不存在，则添加到担保人关联关系表中
								if(count <= 0){
									PersonalWorkFriendItem item = new PersonalWorkFriendItem();
									item.setCreater(customerId);
									item.setCtime(new Date());
									item.setCustomerId(customerId);
									item.setIsvalid(Short.valueOf("1"));
									item.setCertificateType(customer.getCertificateType());
									item.setCertificateNo(customer.getCertificateNo());
									//是否是系统用户(组织编号相同表示是同一个系统)
									if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
										item.setIsSystemUser(1);
									}else{
										item.setIsSystemUser(0);
									}
									//添加同事关系
									writeDao.insert(PersonalWorkFriendItem.NAMESPACE, item);
								}
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
	@AccessLog(description = "query personal guarantor relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			PersonalGuarantorItem para =	new PersonalGuarantorItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(PersonalGuarantorItem.NAMESPACE, para);
			return data;
		}
		return null;
	}

	@Override
	@AccessLog(description = "insert personal guarantor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public PersonalGuarantorItem insertPersonalGuarantorRelation(
			PersonalGuarantorItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(PersonalGuarantorItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
