﻿var winW;
var winH;
var openId = "";
var IsIphone=false;
var dbl_i=0;
var remind;
$(function () {
    winW = $(window).width();
    winH = $(window).height();

    var ua = navigator.userAgent.toLowerCase();
	if(ua.indexOf("iphone") >= 0) { IsIphone=true; }
	if(ua.indexOf("mobile") < 0) { $("body").html("<span style='font-size:40px;'>请在手机微信端查看！</span>"); return; }
	
	remind = $("#home_page_remind").val();
    openId = $("#home_page_openId").val();
    if(remind==""){ remind = -1; }
    
    Btn_Init();
    Position_Init();
    Fn_Init();
})

function Fn_Init() {
    getUserInfo();
    setTimeout("getBubbleCount()",5000);
}

function Position_Init() {
	if(remind == -1) { initHome(); }
	else { $(".remind").show(); }

	$(".friend-content").show();
    $(".set").css({ "height": (winH - 55) + "px" });
	$(".guide").css({ "height": (winH - 55) + "px" });
    $(".complain-activity-img").css("left", ((winW - 834) / 2) + "px")
    $(".activity-no").css("left", ((winW - 834) / 2 + 166) + "px")
    $(".activity-yes").css("right", ((winW - 834) / 2 + 139) + "px")
	$(".search").css("height", winH + "px")
	$("#tx-map-iframe").css("height", "88%")

}

function Btn_Init() {
    $(".remind-checkImg").click(function () { RemindCheck(this); })
    $(".remind-iknow").click(function () { saveNoRemind(); })
    $(".friend-head-search").click(function () { showSearch(); })
    $(".menu-item-img").click(function () { menuClick($(this));})
    
    $(".menu-friend").click(function(){
    	dbl_i++;
    	setTimeout(function(){dbl_i=0;},500)
    	if(dbl_i>1){RefreshHome();}
    	else
    	{$(".friend-complain-list").show(); $(".friend-head-search").show();}
    	
    })

    $(".search-btn-submit").click(function () { filterComplainList(); })
    $(".search-btn-cancel").click(function () { $(".search").hide(); })
    $(".search-item").click(function () { getSearchListData(this); })
    $(".search-select-item").click(function () { SeacrchItemSelect(this,true); })
    $(".search-select-btn-cancel").click(function () { $(".search-selectdialog").hide(); })
    $(".search-select-btn-submit").click(function () { selectBtnSubmit(); })
    $(".reach-btn-back").click(function () { $(".reach").hide(); })
    $(".head-photo-img").click(function () { getMyComplainList(''); })
    $(".my-list-menu-item").click(function () { getMyComplainList($(this).attr("id")); $(this).addClass("my-list-menu-item-selected").siblings().removeClass("my-list-menu-item-selected"); })
    $(".menu-complain").click(function () { goComplain(); })
    $(".complain-right").click(function () { $(".right-selectdailog").show(); })
    $(".complain-share-item").click(function () { shareClick(this); })
    $(".bind-phone-submit").click(function () { bindPhoneNumber();$(".bind-phone").hide();$(".rewards").show(); })
    $(".bind-phone-ignore").click(function () { $(".bind-phone").hide(); })
    $(".rewards-bottom-buttom").click(function () { rewardsBtnClick(); })
    $(".star-icon").click(function () { starClick(this); })
}

function filterComplainList(){
	$(".search").hide();
	var districtIds = $('#id-search-selected-district').val();
	var questiontypes = $('#id-search-selected-type').val();
	var time = $('#search-selected-time').val();
	var rivername = $('.search-input-river').val();
	//alert(districtIds+":"+questiontypes+":"+time);
	getComplainlist('FALSE',districtIds,questiontypes,time,rivername,false);
	//getComplainListByFilter('',districtIds,questiontypes,time);
}
function rewardsBtnClick() {
    comlainClose();
    $(".rewards").hide();
    getMyComplainList('');
}

function activity(action) {
	$.ajax({
		url:'../weixinComplain/isJoinActivity',
		type:'GET',
		dataType:'text',
		data:{isJoinActivity:action,openId:openId},
		success:function(data){}
	});
	
    $(".complain-activity").hide();
    $(".complain").hide();
    if (action == -1) { 
    	$(".complain").hide(); getMyComplainList(''); }
    else {
    	 $.ajax({
    	        type: "get",
    	        dataType: 'text',
    	        url: "../weixinComplain/checkIsBindPhoneNum",
    	        data:{'openId':openId},
    	        success: function (json) {
    	        	if (json.indexOf("RequestSuccessful") >= 0) {
    	                json = json.replace("RequestSuccessful", "");
    	                if(json==0){
    	                	$(".bind-phone").show(); 
    	                }
//    	                json = eval('(' + json + ')');
    	        	}
    	        }
    	  });
    	
    	
    }
}

function divShow(obj) { 
    
}
function replySend(){
	//TODO
	var questionId = $("#reply_questionId").val();
	var replyContent = $(".reply-input").val();
	if(replyContent.length==0){
		alert("请填写评论内容！");
		return;
	}
	$.ajax({
		//TODO
//		url:'../assets/jsons/getRepay.txt',
		url:'../weixinComplain/saveReply',
		type:'POST',
		dataType:'text',
		data:{'openId':openId,
			'questionId':questionId,
			'replyContent':replyContent
			},
		success:function(data){
			setRepayValue(data);
			$(".reply-input").val('');
		}
		
	});
	
	
}


