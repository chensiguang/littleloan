$(function(){
	var list = $("#entityArchives-mainGrid");
	var toolbar = $("#entityArchives-gridToolbar");
	var gridDialog=$("#entityArchives-create-gridDialog");
	var form = $("#entityArchives-form");
	//加载下拉框     
	ControllerUtil.loadComboBox($("#entityArchives-arcCategoryName"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"entityArchivesCategoryName"});
	ControllerUtil.loadComboBox($("#entityArchives-search-arcCategoryName"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"entityArchivesCategoryName"});
	ControllerUtil.loadComboBox($("#entityArchives-search-lendStatusName"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"entityArchivesLendStatusName"});
	
	//下拉框只读
	$(".easyui-combobox").combobox({
		editable:false
	});
	list.datagrid({
		loader:function(param,success,error){
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"entityArchives/page.do",
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
		
		singleSelect:true,
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		onDblClickRow:UI.trigger("edit-button",toolbar),
		toolbar:'#entityArchives-gridToolbar',
		columns:[[//表格绑定字段
		          {field:"arcName",title:"档案名称",width:100},
		          {field:"arcNo",title:"档案编号",width:100},
		          {field:"arcCategoryName",title:"档案类别",width:100},
		          {field:"lendStatusName",title:"档案状态",width:100},
		          {field:"arcLocation",title:"档案位置",width:100},
		          {field:"arcNoElec",title:"电子档案编号",width:100},
		          {field:"insUser",title:"录入者",width:100},
		          {field:"insDtime",title:"录入时间",width:100},
		          {field:"updUser",title:"变更者",width:100},
		          {field:"updDtime",title:"变更时间",width:100}
		          ]]
	});
	
	
	var pager = list.datagrid('getPager');
	pager.pagination({
		layout:['list','sep','first','prev','links','next','last','sep','refresh']
	});
	
	
	UI.bindEvents({
		"add-button":function(){//工具栏新增按钮
			form.form("clear");//表单清空
			gridDialog.dialog({
				title:"实体档案录入",
				buttons:[{
					text:"录入",
					handler:function(){
						if(!form.form("validate")){//控件验证
							return;
						}
						
						var obj = new Object();
						//???????
						obj.authorityUserId = $.cookie("userId");
						obj.arcName = $("#entityArchives-arcName").val();
						obj.arcNo = $("#entityArchives-arcNo").val();
						obj.arcCategoryName = $("#entityArchives-arcCategoryName").combobox("getText");
					
						obj.arcLocation = $("#entityArchives-arcLocation").val();
						obj.arcNoElec = $("#entityArchives-arcNoElec").val();
						obj.insUser = $("#entityArchives-insUser").val();
						obj.insDtime = $("#entityArchives-insDtime").val();
						
						
						$.ajax({
							url:SiteUrl.System_API+"entityArchives/insert.do",
							type:"post",
							data:obj,
							dataType:"jsonp",
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示",result.message, "info");
									gridDialog.dialog("close");
									list.resultgrid("reload");
								}else if(result.error==-100){
									$.messager.alert("提示","会话超时，请重新登陆！", "error");
								}else{
									$.messager.alert("提示",result.message, "error");
								}
							}
						});
					}
				},{
					text:'取消',
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"edit-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示","请选择一条记录","info");
				return;
			}
			form.form("load",row);
			gridDialog.dialog({
				title:'实体档案变更',
				buttons:[{
					text:'保存',
					handler:function(){
						if(!form.form("validate")){
							return;
						}
						var obj = new Object();
						
					    //?????
						obj.authorityUserId = $.cookie("userId");
						obj.arcName = $("#entityArchives-arcName").val();
						obj.arcNo = $("#entityArchives-arcNo").val();
						obj.arcCategoryName = $("#entityArchives-arcCategoryName").combobox("getText");
						obj.lendStatusName = $("#entityArchives-lendStatusName").combobox("getValue");
						obj.arcLocation = $("#entityArchives-arcLocation").val();
						obj.arcNoElec = $("#entityArchives-arcNoElec").val();
						obj.insUser = $("#entityArchives-insUser").val();
						obj.insDtime = $("#entityArchives-insDtime").val();
						obj.updUser = $("#entityArchives-updUser").val();
						obj.updDtime = $("#entityArchives-updDtime").val();
						
						$.ajax({
							url:SiteUrl.System_API+"entityArchives/update.do",
							type:'post',
							data:obj,
							dataType:'jsonp',
							success:function(result){
								if(result.error==1){
									$.messager.alert("提示",result.message, "info");
									gridDialog.dialog("close");
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
					text:'取消',
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
		"remove-button":function(){
			var row = list.datagrid("getSelected");
			if(row==null){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
			row.authorityUserId = $.cookie("userId");
			$.messager.confirm("操作提示","确认删除？",function(result){
				if(result){
					$.ajax({
						url:SiteUrl.System_API+"entityArchives/delete.do",
						type:"post",
						data:row,
						dataType:"jsonp",
						success:function(result){
							if(result.error==1){
								$.messager.alert("提示",result.message, "info");
								list.datagrid("reload");
							}
							else if(result.error==-100){
								$.messager.alert("提示", "会话超时，请重新登陆！", "error");
							}
							else{
								$.messager.alert("提示", result.message, "error");
							}
						}
					}); 
				}
			});
		},
		"search-button":function(e){
			e.preventDefault();
			list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    			arcName:$("#entityArchives-search-arcName").val(),
    			arcNo:$("#entityArchives-search-arcNo").val(),
    			arcCategoryId:$("#entityArchives-search-arcCategoryName").val(),
    			lendStatusCode:$("#entityArchives-search-lendStatusName").val()
    		});
		}
	},toolbar);
});