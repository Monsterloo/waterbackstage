

function getUserLoction() {
    wx.getLocation({
        type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
            var speed = res.speed; // 速度，以米/每秒计
            var accuracy = res.accuracy; // 位置精度
            getTXMapLocation(longitude, latitude);
        },
        cancel: function (res) {
            alert('用户拒绝授权获取地理位置');
        }
    });
}

function OpenTXMap(lon,lat,address,name){
	wx.openLocation({
        latitude: lat, // 纬度，浮点数，范围为90 ~ -90
        longitude: lon, // 经度，浮点数，范围为180 ~ -180。
        name: name, // 位置名
        address: address, // 地址详情说明
        scale: 28, // 地图缩放级别,整形值,范围从1~28。默认为最大
        infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
    });
	
}


function OpenTencentMapForIphone(lon,lat,address,name){
	var url="http://apis.map.qq.com/uri/v1/marker/?marker=coord:"+lat+","+lon+";title:"+name+";addr:"+address+"&referer=myapp";
	$("#tx-map-iframe").attr("src",url).parent().show();
	$(".friens-menu").hide();
}

function TencentMapClose(){
	$(".tx-map").hide();
	$(".friens-menu").show();
}


function getTXMapLocation(jingdu,weidu) {
            var data={'location':weidu+","+jingdu,'key': "6AOBZ-5STRX-PVE4V-ZVZB6-3IC7O-YWF3G",'get_poi':0}
            var url="http://apis.map.qq.com/ws/geocoder/v1/?";
            data.output="jsonp";  
             $.ajax({
                  type:"get",
                  dataType:'jsonp',
                  data:data,
                  jsonp:"callback",
                  jsonpCallback:"QQmap",
                  url:url,
                  success: function (json) {
                      var add = json.result.address;
			$("#complain-content-location-lat").val(weidu);
			$("#complain-content-location-lon").val(jingdu)
                	$(".complain-content-location-text").html(add );
                 }
            })
        }

 function getUserAddress(jingdu,weidu) {
            var point = new BMap.Point(jingdu, weidu);
            var gc = new BMap.Geocoder();
            gc.getLocation(point, function (rs) {
                var addComp = rs.addressComponents;
                var address = addComp.province + addComp.city+ addComp.district + addComp.street + addComp.streetNumber
		$("#complain-content-location-lat").val(weidu);
		$("#complain-content-location-lon").val(jingdu)
                $(".complain-content-location-text").html(address);
            });
        }


function showLocationMap() {
	var lat=$("#complain-content-location-lat").val();
	var lon=$("#complain-content-location-lon").val();
	var add=$(".complain-content-location-text").html();
            wx.openLocation({
                latitude: lat, // 纬度，浮点数，范围为90 ~ -90
                longitude: lon, // 经度，浮点数，范围为180 ~ -180。
                name: add, // 位置名
                address: add, // 地址详情说明
                scale: 28, // 地图缩放级别,整形值,范围从1~28。默认为最大
                infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
            });
        }







