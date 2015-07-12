package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerHouseItem;


public interface CustomerHouseService {
    
    /**
     * 插入单条客戶住宅
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerHouseItem insertCustomerHouse(CustomerHouseItem item) throws ErrorCodeException;
    public CustomerHouseItem updateCustomerHouse(CustomerHouseItem item) throws ErrorCodeException;
    public CustomerHouseItem getOneCustomerHouse(Integer id) throws ErrorCodeException;
    public List<CustomerHouseItem> getCustomerHouseList(CustomerHouseItem params) throws ErrorCodeException;
    public Boolean deleteCustomerHouse(CustomerHouseItem item) throws ErrorCodeException;
    public List<CustomerHouseItem> getCustomerHouseListPage(CustomerHouseItem params) throws ErrorCodeException;
    public Integer getCount(CustomerHouseItem params) throws ErrorCodeException;

}
