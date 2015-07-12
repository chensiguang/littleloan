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
import com.hexin.pettyLoan.crm.model.CustomerFamilyItem;
import com.hexin.pettyLoan.crm.model.CustomerHouseItem;
import com.hexin.pettyLoan.crm.service.CustomerHouseService;

@Service("customerHouseService")
public class CustomerHouseServiceImpl implements CustomerHouseService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertCustomerHouse", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerHouseItem insertCustomerHouse(CustomerHouseItem item) throws ErrorCodeException {
        writeDao.insert(CustomerHouseItem.NAMESPACE, item);
        return item;
    }

    @Override
    public CustomerHouseItem updateCustomerHouse(CustomerHouseItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerHouseItem getOneCustomerHouse(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerHouseItem> getCustomerHouseList(CustomerHouseItem params) throws ErrorCodeException {
        return readDao.query(CustomerHouseItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteCustomerHouse(CustomerHouseItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerHouseItem> getCustomerHouseListPage(CustomerHouseItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(CustomerHouseItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

}
