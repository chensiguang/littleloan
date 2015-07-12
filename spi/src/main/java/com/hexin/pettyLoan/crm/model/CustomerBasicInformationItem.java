package com.hexin.pettyLoan.crm.model;

import java.sql.Date;


import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.common.model.AbstractItem;

public class CustomerBasicInformationItem extends AbstractItem {


    private static final long  serialVersionUID = -5125628641098581045L;
    public static final String NAMESPACE        = "crm_customer_basic_information";
    /**
     * id
     */
    private Integer            id;
    /**
     * 组织id
     */
    private Integer            organizationId;
    /**
     * 证书类型
     */
    private String             certificateType;
    /**
     * 证书编号
     */
    private String             certificateNo;
    /**
     * 客户姓名
     */
    private String             customerName;
    /**
     * 客户类型
     */
    private String             customerType;
    /**
     * 农村企业（工商企业）、城市企业（工商企业）、城市各类企业、农村各类企业
     */
    private String             details;
    /**
     *   默认为否，若是往来客户，则只显示客户基础信息、联系信息供填写。
     */
  
    private Integer            correspondent;
    /**
     * 性别
     */
    private Integer            gender;
    /**
     * 年龄
     */
    private Integer            age;
    /**
     * 生日
     */
    private Date               birthday;
    private Integer            marriage;
    /**
     * 等级
     */
    private String             degree;
    private String             nationalType;
    private String             country;
    private String             govermentRegion;
    private String             customerLocal;
    private Double             annualIncome;
    private String             clientSource;
    private String             accountLocal;
    private String             mainManagerId;
    private String             viceManagerId;
    /**
     * 评估
     */
    private String             evaluation;
    /**
     * 客户等级
     */
    private Integer            customerRank;
    private Integer            isOld;
    private Integer            ruleId;
    
    private Integer rows;//每页显示的行数,pageSize
    private Integer page;//当前页,pageIndex
    //客户类型
    public enum CustomerType {
    	
    	PERSONAL("个人","1"), ENTERPRISE("机构","2");
    	
    	private String name;
    	
    	private String code;
    	
    	public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		//构造方法
		private CustomerType(String name, String code){
    		this.name = name;
    		this.code = code;
    	}
    }

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

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getNationalType() {
        return nationalType;
    }

    public void setNationalType(String nationalType) {
        this.nationalType = nationalType;
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

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }

    public String getAccountLocal() {
        return accountLocal;
    }

    public void setAccountLocal(String accountLocal) {
        this.accountLocal = accountLocal;
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

    public Integer getIsOld() {
        return isOld;
    }

    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    
    public Integer getRows() {
        return rows;
    }

    
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    
    public Integer getPage() {
        return page;
    }

    
    public void setPage(Integer page) {
        this.page = page;
    }
}