function Send() {
	if($("#id-complain-content").val()==""){
		alert("请输入河道问题！");
		return;
	}
	
	var title=$("#id-complain-content").val();
	var address = $(".complain-content-location-text").html();
	var  coordinate=$("#complain-content-location-lon").val()+","+$("#complain-content-location-lat").val();
	var server_id = $("#hidden-img-serverId").val();
	if(server_id.length>0){
		server_id=server_id.substring(0,server_id.length);
	}
	var right = 0;
	var area = $("#complain-content-location-area").val();
	$.ajax({
		url:'../weixinComplain/saveCompain',
		type:'post',
		dataType:'text',
		data:{'openId':openId,'title':title,
			'address':address,
			'coordinate':coordinate,
			'right':right,
			'area':area,
			'serverId':server_id},
		//data:{'openId':openId,'title':title},
		success:function(json){
			/*if (json.indexOf("RequestSuccessful") >= 0) {
		        json = json.replace("RequestSuccessful", "");
		        //json = eval('(' + json + ')');
		        if(json!=0){
				alert("您的投诉我们已收到，非常感谢您对广州河道治水工作的积极参与和支持！");
	
				RefreshHome();
		        	
		        }else{
		            $(".complain-activity").show();
		        }
			}*/
		}
	});

	$.ajax({
		url:'../weixinComplain/checkIsJoinAct',
		data:{openId:openId},
		success:function(json){
			if (json.indexOf("RequestSuccessful") >= 0) {
		        json = json.replace("RequestSuccessful", "");
		        //json = eval('(' + json + ')');
		        if(json!=0){
		        	$(".rewards").show();
		        }else{
		            $(".complain-activity").show();
		        }
			}
		}
	});
		

}

function shareClick(obj) {
    var img = $(obj).find("img");
    var hidden = $(obj).find("input");
    var v=0;
    var src = img.attr("src");
    var isShare = hidden.val();
    if (isShare == "1") { src = src.replace("light", "gray");v = 0; }
    else {src = src.replace("gray", "light");  v = 1; }
    img.attr("src", src);
    hidden.val(v);
}

function rightSelected(obj) {
    var text = $(obj).find("span").html();
    var v = $(obj).find("input").val();
    $(".complain-right-selected").html(text);
    $("#hidden-complain-right").val(v);
    $(".right-selectdailog").hide();
}


function goComplain() {
	$(".complain-input").val();
    $("#uploaderInput").click()
}


function getComplainlist(isMyOwn,districtIds,type,time,rivername,isAppend) {
		loadingMask();
		var statusStr = 0;
		if(isMyOwn=="FALSE"){
			statusStr="2,3,4";
		}
		
		$.ajax({
			async:true,
			url:'../weixinComplain/getComplainList',
//			url:'../assets/jsons/getComplainList.txt',
			type:'POST',
			dataType:'text',
			//不然无法点赞 ,isMyOwn自己的还是圈子的
			data:{'statusStr':statusStr,'isMyOwn':isMyOwn,'area':districtIds,
				'questiontype':type,'complainDate':time,'reachname':rivername},
			success:function(json){
				setComplainList(json,isAppend);
				var data = "";
				$(".friend-content-item").each(function(obj,item){
					data += ($(item).attr("id")+",").replace("friend-content-item-","");
				});
				//刷点赞
				$.ajax({
					async:false,
					url:'../weixinComplain/getUserActionList',
					data:{'questionId':data,'openId':openId},
					type:'POST',
					dataType:'text',
					success:function(json){
						// RequestSuccessful[{questionId:132131,myAttention:1,myPraise:1,myRepost:1}]     RequestSuccessfulNull
						if (json.indexOf("RequestSuccessful") >= 0) {
					          json = json.replace("RequestSuccessful", "");
					          if(json=="Null"){return;}
					          json = eval('(' + json + ')');
					          $.each(json,function(i,item){
					        	  if(item.myAttention==1){
					        		  $("#attentive_img_"+item.questionId).attr("src",$("#attentive_img_"+item.questionId).attr("src").replace("gray","light"));
					        	  }
					        	  if(item.myAttention==0){
					        		  $("#attentive_img_"+item.questionId);
					        		  $("#attentive_img_"+item.questionId).attr("src",$("#attentive_img_"+item.questionId).attr("src").replace("light","gray"));
					        	  }
					        	  if(item.myPraise==1){
					        		  $("#praise_img_"+item.questionId);
					        		  $("#praise_img_"+item.questionId).attr("src",$("#praise_img_"+item.questionId).attr("src").replace("gray","light"));
					        	  }
					        	  if(item.myPraise==0){
					        		  $("#praise_img_"+item.questionId).attr("src",$("#praise_img_"+item.questionId).attr("src").replace("light","gray"));
					        	  }
					        	  if(item.myRepost==1){
					        		  $("#repost_img_"+item.questionId).attr("src",$("#repost_img_"+item.questionId).attr("src").replace("gray","light"));
					        	  }
					        	  if(item.myRepost==0){
					        		  $("#repost_img_"+item.questionId).attr("src",$("#repost_img_"+item.questionId).attr("src").replace("light","gray"));
					        	  }
					          });
						}
					}
				});
			}
		});
      
}

function getMyComplainList(status) {
	$(".friend-complain-list").hide();
    $(".friend-head-search").hide();
	$.ajax({
		url:'../weixinComplain/getComplainList',
		type:'POST',
		dataType:'text',
		data:{'statusStr':status,'isMyOwn':'TRUE'},
		success:function(data){
			setMyComplainListValue(data);
		}
	});
    $(".my-complain").show();
}

