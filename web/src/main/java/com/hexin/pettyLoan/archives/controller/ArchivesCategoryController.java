package com.hexin.pettyLoan.archives.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.archives.model.ArchivesCategoryItem;
import com.hexin.pettyLoan.archives.service.ArchivesCategoryService;


@Controller
public class ArchivesCategoryController {
	@Resource(name="archivesCategoryService")
	private ArchivesCategoryService archivesCategoryService;
	
	@RequestMapping("/archivesCategory/getTree.do")
	public @ResponseBody String getTree(ArchivesCategoryItem params, String callback){
		JsonResult result;
		try {
			List<ArchivesCategoryItem> list = archivesCategoryService.getRoot(params);
			for(int i=0;i<list.size();i++){
				ArchivesCategoryItem root = list.get(i);
				root.setText(root.getName());
				int rootId = root.getId();
				if(archivesCategoryService.hasChildren(rootId)){
					List<ArchivesCategoryItem> childrenList = archivesCategoryService.getChildren(rootId);
					root.setChildren(childrenList);
				}
			}
			result = new JsonResult(1,null,list);
		} catch (ErrorCodeException ex) {
			result = new JsonResult(-1,ex.toMessage(),null);
		}
		String json= JSONUtil.toJsonpString(result,callback);
		return json;
	}
	
	@RequestMapping("/archivesCategory/insert.do")
	public @ResponseBody String insert(ArchivesCategoryItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			result = new JsonResult(1,"创建成功",archivesCategoryService.insertArchivesCategory(item,authorityUserId));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("archivesCategory/update.do")
	public @ResponseBody String update(ArchivesCategoryItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			result = new JsonResult(1,"更新成功",archivesCategoryService.updateArchivesCategory(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("archivesCategory/delete.do")
	public @ResponseBody String delete(ArchivesCategoryItem item,String callback,Integer authorityUserId){
		JsonResult result;
		try{
			result = new JsonResult(1,"删除成功",archivesCategoryService.deleteArchivesCategory(item));
		}catch(ErrorCodeException ex){
			result = new JsonResult(-1,ex.toMessage(),null);
		}
		String json= JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
