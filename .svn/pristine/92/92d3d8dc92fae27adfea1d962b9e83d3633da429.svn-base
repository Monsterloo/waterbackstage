<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>我的投诉</title>
<script type="text/javascript" src="../../assets/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
<script type="text/javascript" src="../../js/wx/complainlist.js"></script>
<link rel="stylesheet" href="../../css/weixin/weui.min.css" />
<link rel="stylesheet" href="../../css/weixin/jquery-weui.min.css" />
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
			<div class="weui_cells_title">
				照片上传<span style="color: #fd6e20;">（注：最多上传五张图片）</span>
			</div>
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
													<img src="../../weixin/export/${att.id}"
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
				<span class="celltit">行政区域</span> <span class="cellinfo">${question.area}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">河涌名称</span> <span class="cellinfo">${question.reachname}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">河道长度</span> <span class="cellinfo">${question.length}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">区级河长</span> <span class="cellinfo">${res.distMgrName}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">联系电话</span> <span class="cellinfo">${res.distMgrTel}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">镇街河长</span> <span class="cellinfo">${res.streetMgrName}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">联系电话</span> <span class="cellinfo">${res.streetMgrTel}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">村级河长</span> <span class="cellinfo">${res.villageMgrName}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">联系电话</span> <span class="cellinfo">${res.villageMgrTel}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">问题类型</span> <span class="cellinfo">${question.questiontype}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">问题描述</span> <span style="color: #888">${question.questioncontent}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">处理状态</span> <span style="color: #888">
					<c:choose>
						<c:when test="${question.status==0}">
							<font color="#2e9a3b">已回复投诉</font>
						</c:when>
						<c:when test="${question.status==1}">
							<font color="#d93a3a">待受理投诉</font>
						</c:when>
						<c:when test="${question.status==2}">
							<font color="#ff7e00">已受理投诉</font>
						</c:when>
						<c:when test="${question.status==3}">
							<font color="#ff7e00">无效投诉</font>
						</c:when>
					</c:choose>
				</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">投诉时间</span> <span style="color: #888">${question.complainDate}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">问题位置</span> <span class="cellinfo">${question.questionposition}</span>
			</div>
		</div>
		<div id="allmap"
			style="width: 98%; margin: 0 auto; margin-top: 20px; border: 1px solid #ccc; height: 200px;"></div>

		<div class="weui_panel weui_panel_access">
			<div class="weui_panel_hd">回复意见</div>
			<div class="weui_panel_bd" id="type_list">
				<c:choose>
					<c:when test="${! empty swjAnswer}">
						<c:forEach items="${swjAnswer}" var="answer">
							<pre
								style="margin-left: 5px; white-space: pre-wrap; word-wrap: break-word; border-bottom: 1px dashed #0000cc;">${answer.answercontent}</pre>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>

		<div class="weui_cells">
			<div class="weui_cell">
				<span class="celltit">经办人名称</span> <span style="color: #888">${res.villageMgrName}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">联系电话</span> <span style="color: #888">${res.villageMgrTel}</span>
			</div>
		</div>
		<a href="javascript:history.go(-1)"
			style="margin-top: 15px; margin-bottom: 35px;"
			class="weui_btn weui_btn_default">返回</a>
	</div>

	<script type="text/javascript" src="../../js/weixin/jweixin-1.0.0.js"></script>
	<script src="../../js/weixin/jquery-weui.min.js"></script>
	<script src="../../js/weixin/swiper.min.js"></script>
	<script type="text/javascript">
		var x = '${question.x}';
		var y = '${question.y}';
		var ggPoint = new BMap.Point(x, y);
		//地图初始化
		var bm = new BMap.Map("allmap");
		bm.centerAndZoom(ggPoint, 15);
		bm.addControl(new BMap.NavigationControl());

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
		bm.disableDragging();
		setTimeout(function() {
			var convertor = new BMap.Convertor();
			var pointArr = [];
			pointArr.push(ggPoint);
			convertor.translate(pointArr, 1, 5, translateCallback)
		}, 1000);

		$(document).ready(function() {
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
