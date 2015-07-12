package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerBankAccountItem;


public interface CustomerBankAccountService {
    /**
     * 添加客户银行账号
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerBankAccountItem insertCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException;
    /**
     * 更新客户银行账号
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerBankAccountItem updateCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException;
    /**
     * 获取一个客户银行账号
     * @param id
     * @return
     * @throws ErrorCodeException
     */
    public CustomerBankAccountItem getOneCustomerBankAccount(Integer id) throws ErrorCodeException;
    /**
     * 获取多个客户银行账号
     * @param params
     * @return
     * @throws ErrorCodeException
     */
    public List<CustomerBankAccountItem> getCustomerBankAccountList(CustomerBankAccountItem params) throws ErrorCodeException;
    /**
     * 删除客户银行账号
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public Boolean deleteCustomerBankAccount(CustomerBankAccountItem item) throws ErrorCodeException;
    /**
     * 分页查询客户银行账号
     * @param params
     * @return
     * @throws ErrorCodeException
     */
    public List<CustomerBankAccountItem> getCustomerBankAccountinfoListPage(CustomerBankAccountItem params) throws ErrorCodeException;
    /**
     * 获取客户银行账号数量
     * @param params
     * @return
     * @throws ErrorCodeException
     */
    public Integer getCount(CustomerBankAccountItem params) throws ErrorCodeException;
}
