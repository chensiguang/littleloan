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
import com.hexin.pettyLoan.crm.model.PersonalSponsorItem;
import com.hexin.pettyLoan.crm.service.PersonalSponsorRelationAnalysisService;
import com.vteba.service.context.spring.ApplicationContextHolder;

/**
 * 个人被担保人关系分析实现类
 * @author chenyongzhi
 *
 */
@Service("personalSponsorRelationAnalysisService")
public class PersonalSponsorRelationAnalysisServiceImpl implements
		PersonalSponsorRelationAnalysisService {

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;
	
	
	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}
	
	@Override
	@AccessLog(description = "analysis personal sponsor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public void analysisAndSaveRelation(Integer customerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@AccessLog(description = "query personal sponsor relation", operateType = OperateType.Query, add2DB = Add2DB.Yes)
	public List<AbstractCustomerRelationItem> find(Integer customerId)
			throws ErrorCodeException {
		if(null != customerId){
			PersonalSponsorItem para =	new PersonalSponsorItem();
			para.setCustomerId(customerId);
			para.setIsvalid(Short.valueOf("1"));
			List<AbstractCustomerRelationItem> data = readDao.query(PersonalSponsorItem.NAMESPACE, para);
			return data;
		}
		return null;
	}

	@Override
	@AccessLog(description = "insert personal sponsor relation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public PersonalSponsorItem insertPersonalSponsorRelation(PersonalSponsorItem item)
			throws ErrorCodeException {
		if(null != item){
			if(null == item.getCtime()){
				item.setCtime(new Date());
			}
			int count = (int) writeDao.insert(PersonalSponsorItem.NAMESPACE, item);
			return count > 0 ? item : null;
		}
		return null;
	}

}
