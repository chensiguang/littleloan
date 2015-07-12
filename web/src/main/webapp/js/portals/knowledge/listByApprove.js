$(function(){
            //设置分页模式
			var list=$("#knowledgeByApprove-mainGrid");//获取当前grid名称
			list.datagrid({
				loader:function(param,success,error){
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url:SiteUrl.Portals_API+"knowledge/pageByApprove.do",
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
				toolbar:'#knowledgeByApprove-gridToolbar',//绑定工具栏
				columns:[[//表格绑定字段
				         {field:"title",title:"标题",width:150},
				         {field:"type",title:"类型",width:80},
				         {field:"keywords",title:"关键字",width:150},
				         {field:"content",title:"内容"},
				         {field:"isall",title:"发布范围",width:80,formatter: function (v, r, index){
							if(v ==  1){
								return "全员";
							}else{
								return "非全员";
							}
					      }},
					      {field:"status",title:"状态",width:80,formatter: function (v, r, index){
							if(v ==  0){
								return "新建";
							}else if(v ==  1){
								return "退回";
							}else if(v ==  2){
								return "待审批";
							}else if(v ==  3){
								return "审批通过";
							}
						  }}
				]]
			});
            var pager=list.datagrid('getPager');
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });

            var toolbar=$("#knowledgeByApprove-gridToolbar");
            var gridDialog=$("#knowledgeByApprove-approve-gridDialog");
            var form = $("#knowledgeByApprove-form");
            //加载知识库类型
            $('#knowledgeByApprove-type').combobox({
            	loader:function(param,success,error){
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					param.flexkey = "knowledgeType";
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url:SiteUrl.System_API+"flexkey/list.do",
						type:"post",
						data:param,
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								success(data.rows);//加载数据
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
			    valueField:'flexvalue',
			    textField:'valueDescription'
			});
            //富文本编辑框knowledgeByApprove-content
            var knowledgeByApproveContent = null;
			$("#knowledgeByApprove-kwords").bind("keydown", function(event){
            	if(event.keyCode == "13"){
            		toolbar.find(".search-button").trigger("click");
            	}
            });
            UI.bindEvents({
        		"edit-button":function(){
        			var row = list.datagrid("getSelected");//获取当前选中的knowledge对象
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			if(row.status==1){
        				$.messager.alert("提示", "该记录已退回!", "info");
        				return;
        			}
        			if(row.status==3){
        				$.messager.alert("提示", "该记录已审批通过!", "info");
        				return;
        			}
        			$.ajax({
						url:SiteUrl.Portals_API+"knowledge/getone.do",
						type:"post",
						data:row,
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								form.form("load",data.rows);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
								UI.initUploadFileList("knowledgeByApprove-filelist", data.rows.attachList, false);
								gridDialog.dialog({
			        				title:"审核",
			        				buttons:[{
			        					text:'通过',//通过按钮的事件
			        					handler:function(){
			        						//定义一个 knowledge对象，更新到数据库
			        						var obj = new Object();
			        						obj.authorityUserId = $.cookie("userId");
			        						obj.id = $("#knowledgeByApprove-id").val();
			        						obj.status = 3;
			        						$.ajax({
			        							url:SiteUrl.Portals_API+"knowledge/approve.do",
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
			        					text:'退回',//退回按钮的事件 
			        					handler:function(){
			        						if(!form.form("validate")){//控件验证
			        							return;
			        						}
			        						//定义一个 knowledge对象，更新到数据库
			        						var obj = new Object();
			        						obj.authorityUserId = $.cookie("userId");
			        						obj.id = $("#knowledgeByApprove-id").val();
			        						obj.status = 1;
			        						$.ajax({
			        							url:SiteUrl.Portals_API+"knowledge/approve.do",
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
			        			knowledgeByApproveContent = UI.initKindEditor(knowledgeByApproveContent, "knowledgeByApprove-content", data.rows.content, "");
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
            	"search-button":function(e){//查询按钮执行
            		e.preventDefault();
            		list.datagrid("load",{//刷新一下页面，并将查询条件作为参数传递给后台
            			keywords:$("#knowledgeByApprove-kwords").val()
            		});
            	}
        	},toolbar);
        });