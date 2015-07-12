$(function(){
            //设置分页模式
			var list=$("#systemSwitch-mainGrid");//获取当前grid名称
            var toolbar=$("#systemSwitch-gridToolbar");
            var gridDialog=$("#systemSwitch-dialog");
            var form = $("#systemSwitch-form");

            UI.bindEvents({
        		"add-button":function(){//工具栏新增按钮
        			form.form("clear");//表单清空
        			gridDialog.dialog({
        				title:"新增",
        				buttons:[{
        					text:'创建',
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}
        							
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
        				title:"修改",
        				buttons:[{
        					text:'保存',//保存按钮的事件 
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}       						
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

        				}
        			});
        		},
            	"search-button":function(e){//查询按钮执行
            		e.preventDefault();
            		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台

            		});
            	}
        	},toolbar);
        });