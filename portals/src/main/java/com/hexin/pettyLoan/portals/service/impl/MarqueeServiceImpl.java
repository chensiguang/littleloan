package com.hexin.pettyLoan.portals.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.HtmlUtil;
import com.hexin.core.util.properties.Property;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.ContentItem;
import com.hexin.pettyLoan.portals.model.MarqueeItem;
import com.hexin.pettyLoan.portals.service.ContentService;
import com.hexin.pettyLoan.portals.service.MarqueeService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("marqueeService")
public class MarqueeServiceImpl implements MarqueeService {
	
	@Resource(name="contentService")
	private ContentService contentService;

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}

	@Override
	@AccessLog(description = "insert marquee", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public MarqueeItem insertMarquee(MarqueeItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			writeDao.insert(MarqueeItem.NAMESPACE, item);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00100", ErrorCode.PTL00101, e);
		}
		return item;
	}

	/**
	 * 查询内容列表
	 */
	@Override
	public List<MarqueeItem> getMarqueeList(MarqueeItem item)
			throws ErrorCodeException {
		return readDao.query(MarqueeItem.NAMESPACE, item);
	}

	@Override
	@AccessLog(description = "delete one marquee", operateType = OperateType.Delete, add2DB = Add2DB.Yes)
	public Boolean deleteMarquee(MarqueeItem item) throws ErrorCodeException {
		return writeDao.delete(MarqueeItem.NAMESPACE, item) > 0;
	}

	@Override
	@AccessLog(description = "update one marquee", operateType = OperateType.Update, add2DB = Add2DB.Yes)
	public MarqueeItem updateMarquee(MarqueeItem item)
			throws ErrorCodeException {
		try {
			writeDao.update(MarqueeItem.NAMESPACE, item);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00100", ErrorCode.PTL00101, e);
		}

		return item;
	}

	@Override
	@AccessLog(description = "query one marquee", operateType = OperateType.Query)
	public MarqueeItem getMarquee(Integer id) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.getOne(MarqueeItem.NAMESPACE, new MarqueeItem(id));
	}

	@Override
	public Integer getCount(MarqueeItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(MarqueeItem.NAMESPACE, item);
	}

	@Override
	@AccessLog(description = "update marquee status", operateType = OperateType.Update)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void submiteMarquees(String ids) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {

		} catch (ErrorCodeException e) {
			
			// TODO: handle exception
			throw new ErrorCodeException("PTL00103", ErrorCode.PTL00103, e);
		}

	}

	@Override
	@AccessLog(description = "delete marquees", operateType = OperateType.Delete)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void deleteMarquees(String ids) throws ErrorCodeException {
		try {
			if (ids != null) {
				for (String id : ids.split(",")) {
					MarqueeItem marquee = new MarqueeItem(Integer.valueOf(id));
					deleteMarquee(marquee);
				}
			}
		} catch (ErrorCodeException e) {
			throw new ErrorCodeException("PTL00103", ErrorCode.PTL00103, e);
		}
	}

	@Override
	@AccessLog(description = "insert marquee", operateType = OperateType.Insert,add2DB = Add2DB.Yes)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void pushMarquees(String ids, Integer userId)
			throws ErrorCodeException {
		if(ids!=null){
			for(String id:ids.split(",")){
				if(NumberUtils.isNumber(id)){
					ContentItem content = contentService.getContent(Integer.valueOf(id));
					String summary = HtmlUtil.getTextFromHtml(content.getContent());
					if(summary.length()>200){
						summary = summary.substring(0, 200);
					}
					MarqueeItem marquee = new MarqueeItem();
					marquee.setContentSummary(summary);
					marquee.setCreater(userId);
					marquee.setContentId(Integer.valueOf(id));
					marquee.setType(content.getType());
					marquee.setTitle(content.getTitle());
					//默认图片
					marquee.setPic(Property.getProperty(marquee.getType()));
					insertMarquee(marquee);
				}
			}
		}
	}

	@Override
	@AccessLog(description = "query marquee", operateType = OperateType.Query)
	public List<MarqueeItem> getMarqueeItemListByIds(String ids)
			throws ErrorCodeException {
		List<MarqueeItem> list = new ArrayList<>();
		if(ids!=null){
			for(String i :ids.split(",")){
				list.add(getMarquee(Integer.valueOf(i)));
			}
		}
		return list;
	}
}
