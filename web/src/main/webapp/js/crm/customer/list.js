$(function(){
	//加载下拉框
//	ControllerUtil.loadComboBox($("#userinfo-status"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"userStatus"});
    //设置分页模式
	var list=$("#customer-mainGrid");//获取当前grid名称
	var toolbar=$("#customer-gridToolbar");
    var gridDialog=$("#customer-create-gridDialog");
	list.datagrid({
		loader:function(param,success,error){
			
//			if($("#userinfo-orgnizationId").val()==""){
//				$("#userinfo-orgnizationId").val($.cookie("orgnizationId"));
//			}
			//因为需要先设置#userinfo-orgnizationId的值，再加载控件
//			DepartmentSelector2.load("userinfo-departmentId",{multiple: false}, {orgnizationId:$("#userinfo-orgnizationId").val(), authorityUserId: $.cookie("userId")}, []);
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
//			param.orgnizationId = $("#userinfo-orgnizationId").val();
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"/customerInfo/page.do",
				type:"post",
				data:param,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						success(data);//加载数据
					}
					else if(data.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					}
					else{
						$.messager.alert("提示", data.message, "error");
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
		toolbar:'#customer-gridToolbar',//绑定工具栏		
		columns:[[//表格绑定字段
		         {field:"customerName",title:"客户名称",width:100},
		         {field:"customerType",title:"客户类型",width:100},
		         {field:"isOld",title:"是否是新客户",width:150},
		         {field:"certificateType",title:"证件类型",width:100},
		         {field:"certificateNo",title:"证件号码",width:100},
		         {field:"telephone",title:"联系电话",width:100}
		         {field:"mobile",title:"手机号码",width:100},
		         {field:"address",title:"联系地址",width:100},
		         {field:"ctime",title:"创建时间",width:150},
		         {
		 			field : "others",
		 			title : "操作",
		 			width : 30,
		 			formatter:function(value,row,index){
		 				return "<a style='text-decoration:none;color:blue;cursor:pointer'>授信明细</a>";
		 			}
		 		}
		]]
	});
    var pager=list.datagrid('getPager');
    pager.pagination({
        layout:['list','sep','first','prev','links','next','last','sep','refresh']
    });

    
    var form = $("#customer-form");
    UI.bindEvents({
		"add-button":function(){//工具栏新增按钮
			form.form("clear");//表单清空
			gridDialog.dialog({
				title:"创建数据",
				buttons:[{
					text:'创建',
					handler:function(){
						if(!form.form("validate")){//控件验证
							return;
						}
						
						//定义一个 flexkey对象，赋值，调用ajax新增数据
						var obj = new Object();
						obj.authorityUserId = $.cookie("userId");
						obj.username = $("#userinfo-username").val();
						obj.password = $("#userinfo-password").val();
						obj.realname = $("#userinfo-realname").val();
						obj.email = $("#userinfo-email").val();
						obj.telephone = $("#userinfo-telephone").val();
						obj.departmentId = DepartmentSelector2.getSelectedItems("userinfo-departmentId").id;
						obj.orgnizationId = $("#userinfo-orgnizationId").val();
						obj.status = $("#userinfo-status").combobox("getValue");
						obj.departmentName = DepartmentSelector2.getSelectedItems("userinfo-departmentId").departmentName;
						$.ajax({
							url:SiteUrl.System_API+"customerInfo/put.do",
							type:"post",
							data:obj
							dataType:"jsonp",
							success:function(data){
								if(data.error==1){
									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
									list.datagrid("reload");//刷新页面
								}
								else if(data.error==-10){
									gridDialog.dialog("close");
									$.messager.alert("提示", data.message, "info");
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
			var row = list.datagrid("getSelected");//获取当前选中的flexkey对象
			if(row==null){
				$.messager.alert("提示", "请选择一条记录！", "info");
				return;
			}
//			form.form("load",row);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
			$("#userinfo-id").val(row.id);
			$("#userinfo-username").val(row.username);
			$("#userinfo-password").val(row.password);
			$("#userinfo-realname").val(row.realname);
			$("#userinfo-email").val(row.email);
			$("#userinfo-telephone").val(row.telephone);
			$("#userinfo-departmentId").combotree('setValue',row.departmentId);
			$("#userinfo-orgnizationId").val(row.orgnizationId);
			$("#userinfo-status").combobox('setValue', row.status);
			gridDialog.dialog({
				title:"编辑数据",
				buttons:[{
					text:'保存',//保存按钮的事件 
					handler:function(){
						if(!form.form("validate")){//控件验证
							return;
						}
						
						//定义一个 flexkey对象，更新到数据库
						var obj = new Object();
						obj.id = $("#userinfo-id").val();
						obj.username = $("#userinfo-username").val();
						obj.password = $("#userinfo-password").val();
						obj.realname = $("#userinfo-realname").val();
						obj.email = $("#userinfo-email").val();
						obj.telephone = $("#userinfo-telephone").val();
						obj.departmentId = DepartmentSelector2.getSelectedItems("userinfo-departmentId").id;
						obj.orgnizationId = $("#userinfo-orgnizationId").val();
						obj.status = $("#userinfo-status").combobox("getValue");
						obj.authorityUserId = $.cookie("userId");
						$.ajax({
							url:SiteUrl.System_API+"customerInfo/edit.do",
							type:"post",
							data:obj,
							dataType:"jsonp",
							success:function(data){
								if(data.error==1){
									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
									list.datagrid("reload");
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
				},{
					text:'取消',//取消按钮的事件
					handler:function(){
						gridDialog.dialog("close");
					}
				}]
			});
			gridDialog.dialog("open");
		},
//		"remove-button":function(){
//			var row = list.datagrid("getSelected");
//			if(row==null){
//				$.messager.alert("提示", "请选择一条记录！", "info");
//				return;
//			}
//			row.authorityUserId = $.cookie("userId");
//			$.messager.confirm("操作提示","确认删除？",function(result){
//				if(result){
//					$.ajax({
//						url:SiteUrl.System_API+"user/delete.do",
//						type:"post",
//						data:row,
//						dataType:"jsonp",
//						success:function(data){
//							if(data.error==1){
//								list.datagrid("reload");
//							}
//							else if(data.error==-100){
//								$.messager.alert("提示", "会话超时，请重新登陆！", "error");
//							}
//							else{
//								$.messager.alert("提示", data.message, "error");
//							}
//						}
//					}); 
//				}
//			});
//		},
		"search-button":function(e){//查询按钮执行
    		e.preventDefault();
    		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    			username:$("#userinfo-search-username").val(),
    			realname:$("#userinfo-search-realname").val()
    		});
    	}
	},toolbar);
});