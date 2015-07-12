package com.hexin.pettyLoan.finance.model;

import java.util.Date;
import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 财务会计-科目model
 * @author wuwei
 *
 */
public class SubjectItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "fa_subject_info";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	public static final String SQLID_GETTREE = "getTree";
	
	private Integer id;
	private Integer orgid;					
	private String subcode;			//科目代码
	private String subcode1;		//一级科目代码
	private String subcode2;
	private String subcode3;
	private String subcode4;
	private String subcode5;
	private String subcode6;
	private String subcode7;
	private String subname;			//科目名称
	private Integer subtype;		//科目类型
	private Integer level;			//科目级别
	private double balance;			//科目余额
	private Integer debitorloan;	//借贷方向
	private Integer iscontrol;		//是否管控
	private Integer iscommon;		//是否常用
	private Integer isbill;			//是否挂账科目
	private Integer islast;			//是否末级科目
	private Integer isoverdraft;	//允许透支
	private Integer iscount;		//数量核算
	private Integer iscash;			//现金核算
	private Integer isadjust;		//辅助核算
	private String adjusttypes;		//辅助核算类型
	private Integer pushstate;		//推送状态
	private Integer enablestate;	//启用状态
	private Integer parentid;		//上级科目id
	private String remark;			//备注
	private Integer applier;		//申请人
	private Date apptime;			//申请时间
	private String appdesc;			//申请说明
	private Integer appstate;		//申请状态
	
	private List<SubjectItem> children;	//下级科目
	
//	如果返回的json数据中不需要显示的字段，在字段前加上transient即可
	private transient Integer rows;//每页显示的行数,pageSize
	private transient Integer page;//当前页,pageIndex
	private transient Integer start;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getSubcode1() {
		return subcode1;
	}
	public void setSubcode1(String subcode1) {
		this.subcode1 = subcode1;
	}
	public String getSubcode2() {
		return subcode2;
	}
	public void setSubcode2(String subcode2) {
		this.subcode2 = subcode2;
	}
	public String getSubcode3() {
		return subcode3;
	}
	public void setSubcode3(String subcode3) {
		this.subcode3 = subcode3;
	}
	public String getSubcode4() {
		return subcode4;
	}
	public void setSubcode4(String subcode4) {
		this.subcode4 = subcode4;
	}
	public String getSubcode5() {
		return subcode5;
	}
	public void setSubcode5(String subcode5) {
		this.subcode5 = subcode5;
	}
	public String getSubcode6() {
		return subcode6;
	}
	public void setSubcode6(String subcode6) {
		this.subcode6 = subcode6;
	}
	public String getSubcode7() {
		return subcode7;
	}
	public void setSubcode7(String subcode7) {
		this.subcode7 = subcode7;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public Integer getSubtype() {
		return subtype;
	}
	public void setSubtype(Integer subtype) {
		this.subtype = subtype;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Integer getDebitorloan() {
		return debitorloan;
	}
	public void setDebitorloan(Integer debitorloan) {
		this.debitorloan = debitorloan;
	}
	public Integer getIscontrol() {
		return iscontrol;
	}
	public void setIscontrol(Integer iscontrol) {
		this.iscontrol = iscontrol;
	}
	public Integer getIscommon() {
		return iscommon;
	}
	public void setIscommon(Integer iscommon) {
		this.iscommon = iscommon;
	}
	public Integer getIsbill() {
		return isbill;
	}
	public void setIsbill(Integer isbill) {
		this.isbill = isbill;
	}
	public Integer getIslast() {
		return islast;
	}
	public void setIslast(Integer islast) {
		this.islast = islast;
	}
	public Integer getIsoverdraft() {
		return isoverdraft;
	}
	public void setIsoverdraft(Integer isoverdraft) {
		this.isoverdraft = isoverdraft;
	}
	public Integer getIscount() {
		return iscount;
	}
	public void setIscount(Integer iscount) {
		this.iscount = iscount;
	}
	public Integer getIscash() {
		return iscash;
	}
	public void setIscash(Integer iscash) {
		this.iscash = iscash;
	}
	public Integer getIsadjust() {
		return isadjust;
	}
	public void setIsadjust(Integer isadjust) {
		this.isadjust = isadjust;
	}
	public String getAdjusttypes() {
		return adjusttypes;
	}
	public void setAdjusttypes(String adjusttypes) {
		this.adjusttypes = adjusttypes;
	}
	public Integer getPushstate() {
		return pushstate;
	}
	public void setPushstate(Integer pushstate) {
		this.pushstate = pushstate;
	}
	public Integer getEnablestate() {
		return enablestate;
	}
	public void setEnablestate(Integer enablestate) {
		this.enablestate = enablestate;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getApplier() {
		return applier;
	}
	public void setApplier(Integer applier) {
		this.applier = applier;
	}
	public Date getApptime() {
		return apptime;
	}
	public void setApptime(Date apptime) {
		this.apptime = apptime;
	}
	public String getAppdesc() {
		return appdesc;
	}
	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}
	public Integer getAppstate() {
		return appstate;
	}
	public void setAppstate(Integer appstate) {
		this.appstate = appstate;
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
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
}
