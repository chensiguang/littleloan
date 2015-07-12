//本窗口专门为组织分配准入规则使用
$(function(){
	var list = $("#subjectSetting-tree");
	var treeDialog = $("#subjectSetting-create-treeDialog");
	var form = $("#subjectSetting-form");
	//树的右键菜单
	var treeMenu = $("#subjectSetting-tree-menu");
	var treeData;
	list.tree({
		//右键节点触发的事件
	    onContextMenu:function(e,node){
	        e.preventDefault();
	        treeMenu.menu("show",{
	            left:e.pageX,
	            top:e.pageY
	        });
	        currentNode=node;
	    }
	});
	//右键菜单上面绑定事件
	UI.bindEvents({
	    "add-node":function(){
	    	treeDialog.dialog({
				title:'新增科目',
				buttons:[{
					text:'创建',
					handler:function(){
						
					}
				},{
					text:'取消',
					handler:function(){
						treeDialog.dialog("close");
					}
				}]
			});
			treeDialog.dialog("open");
	    },
	    "edit-node":function(){
	    	alert("编辑");
	    },
	    "remove-node":function(){
	    	alert("删除");
	    }
	},treeMenu);
});