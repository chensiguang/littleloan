/**
 * 
 */
package com.hexin.pettyLoan.archives.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.archives.model.ArchivesStatisticItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesFileItem;
import com.hexin.pettyLoan.archives.model.ElectronicArchivesItem;

/**
 * 电子档案数据存取相关业务接口
 * @author yinxiaolin
 *
 */
public interface ArchivesDataStorageService {

	/**
	 * 数据存储
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem putArchivesData(ElectronicArchivesItem item) throws ErrorCodeException;
	
	/**
	 * 数据获取（档案详情）
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public ElectronicArchivesItem getArchivesData(ElectronicArchivesItem item) throws ErrorCodeException;
	
}
