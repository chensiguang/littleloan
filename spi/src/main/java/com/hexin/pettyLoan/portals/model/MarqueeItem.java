package com.hexin.pettyLoan.portals.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 跑马灯model
 * @author ache
 *
 */
public class MarqueeItem extends AbstractItem {

	public MarqueeItem() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE= "ptl_marquee";
	//主键
	private Integer id;
	//标题
	private String title;
	//类型
	private String type;
	//内容ID
	private Integer contentId;
	//内容摘要
	private String contentSummary;
	//跳转路径
	private String url;
	//图片路径
	private String pic;
	//排序号
	private Integer sortNo;
	//发布状态,0未发布,1已发布
	private Integer status;
	public MarqueeItem(Integer id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getContentSummary() {
		return contentSummary;
	}
	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	//不需要在返回的json数据中显示的字段，在前面加transient
	private Integer rows;
	private Integer page;
	private Integer start;
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
