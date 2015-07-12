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
import com.hexin.pettyLoan.crm.model.EnterpriseSponsorItem;
import com.hexin.pettyLoan.crm.model.EnterpriseStockerItem;
import com.hexin.pettyLoan.crm.service.EnterpriseSponsorRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 企业被担保人关系分析实现类
 * @author chenyongzhi
 *
 */
@Service("enterpriseSponsorRelationAnalysisService")
public class EnterpriseSponsorRelationAnalysisServiceImpl implements
		EnterpriseSponsorRelationAnalysisService {

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	@AccessLog(description = "analysis enterprise sponsor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@AccessLog(description = "query enterprise sponsor relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
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
	@AccessLog(description = "insert enterprise sponsor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public EnterpriseSponsorItem insertEnterpriseSponsorRelation(
			EnterpriseSponsorItem item) throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(EnterpriseSponsorItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
