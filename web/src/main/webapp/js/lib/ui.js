/*
	通用函数封装
*/
var UI=(function(w,d){
	function setTab(href,title,params,callback,tabs){
        if(!tabs.tabs('exists', title)){
            tabs.tabs('add',{
                title: title,
                href: href,
                closable: true,
                fit:true,
                onLoad:function(){
                	
                	callback&&callback(true);
                	$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
						url:SiteUrl.System_API+"menu/queryFunctionByUser.do",
						type:"post",
						data:{authorityUserId:$.cookie("userId")},
						dataType:"jsonp",
						success:function(data){
							if(data.error==1){
								var functionList = data.rows;
								var elementList = tabs.find("[authority]"); //本来是  $(this)
								elementList.hide();
								elementList.each(function(){
									var authority = $(this).attr("authority").toString();
									var element = $(this);
									$.each(functionList, function(i, f){
										var functionName = f.functionName;
										if(functionName===authority){
											element.show();
										}
									});
									if(element.is(":hidden")){
										element.remove();
									}
								});
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
                onBeforeLoad:function(){
                	var panel=$(this);
                	panel.data("params",params);
                },
                style:{
                	padding:10,
                	position:"relative"
                }
            });
        }
        else{
            tabs.tabs('select', title);
        	callback&&callback(false);
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
				var item=$(this);
				var href=item.attr("data-href");
		        var id=item.attr("data-func-id");
		        var title=item.html();
		        var params={"func-id":id};
				setTab(href,title,params,null,pageTabs);
			});
		},
		addTab:function(href,title,params,callback){
			var pageTabs=$("#page-tabs");
			setTab(href,title,params,callback,pageTabs);
		},
		//设置列表的默认分页模式
		setPageMode:function(selector){
			var pager=$(selector).datagrid('getPager');
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });
		},
		//设置默认
		setPageModeSave:function(ctx,param,pageConfig){
			var pager=ctx.datagrid('getPager');
			pageConfig.authorityUserId = param.authorityUserId;
			pageConfig.functionId = param.functionId;
			pageConfig.userId = param.authorityUserId;
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh'],
                onChangePageSize:function(pageSize){
                	var url = SiteUrl.System_API+"pageItem/updatePagePation.do";
                	pageConfig.pageitemValue = pageSize;
        			$.ajax({
        				url:url,
        				type:"post",
        				data:pageConfig,
        				dataType:"jsonp",
        				success:function(data){
        					if(data.error!=1){
        						UI.alert('加载出错');
        					}
        				}
        			});
                }
                
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
		},
		getElement:function(id){
			if(typeof(id)=="string") return $(id);
			return id;
		},
		loadList:function(config){
			var list=ui.getElement(config.list);
			var listParam = config.params;
			listParam.authorityUserId = $.cookie("userId");
			var callback=ui.getLoadCallback(config);
			var url = SiteUrl.System_API+"pageItem/getPageItemAllByUser.do";
			$.ajax({
				url:url,
				type:"post",
				data:listParam,
				dataType:"jsonp",
				success:function(data){
					if(data.error!=1){
						UI.alert('加载出错');
					}else{
						callback&&callback(data);
					}
				}
			});
		},
		getLoadCallback:function(cfg){//cfg 里面是存放的 前面返回的 参数
			var callback=function(config){ //config里面是存放的 列配置
				var list=ui.getElement(cfg.list);
				var toolbar=ui.getElement(cfg.toolbar);
				var listParam = cfg.params||{};
					listParam.authorityUserId = $.cookie("userId");
				var type = cfg.type||"list";	
				var pageConfig = UI.getColumnsConfig(config).pagePation;//取得 分页配置
				var loaderConfig=cfg.config||{};
				var listConfig={
					loader:function(param,success,error){
						param=$.extend({},param,listParam);
						$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
							url:cfg.url,
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
					fitColumns:true,//列宽自适应
					rownumbers:true,
					toolbar:toolbar,//绑定工具栏
					columns:[UI.getColumnsConfig(config).cfg] // 列的动态配置
				};
				var treelistConfig={
						loader:function(param,success,error){
						param=$.extend({},param,listParam);
						$.ajax({//调用ajax获取数据，使用jsonp方式，跨域
							url:cfg.url,
							type:"post",
							data:param,
							dataType:"jsonp",
							success:function(data){
								if(data.error==1){
									if(cfg.treeEv){
										cfg.treeEv.combotree({data:data.rows,width:150});
									}
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
					toolbar: toolbar,
				    idField:loaderConfig.idField,
				    treeField:loaderConfig.treeField,
				    fitColumns:true,
				    rownumbers: true,
					columns:[UI.getColumnsConfig(config).cfg]
					};
				var paginationMode=loaderConfig.pagination!==false?true:false;
				if(paginationMode){
					listConfig.pagination=true;
					listConfig.pageSize=pageConfig.pageSize;
				}else{
					listConfig.pagination=false;
				}
				if(loaderConfig.onDblClickRow!==false){
					listConfig.onDblClickRow=UI.trigger("edit-button",toolbar);
				}
				if(type=="tree"){
					list.treegrid(treelistConfig);
				}else{
					list.datagrid(listConfig);
				}
				
				if(paginationMode) UI.setPageModeSave(list,listParam,pageConfig); //分页 点击切换分页条数并保存
				var settingButton=toolbar.find(".setting-button");
				var binded=settingButton.data("binded");
				if(!binded){
					settingButton.data("binded",true);
					settingButton.data("listConfig",config);
					settingButton.data("listParam",listParam);
					var loadCallback=ui.getLoadCallback(cfg);
					//默认绑定设置按钮
					UI.bindEvents({
						"setting-button":function(e){
	            			//重新组装弹窗内容
	            			var button=$(this);
	            			var listConfig=button.data("listConfig");
	            			var listParam=button.data("listParam");
	            			UI.showListConfig(listConfig,listParam,loadCallback);
	            		}
	            	},toolbar);
				}
			};
			return callback;
		},
		getColumnsConfig:function(config){
			var cfg=[];
			var rows=config.rows;
			var pagePation =new Object();
			pagePation.pageSize = 10;//默认分页10
			for(var i=0,l=rows.length;i<l;i++){
				var row=rows[i];
				if(row.checked&&row.type=="字段"){
					cfg.push({
						field:row.itemDescription,
						title:row.itemName,
						width:150,
						align:row.pageitemValue?row.pageitemValue:"center"
					});
				}else if(row.type=="分页"){
					pagePation.pageItemId = row.id;
					if(/^\d+$/.test(row.pageitemValue)){
						pagePation.pageSize = row.pageitemValue;
					}
					pagePation.checked = row.checked;
				}
			}
			return {cfg:cfg,pagePation:pagePation};
		},
		showListConfig:function(config,listParam,callback){
			var settingDialog=$("#setting-list-dialog");
			var listCacheConfig={};
    		var buf=[];
    		buf.push('<div class="list-setting-panel">');
    		var rows=config.rows;
    		var pationRow = new Object();
    		for(var i=0,l=rows.length;i<l;i++){
    			var row=rows[i];
    			listCacheConfig[row.id]=row;
    			if(row.type=="字段"){
    				var ck="";
        			if(row.checked){
        				ck=' checked="checked"';
        			}
        			var depValue1 = "";
        			var depValue2 = "";
        			var depValue3 = "";
        			if(row.pageitemValue=="left"){
        				depValue1 = 'selected';
        			}else if(row.pageitemValue=="center"){
        				depValue2 = 'selected';
        			}else if(row.pageitemValue=="right"){
        				depValue3 = 'selected';
        			}else{
        				depValue2 = 'selected';
        			}
        			buf.push('<p data-id="'+row.id+'"><label><input type="checkbox"'+ck+'/>'+row.itemName+'</label>'+
        					'&nbsp;&nbsp;&nbsp;<label>字段位置:</label><select class="easyui-combobox" id="pageItem_editList" required="true"  missingMessage="必填" editable="false" >'+
    		            	'<option value="left" '+depValue1+'>左边</option>'+
    		            	'<option value="center" '+depValue2+'>居中</option>'+
    		            	'<option value="right" '+depValue3+'>右边</option>'+
    		            	'</select></p>');
    			}else if(row.type=="分页"){
    				pationRow = row;
    			}
    		}
    		buf.push('</div>');
    		settingDialog.html(buf.join(''));
    		settingDialog.dialog({
    			top:200,
    			buttons:[{
    				text:'保存',
    				handler:function(){
    					var ps=settingDialog.find(".list-setting-panel").children();
    					var data=[];
    					var checkedNum=0;
    					ps.each(function(){
    						var p=$(this);
    						var id=p.attr("data-id");
    						var input=p.find("input");
    						var select = p.find("select");
    						var row=listCacheConfig[id];
    						row.checked=input.get(0).checked?true:false;
    						row.pageitemValue = select.val();
    						if(row.checked){
    							checkedNum++;
    						}
    						data.push(row);
    					});
    					if(checkedNum==0){
    						UI.alert("必须选择一个字段进行显示");
    						return;
    					}
    					data.push(pationRow);
    					ui.saveListConfig(data,listParam,callback);
    					settingDialog.dialog("close");
    				}
    			},
    			{
    				text:'取消',
    				handler:function(){
    					settingDialog.dialog("close");
    				}
    			}]
    		});
    		settingDialog.dialog("open");
		},
		saveListConfig:function(data,listParam,callback){
			var params=ui.buildListConfigParams(data);
			params.authorityUserId=listParam.authorityUserId;
			params.functionId=listParam.functionId;
			$.ajax({
				url:SiteUrl.System_API+"pageItem/insertPage.do",
				type:"post",
				data:params,
				dataType:"jsonp",
				success:function(result){
					if(result.error==1){
						//需要重新组装回与初始加载一样的配置
						callback&&callback({rows:data});
					}
					else if(result.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					}
					else{
						$.messager.alert("提示", result.message, "error");
					}
				}
			});
		},
		getFunctionId:function(ctx){
			var params=ui.getParams(ctx);
			return params["func-id"];
		},
		getParams:function(ctx){
			var parent=ctx.parent();
			var i=0;
			while(!parent.data("params")){
				parent=parent.parent();
				i++;
				if(i>=10){
					return null;
				}
			}
			return parent.data("params");
		},
		getFunctionParam:function(ctx){
			var funcId = ui.getFunctionId(ctx);
			var listParam = new Object;
			listParam.authorityUserId = $.cookie("userId");//参数中带上authorityUserId
			listParam.functionId = funcId;
			return listParam;
		},
		buildListConfigParams:function(data){
			var params={};
			params.rows=[];
			for(var i=0,l=data.length;i<l;i++){
				var cfg=data[i];
				if(cfg.checked&&cfg.type=="字段"){
					params.rows.push({
						pageItemId:cfg.id,
						pageitemValue:cfg.pageitemValue
					});
				}
			}
			params.rows=JSON.stringify(params.rows);
			return params;
		},
		trigger:function(selector,context,type){
			return function(){
				context=context||body;
				type=type||"click";
				if(selector.substring(0,1)!=="."&&selector.substring(0,1)!=="#") selector="."+selector;
				context.find(selector).trigger(type);
			};
		},
		initKindEditor:function(pageEditor, textAreaId, data, config){
			if(null == config){
				config = ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	               			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	               			'insertunorderedlist', '|', 'emoticons', 'image', 'link'];
			}
			if(null == pageEditor) {
				pageEditor = KindEditor.create("#"+textAreaId, {
               		resizeType : 1,
               		minWidth : 300,
               		uploadJson: SiteUrl.System_API + 'file/fileupload4kindedit.do?domain=http://web.pettyloan.com:8081/pettyLoan-web/&authorityUserId='+$.cookie("userId"),
               		items : config
               	});
        	}
        	if(data!=null){
        		pageEditor.html(data);
        		if("" == config) pageEditor.readonly(true);
        	}
        	return pageEditor;
		},
		initUploadFileList:function(listDivId, list, showDelete){
			var selector = $("#"+listDivId);
			selector.html("");//清空列表
			if(null == list) return;
			if(null == showDelete) showDelete = true;
			var buf = [];
			for ( var i = 0; i < list.length; i++) {
				buf.push("<p><span>");
				buf.push(list[i].filename);
				buf.push("</span>");
				buf.push("<a href='");
				buf.push(SiteUrl.Portals_API+"attachment/download.do?id=");
				buf.push(list[i].id);
				buf.push("' class='download'>下载</a>");
				if(showDelete){
					buf.push("<a href='javascript:void(0);' onclick='javascript:UI.deleteAttachment4portal(this,");
					buf.push(list[i].id);
					buf.push(");'>删除</a>");
					buf.push("<input type='hidden' name='attachId' value='");
					buf.push(list[i].id);
					buf.push("'/></input>");
				}
				buf.push("</p>");
			}
			selector.append(buf.join(""));
		},
		deleteAttachment4portal:function(ele, id){
			$.ajax({
				url:SiteUrl.Portals_API+"attachment/delete.do",
				type:"post",
				data:{'id' : id},
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						$(ele).parent().remove();
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
	};
	return ui;
})(window,document);

$(function(){
	UI.initMenu();
});