$(function() {  
	$("#search_startTime,#search_endTime").datetimepicker({
		language: 'zh-CN',
		format: "yyyy-MM-dd hh:ii:ss",
		autoclose: true,
		todayBtn: true
	});
	//地区
	$.ajax({
		type : "POST",
		url : "../waterwx/findAllArea",
		data :  {}, 
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, value){
				$("#area").append("<option class='areaOption'  >"+value+"</option>");
			});

		}

	});
	$("#searchBtn").on("click",function(){
		var startTime = $("#search_startTime").val();
		var area = $("#area").val();
		var endTime = $("#search_endTime").val();
		var param={
				area:area,
				sTime:startTime,
				eTime:endTime
		}
		$.ajax({
			type : "POST",
			url : "../waterwx/getAllQuestion",
			data :  param, 
			dataType : "json",
			async:false,
			success : function(data) {
				if(area == "增城区"){
					area = "增城市";
				}
				var num = 0;
				if(data == null || data.length == 0){
					var bm = new BMap.Map("allmap");
					bm.centerAndZoom(area);
				}else{
					var centerX = data[0].x;
					var centerY = data[0].y;
					var centerPoint = new BMap.Point(centerX,centerY);
					var bm = new BMap.Map("allmap");
					if(area == null || area == "" || area == "请选择"){
						bm.centerAndZoom("广州");
					}else{
						bm.centerAndZoom(area);
					}
					$.each(data, function(i, value){
						num++;
						var x = value.x; // 经度，浮点数，范围为180 ~ -180。
						var y = value.y; // 纬度，浮点数，范围为90 ~ -90
						var ggPoint = new BMap.Point(x,y);
						//  console.log(x+","+y);
						//地图初始化
						bm.addControl(new BMap.NavigationControl());
						var location=y+","+x;
						//坐标转换完之后的回调函数
						translateCallback = function (data){
							if(data.status === 0) {
								var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {  
			                        offset: new BMap.Size(10, 25), // 指定定位位置  
			                        imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移  
			                    });  
								var marker = new BMap.Marker(data.points[0]);
								bm.addOverlay(marker);
								var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});
								marker.setLabel(label); //添加百度label
								//   bm.setCenter(data.points[0]);
							}
						}

						setTimeout(function(){
							var convertor = new BMap.Convertor();
							var pointArr = [];
							pointArr.push(ggPoint);
							convertor.translate(pointArr, 1, 5, translateCallback)
						}, 1000);
					});
				}
					$("#num").val(num);
			},
			error:function(e){
				console.log(e);
				$("#num").val("0");
			}
		});
	});
	$.ajax({
		type : "POST",
		url : "../waterwx/getAllQuestion",
		data :  {}, 
		dataType : "json",
		async:false,
		success : function(data) {
			var centerX = data[0].x;
			var centerY = data[0].y;
			var centerPoint = new BMap.Point(113.275995,23.117055);
			var bm = new BMap.Map("allmap");
			bm.centerAndZoom("广州");
			var num = 0;
			$.each(data, function(i, value){
				num++;
				var x = value.x; // 经度，浮点数，范围为180 ~ -180。
				var y = value.y; // 纬度，浮点数，范围为90 ~ -90
				var ggPoint = new BMap.Point(x,y);
				//  console.log(x+","+y);
				//地图初始化
				bm.addControl(new BMap.NavigationControl());
				var location=y+","+x;
				//坐标转换完之后的回调函数
				translateCallback = function (data){
					if(data.status === 0) {
						var marker = new BMap.Marker(data.points[0]);
						bm.addOverlay(marker);
						var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});
						marker.setLabel(label); //添加百度label
						//   bm.setCenter(data.points[0]);
					}
				}

				setTimeout(function(){
					var convertor = new BMap.Convertor();
					var pointArr = [];
					pointArr.push(ggPoint);
					convertor.translate(pointArr, 1, 5, translateCallback)
				}, 1000);
				$("#num").val(num);
			});
		}
	});

});