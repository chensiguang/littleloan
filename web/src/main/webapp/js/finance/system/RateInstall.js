$(function(){
            //设置分页模式
			var list=$("#RateInstall-mainGrid");//获取当前grid名称
            var toolbar=$("#RateInstall-gridToolbar");
            var gridDialog=$("#RateInstall-dialog");
            var form = $("#RateInstall-form");
            
            list.datagrid({
        		loader:function(param,success,error){
        			param.authorityUserId = $.cookie("userId");
        			$.ajax({
        				url:SiteUrl.Finance_API+"systemRate/page.do",
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
        		onDblClickRow:UI.trigger("edit-button",toolbar),
        		singleSelect:true,
        		fit:true,
        		fitColumns:true,
        		rownumbers:true,
        		pagination:true,
        		toolbar:toolbar,
        		columns:[[
        		          {field:"rateCode",title:"系统费率代码",width:150},
        		          {field:"rateName",title:"费率名称",width:100},
        		          {field:"initRate",title:"费率值",width:150}
        		          ]]
        	});
        	var pager = list.datagrid('getPager');
        	pager.pagination({
        		layout:['list','sep','first','prev','links','next','last','sep','refresh']
        	});
        	
        	
            UI.bindEvents({
            	"add-button":function(){//工具栏新增按钮
        			form.form("clear");//表单清空
        			gridDialog.dialog({
        				title:"新增系统费率",
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
        				title:"修改系统费率",
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
        		}
        	},toolbar);
        });