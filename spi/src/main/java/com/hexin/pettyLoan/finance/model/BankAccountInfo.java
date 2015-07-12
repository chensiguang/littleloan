package com.hexin.pettyLoan.finance.model;

import java.sql.Timestamp;
import java.util.Date;

import com.hexin.pettyLoan.common.model.AbstractItem;

public class BankAccountInfo  extends AbstractItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "fa_bank_account_info";
	private Integer id;
     private Integer subid;
     private String subcode;
     private String subname;
     private String bankname;
     private Short type;
     private String bankaccount;
     private Double bankbalance;
     private Date opendate;
     private String useprint;
     private Short canclesign;
     private Date cancledate;
     private String remark;
     private Integer orgid;
     private Integer creater;
     private Timestamp ctime;
     private Timestamp mtime;
     private Timestamp rtime;
     private Short isvalid;

    // Constructors

    /** default constructor */
    public BankAccountInfo() {
    }

	/** minimal constructor */
    public BankAccountInfo(Integer subid, Short type, String bankaccount, Double bankbalance, Date opendate, Integer orgid, Integer creater, Timestamp ctime, Short isvalid) {
        this.subid = subid;
        this.type = type;
        this.bankaccount = bankaccount;
        this.bankbalance = bankbalance;
        this.opendate = opendate;
        this.orgid = orgid;
        this.creater = creater;
        this.ctime = ctime;
        this.isvalid = isvalid;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubid() {
		return subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public Double getBankbalance() {
		return bankbalance;
	}

	public void setBankbalance(Double bankbalance) {
		this.bankbalance = bankbalance;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public String getUseprint() {
		return useprint;
	}

	public void setUseprint(String useprint) {
		this.useprint = useprint;
	}

	public Short getCanclesign() {
		return canclesign;
	}

	public void setCanclesign(Short canclesign) {
		this.canclesign = canclesign;
	}

	public Date getCancledate() {
		return cancledate;
	}

	public void setCancledate(Date cancledate) {
		this.cancledate = cancledate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getMtime() {
		return mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public Timestamp getRtime() {
		return rtime;
	}

	public void setRtime(Timestamp rtime) {
		this.rtime = rtime;
	}

	public Short getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Short isvalid) {
		this.isvalid = isvalid;
	}

	/** full constructor */
    public BankAccountInfo(Integer subid, String subcode, String subname, String bankname, Short type, String bankaccount, Double bankbalance, Date opendate, String useprint, Short canclesign, Date cancledate, String remark, Integer orgid, Integer creater, Timestamp ctime, Timestamp mtime, Timestamp rtime, Short isvalid) {
        this.subid = subid;
        this.subcode = subcode;
        this.subname = subname;
        this.bankname = bankname;
        this.type = type;
        this.bankaccount = bankaccount;
        this.bankbalance = bankbalance;
        this.opendate = opendate;
        this.useprint = useprint;
        this.canclesign = canclesign;
        this.cancledate = cancledate;
        this.remark = remark;
        this.orgid = orgid;
        this.creater = creater;
        this.ctime = ctime;
        this.mtime = mtime;
        this.rtime = rtime;
        this.isvalid = isvalid;
    }

   
}