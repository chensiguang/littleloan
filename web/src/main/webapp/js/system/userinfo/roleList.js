
function showUserRole(){
	var list=$("#userinfo-mainGrid");
	var row = list.datagrid("getSelected");
	var roleList=$("#userinfo-roleGrid");
	var roleDialog=$("#userinfo-role-gridDialog");
	var roleToolbar=$("#userinfo-roleGridToolbar");
	var allRoleList=$("#userinfo-allRoleGrid");
	var allRoleDialog=$("#userinfo-allRole-gridDialog");
	var allRoleToolbar=$("#userinfo-allRoleToolbar");
	var listType = "server";
	if(row==null){
		$.messager.alert("提示", "请选择一条记录！", "info");
		return;
	}
	roleList.datagrid({
		loader:function(param,success,error){
			param.id = row.id;		//用户id
			param.authorityUserId = $.cookie("userId");
			param.listType = listType;
			$.ajax({
				url:SiteUrl.System_API+"userRole/getUserRolePage.do",
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
		toolbar:roleToolbar,
		columns:[[
		          {field:"ck",checkbox:true},
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
	roleDialog.dialog({
		title:row.username+":角色分配"
	});
	roleDialog.dialog("open");
	UI.bindEvents({
		"add-role-button":function(){
			allRoleList.datagrid({
				loader:function(param,success,error){
					var row = list.datagrid("getSelected");
					param.orgnizationId = $("#userinfo-orgnizationId").val();
					param.authorityUserId = $.cookie("userId");
					param.id = row.id;
					param.listType = listType;
					$.ajax({
						url:SiteUrl.System_API+"user/getRoleNeeded.do",
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
				toolbar:"#userinfo-allRoleToolbar",
				columns:[[
				          {field:"ck",checkbox:true},
				          {field:"roleName",title:"角色名称",width:100},
				          {field:"roleDescription",title:"角色说明",width:200},
				          {field:"isSystemRole",title:"系统角色",width:100,
				        	  formatter:function(value){
				        		  if(value=="1"){
				        			  return "是";
				        		  }else{
				        			  return "否";
				        		  }
				        	  }}
				          ]]
			});
			var row = list.datagrid("getSelected");
			allRoleDialog.dialog({
				title:row.username+":角色分配>>添加"
			});
			allRoleDialog.dialog("open");
		},
		"remove-role-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				return;
			}
			var check = roleList.datagrid("getChecked");
			if(check.length==0){
				$.messager.alert("提示", "请选择需要添加的功能！", "info");
				return;
			}
			$.messager.confirm("操作提示","确认删除？",function(result){
				if(!result){
					return;
				}
				var check = roleList.datagrid("getChecked");
				var userRoleIdArr = [];
				$.each(check,function(index,item){
					userRoleIdArr.push(item.userRoleId);
		    	});
				var userRoleIdStr = userRoleIdArr.join(",");
				var obj = new Object();
				obj.authorityUserId = $.cookie("userId");
				obj.userRoleIds = userRoleIdStr;
				obj.id = row.id;
				$.ajax({
					url:SiteUrl.System_API+"userRole/delete.do",
					data:obj,
					dataType:'jsonp',
					type:'post',
					success:function(result){
						if(result.error==1){
							roleList.datagrid("reload");
						}else if(result.error==-100){
							$.messager.alert("提示","会话超时，请重新登陆！", "error");
						}else{
							$.messager.alert("提示",result.message, "error");
						}
					}
				});
			});
		}
	},roleToolbar);
	UI.bindEvents({
		"sure-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择需要操作的用户！", "info");
				return;
			}
			var check = allRoleList.datagrid("getChecked");
			if(check.length==0){
				$.messager.alert("提示", "请选择需要添加的功能！", "info");
				return;
			}
			var roleIdArr = [];
			$.each(check,function(index,item){
				roleIdArr.push(item.id);
	    	});
			var roleIdStr = roleIdArr.join(",");
			var obj = new Object();
			obj.authorityUserId = $.cookie("userId");
			obj.roleIds = roleIdStr;
			obj.id = row.id;
			$.ajax({
				url:SiteUrl.System_API+"userRole/insert.do",
				data:obj,
				dataType:'jsonp',
				type:'post',
				success:function(result){
					if(result.error==1){
						allRoleDialog.dialog("close");
						roleList.datagrid("reload");
					}
					else if(result.error==-10){
						$.messager.alert("提示",result.message, "info");
						allRoleDialog.dialog("close");
						roleDialog.dialog("close");
						list.datagrid("reload");
					}
					else if(result.error==-100){
						$.messager.alert("提示","会话超时，请重新登陆！", "error");
					}else{
						$.messager.alert("提示",result.message, "error");
					}
				}
			});
		},
		"cancel-button":function(){
			allRoleDialog.dialog("close");
		}
	},allRoleToolbar);
}

