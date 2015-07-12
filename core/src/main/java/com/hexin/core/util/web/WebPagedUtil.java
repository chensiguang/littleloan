package com.hexin.core.util.web;

import javax.servlet.http.HttpServletRequest;

public class WebPagedUtil {
	public WebPagedUtil(String actionUrl,String params,int pageSize,int recordCount,int currentPage,
			HttpServletRequest request){
		this.actionUrl=actionUrl;
		this.params = params;
		this.pageSize=pageSize;
		this.recordCount=recordCount;
		this.request=request;
		this.currentPage=currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private HttpServletRequest request;
	private int pageSize;
	private int recordCount;
	private int currentPage;
	
	private String actionUrl;
	private String params;
	
	private String nextPage;
	private String prevPage;
	public String getNextPage() {
		int totalPage = recordCount/pageSize;
		if(recordCount%pageSize!=0){
			totalPage++;
		}
		if(totalPage==0)totalPage=1;
		nextPage = "<a href='"+WebUtil.getBasePath(request)+actionUrl+"?pageIndex="+(currentPage+1)+params+"'>下一页</a>";
		if(currentPage==totalPage){
			nextPage="下一页";
		}
		return nextPage;
	}
	public String getPrevPage() {
		int totalPage = recordCount/pageSize;
		if(recordCount%pageSize!=0){
			totalPage++;
		}
		if(totalPage==0)totalPage=1;
		prevPage = "<a href='"+WebUtil.getBasePath(request)+actionUrl+"?pageIndex="+(currentPage-1)+params+"'>上一页</a>";
		if(currentPage==1){
			prevPage="上一页";
		}
		return prevPage;
	}
	
	public String getFirstPage(){
		prevPage = "<a href='"+WebUtil.getBasePath(request)+actionUrl+"?pageIndex=1"+params+"'>首页</a>";
		if(currentPage==1){
			prevPage="首页";
		}
		return prevPage;
	}
	
	public String getLastPage(){
		int totalPage = recordCount/pageSize;
		if(recordCount%pageSize!=0){
			totalPage++;
		}
		if(totalPage==0)totalPage=1;
		nextPage = "<a href='"+WebUtil.getBasePath(request)+actionUrl+"?pageIndex="+totalPage+params+"'>末页</a>";
		if(currentPage==totalPage){
			nextPage="末页";
		}
		return nextPage;
	}
}
