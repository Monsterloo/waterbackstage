<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户Demo</title>
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
<!--[if !IE]-->
<link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css" />
<!--[else]>
<link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.ie.css" />
<![endif]-->

<!--jstree-->
<script type="text/javascript"
	src="../assets/jstree-3.3.0/jstree.min.js"></script>
<link rel="stylesheet"
	href="../assets/jstree-3.3.0/themes/default/style.min.css" />

<!--thePage-->
<script type="text/javascript" src="../js/admin/userflow.js"></script>
<script type="text/javascript" src="../js/tmpl/userflow_tmpl.js"></script>
<link rel="stylesheet" href="../css/thePage.css" />
<!--[if lt IE 9]>
		<script type="text/javascript" src="../assets/bootstrap/html5shiv.min.js" ></script>
		<script type="text/javascript" src="../assets/bootstrap/respond.min.js" ></script>
		<![endif]-->

</head>
<body>
	<!-- top nav -->
	<jsp:include page="inc/nav_top.jsp"></jsp:include>
	<!-- End of top nav -->
	<!-- Main Container -->
	<div class="clearfix">
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div class="col-lg-12">
					<h3 class="header" id="title_text"></h3>
				</div>
				<div class="panel-body">
				<div id="main-content"></div>
				</div>
    	</section>
    </div>

	<div class="modal fade" id="myModal_dialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" id="mydialog_body"></div>
				<div class="modal-footer" >
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