package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.MarqueeItem;

/**
 * 跑马灯事务接口
 * @author ache
 *
 */
public interface MarqueeService {
	/**
	 * 添加跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueeItem insertMarquee(MarqueeItem item) throws ErrorCodeException;
	/**
	 * 查询跑马灯列表
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MarqueeItem> getMarqueeList(MarqueeItem item) throws ErrorCodeException;
	/**
	 * 删除跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteMarquee(MarqueeItem item) throws ErrorCodeException;
	/**
	 * 更新跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueeItem updateMarquee(MarqueeItem item)throws ErrorCodeException;
	/**
	 * 查询跑马灯
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueeItem getMarquee(Integer id) throws ErrorCodeException;
	/**
	 * 查询跑马灯个数
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(MarqueeItem item) throws ErrorCodeException;
	/**
	 * 跑马灯提交
	 * @param ids
	 * @throws ErrorCodeException
	 */
	public void submiteMarquees(String ids) throws ErrorCodeException;
	/**
	 * 批量删除跑马灯
	 * @param ids
	 * @throws ErrorCodeException
	 */
	public void deleteMarquees(String ids) throws ErrorCodeException;
	/**
	 * 推送到跑马灯
	 * @param ids
	 * @param userId
	 * @throws ErrorCodeException
	 */
	public void pushMarquees(String ids,Integer userId) throws ErrorCodeException; 
	/**
	 * 通过跑马灯id拼接字符串获得跑马灯列表
	 * @param ids
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MarqueeItem> getMarqueeItemListByIds(String ids) throws ErrorCodeException;
}
