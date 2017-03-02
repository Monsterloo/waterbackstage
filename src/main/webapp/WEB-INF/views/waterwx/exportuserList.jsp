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
	<link rel="stylesheet" href="${ctx}/css/thePage.css" />
	<link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
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
	 <script type="text/javascript">
	 $(document).ready(function(){
		 $("#datatable").hide();
		 			$("#dataLoad").show();
		 			$("#dataError").hide();
				   function myFunction(){
					   $("#dataLoad").hide();
					   $("#datatable").show();
				   	var applyTable = $('#apply_table').DataTable({
						processing: true,
					    serverSide: true,
					    lengthChange:false,
						paging: true,
					    searching:false,
					    ordering: false,
						info:false,
				   	 	ajax: {
				   	 			url : "${ctx}/waterwx/searchAllExportUser",
								type:'post',
								data:{}
							},
						columns:[
							{'data':'id'},
							{'data':'nickname'},
							{'data':'openid'},
							{'data':'headimgurl',
								'render':function(data){
									return "<div><img src='" + data +"'alt='微信用户头像' width='64' height='64'></div>";
								}
							}
						]
					});
				   }
			   	
		   			$.getJSON('../weixin/getGiftingMoneyUser', function(data){
		   				var len = data.length;
			   			 for(var i=0; i<len; i++){
			   			  if(data[i]=="success"||data[i]=="null"){
			   				 myFunction();
			   				 //alert("数据加载成功！");
			   			  }else{
			   				  $("#dataLoad").hide();
			   				  $("#dataError").show();
			   				 //alert("数据加载失败！");
			   			  }
			   			}
		   			});
		   			
			// 导出方法
		   		$('#btn_export').on('click', function() {
		   			if (confirm("确认导出？")) {
		   				$("#exportDownload").submit();
		   				alert("正在导出，请稍后！");
		   			} 
		   		});
			
		   	 });
			  
		   		
	</script> 
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
    		<aside class="sidebar" style="width:200px;">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div id="showspace">
							<div class="col-lg-12">
								<div class="panel panel-info ">
									<div class="panel panel-info ">
									<div class="panel-heading">红包信息导出</div>
									 <div id="dataLoad" style="display:none"><!--页面载入显示-->
										   <table width=100% height=70% border=0 align=center valign=middle>
										    <tr height=50%><td align=center>&nbsp;</td></tr>
										    <tr><td align=center>数据载入中，请稍后......</td></tr>
										    <tr height=50%><td align=center>&nbsp;</td></tr>
										   </table>
									</div>
									<div id="dataError" style="display:none"><!--页面载入显示-->
										   <table width=100% height=70% border=0 align=center valign=middle>
										    <tr height=50%><td align=center>&nbsp;</td></tr>
										    <tr><td align=center>数据获取失败，请联系管理员！</td></tr>
										    <tr height=50%><td align=center>&nbsp;</td></tr>
										   </table>
									</div>
									  <div id="datatable">
									<div class="row">
                                    <div class="col-sm-3" style="margin-left: 0px; margin-bottom: 5px">
                                    </div>
                                   
                                    <div class="col-sm-9 text-right" style="margin-bottom: 5px;margin-top: 10px;">
                                        <div class="btn-group">
                                            <button class="btn btn-default" id="btn_export" type="button" style="margin-right: 5px;">
                                                <i class="fa fa-plus"></i> 导出
                                            </button>
                                            <!-- <button class="btn btn-default " id="btn_refresh" type="button">
                                                <i class="fa fa-pencil"></i> 刷新
                                            </button> -->
                                        </div>
                                	</div>
								  </div>
								
								<!--search -->
								<!-- data table -->
								<table class="table table-hover table-striped table-bordered" id="apply_table">
									<thead>
										<tr class="123">
											<th>序号</th>
											<th>用户昵称</th>
											<th>openid</th>
											<th>用户头像</th>
										</tr>
									</thead>
								</table>
							</div>
							</div>
						</div>
					</div>
				    <div style="display:none;" >
               				<form class="navbar-form navbar-left"  id="exportDownload" action="${ctx }/waterwx/exportTXT" ></form>
           			</div>
				</div>
    	</section>
		</div>
    </div>
</body>
</html>
