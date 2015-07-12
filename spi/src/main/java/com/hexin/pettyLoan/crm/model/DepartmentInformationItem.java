package com.hexin.pettyLoan.crm.model;

import java.sql.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class DepartmentInformationItem extends AbstractItem {

    private static final long  serialVersionUID = 6910233651487606659L;
    public static final String NAMESPACE        = "crm_department_information";

    private Integer            id;
    private Integer            customerId;
    private Integer            organizationId;
    /*
     * 农村企业（工商企业）、城市企业（工商企业）、城市各类企业、农村各类企业
     */
    private String             details;
    /*
     * 默认为否，若是往来客户，则只显示客户基础信息、联系信息供填写。
     */
    private Integer            correspondent;
    private String             shortName;
    private String             scale;
    private String             property;
    private String             regNum;
    private Date               validDate;
    private String             regOrgan;
    private String             country;
    private String             govermentRegion;
    private String             customerLocal;
    private String             deptNo;
    private String             businessScope;
    private String             licence;
    private String             approvalNo;
    private String             vocationDetails;
    private String             vocation;
    private Double             annualSale;
    private Date               regDate;
    private Double             regCapital;
    private String             localTaxNo;
    private String             countryTaxNo;
    private String             clientSource;
    private String             chargeDept;
    private String             regAddress;
    private String             mainManagerId;
    private String             viceManagerId;
    private String             evaluation;
    private Integer            customerRank;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(Integer correspondent) {
        this.correspondent = correspondent;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getRegOrgan() {
        return regOrgan;
    }

    public void setRegOrgan(String regOrgan) {
        this.regOrgan = regOrgan;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovermentRegion() {
        return govermentRegion;
    }

    public void setGovermentRegion(String govermentRegion) {
        this.govermentRegion = govermentRegion;
    }

    public String getCustomerLocal() {
        return customerLocal;
    }

    public void setCustomerLocal(String customerLocal) {
        this.customerLocal = customerLocal;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getVocationDetails() {
        return vocationDetails;
    }

    public void setVocationDetails(String vocationDetails) {
        this.vocationDetails = vocationDetails;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public Double getAnnualSale() {
        return annualSale;
    }

    public void setAnnualSale(Double annualSale) {
        this.annualSale = annualSale;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Double getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Double regCapital) {
        this.regCapital = regCapital;
    }

    public String getLocalTaxNo() {
        return localTaxNo;
    }

    public void setLocalTaxNo(String localTaxNo) {
        this.localTaxNo = localTaxNo;
    }

    public String getCountryTaxNo() {
        return countryTaxNo;
    }

    public void setCountryTaxNo(String countryTaxNo) {
        this.countryTaxNo = countryTaxNo;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }

    public String getChargeDept() {
        return chargeDept;
    }

    public void setChargeDept(String chargeDept) {
        this.chargeDept = chargeDept;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getMainManagerId() {
        return mainManagerId;
    }

    public void setMainManagerId(String mainManagerId) {
        this.mainManagerId = mainManagerId;
    }

    public String getViceManagerId() {
        return viceManagerId;
    }

    public void setViceManagerId(String viceManagerId) {
        this.viceManagerId = viceManagerId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(Integer customerRank) {
        this.customerRank = customerRank;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
