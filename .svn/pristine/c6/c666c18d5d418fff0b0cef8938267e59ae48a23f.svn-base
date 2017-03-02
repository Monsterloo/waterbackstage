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
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
	 <link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" /> 
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.yl:hover{color:#0000ff;}
		td{padding-left:50px;}
		th{padding-left:50px;}
		/* .form-group{border:1px solid #666666;} */
	</style>
	 <script type="text/javascript" src="${ctx }/js/water/sign.js"></script> 
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
									<div class="panel-heading">巡河记录</div>
										<div class="panel-body">
											<div class="row">
                                    <div class="col-sm-9 text-right" style="margin-bottom: 5px">
                                        <div class="col-lg-2" style=" padding-top: 10px; font-size: 14px; " >
											<span>河道名称:</span>
										</div>
										<div class="col-lg-2"  >
											<div class="input-group"  >
	                                           	<input type="text"  id="riverSearch" class="form-control" placeholder="Search..." style="border-radius:4px">
	                                        </div>
										</div>
										
										<div class="col-lg-2" style=" padding-top: 10px; font-size: 14px; ">
											<span>行政区域:</span>
										</div>
										<div class="col-lg-2 qymc "  >
											<select tabindex="-1" name="areaSearch" id="areaSearch" style="width:130px;height:30px;padding-top:5px; "  class="selectpicker" >
											</select>
											<input type="hidden" id="search_area" class="form-control" placeholder="Search...">
										</div>
										<div class="col-lg-2" style=" padding-top: 10px; font-size: 14px; ">
											<span>河道等级:</span>
										</div>
										<div class="col-lg-1 qymc "  >
											
											<input type="hidden" id="search_area" class="form-control" placeholder="Search...">
                                           	 <!-- <button class="btn btn-default" id="btn_search" style="padding-left:12px;" type="button">
                                                <i class="fa fa-plus"></i> 查询
                                            </button> -->
                                            <div class="input-group" >
											<select tabindex="-1" name="gradeSearch" id="gradeSearch" style="width:120px;height:30px; "  class="selectpicker" >
											</select>
	                                        <span class="input-group-btn">
	                                                <button class="btn btn-default" type="button" style="height:30px;" id="searchBtn">
	                                                    <i class="fa fa-search"></i>
	                                                </button>
	                                            </span>
	                                        </div>
										</div>

                                </div>
										</div>
								<!--search -->
								<!-- data table -->
								<table class="table table-hover table-striped table-bordered" id="apply_table">
									<thead>
										<tr class="123">
											<th><input type='checkbox' id="checkAll" name='checkAll' value=""></th>
											<th>巡河人</th>
											<th>行政区域</th>
											<th>河道名称</th>
											<th>河道等级</th>
											<th>巡河时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<!-- data table end-->
                                 
							</div>
						</div>
					</div>
				</div>
			<!--Modal-->
   <div class="modal fade" id="lookSign" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        	查看巡河记录
                    </h4>
                </div>
                <div class="modal-body" style="height:400px;">
                    	<input type="hidden" id="id1"/>
                        <div class="form-group col-lg-12">
                            <label for="questiontype" class="col-lg-3">巡河人</label>
                            <span class=" col-lg-9" id="looksigninname"></span>
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="questiontype" class="control-label  col-lg-3">行政区域</label>
                            <span class="col-lg-9" id="lookarea"></span>
                        </div>
                        <div class="form-group col-lg-12">
                           <label for="answerlimitTime"  class="control-label  col-lg-3">河道名称</label>
                           	 <span class="col-lg-9" id="lookreachname"></span>
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="questiontype" class="control-label  col-lg-3">河道等级</label>
                             <span class=" col-lg-9" id="lookgrade"></span>
                        </div>
                         <div class="form-group col-lg-12">
                            <label for="questiontype" class="control-label  col-lg-3">巡河时间</label>
                            <span class="col-lg-9" id="looksigninDate"></span>
                        </div>
                        <div class="form-group col-lg-12">
							<label for="answerlimitTime"  class="control-label  col-lg-3">定位地址</label>
                           	 <span class="col-lg-9" id="looksigninposition"></span>
                        </div>
                       	<div class="form-group col-lg-12" id="imgAttchLook"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
			</div>
			<div class="modal fade" id="imgShow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
					<div class="modal-dialog" >
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="">图片</h4>
							</div>
							<div class="modal-body">
								<div class="bigImg"></div>
								<!-- <div style="text-align:right;margin-bottom:15px;margin-right:15px;">
									<div><button type="button" class="btn btn-default" data-dismiss="modal" id="expCsv">导出</button></div>
								</div> -->
							</div>
						</div>
					</div>
				</div>
    	</section>
    	</div>
</div>
</body>
</html>
