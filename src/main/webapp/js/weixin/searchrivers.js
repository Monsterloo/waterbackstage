$(document).ready(function(){
	var questType;
	var areaname;
	$("#type_lists").hide();
	allTotal();
	
	//全市统计
	function allTotal(){
		//全市统计
		$.getJSON('../waterwx/getRiverGroupByArea', function(data){
			var html="<span>全市河道统计数据</span>";
			html+="<span style='float:right;'>数量</span>";
			$("#collect_title").html(html);
			fillQuestType(data);
		});
	};
	
	function fillQuestType(total){
		if(!questType){
			$.getJSON('../waterwx/findAllArea', function(data){
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
		lookAreaRiver();
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
				html += '<a class="weui_cell lookAreaRiver" quest="'+data[i].name+'" href="javascript:;">\n';
			    html += '<div class="weui_cell_bd weui_cell_primary">\n';
			    html += '<p>'+data[i].name+'</p></div>\n';
			    html +=  ' <div class="weui_cell_ft" style="color:#FF0000;">'+data[i].value+'</div>\n';
			    html +='</a>\n'
			}
			
		}
		$("#collect_list").empty().append(html);
	}
	//添加查看详情的事件
	function lookAreaRiver(){
		$(".lookAreaRiver").each(function(index, it){
			var item = $(it);
			item.on("click", function(){
				$("#search").show();
				areaname=item.attr("quest");
				var param = "&area="+item.attr("quest");
				$("#type_title").html('&nbsp;&nbsp;&nbsp;&nbsp;'+item.attr("quest")+'河涌列表');
				$.getJSON("../waterwx/getReDataByArea?"+param, function(data){
					var len = data.length;
					var html = "";
					for(var i=0; i<len;i++){
						var quest = data[i];
						for(var key in quest){
							if(!quest[key] && typeof quest[key] != "undefined" && quest[key] != 0){
								quest[key] = "";
							}
						}
						html+='<a href="#" style="color: black;font-size: 16px;" class="quest_detail" qid="'+quest.id+'" rcode="'+quest.riverCode+'">';
						html+='<h4 style="margin-bottom:2px;font-size: 16px; rcode="'+quest.riverCode+'">河道名称：'+quest.riverName+'</h4>';
						html+='<h4 style="margin-bottom:2px;font-size: 16px;">河长：'+quest.distMgrName+'</h4>';
						html+='<p style="font-size: 16px;" class="weui_media_desc">左右岸：'+quest.leftRight+'</p>';
						html+='<p style="font-size: 16px;" class="weui_media_desc">所属河段：'+quest.partName+'</p></a>';
						html += '<div class="weui_media_box weui_media_text">';
						html += '<a href="#" style="color: #999;font-size: 16px;" class="waterquality_detail" waterquality="'+quest.id+'">';
						html += '&nbsp;&nbsp;水质情况</a></div>';
					}
					$("#type_lists").show();
					$("#type_list").show();
					$("#type_list").empty().html(html);
					$("#total_datalist").show();
					$("#detail_2").hide();
					$("#total_list").hide();
					
					$(".quest_detail").each(function(index,it){
						var item = $(it);
						var params = "&id="+item.attr("qid")+"&rivercode="+item.attr("rcode");
						item.on("click",function(){
							var url = "../weixin/responsibilitydetail?"+params;
							$("#detail_iframe").show();
							$("#detail_iframe").attr("src", url);
							$("#type_lists").hide();
							$("#collect_detail").show();
							$("#detail_3").show();
							$("#detail_4").hide();
							$("#total_datalist").hide();
						});
					});
				});
			});
		});
	}

	$("#detail_1").on("click",function(){
		$("#detail_iframe").hide();
		$("#detail_2").hide();
		$("#type_list").hide();
		$("#total_datalist").hide();
		$("#total_list").show();
		areaname="";
	});
	$("#detail_2").on("click",function(){
		$("#total_datalist").show();
		$("#type_list").show();
		$("#type_list1").hide();
		$("#type_title").show();
		$("#detail_2").hide();
		$("#detail_1").show();
	});
	$("#detail_3").on("click",function(){
		$("#type_lists").show();
		$("#total_datalist").show();
		$("#type_list").show();
		$("#type_list1").hide();
		$("#detail_iframe").hide();
		$("#detail_3").hide();
	});
	$("#detail_4").on("click",function(){
		$("#type_lists").show();
		$("#detail_iframe").hide();
		$("#detail_4").hide();
	});
	
	 $('#manageSearchBtn').on('click',function(){
		    var reachname = $("#reachname").val();
		    if(!reachname){
				alert("请输入河道名称");
				return ;
			}
		    var param="&reachname="+reachname+"&area="+areaname;
			$.getJSON("../waterwx/getReByRiverName?"+param, function(data){
					var len = data.length;
					if(len<=0){
						$("#type_title").show();
						$("#type_list1").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;"+"暂无数据！");
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
						html+='<a href="#" style="color: black;font-size: 16px;" class="re_detail" rid="'+quest.id+'" rcode="'+quest.riverCode+'">';
						html+='<h4 style="margin-bottom:2px;font-size: 16px;">河道名称：'+quest.riverName+'</h4>';
						html+='<h4 style="margin-bottom:2px;font-size: 16px;">河长：'+quest.distMgrName+'</h4>';
						html+='<p style="font-size: 16px;" class="weui_media_desc">左右岸：'+quest.leftRight+'</p>';
						html+='<p style="font-size: 16px;" class="weui_media_desc">所属河段：'+quest.partName+'</p></a>';
						html += '<div class="weui_media_box weui_media_text">';
						html += '<a href="#" style="color: #999;font-size: 16px;" class="waterquality_detail" waterquality="'+quest.id+'">';
						html += '&nbsp;&nbsp;水质情况</a></div>';
					
					}
					$("#type_list1").show();
					$("#type_list1").empty().html(html);
					$("#type_list").hide();
					$("#detail_1").hide();
					$("#detail_2").show();
					$("#total_datalist").hide();

					
					$(".re_detail").each(function(index,it){
						var item = $(it);
						var params = "&id="+item.attr("rid")+"&rivercode="+item.attr("rcode");
						item.on("click",function(){
							var url = "../weixin/responsibilitydetail?"+params;
							$("#detail_iframe").show();
							$("#detail_iframe").attr("src", url);
							$("#type_lists").hide();
							$("#detail_3").hide();
							$("#detail_4").show();
							$("#total_datalist").hide();
							$("#collect_detail").show();
						});
					});

			});
		});

	//end;
});

