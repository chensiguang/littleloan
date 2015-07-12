package com.hexin.pettyLoan.crm.dto;

import java.io.Serializable;
import java.sql.Date;


public class CustomerDto implements Serializable{


    private static final long serialVersionUID = -1928462936669050984L;

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
    
    private Integer            customerId;
    
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
    
   
    /**
     * 银行
     */
    private String             bank;
    /**
     * 分支
     */
    private String             branch;
    private String             accountNo;
    private Integer            isDefault;
    private Integer            bankCardType;
    private String             loanCard;
    

    private String liveProperty;
    private Integer liveYear;
    private String liveCondition;
    private Integer            userId;
    private String             mobile;
    private String             email;
    private String             wechat;
    private String             QQ;
    private String             fax;
   



}
