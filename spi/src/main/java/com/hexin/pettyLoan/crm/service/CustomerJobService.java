package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerJobItem;

public interface CustomerJobService {
    /**
     * 插入单条客戶工作
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerJobItem insertCustomerJob(CustomerJobItem item) throws ErrorCodeException;
    public CustomerJobItem updateCustomerJob(CustomerJobItem item) throws ErrorCodeException;
    public CustomerJobItem getOneCustomerJob(Integer id) throws ErrorCodeException;
    public List<CustomerJobItem> getCustomerJobList(CustomerJobItem params) throws ErrorCodeException;
    public Boolean deleteCustomerJob(CustomerJobItem item) throws ErrorCodeException;
    public List<CustomerJobItem> getCustomerJobListPage(CustomerJobItem params) throws ErrorCodeException;
    public Integer getCount(CustomerJobItem params) throws ErrorCodeException;
    
    /**
     * 获取具有相当工作单位的客户工作信息
     * @param customerId
     * @return
     */
    public List<CustomerJobItem> getSameWorkUnitCustomerJobByCustomerId(Integer customerId);
    
    /**
     * 通过客户编号查询客户的工作信息
     * @param customerId
     * @return
     */
    public CustomerJobItem getCustomerJobByCustomerId(Integer customerId);
}
