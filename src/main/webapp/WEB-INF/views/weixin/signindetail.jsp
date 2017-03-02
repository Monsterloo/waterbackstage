<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>巡河记录明细</title>
<script type="text/javascript" src="../assets/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
<script type="text/javascript" src="../js/wx/complainlist.js"></script>
<link rel="stylesheet" href="../css/weixin/weui.min.css" />
<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css" />
<style type="text/css">
.celltit {
	margin-right: 15px;
}

.cellinfo {
	color: #888;
}
</style>
</head>
<body>
	<div class="bd" style="height: 100%;">
		<div class="container" id="container">
			<div class="weui_cells_title">上传的照片</div>
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div class="weui_uploader">
							<div class="weui_uploader_bd">
								<ul class="weui_uploader_files">
									<c:choose>
										<c:when test="${! empty attList}">
											<div class="zpr" id="image_list">
												<c:forEach items="${attList}" var="att">
													<img src="../weixin/export/${att.id}"
														style="width: 100px; height: 100px; float: left; margin-right: 5px; margin-bottom: 5px;" />
												</c:forEach>
											</div>
										</c:when>
									</c:choose>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="weui_cells">
			<div class="weui_cell">
				<span class="celltit">行政区域</span> <span class="cellinfo">${signin.area}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">河涌名称</span> <span class="cellinfo">${signin.reachname}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">河道等级</span> <span class="cellinfo" id="grade"></span>
			</div>
			<div class="weui_cell">
				<span class="celltit">签到人</span> <span class="cellinfo">${signin.signinname}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">签到时间</span> <span style="color: #888">${signin.signinDate}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">签到位置</span> <span class="cellinfo">${signin.signinposition}</span>
			</div>
		</div>
		<br>
		<div id="allmap"
			style="width: 98%; margin: 0 auto; margin-top: 20px; border: 1px solid #ccc; height: 200px;"></div>
		</div>

	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	<script src="../js/weixin/jquery-weui.min.js"></script>
	<script src="../js/weixin/swiper.min.js"></script>
	<script type="text/javascript">
	var x = '${signin.x}';
	var y = '${signin.y}';
	var ggPoint = new BMap.Point(x, y);
	//地图初始化
	var bm = new BMap.Map("allmap");
	bm.centerAndZoom(ggPoint, 15);
	bm.addControl(new BMap.NavigationControl());
	bm.disableDragging();
	//坐标转换完之后的回调函数
	translateCallback = function(data) {
		if (data.status === 0) {
			var qsData = {
				'ak' : "WPoOm3CViGGFjSZDkE2TPwsc",
				'location' : data.points[0].lat + "," + data.points[0].lng,
				'output' : "json",
				"pois" : 0
			};
			$.ajax({
				type : "get",
				async : false,
				url : "http://api.map.baidu.com/geocoder/v2/",
				dataType : "jsonp",
				data : qsData,
				success : function(data1) {
					var address = data1.result.addressComponent;
					var label = address.district + address.street
							+ address.street_number;
					var marker = new BMap.Marker(data.points[0]);
					bm.addOverlay(marker);
					var label = new BMap.Label(label, {
						offset : new BMap.Size(20, -10)
					});
					marker.setLabel(label); //添加百度label
					bm.setCenter(data.points[0]);
				},
				error : function() {
					alert('fail');
				}
			});
		}
	}
		
		setTimeout(function() {
			var convertor = new BMap.Convertor();
			var pointArr = [];
			pointArr.push(ggPoint);
			convertor.translate(pointArr, 1, 5, translateCallback)
		}, 1000);

		$(document).ready(function() {
			
			var data="";
			var grade=${signin.grade};
			if(grade=="1"){
				data="一般河道";
			}else{
				data="重要河道";	
			}
			$("#grade").html(data);
			
			var imgarray = [];
			var images = $("#image_list").children("img");
			for (var i = 0; i < images.length; i++) {
				imgarray.push(images[i].src);
			}
			var photob = $.photoBrowser({
				items : imgarray
			});
			$("#image_list").on("click", function() {
				photob.open();
			});
		});
	</script>
</body>
</html>
