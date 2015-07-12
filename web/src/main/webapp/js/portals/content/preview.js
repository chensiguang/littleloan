$(function(){
	var previewType = UrlUtil.getQueryString("type");
	var previewId = UrlUtil.getQueryString("id");
	var previewUrl = "";
	var previewFlag = [false,false,false,false];
	if('marquee' == previewType){
		previewUrl = SiteUrl.Portals_API+"marquee/page.do?id="+previewId;
		previewFlag[0] = true;
	}else if('content' == previewType){
		previewUrl = SiteUrl.Portals_API+"content/portal.do?id="+previewId;
		previewFlag[1] = true;
	}else if('knowledge' == previewType){
		previewUrl = SiteUrl.Portals_API+"knowledge/page.do?id="+previewId;
		previewFlag[2] = true;
	}else if('survey' == previewType){
		previewUrl = SiteUrl.Portals_API+"survey/page.do?id="+previewId;
		previewFlag[3] = true;
	}
	//加载图片轮播
	$.ajax({
		type:'POST',
		data:{status:1,page:1,rows:5},
		dataType:'jsonp',
		url:previewFlag[0]?previewUrl:SiteUrl.Portals_API+"marquee/page.do",
		success:function(data){
			if(data.error!=1){
				alert("加载跑马灯信息出错, 请稍后再试!");
			}else{
				var rows=data.rows||[];
				var buf1=[];
				var buf2=[];
				for(var i=0,l=rows.length;i<l;i++){
					var row=rows[i];
					buf1.push('<li>');
					buf1.push('<img src="'+'http://localhost:8081/pettyLoan-web/images/default/banner.jpg'+'" alt="">');
					buf1.push('<p class="img-title">'+row.title||'aaaaaa'+'</p>');
					buf1.push('</li>');
					buf2.push('<li>');
					buf2.push(i);
					buf2.push('</li>');
				}
				$("#marqueeBanner .bd ul").html(buf1.join(''));
				$("#marqueeBanner .hd ul").html(buf2.join(''));
				$('#marqueeBanner').slide({
					titCell:".hd ul",
					mainCell:".bd ul",
					effect:"left",
					autoPlay:true,
					autoPage:true,
					trigger:"mouseover"
				});
			}
		}
	});
	//加载内容
	$.ajax({
		type:'POST',
		dataType:'jsonp',
		url:previewFlag[1]?previewUrl:SiteUrl.Portals_API+"content/portal.do",
		success:function(data){
			if(data.error!=1){
				alert("加载分类内容出错, 请稍后再试!");
			}else{
				var rows=data.rows||[];
				var buf=[];
				for(var i=0,l=rows.length;i<l;i++){
					var row=rows[i];
					var cn="";
					if((i+1)/3==0) cn=" nmr";
					buf.push('<div class="list-box'+cn+'">');
					buf.push('<div class="list-box-title"><span>'+row.name+'</span></div>');
					buf.push('<div class="list-box-ul"><ul>');
					var list=row.list||[];
					for(var j=0,ll=list.length;j<ll;j++){
						var item=list[j];
						buf.push('<li><a href="'+SiteUrl.Portals_Web+"html/portals/content/detail.html"+'?id='+item.id+'" target="_blank">'+item.title+'</a></li>');
					}
					buf.push('</ul></div><div class="list-box-bottom">');
					buf.push('<a href="?id='+row.id+'" target="_blank">查看更多</a>');
					buf.push('</div></div>');
				}
				var html=buf.join('');
				$("#portalContentDiv").html(html);
			}
		}
	});
	//加载知识库
	$.ajax({
		type:'POST',
		data:{status:3,isall:1,page:1,rows:5},
		dataType:'jsonp',
		url:previewFlag[2]?previewUrl:SiteUrl.Portals_API+"knowledge/page.do",
		success:function(data){
			if(data.error!=1){
				alert("加载知识库出错, 请稍后再试!");
			}else{
				//alert("动态展现知识库");
			}
		}
	});
	//加载问卷调查
	$.ajax({
		type:'POST',
		data:{status:3,isall:1,page:1,rows:5},
		dataType:'jsonp',
		url:previewFlag[3]?previewUrl:SiteUrl.Portals_API+"survey/page.do",
		success:function(data){
			if(data.error!=1){
				alert("加载问卷调查出错, 请稍后再试!");
			}else{
				//alert("动态展现问卷调查");
			}
		}
	});
});