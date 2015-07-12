$(function(){
	$.ajax({
		url:SiteUrl.System_API+"simpleWorkflow/getListByOrgnizationId.do",
		type:"POST",
		dataType: "jsonp",
		jsonp:"callback",
		data:{orgnizationId:$.cookie("orgnizationId"),authorityUserId:$.cookie("userId")},
		success:function(data){
			if(data.error==1){
				$("#simpleWorkflowBox").empty();
				$.each(data.rows, function(index, obj){
					var inputName = "simpleWorkflow"+obj.workflowName+"Select";
					var html = 
					'<div class="item">'+
						'<div class="checkItem"><input type="checkbox" id="'+obj.workflowName+'" name="'+obj.workflowDescription+'" value="'+obj.result+'" />'+obj.workflowDescription+'</div>'+
						'<div class="roleItem" showable="'+obj.result+'">请选择处理角色：<input id="'+inputName+'" class="easyui-combobox" style="width:200px;" /></div>'+
						'<div style="clear:both;"></div>'+
					'</div>';					
					$("#simpleWorkflowBox").append(html);
					var params = [];
					if(obj.params != ""){
						params = obj.params.split(",");
					}
					RoleSelector.load(inputName,{multiple: true}, {orgnizationId:$.cookie("orgnizationId"), authorityUserId: $.cookie("userId")}, params);
				});
				$(":checkbox").each(function(){
					if($(this).val()=="yes"){
						$(this).attr("checked",true);
					}
				});
				$(".roleItem").each(function(){
					if($(this).attr("showable")!="yes"){
						$(this).hide();
					}
				});
				$(":checkbox").click(function(){
					var v = $(this).val();
					if(v=="yes"){
//						$(this).attr("checked", false);
						$(this).val("no");
						var roleItem = $(this).parent().next();
						roleItem.hide();
						roleItem.attr("showable", "no");				
					}
					else{
//						$(this).attr("checked", true);
						$(this).val("yes");
						var roleItem = $(this).parent().next();
						roleItem.show();
						roleItem.attr("showable", "yes");
					}
				});
			}
			else if(data.error==-100){
				$.messager.alert("提示", "会话超时，请重新登陆！", "error");
			}
			else{
				$.messager.alert("提示", data.message, "error");
			}
		}
	});
	
	$("#btnSimpleWorkflowSave").click(function(){
		var list = new Array();
		$("#simpleWorkflowBox .item").each(function(){
			var obj = new Object();
			obj.workflowName = $(this).find(":checkbox").attr("id");
			obj.workflowDescription = $(this).find(":checkbox").attr("name");
			obj.orgnizationId = $.cookie("orgnizationId");
			obj.result = $(this).find(":checkbox").val();
			if(obj.result=="yes"){
//				obj.params = $(this).find(":input").combobox("getValues");
				obj.params = RoleSelector.getSelected("simpleWorkflow"+obj.workflowName+"Select").join(",");
			}
			else{
				obj.params = "";
			}
			
			list.push(obj);
		});
		$.ajax({
			url:SiteUrl.System_API+"simpleWorkflow/setSimpleWorkflowConfig.do",
			type:"POST",
			dataType: "jsonp",
			jsonp:"callback",
			data:{configListJson:JSON.stringify(list), authorityUserId:$.cookie("userId")},
			success:function(data){
				if(data.error==1){
					$.messager.alert("提示", "保存成功！！", "info");
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
});