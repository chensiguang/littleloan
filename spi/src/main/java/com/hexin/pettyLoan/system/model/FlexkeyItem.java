package com.hexin.pettyLoan.system.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 值集，数据库业务表中不建议存ID，直接存flexvalue即可
 * @author huqitao
 *
 */
public class FlexkeyItem extends AbstractItem{
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE = "sys_flexkey";
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
	private Integer id;
	private String flexkey;
	private String keyDescription;
	private String flexvalue;
	private String valueDescription;
	
//	如果返回的json数据中不需要显示的字段，在字段前加上transient即可
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	
	public FlexkeyItem(){
		
	}
	
	public FlexkeyItem(String flexkey, String flexvalue){
		this.flexkey = flexkey;
		this.flexvalue = flexvalue;
	}
	
	public String getFlexkey() {
		return flexkey;
	}
	public void setFlexkey(String flexkey) {
		this.flexkey = flexkey;
	}
	public String getKeyDescription() {
		return keyDescription;
	}
	public void setKeyDescription(String keyDescription) {
		this.keyDescription = keyDescription;
	}
	public String getFlexvalue() {
		return flexvalue;
	}
	public void setFlexvalue(String flexvalue) {
		this.flexvalue = flexvalue;
	}
	public String getValueDescription() {
		return valueDescription;
	}
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
