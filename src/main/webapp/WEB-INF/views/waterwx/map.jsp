<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<script type="text/javascript" src="../assets/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="../assets/jQuery-ui/combo.select.css" />
<script src="../assets/jQuery-ui/jquery.combo.select.js"></script>
<script type="text/javascript"
	src="../assets/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
<link rel="stylesheet" href="${ctx}/css/thePage.css" />
<!--font-awsome-->
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
<script type="text/javascript" src="../js/weixin/jquery-weui.min.js"></script>
<script type="text/javascript" src="../js/weixin/swiper.min.js"></script>
<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css" />
<!-- <script src="../assets/jQuery-ui/jquery.lightbox-0.5.js"></script> 
	  <link rel="stylesheet" href="../assets/jQuery-ui/jquery.lightbox-0.5.css"/>-->
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
<link rel="stylesheet"
	href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
<link href="../assets/bootstrap/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
<link rel="stylesheet"
	href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css" />
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js"></script>
<link rel="stylesheet"
	href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
<script type="text/javascript" src="${ctx }/js/water/map.js"></script>
<link rel="stylesheet" href="${ctx}/css/main.css" />
</head>
<body style="text-align: center;">

	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<div class="clearfix">
			<aside class="sidebar">
				<nav class="sidebar-nav" id="left_menu_bar" style="text-align:left;"></nav>
			</aside>
			<section class="content">
				<div id="showspace">
					<div class="col-lg-12">
						<div class="col-lg-1"
							style="padding-right: 0px; padding-top: 10px; font-size: 15px; padding-left: 10px;">
							<span>时间:</span>
						</div>
						<div class="col-lg-2" style="width: 150px;">
							<div class="input-group" style="width: 150px; padding-top: 5px;">
								<input type="text" style="height: 30px;" id="search_startTime"
									class="form-control" placeholder="请输入开始时间"
									style="border-radius:4px">
							</div>
						</div>
						<div class="col-lg-2" style="width: 150px;">
							<div class="input-group" style="width: 150px; padding-top: 5px;">
								<input type="text" style="height: 30px;" id="search_endTime"
									class="form-control" placeholder="请输入结束时间"
									style="border-radius:4px">
							</div>
						</div>
						<div class="col-lg-1"
							style="padding-right: 0px; padding-top: 10px; font-size: 15px;">
							<span>地区:</span>
						</div>
						<div class="col-lg-3 qymc" style="width: 150px; padding-top: 5px; margin-left: -20px;">
							<select tabindex="-1" name="area" id="area" style="width: 150px; height: 30px;" class="selectpicker">
								<option>请选择</option>
							</select>
							<input type="hidden" id="search_area" class="form-control"
								placeholder="Search...">
						</div>
						<div class="col-lg-2" style="height:20px;margin-left:10px;">
						<button type="button" class="btn btn-success" id="searchBtn" style="height:30px;padding-top:5px;margin-top:5px;">查询</button>
						</div>
						<div class="col-lg-1"
							style="padding-right: 0px; padding-top: 10px; font-size: 15px;">
							<span>投诉数量:</span>
						</div>
						<div class="col-lg-1"
							style="padding-right: 0px; padding-top:5px;  font-size: 15px;">
							<input type="text"  style="height: 30px;color:#000;" id="num" class="form-control" >
						</div>
					</div>
					<div id="allmap" class="col-lg-12"
						style="width: 98%; margin: 0 auto; margin-top: 20px; border: 1px solid #ccc; height: 500px;"></div>
				</div>
			</section>
		</div>
	</div>
	<script type="text/javascript">
$(document).ready(function(){

	/* $( "#reach" ).autocomplete({
		  source: function( request, response ) {
			  var term = request.term;
	          $.getJSON( "../waterwx/getRiverToWx?area=&rivername="+term, function( data, status, xhr ) {
	        	  if(status === "success"){
	        		  var rivers = [];
	                  for(var i=0; i<data.length; i++){
	                	  rivers.push(data[i].riverName);
	                  }
	                  response( rivers );
	        	  }
	              
	            });
	          }
		}); */
		//$("#river").comboSelect();
});

</script>
</body>
</html>
