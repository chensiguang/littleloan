$(function(){
	var list=$("#dapartment-treegrid");
	var toolbar=$("#dapartment-tree-gridToolbar");
	var departmentParentName = $("#department-departmentParentName");
	//加载下拉框
	ControllerUtil.loadComboBox($("#department-departmentType"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"departmentType"});
	UI.loadList({
		type:"tree",
        list:list,
        toolbar:toolbar,
        url:SiteUrl.System_API+"department/getDeptTree.do",
        config:{
        	idField:"id", //属性结构必传的属性 树节点ID
        	treeField:"departmentName", //属性结构必传的属性 树展现名称
        	pagination:false //分页 默认true，这边可设置 不分页 改成false
        },
        params:{
        	functionId:2,
        	orgnizationId:$.cookie("orgnizationId")
        },
        treeEv:departmentParentName //如果需要将tree的数据同时加载在其他控件上，传需要加载控件
    });
//	var listConfig;//页面列表配置的全局变量
//	var listParam = UI.getFunctionParam(list);//取得当前列表配置 参数 
//	UI.loadList(listParam,loadData);//装载list
//	function loadData(config){
//		listConfig=config;
//		list.treegrid({
//			loader:function(param,success,error){
//				param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
//				param.orgnizationId = $.cookie("orgnizationId");
//				$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
//					url:SiteUrl.System_API+"department/getDeptTree.do",
//					type:"post",
//					data:param,
//					dataType:"jsonp",
//					success:function(data){
//						if(data.error==1){
//							//同时初始化 下拉框
//							departmentParentName.combotree(
//										{data:data.rows,
//											width:150});
//							success(data);//加载数据
//						}
//						else if(data.error==-100){
//							$.messager.alert("提示", "会话超时，请重新登陆！", "error");
//						}
//						else{
//							$.messager.alert("提示", data.message, "error");
//						}
//						
//					}
//				});
//			},
//			toolbar: '#dapartment-tree-gridToolbar',
//		    idField:'id',
//		    treeField:'departmentName',
//		    fitColumns:true,
//		    rownumbers: true,
//		    onDblClickRow:UI.trigger("edit-button",toolbar),
//			columns:[UI.getColumnsConfig(config).cfg]
//		});
//	}
	
	
	var treeNode; //存放上级部门下拉树 选中的节点
	var createDialog=$("#department-dialog");//dialog
	var namePath=$("#department-namePath");//部门路径 根据上级部门路径和部门名称自动生成
	var form = $("#department-form");//form表单
	$("#department-departmentType").combobox({width:150});
	 //初始化 下拉树选项 为空
//	 $("#remove-dep-button").bind('click',function(){
//		 departmentParentName.combotree("clear");
//	 });

	//动态设置 部门路径的值
	function getPath(){
		var deTree = departmentParentName.combotree("tree");
		var deTreeNode = deTree.tree("getSelected");
		treeNode = deTreeNode;
		var deName =$("#department-departmentName").val();
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
	$("#department-departmentName").textbox({
		onChange:function(newValue,oldValue){
			getPath();
		}
	});
	
	UI.bindEvents({
		"add-button":function(){
			var node=list.treegrid("getSelected");
			namePath.html("");
			form.form("clear");//表单清空
			departmentParentName.combotree({readonly:false});
//			if(node&&node.id){
//				departmentParentName.combotree("setValue",node.id);
//			}			
			createDialog.dialog({
				title:"创建部门",
				buttons:[{
					text:'创建',
					handler:function(){	
						form.form('submit',{
			                url: SiteUrl.System_API+"department/insertDepartment.do",
			                onSubmit: function(param){
								param.pid = treeNode&&treeNode.id||0;
								param.idPath = treeNode&&treeNode.idPath||0;
								param.orgnizationId = $.cookie("orgnizationId");
								param.authorityUserId = $.cookie("userId");
								param.namePath =namePath.html(); 
								param.departmentType = $("#department-departmentType").combobox("getValue");
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
			$("#department-departmentType").combobox("select",node.departmentType);
			namePath.html(node.namePath);
			createDialog.dialog({
				title:"修改部门",
				buttons:[{
					text:'保存',
					handler:function(){
						form.form('submit',{
			                url: SiteUrl.System_API+"department/updateDepartment.do",
			                onSubmit: function(param){
								param.orgnizationId = $.cookie("orgnizationId");
								param.authorityUserId = $.cookie("userId");
								param.namePath =namePath.html();
								param.departmentType = $("#department-departmentType").combobox("getValue");
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

		}/*,
    	"setting-button":function(e){
    		//重新组装弹窗内容
    		UI.showListConfig(listConfig,listParam,loadData);
    	}*/
	},toolbar);

});