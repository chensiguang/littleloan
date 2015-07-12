package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class CustomerBankAccountItem extends AbstractItem {

    private static final long  serialVersionUID = -1583847568269236573L;
    public static final String NAMESPACE        = "crm_customer_bank_account";
    /**
     * id
     */
    private Integer            id; 
    /**
     * 客户id
     */
    private Integer            customerId;
    /**
     * 组织id
     */
    private Integer            organizationId;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getLoanCard() {
        return loanCard;
    }

    public void setLoanCard(String loanCard) {
        this.loanCard = loanCard;
    }

}
