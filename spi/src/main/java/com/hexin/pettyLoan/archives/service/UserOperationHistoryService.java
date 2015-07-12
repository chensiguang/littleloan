/**
 * 
 */
package com.hexin.pettyLoan.archives.service;

import com.hexin.pettyLoan.archives.model.UserOperationHistoryItem;

/**
 * 日志查询
 * @author wanglei2
 *
 */
public interface UserOperationHistoryService {

	/**
	 * 日志查询
	 * @param item
	 * @return
	 */
	public Boolean allotArchivesAuthority(UserOperationHistoryItem item);
}
