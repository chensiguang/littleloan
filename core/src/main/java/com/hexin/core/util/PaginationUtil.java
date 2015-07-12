package com.hexin.core.util;

/**
 * 分页器
 * 
 */
public class PaginationUtil {
	
	/**
	 * 每页显示的记录数(默认为20条,可以通过set改变其数量)
	 */
	private int pageSize = 20;
	
	/**
	 * 总记录数
	 */
	private int totalRows;
	
	/**
	 * 当前页码
	 */
	private int curPage;
	
	/**
	 * 
	 */
	public PaginationUtil() {
		// ...
	}
	
	/**
	 * 
	 * @param totalRows
	 * @param pageSize
	 * @param curPage
	 */
	public PaginationUtil(int totalRows, int pageSize, int curPage) {
		this.setTotalRows(totalRows);
		this.setPageSize(pageSize);
		this.setCurPage(curPage);
	}
	
	/**
	 * 返回当前页面数
	 * @return
	 */
	public int getCurPage() {

		return curPage < 1?this.curPage = 1:curPage;
	}
	
	/**
	 * 返回总页数
	 * @return
	 */
	public int getTotalPages() {
		
		return (int) Math.ceil((double) totalRows / pageSize);
	}
	
	/**
	 * 返回总行数
	 * @return
	 */
	public int getTotalRows() {

		return totalRows;
	}
	
	/**
	 * 第一页
	 * @return
	 */
	public int firstPage() {

		return 1;
	}
	
	/**
	 * 最后一页
	 * 
	 * @return
	 */
	public int lastPage() {

		return getTotalPages();
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int previousPage() {

		return (curPage - 1 > getTotalPages())?getTotalPages():curPage - 1;
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int nextPage() {

		return (curPage + 1 > getTotalPages())?getTotalPages():curPage + 1;
	}
	
	/**
	 * 是否是最后一页
	 * @return
	 */
	public boolean isFirstPage() {

		return (curPage == 1)?true:false;
	}
	
	/**
	 * 当前开始下标
	 * @return
	 */
	public int getStart() {
		// start = (curPage <= 1) ? 0 : ((curPage - 1) * pageSize) + 1; oracle的分页

		return (curPage <= 1)?0:((curPage - 1)*pageSize); // mysql的分页;
	}
	
	/**
	 * 每页显示的记录数(默认为10条,可以通过set改变其数量)
	 * @return
	 */
	public int getPageSize() {

		return pageSize;
	}
	
	public void setCurPage(int curPage) {

		this.curPage = curPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}