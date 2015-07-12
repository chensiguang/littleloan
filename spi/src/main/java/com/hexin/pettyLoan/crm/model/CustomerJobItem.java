package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;


public class CustomerJobItem  extends AbstractItem{

   
    private static final long serialVersionUID = 1655635722036882891L;
    public static final String NAMESPACE        = "crm_customer_job";
    
    private Integer            id;
    private Integer            customerId;
    private Integer            organizationId;
    
    private String occupation;
    private String vocation;
    private String company;
    private String address;
    private String zipcode;
    private String telephone;
    private String companyVocation;
    private String beginYear;
    private String post;
    private String title;
    
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
    
    public String getOccupation() {
        return occupation;
    }
    
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public String getVocation() {
        return vocation;
    }
    
    public void setVocation(String vocation) {
        this.vocation = vocation;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getCompanyVocation() {
        return companyVocation;
    }
    
    public void setCompanyVocation(String companyVocation) {
        this.companyVocation = companyVocation;
    }
    
    public String getBeginYear() {
        return beginYear;
    }
    
    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }
    
    public String getPost() {
        return post;
    }
    
    public void setPost(String post) {
        this.post = post;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

}
