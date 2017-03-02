var checkCode ;
$( function(){
	$(".login_type").click(function(){
		var bgSrc=$(".login_type").css("background-image");
		if(bgSrc.indexOf("wx_login")>=0){
			bgSrc=bgSrc.replace("wx_login","pc_login");
			$(".wx_login").css("display","block");
			$(".pc_login").hide();
			getwxEweima();
		}
		else{
			bgSrc=bgSrc.replace("pc_login","wx_login");
			$(".pc_login").show();
			$(".wx_login,.wx_login_eweima").css("display","none");
			clearInterval(checkCode);
		}
		$(".login_type").css("background-image",bgSrc);
	})
	
})

function getwxEweima()
{
	var loc=location.href;
	loc=loc.replace("/admin/login","/outFall/getwxEweima")
	loc+="?Width=220&Height=220";
	var ukey=$("#UKey").val();
	loc+="&CodeText="+ukey;
	loc+="&rnd="+Math.random();
	$(".wx_login_eweima").attr("src",loc).css("display","block");	
	
	checkCode = setInterval(CheckIsScan, 3000);
}

function CheckIsScan(){
	//if(hr_checkScan==false){return;}
	var result;
	var ukey=$("#UKey").val();
	var loc=location.href;
	loc=loc.replace("/admin/login","/outFall/CheckIsScan")
	loc+="?UKey="+ukey+"&rnd="+Math.random();
	$.ajax({
        url:loc,
        async: false,
        success: function (data) {
            result = data;
        }
    });
	if (result == '1') {
        clearInterval(checkCode);
        $("#IsScan").val(1);
        $("#loginform").submit();
    }
    else if (result.indexOf("HasScan") >= 0) {
        result = result.replace("HasScan", "");
        SetMask(result);
    }
    else {
        $("#ScanMask").remove();
    }
}

function SetMask(name) {
	var w=$(".wx_login").css("width").replace("px","")-0;
	var mask_w=(w-143)/2;
	var _maskHtml = "<div Id='ScanMask' style='background:rgba(0,0,0,0.5);width:143px;height:143px;position: absolute;top: 50px;right: "+mask_w+"px;text-align:center;color:#fff'>"
                  + "<div class='login_mask' style='width:80px;height:80px;margin:20px auto 0;'></div>"
                  + "<span style='background:#62bb4b;width:100%;height:26px;border-radius:13px;display:block;line-height:26px;margin:2px auto;font-size: 12px;'>" + name + "扫码成功!</span></div>"
    $("#ScanMask").remove();
    $(".wx_login").append(_maskHtml);
}





