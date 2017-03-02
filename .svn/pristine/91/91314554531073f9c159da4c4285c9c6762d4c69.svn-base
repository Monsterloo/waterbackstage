
$( function(){
	$(".login_type").click(function(){
		var bgSrc=$(".login_type").css("background-image");
		if(bgSrc.indexOf("wx_login")>=0){
			bgSrc=bgSrc.replace("wx_login","pc_login");
			$(".wx_login").show();
			$(".pc_login").hide();
			getwxEweima();
		}
		else{
			bgSrc=bgSrc.replace("pc_login","wx_login");
			$(".pc_login").show();
			$(".wx_login,.wx_login_eweima").hide();
		}
		$(".login_type").css("background-image",bgSrc);
	})
	
})

function getwxEweima()
{
	
	$.ajax({
		url:'http://localhost:8080/admin/getEweima?Width=180&Height=180&CodeText=10086',
		dataType:'text',
		type:'get',
		success:function(data){
			$(".wx_login_eweima").attr("src",data).show();		
		}
	});
}




