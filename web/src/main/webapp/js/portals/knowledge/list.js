$(function(){
            //设置分页模式
			var list=$("#knowledge-mainGrid");//获取当前grid名称
			list.datagrid({
				loader:function(param,success,error){
					param.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
					$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url:SiteUrl.Portals_API+"knowledge/page.do",
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
				toolbar:'#knowledge-gridToolbar',//绑定工具栏
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

            var toolbar=$("#knowledge-gridToolbar");
            var gridDialog=$("#knowledge-create-gridDialog");
            var form = $("#knowledge-form");
            //加载知识库类型
            $('#knowledge-type').combobox({
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
								data.rows[0].selected = "true";
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
            //富文本编辑框knowledge-content
            var knowledgeContent = null;
            //上传附件成功后展示列表
            var onUploadSuccess4KnowledgeFn = function(file, data, response){
            	var dataObj = JSON.parse(data);
            	var obj = dataObj.rows;
            	$("#knowledge-filelist").append("<p><span>"+obj.filename
						+"</span><a href='javascript:void(0);' onclick='javascript:UI.deleteAttachment4portal(this,"
						+obj.id+");'>删除</a>"
						+"<input type='hidden' name='attachId' value='"+obj.id+"'/>"
						+"</input></p>");
			};
			//上传附件
            var swf4knowledge = "../js/lib/uploadify-v3.1/uploadify.swf";
        	var uploader4knowledge = SiteUrl.System_API + "file/fileupload2db.do";
        	$("#knowledge-file").uploadify({
				'swf'  :   swf4knowledge,  
				'uploader'    :   uploader4knowledge,
				'buttonText':"上传",
				'queueId'   :   "fileQueue",  
				'queueSizeLimit'    :   9,//限制上传文件的数量  
				'fileTypeExts'   :   "*.*",  
				'auto'      :   true,  
				'multi'     :   true,//是否允许多文件上传  
				'simUploadLimit':   2,//同时运行上传的进程数量
				'fileObjName' : 'upFileName',
				'method'    :   "POST" ,
				'onUploadStart':function(){
						var formData = {'authorityUserId' : $.cookie("userId"), 'type' : 'ptl_knowledge', 'typeid' : $("#knowledge-id").val()};
						$("#knowledge-file").uploadify("settings", "formData", formData);
					},
				'onUploadSuccess' : onUploadSuccess4KnowledgeFn
            });
        	$("#knowledge-kwords").bind("keydown", function(event){
            	if(event.keyCode == "13"){
            		toolbar.find(".search-button").trigger("click");
            	}
            });
            UI.bindEvents({
        		"add-button":function(){//工具栏新增按钮
        			form.form("clear");//表单清空
        			UI.initUploadFileList("knowledge-filelist");
        			gridDialog.dialog({
        				title:"创建知识库",
        				buttons:[{
        					text:'确定',
        					handler:function(){
        						if(!form.form("validate")){//控件验证
        							return;
        						}			
        						//定义一个 knowledge对象，赋值，调用ajax新增数据
        						var obj = new Object();
        						obj.authorityUserId = $.cookie("userId");
        						obj.title = $("#knowledge-title").val();
        						obj.type = $("#knowledge-type").combobox("getValue");
        						obj.keywords = $("#knowledge-keywords").val();
        						obj.content = knowledgeContent.html();
        						obj.isall = $("#knowledge-isall").combobox("getValue");
        						var buf=[];
        						$("[name='attachId']", form).each(function(index, domEle){
        							buf.push($(domEle).val());
        						});
        						obj.attachIds = buf.join(",");
        						$.ajax({
        							url:SiteUrl.Portals_API+"knowledge/insert.do",
        							type:"post",
        							data:obj,
        							dataType:"jsonp",
        							success:function(data){
        								if(data.error==1){
        									gridDialog.dialog("close");//如果成功关闭窗口，刷新页面
        									list.datagrid("reload");//刷新页面
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
        			knowledgeContent = UI.initKindEditor(knowledgeContent, "knowledge-content", "");
        		},
        		"edit-button":function(){
        			var row = list.datagrid("getSelected");//获取当前选中的knowledge对象
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			if(row.status==2){
        				$.messager.alert("提示", "该记录已提交审核!", "info");
        				return;
        			}
        			if(row.status==3){
        				$.messager.alert("提示", "该记录已审批通过!", "info");
        				return;
        			}
        			$.ajax({
						url:SiteUrl.Portals_API+"knowledge/getone.do",
						type:"post",
						data:{id:row.id},
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								form.form("load",data.rows);//初始化界面上的控件值，控件的name属性必须与选中行的field对应
								UI.initUploadFileList("knowledge-filelist", data.rows.attachList);
								gridDialog.dialog({
			        				title:"编辑数据",
			        				buttons:[{
			        					text:'保存',//保存按钮的事件 
			        					handler:function(){
			        						if(!form.form("validate")){//控件验证
			        							return;
			        						}
			        						
			        						//定义一个 knowledge对象，更新到数据库
			        						var obj = new Object();
			        						obj.authorityUserId = $.cookie("userId");
			        						obj.id = $("#knowledge-id").val();
			        						obj.title = $("#knowledge-title").val();
			        						obj.type = $("#knowledge-type").combobox("getValue");
			        						obj.keywords = $("#knowledge-keywords").val();
			        						obj.content = knowledgeContent.html();
			        						obj.isall = $("#knowledge-isall").combobox("getValue");
			        						$.ajax({
			        							url:SiteUrl.Portals_API+"knowledge/update.do",
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
			        					text:'提交',//提交按钮的事件
			        					handler:function(){
			        						if(!form.form("validate")){//控件验证
			        							return;
			        						}
			        						
			        						//定义一个 knowledge对象，更新到数据库
			        						var obj = new Object();
			        						obj.authorityUserId = $.cookie("userId");
			        						obj.id = $("#knowledge-id").val();
			        						obj.status = 2;
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
			        			knowledgeContent = UI.initKindEditor(knowledgeContent, "knowledge-content", data.rows.content);
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
        		"remove-button":function(){
        			var row = list.datagrid("getSelected");
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			if(row.status==2){
        				$.messager.alert("提示", "该记录已提交审核!", "info");
        				return;
        			}
        			if(row.status==3){
        				$.messager.alert("提示", "该记录已审批通过!", "info");
        				return;
        			}
        			row.authorityUserId = $.cookie("userId");
        			$.messager.confirm("操作提示","确认删除？",function(result){
        				if(result){
        					$.ajax({
    							url:SiteUrl.Portals_API+"knowledge/delete.do",
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
        		"approve-button":function(){
        			var row = list.datagrid("getSelected");//获取当前选中的knowledge对象
        			if(row==null){
        				$.messager.alert("提示", "请选择一条记录！", "info");
        				return;
        			}
        			if(row.status==2){
        				$.messager.alert("提示", "该记录已提交审核!", "info");
        				return;
        			}
        			if(row.status==3){
        				$.messager.alert("提示", "该记录已审批通过!", "info");
        				return;
        			}
        			$.messager.confirm('提示','你确定提交么？',function(result){
        				if(result){
	        				var obj = new Object();
	        				obj.authorityUserId = $.cookie("userId");
	        				obj.id = row.id;
	        				obj.status = 2;
							$.ajax({
								url : SiteUrl.Portals_API + "knowledge/approve.do",
								type : "post",
								data : obj,
								dataType : "jsonp",
								success : function(data) {
									if (data.error == 1) {
										list.datagrid("reload");
									} else if (data.error == -100) {
										$.messager.alert("提示", "会话超时，请重新登陆！", "error");
									} else {
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
            			keywords:$("#knowledge-kwords").val()
            		});
            	}
        	},toolbar);
        });