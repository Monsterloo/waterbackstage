$(document).ready(function(){
	var ro = $("#ro").val();
	if(ro == "1"){
		$(".dslts").attr("style","display:none;");
	}
	$(".dslts").click(function(){
		lookQuest("1");
	});
	$(".yslts").click(function(){
		lookQuest("2");
	});
	$(".yhfts").click(function(){
		lookQuest("0");
	});
	$(".wjts").click(function(){
		lookQuest("35");
	});
	
	//添加查看详情的事件
	function lookQuest(menu){
		$("#menu").val(menu);
		var sta = "";
		if(menu == "0"){
			sta = "已回复投诉";
		}else if(menu == "1"){
			sta = "<font color='#FF0000'>待受理投诉</font>";
		}else if(menu == "2"){
			sta = "<font color='#EEAD0E'>已受理投诉</font>";
		}else if(menu == "35"){
			sta = "完结投诉";
		}
		//$(".lookQuest").each(function(index, it){
			//var item = $(it);
			//item.on("click", function(){
				//param += "&questiontype="+item.attr("quest");
				$("#type_title").html("&nbsp;&nbsp;&nbsp;&nbsp;"+sta);
				$.getJSON('../complain/searchByMenu?menu='+menu, function(data){
					  var len = data.length;
					var html = "";
					for(var i=0; i<len;i++){
						var quest = data[i];
						for(var key in quest){
							if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
								quest[key] = "";
							}
						}
						var sta = "";
						if(quest.status == "0"){
							sta = "已回复投诉";
						}else if(quest.status == 1){
							sta = "<font color='#FF0000'>待受理投诉</font>";
						}else if(quest.status == 2){
							sta = "<font color='#EEAD0E'>已受理投诉</font>";
						}else if(quest.status == 3){
							sta = "<font color='#7AC5CD'>无效投诉</font>";
						}else if(quest.status == 5){
							sta = "完结投诉";
						}
						html+='<div class="weui_media_box weui_media_text">';
						html+='<h4 class="weui_media_title">行政区域：'+quest.area+'</h4>';
						html+='<h5 >问题位置：'+quest.questionposition+'</h5>';
						html+='<p class="weui_media_desc">问题描述：'+quest.questioncontent+'</p></div>';
						html += '<a href="#" class="weui_panel_ft quest_detail" statu="'+menu+'" qid="'+quest.id+'">详细内容</a>';
					}
					$("#type_list").empty().html(html);
					$("#total_datalist").show();
					$("#total_list").hide();
					
					$(".quest_detail").each(function(index,it){
						var item = $(it);
						item.on("click",function(){
							var url = "../complain/questiondetail?id="+item.attr("qid");
							var statu = item.attr("statu");
							$("#menu1").val(statu);
							$("#detail_iframe").attr("src", url);
							$("#total_datalist").hide();
							$("#collect_detail").show();
						});
					});
				});
		//	});
		//});
	}
	$("#collect_detail").on("click", function(){
		$("#detail_iframe").attr("src", "");
		$("#total_datalist").show();
		$("#collect_detail").hide();
	});
	$("#detail_1").on("click",function(){
		$("#total_datalist").hide();
		$("#total_list").show();
	});
	
	//end;
});

