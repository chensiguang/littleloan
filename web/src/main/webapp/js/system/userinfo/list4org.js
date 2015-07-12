$(function(){
	Userinfo = {
		loadData : function(){
			list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    			username:$("#userinfo4org-search-username").val(),
    			realname:$("#userinfo4org-search-realname").val()
    		});
		}
	};
	//加载下拉框
	ControllerUtil.loadComboBox($("#userinfo4org-status"), SiteUrl.System_API+"flexkey/list.do", {flexkey:"userStatus"});
    //设置分页模式
	var list=$("#userinfo4org-mainGrid");//获取当前grid名称
	var toolbar=$("#userinfo4org-gridToolbar");
    var gridDialog=$("#userinfo4org-create-gridDialog");
	list.datagrid({
		loader:function(param,success,error){
			
			if($("#userinfo4org-orgnizationId").val()==""){
				$("#userinfo4org-orgnizationId").val($.cookie("orgnizationId"));
			}
			DepartmentSelector2.load("userinfo4org-departmentId",{multiple: false}, {orgnizationId:$("#userinfo4org-orgnizationId").val(), authorityUserId: $.cookie("userId")}, []);
			param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			param.orgnizationId = $("#userinfo4org-orgnizationId").val();
			$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
				url:SiteUrl.System_API+"user/page.do",
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
		toolbar:'#userinfo4org-gridToolbar',//绑定工具栏		
		columns:[[//表格绑定字段
		         {field:"username",title:"用户名",width:100},
		         {field:"realname",title:"真实姓名",width:100},
		         {field:"email",title:"电子邮箱",width:150},
		         {field:"telephone",title:"联系电话",width:100},
		         {field:"departmentName",title:"所处部门",width:100},
		         {field:"orgnizationName",title:"所属组织",width:100},
		         {field:"status",title:"状态",width:100}
		]]
	});
    var pager=list.datagrid('getPager');
    pager.pagination({
        layout:['list','sep','first','prev','links','next','last','sep','refresh']
    });

    
    var form = $("#userinfo4org-form");
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
						obj.username = $("#userinfo4org-username").val();
						obj.password = $("#userinfo4org-password").val();
						obj.realname = $("#userinfo4org-realname").val();
						obj.email = $("#userinfo4org-email").val();
						obj.telephone = $("#userinfo4org-telephone").val();
						obj.departmentId = DepartmentSelector2.getSelectedItems("userinfo4org-departmentId").id;
						obj.orgnizationId = $("#userinfo4org-orgnizationId").val();
						obj.status = $("#userinfo4org-status").combobox("getValue");
						obj.departmentName = DepartmentSelector2.getSelectedItems("userinfo4org-departmentId").departmentName;
						$.ajax({
							url:SiteUrl.System_API+"user/insert.do",
							type:"post",
							data:obj,
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
			$("#userinfo4org-id").val(row.id);
			$("#userinfo4org-username").val(row.username);
			$("#userinfo4org-password").val(row.password);
			$("#userinfo4org-realname").val(row.realname);
			$("#userinfo4org-email").val(row.email);
			$("#userinfo4org-telephone").val(row.telephone);
			$("#userinfo4org-departmentId").combotree('setValue',row.departmentId);
			$("#userinfo4org-orgnizationId").val(row.orgnizationId);
			$("#userinfo4org-status").combobox('setValue', row.status);
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
						obj.id = $("#userinfo4org-id").val();
						obj.username = $("#userinfo4org-username").val();
						obj.password = $("#userinfo4org-password").val();
						obj.realname = $("#userinfo4org-realname").val();
						obj.email = $("#userinfo4org-email").val();
						obj.telephone = $("#userinfo4org-telephone").val();
						obj.departmentId = DepartmentSelector2.getSelectedItems("userinfo4org-departmentId").id;
						obj.orgnizationId = $("#userinfo4org-orgnizationId").val();
						obj.status = $("#userinfo4org-status").combobox("getValue");
						obj.authorityUserId = $.cookie("userId");
						$.ajax({
							url:SiteUrl.System_API+"user/update.do",
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
						url:SiteUrl.System_API+"user/delete.do",
						type:"post",
						data:row,
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
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
			});
		},
		"search-button":function(e){//查询按钮执行
    		e.preventDefault();
    		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
    			username:$("#userinfo4org-search-username").val(),
    			realname:$("#userinfo4org-search-realname").val()
    		});
    	},
    	"distribute-button":function(){//用户角色分配
    		showUserRole();
    	}
	},toolbar);
});