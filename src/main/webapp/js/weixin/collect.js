$(document).ready(function(){
	var questType;
	$.getJSON('../waterwx/findAllArea', function(data){
		  var len = data.length;
		  var html = "<option value=\"0\">选择行政区域</option>";
		  for(var i=0; i<len; i++){
			  if(data[i]){
				  html += "<option value="+data[i]+">"+data[i]+"</option>\n";
			  }
		  }
		  $("#area").append(html);
	});
	//问题分类
	function fillQuestType(total){
		if(!questType){
			$.getJSON('../waterwx/findAllTypeByTj', function(data){
				  var len = data.length;
				  questType = new Array();
				  for(var i=0; i<len; i++){
					 var obj = {"name":data[i],"value":0};
					 questType.push(obj);
				  }
				  collectist(total);
			});
		}else{
			collectist(total);
		}
	}
	function collectist(total){
		for(var i=0; i<questType.length; i++){
			questType[i].value = 0;
			for(var k=0;k<total.length; k++){
				if(questType[i].name === total[k][0]){
					questType[i].value = total[k][1];
					break;
				}
			}
		}
		htmlTotle(questType);	
		lookQuest();
	}
	
	function htmlTotle(data){
		var html="";
		for(var i=0; i<data.length; i++){
			if(data[i].value == 0){
				html += '<div class="weui_cell">\n';
			    html += '<div class="weui_cell_bd weui_cell_primary">\n';
			    html += '<p>'+data[i].name+'</p></div>\n';
			    html +=  ' <div class="weui_cell_ft" style="color:#FF0000;">'+data[i].value+'</div>\n';
			    html +='</div>\n'
			}else{
				html += '<a class="weui_cell lookQuest" quest="'+data[i].name+'" href="javascript:;">\n';
			    html += '<div class="weui_cell_bd weui_cell_primary">\n';
			    html += '<p>'+data[i].name+'</p></div>\n';
			    html +=  ' <div class="weui_cell_ft" style="color:#FF0000;">'+data[i].value+'</div>\n';
			    html +='</a>\n'
			}
			
		}
		$("#collect_list").empty().append(html);
	}
	//添加查看详情的事件
	function lookQuest(){
		$(".lookQuest").each(function(index, it){
			var item = $(it);
			item.on("click", function(){
				var str = $("#area").val();
				var param = "area=";
				if(str == 0){
					param += "&riverName=";
				}else{
					param += str+"&riverName="+$("#riverName").val();
				}
				
				param += "&questiontype="+item.attr("quest");
				$("#type_title").html("&nbsp;&nbsp;&nbsp;&nbsp;"+item.attr("quest")+"投诉分类列表");
				$.getJSON("../waterwx/getQuestionByWx?"+param, function(data){
					var len = data.length;
					var html = "";
					for(var i=0; i<len;i++){
						var quest = data[i];
						for(var key in quest){
							if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
								quest[key] = "";
							}
						}
						
						html+='<div><a href="#" class="weui_panel_ft quest_detail" qid="'+quest.id+'"><h4 class="weui_media_title">河道名称：'+quest.reachname+'</h4>';
						html+='<h5 >问题位置：'+quest.questionposition+'</h5>';
						html+='<p class="weui_media_desc">问题描述：'+quest.questioncontent+'</p>';
						html += '</a><div>';
					}
					$("#type_list").empty().html(html);
					$("#total_datalist").show();
					$("#total_list").hide();
					
					$(".quest_detail").each(function(index,it){
						var item = $(it);
						item.on("click",function(){
							var url = "../weixin/questiondetail?id="+item.attr("qid");
							$("#detail_iframe").attr("src", url);
							$("#total_datalist").hide();
							$("#collect_detail").show();
						});
					});
				});
			});
		});
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
	
	//全市统计
	function allTotal(){
		//全市统计
		$.getJSON('../waterwx/getTypeByArea', function(data){
			$("#collect_title").html("全市统计数据");
			fillQuestType(data);
		});
	};
	allTotal();
	
	$("#area").on("change",function(){
		var area = $("#area").val();
		$("#collect_title").html(area+"统计数据");
		if(area){
			$.getJSON('../waterwx/getRiverByArea?area='+area, function(data){
				  var len = data.length;
				  var html = "<option value=\"0\">选择河段</option>";
				  for(var i=0; i<len; i++){
					  if(data[i]){
						  html += "<option value="+data[i].riverName+">"+data[i].riverName+"</option>\n";
					  }
				  }
				  $("#riverName").empty().append(html);
			});
			//按区域统计
			$.getJSON('../waterwx/getRiverByAreaToTj?area='+area, function(data){
				fillQuestType(data);
			});
		}else{
			var html = "<option value=\"\">选择河段</option>";
			$("#questType").empty().append(html);
			allTotal();
		}
		
	});
	
	//按区域与河流名统计
	$("#riverName").on("change",function(){
		var area = $("#area").val();
		var river = $("#riverName").val();
		$("#collect_title").html(area+","+river+"统计数据");
		if(river){
			$.getJSON('../waterwx/getTypeByRiverNameAndAreaTj?area='+area+"&rivername=river", function(data){
				fillQuestType(data);
			});
		}else{
			 $("#collect_list").empty();
		}
	});
	
	//end;
});

