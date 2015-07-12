package com.hexin.pettyLoan.system.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.system.model.PageAuthorityItem;
import com.hexin.pettyLoan.system.model.PageItem;
import com.hexin.pettyLoan.system.service.PageService;
@Controller
public class PageItemController {
	@Resource(name="pageService")
	private PageService pageService;

	/**
	 * 根据 功能ID 取得该功能下的所有选项
	 */
	@RequestMapping("/pageItem/getPageItemAll.do")
	public @ResponseBody String getPageListAll(Integer functionId,String callback)
			throws ErrorCodeException {
		PageItem item = new PageItem();
		item.setFunctionId(functionId);
		List<PageItem> list =pageService.getPageListAll(item);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 根据 功能ID 取得该功能下的所有选项
	 */
	@RequestMapping("/pageItem/getPageItemAllByUser.do")
	public @ResponseBody String getPageListAllByUser(Integer authorityUserId,Integer functionId,String callback)
			throws ErrorCodeException {
		PageItem item = new PageItem();
		item.setFunctionId(functionId);
		item.setUserId(authorityUserId);
		int i = 0;
		List<PageItem> list =pageService.getPageListAllByUser(item);
		for(PageItem iu:list){
			if(iu.getChecked()&&"字段".equals(iu.getType())){
				i +=1;
			}
		}
		if(i==0){
			for(int ji =0;ji<list.size();ji++){
				if("字段".equals(list.get(ji).getType())){
					list.get(ji).setChecked(true);
				}
			}
		}
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * 根据 功能ID 用户ID 
	 * 取得该用户选中的选项
	 */
	@RequestMapping("/pageItem/getPageItemByUser.do")
	public @ResponseBody String getPageListByUser(Integer authorityUserId,Integer functionId,String callback)
			throws ErrorCodeException {
		PageItem item = new PageItem();
		item.setFunctionId(functionId);
		item.setUserId(authorityUserId);
		List<PageItem> list =pageService.getPageListByUser(item);
//		List<PageItem> Alllist =pageService.getPageListAll(item);
//		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
//		for(PageItem ie:list){
//			if(!map.containsKey(ie.getId())){
//				map.put(ie.getId(), ie.getId());
//			}
//		}
//		for(PageItem io:Alllist){
//			if(map.containsKey(io.getId())){
//				io.setChecked(true);
//			}else{
//				io.setChecked(false);
//			}
//		}
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * 新增  / 修改 该用户的选项 = type = “字段”
	 * 修改 先删除该用户下的所有 然后再新增进去
	 */
	@RequestMapping("/pageItem/insertPage.do")
	public @ResponseBody String insertPage(String rows, Integer authorityUserId,Integer functionId, String callback) 
			throws ErrorCodeException {
		 JsonResult result = null;
		 System.out.println("  "+rows);
		try{
			List<PageAuthorityItem> list =  JSONUtil.parseArray(rows, PageAuthorityItem.class);
			for(PageAuthorityItem item:list){
				item.setCreater(authorityUserId);
				item.setUserId(authorityUserId);
				item.setFunctionId(functionId);
			}
			pageService.insertPage(list,authorityUserId,functionId);
			result = new JsonResult(1, "设置成功！", null);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * 新增  / 修改 该用户的选项 = type = “分页”
	 * 修改 先删除该用户下的所有 然后再新增进去
	 */
	@RequestMapping("/pageItem/updatePagePation.do")
	public @ResponseBody String insertPage(PageAuthorityItem item,Integer authorityUserId,boolean checked, String callback) 
			throws ErrorCodeException {
		 JsonResult result = null;
		try{
			if(checked){
				pageService.updatePagePation(item);
			}else{
				item.setCreater(authorityUserId);
				pageService.insertPagePation(item);
			}
			
			result = new JsonResult(1, "设置成功！", null);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
}
