$(function(){
	var list = $("#archivesCategory-tree");
	var toolbar=$("#archivesCategory-treeToolbar");
	var formPanel=$("#archivesCategory-treeForm");
	var formEl=formPanel.find("form");
	var treeDialog = $("#archivesCategory-create-treeDialog");
	var form = $("#archivesCategory-form");
	var treedata;
	
	list.tree({
		loader:function(param,success,error){
			param.authorityUserId=$.cookie("userId");
			param.orgId=$.cookie("orgnizationId");
	    	$.ajax({
	    		url:SiteUrl.System_API+"archivesCategory/getTree.do",
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
			if(node !=null){
				list.tree('select', node.target);
			}
		}
	});
	UI.bindEvents({
		"add-button":function(){
			form.form("clear");
			treeDialog.dialog({
				title:'添加根类别',
				buttons:[{
					text:'创建',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						obj.authorityUserId=$.cookie("userId");
						obj.orgId=$.cookie("orgnizationId");
						obj.creater=$.cookie("userId");
						obj.name=$("#archives-archivesCategoryName").val();
						obj.notes=$("#archives-archivesCategoryNotes").val();
						obj.path ="/";
						obj.depth = 0;
						obj.parentId= 0;
						$.ajax({
							url:SiteUrl.System_API+"archivesCategory/insert.do",
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
			var root = list.tree("getSelected");
			if(!root) {
				$.messager.alert("提示", "请选择一条记录！", "error");
			};
			form.form("clear");
			treeDialog.dialog({
				title:'添加子类别',
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
						obj.parentId=node.id;
						obj.path=node.path;
						obj.depth=(node.depth+1);
						obj.authorityUserId=$.cookie("userId");
						obj.orgId=$.cookie("orgnizationId");
						obj.creater=$.cookie("userId");
						obj.name=$("#archives-archivesCategoryName").val();
						obj.parentName=$("#archives-archivesCategoryParentName").val();
						obj.notes=$("#archives-archivesCategoryNotes").val();
						$.ajax({
							url:SiteUrl.System_API+"archivesCategory/insert.do",
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
			var node = list.tree("getSelected");
			var name = node.text;
			form.form("load",{
				parentName:name
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
						obj.id = node.id;
						obj.authorityUserId=$.cookie("userId");
						obj.mender=$.cookie("userId");
						obj.name=$("#archives-archivesCategoryName").val();
						obj.notes=$("#archives-archivesCategoryNotes").val();
						$.ajax({
							url:SiteUrl.System_API+"archivesCategory/update.do",
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
			if(!node) {
				$.messager.alert("提示", "请选择一条记录！", "error");
			};
			if(node.children =="[]"){
				console.log("不能删除");
				var name=node.text;
				var msg="组织："+name+" 含有下级组织，不能删除";
				UI.alert(msg,"警告");
				return;
			};
			UI.confirm("是否删除该组织？",function(){
				url = SiteUrl.System_API+"archivesCategory/delete.do";
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
})