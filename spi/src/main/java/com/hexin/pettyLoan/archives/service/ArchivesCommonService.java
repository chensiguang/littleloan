/**
 * 
 */
package com.hexin.pettyLoan.archives.service;

import java.util.List;
import java.util.Map;


/**
 * 基础数据相关业务接口
 * @author yinxiaolin
 *
 */
public interface ArchivesCommonService {

	/**
	 * 获取用户列表
	 * @return
	 */
	public List<String> getUsers();
	
	/**
	 * 获取档案状态列表
	 * @return
	 */
	public List<Map<Integer, String>> getArchivesStatus();
	
	/**
	 * 获取档案类别列表
	 * @return
	 */
	public List<Map<Integer, String>> getArchivesCategory();
	
	/**
	 * 获取档案来源（终端）列表
	 * @return
	 */
	public List<Map<Integer, String>> getArchivesSrcTerminal();
	
	/**
	 * 获取档案来源（系统）列表
	 * @return
	 */
	public List<Map<Integer, String>> getArchivesSrcSystem();
	
	/**
	 * 获取电子档案编号列表
	 * @return
	 */
	public List<Map<String, String>> getElectronicArchivesNo();
	
	/**
	 * 获取实体档案编号列表
	 * @return
	 */
	public List<Map<String, String>> getEntityArchivesNo();
	
	/**
	 * 获取用户动作列表
	 * @return
	 */
	public List<Map<Integer, String>> getOperatationType();
	
	/**
	 * 获取档案权限级别
	 * @return
	 */
	public List<Map<Integer, String>> getAuthorityLevel();
}
