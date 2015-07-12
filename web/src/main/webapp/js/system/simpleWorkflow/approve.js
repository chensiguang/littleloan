$(function(){
            //设置分页模式
			var list=$("#simpleWorkflow-mainGrid");//获取当前grid名称
			var toolbar=$("#simpleWorkflow-gridToolbar");
            var gridDialog=$("#simpleWorkflow-create-gridDialog");
			list.datagrid({
				loader:function(param,success,error){
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					param.dealUser = $.cookie("userId");
					param.status = $("#simpleWorkflow-status-search").val();
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url:SiteUrl.System_API+"simpleWorkflow/getWorkflowExecuteByUserId.do",
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
				singleSelect:true,
				fit:true,
				fitColumns:true,
				rownumbers:true,
				pagination:true,
				toolbar:'#simpleWorkflow-gridToolbar',//绑定工具栏
				onDblClickRow:UI.trigger("add-button",toolbar),
				columns:[[//表格绑定字段
				         {field:"workflowDescription",title:"工作名称",width:150},
				         {field:"description",title:"工作说明",width:350}
				]]
			});
            var pager=list.datagrid('getPager');
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });

            
            var form = $("#simpleWorkflow-form");
            UI.bindEvents({
        		"add-button":function(){//工具栏新增按钮
        			var row = list.datagrid("getSelected");//获取当前选中的flexkey对象
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
//        			form.form("load",row);
        			$("#simpleWorkflow-id").val(row.id);
        			$("#simpleWorkflow-workflowDescription").html(row.workflowDescription);
        			$("#simpleWorkflow-description").html(row.description);
        			gridDialog.dialog({
        				title:"审批工作",
        				buttons:[{
        					text:'审批',
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}
        						
        						//定义一个 flexkey对象，赋值，调用ajax新增数据
        						var obj = new Object();
        						obj.id = row.id;
        						obj.status = $("#simpleWorkflow-status").val();
        						obj.approveDescription = $("#simpleWorkflow-approveDescription").val();
        						obj.approveUserId = $.cookie("userId");
        						obj.authorityUserId = $.cookie("userId");
        						$.ajax({
        							url:SiteUrl.System_API+"simpleWorkflow/approveWorkflowExecute.do",
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
            	"search-button":function(e){//查询按钮执行
            		e.preventDefault();
            		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
            			flexkey:$("#flexkey-name").val()
            		});
            	}
        	},toolbar);
        });