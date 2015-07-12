package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerFamilyItem;


public interface CustomerFamilyService {
    /**
     * 插入单条客户家庭成员
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerFamilyItem insertCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException;
    /**
     * 插入多条客户家庭成员
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public void  insertCustomerFamilyList(List<CustomerFamilyItem> itemList) throws ErrorCodeException;
    public CustomerFamilyItem updateCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException;
    public CustomerFamilyItem getOneCustomerFamily(Integer id) throws ErrorCodeException;
    public List<CustomerFamilyItem> getCustomerFamilyList(CustomerFamilyItem params) throws ErrorCodeException;
    public Boolean deleteCustomerFamily(CustomerFamilyItem item) throws ErrorCodeException;
    public List<CustomerFamilyItem> getCustomerFamilyListPage(CustomerFamilyItem params) throws ErrorCodeException;
    public Integer getCount(CustomerFamilyItem params) throws ErrorCodeException;

}
