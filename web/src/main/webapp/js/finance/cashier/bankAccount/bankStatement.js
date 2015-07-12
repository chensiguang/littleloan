$(function(){
            //设置分页模式
			var list=$("#bankStatement-mainGrid");//获取当前grid名称
            var toolbar=$("#bankStatement-gridToolbar");
            UI.bindEvents({
            	"search-button":function(e){//查询按钮执行
            		e.preventDefault();
            		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台

            		});
            	}
        	},toolbar);
        });