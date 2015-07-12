package com.hexin.pettyLoan.crm.model;

import java.sql.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;


public class CustomerInfoModifyHistoryItem  extends AbstractItem{

    private static final long serialVersionUID = 3381995954330497316L;
    public static final String NAMESPACE        = "crm_customer_info_modify_history";
    
    private Integer id;
    private Integer modifierId;
    private Integer organizationId;
    private Integer customerId;
    private Date modifyDate;
    private String fieldname;
    private String oldvalue;
    private String newvalue;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getModifierId() {
        return modifierId;
    }
    
    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
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
    
    public Date getModifyDate() {
        return modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public String getFieldname() {
        return fieldname;
    }
    
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }
    
    public String getOldvalue() {
        return oldvalue;
    }
    
    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }
    
    public String getNewvalue() {
        return newvalue;
    }
    
    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }
    
 
    

}
