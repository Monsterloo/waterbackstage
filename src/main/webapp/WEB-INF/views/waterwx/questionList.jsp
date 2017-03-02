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
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<script type="text/javascript" src="../assets/jquery-2.1.4.min.js"></script>
	  <link rel="stylesheet" href="../assets/jQuery-ui/combo.select.css"/>
	 <script src="../assets/jQuery-ui/jquery.combo.select.js"></script>
	<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
	<link rel="stylesheet" href="${ctx}/css/thePage.css" />
	<!--font-awsome-->
		<link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="../js/weixin/jquery-weui.min.js"></script>
	<script type="text/javascript" src="../js/weixin/swiper.min.js"></script>
	<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css"/>
	<!-- <script src="../assets/jQuery-ui/jquery.lightbox-0.5.js"></script> 
	  <link rel="stylesheet" href="../assets/jQuery-ui/jquery.lightbox-0.5.css"/>-->
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
	 <script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
	  <link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
	  <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" ></script>
		<link rel="stylesheet" href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.yl:hover{color:#0000ff;}
		.combo-select{border:0px;}
		.form-control{color:#000;}
		input{color:#000 !important;}
		.wx1{width:10% !important;}
		.wx2{width:90% !important;}
		th{text-align:center;}
		td{text-align:center;}
		.lookth{font-size:14px !important;font-weight:bold;}
		.lookTitle{text-align:left;border-bottom:2px solid #ccc;font-size:15px;font-weight:bold;}
		.lookTitle1{text-align:left;font-size:16px;font-weight:bold;}
	</style>
	<script type="text/javascript" src="${ctx }/js/water/allQuestion.js"></script>
	<script type="text/javascript" src="${ctx }/js/water/answer.js"></script>
	<script type="text/javascript" src="${ctx }/js/water/export.js"></script>
</head>
<body style="text-align:center;">
<input type="hidden" id = "menu" value="${menu }">
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
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-info ">
									<div class="panel-heading">投诉信息</div>
										<div class="panel-body">
										<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>受理编号:</span>
										</div>
										<div class="col-lg-2" style="width:100px;" >
											<div class="input-group"  style="width:100px;padding-top:5px;">
	                                           	<input type="text" style="height:30px;color:#000;" id="search_code" class="form-control" placeholder="Search..." style="border-radius:4px">
	                                        </div>
										</div>
										
										<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>地区:</span>
										</div>
										<div class="col-lg-2 qymc " style="width:150px;padding-top:5px;margin-left:-20px;" >
											<select tabindex="-1" name="area" id="area" style="width:150px;height:30px; "  class="selectpicker" >
											<option >请选择</option>
											</select>
											<input type="hidden" id="search_area" class="form-control" placeholder="Search...">
										</div>
										<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>河段:</span>
										</div>
										<div class="col-lg-2 qymc " style="width:150px;padding-top:5px;margin-left:-20px;" >
											<select tabindex="-1"  name="river" id="river" style="width:150px;height:30px;color:#000;"   >
											<option >请选择</option>
											</select>
											<input type="hidden" id="search_river" class="form-control" placeholder="Search...">
										</div>
										<div class="col-lg-1 qymc" style="width:150px;margin-left:5px;padding-top:5px;">
											<select name="coast" id="coast" style="width:150px;height:30px;" >
												<option >请选择</option>
											</select>
											<input type="hidden" id="search_coast" class="form-control" placeholder="Search...">
										</div>
										<div class="col-lg-2 qymc" style="width:160px;padding-top:5px;">
											<div class="input-group">
											<select name="reach" id="reach"  style="width:140px;height:30px;" >
											</select>
	                                            	<input type="hidden" id="search_reach" class="form-control" placeholder="Search...">
	                                        </div>
										</div>
										<div class="col-lg-12">
											
											<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; padding-left: 10px;">
												<span>时间:</span>
											</div>
											<div class="col-lg-2" style="width:150px;" >
												<div class="input-group"  style="width:150px;padding-top:5px;">
	                                           		<input type="text" style="height:30px;" id="search_startTime" class="form-control" placeholder="请输入开始时间" style="border-radius:4px">
	                                       	 	</div>
											</div>
											<div class="col-lg-2" style="width:150px;" >
												<div class="input-group"  style="width:150px;padding-top:5px;">
	                                           		<input type="text" style="height:30px;" id="search_endTime" class="form-control" placeholder="请输入结束时间" style="border-radius:4px">
	                                       	 	</div>
											</div>
											<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>越级下发:</span>
										</div>
										<div class="col-lg-2 qqid" style="width:100px;" >
											<div class="input-group" style="width:100px;padding-top:5px;" >
											<select name="iscross" id="iscross" style="width:100px;height:30px;">
												<option >请选择</option>
												<option>是</option>
												<option>否</option>
											</select>
	                                           	<input type="hidden" id="search_questionstatus" class="form-control" placeholder="Search..." style="border-radius:4px">
	                                        </div>
										</div>
										<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>状态:</span>
										</div>
										<div class="col-lg-2 qqid" style="width:100px;" >
											<div class="input-group" style="width:100px;padding-top:5px;" >
											<select name="status" id="status" style="width:100px;height:30px;">
												<option >请选择</option>
												<option>已回复投诉</option>
												<option>已受理投诉</option>
												<option>待受理投诉</option>
												<option>无效投诉</option>
												<option>完结投诉</option>
											</select>
	                                           	<input type="hidden" id="search_questionstatus" class="form-control" placeholder="Search..." style="border-radius:4px">
	                                        </div>
										</div>
											<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; ">
											<span>问题类型:</span>
										</div>
										<div class="col-lg-3 qqid"  style="width:120px;" >
											<div class="input-group" style="width:120px;padding-top:5px;">
											<select name="qtype" id="qtype" style="width:120px;height:30px;" >
											</select>
	                                           	<input  type="hidden" id="search_qtype" class="form-control" placeholder="Search..." style="border-radius:4px">
	                                        <span class="input-group-btn">
	                                                <button class="btn btn-default" type="button" style="height:30px;" id="manageSearchBtn">
	                                                    <i class="fa fa-search"></i>
	                                                </button>
	                                            </span>
	                                        </div>
										</div>
										</div>
								<!--search -->
								<!-- data table -->
								<table class="table table-hover table-striped table-bordered" id="apply_table">
									<thead>
										<tr class="123">
											<th width="2%"></th>
											<th width="8%">受理编号</th>
											<th width="13%">河段名称</th>
											<th width="16%">问题类型</th>
											<th width="28%">问题描述</th>
											<th width="8%">投诉日期</th>
											<th width="9%">状态</th>
											<th width="8%">越级下发</th>
											<th width="8%">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<!-- data table end-->
                                 <button class="btn btn-default export">导出</button>
							</div>
						</div>
					</div>
				</div>
				<!--model apply-->
				<div class="modal fade" id="exportChoose" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true" style="width: 100%;">
					<div class="modal-dialog" style="width:700px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="applyModalLabel">选择导出列</h4>
							</div>
							<div class="modal-body">
								<div><input type="checkbox" id="allCheck"  name="allCheck">
										全选</div>
									<div class="col-lg-12">
										<input type="checkbox" id="codeExp" value="受理编号" name="exp">
										受理编号&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="areaExp" value="行政区域" name="exp">
										行政区域&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="riverExp" value="投诉河道" name="exp">
										投诉河道&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="typeExp" value="问题类型" name="exp">
										问题类型&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="questionpositionExp" value="问题位置" name="exp">
										问题位置&nbsp;&nbsp;&nbsp;
									</div>
									<div class="col-lg-12">
										<input type="checkbox" id="personExp" value="投诉人" name="exp">
										投诉人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="phoneExp" value="投诉人电话" name="exp">
										投诉人电话&nbsp;&nbsp;
										<input type="checkbox" id="timeExp" value="投诉时间" name="exp">
										投诉时间&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="contentExp" value="投诉内容" name="exp">
										投诉内容&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="positionExp" value="投诉位置" name="exp">
										投诉位置&nbsp;&nbsp;&nbsp;
									</div>
									<div class="col-lg-12">
										<input type="checkbox" id="photoExp" value="照片" name="exp">
										照片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="areapersonExp" value="区级河长" name="exp">
										区级河长&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="streetpersonExp" value="镇街河长" name="exp">
										镇街河长&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="villagepersonExp" value="村级河长" name="exp">
										村级河长&nbsp;&nbsp;&nbsp; 
										<input type="checkbox" id="answertimeExp" value="回复时间" name="exp">
										回复时间&nbsp;&nbsp;&nbsp;
									</div>
									<!-- <div clas="col-lg-4"></div>
									<div class="col-lg-2"></div>
									<div clas="col-lg-4"></div> -->
							</div>
								<div style="text-align:right;margin-bottom:15px;margin-right:15px;">
									<div><button type="button" class="btn btn-default" data-dismiss="modal" id="expCsv">导出</button></div>
								</div>
						</div>
					</div>
				</div>
				<!--model apply end-->
				<!--model apply-->
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
				<!--model apply end-->
				
				<div class="modal fade" id="lookQuestion" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">查看投诉</h4>
								</div>
								<div class="modal-body"
									style="width: 595px; height: 490px; overflow-y: scroll">
									<div class="col-lg-12 lookTitle">投诉基本信息</div>
										 <input type="hidden" id="id1" />
										<div class="form-group col-lg-12">
											<label for="questiontype" class="col-lg-2">问题类型</label> <span
												class=" col-lg-3" id="lookType"></span> <label
												for="questiontype" class="control-label  col-lg-3">投诉人联系电话</label>
											<span class=" col-lg-3" id="lookPhone"></span>
										</div>
										<div class="form-group col-lg-12">
											<label for="questioncontent" class="control-label  col-lg-2">问题描述</label>
											<span class=" col-lg-10" id="lookContent"></span>
										</div>
										<div class="form-group col-lg-12">
											<label for="questioncontent" class="control-label  col-lg-2">问题位置</label>
											<span class=" col-lg-10" id="lookPosition"></span>
										</div>
										<div class="form-group col-lg-12">
											<label for="questiontype" class="control-label  col-lg-2">行政区域</label>
											<span class="col-lg-3" id="lookArea"></span> <label
												for="answerlimitTime" class="control-label  col-lg-3">回复截止时间</label>
											<span class="col-lg-4" id="lookLimitTime"></span>
										</div>
										<div class="form-group col-lg-12">
											<label for="questiontype" class="control-label  col-lg-2">河道信息</label>
											<span class="col-lg-10" id="lookRiver"></span>
										</div>
										<div class="form-group col-lg-12" id="areaperson">
											<label for="questioncontent" class="control-label  col-lg-2">区级河长</label>
											<span class="col-lg-2" id="lookAreaPerson"></span> <label
												for="questioncontent" class="control-label  col-lg-2">街镇河长</label>
											<span class="col-lg-2" id="lookStreetPerson"></span> <label
												for="questioncontent" class="control-label  col-lg-2">村级河长</label>
											<span class="col-lg-2" id="lookVillagePerson"></span>
										</div>
										<div class="form-group col-lg-12" id="imgAttchLook"></div>
										<div id="allmaplook" class="col-lg-12"
											style="width: 98%; margin: 0 auto; margin-top: 20px; border: 1px solid #ccc; height: 200px;"></div>
									<div class="col-lg-12">&nbsp; </div>
									<div class="lookTitle1 col-lg-12">回复信息</div>
									<div id="lookAnswer">
										<table class="table table-hover table-striped table-bordered" id="look_answer" style="width:100%;">
											<thead>
												<tr>
													<th id="lookth" style="font-size:15px;">回复人</th>
													<th id="lookth" style="font-size:15px;">回复时间</th>
													<th id="lookth" style="font-size:15px;">回复内容</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
									<div class="lookTitle1 col-lg-12">反馈信息</div>
									<div id="lookFeedback">
										<table class="table table-hover table-striped table-bordered" id="look_feedback" style="width:100%;">
											<thead>
												<tr>
													<th style="font-size:15px;" id="lookth">反馈人</th>
													<th style="font-size:15px;" id="lookth">反馈人角色</th>
													<th style="font-size:15px;" id="lookth">反馈内容</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div><!-- /.modal -->
			</div>
				<!--model apply end-->
					<div style="display:none;" >
               <form class="navbar-form navbar-left"  id="exportDownload" action="${ctx }/waterwx/exportCSV" >
                    <input type="text" id="codeExport" name="codeExport" value=""/>
                    <input type="text" id="qtypeExport" name="qtypeExport" value=""/>
                    <input type="text" id="statusExport" name="statusExport" value=""/>
                    <input type="text" id="riverExport" name="riverExport" value=""/>
                    <input type="text" id="coastExport" name="coastExport" value=""/>
                     <input type="text" id="reachExport" name="reachExport" value=""/>
                      <input type="text" id="startExport" name="startExport" value=""/>
                       <input type="text" id="endExport" name="endExport" value=""/>
                        <input type="text" id="areaExport" name="areaExport" value=""/>
                     <input type="text" id="contentExport" name="contentExport" value=""/>
                    <button type="submit" class="btn btn-success export" id="export"><span class="fa fa-search"></span>查询</button>
               </form>
           </div>
           </div>
    	</section>
    	</div>
</div>

</body>
</html>
