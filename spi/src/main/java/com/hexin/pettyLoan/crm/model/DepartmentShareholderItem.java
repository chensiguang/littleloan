package com.hexin.pettyLoan.crm.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class DepartmentShareholderItem extends AbstractItem {

    private static final long  serialVersionUID = 3108214068035133300L;
    public static final String NAMESPACE        = "crm_department_shareholder";
    private Integer            id;
    private Integer            customerId;
    private Integer            organizationId;
    private String             name;
    private String             holderType;
    private String             category;
    private Double             holdings;
    private Integer            shares;
    private Double             ratio;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getHoldings() {
        return holdings;
    }

    public void setHoldings(Double holdings) {
        this.holdings = holdings;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
