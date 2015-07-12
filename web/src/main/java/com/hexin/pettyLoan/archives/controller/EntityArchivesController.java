package com.hexin.pettyLoan.archives.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.archives.model.EntityArchivesItem;
import com.hexin.pettyLoan.archives.service.EntityArchivesService;
import com.hexin.pettyLoan.common.Constants;

import javax.annotation.Resource;

@Controller
public class EntityArchivesController {
	@Resource(name="entityArchivesService")
	private EntityArchivesService entityArchivesService;
	
	@RequestMapping("/entityArchives/list.do")
	public @ResponseBody String list(EntityArchivesItem params,String callback){
		JsonResult result = new JsonResult(1,null,entityArchivesService.getEntityArchivesList(params));
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	@RequestMapping("/entityArchives/page.do")
	public @ResponseBody String page(EntityArchivesItem params, String callback){
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<EntityArchivesItem> list = entityArchivesService.getEntityArchivesList(params);
		Integer count = entityArchivesService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String json = JSONUtil.toJsonpString(result,callback);
		return json;
	}
	@RequestMapping("/entityArchives/insert.do")
	public @ResponseBody String intsert(EntityArchivesItem item, String callback, Integer authorityUserId){
		JsonResult result;
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1, "创建成功", entityArchivesService.insertArchives(item));
			
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	@RequestMapping("/entityArchives/update.do")
	public @ResponseBody String update(EntityArchivesItem item, String callback, Integer authorityUserId){
		JsonResult result;
		try{
			item.setMender(authorityUserId);
			result = new JsonResult(1, "修改成功", entityArchivesService.updateArchives(item));
			
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json = JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
}
