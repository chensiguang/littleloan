$(function(){
	SystemAuthority = {
		loadData : function(){
			if($("#systemAuthority_orginizationId").val()==""){
				$("#systemAuthority_orginizationId").val($.cookie("orgnizationId"));
			}
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"system/getSystemAuthorityListByOrgnizationId.do",
				type:"post",
				data:{orgnizationId:$("#systemAuthority_orginizationId").val(), authorityUserId:$.cookie("userId")},
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						$.each(data.rows, function(index, obj){
							$("#systemAuthorityDateBox-"+ obj.systemId).datebox("setValue", obj.validateDate);
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
		}
	};
	
	SystemAuthority.loadData();
	
	$("#btnSystemAuthoritySave").click(function(){
		var list = new Array();
		$(".item").each(function(){
			var obj = new Object();
			obj.orgnizationId = $("#systemAuthority_orginizationId").val();
			obj.systemId = $(this).find(".systemNameItem").attr("systemId");
			obj.validateDate = $(this).find(":input").datebox('getValue');
			list.push(obj);
		});
		$.ajax({
			url:SiteUrl.System_API+"system/setSystemAuthority.do",
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