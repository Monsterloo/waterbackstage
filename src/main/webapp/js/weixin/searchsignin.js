$(document).ready(function(){
	/*$("#search_startTime,#search_endTime").datetimepicker({
		language: 'zh-CN',
		format: "yyyy-MM-dd",
		minView: 2,
		autoclose: true,
		todayBtn: true
	}); */
	
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
	    
	    /*$.getJSON("../waterwx/findAllQuestion", function(data){
	    	var len = data.length;
			var html = "";
			for(var i=0; i<len;i++){
				var quest = data[i];
				for(var key in quest){
					if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
						quest[key] = "";
					}
				}
			html+='<div class="weui_media_box weui_media_text">';
			html+='<h4 >受理编号：'+quest.code+'</h4>';
			html+='<h4 class="weui_media_title">河道名称：'+quest.reachname+'</h4>';
			html+='<h4 class="weui_media_title">河长：'+quest.personname+'</h4>';
			html+='<h5 >问题位置：'+quest.questionposition+'</h5>';
			html+='<p class="weui_media_desc">问题描述：'+quest.questioncontent+'</p></div>';
			html += '<a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'">详细内容</a>';
			
			} 
			$("#type_list").empty().html(html);
			$("#total_datalist").show();
			
			$(".quest_detail").each(function(index,it){
				var item = $(it);
				item.on("click",function(){
					var url = "../weixin/questiondetail?id="+item.attr("qid");
					$("#detail_iframe").show();
					$("#detail_iframe").attr("src", url);
					$("#total_datalist").hide();
					$("#total_list").hide();
					$("#collect_detail").show();
				});
			});
	    });
	    */
	    
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
			$("#total_datalist").hide();
			$("#total_list").show();
		});
		
 	     
	    
		 $('#manageSearchBtn').on('click',function(){
			 
			 	$("#total_list").hide();
			    var reachname = $("#reachname").val();
			    var grade = $("#grade").val();
	     		var area = $("#area").val();

	    		var param="&reachname="+reachname+"&grade="+grade+"&area="+area;
				$.getJSON("../waterwx/findSignInByWx?"+param, function(data){
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
							var grade="";
							var a=quest.grade;
							if(a=="1"){
								grade="一般河道";
							}else{
								grade="重要河道";	
							}
						html+='<a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'">';
						html+='<h4 >河道名称：'+quest.reachname+'</h4>';
						html+='<h4 class="weui_media_title">河道等级：'+grade+'</h4>';
						html+='<h5 >签到位置：'+quest.signinposition+'</h5>';
						html+='<h5 >签到人：'+quest.signinname+'</h5>';
						html+='<p class="weui_media_desc">签到时间：'+quest.signinDate+'</p></a>\n';
						
						}
						$("#type_list").empty().html(html);
						$("#total_datalist").show();
						
						$(".quest_detail").each(function(index,it){
							var item = $(it);
							item.on("click",function(){
								var url = "../weixin/signindetail?id="+item.attr("qid");
								$("#detail_iframe").show();
								$("#detail_iframe").attr("src", url);
								$("#total_datalist").hide();
								$("#total_list").hide();
								$("#collect_detail").show();
							});
						});

				});
			}); 
	    
	    
		$("#gohome").on("click", function(){
			window.opener=null;    
			//window.opener=top;    
			window.open("","_self");    
			window.close();    
			}  );
		
})