function setComplainList(json,isAppend){
	if (json.indexOf("RequestSuccessful") >= 0) {
          json = json.replace("RequestSuccessful", "");
          if(json=="Null"){
              	h = '<div class=\"weui-loadmore weui-loadmore_line\"><span class=\"weui-loadmore__tips\">暂无数据</span></div>';
              	$('.friend-complain-list').append(h);
              	return;
        	  
          }
          json = eval('(' + json + ')');
          if(isAppend ==false){$('.friend-complain-list').empty();}
          
          var iphoneFont="";
          //if(IsIphone){iphoneFont="style='font-size:45px;'"}
          
          var count = json.count;
          var str = "";
		  var index_count = 0;
		  var next_page_ ="";
		  
		  if(count == 0){$('.friend-complain-list').html("<div class=\"weui-loadmore weui-loadmore_line\"><span class=\"weui-loadmore__tips\">暂无数据</span></div>")}
          $.each(json.complainList, function (i, item) {
              var imgCount = item.photoList.listCount;
              var imglistH = (106 * Math.ceil(imgCount / 3));
			  index_count = index_count + 1;
              //var len=item.questioncontent.length;
              //var w=winW*0.96*0.97;
              //var row=Math.ceil(len*39/w)
              
              //var itemH = 360+(row*60) + imglistH;

              //var Address = item.address.length > 15 ? (item.address.substring(0, 13) + "..") : item.address;
              var location=item.questionposition;
              location= location.replace("广东省","").replace("广州市","").replace("广东","").replace("广州","");
              location= (location.length>16?location.substring(0,16):location) ;
              var Status = item.status == 0 ? "no" : "yes";
			  var lv=item.level;

              //var h = "<div class=\"friend-content-item "+ item.questionId+"\" style='height:" + itemH + "px'>"
			  var h = "<div class=\"friend-content-item "+ item.questionId+"\" id=\"friend-content-item-"+item.questionId+"\">"
			  //h += " <div style=\" width:96%; margin:0 auto;\">"
              h += "    <div class=\"content-item-top\"><div class=\"content-item-left-photo-div\"><img src=\"" + item.photo + "\" class=\"content-item-left-photo\" />"
			  h += "         <img src=\"../assets/Include/image/vip_icon"+lv+".png\"  class=\" item-photo-level\"/>"
			  h += "    </div>"
              h += "        <div class=\"content-item-username\">" + item.name + "</div>"
              h += "        <div class=\"content-item-time\">" + item.timeDesc + "</div>";
              h += "        <img src=\"../assets/Include/image/accept-" + Status + ".png\" class=\"content-item-status-img\" />";
              h += "    </div>";
              h += "    <div class=\"content-item-bottom\">"
              h += "        <div class=\"content-item-content item-content_"+item.questionId+"\"  "+iphoneFont+">" + item.questioncontent + "</div>"
              h += "        <div class=\"content-item-imglist\" style='height:+" + imglistH + "px'>";
              if(imgCount!=0){
              	 $.each(item.photoList.list, function (j, imgList) {
                       h += "            <img onclick=\"PreviewImg(this,'"+imgList.locationId+"','"+item.questionId+"')\" src=\"http://wx1.ccqm.cn/water/weixinComplain/getLittlePicById?id=" + imgList.locationId + "\" class=\"content-item-imglist-img item-img-list-"+item.questionId+"\" />"
              	 })
              }
              h += "        </div>"
              h += "        <div class=\"content-item-bottomline\">"
			  if(IsIphone){
				h += "            <img src=\"../assets/Include/image/location-light.png\" class=\"content-item-bottomline-icon\" /><span class=\"content-item-location\" onclick=\"OpenTencentMapForIphone('"+(item.x)+"','"+(item.y)+"','"+item.questionposition+"','"+location+"')\">" + location+ "</span>"
              }else
			  {
				h += "            <img src=\"../assets/Include/image/location-light.png\" class=\"content-item-bottomline-icon\" /><span class=\"content-item-location\" onclick=\"OpenTXMap('"+(item.x)+"','"+(item.y)+"','"+item.questionposition+"','"+location+"')\">" + location+ "</span>"
			  }              h += "            <span class=\"content-item-reach\" onclick=\"showReach('" + item.rivercode + "')\">" + item.riverName + "</span>"
              h += "        </div>"
			  //h += "</div>"
              h += "        <div class=\"content-item-op-div  border-top-1\">"
              	
              //转发
              h += "       <div onclick=\"Repost('"+item.questionId+"',this)\"> <div class=\"content-item-op border-none\"><img src=\"../assets/Include/image/op-repost-gray.png\" class=\"content-item-op-img op-repost\" id=\"repost_img_"+item.questionId+"\"/></div>"

              h += "<div class=\"content-item-op\"><div id='repost_"+item.questionId+"'  class=\"content-item-op-count\">" + (item.repostCount<=0?"转发":item.repostCount) + "</div></div></div>";
			  //关注
              h += "<div onclick=\"saveQuestionItem('"+item.questionId+"','attention',this)\"><div class=\"content-item-op border-none\"><img src=\"../assets/Include/image/op-attentive-gray.png\" class=\"content-item-op-img op-attentive\"  id=\"attentive_img_"+item.questionId+"\"/></div>"
              h += "            <div class=\"content-item-op\"><div  id='attention_"+item.questionId+"' class=\"content-item-op-count\">" +(item.attentionCount<=0?"关注":item.attentionCount) + "</div></div></div>";
              
              //点赞
              h += "   <div onclick=\"saveQuestionItem('"+item.questionId+"','praise',this)\">    <div class=\"content-item-op border-none\"><img src=\"../assets/Include/image/op-praise-gray.png\" class=\"content-item-op-img op-praise\"  id=\"praise_img_"+item.questionId+"\"/></div>"
              h += "            <div class=\"content-item-op border-none\"><div id='praise_"+item.questionId+"' class=\"content-item-op-count\">" + (item.praiseCount<=0?"点赞":item.praiseCount) + "</div></div></div>";
              
              
              h += "        </div>"
			  if(index_count == count){
				h += "      <div style=\" width:100%;height:30px; border-top:1px solid #d2d2d2;\"></div>  "
				next_page_ +="     <div style=\" background:#f5f5f5; width:100%;height:10px; border-top:1px solid #d2d2d2;border-bottom:1px solid #d2d2d2;\"></div>  ";
				next_page_ += "<div id=\"next_page_pic_"+json.lastRecordTime+"\" style='text-align: center;' onclick=\"getNextPageComplain('"+json.lastRecordTime+"')\"><img class=\"next_page_pic\" src=\"../assets/Include/image/next_page_record.png\" style='margin: 0 auto 75px;width: 24px;height: 24px;' /></div>";
		        
			  }
			  else{
				h += "      <div style=\" background:#f5f5f5; width:100%;height:10px; border-top:1px solid #d2d2d2;border-bottom:1px solid #d2d2d2;\"></div>  "
				next_page_ ="";
			  }
              h += "    </div>"
              h += "</div>";
              //                var last = $('#thelist div:last')
              //                alert(last.html());
              $('.friend-complain-list').append(h);
              $('.friend-complain-list').append(next_page_);
          })
    	  //var next_page ="<div style=\" background:#f5f5f5; width:100%;height:10px; border-top:1px solid #d2d2d2;border-bottom:1px solid #d2d2d2;\"></div>";
          
          
	
	  }

      $(".friend-complain-list").show();
	  loadingHide();
	
}

