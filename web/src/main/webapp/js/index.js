$(function(){
	$.ajax({
		type:'POST',
		data:{"systemId":UrlUtil.getQueryString("systemId"),"authorityUserId":$.cookie("userId")},
		dataType:'jsonp',
		url:SiteUrl.System_API+"menu/getMenuList.do",
		success:function(data){
			if(data.error!=1){
				UI.alert("加载菜单发生错误");
			}else{
				buildMenu(data);
			}
		}
	});
	
	$.ajax({
		type:'POST',
		data:{userId:$.cookie("userId"), authorityUserId: $.cookie("userId")},
		dataType:'jsonp',
		url:SiteUrl.System_API+"system/getAuthoritySystemByUserId.do",
		success:function(data){
			if(data.error!=1){
				UI.alert("加载菜单发生错误");
			}else{
				var ul = $(".header-menu").children("ul");
				ul.empty();
				var s = '<li><a href="index.html" class="selected">工作台</a></li>';
				$.each(data.rows, function(index, obj){
					s += '<li><a href="index.html?systemId='+obj.id+'">'+obj.systemName+'</a></li>';
				});
				ul.append(s);
				
				
				if(UrlUtil.getQueryString("systemId")!=null){
					ul.find(".selected").removeClass("selected");
					ul.find("[href$='systemId="+UrlUtil.getQueryString("systemId")+"']").addClass("selected");
				}
			}
		}
	});

	function buildMenu(data){
		var indexMenu=$("#index-menu");
		var buf=[];
		var rows=data.rows;
		for(var i=0,l=rows.length;i<l;i++){
			var row=rows[i];
			buf.push('<div title="'+row.title+'" data-options="selected:true">');
			buf.push('<ul class="left-menu">');
			for(var j=0,ll=row.functionList.length;j<ll;j++){
				var func=row.functionList[j];
				buf.push('<li><a class="page-menu-item" href="#" data-href="'+func.url+'" data-func-id="'+func.id+'">'+func.title+'</a></li>');
			}
			buf.push('</ul></div>');
		}
		indexMenu.html(buf.join(''));
		indexMenu.accordion({
			fit:true,
			border:false
		});
		UI.initMenu();
	}
});