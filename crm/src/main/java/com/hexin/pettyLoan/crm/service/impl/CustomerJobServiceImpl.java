package com.hexin.pettyLoan.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.spring.WebApplicationContextHolder;
import com.hexin.pettyLoan.crm.model.CustomerHouseItem;
import com.hexin.pettyLoan.crm.model.CustomerJobItem;
import com.hexin.pettyLoan.crm.service.CustomerJobService;

@Service("customerJobService")
public class CustomerJobServiceImpl implements CustomerJobService{
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertCustomerJob", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerJobItem insertCustomerJob(CustomerJobItem item) throws ErrorCodeException {
        writeDao.insert(CustomerJobItem.NAMESPACE, item);
        return item;
    }

    @Override
    public CustomerJobItem updateCustomerJob(CustomerJobItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerJobItem getOneCustomerJob(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerJobItem> getCustomerJobList(CustomerJobItem params) throws ErrorCodeException {
        return readDao.query(CustomerJobItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteCustomerJob(CustomerJobItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerJobItem> getCustomerJobListPage(CustomerJobItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(CustomerJobItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List<CustomerJobItem> getSameWorkUnitCustomerJobByCustomerId(
			Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerJobItem getCustomerJobByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