function getNextPageComplain(lastRecordTime){
	
	$('#next_page_pic_'+lastRecordTime).find("img").attr("src",
			$('#next_page_pic_'+lastRecordTime).find("img").attr("src").replace("next_page_record.png","loader.gif"));
	
	var districtIds = $('#id-search-selected-district').val();
	var questiontypes = $('#id-search-selected-type').val();
	var time = $('#search-selected-time').val();
	var rivername = $('.search-input-river').val();
	
	$.ajax({
		url:'../weixinComplain/getComplainList',
		type:'get',
		dateType:'text',
		data:{'lastRecordTime':lastRecordTime,'isMyOwn':'FALSE','statusStr':'2,3,4','area':districtIds,
			'questiontype':questiontypes,'complainDate':time,'reachname':rivername},
		success:function(data){
			$('#next_page_pic_'+lastRecordTime).remove();
			setComplainList(data,true);
		}
		
	});
}

function setMyComplainListValue(json){
	var h = "";
	var map = {'map5':'已办结','map1':'未受理','map2':'受理成功','map0':'已评价','map3':'非河道问题'};
//	(0:已回复投诉1:待受理投诉2:已受理投诉3：无效投诉5：完结操作  -1:被删除)
	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
        if(json.count!=0){
        	for(var i=0;i<json.count;i++){
        		var obj = json.complainList[i];
				var st=map["map"+obj.status]||"非河道问题";
				h += "<div style=\" background:#f5f5f5; width:100%;height:10px; border-top:1px solid #d2d2d2;border-bottom:1px solid #d2d2d2;\"></div>"
            	h +="<div class=\"my-complain-item status-"+obj.status+"\">";
              	h += '<div class="my-complain-item-top"><div class="my-complain-item-top-location">'+obj.area+(obj.riverName==""?"":'.'+obj.riverName)+'</div><div class="my-complain-item-top-status color-red">'+st+'</div></div>';
              	h += '<div class="my-complain-item-content">';
              	h += '<div class="my-complain-item-imglist">';
              		if(obj.photoList.listCount!=0){
                      	h += '<img src="http://wx1.ccqm.cn/water/weixinComplain/getLittlePicById?id='+obj.photoList.list[0].locationId+'" class="my-complain-item-img item-img-list-'+obj.questionId+'" />';
                  	}
              	h += '</div>';
              	h += "<div class=\"my-complain-item-content-right\" onclick=\"showComplainMessage('" + obj.questionId + "')\">";
              	h += '<p class="my-complain-item-title">'+(obj.questioncontent.length>45?obj.questioncontent.substring(0,45)+"...":obj.questioncontent)+'</p>';
              	//h += '<p class="my-complain-item-type">投诉类型：<span class="item-type color-80">'+obj.questiontype+'</span></p>';
              	h += '<p class="my-complain-item-code">投诉编号：<span class="item-code color-80">'+obj.questionCode+'</span></p>';
              	h += '</div>';
              	h += ' <div class="my-complain-item-location">';
              	h += ' <img src="../assets/Include/image/location-light.png" class="my-complain-item-location-icon" /><span class="my-complain-item-location-name">'
              		+obj.questionposition.replace("广东省","").replace("广州市","").replace("广东","").replace("广州","").replace(obj.area,"")+'</span>';
              	h += '</div>';
              	h += ' </div>';
              	h += '<div class="my-complain-item-statisties">';
              	h += '  <div class="my-complain-item-statisties-d border-none"><img src="../assets/Include/image/op-repost-gray.png" class="my-complain-item-statisties-img statisties-attentive" /></div>';
              	h += ' <div class="my-complain-item-statisties-d my-complain-item-repost-count">'+obj.repostCount+'</div>';
              	h += '<div class="my-complain-item-statisties-d border-none"><img src="../assets/Include/image/op-replay-gray.png" class="my-complain-item-statisties-img statisties-attentive" /></div>';
              	h += '<div class="my-complain-item-statisties-d my-complain-item-replay-count">'+obj.attentionCount+'</div>';
              	h += '<div class="my-complain-item-statisties-d border-none"><img src="../assets/Include/image/op-praise-gray.png" class="my-complain-item-statisties-img statisties-attentive" /></div>';
              	h += '<div class="my-complain-item-statisties-d border-none my-complain-item-praise-count">'+obj.praiseCount+'</div>';
              	h += '</div>';
              	h += ' <div class="my-complain-item-op">';
              	if(obj.status==1){//待受理  待受理投诉
              		h += '<div class="my-complain-item-op-btn" onclick="showFlow(\''+obj.questionId+'\',\''+obj.openId+'\')">查看办理</div>';
              		//h += '<div class="my-complain-item-op-btn">删除</div>';
              	}else if(obj.status==2){//待分派  已受理 
              		h += '<div class="my-complain-item-op-btn" onclick="showFlow(\''+obj.questionId+'\',\''+obj.openId+'\')">查看办理</div>';
              		//h += '<div class="my-complain-item-op-btn" id="'+obj.questionId+'_'+obj.openId+'_myAttention" onclick="cancelAttention("'+obj.questionId+'","'+obj.openId+'")">取消关注</div>';
              	}else if(obj.status==3){//待办结 
              		h += '<div class="my-complain-item-op-btn" onclick="showFlow(\''+obj.questionId+'\',\''+obj.openId+'\')">查看办理</div>';
              		//h += '<div class="my-complain-item-op-btn">取消关注</div>';
              	}else if(obj.status==4){//待评价   已完结
              		h += '<div class="my-complain-item-op-btn" onclick="ShowEvaluate(\''+obj.questionId+'\')">评价</div>';
              		//h += '<div class="my-complain-item-op-btn" onclick="showFlow(\''+obj.questionId+'\',\''+obj.openId+'\')">查看办理</div>';
              	}else if(obj.status==5){//已评价  0:已回复投诉
              		h += '<div class="my-complain-item-op-btn" onclick="showFlow(\''+obj.questionId+'\',\''+obj.openId+'\')">查看办理</div>';
              		//h += '<div class="my-complain-item-op-btn">取消关注</div>';
              	}
              	h += '</div>';
              	h += '</div>';
        	}
        }else{
        	h +='<div class=\"weui-loadmore weui-loadmore_line\"><span class=\"weui-loadmore__tips\">暂无数据</span></div>';
        }
	}
	$(".my-complain-list").html(h);
}

