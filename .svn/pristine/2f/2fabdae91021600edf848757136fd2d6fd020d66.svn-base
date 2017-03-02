wx.ready(function(){
	var serverId = [], localIds = [];
	var _x,_y, uploadData = {};
	
	 /**
	 * 选择区域
	 */
	$.getJSON('../waterwx/findAllArea', function(data){
		  var len = data.length;
		  var html = "<option value=''>选择行政区域</option>";
		  for(var i=0; i<len; i++){
			  if(data[i]){
				  html += "<option value="+data[i]+">"+data[i]+"</option>\n";
			  }
			 
		  }
		  $("#area").append(html);
	});
	var area = $("#area").val();
	$("#area").on("change",function(){
		var area = $("#area").val();
		if(area){
			loadRiver(area);
		}else{
			var html = "<option value=\"\">选择河段</option>";
			$("#reachname").empty().append(html);
		}

	});
	function loadRiver(area){
		$.getJSON('../waterwx/getRiverByArea?area='+area, function(data){
			var len = data.length;
			var html = "<option value=''>选择河段</option>";
			for(var i=0; i<len; i++){
				if(data[i]){
					html += "<option value="+data[i].riverName+">"+data[i].riverName+"</option>\n";
				}
			}
			$("#reachname").empty().append(html);
		});
		
	}
	$("#reachname").on("change",function(){
		var reachname = $("#reachname").val();
		if(reachname){
			$.getJSON('../waterwx/getGradeByRiverName?rivername='+reachname, function(data){
				  var len = data.length;
				  for(var i=0; i<len; i++){
					  if(data[i]){
						  var html="<input type='hidden' class='weui_panel_hd' value='" + data[i].grade +"' id='rivergrade'>";
					  }
				  }
				  $("#grade").empty().append(html);
				  });
				}
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
									ids.splice(k,1);
								}
							}
							
						});
					});	
					uploadImages(ids);
				}
			});
		}else{
			alert("最多只能上传5张图片");
		}
	});
	
	
    
    
    var poi = 0;
    function uploadImages(ids) {
      wx.uploadImage({
        localId: ids[poi].toString(),
        success: function (res) {
          serverId.push(res.serverId);
          ids.shift();
          uploadImages(ids);
        },
        fail: function (res) {
          alert(JSON.stringify(res));
        }
      });
    }


	
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
	});
	
	
	function uploadComplain(){
		var signinposition = $("#signinposition").val();
		var reachname = $("#reachname").val();
		var area = $("#area").val();
		var grade=$("#rivergrade").val();
		if(!area){
			alert("请选择行政区域");
			return ;
		}
		
		if(!reachname){
			alert("请选择河涌名称");
			return ;
		}
		
		if(localIds.length=='0'){
			alert("请选择上传照片");
			return ;
		}

		if(!signinposition){
			alert("请填写签到位置");
			return ;
		}
		uploadData =  {"serverId": serverId.join(","),
				 "reachname":reachname,"area":area,"grade":grade,"x":_x,"y":_y,"signinposition":signinposition};
		$("#updload_data").off("click");
		$("#updload_data").removeClass("weui_btn_primary");
		$("#updload_data").addClass("weui_btn_disabled weui_btn_default");
		$.post('../weixin/savesignin', uploadData,
			function(response){
				if(response == "true"){
					alert("签到成功！");
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
                      	var area = $("#area").val();
                      	loadRiver(area);//地图加载，加载河道信息
                        var label = address.district+address.street+address.street_number;
                        $("#signinposition").val(label);
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
	
	
	/*$( "#reachname" ).autocomplete({
	  source: function( request, response ) {
		  var term = request.term;
		  var area = $("#area").val;
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
	});*/
	
//end;
});


