/**
 * 
 */
package com.hexin.pettyLoan.archives.model;

import com.hexin.pettyLoan.common.model.AbstractItem;

import java.util.List;
/**
 * 数据统计model
 * @author wanglei2
 *
 */
public class ArchivesStatisticItem extends AbstractItem {

	/**
	 * 
	 */
	
	private String beginYmd;//开始时间
	private String endYmd;//结束时间
	private Boolean isSummary;//是否汇总
	private List<ArchivesStatisticResultItem> statisticResult;//统计结果

	public ArchivesStatisticItem() {
	
	}

	public String getBeginYmd() {
		return beginYmd;
	}

	public void setBeginYmd(String beginYmd) {
		this.beginYmd = beginYmd;
	}

	public String getEndYmd() {
		return endYmd;
	}

	public void setEndYmd(String endYmd) {
		this.endYmd = endYmd;
	}

	public Boolean getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(Boolean isSummary) {
		this.isSummary = isSummary;
	}

	public List<ArchivesStatisticResultItem> getStatisticResult() {
		return statisticResult;
	}

	public void setStatisticResult(List<ArchivesStatisticResultItem> statisticResult) {
		this.statisticResult = statisticResult;
	}

}
