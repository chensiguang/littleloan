//获取问卷问题列表
function findSurveyQuestion(id, wrapId){
	var obj = new Object();
	obj.authorityUserId = $.cookie("userId");
	obj.surveyid = id;
	$.ajax({
		url:SiteUrl.System_API+"surveyquestion/queryList.do",
		type:"post",
		data:obj,
		dataType:"jsonp",
		success:function(data){
			if(data.error==1){
				if("" != data.rows && data.rows.length > 0){
					var temp = "";
					for(var i=0; i<data.rows.length; i++){
						var obj = data.rows[i];
						temp += "<div class='survey-question-item'>";
						$.each(obj,function(key,value){
							if(value.length > 0){
								temp += createTitleDom(i+1, value[0]);
								var options = new Array();
								if(value.length > 1){
									for(var j=1; j<value.length; j++){
										options.push(value[j]);
									}
								}
								temp += createOptionsDom(value[0].type, options);
							}
						});
						temp +="</div>";
					}
					var targetObj = $(temp).appendTo("#" + wrapId);
					$.parser.parse(targetObj);
				}
			} else if(data.error==-100){
				$.messager.alert("提示", "会话超时，请重新登陆！", "error");
			} else{
				$.messager.alert("提示", data.message, "error");
			}
		}
	});
}
//创建问题标题
function createTitleDom(number,title){
	var temp = '<div class="survey-question" style="border:2px solid #fff;padding:4px;clear:both;margin:2px auto 0px; width:96%;height:auto;">' +
					'<div class="survey-question-num" style="padding-top:2px;font-size:15px;color#444;font-weight:bold;height;auto;line-height:20px;">' +
						'<div class="" style="font-weight:bold;width:28px;float:left;"><b>'+number+' .</b></div>'+
					'</div>'+
					'<div class="survey-question-title" style="line-height:20px;">'+title.content+(2 == title.type ? "（多选）" : "")+'</div>'+
				'</div>';
	return temp;
}
//创建问题选项
function createOptionsDom(type, options){
	var temp = '<div class="clear" style="clear:both;padding-left:24px;padding-bottom:2px;font-size:14px;color:#333;">'+
	'<div style="clear:both;margin-top:4px;"></div>'+
	'<ul style="clear:both;list-style:none; border:none;margin:5px 0px 0px 5px;">';
	if(3 != type){
		for(var i=0; i<options.length; i++){
			var obj = options[i];
			temp+='<li class="clear" style="width:auto;line-height:30px;positio:relative;float:left;list-style-type:none;margin-bottom:2px;">';
			//选项类型处理
			if(2 == type){	//多选
				temp += '<input type="radio" title="非常不满意" style="float:left;positio:relative;height:24px;">';
			}else {
				temp += '<input type="radio" title="非常不满意" name='+obj.questionid+' style="float:left;positio:relative;height:24px;">'; 
			}
			temp+= '<label style="float:left;padding-left:2px;display:block;">'+numberToLetter(obj.sortno)+'.'+obj.content+'</label>'+
			'</li>';
		}
	}else{
		temp+='<li class="clear" style="width:auto;line-height:30px;positio:relative;float:left;list-style-type:none;margin-bottom:2px;">'+
				'<textarea rows="3" style="float:left;positio:relative;height:32px;"></textarea>'+
			  '</li>';
	}
	temp += '</ul></div>';
	return temp;
}

//数字转换成字母
function numberToLetter(number){
	if(1== number){
		return "A";
	}else if(2== number){
		return "B";
	}else if(3== number){
		return "C";
	}else if(4== number){
		return "D";
	}else if(5== number){
		return "E";
	}else if(6== number){
		return "F";
	}else{
		return number;
	}
}

//删除问卷
function deleteSurvey(id,name, gridId){
	$.messager.confirm("删除提示","你确定要删除问卷“" + name+  "”",function(data){
		if(data){
			var obj = new Object();
			obj.authorityUserId = $.cookie("userId");
			obj.id = id;
			$.ajax({
				url:SiteUrl.System_API+"survey/delete.do",
				type:"post",
				data:obj,
				dataType:"jsonp",
				success:function(data){
					if(data.error==1){
						$("#" + gridId).datagrid("reload");//刷新页面
					} else if(data.error==-100){
						$.messager.alert("提示", "会话超时，请重新登陆！", "error");
					} else{
						$.messager.alert("提示", data.message, "error");
					}
				}
			});
		}else{
			//取消
		}
	});
}

//更新问卷状态
function updateSurvey(id,status, onSuccess,onError){
	if("" == id || "" == status){
		$.messager.alert("提示", "参数格式不正确", "error");
	}else{
		var obj = new Object();
		obj.authorityUserId = $.cookie("userId");
		obj.id = id;
		obj.status = status;
		$.ajax({
			url:SiteUrl.System_API+"survey/updateStatus.do",
			type:"post",
			data:obj,
			dataType:"jsonp",
			success:function(data){
				if(data.error==1){
					if(onSuccess && typeof(onSuccess) === "function"){
						onSuccess(data);
					}
				} else if(data.error==-100){
					$.messager.alert("提示", "会话超时，请重新登陆！", "error");
				} else{
					if(onError && typeof(onError) === "function"){
						onError(data);
					}
				}
			}
		});
	}
}
