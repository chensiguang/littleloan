$(function(){
	//设置分页模式
	var list=$("#survey-mainGrid");//获取当前grid名称
	list.datagrid({
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"survey/page.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						success(data);//加载数据
					}
					else if(data.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					}
					else{
						$.messager.alert("提示", data.message, "error");
					}
					
				}
			});
		},
		fit:true,
		fitColumns:true,
		singleSelect:true,
		loadMsg:'正在加载问卷列表…',
		onLoadSuccess:function(data){
			var vc = $("#survey-mainGrid").datagrid("getPanel").children("div.datagrid-view");
			if($("#querySurveyEmptyTip").length){
				$("#querySurveyEmptyTip").remove();
			}
			if(data.total <= 0){
				var d= $('<div id="querySurveyEmptyTip" class="datagrid-emtpy"></div>').html("暂时没有相关记录").appendTo(vc);
				d.css({
					position:'absolute',
					left:0,
					top:50,
					width:'100%',
					textAlign:'center',
					color:'#999'
				});
			}
			$("#queryModitySurveyBtn").linkbutton("disable");
			$("#queryDeleteSurveyBtn").linkbutton("disable");
			$("#queryCheckedSurveyBtn").linkbutton("disable");
		},
		toolbar:[
		{
			id		: 'queryAddSurveyBtn',
			text	: '新增',
			iconCls : 'icon-add',
			handler : addSurvey
		},{
			id		: 'queryModitySurveyBtn',
			text	: '修改',
			iconCls : 'icon-edit',
			handler : function(){
				var row = $("#survey-mainGrid").datagrid("getSelected");
				queryModifySurvey(row.id, row.name,row.description, "survey-mainGrid");
			}
		},{
			id		: 'queryDeleteSurveyBtn',
			text	: '删除',
			disabled : true,
			iconCls : 'icon-remove',
			handler : function(){
				var row = $("#survey-mainGrid").datagrid("getSelected");
				deleteSurvey(row.id, row.name, "survey-mainGrid");
			}
		},{
			id		: 'queryCheckedSurveyBtn',
			text	: '提交审核',
			disabled : true,
			iconCls : 'icon-edit',
			handler : submitCheckSurvey
		}],
		rownumbers:false,
		striped : true,
		pagination:true,
		columns:[[//表格绑定字段
			{field:"id", title:"", checkbox:true},
			{field:"name",title:"问卷名称", align:"center",width:80, styler:function(v, row, index){
				return "text-align:center";
			}},
			{field:"description",title:"描述", align:"center", styler:function(v, row, index){
				return "text-align:center";
			},width:150},
			{field:"isAll",title:"发布范围", align:"center", styler:function(v, row, index){
				return "text-align:center";
			},width:30,formatter: function (v, r, index){
				return "全员";
			}},
			{field:"status",title:"状态", align:"center",width:30, styler:function(v, row, index){
					return "text-align:center";
				},formatter: function (v, r, index){
					if(v ==  0){
						return "新建";
					}else if(v ==  1){
						return "退回";
					}else if(v ==  2){
						return "待审批";
					}else if(v ==  3){
						return "审批通过";
					}
			}},
			{field:"ck",title:"操作", align:"center",width:30,formatter: function (v, row, index){
				return "<a href='javascript:void(0)' style='color:#0066FF;' onclick=javascript:detailSurvey(\'"+ row.id+"\',\'"+row.name+"\',\'"+row.description+"\') >查看</a>";
			}}
		]],
		onSelect : function(index, row){
			if(3 == row.status || 2 == row.status){
				$("#queryModitySurveyBtn").linkbutton("disable");
				$("#queryDeleteSurveyBtn").linkbutton("disable");
				$("#queryCheckedSurveyBtn").linkbutton("disable");
			}else{
				$("#queryModitySurveyBtn").linkbutton("enable");
				$("#queryDeleteSurveyBtn").linkbutton("enable");
				$("#queryCheckedSurveyBtn").linkbutton("enable");
			}
		},
		onUnselect :function(index, row){
			$("#queryModitySurveyBtn").linkbutton("disable");
			$("#queryDeleteSurveyBtn").linkbutton("disable");
			$("#queryCheckedSurveyBtn").linkbutton("disable");
		}
	});
	var pager=list.datagrid('getPager');
	pager.pagination({
		beforePageText:'第',
		pageSize:10,
		afterPageText:'页 共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录  共{total}条记录',
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});
	
	//问卷详情窗口
	$("#survey-queryDetailSurvey-window").window({
		title:"问卷详情",
		closed:true,
		modal:true,
		top:120,
		cache:false,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		shadow:false
	});
	//问卷详情文本框
	$("#survey-query-detail-name").textbox({
		editable:false,
		readonly:true
	});
	$("#survey-query-detail-area").textbox({
		editable:false,
		readonly:true
	});
	$("#survey-query-detail-description").textbox({
		editable:false,
		multiline:true,
		height:62,
		readonly:true
	});
	$("#survey-question-query-cancelSurveyBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	$("#survey-question-query-cancelSurveyBtn").bind("click",function(){
		$("#survey-queryDetailSurvey-window").window("close");
	});
	
	//新增问卷窗口
	$("#survey-addSurvey-window").window({
		closed:true,
		modal:true,
		top:120,
		cache:false,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		shadow:false
	});
	
	//问卷名称
	$("#survey-name").textbox({
		width:312,
		required:true,
		missingMessage:"问卷名称不能为空",
		height:22
	});
	//问卷描述
	$("#survey-description").textbox({
		multiline:true,
		width:312,
		required:true,
		missingMessage:"问卷描述不能为空",
		height:82
	});
	//发布范围
	$("#survey-area").combobox({
		width:312,
		editable:false
	});
	
	//操作按钮
	$("#survey-addSurveyBtn").linkbutton({
		iconCls	: 'icon-save'
	});
	$("#survey-cancelSurveyBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	//关闭按钮事件控制
	$("#survey-cancelSurveyBtn").bind("click",function(){
		$("#survey-addSurveyBtn").unbind("click");
		$("#survey-addSurvey-window").window("close");
	});
});

