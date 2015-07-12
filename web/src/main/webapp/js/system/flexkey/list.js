$(function(){
            //设置分页模式
			var list=$("#flexkey-mainGrid");//获取当前grid名称
            var toolbar=$("#flexkey-gridToolbar");
            var gridDialog=$("#flexkey-create-gridDialog");
            var form = $("#flexkey-form");
            UI.loadList({
                list:list,
                toolbar:toolbar,
                url:SiteUrl.System_API+"flexkey/page.do",
                /*config:{
                	pagination:false; //分页 默认true，这边可设置 不分页 改成false
                	onDblClickRow：false;//双击 row 时间 是否触发事件 
                },*/
                params:{
                	functionId:7
                }
            });
//            var listConfig; //设置 列配置 的 全局变量
//            var listParam = UI.getFunctionParam(list);
//            UI.loadList(listParam,loadData); //进入 获取页的 列取得 loadData 是方法回调函数         
//            function loadData(config){	
//				listConfig=config;// 将取得的列配置 付给 全局变量
//				var pageConfig = UI.getColumnsConfig(config).pagePation;//取得 分页配置 
//				list.datagrid({
//					loader:function(param,success,error){
//						param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
//						$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
//							url:SiteUrl.System_API+"flexkey/page.do",
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
//					fitColumns:true,
//					rownumbers:true,
//					pagination:true,
//					pageSize:pageConfig.pageSize,
//					onDblClickRow:UI.trigger("edit-button",toolbar),
//					toolbar:'#flexkey-gridToolbar',//绑定工具栏
//					columns:[UI.getColumnsConfig(config).cfg]
//				});
//				UI.setPageModeSave(list,listParam,pageConfig); //分页 点击切换分页条数并保存
//			}
            
//			list.datagrid({
//				loader:function(param,success,error){
//					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
//					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
//						url:SiteUrl.System_API+"flexkey/page.do",
//						type:"post",
//						data:param,
//						dataType:"jsonp",
//						success:function(data){
//							if(data.error==1){
//								success(data);//加载数据
//							}
//							else if(data.error==-100){
//								$.messager.alert("提示", "会话超时，请重新登陆！", "error");
//							}
//							else{
//								$.messager.alert("提示", data.message, "error");
//							}
//							
//						}
//					});
//				},
//				singleSelect:true,
//				fit:true,
//				fitColumns:true,
//				rownumbers:true,
//				pagination:true,
//				onDblClickRow:UI.trigger("edit-button",toolbar),
//				toolbar:'#flexkey-gridToolbar',//绑定工具栏
//				columns:[[//表格绑定字段
//				         {field:"flexkey",title:"值集名称",width:150},
//				         {field:"keyDescription",title:"值集说明",width:150},
//				         {field:"flexvalue",title:"值的名称",width:150},
//				         {field:"valueDescription",title:"值的说明",width:150}
//				]]
//			});
//            var pager=list.datagrid('getPager');
//            pager.pagination({
//                layout:['list','sep','first','prev','links','next','last','sep','refresh']
//            });

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
        						
        						//定义一个 flexkey对象，赋值，调用ajax新增数据
        						var obj = new Object();
        						obj.authorityUserId = $.cookie("userId");
        						obj.flexkey = $("#flexkey-flexkey").val();
        						obj.keyDescription = $("#flexkey-keyDescription").val();
        						obj.flexvalue = $("#flexkey-flexvalue").val();
        						obj.valueDescription = $("#flexkey-valueDescription").val();
        						$.ajax({
        							url:SiteUrl.System_API+"flexkey/insert.do",
        							type:"post",
        							data:obj,
        							dataType:"jsonp",
        							success:function(data){
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
        						obj.id = $("#flexkey-id").val();
        						obj.flexkey = $("#flexkey-flexkey").val();
        						obj.keyDescription = $("#flexkey-keyDescription").val();
        						obj.flexvalue = $("#flexkey-flexvalue").val();
        						obj.valueDescription = $("#flexkey-valueDescription").val();
        						$.ajax({
        							url:SiteUrl.System_API+"flexkey/update.do",
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
    							url:SiteUrl.System_API+"flexkey/delete.do",
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
            			flexkey:$("#flexkey-search-name").val(),
            			keyDescription:$("#flexkey-search-description").val()
            		});
            	}/*,
            	"setting-button":function(e){
            		//重新组装弹窗内容
            		UI.showListConfig(listConfig,listParam,loadData);
            	}*/
        	},toolbar);
        });