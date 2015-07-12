package com.hexin.pettyLoan.crm.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.crm.model.CustomerContactInformationItem;

public interface CustomerContactInformationService {

    /**
     * 插入单条客戶联系信息
     * 
     * @param item
     * @return
     * @throws ErrorCodeException
     */
    public CustomerContactInformationItem insertCustomerContactInformation(CustomerContactInformationItem item)
                                                                                                               throws ErrorCodeException;

    public CustomerContactInformationItem updateCustomerContactInformation(CustomerContactInformationItem item)
                                                                                                               throws ErrorCodeException;

    public CustomerContactInformationItem getOneCustomerContactInformation(Integer id) throws ErrorCodeException;
    
    public CustomerContactInformationItem getCustomerContactInfo(Integer customerId) throws ErrorCodeException;

    public List<CustomerContactInformationItem> getCustomerContactInformationList(CustomerContactInformationItem params)
                                                                                                                        throws ErrorCodeException;

    public Boolean deleteCustomerContactInformation(CustomerContactInformationItem item) throws ErrorCodeException;

    public List<CustomerContactInformationItem> getCustomerContactInformationListPage(CustomerContactInformationItem params)
                                                                                                                            throws ErrorCodeException;

    public Integer getCount(CustomerContactInformationItem params) throws ErrorCodeException;
}
