$(function(){
	var list = $("#function-mainGrid");
	var toolbar = $("#function-gridToolbar");
	var gridDialog=$("#function-create-gridDialog");
	var form = $("#function-form");
	//加载下拉框
	ControllerUtil.loadComboBox($("#function-type"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"functionType"});
	ControllerUtil.loadComboBox($("#function-systemId"), SiteUrl.System_API+"system/getSystemAuthorityListByOrgnizationId.do",{orgnizationId:$.cookie("orgnizationId")});
	//下拉框只读
	$(".easyui-combobox").combobox({
		editable:false
	});
	list.datagrid({
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");
			$.ajax({
				url:SiteUrl.System_API+"function/page.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						//加载数据
						success(data);
					}else if(data.error==-100){
						$.messager.alert("提示","会话超时，请重新登陆！", "error");
					}else{
						$.messager.alert("提示",data.message, "error");
					}
				}
			});
		},
		onDblClickRow:UI.trigger("edit-button",toolbar),
		singleSelect:true,
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		toolbar:toolbar,
		columns:[[
		          {field:"functionName",title:"功能名称",width:100},
		          {field:"functionDescription",title:"功能说明",width:200},
		          {field:"type",title:"类型",width:50},
		          {field:"url",title:"url地址",width:150},
		          {field:"module",title:"模块名称",width:100},
		          {field:"fgroup",title:"分组",width:100}
		          ]]
	});
	var pager = list.datagrid('getPager');
	pager.pagination({
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});
	UI.bindEvents({
		"add-button":function(){
			form.form("clear");
			gridDialog.dialog({
				title:"添加功能",
				buttons:[{
					text:"创建",
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.authorityUserId = $.cookie("userId");
						obj.functionName = $("#function-functionName").val();
						obj.functionDescription = $("#function-functionDescription").val();
						obj.type = $("#function-type").combobox("getText");
						obj.url = $("#function-url").val();
						obj.module = $("#function-module").val();
						obj.fgroup = $("#function-fgroup").val();
						obj.systemId = $("#function-systemId").combobox("getValue");
						$.ajax({
							url:SiteUrl.System_API+"function/insert.do",
							type:"post",
							data:obj,
							dataType:"jsonp",
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示",result.message, "info");
									gridDialog.dialog("close");
									list.resultgrid("reload");
								}else if(result.error==-100){
									$.messager.alert("提示","会话超时，请重新登陆！", "error");
								}else{
									$.messager.alert("提示",result.message, "error");
								}
							}
						});
					}
				},{
					text:'取消',
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"edit-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示","请选择一条记录","info");
				return;
			}
			form.form("load",row);
			gridDialog.dialog({
				title:'编辑功能',
				buttons:[{
					text:'保存',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.authorityUserId = $.cookie("userId");
						obj.functionName = $("#function-functionName").val();
						obj.functionDescription = $("#function-functionDescription").val();
						obj.url = $("#function-url").val();
						obj.type = $("#function-type").combobox("getText");
						obj.module = $("#function-module").val();
						obj.fgroup = $("#function-fgroup").val();
						obj.id = $("#function-id").val();
						obj.systemId = $("#function-systemId").combobox("getValue");
						$.ajax({
							url:SiteUrl.System_API+"function/update.do",
							type:'post',
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示",result.message, "info");
									gridDialog.dialog("close");
									list.datagrid("reload");
								}else if(data.error==-100){
									$.messager.alert("提示","会话超时，请重新登陆！", "error");
								}else{
									$.messager.alert("提示",result.message, "error");
								}
							}
						});
					}
				},{
					text:'取消',
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"remove-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
			row.authorityUserId = $.cookie("userId");
			$.messager.confirm("操作提示","确认删除？",function(result){
				if(result){
					$.ajax({
						url:SiteUrl.System_API+"function/delete.do",
						type:"post",
						data:row,
						dataType:"jsonp",
						success:function(result){
							if(result.error==1){
								$.messager.alert("提示",result.message, "info");
								list.datagrid("reload");
							}
							else if(result.error==-100){
								$.messager.alert("提示", "会话超时，请重新登陆！", "error");
							}
							else{
								$.messager.alert("提示", result.message, "error");
							}
						}
					}); 
				}
			});
		},
		"search-button":function(e){
			e.preventDefault();
			list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    			functionName:$("#function-name").val()
    		});
		}
	},toolbar);
});