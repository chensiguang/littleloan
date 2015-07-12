$(function(){
	System4orgAuthority = {
		loadData : function(){
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"system/getSystemAuthorityListByOrgnizationId.do",
				type:"post",
				data:{orgnizationId:$("#systemAuthority4org_orginizationId").val(), authorityUserId:$.cookie("userId")},
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						$.each(data.rows, function(index, obj){
							$("#systemAuthority4orgDateBox-"+ obj.systemId).datebox("setValue", obj.validateDate);
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
	
	System4orgAuthority.loadData();
	
	$("#btnSystemAuthority4orgSave").click(function(){
		var list = new Array();
		$(".item4org").each(function(){
			var obj = new Object();
			obj.orgnizationId = $("#systemAuthority4org_orginizationId").val();
			obj.systemId = $(this).find(".systemName4orgItem").attr("systemId");
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