function cancelAttention(questionId,openId){
	var objId = questionId+"_"+openId+"_myAttention";
//	$.ajax({
//		url:'../weixinComplain/cancelAttention',
//		type:'GET',
//		dataType:'text',
//		data:{'questionId':id,'openId':openId},
//		success:function(data){
//			
//		}
//	});
}

function showReach(id) {
	$.ajax({
		url:'../weixinComplain/getReachInfo',
//		url:'../assets/jsons/getReachInfo.txt',
		type:'GET',
		dataType:'text',
		data:{'riverId':id},
		success:function(data){
			setReachValue(data);
		}
	});
	
    $(".reach").show();
    
}
function setReachValue(json){

	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
        //标题
        //$("#reach-item-name_0").html(json.title);
        //河道（涌）名称
        $("#reach-item-name_1").html(json.name);
        //河段起点
        $("#reach-item-name_2").html(json.start);
        //河段终点
        $("#reach-item-name_3").html(json.end);
        //河段长度
        $("#reach-item-name_4").html(json.length);
        if(json.leaderInfo.length!=0){
        	 //区级河长
            $("#reach-item-name_5").html(json.leaderInfo[0].AreaLeader);
            //镇（街）级河长
            $("#reach-item-name_6").html(json.leaderInfo[1].TownsLeader);
            //村（居）级河长
            $("#reach-item-name_7").html(json.leaderInfo[2].VillageLeader);
            //管理单位负责人
            $("#reach-item-name_8").html(json.leaderInfo[3].ManagementLeader);
            
            //$("#reach-item-phone_0").html(json.leaderInfo[0].Phone);
            
            $("#reach-item-phone_1").html("<a href=\"tel://"+json.leaderInfo[1].Phone+"\">" + json.leaderInfo[1].Phone + "</a>");
            
            $("#reach-item-phone_2").html("<a href=\"tel://"+json.leaderInfo[2].Phone+"\">" + json.leaderInfo[2].Phone + "</a>");
            
            $("#reach-item-phone_3").html("<a href=\"tel://"+json.leaderInfo[3].Phone+"\">" + json.leaderInfo[3].Phone + "</a>");
        }else{
       	 //区级河长
            $("#reach-item-name_5").html('');
            //镇（街）级河长
            $("#reach-item-name_6").html('');
            //村（居）级河长
            $("#reach-item-name_7").html('');
            //管理单位负责人
            $("#reach-item-name_8").html('');
            
            $("#reach-item-phone_0").html('');
            
            $("#reach-item-phone_1").html('（待定）');
            
            $("#reach-item-phone_2").html('（待定）');
            
            $("#reach-item-phone_3").html('（待定）');
        }
	}
}



function menuClick(obj) {
	$(".menu-item-img").each(function () {
        var src = $(this).attr("src");
        $(this).attr("src", src.replace("light", "gray"));
    })
    var t=$(obj).attr("id");
    var s = $(obj).attr("src");
    $(obj).attr("src", s.replace("gray", "light"));
	
    $(".display-none").hide();
    $("." + t).show();
    if(t == "friend"){$(".my-complain").hide();$(".friend-complain-list").show(); }
}

function getMessageList(){
	//loadingMask();
	$.ajax({
		async:true,
		url:'../weixinComplain/getMyReplyList',
		data:{'openId':openId},
		type:'GET',
		dataType:'text',
		success:function(data){
			setMyReplyValue(data);
		}
	});
	//loadingHide();
}
function setMyReplyValue(json){
	$(".message").empty();
	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        if(json=="NULL"){
        	$(".message").html("<div class=\"weui-loadmore weui-loadmore_line\"><span class=\"weui-loadmore__tips\">暂无消息</span></div>");
        	return;
        }
        if(json.indexOf("userpic")<0){
        	$(".message").html("<div class=\"weui-loadmore weui-loadmore_line\"><span class=\"weui-loadmore__tips\">暂无消息</span></div>");
        	return;
        }
        json = eval('(' + json + ')');
        var length = json.length;
        if(length!=0){
        	 var h = "";
        	$.each(json, function (i, item) {
				var content=item.content;
				if(content.length>25){content=content.substring(0,23)+"...";}
        		h += '<div class="message-item">';
        		h += '<img src="'+item.userpic+'" class="message-item-photo" id="message-item-photo-message"/>';
        		h += '<div class="message-item-content">'
        		h += ' <span class="message-item-name">'+item.nickname+'</span>'
                h += '<span class="message-item-time">'+item.replyTime+'</span>'
                h += '<span class="message-item-message ellipsis">'+content+'</span>'
                h += '</div>'
                  		if(item.questionPic!=""){
                  			h+='<img src="../weixinComplain/getLittlePicById?id=' + item.questionPic + '" class="complain-message-img" />';
                      	}
                h += '</div>'   
        	});
      	  var next_page_ = "<div class=\"next_page_pic\" id=\"next_page_pic_"+json.lastRecordTime+"\" onclick=\"getNextPageReply('"+json.lastRecordTime+"')\"><img src=\"../assets/Include/image/next_page_record.png\" width=\"100%\" height=\"90\" /></div>";
          $('.message').append(next_page_);
        	
        	
        	$(".message").html(h);
			$(".message").append("<div style='width:100%;height:80px;'></div>")
        }
	}
}

