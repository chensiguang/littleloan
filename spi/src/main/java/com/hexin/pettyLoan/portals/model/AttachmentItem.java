package com.hexin.pettyLoan.portals.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

/**
 * 附件表
 * @author wangfan2
 *
 */
public class AttachmentItem extends AbstractItem {

	private static final long serialVersionUID = 1L;

	public static final String NAMESPACE = "ptl_attachment";
	
	public static final String SQLID_PAGE = "page";
	public static final String SQLID_COUNT = "count";
//	如果返回的json数据中不需要显示的字段，在字段前加上transient即可
	private Integer rows;//每页显示的行数,pageSize
	private Integer page;//当前页,pageIndex
	private Integer start;
	
	public AttachmentItem() {
		super();
	}
	
	public AttachmentItem(Integer id) {
		super();
		this.id = id;
	}
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 附件类型
	 */
	private String type;
	
	/**
	 * 类型关联表ID
	 */
	private Integer typeid;
	
	/**
	 * 附件原始名
	 */
	private String filename;
	
	/**
	 * 附件类型
	 */
	private String filetype;
	
	/**
	 * 附件大小
	 */
	private Long filesize;
	
	/**
	 * 存放服务器
	 */
	private String server;
	
	/**
	 * 磁盘路径
	 */
	private String path;
	
	/**
	 * 下载次数
	 */
	private Integer downloads;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
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
