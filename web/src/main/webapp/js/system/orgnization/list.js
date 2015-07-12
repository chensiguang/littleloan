//本窗口专门为组织分配准入规则使用
$(function(){
	var list = $("#orgnization-tree");
	var toolbar=$("#orgnization-treeToolbar");
	var formPanel=$("#orgnization-treeForm");
	var formEl=formPanel.find("form");
	var treeDialog = $("#orgnization-create-treeDialog");
	var form = $("#orgnization-form");
	//加载下拉框
	ControllerUtil.loadComboBox($("#orgnization-orgnizationType"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"orgnizationType"});
	//下拉框只读
	$(".easyui-combobox").combobox({
		editable:false
	});
	var treedata;
	list.tree({
	    loader:function(param,success,error){
	    	param.authorityUserId=$.cookie("userId");
	    	$.ajax({
	    		url:SiteUrl.System_API+"orgnization/getTree.do",
	    		type:"post",
	    		data:param,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				treedata=data.rows;
	    				success(treedata);
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
	    },
		onSelect:function(node){
			formEl.form("clear");
			formEl.form("load",node);
		},
		onLoadSuccess:function(){
			var node = list.tree('getRoot');
			list.tree('select', node.target);
		}
	});
	UI.bindEvents({
		"add-button":function(){
			form.form("clear");
			treeDialog.dialog({
				title:'添加组织',
				buttons:[{
					text:'创建',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.authorityUserId=$.cookie("userId");
						obj.creater=$.cookie("userId");
						obj.orgnizationName=$("#orgnization-orgnizationName").val();
						obj.shortname=$("#orgnization-shortname").val();
						obj.orgnizationCode=$("#orgnization-orgnizationCode").val();
						obj.legalRepresentative=$("#orgnization-legalRepresentative").val();
						obj.orgnizationType=$("#orgnization-orgnizationType").combobox("getText");
						obj.registeredAddress=$("#orgnization-registeredAddress").val();
						obj.cityName=$("#orgnization-cityName").val();
						obj.url=$("#orgnization-url").val();
						obj.telephone=$("#orgnization-telephone").val();
						obj.fax=$("#orgnization-fax").val();
						obj.email=$("#orgnization-email").val();
						obj.bak=$("#orgnization-bak").val();
						obj.status=$("#orgnization-status").val();
						$.ajax({
							url:SiteUrl.System_API+"orgnization/insert.do",
							type:"post",
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示", result.message, "info");
									treeDialog.dialog("close");
									list.tree('reload');
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
						treeDialog.dialog("close");
					}
				}]
			});
			treeDialog.dialog("open");
		},
		"addChild-button":function(){
			form.form("clear");
			treeDialog.dialog({
				title:'添加子组织',
				buttons:[{
					text:'创建',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var node = list.tree("getSelected");
						if(!node){
							$.messager.alert("提示", "请选择一条记录！", "info");
							return;
						};
						var obj = new Object();
						obj.pid=node.id;
						obj.idPath=node.idPath;
						obj.namePath=node.namePath;
						obj.authorityUserId=$.cookie("userId");
						obj.creater=$.cookie("userId");
						obj.orgnizationName=$("#orgnization-orgnizationName").val();
						obj.shortname=$("#orgnization-shortname").val();
						obj.orgnizationCode=$("#orgnization-orgnizationCode").val();
						obj.legalRepresentative=$("#orgnization-legalRepresentative").val();
						obj.orgnizationType=$("#orgnization-orgnizationType").combobox("getText");
						obj.registeredAddress=$("#orgnization-registeredAddress").val();
						obj.cityName=$("#orgnization-cityName").val();
						obj.url=$("#orgnization-url").val();
						obj.telephone=$("#orgnization-telephone").val();
						obj.fax=$("#orgnization-fax").val();
						obj.email=$("#orgnization-email").val();
						obj.bak=$("#orgnization-bak").val();
						obj.status=$("#orgnization-status").val();
						$.ajax({
							url:SiteUrl.System_API+"orgnization/insertChild.do",
							type:"post",
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示", result.message, "info");
									treeDialog.dialog("close");
									list.tree('reload');
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
						treeDialog.dialog("close");
					}
				}]
			});
			treeDialog.dialog("open");
		},
		"edit-button":function(){
			var node = list.tree("getSelected");
			form.form("load",node);
			treeDialog.dialog({
				title:'更改组织',
				buttons:[{
					text:'确定',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						if(!node){
							$.messager.alert("提示", "请选择一条记录！", "info");
							return;
						};
						var obj = new Object();
						obj.id=node.id;
						obj.authorityUserId=$.cookie("userId");
						obj.creater=$.cookie("userId");
						obj.orgnizationName=$("#orgnization-orgnizationName").val();
						obj.shortname=$("#orgnization-shortname").val();
						obj.orgnizationCode=$("#orgnization-orgnizationCode").val();
						obj.legalRepresentative=$("#orgnization-legalRepresentative").val();
						obj.orgnizationType=$("#orgnization-orgnizationType").combobox("getText");
						obj.registeredAddress=$("#orgnization-registeredAddress").val();
						obj.cityName=$("#orgnization-cityName").val();
						obj.url=$("#orgnization-url").val();
						obj.telephone=$("#orgnization-telephone").val();
						obj.fax=$("#orgnization-fax").val();
						obj.email=$("#orgnization-email").val();
						obj.bak=$("#orgnization-bak").val();
						obj.status=$("#orgnization-status").val();
						obj.pid=node.pid;
						$.ajax({
							url:SiteUrl.System_API+"orgnization/update.do",
							type:"post",
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示", result.message, "info");
									treeDialog.dialog("close");
									list.tree('reload');
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
						treeDialog.dialog("close");
					}
				}]
			});
			treeDialog.dialog("open");
		},
		"remove-button":function(){
			var node = list.tree("getSelected");
			if(!node) return;
			if(node.children){
				var name=node.text;
				var msg="组织："+name+" 含有下级组织，不能删除";
				UI.alert(msg,"警告");
				return;
			};
			UI.confirm("是否删除该组织？",function(){
				url = SiteUrl.System_API+"orgnization/delete.do";
				$.post(url,{id:node.id,authorityUserId:$.cookie("userId")},function(result){
					if(result.error==1){
						$.messager.alert("提示", result.message, "info");
						list.tree('reload');
					}else if(result.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					}else{
						$.messager.alert("提示", result.message, "error");
					}
				},'jsonp');
			});
		}
	},toolbar);
	
	$("#btn-orgnization-system-authority").click(function(){		
		UI.addTab("system/systemAuthority/list4org.html","组织分配准入规则",null,function(flag){
			$("#systemAuthority4org_orginizationId").val(list.tree("getSelected").id);//目标窗口中的元素
			System4orgAuthority.loadData();
		});
	});
	$("#btn-orgnization-role").click(function(){		
		UI.addTab("system/role/list4org.html","组织定义角色",null,function(flag){
			$("#role4org-orgnizationId").val(list.tree("getSelected").id);//目标窗口中的元素
			role4orgAuthority.loadData();
		});
	});
	$("#btn-orgnization-userinfo").click(function(){		
		UI.addTab("system/userinfo/list4org.html","组织定义用户",null,function(flag){
			$("#userinfo4org-orgnizationId").val(list.tree("getSelected").id);//目标窗口中的元素
			Userinfo.loadData();
		});
	});
	$("#btn-orgnization-department").click(function(){		
		UI.addTab("system/department/list4org.html","组织创建部门",null,function(flag){
			$("#dapartment4org-orgnizationId").val(list.tree("getSelected").id);//目标窗口中的元素
			Department.loadData();
		});
	});
	
});