function getNextPageReply(lastrecordtime){
	
}


function saveQuestionItem(questId,type,obj){
	var img=$(obj).find("img");
	var src=img.attr("src");
	var cur=false;
	if(src.indexOf("light")>=0){ cur=true;}
	else {cur=false;}
	if(cur){src=src.replace("light","gray");}
	else {src=src.replace("gray","light");}
	img.attr("src",src);
	$.ajax({
		url:'../weixinComplain/saveQuesItem',
		type:'GET',
		dataType:'text',
		data:{complainId:questId,openId:openId,'type':type},
		success:function(data){
			setquestionItemValue(questId,type,data);
		}
	});
}
function setquestionItemValue(questId,type,json){
	
	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
		var now_Count=$("#"+type+"_"+questId).html();
		if(now_Count=="点赞"||now_Count=="关注"){now_Count=0;}
		
    	$("#"+type+"_"+questId).html(now_Count - 0 +parseInt(json.count));
	}
}


function getUserInfo() {
		$.ajax({
			url:'../weixinComplain/getUserInfo',
			type:'GET',
			dataType:'text',
			data:{'openId':openId},
			success:function(json){
				setUserInfo(json);
			}
		});
}

function setUserInfo(json){
	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
        $("#userId").val(json.userId);
        $(".friend-head-name").html(json.name);
        $(".head-photo-img").attr("src", json.photoHref);
        var map={"sex1":"男","sex2":"女","sex0":"未知"};
        $("#weixin_user_sex").val(map["sex"+json.sex]);
        $("#weixin_user_signature").val(json.signature);
        $("#weixin_user_phonenum").val(json.phoneNum);
        $("#weixin_user_area").val(json.address);
        
    	$(".set-photo").attr("src",json.photoHref);
    	$(".set-item-name").html(json.name);
    	$(".set-item-sex").html(map["sex"+json.sex]);
    	$(".set-item-district").html(json.address);
    	$("#set-item-name_text-ellipsis").html(json.signature);
    	$("#set-item-name_tel_phone").html(json.phoneNum);
    	
        var level = json.level;
        var i=0;
        $(".head-level-img").each(function () {
            if (i>0&&i <= level) {
                var s = $(this).attr("src");
                $(this).attr("src", s.replace("gray", "light"));
            }
            i++;
        })
    }
}

function getSearchListData(obj) {
    //document.removeEventListener("touchmove", prevent, false);
	var t = $(obj).find("input[type=hidden]").attr("id").split("-")[2];
    $("#selecter-type").val(t);
    $(".search-select-list").html("");
    var selected = $(obj).find("input[type=hidden]").val();
    var _url = ""; var text = "";
    var json = "";
    var multiple = true;
    if (t == "district") {
        //json = "RequestSuccessful{\"Count\":\"8\",\"List\":[{\"Id\":\"1\",\"Name\":\"越秀区\"},{\"Id\":\"2\",\"Name\":\"荔湾区\"},{\"Id\":\"3\",\"Name\":\"海珠区\"},{\"Id\":\"4\",\"Name\":\"天河区\"},{\"Id\":\"5\",\"Name\":\"白云区\"},{\"Id\":\"6\",\"Name\":\"黄埔区\"},{\"Id\":\"7\",\"Name\":\"番禺区\"},{\"Id\":\"8\",\"Name\":\"花都区\"}]}"
		$.ajax({
//			url:'../assets/jsons/getDistrictList.txt',
			url:'../weixinComplain/getDistrictList',
			type:'GET',
			dataType:'text',
			async:false,
			success:function(data){
				json=data;
			}
		});
	}
    else if (t == "type") {
        //json = "RequestSuccessful{\"Count\":\"3\",\"List\":[{\"Id\":\"1\",\"Name\":\"生活污水排放\"},{\"Id\":\"2\",\"Name\":\"工业废水排放\"},{\"Id\":\"3\",\"Name\":\"建筑废料堆积\"}]}"
		$.ajax({
//			url:'../assets/jsons/getTypeList.txt',
			url:'../weixinComplain/getTypeList',
			type:'GET',
			dataType:'text',
			async:false,
			success:function(data){
				json = data;
			}
		});
	}
    else if (t == "time") {
        multiple = false;
        json = "RequestSuccessful{\"count\":\"6\",\"list\":[" +
        		"{\"Id\":\"0\",\"Name\":\"今日\"}," +
        		"{\"Id\":\"1\",\"Name\":\"一天内\"}," +
        		"{\"Id\":\"2\",\"Name\":\"两天内\"}," +
        		"{\"Id\":\"7\",\"Name\":\"一周内\"}," +
        		"{\"Id\":\"30\",\"Name\":\"一个月内\"}," +
        		"{\"Id\":\"182\",\"Name\":\"半年内\"}]}"
	}
    else if (t == "right") {
        multiple = false;
        json = "RequestSuccessful{\"count\":\"2\",\"list\":[{\"Id\":\"0\",\"Name\":\"公开\"},{\"Id\":\"1\",\"Name\":\"仅个人\"}]}"
	}
    
    if (json.indexOf("RequestSuccessful") >= 0){
        json = json.replace("RequestSuccessful", "");
        json = eval('('+json+')');
        var c = json.count;
        for (var i = 0; i < c; i++) {
            var isSelected = selected.indexOf(json.list[i].Id) >=0? "isSelected" : "";
            var h = "<div class=\"search-select-item\" onclick='SeacrchItemSelect(this,"+multiple+")'>";
                h += "    <span>" + json.list[i].Name + "</span>";
                h += "    <div class=\"search-select-isselected "+isSelected+"\"></div>";
                h += "    <input type=\"hidden\" class=\"selecter-id\" value=\"" + json.list[i].Id + "\" />";
                h += "</div>";
                $(".search-select-list").append(h);
        }
    }

    $(".search-selectdialog").show();
}

