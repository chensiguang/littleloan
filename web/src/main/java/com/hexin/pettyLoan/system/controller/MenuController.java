package com.hexin.pettyLoan.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.model.PagedJsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.pettyLoan.common.Constants;
import com.hexin.pettyLoan.system.model.FunctionItem;
import com.hexin.pettyLoan.system.model.MenuItem;
import com.hexin.pettyLoan.system.service.MenuService;

/**
 *  单点 登录 动态菜单栏
 * @author 
 *
 */
@Controller
public class MenuController {
	
	@Resource(name="menuService")
	private MenuService menuService;
	/**
	 * --------------------!--菜单权限管理
	 */
	/**
	 * 根据 用户  ID
	 * 取得菜单列表
	 * param ： 登录用户ID, 系统ID(sysid)
	 */ 
	@RequestMapping("/menu/getMenuList.do")
	public @ResponseBody String getMenuList(Integer authorityUserId,Integer systemId,String callback)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<MenuItem> mlist = menuService.getMenuList(authorityUserId,systemId);
		List<MenuItem> list = new ArrayList<MenuItem>();
		if(list!=null){
			for(MenuItem item:mlist){
				List<FunctionItem> flist = menuService.queryFunctionByMenu(authorityUserId,systemId,item.getId()); 
				item.setFunctionList(flist);
				list.add(item);
			}
		}
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * 根据 用户  ,菜单ID
	 * 取得菜单下的功能列表
	 * param ：  登录用户ID, 系统ID(sysid)，菜单ID(menuid)
	 */ 
	@RequestMapping("/menu/getFunctionByMenu.do")
	public @ResponseBody String queryFunctionByMenu(Integer authorityUserId,
			Integer systemId, Integer menuId,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<FunctionItem> list = menuService.queryFunctionByMenu(authorityUserId,systemId,menuId);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	/**
	 * 根据 用户   
	 * 取得该用户所有权限功能
	 * param ： 登录用户ID
	 */ 
	@RequestMapping("/menu/queryFunctionByUser.do")
	public @ResponseBody String queryFunctionByUser(Integer authorityUserId ,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<FunctionItem> list = menuService.queryFunctionByUser(authorityUserId);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * --------------------!--菜单-功能 管理
	 */
	/**
	 * 根据 用户  ,菜单ID
	 * 取得菜单下的所有的功能列表 不涉及个人权限
	 * param ：   菜单ID(menuid)
	 */ 
	@RequestMapping("/menu/queryAllFunMenu.do")
	public @ResponseBody String queryAllFunMenu(MenuItem params,String type,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<FunctionItem> list = menuService.queryAllFunMenu(params,type);
		int count = menuService.queryAllFunMenuCount(params,type);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * 根据 用户  ,菜单ID
	 * 新增 功能 不涉及个人权限
	 * param ：   菜单ID(menuid)
	 */ 
	@RequestMapping("/menu/insertFunMenu.do")
	public @ResponseBody String insertFunMenu(String functionIds,Integer menuId, Integer authorityUserId,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub
		 JsonResult result = null;
			try{
				String[] array = functionIds.split(",");
				List<Integer> ids = new ArrayList<Integer>();
				for(String str:array){
					ids.add(Integer.valueOf(str));
				}
				FunctionItem item = new FunctionItem();
				item.setIds(ids);
				item.setMenuId(menuId);
				item.setCreater(authorityUserId);
				menuService.insertFunMenu(item);
				result = new JsonResult(1, "删除成功！", null);
			}
			catch(ErrorCodeException ex){
				result = new JsonResult(-1, ex.toMessage(), null);
			}
			String jsonresult = JSONUtil.toJsonpString(result, callback);
			return jsonresult;
	}
	/**
	 * 根据 用户  ,菜单ID
	 * 修改 功能 不涉及个人权限
	 * param ：   FunctionItem item
	 */ 
	@RequestMapping("/menu/updateFunMenu.do")
	public @ResponseBody String updateFunMenu(FunctionItem item, Integer authorityUserId,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub;
		 JsonResult result = null;
			try{
				item.setCreater(authorityUserId);
				menuService.updateFunMenu(item);
				result = new JsonResult(1, "修改成功！", null);
			}
			catch(ErrorCodeException ex){
				result = new JsonResult(-1, ex.toMessage(), null);
			}
			String jsonresult = JSONUtil.toJsonpString(result, callback);
			return jsonresult;
	}
	/**
	 * 根据 用户  ,菜单ID
	 * 删除 功能 不涉及个人权限
	 * param ：   菜单ID(menuid)
	 */ 
	@RequestMapping("/menu/deleteFunMenu.do")
	public @ResponseBody String deleteFunMenu(String functionIds,Integer menuId,String callback) throws ErrorCodeException {
		// TODO Auto-generated method stub
		 JsonResult result = null;
			try{
				String[] array = functionIds.split(",");
				List<Integer> ids = new ArrayList<Integer>();
				for(String str:array){
					ids.add(Integer.valueOf(str));
				}
				FunctionItem item = new FunctionItem();
				item.setIds(ids);
				item.setMenuId(menuId);
				menuService.deleteFunMenu(item);
				result = new JsonResult(1, "设置成功！", null);
			}
			catch(ErrorCodeException ex){
				result = new JsonResult(-1, ex.toMessage(), null);
			}
			String jsonresult = JSONUtil.toJsonpString(result, callback);
			return jsonresult;
	}
	
	/**
	 * --------------------!--菜单管理
	 */
	@RequestMapping("/menu/page.do")
	public @ResponseBody String page(MenuItem params, String callback) {
		if(params.getPage()==null){
			params.setPage(1);
		}
		if(params.getRows()==null){
			params.setRows(Constants.PAGESIZE);
		}
		params.setStart((params.getPage()-1)*params.getRows());
		List<MenuItem> list = menuService.getAllMenuPage(params);
		Integer count = menuService.getCount(params);
		PagedJsonResult result = new PagedJsonResult(list, 1, null, count, params.getRows());
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping("/menu/list.do")
	public @ResponseBody String list(MenuItem params, String callback) {
		List<MenuItem> list = menuService.getAllMenuList(params);
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	
	@RequestMapping(value="/menu/insert.do")
	public @ResponseBody String insert(MenuItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1, null, menuService.insertMenu(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/menu/update.do")
	public @ResponseBody String update(MenuItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, menuService.updateMenu(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	@RequestMapping("/menu/delete.do")
	public @ResponseBody String delete(MenuItem item, String callback, Integer authorityUserId){
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, menuService.deleteMenu(item));
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
	
	
	@RequestMapping("/menu/getone.do")
	public @ResponseBody String getOne(MenuItem item, String callback){
		JsonResult result = new JsonResult();
		try{
			MenuItem menuItem = menuService.getOneMenuItem(item);
			result = new JsonResult(1, null, menuItem);
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}

	
}
