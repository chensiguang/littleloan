ControllerUtil = {
		/*
		 * 加载combobox
		 */
		loadComboBox:function(comboBox, jsonUrl, param){
			param.authorityUserId = $.cookie("userId");
			$.ajax({
	    		url:jsonUrl,
	    		type:"post",
	    		data:param,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				comboBox.combobox("loadData", data.rows);
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		}
};