package com.hexin.pettyLoan.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hexin.pettyLoan.common.model.AbstractItem;
import com.hexin.pettyLoan.crm.model.CustomerBasicInformationItem;
import com.hexin.pettyLoan.crm.service.CustomerBankAccountService;
import com.hexin.pettyLoan.crm.service.CustomerBasicInformationService;
import com.hexin.pettyLoan.crm.service.CustomerContactInformationService;
import com.hexin.pettyLoan.crm.service.CustomerFamilyService;
import com.hexin.pettyLoan.crm.service.CustomerHouseService;
import com.hexin.pettyLoan.crm.service.CustomerJobService;
import com.hexin.pettyLoan.crm.service.CustomerOperatorService;

public class CustomerOperatorServiceImpl implements CustomerOperatorService {

    @Resource(name = "customerBasicInformationService")
    private CustomerBasicInformationService   customerBasicInformationService;

    @Resource(name = "customerJobService")
    private CustomerJobService                customerJobService;

    @Resource(name = "customerBankAccountService")
    private CustomerBankAccountService        customerBankAccountService;

    @Resource(name = "customerHouseService")
    private CustomerHouseService              customerHouseService;

    @Resource(name = "customerContactInformationService")
    private CustomerContactInformationService customerContactInformationService;

    @Resource(name = "customerFamilyService")
    private CustomerFamilyService             customerFamilyService;

    @Override
    public void putAllCustomerInfo(Map<String, List<AbstractItem>> customerInfoMap) {
        for (Map.Entry<String, List<AbstractItem>> entry : customerInfoMap.entrySet()) {
            if (CustomerBasicInformationItem.class.getSimpleName().equals(entry.getKey())) {

            }
        }

    }

}
