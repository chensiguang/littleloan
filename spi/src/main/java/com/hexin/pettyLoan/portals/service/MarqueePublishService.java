package com.hexin.pettyLoan.portals.service;

import java.util.List;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.portals.model.MarqueePublishItem;

/**
 * 跑马灯发布事务接口
 * @author ache
 *
 */
public interface MarqueePublishService {
	/**
	 * 添加跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueePublishItem insertMarqueePublish(MarqueePublishItem item) throws ErrorCodeException;
	/**
	 * 查询跑马灯列表
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public List<MarqueePublishItem> getMarqueePublishList(MarqueePublishItem item) throws ErrorCodeException;
	/**
	 * 删除跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public Boolean deleteMarqueePublish(MarqueePublishItem item) throws ErrorCodeException;
	/**
	 * 更新跑马灯
	 * @param 跑马灯
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueePublishItem updateMarqueePublish(MarqueePublishItem item)throws ErrorCodeException;
	/**
	 * 查询跑马灯
	 * @param id
	 * @return
	 * @throws ErrorCodeException
	 */
	public MarqueePublishItem getMarqueePublish(Integer id) throws ErrorCodeException;
	/**
	 * 查询跑马灯个数
	 * @param item
	 * @return
	 * @throws ErrorCodeException
	 */
	public Integer getCount(MarqueePublishItem item) throws ErrorCodeException;

	/**
	 * 删除多个跑马灯发布
	 * @param ids
	 * @throws ErrorCodeException
	 */
	public void deleteMarqueePublishes(String ids) throws ErrorCodeException;
	/**
	 * 审核跑马灯发布内容
	 * @param item
	 * @return
	 */
	public MarqueePublishItem checkMarqueePublish(MarqueePublishItem item);
}
