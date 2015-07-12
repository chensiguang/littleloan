$(function(){
	var list = $("#userArchivesAuthority-mainGrid");// 获取当前grid名称
	var toolbar = $("#userArchivesAuthority-gridToolbar");
	var dialog = $("#userArchivesAuthority-edit-dialog");
	var form = $("#userArchivesAuthority-form");
	
	$('#userArchivesAuthorityLevel').combobox({
		onChange:function(newValue,oldValue){
			if(newValue == "1"){
				$('#userArchivesAuthorityTargetUserName').combobox("enable");
				$('#userArchivesAuthorityArcCategoryName').combobox("disable");
				$('#userArchivesAuthorityArcNo').combobox("disable");
			}else if(newValue == "2"){
				$('#userArchivesAuthorityTargetUserName').combobox("enable");
				$('#userArchivesAuthorityArcCategoryName').combobox("enable");
				$('#userArchivesAuthorityArcNo').combobox("disable");
			}else if(newValue == "3"){
				$('#userArchivesAuthorityTargetUserName').combobox("disable");
				$('#userArchivesAuthorityArcCategoryName').combobox("disable");
				$('#userArchivesAuthorityArcNo').combobox("enable");
			}
		}
	});
	
	$('#userArchivesAuthority-name').combobox({
    	loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"userArchivesAuthority/userList.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						data.rows[0].selected = "true";
						success(data.rows);//加载数据
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
	    valueField:'userId',
	    textField:'userName'
	});
	list.datagrid({
		loader:function(param, success, error){
			param.authorityUserId = $.cookie("userId");
			param.orgId = $.cookie("orgnizationId");
			$.ajax({
				url: SiteUrl.Portals_API + "userArchivesAuthority/page.do",
				type:"post",
				data : param,
				dataType : "jsonp",
				success:function(data){
					if(data.error == 1){
						success(data);// 加载数据
					} else if (data.error == -100) {
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					} else {
						$.messager.alert("提示", data.message, "error");
					}
				}
			})
		},
		fit : true,
		fitColumns : true,
		pagination : true,
		loadMsg : '正在加载内容列表…',
		toolbar : toolbar,// 绑定工具栏
		rownumbers : true,
		striped : true,
		singleSelect:true,
		onLoadSuccess:function(data){
			var vc = $("#userArchivesAuthority-mainGrid").datagrid("getPanel").children("div.datagrid-view");
			if($("#userSurveyEmptyTip").length){
				$("#userSurveyEmptyTip").remove();
			}
			if(data.total <= 0){
				var d= $('<div id="userSurveyEmptyTip" class="datagrid-emtpy"></div>').html("暂时没有相关记录").appendTo(vc);
				d.css({
					position:'absolute',
					left:0,
					top:50,
					width:'100%',
					textAlign:'center',
					color:'#999'
				});
			}
		},
		columns :[[
		           {field : "id",title : "",checkbox : true},
		           {field:"level",title:"权限级别",width:30,formatter : function(v, r, index) {
						var str;
						switch (v) {
						case 1:
							str = "用户";
							break;
						case 2:
							str = "档案类别";
							break;
						default:
							str = "档案";
							break;
						}
						return str;
					}
		           },
		           {field:"type",title:"有无权限",width:30,formatter : function(v, r, index) {
						var str;
						switch (v) {
						case 1:
							str = "有";
							break;
						default:
							str = "无";
							break;
						}
						return str;
					}
		           },
		           {field:"targetUserName",title:"对象用户",width:30},
		           {field:"arcCategoryName",title:"档案类别",width:30},
		           {field:"arcName",title:"档案名称",width:30},
		           {field:"arcNo",title:"档案编号",width:30}
		           ]]
	});
	
	var pager=list.datagrid('getPager');
    pager.pagination({
        layout:['list','sep','first','prev','links','next','last','sep','refresh']
    });
	
	
	UI.bindEvents({
		"search-button": function(e){
			e.preventDefault();
			list.datagrid("load", {// 刷新一下页面，并将查询条件作为参数传递给后台
				userId : $("#userArchivesAuthority-name").val(),
				orgId : $.cookie("orgnizationId")
			})
		},
		
		"insert-button": function(){
			var node = list.datagrid("getSelected");
			if(node == null){
				form.form("clear");
				$('#userArchivesAuthorityTargetUserName').combobox("enable");
				$('#userArchivesAuthorityArcCategoryName').combobox("enable");
				$('#userArchivesAuthorityArcNo').combobox("enable");
				dialog.dialog({
					title:'添加权限',
					buttons:[{
						text:'创建',
						handler:function(){
							if(!form.form("validate")){
								return;
							}
							var obj = new Object();
							obj.authorityUserId=$.cookie("userId");
							obj.creater=$.cookie("userId");
							obj.orgId=$.cookie("orgnizationId");
							obj.userId=$("#userArchivesAuthorityName").combobox("getValue");
							obj.level=$("#userArchivesAuthorityLevel").combobox("getValue");
							obj.targetUserId=$("#userArchivesAuthorityTargetUserName").combobox("getValue");
							obj.arcCategoryId=$("#userArchivesAuthorityArcCategoryName").combobox("getValue");
							obj.arcNo=$("#userArchivesAuthorityArcNo").combobox("getValue");
							obj.type = 1;
							obj.arcName=$("#userArchivesAuthorityArcName").val();
							$.ajax({
								url:SiteUrl.System_API+"userArchivesAuthority/insert.do",
								type:"post",
								data:obj,
								dataType:'jsonp',
								success:function(result){
									if(result.error==1){
										$.messager.alert("提示", result.message, "info");
										dialog.dialog("close");
										list.datagrid('reload');
									}else if(result.error==-100){
										$.messager.alert("提示", "会话超时，请重新登陆！", "error");
									}else{
										$.messager.alert("提示", result.message, "error");
									}
								}
							});
						}
					},{
						text:'取消',
						handler:function(){
							dialog.dialog("close");
						}
					},{
						text:'移除权限',
						handler:function(){
							if(!form.form("validate")){
								return;
							}
							var obj = new Object();
							obj.authorityUserId=$.cookie("userId");
							obj.creater=$.cookie("userId");
							obj.orgId=$.cookie("orgnizationId");
							obj.userId=$("#userArchivesAuthorityName").combobox("getValue");
							obj.level=$("#userArchivesAuthorityLevel").combobox("getValue");
							obj.targetUserId=$("#userArchivesAuthorityTargetUserName").combobox("getValue");
							obj.arcCategoryId=$("#userArchivesAuthorityArcCategoryName").combobox("getValue");
							obj.arcNo=$("#userArchivesAuthorityArcNo").combobox("getValue");
							obj.type = 1;
							obj.arcName=$("#userArchivesAuthorityArcName").val();
							$.ajax({
								url:SiteUrl.System_API+"userArchivesAuthority/delete.do",
								type:"post",
								data:obj,
								dataType:'jsonp',
								success:function(result){
									if(result.error==1){
										$.messager.alert("提示", result.message, "info");
										dialog.dialog("close");
										list.datagrid('reload');
									}else if(result.error==-100){
										$.messager.alert("提示", "会话超时，请重新登陆！", "error");
									}else{
										$.messager.alert("提示", result.message, "error");
									}
								}
							});
						}
					}]
				});
				dialog.dialog("open");
			}else{
				var node = list.datagrid("getSelected");
				if(node.type == "1"){
					$('#userArchivesAuthorityTargetUserName').combobox("enable");
					$('#userArchivesAuthorityArcCategoryName').combobox("disable");
					$('#userArchivesAuthorityArcNo').combobox("disable");
				}else if(node.type == "2"){
					$('#userArchivesAuthorityTargetUserName').combobox("enable");
					$('#userArchivesAuthorityArcCategoryName').combobox("enable");
					$('#userArchivesAuthorityArcNo').combobox("disable");
				}else if(node.type == "3"){
					$('#userArchivesAuthorityTargetUserName').combobox("disable");
					$('#userArchivesAuthorityArcCategoryName').combobox("disable");
					$('#userArchivesAuthorityArcNo').combobox("enable");
				}
				form.form("load",node);
				dialog.dialog({
					title:'添加设定',
					buttons:[{
						text:'创建',
						handler:function(){
							if(!form.form("validate")){
								return;
							}
							var obj = new Object();
							obj.authorityUserId=$.cookie("userId");
							obj.creater=$.cookie("userId");
							obj.orgId=$.cookie("orgnizationId");
							obj.userId=$("#userArchivesAuthorityName").combobox("getValue");
							obj.level=$("#userArchivesAuthorityLevel").combobox("getValue");
							obj.targetUserId=$("#userArchivesAuthorityTargetUserName").combobox("getValue");
							obj.arcCategoryId=$("#userArchivesAuthorityArcCategoryName").combobox("getValue");
							obj.arcNo=$("#userArchivesAuthorityArcNo").combobox("getValue");
							obj.type = 1;
							obj.arcName=$("#userArchivesAuthorityArcName").val();
							$.ajax({
								url:SiteUrl.System_API+"userArchivesAuthority/insert.do",
								type:"post",
								data:obj,
								dataType:'jsonp',
								success:function(result){
									if(result.error==1){
										$.messager.alert("提示", result.message, "info");
										dialog.dialog("close");
										list.datagrid('reload');
									}else if(result.error==-100){
										$.messager.alert("提示", "会话超时，请重新登陆！", "error");
									}else{
										$.messager.alert("提示", result.message, "error");
									}
								}
							});
						}
					},{
						text:'取消',
						handler:function(){
							dialog.dialog("close");
						}
					},{
						text:'移除权限',
						handler:function(){
							if(!form.form("validate")){
								return;
							}
							var obj = new Object();
							obj.authorityUserId=$.cookie("userId");
							obj.creater=$.cookie("userId");
							obj.orgId=$.cookie("orgnizationId");
							obj.userId=$("#userArchivesAuthorityName").combobox("getValue");
							obj.level=$("#userArchivesAuthorityLevel").combobox("getValue");
							obj.targetUserId=$("#userArchivesAuthorityTargetUserName").combobox("getValue");
							obj.arcCategoryId=$("#userArchivesAuthorityArcCategoryName").combobox("getValue");
							obj.arcNo=$("#userArchivesAuthorityArcNo").combobox("getValue");
							obj.type = 1;
							obj.arcName=$("#userArchivesAuthorityArcName").val();
							$.ajax({
								url:SiteUrl.System_API+"userArchivesAuthority/delete.do",
								type:"post",
								data:obj,
								dataType:'jsonp',
								success:function(result){
									if(result.error==1){
										$.messager.alert("提示", result.message, "info");
										dialog.dialog("close");
										list.datagrid('reload');
									}else if(result.error==-100){
										$.messager.alert("提示", "会话超时，请重新登陆！", "error");
									}else{
										$.messager.alert("提示", result.message, "error");
									}
								}
							});
						}
					}]
				});
				dialog.dialog("open");
			}
		}
	},toolbar);

})