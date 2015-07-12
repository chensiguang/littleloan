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
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem.CustomerType;
import com.hexin.pettyLoan.crm.model.DepartmentShareholderItem;
import com.hexin.pettyLoan.crm.model.EnterpriseStockerItem;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.DepartmentInformationService;
import com.hexin.pettyLoan.crm.service.DepartmentShareholderService;
import com.hexin.pettyLoan.crm.service.EnterpriseStockerRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 企业股东关系分析实现类
 * @author chenyongzhi
 *
 */
@Service("enterpriseStockerRelationAnalysisService")
public class EnterpriseStockerRelationAnalysisServiceImpl implements
		EnterpriseStockerRelationAnalysisService {
	/**
	 * 客户基本信息
	 */
	@Resource(name = "customerBasicInformationService")
	private CustomerBasicInformationService customerBasicInformationService;
	
	/**
	 * 机构客户股东信息
	 */
	@Resource(name = "departmentShareholderService")
	private DepartmentShareholderService departmentShareholderService;
	
	/**
	 * 机构基本信息服务接口
	 */
	@Resource(name = "departmentInformationService")
	private DepartmentInformationService departmentInformationService;
	
	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	
	@Override
	@AccessLog(description = "analysis enterprise stocker relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
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
						DepartmentShareholderItem para = new DepartmentShareholderItem();
						para.setCustomerId(customer.getId());
						List<DepartmentShareholderItem> stockers =	departmentShareholderService.getDepartmentShareholderList(para);
						for(int j=0; j<stockers.size(); j++){
							DepartmentShareholderItem holder = stockers.get(j);
							//判断是否已经存在股东关联关系表中
							EnterpriseStockerItem stockPara = new EnterpriseStockerItem();
							stockPara.setStockerName(holder.getName());
							stockPara.setStockerType(holder.getHolderType());
							EnterpriseStockerItem stocker =	readDao.getOne(EnterpriseStockerItem.NAMESPACE, stockPara);
							if(null == stocker){	//不存在，则添加；否则，更新持股信息
								stocker = new  EnterpriseStockerItem();
								stocker.setCustomerId(customerId);
								stocker.setStockerName(holder.getName());
								stocker.setStockerType(holder.getHolderType());
								stocker.setHoldAmount(holder.getHoldings());
								stocker.setHoldNumber(holder.getShares());
								stocker.setHoldScale(holder.getRatio() + "");
								stocker.setCreater(customerId);
								stocker.setCtime(new Date());
								stocker.setIsvalid(Short.valueOf("1"));
								//是否是系统用户(组织编号相同表示是同一个系统)
								if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
									stocker.setIsSystemUser(1);
								}else{
									stocker.setIsSystemUser(0);
								}
								//添加股东关联关系
								writeDao.insert(EnterpriseStockerItem.NAMESPACE, stocker);
							}else{
								if(1 != stocker.getIsSystemUser()){			//是否是系统用户(组织编号相同表示是同一个系统)
									if(currentCustomer.getOrganizationId() == customer.getOrganizationId()){
										stocker.setIsSystemUser(1);
									} 
								}
								stocker.setHoldAmount(holder.getHoldings());
								stocker.setHoldNumber(holder.getShares());
								stocker.setHoldScale(holder.getRatio().toString());
								stocker.setMtime(new Date());
								writeDao.update(EnterpriseStockerItem.NAMESPACE, stocker);
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
	@AccessLog(description = "query enterprise stocker relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			EnterpriseStockerItem para =	new EnterpriseStockerItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(EnterpriseStockerItem.NAMESPACE, para);
			return data;
		}
		
		return null;	
	}

	@Override
	@AccessLog(description = "insert enterprise stocker relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public EnterpriseStockerItem insertEnterpriseStockerRelation(
			EnterpriseStockerItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(EnterpriseStockerItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
