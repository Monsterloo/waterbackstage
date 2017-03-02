var winW;
var winH;
var IsIphone = false;
var clc_i = 0;
$(function () {
    winW = $(window).width();
    winH = $(window).height();

    var ua = navigator.userAgent.toLowerCase();
    var em;
    if (ua.indexOf("iphone") >= 0) { IsIphone = true; }
    if (ua.indexOf("mobile") >= 0) { em = "Mobile"; }
    else {
        $("body").html("<span style='font-size:40px;'>请在手机微信端查看！</span>")
        return;
        //        em = "Conputer";
        //        AutoSize();
    }

    //$(".HomeDiv").css({ "width": winW + "px", "height": winH + "px" }).show();
    //$(".complainlist").css({ "height": winH - 170 + "px" });
    $(".HomeDiv").show();
    Btn_Init();
    Position_Init();
    Fn_Init();
    
})


function Fn_Init() {
    //以下方法完成后请删除或注释

    //以上方法完成后请删除或注释
}

function Position_Init() {
    $(".div-mask").css({ "width": winW + "px", "height": winH + "px" });
}

function Btn_Init() {
    $(".menu-zhishui").click(function () {
        clc_i++;
        setTimeout(function () { clc_i = 0; }, 500);
        if (clc_i > 1) {
            RefreshHome();
        }
        else { $(".display-none").hide(); $(".zhishui").show(); }
    })
    $(".zhishui-item").click(function () { zhishuiClick(this);})
    $(".reach-btn-back").click(function () { reachBack(); })

}

function showComplainMessage(id,act) {
    $.ajax({
    	url:'../weixinComplain/getComplainInfo',
        type: 'GET',
        dataType: 'text',
        data: { complainId: id },
        success: function (data) {
            setComplainMessage(data,act);
        }
    });

    $(".complain-message").show();

    $.ajax({
    	url:'../weixinRiverMaster/getRemark',
        type: 'GET',
        dataType: 'text',
        data: { complainId: id },
        success: function (data) {
            setRepayValue(data);
        }
    });
}

function setComplainMessage(json, act) {
    if (act == "accept") { $(".accept-btn").show(); }
    if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');

        $(".complain-message-head-name").html(json.name);
        $(".complain-message-head-title").html(json.questioncontent);
        $(".complain-message-head-photo").attr("src", json.photo);
        $("#hide-complainId").val(json.questionId)
        $(".content-item-location").html(json.address);
        var imgCount = json.photoList.listCount;
        var imglistH = (250 * Math.ceil(imgCount / 3) + 20);
        $(".complain-message-imglist").css("height", imglistH + "px");
        var html = ""
        if (json.photoList.listCount != 0) {
            var size = json.photoList.listCount;
            for (var i = 0; i < size; i++) {
                var obj = json.photoList.list[i];
                html += '<img src="http://ttbtech.com/sw/Include/image/FriendImage/00'+(i+1)+'.jpg" class="complain-message-img" />';
            }
        }
        $(".complain-message-imglist").html(html);
    }
}


function setRepayValue(json) {
    if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        json = eval('(' + json + ')');
        //{"count":0,"list":[]}
        $("#reply_num").html(json.count);
        var html = "";
        if (json.count != 0) {
            for (var i = 0; i < json.count; i++) {
                var obj = json.list[i];
                html += '<div class="complain-message-reply-item">';
                //html += '<div class="complain-message-reply-item-head">';
                //html += '<img src="http://ttbtech.com/sw/Include/Image/hr-liuyifei.jpg" class="complain-message-reply-item-photo" />';
                //html += '</div>';
                html += '<div class="complain-message-reply-item-content">';
                //html +=  "投诉备注："+json.list[i] ;
                html += '</div>';
                html += '</div>';
            }
        }

        $(".complain-message-replylist").html(html);
    }
}

function getComplainList(act) {
	var status;
	if(act=="accept"){
		status=1;
	}else if(act=="solve"){
		status=2
	}else if(act=="follow"){
		status=3
	}else{
		status=4
	}
    $.ajax({
        type: "get",
        dataType: 'text',
        url:'../weixinRiverMaster/findComplainByUserId',
        data:{status:status},
        success: function (data) {
            setComplainList(data,act);
        }
    })
}

