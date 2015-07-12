package com.hexin.pettyLoan.portals.controller;

import java.util.List;
import java.util.Map;

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
import com.hexin.pettyLoan.portals.model.ContentItem;
import com.hexin.pettyLoan.portals.service.ContentService;


@Controller
public class ContentController{
	private final static Log logger = LogFactory.getLog(ContentService.class);
	@Resource(name="contentService")
	private ContentService contentService;
	
	/**
	 * 查询内容列表
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/content/list.do")
	public @ResponseBody String getContentList(ContentItem item,String callback) 
		throws ErrorCodeException {
		List<ContentItem> list = contentService.getContentList(item);
		JsonResult result = new JsonResult(1, null, list);
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	/**
	 * 查询内容分页列表
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/content/page.do")
	public @ResponseBody String getContentPageList(ContentItem item,String callback) 
		throws ErrorCodeException {
		if(item.getPage()==null){
			item.setPage(1);
		}
		if(item.getRows()==null){
			item.setRows(Constants.PAGESIZE);
		}
		item.setStart((item.getPage()-1)*item.getRows());
		List<ContentItem> list = contentService.getContentList(item);
		Integer count = contentService.getCount(item);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, item.getRows());
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	/**
	 * 修改内容
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/content/update.do")
	public @ResponseBody String updateContent(ContentItem item,String callback,Integer authorityUserId) throws ErrorCodeException{
		JsonResult result;
		try{
			result = new JsonResult(1, null, contentService.updateContent(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 添加内容
	 * @param item
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/content/insert.do")
	public @ResponseBody String addContent(ContentItem item,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1, null, contentService.insertContent(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
			logger.error(ex.toMessage(), ex);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 提交内容审核
	 * @param ids
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("content/submit.do")
	public @ResponseBody String submitContent(String ids,String callback,Integer authorityUserId)
	throws ErrorCodeException{
		JsonResult result;
		String json;
		if(ids == null ||ids.split(",").length==0){
			result = new JsonResult(-1,ErrorCode.PTL00102,null);
			json  = JSONUtil.toJsonpString(result, callback);
			return json;
		}
		contentService.submiteContent(ids);
		result = new JsonResult(1, null, ids);
		json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 删除内容
	 * @param ids
	 * @param callback
	 * @param authorityUserId
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("content/delete.do")
	public @ResponseBody String deleteContent(String ids,String callback,Integer authorityUserId)
		throws ErrorCodeException{
		JsonResult result;
		String json;
		if(ids == null ||ids.split(",").length==0){
			result = new JsonResult(-1,ErrorCode.PTL00102,null);
			json  = JSONUtil.toJsonpString(result, callback);
			return json;
		}
		contentService.deleteContents(ids);
		result = new JsonResult(1, null, ids);
		json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	/**
	 * 按类型查询内容列表各前五条
	 * @param item
	 * @param callback
	 * @return
	 * @throws ErrorCodeException
	 */
	@RequestMapping("/content/portal.do")
	public @ResponseBody String getPortalContent(String callback) 
		throws ErrorCodeException {
		List<Map<String, Object>> list = contentService.getPortalContent(5);
		JsonResult result = new JsonResult(1, null, list);
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	@RequestMapping("/content/getone.do")
	public @ResponseBody String getOne(Integer id, String callback){
		JsonResult result = new JsonResult();
		try{
			ContentItem contentItem = contentService.getContent(id);
			result = new JsonResult(1, null, contentItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
