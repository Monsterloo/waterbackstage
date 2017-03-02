var lengthCoast = "";
var riverlength = "";
var streetName = "";
var leftRigth = "";
var partName = "";
$(function() {
	var serverId = [], localIds = [];
	//点击受理回复按钮
	$("#answerQuestion").on("click",function(){
		
		//$('#detail_iframe', window.parent.document).attr("style","height:100%;");
		$("#answerHtml").popup();
		//$('#collect_detail_ret', window.parent.document).attr("style","display:none;");
		var qId = $("#id1").val();
		loadAnswerHtml(qId);
	});
	//无效投诉
	$("#wxQuestion").on("click",function(){
		$("#wxHtml").popup();
		//$('#collect_detail_ret', window.parent.document).attr("style","display:none;");
		loadWxHtml();
	});
	$("#back").on("click",function(){
		//$('#collect_detail_ret', window.parent.document).attr("style","display:block;");
		$.closePopup();
	});
	$("#feedbackQuestion").on("click",function(){
		$("#feedbackHtml").popup();
		//$('#collect_detail_ret', window.parent.document).attr("style","display:none;");
		loadFeedbackHtml();
	});
});
//加载回复受理页面
function loadAnswerHtml(qId){
	alert(2);
	var username = $("#username").val();
	$.getJSON('../complain/searchAllAnswer?questionId='+qId, function(data){
		  var len = data.length;
		var html = "";
		for(var i=0; i<len;i++){
			var answer = data[i];
			for(var key in answer){
				if(!answer[key] && typeof answer[key] != "undefined" && answer[key] != 0){
					answer[key] = "";
				}
			}
			html+='<div class="weui_media_box weui_media_text">';
			html+='<h4 class="weui_media_title">回复人：'+answer.username+'</h4>';
			html+='<h5 >回复内容：'+answer.answercontent+'</h5>';
			var aUsername = answer.username;
			var answerId = answer.id;
			if(username == aUsername){
				html += '<a href="#" onclick="deleteAnswer(this);" answerId="'+answerId+'" class="weui_panel_ft quest_detail" >删除</a>';
			}
			html +='</div>\n';
		}
		$(".quest_detail").each(function(index,it){
			var item = $(it);
			item.on("click",function(){
				var id = item.attr("answerid");
				deleteAnswer(id);
			});
		});
		html+='<div class="weui_cells_title">回复内容</div>';
		html+='<input style="display:none" id="qId" value="'+qId+'">';
		html+=' <div class="weui_cell_bd weui_cell_primary">'+
        '<textarea class="weui_textarea"  rows="3" id="answerContent"></textarea>'+
        '</div>';
		html+=' <button id="saveAnswer" onclick="saveAnswer();"  class="weui_btn weui_btn_primary">回复</button>';
		html+='<button id="back"  class="weui_btn weui_btn_plain_default close-popup">返回</button>';
		$("#answerDiv").empty().append(html);
	});
}
//删除回复
function deleteAnswer(obj){
		// 更换Action位置
	var id = $(obj).attr("answerId");
		$.ajax({
			type : 'post',
			data : {'id':id},
			url : "../waterwx/deleteAnswer",
			success : function(data) {
				if(data == "succ"){
					window.location.reload();
				}else{
					$.alert("删除失败");
				}

			},
			error:function(e){
				$.alert("error")	;
			}
		}); 
}
//保存回复
function saveAnswer(){
	var qid = $("#qId").val();
	var content = $("#answerContent").val();
	//$("#con").val(content);
	//$("#answerForm").submit();
	$.ajax({
		type : 'post',
		data : {'qId':qid,'content':content},
		url : "../complain/saveAnswerWeixin",
		success : function(flag) {
			if(flag == "succ"){
				//$.alert("保存成功");
				window.location.reload();
			}
		},
		error:function(e){
			$.alert("error");
			//return "<p class='yl' fileid='"+data+"' \"></p>";
		}
	});
}
//无效投诉保存
function wxQuestionSave(){
	var id = $("#id1").val();
	var content="";
	$('input[name="select_wx"]').each(function(i) {
		if ($(this).prop('checked')) {
			var wxId = $(this).attr('value');
			$.ajax({
				type : "POST",
				url : "../wxQuestion/findById",
				dataType:"json",
				data :  {id:wxId}, 
				success : function(data) {
					content += data.content;
				}
			});
		}
	});
	$.ajax({
		type : 'post',
		data : {"id":id,"content":content},
		url : "../waterwx/wxQuestion",
		success : function(data) {
			if(data == "succ"){
				$.alert("保存成功！");
				window.location.reload();
			}else{
				$.alert("保存失败");
			}

		},
		error:function(e){
			$.alert("error")	;
		}
	});  
}