function setComplainList(json,act) {
	$(".complainlist").empty();
    if (json.indexOf("RequestSuccessful") >= 0) {
        json = json.replace("RequestSuccessful", "");
        if (json == "NULL") {  
        	var h = "<span style='font-size:45px;margin:100px auto;display:block;width:100%;text-align:center'>暂无待处理投诉</span>";
        	h += "<div class='btn' onclick='backToZhishui()'>返回</div>"
            $(".complainlist").html(h);
        	return; 
        }
        json = eval('(' + json + ')');

        var count = json.count;
        var str = "";
        $.each(json.complainList, function (i, item) {
            var imgCount = item.photoList.listCount;
            var imglistH = (250 * Math.ceil(imgCount / 3) + 20);

            var location = item.questionposition;
            location = location.replace("广东省", "").replace("广州市", "").replace("广东", "").replace("广州", "");
            location = (location.length > 16 ? location.substring(0, 16) : location);
            var Status = item.status == 0 ? "no" : "yes";
            var lv = item.level;

            var clickFn = "onclick=\"showComplainMessage('" + item.questionId + "','"+act+"')\"";

            var h = "<div class=\"friend-content-item " + item.questionId + "\" >"
            h += "    <div class=\"content-item-top\"><div class=\"content-item-left-photo-div\"><img src=\"" + item.photo + "\" class=\"content-item-left-photo\" />"
            h += "         <img src=\"../js/rivermaster/image/vip_icon" + lv + ".png\"  class=\" item-photo-level\"/>"
            h += "    </div>"
            h += "        <div class=\"content-item-username\">" + item.name + "</div>"
            h += "        <div class=\"content-item-time\">" + item.timeDesc + "</div>";
            h += "    </div>";
            h += "    <div class=\"content-item-bottom\">"
            h += "        <div class=\"content-item-content item-content_" + item.questionId + "\" " + clickFn + "   style=\"font-size:45px\">" + item.questioncontent + "</div>"
            h += "        <div class=\"content-item-imglist\" style='height:+" + imglistH + "px'>";
            if (imgCount != 0) {
                $.each(item.photoList.list, function (j, imgList) {
                    h += "            <img onclick=\"PreviewImg(this,'" + imgList.locationId + "','" + item.questionId + "')\" src=\"../weixinComplain/getLittlePicById?id=" + imgList.locationId + "\" class=\"content-item-imglist-img item-img-list-" + item.questionId + "\" />"
                })
            }
            h += "        </div>"
            h += "        <div class=\"content-item-bottomline\">"
            if (IsIphone) {
                h += "            <img src=\"../js/rivermaster/image/location-light.png\" class=\"content-item-bottomline-icon\" /><span class=\"content-item-location\" onclick=\"OpenTencentMapForIphone('" + (item.x) + "','" + (item.y) + "','" + item.questionposition + "','" + location + "')\">" + location + "</span>"
            } else {
                h += "            <img src=\"../js/rivermaster/image/location-light.png\" class=\"content-item-bottomline-icon\" /><span class=\"content-item-location\" onclick=\"OpenTXMap('" + (item.x) + "','" + (item.y) + "','" + item.questionposition + "','" + location + "')\">" + location + "</span>"
            }

            h += "            <span class=\"content-item-reach\" onclick=\"showReach('" + item.rivercode + "')\">" + item.riverName + "</span>"
            h += "        </div>"
            //h += "</div>"
            h += "        <div class=\"content-item-op-div  border-top-1\">"

            //转发
            h += "            <div class=\"content-item-op border-none\"><img src=\"../js/rivermaster/image/op-repost-gray.png\" class=\"content-item-op-img op-repost\" /></div>"

            h += "<div class=\"content-item-op\"><div id='repost_" + item.questionId + "'  class=\"content-item-op-count\">" + (item.repostCount <= 0 ? "转发" : item.repostCount) + "</div></div>";

            //关注
            h += "<div class=\"content-item-op border-none\" ><img src=\"../js/rivermaster/image/op-attentive-gray.png\" class=\"content-item-op-img op-attentive\" /></div>"
            h += "            <div class=\"content-item-op\"><div  id='attention_" + item.questionId + "' class=\"content-item-op-count\">" + (item.attentionCount <= 0 ? "关注" : item.attentionCount) + "</div></div>";

            //点赞
            h += "            <div class=\"content-item-op border-none\" ><img src=\"../js/rivermaster/image/op-praise-gray.png\" class=\"content-item-op-img op-praise\" /></div>"
            h += "            <div class=\"content-item-op border-none\"><div id='praise_" + item.questionId + "' class=\"content-item-op-count\">" + (item.praiseCount <= 0 ? "点赞" : item.praiseCount) + "</div></div>";


            h += "        </div>"
            h += "      <div style=\" background:#f5f5f5; width:100%;height:30px; border-top:1px solid #b0b0b0;border-bottom:1px solid #b0b0b0;\"></div>  "
            h += "    </div>"
            h += "</div>";
            $('.complainlist').append(h);
        })

    } else {
        var h = "<span style='font-size:45px;margin:100px auto;display:block;width:100%;text-align:center'>暂无待处理投诉</span>";
        h += "<div class='btn' onclick='backToZhishui()'>返回</div>"
        $(".complainlist").html(h);
    }
    $(".complainlist").show()
}
function zhishuiClick(obj) {
    zhishuiCssChange(obj);
    var act = $(obj).attr("id").split('-')[1];
    getComplainList(act);
}

