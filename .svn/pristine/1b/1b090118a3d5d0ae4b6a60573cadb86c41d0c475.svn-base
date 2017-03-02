var lengthCoast = "";
var riverlength = "";
var streetName = "";
var leftRigth = "";
var partName = "";
$(function() {  

	$('#imgShow,#wx_question,#feedbackQuestion').on('hide.bs.modal', function () {
		setTimeout(function(){
			$("body").attr("style","text-align:center;");
		}, 500);

	});
});
//回复受理
function answer(qId){
	//$("#answer_table tbody").empty();
	$("#answer_model").modal('toggle');
	$("#qId").val(qId);
	var answer_table = $('#answer_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		destroy:true,
		info:false,
		ajax: {
			url : "../waterwx/searchAnswer",
			type:'post',
			data:{'questionId':qId}
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' class='tdId' id='" + data +"' name='id' value='" + data +"'/>";
		        	 }
		         },
		         {'data':'answercontent'},
		         {'data':'answerDate'},
		         {'data': 'id',
		        	 'render': function ( data, type, full, meta ) {
		        		 return "<p class='yl' fileid='"+data+"' \"></p>";
		        	 }
		         }
		         ]
	});
	//$("#answer_model").show();
	setTimeout(function(){
		$(".tdId").each(function(i){
			var data = $(this).val();
			var tr = $(this).parent().parent();
			$.ajax({
				type : 'post',
				data : {'id1':data},
				url : "../waterwx/findAnswer",
				success : function(flag) {
					if(flag == "succ"){
						var p = "<p class='yl' fileid='"+data+"' onclick=\"deleteAnswer('"+data+"');\">删除</p>";
						tr.find("td:last").append(p);
					}
				},
				error:function(e){
					//return "<p class='yl' fileid='"+data+"' \"></p>";
				}
			});
		});
	}, 200);
}
//删除回复
function deleteAnswer(id){
	if (confirm("确认删除选中的文件！？")) {
		// 更换Action位置
		$.ajax({
			type : 'post',
			data : {'id':id},
			url : "../waterwx/deleteAnswer",
			success : function(data) {
				if(data == "succ"){
					alert("删除成功！");
					window.location.reload();
				}else{
					alert("删除失败");
				}

			},
			error:function(e){
				alert("error")	;
			}
		}); 
	}
}
//保存回复
function saveAnswer(){
	var qid = $("#qId").val();
	var content = $("#content").val();
	$("#con").val(content);
	$("#answerForm").submit();
}
//无效投诉保存
function wxQuestionSave(){
	var id = $("#id1").val();
	var content="";
	$('#checkAllwx').on('click', function() {
		$("input[name='select_wx']").prop('checked', $(this).prop('checked'));
	});
	$('input[name="select_wx"]').each(function(i) {
		if ($(this).prop('checked')) {
			var wxId = $(this).attr('value');
			$.ajax({
				type : "POST",
				url : "../wxQuestion/findById",
				async:false,
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
		async:false,
		success : function(data) {
			if(data == "succ"){
				alert("保存成功！");
				window.location.reload();
			}else{
				alert("保存失败");
			}

		},
		error:function(e){
			alert("error")	;
		}
	});  
}

//无效投诉
function wxQuestion(){
	$("#wx_question").modal('toggle');
	$('#checkAllwx').on('click', function() {
		$("input[name='select_wx']").prop('checked', $(this).prop('checked'));
	});
	var wxTable = $('#wx_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		destroy:true,
		info:false,
		ajax: {
			url : "../wxQuestion/searchAllWx",
			type:'post',
			data:{}
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='select_wx' value='" + data +"'>";
		        	 }
		         },
		         {'data':'title'}
		         ]
	});

}
//返回上级处理的投诉信息
function backQuestion(){
	$("#feedbackQuestion").modal("toggle");
	var qId = $("#id1").val();
	$("#feedbackQId").val(qId);
	var answer_table = $('#feedback_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		destroy:true,
		info:false,
		ajax: {
			url : "../feedback/searchAllFeedback",
			type:'post',
			data:{'questionId':qId}
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' class='feedbackId' id='" + data +"' name='id' value='" + data +"'/>";
		        	 }
		         },
		         {'data':'username'},
		         {'data':'userrole'},
		         {'data':'content'},
		         {'data': 'id',
		        	 'render': function ( data, type, full, meta ) {
		        		 return "<p class='yl' fileid='"+data+"' \"></p>";
		        	 }
		         }
		         ]
	});
	//$("#answer_model").show();
	setTimeout(function(){
		$(".feedbackId").each(function(i){
			var data = $(this).val();
			var tr = $(this).parent().parent();
			$.ajax({
				type : 'post',
				data : {'id1':data},
				url : "../feedback/findFeedback",
				success : function(flag) {
					if(flag == "succ"){
						var p = "<p class='yl' fileid='"+data+"' onclick=\"deleteFeedback('"+data+"');\">删除</p>";
						tr.find("td:last").append(p);
					}
				},
				error:function(e){
					//return "<p class='yl' fileid='"+data+"' \"></p>";
				}
			});
		});
	}, 200);
}
//保存反馈信息
function saveFeedback(){
	var qid = $("#feedbackQId").val();
	var content = $("#feedbackContent").val();
	$.ajax({
		type : 'post',
		data : {'questionId':qid,'content':content},
		url : "../feedback/saveFeedback",
		success : function(flag) {
			if(flag == "succ"){
				alert("保存成功");
				window.location.reload();
			}
		},
		error:function(e){
			alert("error");
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