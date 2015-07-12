package com.hexin.pettyLoan.system.model;

import java.util.List;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 组织机构实体
 * @author wuwei
 *
 */
 
public class OrgnizationItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_orgnization";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	public static final String SQLID_GETCHILDREN = "getChildren";
	public static final String SQLID_GETFIRST = "getFirst";		//获取组织关系树的第一层
	public static final String SQLID_UPDATENAMEPATH = "updateNamePath";		//更新组织时使用，更新所有相关组织的namepath
	private Integer id;
	private String orgnizationName;
	private String shortname;
	private String orgnizationCode;
	private String legalRepresentative;
	private String orgnizationType;
	private String registeredAddress;
	private Integer cityId;
	private String cityName;
	private String url;
	private String telephone;
	private String fax;
	private String email;
	private String bak;
	private String status;
	private Integer pid;
	private String idPath;
	private String namePath;
	private String spell;
	private String pNamePath;
	private List<OrgnizationItem> children;
	private String text;		//树的节点名（取orgnizationName）
	
	
	//不需要在返回的json数据中显示的字段，在前面加transient
	private Integer rows;
	private Integer page;
	private Integer start;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getOrgnizationCode() {
		return orgnizationCode;
	}
	public void setOrgnizationCode(String orgnizationCode) {
		this.orgnizationCode = orgnizationCode;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getOrgnizationType() {
		return orgnizationType;
	}
	public void setOrgnizationType(String orgnizationType) {
		this.orgnizationType = orgnizationType;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getIdPath() {
		return idPath;
	}
	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}
	public String getNamePath() {
		return namePath;
	}
	public void setNamePath(String namePath) {
		this.namePath = namePath;
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
	public List<OrgnizationItem> getChildren() {
		return children;
	}
	public void setChildren(List<OrgnizationItem> children) {
		this.children = children;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getpNamePath() {
		return pNamePath;
	}
	public void setpNamePath(String pNamePath) {
		this.pNamePath = pNamePath;
	}
	
}
