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
				$('#marquee-type').combobox({
					data:data.rows,
					valueField:'flexvalue',
					textField:'valueDescription'
				});
			} 
		}
	});
	
	
	//设置分页模式
	var list = $("#marquee-mainGrid");//获取当前grid名称
	//跑马灯发布table列表
	var submitlist = $("#marquee-publish-submit-mainGrid");
	var toolbar = $("#marquee-gridToolbar");
	var gridDialog = $("#marquee-create-gridDialog");
	var form = $("#marquee-form");
	var publishform = $("#marqueePublish-form");
	var dialog = $("#marquee-publish-submit-mainGrid");
	var publishlist = $("#marquee-publish-mainGrid");
	var seelist = $("#marquee-publish-see-mainGrid");
	var seeDialog = $("#marquee-publish-see-gridDialog");
	var publishtoolbar = $("#publish-gridToolbar");
	list.datagrid({
		loader : function(param, success, error) {
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url : SiteUrl.Portals_API + "marquee/page.do",
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
		fit : true,
		fitColumns : true,
		pagination : true,
		loadMsg:'正在加载跑马灯列表…',
		toolbar : '#marquee-gridToolbar',//绑定工具栏
		rownumbers:false,
		striped : true, 
		columns : [ [//表格绑定字段
		{
			field : "id",
			title : "",
			checkbox : true
		}, {
			field : "title",
			title : "标题",
			width : 40
		}, {
			field : "type",
			title : "类型",
			width : 30
		}, {
			field : "contentSummary",
			title : "内容摘要",
			width : 100,
			formatter : function(v, r, index) {
				if(v!=null&&v.length>20){
					v = v.substring(0,20);
				}
				return v;
			}
		}, {
			field : "status",
			title : "状态",
			width : 30,
			formatter : function(value, row, index) {
				if(value==0){
					return "未发布";
				}
				return "已发布";
			}
		} ,{
			field : "sortNo",
			title : "排序号",
			width : 30,
		},
		 {
			field : "others",
			title : "操作",
			width : 30,
			formatter:function(value,row,index){
				return "<a style='text-decoration:none;color:blue;cursor:pointer'>预览</a>";
			}
		} ] ]
	});

	publishlist.datagrid({
		loader : function(param, success, error) {
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url : SiteUrl.Portals_API + "publish/page.do",
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
		fit : true,
		fitColumns : true,
		pagination : true,
		loadMsg:'正在加载跑马灯列表…',
		toolbar : '#publish-gridToolbar',//绑定工具栏
		rownumbers:false,
		striped : true, 
		columns : [ [//表格绑定字段
		{
			field:"id",
			title:"",
			checkbox:true
		},
		{
			field : "description",
			title : "描述",
			width : 40
		}, {
			field : "stime",
			title : "提交时间",
			width : 30
		}, {
			field : "status",
			title : "状态",
			width : 30,
			formatter : function(value, row, index) {
				var status = "";
				switch (value) {
				case 0:
					status = "待审批";
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
		}] ]
	});
	

	UI.bindEvents({
		"submit-button" : function() {
			var rows = list.datagrid("getChecked");	
			var data = new Object();
			data.rows = rows;
			if (rows == null||rows.length<1) { 
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else{
				dialog.dialog({
					title : "跑马灯发布提交",
					buttons : [
							{
								text : '取消',//取消按钮的事件
								handler : function() {
									gridDialog.dialog("close");
								}
							}, {
								text : '预览',//取消按钮的事件
								handler : function() {
									gridDialog.dialog("close");
								}
							},{
								text : '确定',//保存按钮的事件 
								handler : function() {
									if (!publishform.form("validate")) {//控件验证
										return;
									}
									var data=submitlist.datagrid("getData");
									var obj = new Object();	
									var solution = "";
									//定义一个 marquee对象，更新到数据库
									for(var i in data.rows){
										solution = solution + data.rows[i].id;
										if(i!=data.rows.length-1){
											solution = solution + ',';
										}
									}
									obj.solution = solution;
									obj.description = $("#marqueePublish-description").val();
									obj.creater = $.cookie("userId");
									obj.authorityUserId =  $.cookie("userId");
									$.ajax({
										url : SiteUrl.Portals_API
												+ "publish/submit.do",
										type : "post",
										data : obj,
										dataType : "jsonp",
										success : function(data) {
											if (data.error == 1) {
												dialog.dialog("close");//如果成功关闭窗口，刷新页面
												list.datagrid("reload");
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
							}]
				});
				dialog.dialog("open");
				submitlist.datagrid({
					data:data,
					fit : true,
					dragSelection:true,
					fitColumns : true,
					pagination : false,
					loadMsg:'正在加载跑马灯发布列表…',
					rownumbers:true,
					singleSelect:true,
					striped : true, 
					toolbar:$("#marqueePublishSubmit-gridToolbar"),
					columns : [ [//表格绑定字段
					 {
						field : "title",
						title : "标题",
						width : 40
					}, {
						field : "type",
						title : "类型",
						width : 40
					}]],	  
					onLoadSuccess:function(){				
						$(this).datagrid("enableDnd");
					}
				});
			}
		},
		"remove-button":function(){
			var rows = list.datagrid("getChecked");
			if (rows == null||rows.length<1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else{
				$.messager.confirm("操作提示", "确定要删除吗？", function(result) {
					var param = new Object();
					var ids = "";
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					if(result){
						for(var i in rows){
							ids = ids + rows[i].id;
							if(i!=rows.length-1)
								ids = ids + ",";
						}
						param.ids = ids;
						$.ajax({
							url : SiteUrl.System_API + "marquee/delete.do",
							type : "post",
							data : param,
							dataType : "jsonp",
							success : function(data) {
								if (data.error == 1) {
									list.datagrid("reload");
								} else if (data.error == -100) {
									$.messager.alert("提示", "会话超时，请重新登陆！", "error");
								} else {
									$.messager.alert("提示", data.message, "error");
								}
							}
						});
					}
				});
			}
		},
		"update-button":function(){
			var rows = list.datagrid("getChecked");
			if (rows == null||rows.length<1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else if(rows.length>1){
				$.messager.alert("提示", "您选择的内容过多，请选择一条记录！", "info");
				return;
			}
			form.form("load", rows[0]);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
			gridDialog.dialog({
				title : "编辑数据",
				buttons : [
					{
						text : '保存',//保存按钮的事件 
						handler : function() {
							if (!form.form("validate")) {//控件验证
								return;
							}
							var obj = new Object();	
							//定义一个 marquee对象，更新到数据库
							obj.authorityUserId = $.cookie("userId");
							obj.id = $("#marquee-id").val();
							obj.title = $("#marquee-title").val();
							obj.type = $("#marquee-type").combobox('getValue');
							obj.contentSummary = $("#marquee-contentSummary").val();
							$.ajax({
								url : SiteUrl.Portals_API
										+ "marquee/update.do",
								type : "post",
								data : obj,
								dataType : "jsonp",
								success : function(data) {
									if (data.error == 1) {
										gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
										list.datagrid("reload");
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
						text : '取消',//取消按钮的事件
						handler : function() {
							gridDialog.dialog("close");
						}
					} ]
			});
			gridDialog.dialog("open");
		}
	}, toolbar);
	
	UI.bindEvents({
		"see-button":function(){
			var rows = publishlist.datagrid("getChecked");
			if (rows == null||rows.length<1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else if(rows.length>1){
				$.messager.alert("提示", "您选择的内容过多，请选择一条记录！", "info");
				return;
			}
			
			seelist.datagrid({
				loader : function(param, success, error) {
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					param.ids = rows[0].solution;
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url : SiteUrl.Portals_API + "marquee/publish.do",
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
				fit : true,
				dragSelection:true,
				fitColumns : true,
				pagination : false,
				loadMsg:'正在加载跑马灯发布列表…',
				rownumbers:true,
				singleSelect:true,
				striped : true, 
				toolbar:$("#bar"),
				columns : [ [//表格绑定字段
				 {
					field : "title",
					title : "标题",
					width : 40
				}, {
					field : "type",
					title : "类型",
					width : 40
				}]]
			});
			$("#ms").text("描述:"+rows[0].description);
			var  status= "";
			switch (rows[0].status) {
			case 0:
				status = "待审批";
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
			$("#zz").text("状态:"+status);
			seeDialog.dialog("open");
		},

		"check-button":function(){
			var rows = publishlist.datagrid("getChecked");
			if (rows == null||rows.length<1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else if(rows.length>1){
				$.messager.alert("提示", "您选择的内容过多，请选择一条记录！", "info");
				return;
			}
			$("#m-form").form("load", rows[0]);
			seeDialog.dialog({
				title : "跑马灯发布审核",
				buttons : [
						{
							text : '取消',//取消按钮的事件
							handler : function() {
								gridDialog.dialog("close");
							}
						}, {
							text : '退回',//取消按钮的事件
							handler : function() {
								var obj = new Object();	
								obj.id = $("#m-id").val();
								obj.status = 1;
								//定义一个 marquee对象，更新到数据库
								obj.approveUserId = $.cookie("userId");
								obj.authorityUserId =  $.cookie("userId");
								$.ajax({
									url : SiteUrl.Portals_API
											+ "publish/check.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											seeDialog.dialog("close");//如果成功关闭窗口，刷新页面
											publishlist.datagrid("reload");
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
						},{
							text : '通过',//保存按钮的事件 
							handler : function() {
								var obj = new Object();	
								obj.id = $("#m-id").val();
								obj.status = 3;
								//定义一个 marquee对象，更新到数据库
								obj.approveUserId = $.cookie("userId");
								obj.authorityUserId =  $.cookie("userId");
								$.ajax({
									url : SiteUrl.Portals_API
											+ "publish/check.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											seeDialog.dialog("close");//如果成功关闭窗口，刷新页面
											publishlist.datagrid("reload");
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
						}]
			});
			seelist.datagrid({
				loader : function(param, success, error) {
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					param.ids = rows[0].solution;
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url : SiteUrl.Portals_API + "marquee/publish.do",
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
				fit : true,
				dragSelection:true,
				fitColumns : true,
				pagination : false,
				loadMsg:'正在加载跑马灯发布列表…',
				rownumbers:true,
				singleSelect:true,
				striped : true, 
				toolbar:$("#bar"),
				columns : [ [//表格绑定字段
				 {
					field : "title",
					title : "标题",
					width : 40
				}, {
					field : "type",
					title : "类型",
					width : 40
				}]]
			});
		seeDialog.dialog("open");
			$("#ms").text("描述:"+rows[0].description);
			var  status= "";
			switch (rows[0].status) {
			case 0:
				status = "待审批";
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
			$("#zz").text("状态:"+status);
			seeDialog.dialog("open");
		},
		"remove-button":function(){
			var rows = publishlist.datagrid("getChecked");
			if (rows == null||rows.length<1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}else{
				$.messager.confirm("操作提示", "确定要删除吗？", function(result) {
					var param = new Object();
					var ids = "";
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					if(result){
						for(var i in rows){
							ids = ids + rows[i].id;
							if(i!=rows.length-1)
								ids = ids + ",";
						}
						param.ids = ids;
						$.ajax({
							url : SiteUrl.System_API + "publish/delete.do",
							type : "post",
							data : param,
							dataType : "jsonp",
							success : function(data) {
								if (data.error == 1) {
									publishlist.datagrid("reload");
								} else if (data.error == -100) {
									$.messager.alert("提示", "会话超时，请重新登陆！", "error");
								} else {
									$.messager.alert("提示", data.message, "error");
								}
							}
						});
					}
				});
			}
		}
	}, publishtoolbar);
	
	$('#marquee-tab').tabs({
		border:false,
		onSelect:function(title){
			if(title=="跑马灯管理"){
				list.datagrid("reload");
			}else{
				publishlist.datagrid("reload");
			}
		}
	});
});
