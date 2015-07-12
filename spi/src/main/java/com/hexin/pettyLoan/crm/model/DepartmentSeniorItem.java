package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;


public class DepartmentSeniorItem  extends AbstractItem {

    private static final long serialVersionUID = 6890667742776393552L;
    public static final String NAMESPACE        = "crm_department_senior";
    private Integer            id;
    private Integer            customerId;
    private Integer            organizationId;
    private String seniorName;
    private String seniorType;
    private String departmentName;
    private String           holdings;
    private Double            shares;		//年收入
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public Integer getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    public String getSeniorName() {
        return seniorName;
    }
    
    public void setSeniorName(String seniorName) {
        this.seniorName = seniorName;
    }
    
    public String getSeniorType() {
        return seniorType;
    }
    
    public void setSeniorType(String seniorType) {
        this.seniorType = seniorType;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public String getHoldings() {
        return holdings;
    }
    
    public void setHoldings(String holdings) {
        this.holdings = holdings;
    }
    
    public Double getShares() {
        return shares;
    }
    
    public void setShares(Double shares) {
        this.shares = shares;
    }

}
