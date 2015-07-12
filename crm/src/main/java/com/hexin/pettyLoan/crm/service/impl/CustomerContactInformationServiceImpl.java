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
import com.hexin.pettyLoan.crm.model.CustomerBankAccountItem;
import com.hexin.pettyLoan.crm.model.CustomerContactInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerContactInformationService;

@Service("customerContactInformationService")
public class CustomerContactInformationServiceImpl implements CustomerContactInformationService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertCustomerContactInformation", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerContactInformationItem insertCustomerContactInformation(CustomerContactInformationItem item)
                                                                                                               throws ErrorCodeException {
        writeDao.insert(CustomerContactInformationItem.NAMESPACE, item);
        return item;
    }

    @Override
    public CustomerContactInformationItem updateCustomerContactInformation(CustomerContactInformationItem item)
                                                                                                               throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerContactInformationItem getOneCustomerContactInformation(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerContactInformationItem> getCustomerContactInformationList(CustomerContactInformationItem params)
                                                                                                                        throws ErrorCodeException {
        return readDao.query(CustomerContactInformationItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteCustomerContactInformation(CustomerContactInformationItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerContactInformationItem> getCustomerContactInformationListPage(CustomerContactInformationItem params)
                                                                                                                            throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(CustomerContactInformationItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public CustomerContactInformationItem getCustomerContactInfo(Integer customerId) throws ErrorCodeException {
        
        return null;
    }
}
