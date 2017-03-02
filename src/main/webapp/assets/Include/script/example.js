
function bind_Upload(){
	var serverId = [], localIds = [];
	var _x,_y, uploadData = {};
	

	$('#uploaderInput').on("click", function (event) {
	    if (localIds.length < 9) {
	        var cur_len = $(".weui_uploader_file").length||0;
			 wx.chooseImage({
			     count: 9 - cur_len,
			    sizeType: ['original', 'compressed'],
			    sourceType: ['album', 'camera'],
				success : function(res) {
					$(".display-none").hide();
					$(".complain").show();
				    var ids = res.localIds;
					var len = ids.length;
					for(var i=0; i<len; i++){
						localIds.push(ids[i]);
						var img = '<li class="weui_uploader_file" index="'+ids[i]+'" style="position:relative;background-image:url('+ids[i]+');background-size:100% 100%;"><i class="weui_icon_cancel" style="float:right;width:14px;height:14px;position:absolute;top:-3px;font-size:12px;right:-3px;background:red;color:#fff;line-height:14px;border-radius:7px;text-align:center">X</i></li>';
						$("#uploaderFiles").append(img);
						$(".weui-uploader__info").html(localIds.length + "/9");
						$(".weui_uploader_file").css({"width":"77px","height":"77px","float":"left","margin":"3px"}).show();
						$(".weui_icon_cancel").show();
						$("#hidden-img-serverId").val(serverId.join(","))				
					}
					//alert($(".weui_icon_cancel").css("top"))
					$(".weui_icon_cancel").each(function(index, it){
						var item = $(it);
						item.on("click",function(){
							var de = item.attr("index");
							item.parent().remove();
							
							for(var k=0; k<localIds.length; k++){
								if(de === localIds[k]){
									localIds.splice(k,1);
									serverId.splice(k,1);
								}
							}
							$(".weui-uploader__info").html(($(".weui_uploader_file").length||0) + "/9");
							
						});
					});	
					upload();
					
				}
			});
		}else{
			alert("最多只能上传9张图片");
		}
	});
	
	
	var poi = 0;
    function upload() {
      wx.uploadImage({
        localId: localIds[poi],
       success: function (res) {
        	poi++;
          serverId.push(res.serverId);
		  $("#hidden-img-serverId").val($("#hidden-img-serverId").val()==""?res.serverId:("#hidden-img-serverId").val()+","+res.serverId);
		  
          if (poi < len) {
            upload();
          }
        },
        fail: function (res) {
          alert(JSON.stringify(res));
        }
      });
    }
	
	$("#gohome").on("click", function(){wx.closeWindow();});
	$("#updload_data").on("click", function(){
		uploadComplain(uploadData);
		//upload();
	});
	
	
	function uploadComplain(){
		var questioncontent = $("#questioncontent").val();
		var questionposition = $("#questionposition").val();
		var reachname = $("#reachname").val();
		var area = $("#area").val();
		var phone = $("#phone").val();

		uploadData =  {"serverId": serverId.join(","), "questioncontent":questioncontent,
				 "reachname":reachname,"area":area,"phone":phone, "x":_x,"y":_y,"questionposition":questionposition};
		$("#updload_data").off("click");
		$("#updload_data").removeClass("weui_btn_primary");
		$("#updload_data").addClass("weui_btn_disabled weui_btn_default");
		$.post('../weixin/savecomplain', uploadData,
			function(response){
				if(response == "true"){
					alert("您的投诉我们已收到，非常感谢您对广州河道治水工作的积极参与和支持！");
					location.href = "../weixin/encourage";
				}
		});
	}
}


