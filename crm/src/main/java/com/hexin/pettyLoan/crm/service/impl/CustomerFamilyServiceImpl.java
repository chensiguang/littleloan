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
import com.hexin.pettyLoan.crm.model.CustomerContactInformationItem;
import com.hexin.pettyLoan.crm.model.CustomerFamilyItem;
import com.hexin.pettyLoan.crm.service.CustomerFamilyService;

@Service("customerFamilyService")
public class CustomerFamilyServiceImpl implements CustomerFamilyService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertCustomerFamily", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerFamilyItem insertCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException {
        writeDao.insert(CustomerFamilyItem.NAMESPACE, item);
        return item;
    }
    
    @Override
    @AccessLog(description = "insertCustomerFamilyList", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public void  insertCustomerFamilyList(List<CustomerFamilyItem> itemList) throws ErrorCodeException {
              for(CustomerFamilyItem customerFamilyItem:itemList){
                  writeDao.insert(CustomerFamilyItem.NAMESPACE, customerFamilyItem);
              }
    }


    @Override
    public CustomerFamilyItem updateCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerFamilyItem getOneCustomerFamily(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerFamilyItem> getCustomerFamilyList(CustomerFamilyItem params) throws ErrorCodeException {
        return readDao.query(CustomerFamilyItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerFamilyItem> getCustomerFamilyListPage(CustomerFamilyItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(CustomerFamilyItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
