$(document).ready(function(){
	$("#search_startTime,#search_endTime").datetimepicker({
		language: 'zh-CN',
		format: "yyyy-MM-dd",
		minView: 2,
		autoclose: true,
		todayBtn: true
	}); 
  	//问题类型
	   $.ajax({
			type : "POST",
			url : "../waterwx/getquestiontype",
			data :  {}, 
			dataType : "json",
			async:false,
			success : function(data) {
				$("#qtype").empty().append("<option>请选择</option>");
				$.each(data, function(i, value){
					$("#qtype").append("<option >"+value.detail+"</option>");
				});
							
			}
						
		});
	    
	    
	    $("#collect_detail").on("click", function(){
			$("#detail_iframe").attr("src", "");
			$("#total_datalist").show();
			$("#collect_detail").hide();
		});
		$("#detail_1").on("click",function(){
			$("#detail_iframe").hide();
			$("#total_datalist").show();
			$("#total_list").hide();
		});
		$("#detail_2").on("click",function(){
			$("#detail_iframe").hide();
			$("#total_datalist").show();
			$("#total_list").show();
			$("#type_list1").show();
			$("#detail_2").hide();
		});
		$("#detail_3").on("click",function(){
			$("#detail_iframe").hide();
			$("#total_datalist").hide();
			$("#total_list").show();
			$("#type_list").hide();
			$("#type_list1").show();
			$("#detail_3").hide();
		});
		
 	     
	    
		 $('#manageSearchBtn').on('click',function(){
			 	$("#type_list1").hide();
			 	$("#total_list").hide();
			 	$("#detail_2").show();
			 	searchQuestion();
			}); 
	    
		 function searchQuestion(){
			 	var code = $("#code").val();
			    var reachname = $("#reachname").val();
			    var qtype = $("#qtype").val();
	     		var search_startTime = $('#search_startTime').val();
	     		var search_endTime = $('#search_endTime').val();
	     		var person = $("#person").val();
	     		
	    		var param="&code="+code+"&reachname="+reachname+"&qtype="+qtype+"&startTime="+search_startTime+"&endTime="+search_endTime+"&person="+person;
				$.getJSON("../waterwx/findQuestionByWx?"+param, function(data){
						var len = data.length;
						if(len<=0){
							$("#type_title").show();
							$("#type_title").html("&nbsp;&nbsp;&nbsp;&nbsp;"+"暂无数据！");
						}else{
							$("#type_title").hide();
						}
						var html = "";
						for(var i=0; i<len;i++){
							var quest = data[i];
							for(var key in quest){
								if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
									quest[key] = "";
								}
							}
						html+='<div><a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'">';
						html+='<h4 >受理编号：'+quest.code+'</h4>';
						html+='<h4 class="weui_media_title">河道名称：'+quest.reachname+'</h4>';
						html+='<h4 class="weui_media_title">河长：'+quest.personname+'</h4>';
						html+='<h5 >问题位置：'+quest.questionposition+'</h5>';
						html+='<p class="weui_media_desc">问题描述：'+quest.questioncontent+'</p></a></div>';
						
						} 
						$("#type_list").empty().html(html);
						$("#total_datalist").show();
						$("#type_list").show();
						
						$(".quest_detail").each(function(index,it){
							var item = $(it);
							item.on("click",function(){
								var url = "../weixin/questiondetail?id="+item.attr("qid");
								$("#detail_iframe").show();
								$("#detail_iframe").attr("src", url);
								$("#total_datalist").hide();
								$("#total_list").hide();
								$("#detail_1").show();
								$("#detail_3").hide();
								$("#collect_detail").show();
							});
						});
				});
		 }
		 
		 function searchQuestionAll(){
			 var code = $("#code").val();
			    var reachname = "";
			    var qtype = "";
	     		var search_startTime ="";
	     		var search_endTime ="";
	     		var person = "";
	     		
	    		var param="&code="+code+"&reachname="+reachname+"&qtype="+qtype+"&startTime="+search_startTime+"&endTime="+search_endTime+"&person="+person;
				$.getJSON("../waterwx/findQuestionByWx?"+param, function(data){
						var len = data.length;
						if(len<=0){
							$("#type_title").show();
							$("#type_title").html("&nbsp;&nbsp;&nbsp;&nbsp;"+"暂无数据！");
						}else{
							$("#type_title").hide();
						}
						var html = "";
						for(var i=0; i<len;i++){
							var quest = data[i];
							for(var key in quest){
								if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
									quest[key] = "";
								}
							}
						html+='<div><a href="#" class="weui_panel_ft question_detail" qid="'+quest.id+'">';
						html+='<h4 >受理编号：'+quest.code+'</h4>';
						html+='<h4 class="weui_media_title">河道名称：'+quest.reachname+'</h4>';
						html+='<h4 class="weui_media_title">河长：'+quest.personname+'</h4>';
						html+='<h5 >问题位置：'+quest.questionposition+'</h5>';
						html+='<p class="weui_media_desc">问题描述：'+quest.questioncontent+'</p></a></div>';
						
						} 
						 $("#type_list1").empty().html(html);
						 $("#detail_2").hide();
						 $("#total_datalist").show();
						 $("#type_title").hide();
						 
						$(".question_detail").each(function(index,it){
							var item = $(it);
							item.on("click",function(){
								var url = "../weixin/questiondetail?id="+item.attr("qid");
								$("#detail_iframe").show();
								$("#detail_iframe").attr("src", url);
								$("#total_datalist").hide();
								$("#total_list").hide();
								$("#detail_1").hide();
								$("#detail_3").show();
								$("#collect_detail").show();
							});
						});
				});
		 }
		 
		 searchQuestionAll();
		
})

