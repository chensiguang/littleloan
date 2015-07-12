package com.hexin.pettyLoan.portals.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.MarqueePublishItem;
import com.hexin.pettyLoan.portals.service.MarqueePublishService;


@Controller
public class MarqueePublishController{
	private final static Log logger = LogFactory.getLog(MarqueePublishService.class);
	@Resource(name="marqueePublishService")
	private MarqueePublishService marqueePublishService;

	/**
	 * 提交跑马灯发布
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/publish/submit.do")
	public @ResponseBody String submitMarqueePublish(MarqueePublishItem item,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		try{
			item = marqueePublishService.insertMarqueePublish(item);
			result = new JsonResult(1, null, item);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}

	/**
	 * 查询跑马灯发布分页列表
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/publish/page.do")
	public @ResponseBody String getMarqueePublishPageList(MarqueePublishItem item,String callback) 
		throws ErrorCodeException {
		if(item.getPage()==null){
			item.setPage(1);
		}
		if(item.getRows()==null){
			item.setRows(Constants.PAGESIZE);
		}
		item.setStart((item.getPage()-1)*item.getRows());
		List<MarqueePublishItem> list = marqueePublishService.getMarqueePublishList(item);
		Integer count = marqueePublishService.getCount(item);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, item.getRows());
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	/**
	 * 修改跑马灯发布
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/publish/update.do")
	public @ResponseBody String updateMarqueePublish(MarqueePublishItem item,String callback,Integer authorityUserId) throws ErrorCodeException{
		JsonResult result;
		try{
			result = new JsonResult(1, null, marqueePublishService.updateMarqueePublish(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 审核跑马灯发布
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/publish/check.do")
	public @ResponseBody String checkMarqueePublish(MarqueePublishItem item,String callback,Integer authorityUserId) throws ErrorCodeException{
		JsonResult result;
		try{
			result = new JsonResult(1, null, marqueePublishService.checkMarqueePublish(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	
	
	@RequestMapping("publish/delete.do")
	public @ResponseBody String deleteMarqueePublish(String ids,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		String json;
		if(ids == null ||ids.split(",").length==0){
			result = new JsonResult(-1,ErrorCode.PTL00102,null);
			json  = JSONUtil.toJsonpString(result, callback);
			return json;
		}
		marqueePublishService.deleteMarqueePublishes(ids);
		result = new JsonResult(1, null, ids);
		json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
}
