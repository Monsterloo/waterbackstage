<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>投诉处理</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
<script type="text/javascript" src="../assets/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="../css/weixin/weui.min.css" />
<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css" />
<script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
<link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
<style type="text/css">
.celltit {
	margin-right: 15px;
}

.cellinfo {
	color: #888;
}
input{width:80%;}
</style>
</head>
<body>
	<div class="bd" style="height: 100%;">
		<div class="container" id="container">
			<input style="display:none;" id="role" value="${role }">
			<input style="display:none;" id="username" value="${username}">
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
			<input type="hidden" id="id1" value="${question.id }" />
			 <div class="weui_cell weui_cell_select weui_select_after">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">问题类型</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" id="selecttype11" ><option></option></select>
	                 <input type="hidden" class=" col-lg-6" name="questiontype" id="questiontype1">
	           	 </div>
   			</div>
   			<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">投诉人电话</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="phone1" name="phone" />
	            </div>
	        </div>
	         <div class="weui_cells_title">问题位置</div>
			    <div class="weui_cells weui_cells_form">
			        <div class="weui_cell">
			            <div class="weui_cell_bd weui_cell_primary">
			                <input type="text" class="weui_textarea" id="questionposition" name="questionposition"/>
			            </div>
			            
			        </div>
   			 </div>
   			 <div class="weui_cells_title">问题描述</div>
		     <div class="weui_cells weui_cells_form">
		        <div class="weui_cell">
		            <div class="weui_cell_bd weui_cell_primary">
		                <textarea class="weui_textarea"  rows="3" id="questioncontent1"></textarea>
		            </div>
		        </div>
		    </div>
	        <!-- <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">问题描述</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="questioncontent1" name="questioncontent" />
	            </div>
	        </div> -->
	       <!--  <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">问题位置</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="questionposition" name="questionposition" />
	            </div>
	        </div> -->
	        <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">截止时间</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" placeholder="请输入截止时间" id="answerlimitTime" name="answerlimitTime" />
	            </div>
	        </div>
	        <div class="weui_cell weui_cell_select weui_select_after">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">行政区域</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select"  id="area1" name="area1" ><option></option></select>
	           	 </div>
   			</div>
   			<div class="weui_cell">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">河道选择</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="river1"  placeholder="请输入河涌名称"/>
	           	 </div>
   			</div>
   			<div class="weui_cell">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">左右岸</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="coast1"  placeholder="请输入左右岸"/>
	           	 </div>
   			</div>
   			<div class="weui_cell">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">河段</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                 <input class="weui_input js-typeahead-input" type="text" id="reach1"  placeholder="请输入河段名称"/>
	           	 </div>
   			</div>
             <input type="hidden" class=" col-lg-4" id="area2" name="area2">
			<input type="hidden" name="reachname" id="river2"/>
			<input type="hidden" name="reachname" id="coast2"/>
			<input type="hidden" name="reachname" id="reach2"/>
			
			<div class="weui_cell weui_cell_select weui_select_after">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">区级河长</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select"   name="areapersonname" id="areapersonname" ></select>
	           	 	<input type="hidden" class=" col-lg-4" id="areaPerson" name="areaPerson">
	           	 </div>
   			</div>
			<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">联系电话</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text"  id="areaPhone" name="areaPhoneareaPhone" />
	            </div>
	        </div>
	        <div class="weui_cell weui_cell_select weui_select_after">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">镇街河长</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select"   name="streetpersonname" id="streetpersonname" ></select>
	                 <input type="hidden" class=" col-lg-4" id="streetPerson" name="streetPerson">
	           	 </div>
   			</div>
			<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">联系电话</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="streetPhone" name="streetPhone" />
	            </div>
	        </div>
	        <div class="weui_cell weui_cell_select weui_select_after">
	            <div class="weui_cell_hd">
	                <label for="" class="weui_label">村级河长</label>
	            </div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select"   name="villagepersonname" id="villagepersonname" ></select>
	           	 	<input type="hidden" class=" col-lg-4" id="villagePerson" name="villagePerson">
	           	 </div>
   			</div>
			<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">联系电话</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input js-typeahead-input" type="text" id="villagePhone" name="villagePhone" />
	            </div>
	        </div>
        </div>
		</div>
		<div id="allmap"
			style="width: 98%; margin: 0 auto; margin-top: 20px; border: 1px solid #ccc; height: 200px;"></div>
	<div class="button_sp_area">
	  <a href="javascript:;" id="saveQuestion" class="weui_btn weui_btn_mini weui_btn_default">投诉受理</a>
	  <a href="javascript:;" id="answerQuestion" class="weui_btn weui_btn_mini weui_btn_default open-popup" data-target="weui-popup-container">受理回复</a>
	  <a href="javascript:;" id="wxQuestion" class="weui_btn weui_btn_mini weui_btn_default">无效投诉</a>
	  <a href="javascript:;" id="feedbackQuestion"  class="weui_btn weui_btn_mini weui_btn_default">反馈上级</a>
	  <a href="javascript:;" id="endQuestion"  class="weui_btn weui_btn_mini weui_btn_default">完结投诉</a>
  </div>
  <!-- 受理回复 -->
  <div id="answerHtml" class="weui-popup-container">
	  <div class="weui-popup-overlay"></div>
	  <div class="weui-popup-modal" id="answerDiv">
	  	 <!--  <div class="weui_uploader">
                    <div class="weui_uploader_hd weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">图片上传</div>
                        <div class="weui_cell_ft">0/5</div>
                    </div>
                    <div class="weui_uploader_bd">
                        <ul class="weui_uploader_files">
                        </ul>
                        <div class="weui_uploader_input_wrp">
                            <input class="weui_uploader_input" id="chooseImage" type="button"/>
                        </div>
                    </div>
                </div>  -->
	   	<!--  <div class="weui_cells_title">回复内容</div>
		 <div class="weui_cells weui_cells_form">
		 		
		 </div> -->
	  </div>
  </div>
  <!-- 无效投诉 -->
  <div id="wxHtml" class="weui-popup-container">
	  <div class="weui-popup-overlay"></div>
	  <div class="weui-popup-modal wxDiv" id="wxDiv">
	  	
	  </div>
  </div>
  <!-- 反馈上级 -->
  <div id="feedbackHtml" class="weui-popup-container">
	  <div class="weui-popup-overlay"></div>
	  <div class="weui-popup-modal feedbackDiv" id="feedbackDiv">
	  	
	  </div>
  </div>
	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	<script src="../js/weixin/jquery-weui.min.js"></script>
	<script src="../js/weixin/swiper.min.js"></script>
	<script src="../js/complainweixin/handle.js"></script>
	<script src="../js/complainweixin/answerweixin.js"></script>
    <!-- <script>
    wx.config({
        debug: false,
        appId: '${appId}',
        timestamp: '${timestamp}',
        nonceStr: '${noncestr}',
        signature: '${signature}',
        jsApiList: [
			"chooseImage",
			"uploadImage",
			"openLocation",
			"getLocation"
        ]
    });
    </script> -->
	<script type="text/javascript">
		var x = '${question.x}';
		var y = '${question.y}';
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
