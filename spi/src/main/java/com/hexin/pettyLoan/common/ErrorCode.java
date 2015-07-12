package com.hexin.pettyLoan.common;

public class ErrorCode {
	public final static String WF00001  = "工作流综合错误信息！";
	public final static String SYS00001 = "该值集已经存在，请确认！";//hqt
	public final static String SYS00002 = "执行方法时发生错误，请联系管理员！";//hqt
	public final static String SYS00003 = "该组织代码已经存在，请确认！";
	public final static String SYS00004 = "该组织中已存在此角色，请确认！";
	public final static String SYS00005 = "该用户已配置此角色，请确认！";
	public final static String SYS00006 = "该此功能名已存在，请确认！";
	public final static String SYS00007 = "该角色已配置此功能，请确认！";
	public final static String SYS00008 = "该用户名已经存在，请确认！";
	
	/***********************  门户内容异常码定义       ************************/
	public final static String PTL00301 = "问卷记录不存在或已被删除";
	public final static String PTL00302 = "更新问卷状态不能为空";
	public final static String PTL00303 = "对不起，该问卷已被退回不能再次审核";
	public final static String PTL00304 = "对不起，存在同名的问卷";
	public final static String PTL00305 = "对不起，该操作参数格式不正确";
	public final static String PTL00306 = "对不起，该问卷还没录入问题项";
	public final static String PTL00307 = "该问卷已经提交审核不能进行修改";
	public final static String PTL00308 = "该问卷已经审核通过不能进行修改";
	public final static String PTL00309 = "对不起，该记录不存在或已被删除";
	public final static String PTL00310 = "该问卷已经提交审核不能进行删除";
	public final static String PTL00311 = "该问卷已经审核通过不能进行删除";
	public final static String PTL00312 = "该问卷已经提交审核不能进行排序";
	public final static String PTL00313 = "该问卷已经审核通过不能进行排序";
	public final static String PTL00314 = "对不起，该问题选项不属于同一个问卷不能进行排序";
	public final static String PTL00315 = "对不起，该问卷已经提交审核不能进行新增";
	public final static String PTL00316 = "对不起，该问卷已经审核通过不能进行新增";
	
	
	public final static String PTL00401 = "该数据已存在, 请确认!";
	public final static String PTL00402 = "该数据已被删除, 请刷新页面!";
	public final static String PTL00403 = "该数据状态已发生变化, 请刷新页面!";
	public final static String PTL00101 = "该内容中的信息与其他信息有冲突，请确认";
	public final static String PTL00102 = "对不起,请选择你所要选择的内容";
	public final static String PTL00103 = "内容主键的类型有无 无法转化成数字类型";
	/***********************end  门户内容异常码定义      *********************/

	
	
	/***********************  CRM异常码定义       ************************/
	public final static String CRM00001 = "此条客户信息已存在";
	public final static String CRM00002 = "此条客户信息不存在";

	/*********************** end  CRM异常码定义       ************************/

	
	/***********************  电子档案异常码定义       ************************/
	public final static String ARC00001 = "该数据已存在, 请确认!";
	public final static String ARC00002 = "该数据已存在, 请确认!";
	public final static String ARC00003 = "该数据已存在, 请确认!";
	public final static String ARC00004 = "该数据已存在, 请确认!";
	public final static String ARC00005 = "该数据已存在, 请确认!";
	public final static String ARC00006 = "该数据已存在, 请确认!";
	public final static String ARC00007 = "该数据已存在, 请确认!";
	public final static String ARC00008 = "该数据已存在, 请确认!";
	public final static String ARC00009 = "该数据已存在, 请确认!";
	public final static String ARC00010 = "该数据已存在, 请确认!";
	/***********************  电子档案异常码定义       ************************/

	/***********************  财务会计管理异常码定义       ************************/
	
	public final static String FA00001 = "系统费率代码已存在";
	public final static String FA00002 = "系统开关控制参数已存在";
	public final static String FA00003 = "该业务类别下的参数值已存在";
	public final static String FA01000 = "查询数据字典失败";
	public final static String FA01001 = "查询银行账户失败";
	public final static String FA01002 = "添加银行账户失败";
	/*********************** end   财务会计管理 异常码定义       ************************/

}
