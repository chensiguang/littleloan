$(function(){
	$("#contentPortalPath").html(" > "+UrlUtil.getQueryString("name"));
	//加载内容列表
	$.ajax({
		type:'POST',
		data:{id:UrlUtil.getQueryString("id")},
		dataType:'jsonp',
		url:SiteUrl.Portals_API+"content/getone.do",
		success:function(data){
			if(data.error!=1){
				alert("加载分类内容出错, 请稍后再试!");
			}else{
				var rows=data.rows||{};
				var buf=[];
				buf.push('<div style="text-align:center">');
				buf.push('<h1>'+rows.title+'</h1>');
				buf.push('<p>&nbsp;</p>');
				buf.push('<p>'+rows.content+'</p>');
				buf.push('</div>');
				var html=buf.join('');
				$("#contentDetail").html(html);
			}
		}
	});
	//加载内容
	$.ajax({
		type:'POST',
		data:{id:UrlUtil.getQueryString("id")},
		dataType:'jsonp',
		url:SiteUrl.Portals_API+"content/getone.do",
		success:function(data){
			if(data.error!=1){
				alert("加载分类内容出错, 请稍后再试!");
			}else{
				var rows=data.rows||{};
				var buf=[];
				buf.push('<div style="text-align:center">');
				buf.push('<h1>'+rows.title+'</h1>');
				buf.push('<p>&nbsp;</p>');
				buf.push('<p>'+rows.content+'</p>');
				buf.push('</div>');
				var html=buf.join('');
				$("#contentDetail").html(html);
			}
		}
	});
});