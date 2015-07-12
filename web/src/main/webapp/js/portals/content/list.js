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
				$('#content-type').combobox({
					data:data.rows,
					valueField:'flexvalue',
					textField:'valueDescription'
				});
			} 
		}
	});
	
	// 设置分页模式
	var list = $("#content-mainGrid");// 获取当前grid名称
	var toolbar = $("#content-gridToolbar");
	var gridDialog = $("#content-create-gridDialog");
	var form = $("#content-form");
	var file = "";
	// 正文富编辑器
	var conContent = null;

	list.datagrid({
				loader : function(param, success, error) {
					param.authorityUserId = $.cookie("userId");// 参数中带上authorityUserId
					$.ajax({// 调用ajax获取数据，使用jsonp方式，跨域
						url : SiteUrl.Portals_API + "content/page.do",
						type : "post",
						data : param,
						dataType : "jsonp",
						success : function(data) {
							if (data.error == 1) {
								success(data);// 加载数据
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
				loadMsg : '正在加载内容列表…',
				toolbar : '#content-gridToolbar',// 绑定工具栏
				rownumbers : false,
				striped : true,
				columns : [ [// 表格绑定字段
						{
							field : "id",
							title : "",
							checkbox : true
						},
						{
							field : "title",
							title : "标题",
							width : 100
						},
						{
							field : "type",
							title : "内容类型",
							width : 30
						},
						{
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
						},
						{
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
							field : "cc",
							title : "操作",
							width : 30,
							formatter : function(value, row, index) {
								
								return "<a style='text-decoration:none;color:blue;cursor:pointer' href='"
										+SiteUrl.Portals_Web+"html/portals/content/preview.html?type=content&id="+row.id
										+"' target='_blank'>预览</a>";
							}
						} ] ]
			});
	var pager = list.datagrid('getPager');
	pager.pagination({
		beforePageText : '第',
		afterPageText : '页 共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录  共{total}条记录',
		layout : [ 'list', 'sep', 'first', 'prev', 'links', 'next', 'last',
				'sep', 'refresh' ]
	});

	UI.bindEvents({
		"add-button" : function() {// 工具栏新增按钮
			form.form("clear");// 表单清空
			gridDialog.dialog({
				title : "新增内容",
				buttons : [
						{
							text : '新增',
							handler : function() {
								if (!form.form("validate")) {// 控件验证
									return;
								}
								// 定义一个 content对象，赋值，调用ajax新增数据
								var obj = new Object();
								obj.authorityUserId = $.cookie("userId");
								obj.type = $("#content-type").combobox(
										'getValue');
								obj.content = conContent.html();
								obj.isAll = $("#content-isAll").combobox(
										'getValue');
								obj.title = $("#content-title").val();
								obj.file = file;
								$.ajax({
									url : SiteUrl.Portals_API
											+ "content/insert.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											gridDialog.dialog("close");// 如果成功关闭窗口，刷新页面
											list.datagrid("reload");// 刷新页面
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
							text : '取消',
							handler : function() {
								gridDialog.dialog("close");
							}
						} ],
						onClose : removeFile
			});
			gridDialog.dialog("open");
			conContent = UI.initKindEditor(conContent, "content-con", "");
		},
		"submit-button" : function() {
			// var row = list.datagrid("getSelected");//获取当前选中的content对象
			var rows = list.datagrid("getChecked");
			if (rows == null || rows.length < 1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			} 
			else {
				var isPass = true;
				for(var i in rows){
					if(rows[i].status==1||rows[i].status==2){
						isPass = false;
					}
				}
				if(!isPass){
					$.messager.alert("提示", "对不起选中的内容 有部分已提交过，请仔细确认！", "info");
					return;
				}
				$.messager.confirm('提示', '你确定提交这几条内容？', function(r) {
					var param = new Object();
					var ids = "";
					param.authorityUserId = $.cookie("userId");// 参数中带上authorityUserId
					if (r) {
						for ( var i in rows) {
							ids = ids + rows[i].id;
							if (i != rows.length - 1)
								ids = ids + ",";
						}
						param.ids = ids;
						$.ajax({
							url : SiteUrl.System_API + "content/submit.do",
							type : "post",
							data : param,
							dataType : "jsonp",
							success : function(data) {
								if (data.error == 1) {
									list.datagrid("reload");
								} else if (data.error == -100) {
									$.messager.alert("提示", "会话超时，请重新登陆！",
											"error");
								} else {
									$.messager.alert("提示", data.message,
											"error");
								}
							}
						});
					}
				});
			}
		},
		"remove-button" : function() {
			var rows = list.datagrid("getChecked");
			if (rows == null || rows.length < 1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			} else {
				
				var isPass = true;
				for(var i in rows){
					if(rows[i].status==1||rows[i].status==2){
						isPass = false;
					}
				}
				if(!isPass){
					$.messager.alert("提示", "对不起选中的内容 有部分已提交过不能删除，请仔细确认！", "info");
					return;
				}
				$.messager.confirm("操作提示", "确定要删除吗？", function(result) {
					var param = new Object();
					var ids = "";
					param.authorityUserId = $.cookie("userId");// 参数中带上authorityUserId
					if (result) {
						for ( var i in rows) {
							ids = ids + rows[i].id;
							if (i != rows.length - 1)
								ids = ids + ",";
						}
						param.ids = ids;
						$.ajax({
							url : SiteUrl.System_API + "content/delete.do",
							type : "post",
							data : param,
							dataType : "jsonp",
							success : function(data) {
								if (data.error == 1) {
									list.datagrid("reload");
								} else if (data.error == -100) {
									$.messager.alert("提示", "会话超时，请重新登陆！",
											"error");
								} else {
									$.messager.alert("提示", data.message,
											"error");
								}
							}
						});
					}
				});
			}
		},
		"update-button" : function() {
			var rows = list.datagrid("getChecked");
			if (rows == null || rows.length < 1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			} else if (rows.length > 1) {
				$.messager.alert("提示", "您选择的内容过多，请选择一条记录！", "info");
				return;
			} else if(rows[0].status==2||rows[0].status==3){
				$.messager.alert("提示", "对不起，该内容已提交！", "info");
				return;
			}
			form.form("load", rows[0]);// 初始化界面上的控件值，控件的name属性必须与选中行的field对应
			gridDialog.dialog({
				title : "编辑数据",
				buttons : [
						{
							text : '保存',// 保存按钮的事件
							handler : function() {
								if (!form.form("validate")) {// 控件验证
									return;
								}
								var obj = new Object();
								// 定义一个 content对象，更新到数据库
								obj.authorityUserId = $.cookie("userId");
								obj.id = $("#content-id").val();
								obj.title = $("#content-title").val();
								obj.type = $("#content-type").combobox(
										'getValue');
								obj.content = conContent.html();
								obj.isAll = $("#content-isAll").combobox(
										'getValue');
								$.ajax({
									url : SiteUrl.Portals_API
											+ "content/update.do",
									type : "post",
									data : obj,
									dataType : "jsonp",
									success : function(data) {
										if (data.error == 1) {
											gridDialog.dialog("close");// 如果成功关闭窗口，刷新页面
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
							text : '取消',// 取消按钮的事件
							handler : function() {
								gridDialog.dialog("close");
							}
						} ],
				onClose :removeFile
			});
			gridDialog.dialog("open");
			conContent = UI.initKindEditor(conContent, "content-con", rows[0].content);
		},
		"marquee-button" : function() {
			var rows = list.datagrid("getChecked");
			if (rows == null || rows.length < 1) {
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			} else {
				$.messager.confirm('提示', '你确定推送到跑马灯？', function(r) {
					var param = new Object();
					var ids = "";
					param.authorityUserId = $.cookie("userId");// 参数中带上authorityUserId
					if (r) {
						for ( var i in rows) {
							ids = ids + rows[i].id;
							if (i != rows.length - 1)
								ids = ids + ",";
						}
						param.ids = ids;
						$.ajax({
							url : SiteUrl.System_API + "marquee/push.do",
							type : "post",
							data : param,
							dataType : "jsonp",
							success : function(data) {
								if (data.error == 1) {
									$.messager
											.alert("提示", "已成功推送到跑马灯！", "info");
									list.datagrid("reload");
								} else if (data.error == -100) {
									$.messager.alert("提示", "会话超时，请重新登陆！",
											"error");
								} else {
									$.messager.alert("提示", data.message,
											"error");
								}
							}
						});
					}
				});
			}
		},
		"search-button" : function(e) {// 查询按钮执行
			e.preventDefault();
			list.datagrid("load", {// 刷新一下页面，并将查询条件作为参数传递给后台
				title : $("#content-title").val()
			});
		}
	}, toolbar);

	// 上传附件
	var onUploadSuccessFn = function(file, data, response) {
		var obj = JSON.parse(data);
		var url = obj.rows + "";
		var index = url.lastIndexOf("\/");
		file = url.substr(index + 1);
		$("#content-filelist").html(
				"<p><span>" + url.substr(index + 1)
						+ "</span><a href='#' onclick='removeFile()'>删除</a></p>");
	};
	var removeFile = function(){
		file = "";
		$("#content-filelist").html("");
	};
	var swf4knowledge = "../js/lib/uploadify-v3.1/uploadify.swf";
	var uploader4knowledge = SiteUrl.System_API + "file/fileupload.do";
	$("#upFileName").uploadify({
		'swf' : swf4knowledge,
		'uploader' : uploader4knowledge,
		'buttonText' : "上传",
		'queueId' : "fileQueue",
		'queueSizeLimit' : 9,// 限制上传文件的数量
		'fileTypeExts' : "*.*",
		'auto' : true,
		'multi' : true,// 是否允许多文件上传
		'simUploadLimit' : 2,// 同时运行上传的进程数量
		'fileObjName' : 'upFileName',
		'method' : "POST",
		'formData' : {
			'authorityUserId' : $.cookie("userId")
		},
		'onUploadStart' : function() {
			$("#upFileName").uploadify("settings", "formData", {
				'authorityUserId' : $.cookie("userId")
			});
		},
		'onUploadSuccess' : onUploadSuccessFn
	});
});