function selectBtnSubmit() {
    var name = ""; var id = ""; var len = 0;
    var t = $("#selecter-type").val();
    $(".search-select-item").each(function () {
        var isSelected = $(this).find(".search-select-isselected");
        if (isSelected.length > 0) {
            var h = isSelected.hasClass("isSelected");
            if (h) {
                name += $(this).find("span").html()+",";
                id += $(this).find(".selecter-id").val() + ",";
                len++;
            }
        }
    })
    
    //if (len <= 0) { alert("请至少选择一个选项"); return; }
	if (len <= 0) {
		id = "";
		name = ""; 
		$("#id-search-selected-" + t).val(name);
		$("#search-selected-" + t).val(id);
		$(".search-selected-" + t).html("请选择");
		$(".search-selected-" + t).css("color","#AFAFAF");
	} else { 
		name = name.substring(0, name.length - 1);
		$("#id-search-selected-" + t).val(name);
		if (name.length > 12) { name = name.substring(0, 10) + "...";}
		id = id.substring(0, id.length - 1);
		$("#search-selected-" + t).val(id);
		$(".search-selected-" + t).html(name);
		$(".search-selected-" + t).css("color","black");
    }
    $(".search-selectdialog").hide();
}

function SeacrchItemSelect(obj,multiple) {
    var isSelected = $(obj).find(".search-select-isselected");
    if (multiple == false) { $(".search-select-isselected").removeClass("isSelected"); }
    if (isSelected.length <= 0) { return; }
    var h =isSelected.hasClass("isSelected");
    if (h==false) { isSelected.addClass("isSelected"); }
    else { isSelected.removeClass("isSelected");}
}

function showSearch() {
    $(".search").show();
}


function RemindCheck(obj){
    var chk = $("#remind-isNoRemind");
    var src = $(obj).attr("src");
    if (chk.attr("checked") == "checked") {
        src = src.replace("checked", "checkno");
        $(obj).attr("src", src);
        chk.attr("checked", false);
    }
    else {
        src = src.replace("checkno", "checked");
        $(obj).attr("src", src);
        chk.attr("checked", "checked");
    }
}

function saveNoRemind() {
	var chk = $("#remind-isNoRemind"); 
    if (chk.attr("checked") == "checked") {
	 $.ajax({
			url:'../weixinComplain/saveNoRemind',
	        dataType: 'text',
	        data:{openId:openId},
	        success: function (data) {
	        	if (data.indexOf("RequestSuccessful") >= 0) {
	                data = data.replace("RequestSuccessful", "");
	                if(data=="1"){ initHome(); }
	        	}
	        }
	        
	    });
    }
    else{ initHome(); }
}

function initHome(){
	$(".remind").remove(); 
	$(".HomeDiv").show();
	$(".friend").show();
	getComplainlist('FALSE','','','','',false);
	getMessageList();
}

function RefreshHome() {
    location.reload();
}

function myClose() {
    alert("你确定要关闭？")
}

function checkInput(obj, title) {
    var v = $(obj).val();
    if (v == '') {$(obj).val(title); }
}

function bindPhoneNumber(){
	var phoneNum = $(".bind-phone-input").val();
	$.ajax({
		url:'../weixinComplain/bindPhoneNum',
		data:{phoneNum:phoneNum,openId:openId},
		type:'POST',
		dataType:'text',
			success:function(data){
		}
	});
}

function checkPhone(obj) {
    var v = $(obj).val();
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    if (reg.test(v)) { 
    	$(".bind-phone-isok").css("background", "#1dbb20"); 
    }
    else {
    	$(".bind-phone-isok").css("background", "#c0c0c0"); 
    }
}


function showFlow(id) {
	var map =['已办结','未受理','受理成功','已评价','非河道问题']
    $.ajax({
        type: "get",
        dataType: 'text',
        url: "../weixinComplain/getComplainInfo",
        data:{'complainId':id},
        success: function (data) {
            if (data.indexOf("RequestSuccessful") >= 0) {
                data = data.replace("RequestSuccessful", "");
                var json = eval('(' + data + ')');
              	$(".flow-complain-img").attr("src", json.photoList.listCount==0?"":"/weixinComplain/getLittlePicById?id="+json.photoList.list[0].locationId);
                $(".flow-status").html(map[json.status]);
                $(".flow-title").html(json.questioncontent);
                $(".flow-code").html(json.code);
                var h = "";
                var c = json.flowcount;
                if (c == 0) {
                    h += "<div class=\"flow-item-current\">"
                    h += "    <div class=\"flow-item-current-radius\"></div>"
                    h += "    <div class=\"flow-item-current-content\">暂无办理"
                    h += "        <div class=\"flow-item-line\"></div>"
                    h += "    </div>"
                    h += "</div>"
                    $(".flow-currentpeople").hide();
                    $(".flow-flow").html(h);
                    return;
                }
                $.each(json.flowlist, function (j, item) {
                    if (j == 0) {
                        h += "<div class=\"flow-item-current\">"
                        h += "    <div class=\"flow-item-current-radius\"></div>"
                        h += "    <div class=\"flow-item-current-content\">" + item.content
                        h += "        <p class=\"flow-item-time\">" + item.createTime + "</p>"
                        h += "        <div class=\"flow-item-line\"></div>"
                        h += "    </div>";
                        h += "</div>"
                    }
                    else {
                        h += "<div class=\"flow-item\">"
                        h += "    <div class=\"flow-item-radius\"></div>"
                        h += "    <div class=\"flow-item-content\">" + item.content
                        h += "        <p class=\"flow-item-time\">" + item.createTime + "</p>"
                        if (j < c - 1) {
                            h += "        <div class=\"flow-item-line\"></div>"
                        }
                        h += "	</div>";
                        h += "</div>"
                    }
                })
                $(".flow-currentpeople").show();
                $(".flow-flow").html(h);
            }
        }
    });
    $(".flow").show();
}

function flowClose() {
    $(".flow").hide();
}

