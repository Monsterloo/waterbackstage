<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
	<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
	<link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx}/css/thePage.css" />
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctx}/css/main.css" />
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.yl:hover{color:#0000ff;}
		td{padding-left:50px;}
		th{padding-left:50px;}
	</style>
</head>
<body style="text-align:center;">

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
     			 <nav class="sidebar-nav" id="left_menu_bar" style="text-align:left;">
     			 </nav>
    		</aside>
    		<section class="content">
							<div class="col-lg-12">
									<div class="panel panel-info ">
									<div class="panel-heading">内部通知查看</div>
							</div>
					</div>
			<!-- <div style="width:100%;height:100%;" id="collect_detail">
  				<iframe width="100%" frameborder="0" scrolling="auto" id="detail_iframe" onload="this.height=window.screen.height - 150"></iframe>
  			</div> -->
  			<div id="leamain">
  			       <iframe marginheight="0" src="../weixin/insidenotice" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="75%" id="iframepage" name="iframepage"  ></iframe>
  			 </div>
    	</section>
    	</div>
</div>

	<script>
		$(function() {
			
			
			
		});
	</script>
</body>
</html>