//无效投诉页面
function loadWxHtml(){
	$.getJSON('../complain/searchAllWx', function(data){
		  var len = data.length;
		var html = "<div style='text-align:center;'>无效投诉分类</div>";
		for(var i=0; i<len;i++){
			var wx = data[i];
			for(var key in wx){
				if(!wx[key] && typeof wx[key] != "undefined" && wx[key] != 0){
					wx[key] = "";
				}
			}
			
			html+=' <div style="padding-top:15px;" class="weui_cell_bd weui_cell_primary">';
			html+='<input style="width:5%;" type="checkbox" id="'+wx.id+'" name="select_wx" value="'+wx.id+'">';
			html+=wx.title;
			html +='</div>\n';
			//html += '<a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'">删除</a>';
		}
		html+=' <button id="saveWx" onclick="wxQuestionSave();"  class="weui_btn weui_btn_primary">保存</button>';
		html+='<button id="back"  class="weui_btn close-popup weui_btn_default">返回</button>';
		$("#wxDiv").empty().append(html);
	});
}
//返回上级处理的页面
function loadFeedbackHtml(){
	var qId = $("#id1").val();
	
	$.getJSON('../complain/searchAllFeedback?qId='+qId, function(data){
		  var len = data.length;
		var html = "<div style='text-align:center;'>反馈上级处理</div>";
		for(var i=0; i<len;i++){
			var fb = data[i];
			for(var key in fb){
				if(!fb[key] && typeof fb[key] != "undefined" && fb[key] != 0){
					fb[key] = "";
				}
			}
			html+='<div class="weui_media_box weui_media_text">';
			html+='<h4 class="weui_media_title">反馈人：'+fb.username+'</h4>';
			html+='<h5 >反馈人角色：'+fb.userrole+'</h5>';
			html+='<p class="weui_media_desc">反馈内容：'+fb.content+'</p></div>';
			html +='</div>\n';
			//html += '<a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'">删除</a>';
		}
		html+='<div>反馈内容</div>';
		html+=' <div class="weui_cell_bd weui_cell_primary">'+
            '<textarea class="weui_textarea"  rows="3" id="feedbackContent"></textarea>'+
            '<input style="display:none;" id="feedbackQId">'+
            '</div>';
		html+=' <button id="saveFeedback" onclick="saveFeedback();"   class="weui_btn weui_btn_primary">反馈</button>';
		html+='<button id="back"  class="weui_btn close-popup weui_btn_default">返回</button>';
		$("#feedbackDiv").empty().append(html);
		$("#feedbackQId").val(qId);
	});
}
//保存反馈信息
function saveFeedback(){
	var qid =  $("#feedbackQId").val();
	var content = $("#feedbackContent").val();
	$.ajax({
		type : 'post',
		data : {'questionId':qid,'content':content},
		url : "../feedback/saveFeedbackByWeixin",
		success : function(flag) {
			if(flag == "succ"){
				$.alert("保存成功");
				window.location.reload();
			}
		},
		error:function(e){
			$.alert("error");
			//return "<p class='yl' fileid='"+data+"' \"></p>";
		}
	});
}
//删除反馈
function deleteFeedback(id){
	$.ajax({
		type : 'post',
		data : {'id':id},
		url : "../feedback/deleteFeedback",
		success : function(flag) {
			if(flag == "succ"){
				alert("删除成功");
				window.location.reload();
			}
		},
		error:function(e){
			alert("error");
			//return "<p class='yl' fileid='"+data+"' \"></p>";
		}
	});
}
wx.ready(function(){
	var serverId = [], localIds = [];
	var _x,_y, uploadData = {};
	
	/**
	 * 选择区域
	 */
	$.getJSON('../waterwx/findAllArea', function(data){
		  var len = data.length;
		  var html = "<option value=\"\">选择行政区域</option>";
		  for(var i=0; i<len; i++){
			  if(data[i]){
				  html += "<option value="+data[i]+">"+data[i]+"</option>\n";
			  }
			 
		  }
		  $("#area").append(html);
	});
	
	$('#chooseImage').on("click", function(event){
		if(localIds.length<5){
			 wx.chooseImage({
				count: 5,
			    sizeType: ['original', 'compressed'],
			    sourceType: ['album', 'camera'],
				success : function(res) {
					var ids = res.localIds;
					var len = ids.length;
					for(var i=0; i<len; i++){
						localIds.push(ids[i]);
						var img = '<li class="weui_uploader_file" index="'+ids[i]+'" style="background-image:url('+ids[i]+');"><i class="weui_icon_cancel" style="float:right;"></i></li>';
						$(".weui_uploader_files").append(img);
						$(".weui_cell_ft").html(localIds.length+"/5");
					}
					$(".weui_icon_cancel").each(function(index, it){
						var item = $(it);
						item.on("click",function(){
							var de = item.attr("index");
							item.parent().remove();
							for(var k=0; k<localIds.length; k++){
								if(de === localIds[k]){
									localIds.splice(k,1);
									serverId.splice(k,1);
								}
							}
							
						});
					});	
					upload();
				}
			});
		}else{
			alert("最多只能上传5张图片");
		}
	});
	
	
	var poi = 0;
    function upload() {
      wx.uploadImage({
        localId: localIds[poi],
        success: function (res) {
        	poi++;
          serverId.push(res.serverId);
          if (poi < len) {
            upload();
          }
        },
        fail: function (res) {
          alert(JSON.stringify(res));
        }
      });
    }
	
	$("#gohome").on("click", function(){wx.closeWindow();});
	$("#updload_data").on("click", function(){
		uploadComplain(uploadData);
		//upload();
	});
	
	
	function uploadComplain(){
		var questioncontent = $("#questioncontent").val();
		var questionposition = $("#questionposition").val();
		var reachname = $("#reachname").val();
		var area = $("#area").val();
		var phone = $("#phone").val();
		
		if(!area){
			alert("请选择行政区域");
			return ;
		}
		
		if(!localIds){
			alert("请选择上传照片");
			return ;
		}
		if(!questioncontent){
			alert("请填写问题描述");
			return ;
		}
		if(!questionposition){
			alert("请填写问题位置");
			return ;
		}
		uploadData =  {"serverId": serverId.join(","), "questioncontent":questioncontent,
				 "reachname":reachname,"area":area,"phone":phone, "x":_x,"y":_y,"questionposition":questionposition};
		$("#updload_data").off("click");
		$("#updload_data").removeClass("weui_btn_primary");
		$("#updload_data").addClass("weui_btn_disabled weui_btn_default");
		$.post('../weixin/savecomplain', uploadData,
			function(response){
				if(response == "true"){
					alert("您的投诉我们已收到，非常感谢您对广州河道治水工作的积极参与和支持！");
					location.href = "../weixin/encourage";
				}
		});
	}
	
//end;
});
