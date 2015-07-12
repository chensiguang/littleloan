package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;

public interface CustomerBasicInformationService {
    /**
     * 插入单条客戶基本信息
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerBasicInformationItem insertCustomerBasicInformation(CustomerBasicInformationItem item) throws ErrorCodeException;
    public CustomerBasicInformationItem updateCustomerBasicInformation(CustomerBasicInformationItem item) throws ErrorCodeException;
    public CustomerBasicInformationItem getOneCustomerBasicInformation(Integer id) throws ErrorCodeException;
    public List<CustomerBasicInformationItem> getCustomerBasicInformationList(CustomerBasicInformationItem params) throws ErrorCodeException;
    public Boolean deleteCustomerBasicInformation(CustomerBasicInformationItem item) throws ErrorCodeException;
    public List<CustomerBasicInformationItem> getCustomerBasicInformationListPage(CustomerBasicInformationItem params) throws ErrorCodeException;
    public Integer getCount(CustomerBasicInformationItem params) throws ErrorCodeException;
    
    /**
     * 通过机构名称与工商登记号获取同一个机构的不通客户信息
     * @param name	机构名称
     * @param registNumber 工商登记号
     * @return
     */
    public List<CustomerBasicInformationItem> getSameDepartmentByNameAndRegNum(String name, String registNumber);
    
    /**
     * 根据组织编号、证件类型、证件号码判断是否存在指定客户(个人)
     * @param organizationId
     * @param certificateType
     * @param certificateNO
     * @return CustomerBasicInformationItem
     */
    public CustomerBasicInformationItem isExistedPersonalCustomer(Integer organizationId, String certificateType, String certificateNO);
}
