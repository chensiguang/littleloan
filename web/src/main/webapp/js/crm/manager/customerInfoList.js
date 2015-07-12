$(function(){
	var list = $("#customerInfoList-mainGrid");//获取当前grid名称
	var toolbar = $("#customerInfoList-gridToolbar");//获取当前toolbar名称
	var gridDialog=$("#customerInfoList-create-gridDialog");//获取当前dialog名称
	var form = $("#customerInfoList-form");//获取当前form名称
	UI.loadList({
        list:list,
        toolbar:toolbar,
        url:SiteUrl.System_API+"userManagerChangeHistory/page.do",
        /*config:{
        	pagination:false; //分页 默认true，这边可设置 不分页 改成false
        	onDblClickRow：false;//双击 row 时间 是否触发事件 
        },*/
        params:{
        	functionId:1000036
        }
    });
	//加载下拉框
	//ControllerUtil.loadComboBox($("#function-type"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"functionType"});
	//ControllerUtil.loadComboBox($("#function-systemId"), SiteUrl.System_API+"system/getSystemAuthorityListByOrgnizationId.do",{orgnizationId:$.cookie("orgnizationId")});
	//下拉框只读
	/*$(".easyui-combobox").combobox({
		editable:false
	});*/
	/*list.datagrid({
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");
			$.ajax({
				url:SiteUrl.System_API+"/userManagerChangeHistory/page.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						//加载数据
						success(data);
					}else if(data.error==-100){
						$.messager.alert("提示","会话超时，请重新登陆！", "error");
					}else{
						$.messager.alert("提示",data.message, "error");
					}
				}
			});
		},
		onDblClickRow:UI.trigger("edit-button",toolbar),
		singleSelect:true,
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		toolbar:toolbar,
		columns:[[
		          {field:"functionName",title:"客户名称",width:100},
		          {field:"functionDescription",title:"客户类型",width:200},
		          {field:"type",title:"证件类型",width:50},
		          {field:"url",title:"证件号码",width:150},
		          {field:"module",title:"联系电话",width:100},
		          {field:"fgroup",title:"联系地址",width:100},
		          {field:"module",title:"客户评级",width:100},
		          {field:"module",title:"客户经理",width:100}
		          ]]
	});
	var pager = list.datagrid('getPager');
	pager.pagination({
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});*/
	UI.bindEvents({
		"edit-button":function(){
			var row = list.datagrid("getSelected");//获取当前选中的对象
			if(row==null){
				$.messager.alert("提示","请选择一条记录","info");
				return;
			}
			form.form("load",row);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
			gridDialog.dialog({
				title:'编辑功能',
				buttons:[{
					text:'保存',//保存按钮的事件 
					handler:function(){
						if(!form.form("validate")){//控件验证
							return;
						}
						//定义一个CustomerManager对象，更新到数据库
						var obj = new Object();
						obj.authorityUserId = $.cookie("userId");
						/*obj.functionName = $("#function-functionName").val();
						obj.functionDescription = $("#function-functionDescription").val();
						obj.url = $("#function-url").val();
						obj.type = $("#function-type").combobox("getText");
						obj.module = $("#function-module").val();
						obj.fgroup = $("#function-fgroup").val();
						obj.id = $("#function-id").val();
						obj.systemId = $("#function-systemId").combobox("getValue");*/
						$.ajax({
							url:SiteUrl.System_API+"customerManagerChangeHistory/update.do",
							type:'post',
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
									list.datagrid("reload");
								}else if(data.error==-100){
									$.messager.alert("提示","会话超时，请重新登陆！", "error");
								}else{
									$.messager.alert("提示",result.message, "error");
								}
							}
						});
					}
				},{
					text:'取消',//取消按钮的事件
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"search-button":function(e){
			e.preventDefault();
			list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
				customername:$("#customer-search-customername").val(),
				customertype:$("#customer-search-customertype").val(),
				managername:$("#customer-search-managername").val()
    		});
		}
	},toolbar);
});