//问卷详情
function detailSurvey (id,name,desc){
	$("#survey-query-detail-name").textbox("setValue",name);
	$("#survey-query-detail-area").textbox("setValue","全员");
	$("#survey-query-detail-description").textbox("setValue",desc);
	$("#survey-queryDetailSurvey-window").show();
	$("#survey-question-query-wrap").empty();
	findSurveyQuestion(id, "survey-question-query-wrap");
	$("#survey-queryDetailSurvey-window").window("open");
}
//提交审核
function submitCheckSurvey(){
	var row = $("#survey-mainGrid").datagrid("getSelected");
	if(null == row){
		$.messager.alert("提示", "对不起，请选择要审核的问卷", "error");
		return;
	}else if(row.status == 2){
		$.messager.alert("提示", "对不起，该问卷已经提交审核", "error");
		return;
	}else{
		updateSurvey(row.id,"2",function(data){
			$("#survey-mainGrid").datagrid("reload");//刷新页面
			$.messager.alert("提示", "恭喜您，问卷审核提交成功", "info");
		},function(data){
			$.messager.alert("提示", data.message, "error");
		});
	}
}
//添加问卷
function addSurvey(){
	$("#survey-addSurvey-window").show();
	$("#survey-form").form("reset");
	$("#survey-addSurvey-window").window({title: "新增问卷"});
	
	$("#survey-addSurveyBtn").linkbutton("enable");
	$("#survey-cancelSurveyBtn").linkbutton("enable");
	//添加绑定保存事件
	$("#survey-addSurveyBtn").bind("click",function(){
		saveOrUpdateSurvey(0);
	});
	$("#survey-addSurvey-window").window("open");
}

//保存或更新问卷（不为""或0时表示更新）
function saveOrUpdateSurvey(id){
	if("" == $.trim($("#survey-name").val())){
		$("#survey-name").val("");
		$("input", $("#survey-name").next("span")).focus();
		return;
	}
	if(!$("#survey-name").textbox("isValid")){
		$("input", $("#survey-name").next("span")).focus();
		return;
	}
	if("" == $.trim($("#survey-description").val())){
		$("#survey-description").val("");
		$("textarea", $("#survey-description").next("span")).focus();
		return;
	}
	if(!$("#survey-description").textbox("isValid")){
		$("textarea", $("#survey-description").next("span")).focus();
		return;
	}
	$("#survey-addSurveyBtn").linkbutton("disable");
	$("#survey-cancelSurveyBtn").linkbutton("disable");
	$("#survey-addSurveyBtn").unbind("click");
	$("#survey-cancelSurveyBtn").unbind("click");
	var gridList = $("#survey-mainGrid");//获取当前grid名称
	//定义一个 flexkey对象，赋值，调用ajax新增数据
	var obj = new Object();
	obj.authorityUserId = $.cookie("userId");
	obj.name = $.trim($("#survey-name").val());
	if("" != id || 0 != id){
		obj.id = id;
	}
	obj.description = $.trim($("#survey-description").val());
	obj.isall = $("#survey-area").val();
	$.ajax({
		url:SiteUrl.System_API+"survey/insertOrUpdate.do",
		type:"post",
		data:obj,
		dataType:"jsonp",
		success:function(data){
			//按钮事件处理
			$("#survey-addSurveyBtn").linkbutton("enable");
			$("#survey-cancelSurveyBtn").linkbutton("enable");
			$("#survey-cancelSurveyBtn").bind("click",function(){
				$("#survey-addSurveyBtn").unbind("click");
				$("#survey-addSurvey-window").window("close");
			});
			if(data.error==1){
				gridList.datagrid("reload");//刷新页面
				$("#survey-addSurvey-window").window("close");
				if("" != id || 0 != id){
					$.messager.alert("提示", "恭喜您，问卷更新成功", "info");
				}else{
					$.messager.alert("提示", "恭喜您，问卷添加成功", "info");
				}
			} else if(data.error==-100){
				$.messager.alert("提示", "会话超时，请重新登陆！", "error");
			} else{
				$.messager.alert("提示", data.message, "error");
			}
		}
	});
}

//修改问卷
function queryModifySurvey(id, name,description, gridId){
	$("#survey-addSurvey-window").show();
	
	$("#survey-addSurvey-window").window({title: "修改问卷"});
	$("#survey-name").textbox("setValue",name);
	$("#survey-description").textbox("setValue",description);
	$("#survey-addSurveyBtn").linkbutton("enable");
	$("#survey-cancelSurveyBtn").linkbutton("enable");
	//更新绑定保存事件
	$("#survey-addSurveyBtn").bind("click",function(){
		saveOrUpdateSurvey(id);
	});
	$("#survey-addSurvey-window").window("open");
}
