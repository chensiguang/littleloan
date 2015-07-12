$(function(){
	var list = $("#function-mainGrid");
	var toolbar = $("#function-gridToolbar");
	var gridDialog=$("#function-create-gridDialog");
	var form = $("#function-form");
	
	UI.bindEvents({
		"add-button":function(){
			gridDialog.dialog({
				title:"添加功能",
				buttons:[{
					text:"创建",
					handler:function(){}
				},{
					text:'取消',
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"edit-button":function(){},
		"remove-button":function(){}
	},toolbar);
});