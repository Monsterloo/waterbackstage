<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>河道投诉管理系统</title>
<script type="text/javascript">
</script>
<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="../assets/jsviews.min.js"></script>
<script type="text/javascript" src="../assets/jquery.validate.min.js"></script>
<script type="text/javascript" src="../assets/jquery.form.js"></script>
<script type="text/javascript" src="../assets/jquery.pagination.js"></script>
<!--bootstrap-->
<script type="text/javascript"
	src="../assets/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" href="../assets/bootstrap/bootstrap.min.css" />
<!--font-awsome-->
<link rel="stylesheet"
	href="../assets/fontawesome/css/font-awesome.min.css" />
<!--mentisMenu-->
<script type="text/javascript"
	src="../assets/metisMenu/metisMenu.min.js"></script>
<link rel="stylesheet" href="../assets/metisMenu/metisMenu.min.css" />
<!--echarts-->
<script type="text/javascript" src="../assets/echart/echarts.min.js"></script>

<!--jquery ui-->
<script type="text/javascript"	src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript"	src="../assets/jQuery-ui/jquery-ui-i18n.js"></script>
<!--[if !IE]-->
<link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css" />
<!--[else]>
<link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.ie.css" />
<![endif]-->
<!--splitter
<script type="text/javascript"
	src="../assets/jcubic-jquery.splitter/jquery.splitter-0.14.0.js"></script>
<link rel="stylesheet"
	href="../assets/jcubic-jquery.splitter/jquery.splitter.css" />-->
<!--jstree-->
<script type="text/javascript"
	src="../assets/jstree-3.3.0/jstree.min.js"></script>
<link rel="stylesheet"
	href="../assets/jstree-3.3.0/themes/default/style.min.css" />

<!--thePage-->
<script type="text/javascript" src="../js/admin/flow.js"></script>
<script type="text/javascript" src="../js/tmpl/sysmanager.js"></script>
<link rel="stylesheet" href="../css/thePage.css" />
<link rel="stylesheet" href="../css/main.css" />
<!--[if lt IE 9]>
<script type="text/javascript" src="../assets/bootstrap/html5shiv.min.js" ></script>
<script type="text/javascript" src="../assets/bootstrap/respond.min.js" ></script>
		<![endif]-->
	
	<style type="text/css">
	
		.metismenu .active>a>.glyphicon.arrow:before{
			content:"";
		}
		.metismenu .glyphicon.arrow:before{
			content:"";
		}
	</style>
</head>
<body>
	<!-- top nav -->
	<jsp:include page="inc/nav_top.jsp"></jsp:include>
	<!-- End of top nav -->
	<!-- side bar -->
	<div class="clearfix">
   		<aside class="sidebar">
   			 <nav class="sidebar-nav" id="left_menu_bar">
   			 </nav>
   		</aside>
   		<section class="content">
	     	 <div class="col-lg-12">
				<h3 class="content_header" id="title_text"></h3>
			</div>
			<div class="panel-body">
				<div id="main-content"></div>
			</div>
   		</section>
   	</div>
	<!-- End side bar -->
	
	<div class="modal fade" id="myModal_dialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<input type="hidden" id="hide_area" value="${area}"/>
        <input type="hidden" id="hide_role" value="${role}"/>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" id="mydialog_body" style="max-height:600px;overflow:auto;"></div>
				<div class="modal-footer" >
				<ul id="dialog_pagination" style="margin: 0;" class="pagination pagination-sm pull-left"></ul>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
					<button type="button" class="btn btn-primary" id="myModal_dialog_ok">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>