$(function(){
	role4orgAuthority = {
		loadData : function(){
			list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    		});
		}
	};
	var listType = "loan";
	var list = $("#role4org-mainGrid");
	var toolbar=$("#role4org-gridToolbar");
	var gridDialog=$("#role4org-create-gridDialog");
	var form = $("#role4org-form");
	
	list.datagrid({
		loader:function(param,success,error){
			if($("#role4org-orgnizationId").val()==""){
				$("#role4org-orgnizationId").val($.cookie("orgnizationId"));
			}
			param.listType = listType;
			param.orgnizationId = $("#role4org-orgnizationId").val();
			param.authorityUserId = $.cookie("userId");
			$.ajax({
				url:SiteUrl.System_API+"role/page.do",
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
		singleSelect:true,
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		toolbar:toolbar,
		onDblClickRow:UI.trigger("edit-button",toolbar),
		columns:[[
		          {field:"roleName",title:"角色名称",width:100},
		          {field:"roleDescription",title:"角色说明",width:200},
		          {field:"isSystemRole",title:"系统角色",width:100,
		        	  formatter:function(value){
		        		  if(value=="1"||value=="2"){
		        			  return "是";
		        		  }else{
		        			  return "否";
		        		  }
		        	  }}
		          ]]
	});
	var pager = list.datagrid('getPager');
	pager.pagination({
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});
	var functionDialog=$("#role4org-function-gridDialog");
	var functionList = $("#role4org-functionGrid");
	UI.bindEvents({
		"add-button":function(){
			form.form("clear");
			gridDialog.dialog({
				title:"添加角色",
				buttons:[{
					text:"创建",
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.orgnizationId = $("#role4org-orgnizationId").val();
						obj.authorityUserId = $.cookie("userId");
						obj.roleName = $("#role4org-roleName").val();
						obj.roleDescription = $("#role4org-roleDescription").val();
						obj.isSystemRole = $("#role4org-isSystemRole").val();
						$.ajax({
							url:SiteUrl.System_API+"role/insert.do",
							type:"post",
							data:obj,
							dataType:"jsonp",
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示",result.message, "info");
									gridDialog.dialog("close");
									list.datagrid("reload");
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
			var buttonsConfig = [];
			buttonsConfig.push({
				text:'取消',
				handler:function(){
					gridDialog.dialog("close");
				}
			});
			//非系统角色添加“保存”按钮
			if(row.isSystemRole==0){
				buttonsConfig.push({
					text:'保存',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.authorityUserId = $.cookie("userId");
						obj.id = $("#role2loan-id").val();
						obj.roleName = $("#role2loan-roleName").val();
						obj.roleDescription = $("#role2loan-roleDescription").val();
						obj.isSystemRole = row.isSystemRole;
						$.ajax({
							url:SiteUrl.System_API+"role/update.do",
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
				});
			}
			gridDialog.dialog({
				title:'编辑角色',
				buttons:buttonsConfig
			});
			gridDialog.dialog("open");
		},
		"function-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
			showRoleFunction();
		},
		"remove-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
			if(row.isSystemRole==1){
				$.messager.alert("提示", "系统角色不能删除！", "info");
				return;
			}
			row.authorityUserId = $.cookie("userId");
			$.messager.confirm("操作提示","确认删除？",function(result){
				if(result){
					$.ajax({
						url:SiteUrl.System_API+"role/delete.do",
						type:"post",
						data:row,
						dataType:"jsonp",
						success:function(data){
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
    			roleName:$("#role4org-name").val()
    		});
		}
	},toolbar);
	var functionToolbar=$("#role4org-functionGridToolbar");
	var allFunctionDialog=$("#role4org-allFunction-gridDialog");
	var allFunctionList=$("#role4org-allFunctionGrid");
	UI.bindEvents({
			"add-function-button":function(){
				allFunctionList.datagrid({
					loader:function(param,success,error){
						var row = list.datagrid("getSelected");
						param.id = row.id;
						param.orgnizationId = $.cookie("orgnizationId");
						param.authorityUserId = $.cookie("userId");
						$.ajax({
							url:SiteUrl.System_API+"role/getFunctionNeeded.do",
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
					singleSelect:false,
					fitColumns:true,
					rownumbers:true,
					pagination:true,
					toolbar:"#role4org-allFunctionToolbar",
					columns:[[
					          {field:"ck",checkbox:true},
					          {field:"functionName",title:"功能名称",width:100},
					          {field:"functionDescription",title:"功能说明",width:200},
					          {field:"type",title:"分类",width:100},
					          {field:"systemName",title:"系统",width:100},
					          {field:"module",title:"模块名称",width:100},
					          {field:"fgroup",title:"分组",width:100}
					          ]]
				});
				var row = list.datagrid("getSelected");
				allFunctionDialog.dialog({
					title:row.roleName+":功能分配>>添加"
				});
				allFunctionDialog.dialog("open");
			},
			"remove-function-button":function(){
				var row = list.datagrid("getSelected");
				if(row==null){
					return;
				}
				$.messager.confirm("操作提示","确认删除？",function(result){
					if(!result){
						return;
					}
					var check = functionList.datagrid("getChecked");
					var roleFunctionIdArr = [];
					$.each(check,function(index,item){
						roleFunctionIdArr.push(item.roleFunctionId);
			    	});
					var roleFunctionIdStr = roleFunctionIdArr.join(",");
					var obj = new Object();
					obj.authorityUserId = $.cookie("userId");
					obj.roleFunctionIds = roleFunctionIdStr;
					obj.id = row.id;
					$.ajax({
						url:SiteUrl.System_API+"roleFunction/delete.do",
						data:obj,
						dataType:'jsonp',
						type:'post',
						success:function(result){
							if(result.error==1){
								$.messager.alert("提示",result.message, "info");
								allFunctionDialog.dialog("close");
								functionList.datagrid("reload");
							}else if(result.error==-100){
								$.messager.alert("提示","会话超时，请重新登陆！", "error");
							}else{
								$.messager.alert("提示",result.message, "error");
							}
						}
					});
				});
			}
		},functionToolbar);
	var allFunctionToolbar=$("#role4org-allFunctionToolbar");
	UI.bindEvents({
		"sure-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择需要操作的用户！", "info");
				return;
			}
			var check = allFunctionList.datagrid("getChecked");
			if(check.length==0){
				$.messager.alert("提示", "请选择需要添加的功能！", "info");
				return;
			}
			var functionIdArr = [];
			$.each(check,function(index,item){
				functionIdArr.push(item.id);
	    	});
			var functionIdStr = functionIdArr.join(",");
			var obj = new Object();
			obj.authorityUserId = $.cookie("userId");
			obj.functionIds = functionIdStr;
			obj.id = row.id;
			$.ajax({
				url:SiteUrl.System_API+"roleFunction/insert.do",
				data:obj,
				dataType:'jsonp',
				type:'post',
				success:function(result){
					if(result.error==1){
						$.messager.alert("提示",result.message, "info");
						allFunctionDialog.dialog("close");
						functionList.datagrid("reload");
					}else if(result.error==-100){
						$.messager.alert("提示","会话超时，请重新登陆！", "error");
					}else{
						$.messager.alert("提示",result.message, "error");
					}
				}
			});
		},
		"cancel-button":function(){
			allFunctionDialog.dialog("close");
		}
	},allFunctionToolbar);
	function showRoleFunction(){
		var row = list.datagrid("getSelected");
		functionList.datagrid({
			loader:function(param,success,error){
				param.id = row.id;		//角色id
				param.authorityUserId = $.cookie("userId");
				$.ajax({
					url:SiteUrl.System_API+"role/getRoleFunctionPage.do",
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
			singleSelect:false,
			fitColumns:true,
			rownumbers:true,
			pagination:true,
			toolbar:functionToolbar,
			columns:[[
			          {field:"ck",checkbox:true},
			          {field:"functionName",title:"功能名称",width:100},
			          {field:"functionDescription",title:"功能说明",width:200},
			          {field:"type",title:"分类",width:100},
			          {field:"url",title:"url地址",width:100},
			          {field:"systemName",title:"系统",width:100},
			          {field:"module",title:"模块名称",width:100},
			          {field:"fgroup",title:"分组",width:100}
			          ]]
		});
		functionDialog.dialog({
			title:row.roleName+":功能分配"
		});
		//如果是系统角色则不显示增删功能按钮
		if(row.isSystemRole==1){
			functionToolbar.hide();
		}
		functionDialog.dialog("open");
	}
});
