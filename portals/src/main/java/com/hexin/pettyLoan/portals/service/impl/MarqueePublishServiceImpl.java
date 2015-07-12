package com.hexin.pettyLoan.portals.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.MarqueeItem;
import com.hexin.pettyLoan.portals.model.MarqueePublishItem;
import com.hexin.pettyLoan.portals.service.MarqueePublishService;
import com.hexin.pettyLoan.portals.service.MarqueeService;
import com.vteba.service.context.spring.ApplicationContextHolder;

@Service("marqueePublishService")
public class MarqueePublishServiceImpl implements MarqueePublishService {
	
	@Resource(name="marqueeService")
	private MarqueeService marqueeService;

	@Resource(name = "writeDaoHelper")
	DaoHelper writeDao;

	@Resource(name = "readDaoHelper")
	DaoHelper readDao;

	public void initMethod() {
		writeDao = ApplicationContextHolder.getBean("writeDaoHelper");
		readDao = ApplicationContextHolder.getBean("readDaoHelper");
	}

	@Override
	@AccessLog(description = "insert marqueePublish", operateType = OperateType.Insert, add2DB = Add2DB.Yes)
	public MarqueePublishItem insertMarqueePublish(MarqueePublishItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			writeDao.insert(MarqueePublishItem.NAMESPACE, item);
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
	@AccessLog(description = "query marqueePublish", operateType = OperateType.Query)
	public List<MarqueePublishItem> getMarqueePublishList(MarqueePublishItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.query(MarqueePublishItem.NAMESPACE, item);
	}

	@Override
	@AccessLog(description = "delete one marqueePublish", operateType = OperateType.Delete, add2DB = Add2DB.Yes)
	public Boolean deleteMarqueePublish(MarqueePublishItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return writeDao.delete(MarqueePublishItem.NAMESPACE, item) > 0;
	}

	@Override
	@AccessLog(description = "update one marqueePublish", operateType = OperateType.Update, add2DB = Add2DB.Yes)
	public MarqueePublishItem updateMarqueePublish(MarqueePublishItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			writeDao.update(MarqueePublishItem.NAMESPACE, item);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00100", ErrorCode.PTL00101, e);
		}
		return item;
	}

	@Override
	@AccessLog(description = "query one marqueePublish", operateType = OperateType.Query)
	public MarqueePublishItem getMarqueePublish(Integer id) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.getOne(MarqueePublishItem.NAMESPACE, new MarqueePublishItem(id));
	}

	@Override
	@AccessLog(description = "query marqueePublish count", operateType = OperateType.Query)
	public Integer getCount(MarqueePublishItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		return readDao.count(MarqueePublishItem.NAMESPACE, item);
	}


	@Override
	@AccessLog(description = "delete marqueePublish", operateType = OperateType.Delete)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public void deleteMarqueePublishes(String ids) throws ErrorCodeException {
		// TODO Auto-generated method stub
		try {
			if (ids != null) {
				for (String id : ids.split(",")) {
					MarqueePublishItem marquee = new MarqueePublishItem(Integer.valueOf(id));
					deleteMarqueePublish(marquee);
				}
			}
		} catch (ErrorCodeException e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00103", ErrorCode.PTL00103, e);
		}
	}

	@Override
	@AccessLog(description = "update marqueePublish", operateType = OperateType.Update)
	@Transactional(rollbackOn = ErrorCodeException.class)
	public MarqueePublishItem checkMarqueePublish(MarqueePublishItem item) {
	     
		// TODO Auto-generated method stub
		try {
			//修改跑马灯发布状态
			writeDao.update(MarqueePublishItem.NAMESPACE, item);
			//判断是否为审核通过
			if(item.getStatus()==3){
				//将所有跑马灯改为未发布状态
				MarqueeItem m = new MarqueeItem(-999);
				m.setSortNo(-1);
				m.setStatus(0);
				marqueeService.updateMarquee(m);
				//将审核通过的跑马灯改为审核通过状态
				MarqueePublishItem marqueeItem = getMarqueePublish(item.getId());
				String solution = marqueeItem.getSolution()==null? "":marqueeItem.getSolution();
				String[] ids = solution.split(",");
				for(int i =0;i<ids.length;i++){
					MarqueeItem marquee = new MarqueeItem(Integer.valueOf(ids[i]));
					marquee.setSortNo(i+1);
					marquee.setStatus(1);
					marqueeService.updateMarquee(marquee);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ErrorCodeException("PTL00100", ErrorCode.PTL00101, e);
		}
		return item;
	}
}
