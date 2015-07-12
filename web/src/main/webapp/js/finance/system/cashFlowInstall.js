$(function(){
            //设置分页模式
		var list=$("#cashFlowInstall-mainGrid");//获取当前grid名称
        var toolbar=$("#cashFlowInstall-gridToolbar");
        var gridDialog=$("#cashFlowInstall-dialog");
        var form = $("#cashFlowInstall-form");
        var parentName = $("#cashFlowInstall-parentName");
         var data=[			 {
         	"id":1,
        	"name":"经营活动产生的现金流量",
        	"text":"经营活动产生的现金流量",
        	"code":"11",
        	"status":"其他",
        	"children":[
        		{
            		"id":2,
            		"name":"经营活动现金流入",
            		"text":"经营活动现金流入",
            		"code":"1101",
            		"status":"流入",
            		"children":[{
            			"id":21,
            			"name":"同业拆入_拆出资金净额",
            			"text":"同业拆入_拆出资金净额",
            			"code":"110101",
            			"status":"流入"
            		}]
            	},{
            		"id":3,
            		"name":"经营活动现金流出",
            		"text":"经营活动现金流出",
            		"code":"1102",
            		"status":"流出",
            		"children":[
	            		{
	            			"id":31,
	                		"name":"客户贷款净增加额11",
	                		"text":"客户贷款净增加额11",
	                		"code":"110201",
	                		"status":"流出"
	            		},{
	            			"id":32,
	                		"name":"支付的各项税费",
	                		"text":"支付的各项税费",
	                		"code":"110202",
	                		"status":"流出"
	            		},{
	            			"id":33,
	                		"name":"支付的租金",
	                		"text":"支付的租金",
	                		"code":"110203",
	                		"status":"流出"
	            		}]
            	}]	
}];   
	     list.treegrid({
	    	data:data,
			toolbar: toolbar,
			method:"get",
		    idField:"id",
		    treeField:"name",
		    fitColumns:true,
		    rownumbers: true,
			columns:[
			         [{title:"类别名称",field:"name",width:100,align:"left"},
			          {title:"类别编码",field:"code",width:100},
			          {title:"标志",field:"status",width:100}]]
		  });
	     parentName.combotree({data:data,width:150});
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
    		}
    	},toolbar);
});