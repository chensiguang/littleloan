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
import com.hexin.pettyLoan.crm.model.CustomerJobItem;
import com.hexin.pettyLoan.crm.model.PersonalWorkFriendItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerJobService;
import com.hexin.pettyLoan.crm.service.PersonalWorkFriendRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 同事关系分析实现类
 * @author chenyognzhi
 *
 */
@Service("personalWorkFriendRelationAnalysisService")
public class PersonalWorkFriendRelationAnalysisServiceImpl implements
		PersonalWorkFriendRelationAnalysisService {
	
	/**
	 * 客户基本信息
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	/**
	 * 客户工作信息接口
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
	@AccessLog(description = "analysis personal work friend relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		if(null != customerId){
			try {
				//当前客户基本信息
				CustomerBasicInformationItem currentCustomer = customerBasicInformationService.getOneCustomerBasicInformation(customerId);
				if(null != currentCustomer){
					//客户工作信息情况
					List<CustomerJobItem> jobs = customerJobService.getSameWorkUnitCustomerJobByCustomerId(customerId);
					if(null != jobs){
						for(int i=0; i<jobs.size(); i++){
							CustomerJobItem job = jobs.get(i);
							//关联客户（相同单位）
							CustomerBasicInformationItem customer = customerBasicInformationService.getOneCustomerBasicInformation(job.getCustomerId());
							if(null != customer && customer.getId() != customerId){		//同一个客户不做同事关联关系处理
								//判断当前客户是否已经做同事关联关系处理
								PersonalWorkFriendItem para = new PersonalWorkFriendItem();
								para.setCustomerId(customerId);
								para.setCertificateType(customer.getCertificateType());
								para.setCertificateNo(customer.getCertificateNo());
								PersonalWorkFriendItem item = readDao.getOne(PersonalWorkFriendItem.NAMESPACE, para);
								//不存在，则添加到同事关联关系表中
								if(null == item){
									item = new PersonalWorkFriendItem();
									item.setAddress(job.getAddress());
									item.setCreater(customerId);
									item.setCtime(new Date());
									item.setCustomerId(customerId);
									item.setDuty(job.getPost());	//个人职务
									item.setIsvalid(Short.valueOf("1"));
									item.setTelphone(job.getTelephone());
									item.setCertificateType(customer.getCertificateType());
									item.setCertificateNo(customer.getCertificateNo());
									//同事姓名
									if(null != customer){
										item.setName(customer.getCustomerName());
									}
									item.setWorkUnit(job.getCompany());
									//是否是系统用户(组织编号相同表示是同一个系统)
									if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
										item.setIsSystemUser(1);
									}else{
										item.setIsSystemUser(0);
									}
									//添加同事关系
									writeDao.insert(PersonalWorkFriendItem.NAMESPACE, item);
								}else{	//存在判断是否是系统用户
									if(1 != item.getIsSystemUser()){
										//是否是系统用户(组织编号相同表示是同一个系统)
										if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
											item.setMtime(new Date());
											item.setIsSystemUser(1);
											writeDao.update(PersonalWorkFriendItem.NAMESPACE, item);
										}
									}
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
	@AccessLog(description = "query personal work friend relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			PersonalWorkFriendItem para =	new PersonalWorkFriendItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(PersonalWorkFriendItem.NAMESPACE, para);
			return data;
		}
		return null;
	}

	@Override
	@AccessLog(description = "insert personal work friend relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public PersonalWorkFriendItem insertWorkFriendRelation(
			PersonalWorkFriendItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(PersonalWorkFriendItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
