$(function(){
	$("#survey-select-for-question").combobox({
		valueField:"id",
		textField:"name",
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"survey/queryList.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						success(data.rows);//加载数据
						if(data.rows && data.rows.length > 0){
							var first = data.rows[0];
							$("#survey-select-for-question").combobox("select",first.id);
						}else{
							
						}
					}else if(data.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					}
					else{
						$.messager.alert("提示", data.message, "error");
					}
					
				}
			});
		},
		onSelect:function(record){
			$("#survey-questionMainGrid").treegrid("reload");//刷新页面
		}
	});
	
	$("#survey-questionMainGrid").treegrid({
		idField:'id',
		treeField:'content',
		fit:true,
		method:"post",
		fitColumns:true,
		animate:true,
		singleSelect:true,
		loadMsg:'正在加载选项列表…',
		loader:function(param,success,error){
			setTimeout(function(){
				var surveyId = $("#survey-select-for-question").combobox("getValue");
				
				param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
				param.surveyid = surveyId;
				$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
					url:SiteUrl.System_API+"surveyquestion/queryQuestionList.do",
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
			}, 300);
		},
		onLoadSuccess:function(){
			var data =  $("#survey-questionMainGrid").treegrid("getData");
			var vc = $("#survey-questionMainGrid").treegrid("getPanel").children("div.datagrid-view");
			if($("#querySurveyQuestionEmptyTip").length > 0){
				$("#querySurveyQuestionEmptyTip").remove();
			}
			if(data.length <= 0){
				var d= $('<div id="querySurveyQuestionEmptyTip" class="datagrid-emtpy"></div>').html("暂时没有相关记录").appendTo(vc);
				d.css({
					position:'absolute',
					left:0,
					top:50,
					width:'100%',
					textAlign:'center',
					color:'#999'
				});
			}
			$("#survey-questionMainGrid").treegrid("unselectAll");
			$("#modityQuestionBtn").linkbutton("disable");
			$("#deletQuestioneBtn").linkbutton("disable");
		},
		toolbar : "#survey-question-gridToolbar",
		striped : true,
		pagination:true,
		columns:[[//表格绑定字段
			{field:"id", title:"", checkbox:true},
			{field:"content",title:"问题描述", width:120, align:"center", styler:function(v, row, index){
				return "text-align:left";
			}},
			{field:"type",title:"问题类型", align:"center",width:30,formatter: function (v, row, index){
				if(1 == v){
					return "单选";
				}else if(2 == v){
					return "多选";
				}else if(3 == v){
					return "问答";
				}else{
					return "p";
				}
			}},
			{field:"surveyName",title:"所属问卷", align:"center", width:120,formatter: function (v, row, index){
				if(null != row.type && typeof(row.type) != "undefined"){
					return v+"&nbsp;<a href='javascript:void(0)' onclick=javascript:detailSurveyForQuestion(\'"+ row.surveyId+"\',\'"+row.surveyName+"\',\'"+row.description+"\')>预览</a>";
				}else{
					return "";
				}
			}},
			{field:"status",title:"问卷状态", align:"center" , width:30,formatter: function (v, row, index){
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
			{field:"surveyId", align:"center", title:"操作",width:60,formatter: function (v, row, index){
				if(null != row.type && typeof(row.type) != "undefined"){
					if(typeof(row.upId) != "undefined" && typeof(row.nextId) == "undefined"){
						return "<a href='javascript:void(0)' style='color:#0066FF;'  onclick=javascript:updateQuestionSortNo(\'"+row.id +"',\'"+row.upId+"\') >上移</a>" + "&nbsp;&nbsp;&nbsp;" +"<a href='javascript:void(0)' onclick=javascript:detailQuestion(\'"+ row.id+"\',\'"+row.content+"\') >查看</a>";
					}else if(typeof(row.upId) == "undefined" && typeof(row.nextId) != "undefined"){
						return "<a href='javascript:void(0)' style='color:#0066FF;'  onclick=javascript:updateQuestionSortNo(\'"+row.id +"',\'"+row.nextId+"\') >下移</a>" + "&nbsp;&nbsp;&nbsp;" +"<a href='javascript:void(0)' onclick=javascript:detailQuestion(\'"+ row.id+"\',\'"+row.content+"\') >查看</a>";
					}else if (typeof(row.upId) != "undefined" && typeof(row.nextId) != "undefined"){
						return "<a href='javascript:void(0)' style='color:#0066FF;'  onclick=javascript:updateQuestionSortNo(\'"+row.id +"',\'"+row.upId+"\') >上移</a>" +
								"&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' style='color:#0066FF;'  onclick=javascript:detailQuestion(\'"+ row.id+"\',\'"+row.content+"\') >查看</a>" +
								"&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' style='color:#0066FF;'  onclick=javascript:updateQuestionSortNo(\'"+row.id +"',\'"+row.nextId+"\') >下移</a>";
					}else{
						return "";
					}
				}else{
					return "";
				}
			}}
		]],
		onSelect : function(index, row){
			var row = $("#survey-questionMainGrid").treegrid("getSelected");
			if(typeof(row.status) == "undefined" || 2 == row.status || 3 == row.status ){
				$("#addQuestionBtn").linkbutton("disable");
				$("#modityQuestionBtn").linkbutton("disable");
				$("#deletQuestioneBtn").linkbutton("disable");
				
				$("#addQuestionBtn").unbind("click");
				$("#modityQuestionBtn").unbind("click");
				$("#deletQuestioneBtn").unbind("click");
			}else{
				$("#addQuestionBtn").linkbutton("enable");
				$("#deletQuestioneBtn").linkbutton("enable");
				$("#modityQuestionBtn").linkbutton("enable");
				$("#addQuestionBtn").bind("click",function(){
					addSurveyQuestion();
				});
				$("#modityQuestionBtn").bind("click",function(){
					modifySurveyQuestion();
				});;
				$("#deletQuestioneBtn").bind("click",function(){
					var row = $("#survey-questionMainGrid").treegrid("getSelected");
					deleteSurveyQuestion(row.id, row.content,row.type, "survey-questionMainGrid");
				});
			}
		},
		onUnselect :function(index, row){
			$("#addQuestionBtn").linkbutton("disable");
			$("#modityQuestionBtn").linkbutton("disable");
			$("#deletQuestioneBtn").linkbutton("disable");
		}
	});
	$("#survey-questionMainGrid").treegrid('getPager').pagination({
		beforePageText:'第',
		pageSize:10,
		afterPageText:'页 共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录  共{total}条记录',
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});
	
	//事件绑定
	UI.bindEvents({
		"add-button" : addSurveyQuestion,
		"edit-button":modifySurveyQuestion,
		"remove-button":function(){
			var row = $("#survey-questionMainGrid").treegrid("getSelected");
			deleteSurveyQuestion(row.id, row.content,row.type, "survey-questionMainGrid");
		}
	},$("#survey-question-gridToolbar"));
	
	
	//问题添加或修改窗口
	$("#survey-addSurveyQuestion-window").window({
		closed:true,
		modal:true,
		top:120,
		cache:false,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		shadow:false
	});
	//操作按钮
	$("#survey-addQuestionBtn").linkbutton({
		iconCls	: 'icon-save'
	});
	$("#survey-cancelQuestionBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	$("#survey-cancelQuestionBtn").bind("click", function(){
		closeQuestionWindow();
	});
	$("#survey-question-add-name").textbox({
		type : "text",
		width:312,
		required:true,
		missingMessage:"问题名称不能为空",
		height:22
	});
	$("#survey-question-add-type-wrap").children("input[name='type']").click(function(){
		if(3 == $(this).val()){
			$("#survey-question-options-wrap").hide();
		}else{
			$("#survey-question-options-wrap").show();
		}
	});
	$("#survey-question-add-option-A").textbox({
		editable:true,
		multiline:true,
		width:312,
		height:72
	});
	$("#survey-question-add-option-B").textbox({
		editable:true,
		multiline:true,
		width:312,
		height:72
	});
	$("#survey-question-add-option-C").textbox({
		editable:true,
		multiline:true,
		width:312,
		height:72
	});
	$("#survey-question-add-option-D").textbox({
		editable:true,
		multiline:true,
		width:312,
		height:72
	});
	
	
	
	//问卷详情窗口
	$("#survey-questionDetailSurvey-window").window({
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
	$("#survey-question-cancelSurveyBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	//详情关闭按钮
	$("#survey-question-detail-cancelBtn").linkbutton({
		iconCls	: 'icon-cancel'
	});
	$("#survey-question-cancelSurveyBtn").bind("click",function(){
		$("#survey-questionDetailSurvey-window").window("close");
	});
	
	$("#survey-question-detail-cancelBtn").bind("click",function(){
		$("#survey-questionDetailSurvey-window").window("close");
	});
});

//添加问题窗口
function addSurveyQuestion (){
	$("#survey-addSurveyQuestion-window").show();
	$("#survey-question-options-wrap").show();
	$("#survey-question-form").form("reset");
	$("#survey-question-add-id").val("");
	$("#survey-addSurveyQuestion-window").window({title: "新增问题"});
	$("#survey-addQuestionBtn").linkbutton("enable");
	$("#survey-cancelQuestionBtn").linkbutton("enable");
	$("#survey-question-add-surveyid").val($("#survey-select-for-question").combobox("getValue"));
	$("#survey-question-add-survey-name").html($("#survey-select-for-question").combobox("getText"));
	//添加绑定保存事件
	$("#survey-addQuestionBtn").bind("click",function(){
		saveOrUpdateQuestion(0);
	});
	$("#survey-addSurveyQuestion-window").window("open");
}

//修改问题弹出窗口
function modifySurveyQuestion(){
	$("#survey-question-add-id").val("");
	$("#survey-question-form").form("reset");
	var row = $("#survey-questionMainGrid").treegrid("getSelected");
	var id = row.id;
	$("#survey-question-add-id").val(id);
	$("#survey-question-add-surveyid").val(row.surveyid);
	$("#survey-question-add-name").textbox("setValue", row.content);
	$("#survey-question-add-survey-name").html($("#survey-select-for-question").combobox("getText"));
	$("#survey-question-add-type-wrap").children("input:radio[name='type']:checked").removeAttr("checked");
	$("#survey-question-add-type-wrap").children("input[name='type'][value="+row.type+"]")[0].checked = true;
	if(row.type == 3){
		$("#survey-question-options-wrap").hide();
	}else{
		if(row.children.length && row.children.length >=1){
			$("#survey-question-add-option-A").textbox("setValue",row.children[0].content);
		}
		if(row.children.length && row.children.length >=2){
			$("#survey-question-add-option-B").textbox("setValue",row.children[1].content);
		}
		if(row.children.length && row.children.length >=3){
			$("#survey-question-add-option-C").textbox("setValue",row.children[2].content);
		}
		if(row.children.length && row.children.length >=4){
			$("#survey-question-add-option-D").textbox("setValue",row.children[3].content);
		}
		$("#survey-question-options-wrap").show();
	}
	$("#survey-addSurveyQuestion-window").show();
	$("#survey-addSurveyQuestion-window").window({title: "修改问题"});
	$("#survey-addQuestionBtn").linkbutton("enable");
	$("#survey-cancelQuestionBtn").linkbutton("enable");
	//$("#survey-question-add-surveyid").val($("#survey-select-for-question").combobox("getValue"));
	
	//更新绑定保存事件
	$("#survey-addQuestionBtn").bind("click",function(){
		saveOrUpdateQuestion(id);
	});
	$("#survey-addSurveyQuestion-window").window("open");
}

//保存或更新问卷（不为""或0时表示更新）
function saveOrUpdateQuestion(id){
	if("" == $.trim($("#survey-question-add-name").val())){
		$("#survey-question-add-name").val("");
		$("input", $("#survey-question-add-name").next("span")).focus();
		return;
	}
	if(!$("#survey-question-add-name").textbox("isValid")){
		$("input", $("#survey-question-add-name").next("span")).focus();
		return;
	}
	if(3 != $("#survey-question-add-type-wrap").children("input:radio[name='type']:checked").val()){
		if("" == $.trim($("#survey-question-add-option-A").val())){
			$("#survey-question-add-option-A").val("");
			$("textarea", $("#survey-question-add-option-A").next("span")).focus();
			return;
		}
		if("" == $.trim($("#survey-question-add-option-B").val())){
			$("#survey-question-add-option-B").val("");
			$("textarea", $("#survey-question-add-option-B").next("span")).focus();
			return;
		}
		if("" == $.trim($("#survey-question-add-option-C").val())){
			$("#survey-question-add-option-C").val("");
			$("textarea", $("#survey-question-add-option-C").next("span")).focus();
			return;
		}
		if("" == $.trim($("#survey-question-add-option-D").val())){
			$("#survey-question-add-option-D").val("");
			$("textarea", $("#survey-question-add-option-D").next("span")).focus();
			return;
		}
	}
	
	$("#survey-addQuestionBtn").unbind("click");
	$("#survey-cancelQuestionBtn").unbind("click");
	//定义一个 flexkey对象，赋值，调用ajax新增数据
	var obj = new Object();
	obj.authorityUserId = $.cookie("userId");
	var id = $("#survey-question-add-id").val();
	if("" != id && 0 != id){
		obj.id = id;
	}
	obj.content = $.trim($("#survey-question-add-name").textbox("getValue"));
	obj.surveyid = $("#survey-question-add-surveyid").val();
	obj.type = $("#survey-question-add-type-wrap").children("input:radio[name='type']:checked").val();
	obj.option = $.trim($("#survey-question-add-option-A").textbox("getValue")) + "," +
				$.trim($("#survey-question-add-option-B").textbox("getValue")) + "," +
				$.trim($("#survey-question-add-option-C").textbox("getValue")) + "," +
				$.trim($("#survey-question-add-option-D").textbox("getValue"));
	$.ajax({
		url:SiteUrl.System_API+"surveyquestion/insertOrUpdate.do",
		type:"post",
		data:obj,
		dataType:"jsonp",
		success:function(data){
			//按钮事件处理
			$("#survey-addQuestionBtn").linkbutton("enable");
			$("#survey-cancelQuestionBtn").linkbutton("enable");
			$("#survey-cancelQuestionBtn").bind("click",function(){
				$("#survey-addQuestionBtn").unbind("click");
				$("#survey-addSurveyQuestion-window").window("close");
			});
			$("#survey-addSurveyQuestion-window").window("close");
			if(data.error==1){
				$("#survey-questionMainGrid").treegrid("reload");//刷新页面
				if(id && "" != id && 0 != id){
					$.messager.alert("提示", "恭喜您，问题更新成功", "info");
				}else{
					$.messager.alert("提示", "恭喜您，问题添加成功", "info");
				}
			} else if(data.error==-100){
				$.messager.alert("提示", "会话超时，请重新登陆！", "error");
			} else{
				$.messager.alert("提示", data.message, "error");
			}
		}
	});
}

//删除问题或选项
function deleteSurveyQuestion(id, content,type, gridId){
	var tipMsg = "";
	if(null != type && typeof(type) != "undefined"){
		tipMsg = "您确定要删除问题“" + content+"”";
	}else{
		tipMsg = "您确定要删除问题选项“" + content+"”";
	}
	$.messager.confirm("删除提示",tipMsg ,function(data){
		if(data){
			var obj = new Object();
			obj.authorityUserId = $.cookie("userId");
			obj.id = id;
			$.ajax({
				url:SiteUrl.System_API+"surveyquestion/delete.do",
				type:"post",
				data:obj,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						$("#" + gridId).treegrid("reload");//刷新页面
					} else if(data.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					} else{
						$.messager.alert("提示", data.message, "error");
					}
				}
			});
		}else{
			//取消
		}
	});
}
//问卷详情
function detailSurveyForQuestion(id,name,desc){
	$("#survey-question-detail-name").textbox("setValue",name);
	$("#survey-question-detail-area").textbox("setValue","全员");
	$("#survey-question-detail-description").textbox("setValue",desc);
	$("#survey-questionDetailSurvey-window").show();
	$("#survey-question-wrap").empty();
	findSurveyQuestion(id, "survey-question-wrap");
	$("#survey-questionDetailSurvey-window").window("open");
}

