package com.hexin.pettyLoan.crm.model;


/**
 * 企业股东对象
 * @author chenyongzhi
 *
 */
public class EnterpriseStockerItem extends AbstractCustomerRelationItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624117044835462867L;
	
	public static final String NAMESPACE= "crm_enterprise_stocker";
	
	/**
	 * 股东名称
	 */
	private String stockerName;
	
	/**
	 * 客户类型
	 */
	private String stockerType;
	
	/**
	 * 持股金额
	 */
	private Double holdAmount;
	
	/**
	 * 持股数量
	 */
	private Integer holdNumber;
	
	/**
	 * 持股比例
	 */
	private  String holdScale;
	
	public String getStockerName() {
		return stockerName;
	}

	public void setStockerName(String stockerName) {
		this.stockerName = stockerName;
	}

	public String getStockerType() {
		return stockerType;
	}

	public void setStockerType(String stockerType) {
		this.stockerType = stockerType;
	}

	public Double getHoldAmount() {
		return holdAmount;
	}

	public void setHoldAmount(Double holdAmount) {
		this.holdAmount = holdAmount;
	}

	public Integer getHoldNumber() {
		return holdNumber;
	}

	public void setHoldNumber(Integer holdNumber) {
		this.holdNumber = holdNumber;
	}

	public String getHoldScale() {
		return holdScale;
	}

	public void setHoldScale(String holdScale) {
		this.holdScale = holdScale;
	}
}