function zhishuiCssChange(obj) {
    $(".item-icon").each(function () {
        var src = $(this).attr("src");
        src = src.replace("light", "gray");
        $(this).attr("src", src);
    })
    $(obj).addClass("item-selected").siblings().removeClass("item-selected");
    var icon = $(obj).find("img")
    var icon_src = icon.attr("src").replace("gray", "light");
    icon.attr("src", icon_src);
}

function divShow(obj) { 
    
}

function RefreshHome() {
    var loc = location.href;
    var rnd = "";
    if (loc.indexOf("?") >= 0) {
        if (loc.indexOf("&rnd") >= 0) {
            loc = loc.split("&rnd")[0];
            rnd = "&rnd=" + Math.random();
        } else if (loc.indexOf("?rnd") >= 0) {
            loc = loc.split("?rnd")[0];
            rnd = "?rnd=" + Math.random();
            
        } else {
            rnd = "&rnd=" + Math.random();
        }
    }
    else { rnd = "?rnd=" + Math.random(); }
    location.href = loc + rnd;
}

function backToZhishui() {
    $(".display-none").hide();
}

function backToComplainList() {
    $(".complain-message").hide();
}

function showMask(objOrClassOrId) {
    $(objOrClassOrId).show().animate({ top: 0 });
}

function hideMask(objOrClassOrId) {
    $(objOrClassOrId).animate({ top: "100%" });
    setTimeout(function () { $(objOrClassOrId).hide(); }, 500)
}

function showAccept() {
	$("#hide-accept-complainId").val($("#hide-complainId").val());
	$(".accept-op-list").empty();
	$(".accept-op-list").append("<div class=\"accept-op\" onclick=\"saveNextDepartment(this)\">分派区治水办处理</div>");
	$(".accept-op-list").append("<div class=\"accept-op\" onclick=\"replyNo(this)\">回复非河道问题</div>");
	$(".accept-op-list").append("<div class=\"accept-op  margin-t-10\" onclick='backToMessage()'>返回</div>");
	$(".accept").show().animate({top:0});
}

function backToMessage(){
    hideMask(".accept");	
}

function replyNo(obj) {
    var op = $(obj).html();
    $(".reply-no-title").html(op)
    showMask(".reply-no");
}

function setReplyItem(data) {
    $(".reply-no-op-list").empty();
    if (data.indexOf("RequestSuccessful") >= 0) {
        data = data.replace("RequestSuccessful", "");
        var json = eval('(' + data + ')');
        var count = json.Count;
        var str = "";
        $.each(json.List, function (i, item) {
            var h = "<div class=\"accept-op\" onclick=\"saveReplyNo('" + item.id + "','" + item.value + "')\">" + item.value + "</div>";
            $(".reply-no-op-list").append(h);
        })
    }
    $(".reply-no-op-list").append("<div class=\"accept-op\" onclick='backToAccept()'>返回</div>");
    $(".reply-no").show().animate({ top: 0 });
}

function backToAccept() {
    hideMask(".reply-no");
}

function saveReplyNo(id, value) {
    if (confirm("您确定回复为【" + value + "】")) {
        alert("回复成功！");
        acceptIsOk("replyNo");
   }
}

function acceptIsOk() {
	var areaName=$(".area-selected").html()
	var remark=$("#remark").val();
	var complainId=$("#hide-complainId").val();
	
	$.ajax({
		url:'../weixinRiverMaster/cityAcceptComplain',
		data:{'complainId':complainId,'areaName':areaName,'remark':remark},
		type:'POST',
		dataType:'text',
		success:function(data){
			if (data.indexOf("RequestSuccessful") >= 0) {
                data = data.replace("RequestSuccessful", "")
                if(data==1){
                	hideMask(".next-department");
                    backToAccept();
                    backToMessage();
                    backToComplainList()

                    var id = $("#hide-complainId").val();
                    $("#" + id).remove();
                    if ($(".friend-content-item").length <= 0) {
                        var h = "<span style='font-size:45px;margin:100px auto;display:block;width:100%;text-align:center'>暂无待处理投诉</span>";
                        h += "<div class='btn' onclick='backToZhishui()'>返回</div>"
                        $(".complainlist").html(h);
                    }
                    alert("受理成功！");
                }
			}else{
				alert("保存失败！")
			}
		}
	});
}

