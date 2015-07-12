package com.hexin.pettyLoan.crm.service;

import java.util.List;
import java.util.Map;

import com.hexin.pettyLoan.common.model.AbstractItem;


public interface CustomerOperatorService {
    
    public void putAllCustomerInfo(Map<String, List<AbstractItem>> customerInfoMap);

}
