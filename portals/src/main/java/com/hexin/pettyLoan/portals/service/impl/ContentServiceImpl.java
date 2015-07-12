package com.hexin.pettyLoan.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.util.ArrayUtil;
import com.hexin.core.util.MapUtil;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.constants.ContentConstants;
import com.hexin.pettyLoan.portals.model.ContentItem;
import com.hexin.pettyLoan.portals.service.ContentService;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}

	@Override
	@AccessLog(description = "insert content", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public ContentItem insertContent(ContentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			writeDao.insert(ContentItem.NAMESPACE, item);
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
	@AccessLog(description = "query Content", operateType = OperateType.Query)
	public List<ContentItem> getContentList(ContentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(ContentItem.NAMESPACE, item);
	}

	@Override
	@AccessLog(description = "delete one content", operateType = OperateType.Delete, add2DB = Add2DB.Yes)
	public Boolean deleteContent(ContentItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return writeDao.delete(ContentItem.NAMESPACE, item) > 0;
	}

	@Override
	@AccessLog(description = "update one content", operateType = OperateType.Update, add2DB = Add2DB.Yes)
	public ContentItem updateContent(ContentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			writeDao.update(ContentItem.NAMESPACE, item);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00100", ErrorCode.PTL00101, e);
		}

		return item;
	}

	@Override
	public ContentItem getContent(Integer id) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.getOne(ContentItem.NAMESPACE, new ContentItem(id));
	}

	@Override
	@AccessLog(description = "query content count", operateType = OperateType.Query)
	public Integer getCount(ContentItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(ContentItem.NAMESPACE, item);
	}

	@Override
	@AccessLog(description = "update content status", operateType = OperateType.Update)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void submiteContent(String ids) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			if (ids != null) {
				for (String id : ids.split(",")) {
					ContentItem content = new ContentItem(Integer.valueOf(id));
					content.setStatus(ContentConstants.PENDING_STATUS);
					content.setStime(new Date());
					updateContent(content);
				}
			}
		} catch (ErrorCodeException e) {
			
			// TODO: handle exception
			throw new ErrorCodeException("PTL00103", ErrorCode.PTL00103, e);
		}

	}

	@Override
	@AccessLog(description = "delete contents", operateType = OperateType.Delete)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void deleteContents(String ids) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			if (ids != null) {
				for (String id : ids.split(",")) {
					ContentItem content = new ContentItem(Integer.valueOf(id));
					deleteContent(content);
				}
			}
		} catch (ErrorCodeException e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00103", ErrorCode.PTL00103, e);
		}
	}
	
	/**
	 * 查询内容列表各类型的前num条
	 * 内网门户首页, 未登录时, 不用记录日志
	 */
	@Override
	public List<Map<String, Object>> getPortalContent(Integer num) throws ErrorCodeException {
		//先获取当前有效的内容类型
		FlexkeyItem params = new FlexkeyItem(ContentItem.TYPE_FLEXKEY, null);
		List<FlexkeyItem> contentTypes = readDao.query(FlexkeyItem.NAMESPACE, params);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(contentTypes.size());
		//组装查询SQL
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("types", ArrayUtil.joinStrForSQL(contentTypes, "flexvalue"));
		sqlParams.put("num", num);
		List<ContentItem> contentList = readDao.getSqlSessionTemplate().selectList(ContentItem.NAMESPACE+".queryForPortal", sqlParams);
		Map<String, List<ContentItem>> typeContentList = MapUtil.map(contentList, "type");
		for (FlexkeyItem item : contentTypes) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", item.getFlexvalue());
			map.put("name", item.getValueDescription());
			map.put("list", typeContentList.get(item.getFlexvalue()));
			result.add(map);
		}
		return result;
	}
}
