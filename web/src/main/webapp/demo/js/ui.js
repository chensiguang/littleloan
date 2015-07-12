/*
	通用函数封装
*/
var UI=(function(w,d){
	function setTab(item,tabs){
		var href=item.attr("data-href");
        var title=item.html();
        if(!tabs.tabs('exists', title)){
            tabs.tabs('add',{
                title: title,
                href: href,
                closable: true,
                fit:true,
                style:{
                	padding:10,
                	position:"relative"
                }
            });
        }
        else{
            tabs.tabs('select', title);
        }
	}
	var body=$(d.body);

	var ui={
		alert:function(msg,title){
			title=title||"提示";
			$.messager.alert(title,msg);
		},
		confirm:function(msg,callback,title){
			title=title||"提示";
			$.messager.confirm(title, msg, function (r) {
				if (r) {
					if (jQuery.isFunction(callback))
						callback.call();
				}
			});
		},
		message:function(msg,title){
			title=title||"提示";
			$.messager.show({
				title:title,
				msg:msg,
				showType:"show"
			});
		},
		//左侧菜单点击 右侧创建一个tab
		initMenu:function(){
			var menuItems=$(".page-menu-item");
			var pageTabs=$("#page-tabs");
			menuItems.on("click",function(e){
				e.preventDefault();
				setTab($(this),pageTabs);
			});
		},
		//设置列表的默认分页模式
		setPageMode:function(selector){
			var pager=$(selector).datagrid('getPager');
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });
		},
		//给区域内的节点绑定事件 默认处理click事件
		bindEvents:function(config,context){
			context=context||body;
			for(var name in config){
				var selector=name;
				if(selector.substring(0,1)!=="."&&selector.substring(0,1)!=="#") selector="."+selector;
				var el=context.find(selector);
				var callback=config[name];
				el.on("click",callback);
			}
		}
	};
	return ui;
})(window,document);

$(function(){
	UI.initMenu();
});