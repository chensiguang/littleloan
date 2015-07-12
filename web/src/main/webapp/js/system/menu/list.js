$(function(){

			var list=$("#menu-mainGrid");//获取当前grid名称
            var toolbar=$("#menu-gridToolbar");
			//var listConfig; //设置 列配置 的 全局变量
			//var listParam = UI.getFunctionParam(list);//取得当前列表配置 参数 
			//UI.loadList(listParam,loadData); //进入 获取页的 列取得 loadData 是方法回调函数
            UI.loadList({
                list:list,
                toolbar:toolbar,
                url:SiteUrl.System_API+"menu/page.do",
                /*config:{
                	pagination:false; //分页 默认true，这边可设置 不分页 改成false
                	onDblClickRow：false;//双击 row 时间 是否触发事件 
                },*/
                params:{ // 列表展现参数传递
                	functionId:6 //必传 功能ID
                }
            });

			var param = new Object();
			param.orgnizationId = $.cookie("orgnizationId");
			ControllerUtil.loadComboBox($("#menu-system"), SiteUrl.System_API+"system/getSystemAuthorityListByOrgnizationId.do",param);
//			function loadData(config){	
//				listConfig=config;// 将取得的列配置 付给 全局变量
//				var pageConfig = UI.getColumnsConfig(config).pagePation;//取得 分页配置 
//				list.datagrid({
//					loader:function(param,success,error){
//						param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
//						$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
//							url:SiteUrl.System_API+"menu/page.do",
//							type:"post",
//							data:param,
//							dataType:"jsonp",
//							success:function(data){
//								if(data.error==1){
//									success(data);//加载数据
//								}
//								else if(data.error==-100){
//									$.messager.alert("提示", "会话超时，请重新登陆！", "error");
//								}
//								else{
//									$.messager.alert("提示", data.message, "error");
//								}
//								
//							}
//						});
//					},
//					singleSelect:true,
//					fit:true,
//					fitColumns:true,//列宽自适应
//					rownumbers:true,
//					pagination:true,
//					pageSize:pageConfig.pageSize,
//					onDblClickRow:UI.trigger("edit-button",toolbar),
//					toolbar:'#menu-gridToolbar',//绑定工具栏
//					columns:[UI.getColumnsConfig(config).cfg] // 列的动态配置
//				});
//				UI.setPageModeSave(list,listParam,pageConfig); //分页 点击切换分页条数并保存
//			}
//				
            var gridDialog=$("#menu-create-gridDialog");
            var form = $("#menu-form");
            $("#menu-system").combobox({width:150});	
            UI.bindEvents({
        		"add-button":function(){//工具栏新增按钮
        			form.form("clear");//表单清空
        			gridDialog.dialog({
        				title:"创建数据",
        				buttons:[{
        					text:'创建',
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}
        						
        						//定义一个 menu对象，赋值，调用ajax新增数据
        						var obj = new Object();
        						obj.authorityUserId = $.cookie("userId");
        						obj.systemId =$("#menu-system").combobox("getValue")
        						obj.title = $("#menu-title").val();
        						obj.description = $("#menu-description").val();
        						obj.icon = $("#menu-icon").val();
        						obj.order = $("#menu-order").val();
        						obj.url = $("#menu-url").val();
        						 
        						$.ajax({
        							url:SiteUrl.System_API+"menu/insert.do",
        							type:"post",
        							data:obj,
        							dataType:"jsonp",
        							success:function(data){
        								debugger;
        								if(data.error==1){
        									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
        									list.datagrid("reload");//刷新页面
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
        			var row = list.datagrid("getSelected");//获取当前选中的flexkey对象
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			form.form("load",row);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
        			$("#menu-system").combobox("select",row.systemId);	
        			gridDialog.dialog({
        				title:"编辑数据",
        				buttons:[{
        					text:'保存',//保存按钮的事件 
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}
        						
        						//定义一个 flexkey对象，更新到数据库
        						var obj = new Object();
        						obj.authorityUserId = $.cookie("userId");
        						obj.id = $("#menu-id").val();
        						obj.systemId =$("#menu-system").combobox("getValue")
        						obj.title = $("#menu-title").val();
        						obj.description = $("#menu-description").val();
        						obj.icon = $("#menu-icon").val();
        						obj.order = $("#menu-order").val();
        						obj.url = $("#menu-url").val();
        						$.ajax({
        							url:SiteUrl.System_API+"menu/update.do",
        							type:"post",
        							data:obj,
        							dataType:"jsonp",
        							success:function(data){
        								if(data.error==1){
        									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
        									list.datagrid("reload");
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
        				},{
        					text:'取消',//取消按钮的事件
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
    							url:SiteUrl.System_API+"menu/delete.do",
    							type:"post",
    							data:row,
    							dataType:"jsonp",
    							success:function(data){
    								if(data.error==1){
    									list.datagrid("reload");
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
        		},
            	"search-button":function(e){//查询按钮执行
            		e.preventDefault();
            		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
            			systemName:$("#menu-search-systemName").val(),
            			title:$("#menu-search-title").val()
            		});
            	},
            	"allot-button":function(e){//查询按钮执行
            		var row = list.datagrid("getSelected");
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			menuAllot();
            	}/*,
            	"setting-button":function(e){
            		//重新组装弹窗内容
            		UI.showListConfig(listConfig,listParam,loadData);
            	}*/
        	},toolbar);
            
    var gridDialogAllot=$("#allot-function-gridDialog"); 
	var listAllot=$("#menu-allot-mainGrid");//获取当前grid名称      
    //菜单分配功能        
    function  menuAllot(){ 
    	var row = list.datagrid("getSelected");
    	listAllot.datagrid({
			loader:function(param,success,error){
				param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
				param.id= row.id;
				param.type="in";
				$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
					url:SiteUrl.System_API+"menu/queryAllFunMenu.do",
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
			singleSelect:false,
			fit:true,
			fitColumns:true,
			rownumbers:true,
			pagination:true,
			toolbar:'#menu-allot-gridToolbar',//绑定工具栏
			columns:[[//表格绑定字段
			         {field:"ck",checkbox:true},
			         {field:"title",title:"展现标题",width:200},
			         {field:"functionName",title:"功能名称",width:200},
			         {field:"functionDescription",title:"说明",width:200},
			         {field:"type",title:"类型",width:120},
			         {field:"icon",title:"图标",width:120},
			         {field:"order",title:"排序",width:120}, 
			         {field:"url",title:"url",width:350,align:'left'}
			]]
		});
    	
    	var pagerAllot=listAllot.datagrid('getPager');
    	
        pagerAllot.pagination({
            layout:['list','sep','first','prev','links','next','last','sep','refresh']
        });
        
        gridDialogAllot.dialog({
    		title:"分配功能>>"+row.title
    	});
        gridDialogAllot.dialog('open');
        
        toolbarAllot = $("#menu-allot-gridToolbar");
        
    } 
    // 新增 功能
    $("#allot-add-button").bind('click',function(){
    	menuAllotAdd();
    });
    // 修改 功能
    var gridDialogAllotedit=$("#allot-func-edit-gridDialog"); 
    var allotEditform = $("#allot-func-edit-form");
    //菜单功能 界面 搜索
    $("#allot-search-title").bind('click',function(){
    	listAllot.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
			functionName:$("#allot-search-function").val()
		});
    });
    //新增 功能界面 搜索
    $("#allotAdd-search-title").bind('click',function(){
    	listAllotAdd.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
			functionName:$("#allotAdd-search-function").val()
		});
    });
    
    $("#allot-edit-button").bind('click',function(){
    	var row = list.datagrid("getSelected");
    	var check = listAllot.datagrid("getChecked");
    	if(check==null||check.length !=1){
			$.messager.alert("提示", "需要且仅只能选择1条进行修改！", "info");
			return;
		};
		allotEditform.form("load",check[0]);//初始化界面上的控件值，控件的name属性必须与选中行的field对应	
		gridDialogAllotedit.dialog({
			title:"编辑数据",
			buttons:[{
				text:'保存',//保存按钮的事件 
				handler:function(){
					if(!allotEditform.form("validate")){//控件验证
						return;
					}
					
					//定义一个 flexkey对象，更新到数据库
					var obj = new Object();
					obj.authorityUserId = $.cookie("userId");
					obj.id = $("#allot-func-id").val();
					obj.menuId = row.id;
					obj.title = $("#allot-func-title").val();
					obj.icon = $("#allot-func-icon").val();
					obj.order = $("#allot-func-order").val();
					$.ajax({
						url:SiteUrl.System_API+"menu/updateFunMenu.do",
						type:"post",
						data:obj,
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								gridDialogAllotedit.dialog("close");//如果成功关闭窗口，刷新页面
								listAllot.datagrid("reload");
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
			},{
				text:'取消',//取消按钮的事件
				handler:function(){
					gridDialog.dialog("close");
				}
			}]
		});
		gridDialogAllotedit.dialog("open");
    });
   // 删除 功能
    $("#allot-remove-button").bind('click',function(){
    	var row = list.datagrid("getSelected");
    	var check = listAllot.datagrid("getChecked");
    	if(check==null||check.length==0){
			$.messager.alert("提示", "请至少选择一条记录！", "info");
			return;
		};
    	var paramArr = [];
    	$.each(check,function(index,item){
    		paramArr.push(item.id);
    	});
    	var pa = paramArr.join(",");
    	var authorityUserId = $.cookie("userId");
		$.messager.confirm("操作提示","确认删除？",function(result){
			if(result){
				$.ajax({
					url:SiteUrl.System_API+"menu/deleteFunMenu.do",
					type:"post",
					data:{"functionIds":pa,"authorityUserId":authorityUserId,"menuId":row.id},
					dataType:"jsonp",
					success:function(data){
						if(data.error==1){
							listAllot.datagrid("reload");
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
    })
    
    $("#allotAdd-add-button").bind('click',function(){
    	var row = list.datagrid("getSelected");
    	var check = listAllotAdd.datagrid("getChecked");
    	if(check==null||check.length==0){
			$.messager.alert("提示", "请至少选择一条记录！", "info");
			return;
		};
    	var paramArr = [];
    	$.each(check,function(index,item){
    		paramArr.push(item.id);
    	});
    	var pa = paramArr.join(",");
    	var authorityUserId = $.cookie("userId");
    	$.ajax({
			url:SiteUrl.System_API+"menu/insertFunMenu.do",
			type:"post",
			data:{"functionIds":pa,"authorityUserId":authorityUserId,"menuId":row.id},
			dataType:"jsonp",
			success:function(data){
				if(data.error==1){
					gridDialogAllotAdd.dialog("close");//如果成功关闭窗口，刷新页面
					listAllot.datagrid("reload");
				}
				else if(data.error==-100){
					$.messager.alert("提示", "会话超时，请重新登陆！", "error");
				}
				else{
					$.messager.alert("提示", data.message, "error");
				}
			}
		});
    });
    
    $("#allotAdd-remove-button").bind('click',function(){
    	gridDialogAllotAdd.dialog('close');
    });
    
    var gridDialogAllotAdd=$("#allot-func-add-gridDialog"); 
    var listAllotAdd=$("#menu-allotAdd-mainGrid");//获取当前grid名称
    //菜单分配功能        
    function  menuAllotAdd(){ 		
    	var row = list.datagrid("getSelected");
    	listAllotAdd.datagrid({
			loader:function(param,success,error){
				param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
				param.id= row.id;
				param.type="out";
				param.systemId=row.systemId;
				$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
					url:SiteUrl.System_API+"menu/queryAllFunMenu.do",
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
			singleSelect:false,
			fit:true,
			fitColumns:true,
			rownumbers:true,
			pagination:true,
			toolbar:'#menu-allotAdd-gridToolbar',//绑定工具栏
			columns:[[//表格绑定字段
			         {field:"ck",checkbox:true},
			         {field:"functionName",title:"功能名称",width:200},
			         {field:"functionDescription",title:"说明",width:200},
			         {field:"type",title:"类型",width:120},
			         {field:"url",title:"url",width:350,align:'left'}, 
			         {field:"group",title:"group",width:120}
			]]
		});
    	var pagerAllotAdd = listAllotAdd.datagrid().datagrid('getPager');
    	
        pagerAllotAdd.pagination({
            layout:['list','sep','first','prev','links','next','last','sep','refresh']
        });
        
    	gridDialogAllotAdd.dialog({
    		title:"分配功能>>"+row.title+">>新增"
    	});
    	gridDialogAllotAdd.dialog('open');
    } 
});