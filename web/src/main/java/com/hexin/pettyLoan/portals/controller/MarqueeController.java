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
import com.hexin.core.util.UploadPathManager;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.portals.model.MarqueeItem;
import com.hexin.pettyLoan.portals.service.MarqueeService;


@Controller
public class MarqueeController{
	private final static Log logger = LogFactory.getLog(MarqueeService.class);
	@Resource(name="marqueeService")
	private MarqueeService marqueeService;

	
	/**
	 * 查询跑马灯列表
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/marquee/list.do")
	public @ResponseBody String getPortalMarqueeList(MarqueeItem item,String callback) 
		throws ErrorCodeException {
		List<MarqueeItem> list = marqueeService.getMarqueeList(item);
		JsonResult result = new JsonResult(1, null, list);
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	/**
	 * 添加跑马灯
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/marquee/push.do")
	public @ResponseBody String pushMarquees(String ids,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		try{
			marqueeService.pushMarquees(ids, authorityUserId);
			result = new JsonResult(1, null, ids);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}

	/**
	 * 查询跑马灯分页列表
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/marquee/page.do")
	public @ResponseBody String getMarqueePageList(MarqueeItem item,String callback) 
		throws ErrorCodeException {
		if(item.getPage()==null){
			item.setPage(1);
		}
		if(item.getRows()==null){
			item.setRows(Constants.PAGESIZE);
		}
		item.setStart((item.getPage()-1)*item.getRows());
		List<MarqueeItem> list = marqueeService.getMarqueeList(item);
		for (MarqueeItem marqueeItem : list) {
			marqueeItem.setPic(UploadPathManager.getUploadWebPath() + marqueeItem.getPic());
		}
		Integer count = marqueeService.getCount(item);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, item.getRows());
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	/**
	 * 修改跑马灯
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/marquee/update.do")
	public @ResponseBody String updateMarquee(MarqueeItem item,String callback,Integer authorityUserId) throws ErrorCodeException{
		JsonResult result;
		try{
			result = new JsonResult(1, null, marqueeService.updateMarquee(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	
	@RequestMapping("/marquee/publish.do")
	public @ResponseBody String getMarqueeListByIds(String ids,String callback,Integer authorityUserId) throws ErrorCodeException{
		JsonResult result;
		try{
			List<MarqueeItem> list = marqueeService.getMarqueeItemListByIds(ids);
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("marquee/delete.do")
	public @ResponseBody String deleteMarquee(String ids,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		String json;
		if(ids == null ||ids.split(",").length==0){
			result = new JsonResult(-1,ErrorCode.PTL00102,null);
			json  = JSONUtil.toJsonpString(result, callback);
			return json;
		}
		marqueeService.deleteMarquees(ids);
		result = new JsonResult(1, null, ids);
		json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/marquee/portal.do")
	public @ResponseBody String getPortalList(String callback) throws ErrorCodeException{
		JsonResult result;
		try{
			List<MarqueeItem> list = marqueeService.getMarqueeList(null);
			for (MarqueeItem item : list) {
				item.setPic(UploadPathManager.getUploadWebPath() + item.getPic());
			}
			result = new JsonResult(1, null, list);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/marquee/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			MarqueeItem marqueeItem = marqueeService.getMarquee(id);
			result = new JsonResult(1, null, marqueeItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
