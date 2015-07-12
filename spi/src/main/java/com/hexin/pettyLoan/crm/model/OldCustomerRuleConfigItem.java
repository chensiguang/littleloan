package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;


public class OldCustomerRuleConfigItem extends AbstractItem {

   
    private static final long serialVersionUID = -5103409754089455524L;
    public static final String NAMESPACE        = "crm_old_customer_rule_config";
    
    private Integer id;
    private String businessType;
    private String businesStatus;
    private Double contractAmount;
    private Integer contractNumber;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getBusinessType() {
        return businessType;
    }
    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    public String getBusinesStatus() {
        return businesStatus;
    }
    
    public void setBusinesStatus(String businesStatus) {
        this.businesStatus = businesStatus;
    }
    
    public Double getContractAmount() {
        return contractAmount;
    }
    
    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }
    
    public Integer getContractNumber() {
        return contractNumber;
    }
    
    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

   

}
