OrgnizationSelector = {
		/*
		 * controllerId，控件id
		 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
		 * params，ajax查询的参数，主要包括authorityUserId,
		 * selectedIds,默认选中的id列表，数组格式，如：['001','002'],
		 * treeId，部门树控件id
		 */
		load: function(controllerId, controllerParams, params, selectedIds){
			var that = this;
			$.ajax({
	    		url:SiteUrl.System_API+"orgnization/list.do",
	    		type:"post",
	    		data:params,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				controllerParams.textField = "orgnizationName";
	    				controllerParams.valueField = "id";
	    				controllerParams.data = data.rows;
	    				controllerParams.onLoadSuccess = function(){
	    					$("#"+controllerId).combobox("setValues", selectedIds);
	    				};
	    				controllerParams.filter = function(q,row){
	    					if(row.spell.indexOf(q) >= 0)
	    						return true;
	    					if(row.orgnizationCode.indexOf(q)>=0)
	    						return true;
	    					if(row.orgnizationName.indexOf(q)>=0)
	    						return true;
	    					if(row.shortname.indexOf(q)>=0)
	    						return true;
	    					
	    					return false;
	    				};
	    				
	    				$("#"+controllerId).combobox(controllerParams);
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		},
		getSelected : function(controllerId){
			return $("#"+controllerId).combobox("getValues");
		},
		getSelectedItems : function(controllerId){
			var data = $("#"+controllerId).combobox("getData");
			var selectedValues = $("#"+controllerId).combobox("getValues");
			var selectedItems = [];
			for(var i=0; i<selectedValues.length; i++){
				for(var j=0; j<data.length; j++){
					if(selectedValues[i] == data[j].id){
						selectedItems.push(data[j]);
						break;
					}
				}
			}
			return selectedItems;
		}
};

OrgnizationSelector2 = {
		/*
		 * controllerId，控件id
		 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
		 * params，ajax查询的参数，主要包括authorityUserId,
		 * selectedIds,默认选中的id列表，数组格式，如：['001','002'],
		 * treeId，部门树控件id
		 * unSelecterId,待选用户控件id
		 * selectId,已选用户控件id
		 * containerId 显示控件id
		 */
		load: function(controllerId, controllerParams, params, selectedIds,treeId,unSelecterId,selectId){
			var that = this;
			$.ajax({
	    		url:SiteUrl.System_API+"orgnization/list.do",
	    		type:"post",
	    		data:params,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				controllerParams.textField = "orgnizationName";
	    				controllerParams.valueField = "id";
	    				controllerParams.data = data.rows;
	    				controllerParams.onLoadSuccess = function(){
	    					$("#"+controllerId).combobox("setValues", selectedIds);
	    				};
	    				controllerParams.filter = function(q,row){
	    					if(row.spell.indexOf(q) >= 0)
	    						return true;
	    					if(row.orgnizationCode.indexOf(q)>=0)
	    						return true;
	    					if(row.orgnizationName.indexOf(q)>=0)
	    						return true;
	    					if(row.shortname.indexOf(q)>=0)
	    						return true;
	    					
	    					return false;
	    				};
	    				controllerParams.onSelect=function(row){
	    					that.loadTree(row.id,treeId,unSelecterId,selectId)
	    				}
	    				
	    				$("#"+treeId).tree({
		    				onClick: function(node){
		    					if ($(this).tree("isLeaf", node.target) ==true) {
		    						$("#"+unSelecterId).datalist("load",{
		    		                    deptid:node.id
		    		                })
		    		            }else{
		    		                $(this).tree("toggle", node.target);
		    		            }
		    				}
		    			})
		    			$("#"+unSelecterId).datalist({
					        onClickRow:function(index,row){
					        	$("#"+selectId).datalist("appendRow",row)
					            $(this).datalist("deleteRow",index)
					        }
					    })
					
					    $("#"+selectId).datalist({
					        onClickRow:function(index,row){
					        	$("#"+unSelecterId).datalist("appendRow",row)
					            $(this).datalist("deleteRow",index)
					        }
					    })
	    				
	    				$("#"+controllerId).combobox(controllerParams);
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		},
		getSelected : function(controllerId){
			return $("#"+controllerId).combobox("getValues");
		},
		getSelectedItems : function(controllerId){
			var data = $("#"+controllerId).combobox("getData");
			var selectedValues = $("#"+controllerId).combobox("getValues");
			var selectedItems = [];
			for(var i=0; i<selectedValues.length; i++){
				for(var j=0; j<data.length; j++){
					if(selectedValues[i] == data[j].id){
						selectedItems.push(data[j]);
						break;
					}
				}
			}
			return selectedItems;
		},
		loadTree: function(selectvalue,treeId,unSelecterId,selectId){
			$.ajax({
				url:SiteUrl.System_API+"orgnization/list.do",
				type:"post",
	    		//data:{id:selectvalue},
				data: {authorityUserId:1},
	    		dataType:"jsonp",
	    		success:function(data){
	    			$("#"+treeId).tree({
	    				data: data.rows
	    			})
	    			
	    		}
			})	
		},
		selectUser: function(selectId,containerId){
			var data = $("#"+selectId).datalist("getRows");
			var uids = [];
			$.each(data, function() {
			    uids.push(this.uid);
			});
			$("#"+containerId).textbox("setValue",uids.join(","));
		},
		openDialog: function(dlg,title,selectId,containerId){
			var that = this;
			$("#"+dlg).dialog({
				buttons:[{
					text: "确定",
					handler: function(){
						that.selectUser(selectId,containerId);
						$("#"+dlg).dialog("close");
					}
				}],
				onBeforeOpen: function(){
					//that.load(controllerId, controllerParams, params, selectedIds,treeId,unSelecterId,selectId)
				}
			})
			$("#"+dlg).dialog("open").dialog("setTitle",title)
		}
};

DepartmentSelector = {
		/*
		 * controllerId，控件id
		 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
		 * params，ajax查询的参数，主要包括authorityUserId,orgnizationId(组织id),parentDepartmentId(查询一个部门及其子部门),departmentId(只显示一个部门)
		 * selectedIds,默认选中的id列表，数组格式，如：['001','002']
		 */
		load: function(controllerId, controllerParams, params, selectedIds){
			$.ajax({
	    		url:SiteUrl.System_API+"department/list.do",
	    		type:"post",
	    		data:params,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				controllerParams.textField = "departmentName";
	    				controllerParams.valueField = "id";
	    				controllerParams.data = data.rows;
	    				controllerParams.onLoadSuccess = function(){
	    					$("#"+controllerId).combobox("setValues", selectedIds);
	    				};
	    				$("#"+controllerId).combobox(controllerParams);
	    				
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		},
		getSelected : function(controllerId){
			return $("#"+controllerId).combobox("getValues");
		},
		getSelectedItems : function(controllerId){
			var data = $("#"+controllerId).combobox("getData");
			var selectedValues = $("#"+controllerId).combobox("getValues");
			var selectedItems = [];
			for(var i=0; i<selectedValues.length; i++){
				for(var j=0; j<data.length; j++){
					if(selectedValues[i] == data[j].id){
						selectedItems.push(data[j]);
						break;
					}
				}
			}
			return selectedItems;
		}
};
DepartmentSelector2 = {
		/*
		 * controllerId，控件id
		 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
		 * params，ajax查询的参数，主要包括authorityUserId,orgnizationId(组织id),parentDepartmentId(查询一个部门及其子部门),departmentId(只显示一个部门)
		 * selectedId,默认选中的id列表 单选
		 */
		load: function(controllerId, controllerParams, params, selectedId){
			$.ajax({
	    		url:SiteUrl.System_API+"department/list.do",
	    		type:"post",
	    		data:params,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				controllerParams.textField = "departmentName";
	    				controllerParams.valueField = "id";
	    				controllerParams.data = data.rows;
	    				controllerParams.onLoadSuccess = function(){
	    					$("#"+controllerId).combotree("setValue", selectedId);
	    				};
	    				$("#"+controllerId).combotree(controllerParams);
	    				
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		},
		getSelected : function(controllerId){
			return $("#"+controllerId).combobox("getValue");
		},
		getSelectedItems : function(controllerId){
			var departmentParentName=$("#"+controllerId);
			var deTree = departmentParentName.combotree("tree");
			var deTreeNode = deTree.tree("getSelected");
			return deTreeNode;
		}
};

/*
 * controllerId，控件id
 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
 * params，ajax查询的参数，主要包括authorityUserId, parentDepartmentId(查询一个部门及子部门下的人)，departmentId(只查询一个部门的人，不包含子部门)，
 * orgnizationId(取一个组织所有的人)，parentOrgnizationId(取一个组织及子组织所有的人)
 * selectedIds,默认选中的id列表，数组格式，如：['001','002']
 */
UserSelector = {	
	load: function(controllerId, controllerParams, params, selectedIds){
		$.ajax({
    		url:SiteUrl.System_API+"user/list.do",
    		type:"post",
    		data:params,
    		dataType:"jsonp",
    		success:function(data){
    			if(data.error==1){
    				controllerParams.textField = "realname";
    				controllerParams.valueField = "id";
    				controllerParams.data = data.rows;
    				controllerParams.onLoadSuccess = function(){
    					$("#"+controllerId).combobox("setValues", selectedIds);
    				};
    				controllerParams.formatter = function(row){
    					var s = row.realname + "(" +row.departmentName + ")";
    					return s;
    				};
    				controllerParams.filter = function(q,row){
    					if(row.spell.indexOf(q) >= 0)
    						return true;
    					if(row.realname.indexOf(q) >= 0)
    						return true;
    					if(row.username.indexOf(q) >= 0)
    						return true;
    					
    					return false;
    				};
    				
    				$("#"+controllerId).combobox(controllerParams);
    				
    			}else if(data.error==-100){
    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
    			}else{
    				$.messager.alert("提示",data.message, "error");
    			}
    		}
    	});
	},
	getSelected : function(controllerId){
		return $("#"+controllerId).combobox("getValues");
	},
	getSelectedItems : function(controllerId){
		var data = $("#"+controllerId).combobox("getData");
		var selectedValues = $("#"+controllerId).combobox("getValues");
		var selectedItems = [];
		for(var i=0; i<selectedValues.length; i++){
			for(var j=0; j<data.length; j++){
				if(selectedValues[i] == data[j].id){
					selectedItems.push(data[j]);
					break;
				}
			}
		}
		return selectedItems;
	}
};

/*
 * controllerId，控件id
 * controllerParams，控件的参数，如 {multiple:true, multiline:true}，详细可以参考easyui
 * params，ajax查询的参数，主要包括authorityUserId, orgnizationId(取一个组织所有的人)
 * selectedIds,默认选中的id列表，数组格式，如：['001','002']
 */
RoleSelector = {	
		load: function(controllerId, controllerParams, params, selectedIds){
			$.ajax({
	    		url:SiteUrl.System_API+"role/getOrgnizationRole.do",
	    		type:"post",
	    		data:params,
	    		dataType:"jsonp",
	    		success:function(data){
	    			if(data.error==1){
	    				controllerParams.textField = "roleName";
	    				controllerParams.valueField = "id";
	    				controllerParams.data = data.rows;
	    				controllerParams.onLoadSuccess = function(){
	    					$("#"+controllerId).combobox("setValues", selectedIds);
	    				};
	    				
	    				$("#"+controllerId).combobox(controllerParams);
	    				
	    			}else if(data.error==-100){
	    				$.messager.alert("提示","会话超时，请重新登陆！", "error");
	    			}else{
	    				$.messager.alert("提示",data.message, "error");
	    			}
	    		}
	    	});
		},
		getSelected : function(controllerId){
			return $("#"+controllerId).combobox("getValues");
		},
		getSelectedItems : function(controllerId){
			var data = $("#"+controllerId).combobox("getData");
			var selectedValues = $("#"+controllerId).combobox("getValues");
			var selectedItems = [];
			for(var i=0; i<selectedValues.length; i++){
				for(var j=0; j<data.length; j++){
					if(selectedValues[i] == data[j].id){
						selectedItems.push(data[j]);
						break;
					}
				}
			}
			return selectedItems;
		}
		
	};