function showComplainMessage(id) {
	
	$.ajax({
//		url:'../assets/jsons/getComplainInfo.txt',
		url:'../weixinComplain/getComplainInfo',
		type:'GET',
		dataType:'text',
		data:{complainId:id},
		success:function(data){
			setValue(data);
		}
	});
	
    $(".complain-message").show();
    
    $.ajax({
//		url:'../assets/jsons/getRepay.txt',
    	url:'../weixinComplain/getRepay',
		type:'GET',
		dataType:'text',
		data:{complainId:id},
		success:function(data){
			setRepayValue(data);
		}
    });
    
}
function setValue(json){
	if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
        
		var rowLen=(winW*0.96*0.75)/40;
		var contentH=Math.ceil(json.questioncontent.length/rowLen)*52;
        $(".complain-message-head").css("height",(contentH+100)+"px")

        $(".complain-message-head-name").html(json.name);
        $(".complain-message-head-title").html(json.questioncontent);
        $(".complain-message-head-photo").attr("src",json.photo);
        $(".content-message-reach").html(json.riverName);
        $(".content-message-location").html(json.questionposition);
        var html="<input type='hidden' value='"+json.questionId+"' id='reply_questionId'>";
		var imhListH=Math.ceil(json.photoList.listCount/4)*240;

        if(json.photoList.listCount!=0){
			
        	var size = json.photoList.listCount;
        	for(var i=0;i<size;i++){
        		var obj = json.photoList.list[i];
          		if(json.photoList.listCount!=0){
          			html+='<img src="../weixinComplain/getLittlePicById?id=' + obj.locationId + '" class="complain-message-img" />';
              	}
        	}
        }
		$(".complain-message-imglist").css("height",imhListH+"px")
        $(".complain-message-imglist").html(html);
        
	}
}
function setRepayValue(json){
	if (json.indexOf("RequestSuccessful") >= 0) {
	        json = json.replace("RequestSuccessful", "");
	        json = eval('(' + json + ')');
	        $("#reply_num").html(json.count);
	        var html = "";
        if(json.count!=0){
        	for(var i=0;i<json.count;i++){
        		var obj = json.list[i];
        		html+='<div class="complain-message-reply-item">';
        		html+='<div class="complain-message-reply-item-head">';
        		html+='<span class="complain-message-reply-item-time">'+obj.complainDate+'</span>';
        		html+='<span class="complain-message-reply-item-name">'+obj.name+'</span>';
        		html+='<img src='+obj.photo+' class="complain-message-reply-item-photo" />';
        		html+='</div>';
        		html+='<div class="complain-message-reply-item-content">';
        		html+=obj.replay;
        		html+='</div>';
        		html+='</div>';
        	}
        }
        
        $(".complain-message-replylist").html(html);
	}
}


function replyClose() {
    $(".reply-input").val("");
    $(".complain-message").hide();
}

function comlainClose() {
    $(".weui_uploader_file").remove();
    //RefreshHome();
    //getComplainlist('FALSE','','','','',false);
    $(".complain").hide();
    menuClick($(".menu-friend img"));
}

function ShowEvaluate(id) {
    $(".evaluate").show()
}

function starClick(obj) {
    var list = $(obj).parent().find("img");
    var index = list.index(obj);
    for (var i = 0; i < list.length; i++) {
        var star = list.eq(i);
        var src = star.attr("src");
        if (i <= index) { src = src.replace("gray", "light");}
        else { src = src.replace("light", "gray"); }
        star.attr("src", src);
    }
}

function evaluateSubmit() {
    var attitudeStar = 0;
    var efficiencystar = 0;
    var qualitystar = 0;
    attitudeStar = getStarCount($(".attitude-star"));
    efficiencystar = getStarCount($(".efficiency-star"));
    qualitystar = getStarCount($(".quality-star"));
    if (attitudeStar <= 0) { alert("请对服务态度进行评价！"); return; }
    if (efficiencystar <= 0) { alert("请对服务效率进行评价！"); return; }
    if (qualitystar <= 0) { alert("请对服务质量进行评价！"); return; }
    alert("评价成功！");
    $(".evaluate").hide()
}

function getStarCount(obj) {
    var stars = $(obj).find("img");
    var c = 0;
    stars.each(function () {
        var src = $(this).attr("src");
        if (src.indexOf("light") >= 0) { c+=1; }
    })
    return c;
}


//TODO
function Repost(id,obj){
	//TODO
	//弹出微信转发窗
	$.ajax({
		//TODO
		url:'../weixinComplain/saveQuesItem',
		type:'GET',
		dataType:'text',
		data:{'openId':openId,'complainId':id,'type':'repost'},
		success:function(json){
			if(json.indexOf('RequestSuccessful')>=0){
				if(json=='NULL'){
				}else{
					json = json.replace("RequestSuccessful","");
			        json = eval('(' + json + ')');
					var img=$(obj).find("img").eq(0);
					var src= img.attr("src");
					
					if(src.indexOf("light")<0){
						src=src.replace("gray","light");
						img.attr("src",src);
					}
					var now_Count=$("#repost_"+id).html()
					if(now_Count=="转发"){now_Count=0;}
					$("#repost_"+id).html(now_Count-0+parseInt(json.count));
				}
			}	
		}
	})
}


function getBubbleCount() {
	//TODO
    $.ajax({
        type: "get",
        dataType: 'text',
        url: "../weixinComplain/sendUserMessageCnt?rnd="+Math.random(),
        success: function (data) {
        	//RequestSuccessful{count:0}
            if (data.indexOf("RequestSuccessful") >= 0) {
            	data = data.replace("RequestSuccessful", "");
            	if(data=="Null"){
            		return;
            	}
                var json = eval('(' + data + ')');
                
                if(json.ismessupdate=="1"){
                	//消息红点显示
                	//TODO
                	$(".bubble-message").show();
                }
                if(json.iscommunupdate=="1"){
                	//圈子红点显示
                	$(".bubble-friend").show();
                }
            }
        }
    })
    setTimeout("getBubbleCount()",20000)
}


function loadingMask(){
	$(".hr-loading-mask").show();
}

function loadingHide(){
	$(".hr-loading-mask").hide();
}



