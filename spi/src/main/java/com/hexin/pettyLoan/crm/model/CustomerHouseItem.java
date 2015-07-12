package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;


public class CustomerHouseItem extends AbstractItem {

  
    private static final long serialVersionUID = 6587047227260239703L;
    public static final String NAMESPACE        = "crm_customer_house";

    private Integer id;
    private Integer organizationId;
    private Integer customerId;
    private String address;
    private String liveProperty;
    private Integer liveYear;
    private String liveCondition;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getLiveProperty() {
        return liveProperty;
    }
    
    public void setLiveProperty(String liveProperty) {
        this.liveProperty = liveProperty;
    }
    
    public Integer getLiveYear() {
        return liveYear;
    }
    
    public void setLiveYear(Integer liveYear) {
        this.liveYear = liveYear;
    }
    
    public String getLiveCondition() {
        return liveCondition;
    }
    
    public void setLiveCondition(String liveCondition) {
        this.liveCondition = liveCondition;
    }
}
