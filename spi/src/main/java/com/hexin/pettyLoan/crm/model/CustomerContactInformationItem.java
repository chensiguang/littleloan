package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class CustomerContactInformationItem extends AbstractItem {

    /**
     * 
     */
    private static final long  serialVersionUID = -7017515755138260227L;
    public static final String NAMESPACE        = "crm_customer_contact_informatoin";

    private Integer            id;
    private Integer            userId;
    private Integer            organizationId;
    private String             address;
    private String             telephone;
    private String             mobile;
    private String             email;
    private String             wechat;
    private String             QQ;
    private String             fax;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getWechat() {
        return wechat;
    }
    
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public String getQQ() {
        return QQ;
    }
    
    public void setQQ(String qQ) {
        QQ = qQ;
    }

    
    public String getFax() {
        return fax;
    }

    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
   
}
