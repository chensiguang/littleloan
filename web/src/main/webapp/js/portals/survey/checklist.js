$(function(){
	//设置分页模式
	var list=$("#survey-mainCheckGrid");//获取当前grid名称
	list.datagrid({
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"survey/checkList.do",
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
		toolbar:[
		{
			id		: 'detailSurveyBtn',
			text	: '查看',
			disabled : true,
			iconCls : 'icon-remove',
			handler : function(){
				var row = $("#survey-mainCheckGrid").datagrid("getSelected");
				detailSurvey(row.id, row.name, row.description);
			}
		},{
			id		: 'checkedSurveyBtn',
			text	: '审核',
			disabled : true,
			iconCls : 'icon-edit',
			handler : function(){
				var row = $("#survey-mainCheckGrid").datagrid("getSelected");
				checkSurvey(row.id, row.name, row.description);
			}
		}],
		rownumbers:true,
		striped : true,
		onLoadSuccess:function(data){
			var vc = $("#survey-mainCheckGrid").datagrid("getPanel").children("div.datagrid-view");
			if($("#checkSurveyEmptyTip").length){
				$("#checkSurveyEmptyTip").remove();
			}
			if(data.total <= 0){
				var d= $('<div id="checkSurveyEmptyTip" class="datagrid-emtpy"></div>').html("暂时没有相关记录").appendTo(vc);
				d.css({
					position:'absolute',
					left:0,
					top:50,
					width:'100%',
					textAlign:'center',
					color:'#999'
				});
			}
			$("#checkedSurveyBtn").linkbutton("disable");
			$("#detailSurveyBtn").linkbutton("disable");
		},
		pagination:true,
		columns:[[//表格绑定字段
			{field:"id", title:"", checkbox:true},
			{field:"name",title:"问卷名称", align:"center", styler:function(v, row, index){
				return "text-align:center";
			},width:80},
			{field:"description",title:"描述", align:"center", styler:function(v, row, index){
				return "text-align:center";
			},width:150},
			{field:"isAll",title:"发布范围", align:"center",width:30, styler:function(v, row, index){
				return "text-align:center";
			},formatter: function (v, r, index){
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
			{field:"ck",title:"操作", align:"center", styler:function(v, row, index){
				return "text-align:center";
			},width:120,formatter: function (v, row, index){
				if(row.status ==  2){
					return "<a href='javascript:void(0)' style='color:#0066FF;' onclick=javascript:checkSurvey(\'"+ row.id+"\',\'"+row.name+"\',\'"+row.description+"\') >审核</a>";
				}else if(row.status ==  3){
					return "<a href='javascript:void(0)' style='color:#0066FF;' onclick=javascript:detailSurvey(\'"+ row.id+"\',\'"+row.name+"\',\'"+row.description+"\') >查看</a>";
				}
			}}
		]],
		onSelect : function(index, row){
			$("#detailSurveyBtn").linkbutton("enable");
			if(3 == row.status){
				$("#checkedSurveyBtn").linkbutton("disable");
			}else{
				$("#checkedSurveyBtn").linkbutton("enable");
			}
		},
		onUnselect :function(index, row){
			$("#checkedSurveyBtn").linkbutton("disable");
			$("#detailSurveyBtn").linkbutton("disable");
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
	
	//新增问卷窗口
	$("#survey-checkDetailSurvey-window").window({
		title:"问卷详情",
		closed:true,
		modal:true,
		cache:false,
		top:120,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		shadow:false
	});
	//问卷详情文本框
	$("#survey-check-detail-name").textbox({
		editable:false,
		readonly:true
	});
	$("#survey-check-detail-area").textbox({
		editable:false,
		readonly:true
	});
	$("#survey-check-detail-description").textbox({
		editable:false,
		multiline:true,
		height:62,
		readonly:true
	});
	//操作按钮初始化
	$("#survey-question-check-backSurveyBtn").linkbutton({
		iconCls	: 'icon-remove'
	});
	$("#survey-question-check-goSurveyBtn").linkbutton({
		iconCls	: 'icon-edit'
	});
	$("#survey-question-check-cancelSurveyBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	$("#survey-question-check-cancelSurveyBtn").bind("click",function(){
		$("#survey-checkDetailSurvey-window").window("close");
	});
});

//问卷详情查看
function detailSurvey(id,name,desc){
	$("#survey-check-detail-name").textbox("setValue",name);
	$("#survey-check-detail-area").textbox("setValue","全员");
	$("#survey-check-detail-description").textbox("setValue",desc);
	$("#survey-checkDetailSurvey-window").show();
	$("#survey-question-check-backSurveyBtn").hide();
	$("#survey-question-check-goSurveyBtn").hide();
	$("#survey-question-check-wrap").empty();
	findSurveyQuestion(id, "survey-question-check-wrap");
	$("#survey-checkDetailSurvey-window").window("open");
}

//审核问卷
function checkSurvey(id,name,desc){
	$("#survey-check-detail-name").textbox("setValue",name);
	$("#survey-check-detail-area").textbox("setValue","全员");
	$("#survey-check-detail-description").textbox("setValue",desc);
	$("#survey-checkDetailSurvey-window").show();
	$("#survey-question-check-backSurveyBtn").show();
	$("#survey-question-check-goSurveyBtn").show();
	$("#survey-question-check-wrap").empty();
	findSurveyQuestion(id, "survey-question-check-wrap");
	$("#survey-checkDetailSurvey-window").window("open");
	//审核按钮操作
	$("#survey-question-check-goSurveyBtn").bind("click",function(){
		updateSurvey(id,3,function(){
			$("#survey-mainCheckGrid").datagrid("reload");//刷新页面
			$("#survey-checkDetailSurvey-window").window("close");
			$("#checkedSurveyBtn").linkbutton("disable");
			$("#detailSurveyBtn").linkbutton("disable");
			$.messager.alert("提示", "恭喜您，问卷审核成功", "info");
		},function(){
			$.messager.alert("提示", data.message, "error");
		});
	});
	//退回按钮操作
	$("#survey-question-check-backSurveyBtn").bind("click",function(){
		backSurvey(id,2);
	});
}

//退回问卷
function backSurvey(id, status){
	updateSurvey(id,status,function(){
		$("#survey-mainCheckGrid").datagrid("reload");//刷新页面
		$("#survey-checkDetailSurvey-window").window("close");
		$.messager.alert("提示", "恭喜您，问卷退回成功", "info");
	},function(){
		$.messager.alert("提示", data.message, "error");
	});
}