//更新问卷排序状态
function updateQuestionSortNo(id, toId){
	var obj = new Object();
	obj.authorityUserId = $.cookie("userId");
	obj.questionId = id;
	obj.toId = toId;
	$.ajax({
		url:SiteUrl.System_API+"surveyquestion/changeSort.do",
		type:"post",
		data:obj,
		dataType:"jsonp",
		success:function(data){
			if(data.error==1){
				$("#survey-questionMainGrid").treegrid("reload");//刷新页面
			} else if(data.error==-100){
				$.messager.alert("提示", "会话超时，请重新登陆！", "error");
			} else{
				$("#survey-questionMainGrid").treegrid("reload");//刷新页面
				$.messager.alert("提示", data.message, "error");
			}
		}
	});
}

//问题详情
function detailQuestion(id,content){
	$("#survey-question-detail-form").form("reset");
	var row = $("#survey-questionMainGrid").treegrid("find", id);
	$("#survey-question-detail-survey-name").html($("#survey-select-for-question").combobox("getText"));
	$("#survey-question-detail-type-wrap").children("input:radio[name='type']:checked").removeAttr("checked");
	$("#survey-question-detail-type-wrap").children("input[name='type'][value="+row.type+"]")[0].checked = true;
	$("#survey-question-detail-content").val(row.content);
	if(row.type == 3){
		$("#survey-question-detail-options-wrap").hide();
	}else{
		if(row.children.length && row.children.length >=1){
			$("#survey-question-detail-option-A").val(row.children[0].content);
		}
		if(row.children.length && row.children.length >=2){
			$("#survey-question-detail-option-B").val(row.children[1].content);
		}
		if(row.children.length && row.children.length >=3){
			$("#survey-question-detail-option-C").val(row.children[2].content);
		}
		if(row.children.length && row.children.length >=4){
			$("#survey-question-detail-option-D").val(row.children[3].content);
		}
		$("#survey-question-detail-options-wrap").show();
	}
	$("#survey-questionDetailSurvey-window").show();
	$("#survey-questionDetailSurvey-window").window("open");
}

//关闭窗口
function closeQuestionWindow(){
	$("#survey-addQuestionBtn").unbind("click");
	$("#survey-addSurveyQuestion-window").window("close");
}