function saveNextDepartment(obj) {
	var area="";
	var areaId="";
	var complainId=$("#hide-complainId").val();
	$.ajax({
        type: "get",
        dataType: 'text',
        url: "../weixinRiverMaster/getQuestionArea",
        data:{'complainId':complainId},
        success: function (data) {
        	json=data
        	json = eval('('+json+')');
        	area=json.area;
        	areaId=json.area;
        	$(".area-selected").html(area);
        	$("#hide-id-area").val(areaId);
		}
	});
	
    var op = $(obj).html();
    $(".next-department-title").html(op)
    showMask(".next-department");
}

function nextBack() {
    hideMask(".next-department");
}


function getUserLoction() {
    wx.getLocation({
        type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
            var speed = res.speed; // 速度，以米/每秒计
            var accuracy = res.accuracy; // 位置精度
            alert(longitude + "  longitude " + longitude + "  speed" + speed + "  " + accuracy)
        },
        cancel: function (res) {
            alert('用户拒绝授权获取地理位置');
        }
    });
}

function showLocationMap() {
    wx.openLocation({

        latitude: 0, // 纬度，浮点数，范围为90 ~ -90
        longitude: 0, // 经度，浮点数，范围为180 ~ -180。
        name: '', // 位置名
        address: '', // 地址详情说明
        scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
        infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
    });
}


function baiduMapGetLocation() {
    var point = new BMap.Point(116.331938, 39.897445);
    var gc = new BMap.Geocoder();
    gc.getLocation(point, function (rs) {
        var addComp = rs.addressComponents;
        alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    });
}


function showReach(id) {
    $.ajax({
        url: '../weixinComplain/getReachInfo',
        //		url:'../assets/jsons/getReachInfo.txt',
        type: 'GET',
        dataType: 'text',
        data: { 'riverId': id },
        success: function (data) {
            setReachValue(data);
        }
    });
    $(".reach").show();
}

function reachBack() {
    $(".reach").hide();
}


function showAreaSelect() {
	$(".area-dailog").empty();
	$.ajax({
		url:'../weixinComplain/getDistrictList',
		type:'GET',
		dataType:'text',
		async:false,
		success:function(json){
			if (json.indexOf("RequestSuccessful") >= 0){
		        json = json.replace("RequestSuccessful", "");
		        json = eval('('+json+')');
		        var c = json.count;
		        for (var i = 0; i < c; i++) {
					var h="<div class=\"op-row bg-white\" onclick=\"areaSelect(this)\"><span>"+json.list[i].Name +"</span><div class=\"op-row-right width-per-10 float-right margin-r-30 area-select-item\"></div><input type=\"hidden\" value=\""+json.list[i].Id +"\"  /></div>";
					$(".area-dailog").append(h)
		        }
			}
		}
	});
	
    $(".area-dailog").append("<div class=\"btn-red\" onclick=\"areaClose()\">返回</div>")
    showMask(".area-dailog");
}

function areaSelect(obj) {
    var obj = $(obj);
    var name = obj.find("span").html();
    var id = obj.find("input").val();
    $(".area-selected").html(name);
    $("#hide-id-area").val(id);
    hideMask(".area-dailog");
} 


function areaClose() {
    hideMask(".area-dailog");
}

function replyBack() {
    $(".reply-no textarea").val("");
    hideMask(".reply-no");
}


function acceptNo() {
	//alert(1);
    /*var remark = $(".reply-no textarea").val()
    if (!remark) { alert("请输入回复！"); return; }
    var complainId=$("#hide-complainId").val();
    $.ajax({
		url:'../weixinRiverMaster/acceptByBack',
		data:{'complainId':complainId,'remark':remark,'action':'cityBack'},
		type:'POST',
		dataType:'text',
    	success:function(data){
			if (data.indexOf("RequestSuccessful") >= 0) {
                data = data.replace("RequestSuccessful", "")
                if(data==1){
                    replyBack();
                    backToMessage();
                    backToComplainList()

                    var id = $("#hide-complainId").val();
                    $("#" + id).remove();
                    if ($(".friend-content-item").length <= 0) {
                        var h = "<span style='font-size:45px;margin:100px auto;display:block;width:100%;text-align:center'>暂无待处理投诉</span>";
                        h += "<div class='btn' onclick='backToZhishui()'>返回</div>"
                        $(".complainlist").html(h);
                    }
                    alert("保存成功！");
                }
			}else{
				alert("保存失败！")
			}
		}
	});*/
}

