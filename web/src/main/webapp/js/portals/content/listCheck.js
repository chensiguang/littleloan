$(function() {
	var p = new Object();
	p.flexkey = 'contentType';
	p.authorityUserId=$.cookie("userId");// 参数中带上authorityUserId
	$.ajax({
		url : SiteUrl.System_API + "flexkey/list.do",
		type : "post",
		data : p,
		async : false,
		dataType : "jsonp",
		success : function(data) {
			if (data.error == 1) {
				$('#contentCheck-type').combobox({
					data:data.rows,
					valueField:'flexvalue',
					textField:'valueDescription'
				});
			} 
		}
	});
	
	//设置分页模式
	var list = $("#contentCheck-mainGrid");//获取当前grid名称
	list.datagrid({
		loader : function(param, success, error) {
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			param.statusArrayStr = 1,2,3;
			$.ajax({
				//调用ajax获取数据，使用jsonp方式，跨域
				url : SiteUrl.Portals_API + "content/page.do",
				type : "post",
				data : param,
				dataType : "jsonp",
				success : function(data) {
					if (data.error == 1) {
						success(data);//加载数据
					} else if (data.error == -100) {
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					} else {
						$.messager.alert("提示", data.message, "error");
					}
				}
			});
		},
		singleSelect:true,
		fit : true,
		fitColumns : true,
		pagination : true,
		loadMsg:'正在加载内容列表…',
		toolbar : '#contentCheck-gridToolbar',//绑定工具栏
		rownumbers:true,
		striped : true, 
		columns : [ [//表格绑定字段
		{
			field : "id",
			title : "",
			checkbox : true
		}, {
			field : "title",
			title : "标题",
			width : 100
		}, {
			field : "type",
			title : "内容类型",
			width : 30
		}, {
			field : "isAll",
			title : "发布范围",
			width : 30,
			formatter : function(v, r, index) {
				var str;
				switch (v) {
				case 1:
					str = "会员";
					break;
				default:
					str = "非会员";
					break;
				}
				return str;
			}
		}, {
			field : "status",
			title : "状态",
			width : 30,
			formatter : function(value, row, index) {
				var status = "";
				switch (value) {
				case 0:
					status = "新建";
					break;
				case 1:
					status = "退回";
					break;
				case 2:
					status = "待审批";
					break;
				case 3:
					status = "审批通过";
				default:
					break;
				}
				return status;
			}
		},
		 {
				field : "stime",
				title : "提交时间",
				width : 30,
				
			}] ]
	});
	var pager = list.datagrid('getPager');
	var contentByApproveContent = null;
	pager.pagination({
		beforePageText:'第',
		afterPageText:'页 共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录  共{total}条记录',
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});

	var toolbar = $("#contentCheck-gridToolbar");
	var gridDialog = $("#contentCheck-create-gridDialog");
	var form = $("#contentCheck-form");
	UI.bindEvents({
		"check-button" : function() {//工具栏新增按钮
			var row = list.datagrid("getSelected");
			if (row == null) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
			form.form("load",row);//表单清空
			gridDialog.dialog({
				title : "审核内容",
				buttons : [
						{
							text : '退回',
							handler : function() {
								if (!form.form("validate")) {//控件验证
									return;
								}
								//定义一个 flexkey对象，赋值，调用ajax新增数据
								var obj = new Object();
								obj.authorityUserId = $.cookie("userId");
								obj.approveUserId = $.cookie("userId");
								obj.id = $("#contentCheck-id").val();					
								obj.status = 1;
								$.ajax({
									url : SiteUrl.Portals_API
											+ "content/update.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
											list.datagrid("reload");//刷新页面
										} else if (data.error == -100) {
											$.messager.alert("提示",
													"会话超时，请重新登陆！", "error");
										} else {
											$.messager.alert("提示",
													data.message, "error");
										}
									}
								});
							}
						}, {
							text : '预览',
							handler : function() {
								alert("预览");
							}
						} , {
							text : '通过',
							handler : function() {
								if (!form.form("validate")) {//控件验证
									return;
								}
								//定义一个 flexkey对象，赋值，调用ajax新增数据
								var obj = new Object();
								obj.authorityUserId = $.cookie("userId");
								obj.approveUserId = $.cookie("userId");
								obj.id = $("#contentCheck-id").val();					
								obj.status = 3;
								$.ajax({
									url : SiteUrl.Portals_API
											+ "content/update.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
											list.datagrid("reload");//刷新页面
										} else if (data.error == -100) {
											$.messager.alert("提示",
													"会话超时，请重新登陆！", "error");
										} else {
											$.messager.alert("提示",
													data.message, "error");
										}
									}
								});
							}
						} ,{
							text : '取消',
							handler : function() {
								gridDialog.dialog("close");
							}
						} ]
			});
			gridDialog.dialog("open");
			contentByApproveContent = UI.initKindEditor(contentByApproveContent, "contentCheck-con", row.content, "");
		},
		"search-button" : function(e) {//查询按钮执行
			e.preventDefault();
			list.datagrid("load", {//刷新一下页面，并将查询条件作为参数传递给后台
				flexkey : $("#contentCheck-name").val()
			});
		}
	}, toolbar);
});
