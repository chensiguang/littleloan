$(function(){
	Department = {
			loadData : function(){
				loadGrid();
			}
	};
	var list=$("#dapartment4org-treegrid");
	var toolbar=$("#dapartment4org-tree-gridToolbar");
	var departmentParentName = $("#dapartment4org-departmentParentName");
	//加载下拉框
	ControllerUtil.loadComboBox($("#dapartment4org-departmentType"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"departmentType"});
	function loadGrid(){
		list.treegrid("unselectAll");
		list.treegrid({
			loader:function(param,success,error){
				param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
				if(!$("#dapartment4org-orgnizationId").val()){
					$("#dapartment4org-orgnizationId").val($.cookie("orgnizationId"));
				}
				param.orgnizationId =$("#dapartment4org-orgnizationId").val();
				$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
					url:SiteUrl.System_API+"department/getDeptTree.do",
					type:"post",
					data:param,
					dataType:"jsonp",
					success:function(data){
						if(data.error==1){
							//同时初始化 下拉框
							departmentParentName.combotree(
										{data:data.rows,
											width:150});
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
			toolbar: '#dapartment4org-tree-gridToolbar',
		    idField:'id',
		    treeField:'departmentName',
		    fitColumns:true,
		    rownumbers: true,
		    onDblClickRow:UI.trigger("edit-button",toolbar),
			columns:[[
			          {field:"departmentName",title:"部门名称",width:350,align:"left"},
			          {field:"departmentType",title:"部门类型",width:250},
			          {field:"namePath",title:"部门路径",width:350}
			         ]]
		});
	}
	

	var treeNode; //存放上级部门下拉树 选中的节点
	var createDialog=$("#department4org-dialog");//dialog
	var namePath=$("#dapartment4org-namePath");//部门路径 根据上级部门路径和部门名称自动生成
	var form = $("#department4org-form");//form表单
	$("#dapartment4org-departmentType").combobox({width:150});

	//动态设置 部门路径的值
	function getPath(){
		namePath.html("");
		var deTree = departmentParentName.combotree("tree");
		var deTreeNode = deTree.tree("getSelected");
		treeNode = deTreeNode;
		var deName =$("#dapartment4org-departmentName").val();
		if(deTreeNode&&deTreeNode.namePath&&deTreeNode.namePath!=""){
			namePath.html(deTreeNode.namePath+"/"+deName)
		}else{
			namePath.html(deName);
		}
	}
	
	departmentParentName.combotree({
		onChange:function(newValue,oldValue){
			getPath();
		}
	});
	$("#dapartment4org-departmentName").textbox({
		onChange:function(newValue,oldValue){
			getPath();
		}
	});
	
	UI.bindEvents({
		"add-button":function(){
			var node=list.treegrid("getSelected");
			namePath.html("");
			form.form("clear");//表单清空
			departmentParentName.combotree("clear");
			departmentParentName.combotree({readonly:false});		
			createDialog.dialog({
				buttons:[{
					text:'创建',
					handler:function(){	
						form.form('submit',{
			                url: SiteUrl.System_API+"department/insertDepartment.do",
			                onSubmit: function(param){
								param.pid = treeNode&&treeNode.id||0;
								param.idPath = treeNode&&treeNode.idPath||0;
								if(!$("#dapartment4org-orgnizationId").val()){
									$("#dapartment4org-orgnizationId").val($.cookie("orgnizationId"));
								}
								param.orgnizationId =$("#dapartment4org-orgnizationId").val();
								param.authorityUserId = $.cookie("userId");
								param.namePath =namePath.html(); 
								param.departmentType = $("#dapartment4org-departmentType").combobox("getValue");
			                    return $(this).form('validate');
			                },
			                success: function(result){
		                        createDialog.dialog('close');
		                        list.treegrid('reload');
			                }
			            });
					}
				},{
					text:'取消',
					handler:function(){
						createDialog.dialog("close");
					}
				}]
			});
			createDialog.dialog("open");
		},
		"edit-button":function(){
			var node=list.treegrid("getSelected");
			if(!node||node.id==0){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			} 
			form.form('load',node);
			departmentParentName.combotree({readonly:true});
			if(node&&node.pid){
				departmentParentName.combotree("setValue",node.pid);
			}
			$("#dapartment4org-departmentType").combobox("select",node.departmentType);
			namePath.html(node.namePath);
			createDialog.dialog({
				buttons:[{
					text:'保存',
					handler:function(){
						form.form('submit',{
			                url: SiteUrl.System_API+"department/updateDepartment.do",
			                onSubmit: function(param){
			                	debugger;
			                	if(!$("#dapartment4org-orgnizationId").val()){
									$("#dapartment4org-orgnizationId").val($.cookie("orgnizationId"));
								}
								param.orgnizationId =$("#dapartment4org-orgnizationId").val();
								param.authorityUserId = $.cookie("userId");
								param.namePath =namePath.html();
								param.departmentType = $("#dapartment4org-departmentType").combobox("getValue");
								return $(this).form('validate');
			                },
			                success: function(result){
			                	createDialog.dialog('close');
		                        list.treegrid('reload');
			                }
			            });	
					}
				},{
					text:'取消',
					handler:function(){
						createDialog.dialog("close");
					}
				}]
			});
			createDialog.dialog("open");
		},
		"remove-button":function(){
			var node=list.treegrid("getSelected");
			if(!node) return;
			if(node.children&&node.children.length>0){
				var name=node.departmentName;
				var msg="部门："+name+" 含有下级部门，不能删除";
				UI.alert(msg,"警告");
				return;
			};
			if(node.id==0){
				var msg="该节点为根节点,不能删除";
				UI.alert(msg,"警告");
				return;
			}
			$.messager.confirm("操作提示","是否删除该部门？",function(result){
				authorityUserId = $.cookie("userId");
				if(result){
					$.ajax({
						url:SiteUrl.System_API+'department/deleteDepartment.do',
						type:"post",
						data:{id:node.id,authorityUserId:authorityUserId,departmentName:node.departmentName},
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								list.treegrid('unselectAll');
								list.treegrid('reload');	
							}
							else if(data.error==-100){
								$.messager.alert("提示", "会话超时，请重新登陆！", "error");
							}
							else{
								$.messager.alert("提示", data.message, "error");
							}
						}
					}); 
				}
			});

		}
	},toolbar);

});