$(function(){
            //设置分页模式
			var list=$("#endPeriodCutoff-mainGrid");//获取当前grid名称
            var toolbar=$("#endPeriodCutoff-gridToolbar");
            UI.bindEvents({
            	"add-button":function(e){//查询按钮执行
            		e.preventDefault();
        
            	}
        	},toolbar);
        });