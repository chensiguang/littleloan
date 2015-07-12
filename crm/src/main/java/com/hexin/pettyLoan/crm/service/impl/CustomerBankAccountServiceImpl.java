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
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.crm.model.CustomerBankAccountItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerBankAccountService;
import com.hexin.pettyLoan.system.model.UserinfoItem;

@Service("customerBankAccountService")
public class CustomerBankAccountServiceImpl implements CustomerBankAccountService {
    @Resource(name = "writeDaoHelper")
    DaoHelper writeDao;
    @Resource(name = "readDaoHelper")
    DaoHelper readDao;

    public void initMethod() {
        writeDao = WebApplicationContextHolder.getBean("writeDaoHelper");
        readDao = WebApplicationContextHolder.getBean("readDaoHelper");
    }
    @Override
    @AccessLog(description = "insertCustomerBankAccount", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
    public CustomerBankAccountItem insertCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException {
        writeDao.insert(CustomerBankAccountItem.NAMESPACE, item);
        return item;
    }

    @Override
    public CustomerBankAccountItem updateCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerBankAccountItem getOneCustomerBankAccount(Integer id) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerBankAccountItem> getCustomerBankAccountList(CustomerBankAccountItem params)
                                                                                                   throws ErrorCodeException {
        return readDao.query(CustomerBankAccountItem.NAMESPACE, params);
    }

    @Override
    public Boolean deleteCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CustomerBankAccountItem> getCustomerBankAccountinfoListPage(CustomerBankAccountItem params)
                                                                                                           throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getCount(CustomerBankAccountItem params) throws ErrorCodeException {
        // TODO Auto-generated method stub
        return null;
    }

}
