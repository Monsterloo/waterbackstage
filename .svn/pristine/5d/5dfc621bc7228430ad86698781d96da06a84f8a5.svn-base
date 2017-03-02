wx.ready(function(){
	var serverId = [], localIds = [];
	var _x,_y, uploadData = {};
	
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
	
	$('#chooseImage').on("click", function(event){
		if(localIds.length<5){
			 wx.chooseImage({
				count: 5,
			    sizeType: ['original', 'compressed'],
			    sourceType: ['album', 'camera'],
				success : function(res) {
					var ids = res.localIds;
					var len = ids.length;
					for(var i=0; i<len; i++){
						localIds.push(ids[i]);
						var img = '<li class="weui_uploader_file" index="'+ids[i]+'" style="background-image:url('+ids[i]+');"><i class="weui_icon_cancel" style="float:right;"></i></li>';
						$(".weui_uploader_files").append(img);
						$(".weui_cell_ft").html(localIds.length+"/5");
					}
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
							
						});
					});	
					upload();
				}
			});
		}else{
			alert("最多只能上传5张图片");
		}
	});
	
	
	var poi = 0;
    function upload() {
      wx.uploadImage({
        localId: localIds[poi],
        success: function (res) {
        	poi++;
          serverId.push(res.serverId);
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
		
		if(!area){
			alert("请选择行政区域");
			return ;
		}
		
		if(localIds.length=='0'){
			alert("请选择上传照片");
			return ;
		}
		if(!questioncontent){
			alert("请填写问题描述");
			return ;
		}
		if(!questionposition){
			alert("请填写问题位置");
			return ;
		}
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
	
	wx.getLocation({
		type: 'wgs84',
	    success: function (res) {
            var x = res.longitude; // 经度，浮点数，范围为180 ~ -180。
            var y = res.latitude; // 纬度，浮点数，范围为90 ~ -90
            _x = x;
            _y = y;
            
            var ggPoint = new BMap.Point(x,y);
          //地图初始化
            var bm = new BMap.Map("allmap");
            bm.centerAndZoom(ggPoint, 15);
            bm.addControl(new BMap.NavigationControl());

            //坐标转换完之后的回调函数
            translateCallback = function (data){
              if(data.status === 0) {
            	var qsData = {'ak':"WPoOm3CViGGFjSZDkE2TPwsc",'location':data.points[0].lat+","+data.points[0].lng,'output':"json","pois":0};
              	$.ajax({  
                      type: "get",  
                      async: false,  
                      url: "http://api.map.baidu.com/geocoder/v2/",  
                      dataType: "jsonp",
                      data:qsData,
                      success: function(data1){
                      	var address = data1.result.addressComponent;
                      	$("#area").val(address.district);
                        var label = address.district+address.street+address.street_number;
                        $("#questionposition").val(label);
                          var marker = new BMap.Marker(data.points[0]);
                          bm.addOverlay(marker);
                          var label = new BMap.Label(label,{offset:new BMap.Size(20,-10)});
                          marker.setLabel(label); //添加百度label
                          bm.setCenter(data.points[0]);
                      },  
                      error: function(){  
                          alert('fail');  
                      }  
                  });
              }
            }
            bm.disableDragging(); 
            setTimeout(function(){
                var convertor = new BMap.Convertor();
                var pointArr = [];
                pointArr.push(ggPoint);
                convertor.translate(pointArr, 1, 5, translateCallback)
            }, 1000);
           
	    },
	    cancel: function (res) {
	      alert('用户拒绝授权获取地理位置');
	    }
   });
	
	
	$( "#reachname" ).autocomplete({
	  source: function( request, response ) {
		  var term = request.term;
		  var area = $("#area").val();
          $.getJSON( "../waterwx/getRiverToWx?area="+area+"&rivername="+term, function( data, status, xhr ) {
        	  if(status === "success"){
        		  var rivers = [];
                  for(var i=0; i<data.length; i++){
                	  rivers.push(data[i].riverName);
                  }
                  response( rivers );
        	  }
              
            });
          }
	});
	
//end;
});


