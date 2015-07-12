package com.hexin.pettyLoan.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexin.core.exception.ErrorCodeException;
import com.hexin.core.model.JsonResult;
import com.hexin.core.util.JSONUtil;
import com.hexin.core.util.logging.Log;
import com.hexin.core.util.logging.LogFactory;
import com.hexin.pettyLoan.system.model.DepartmentItem;
import com.hexin.pettyLoan.system.model.UserinfoItem;
import com.hexin.pettyLoan.system.service.DepartmentService;
import com.hexin.pettyLoan.system.service.UserinfoService;
/**
 * 部门管理
 * @author magang
 */
@Controller
public class DepartmentController {
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	/**
	 * param : 机构ID
	 * 取得 部门列表 返回树形结构
	 */
	@RequestMapping("/department/getDeptTree.do")
	public @ResponseBody String getDeptList(DepartmentItem item,String callback)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		List<DepartmentItem> list = departmentService.getDeptList(item);	
		JsonResult result = new JsonResult(1, null, list);
		String jsonresult = JSONUtil.toJsonpString(result, callback);
		return jsonresult;
	}
	/**
	 * param : DepartmentItem
	 * 新增部门
	 */
	@RequestMapping("/department/insertDepartment.do")
	public @ResponseBody String insertDepartment(DepartmentItem item, Integer authorityUserId,String callback)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		JsonResult result = new JsonResult();
		try{
			item.setCreater(authorityUserId);
			result = new JsonResult(1, null,departmentService.insertDepartment(item) );
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}


	/**
	 * param : DepartmentItem
	 * 修改部门
	 */
	@RequestMapping("/department/updateDepartment.do")
	public @ResponseBody String updateDepartment(DepartmentItem item,String callback)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null,departmentService.updateDepartment(item) );
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}


	/**
	 * param : 部门ID
	 * 删除部门 / 为假删除 isvalid = 0
	 */
	@RequestMapping("/department/deleteDepartment.do")
	public @ResponseBody String deleteDepartment(DepartmentItem item,String callback) {
		// TODO Auto-generated method stub
		JsonResult result = new JsonResult();
		try{
			UserinfoItem param = new UserinfoItem();
			param.setDepartmentId(item.getId());
			Integer count = userinfoService.getCount(param);
			if(count!=null&&count>0){
				result = new JsonResult(-1,"该部门:"+item.getDepartmentName()+" 已分配用户,不能删除", null);
			}else{
				result = new JsonResult(1, null,departmentService.deleteDepartment(item) );
			}	
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	} 

	
	@RequestMapping("/department/list.do")
	public @ResponseBody String list(DepartmentItem params,String callback)
			throws ErrorCodeException {		
		JsonResult result = new JsonResult();
		try{
			result = new JsonResult(1, null, departmentService.getDeptList(params) );
		}
		catch(ErrorCodeException ex){
			result = new JsonResult(-1, ex.toMessage(), null);
		}
		String json =JSONUtil.toJsonpString(result, callback);
		return json;
	}
}
