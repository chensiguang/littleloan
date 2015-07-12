$(function(){
	var grid=$("#mainGrid");
	var toolbar=$("#gridToolbar");
	var gridDialog=$("#gridDialog");

	UI.bindEvents({
		"add-button":function(){
			gridDialog.dialog({
				title:"创建数据",
				buttons:[{
					text:'创建',
					handler:function(){

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
			gridDialog.dialog({
				title:"编辑数据",
				buttons:[{
					text:'保存',
					handler:function(){

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
		"remove-button":function(){
			alert('3');
		}
	},toolbar);

});

	/*function show(){
		$("#loading").fadeOut("normal", function(){
        	$(this).remove();
    	});
	}
	var delayTime;
	$.parser.onComplete = function(){
	    if(delayTime)
	        clearTimeout(delayTime);
 	    delayTime = setTimeout(show,100);
 	}*/