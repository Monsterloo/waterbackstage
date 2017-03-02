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
		<link rel="stylesheet" href="${ctx}/css/main.css" />
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.combo-select{border:0px;}
		.form-control{color:#000;}
		input{color:#000 !important;}
		.wx1{width:10% !important;}
		.wx2{width:90% !important;}
		th{text-align:center;}
		td{text-align:center;}
		.lookth{font-size:14px !important;font-weight:bold;}
		.lookTitle{text-align:left;border-bottom:2px solid #ccc;font-size:15px;}
		.lookTitle1{text-align:left;font-size:16px;}
		.modal-dialog .modal-content .modal-body div label{
			font-weight:normal;
		}
		
	</style>
	<script type="text/javascript" src="${ctx }/js/water/questionList.js"></script>
	<script type="text/javascript" src="${ctx }/js/water/answer.js"></script>
	<script type="text/javascript" src="${ctx }/js/water/export.js"></script>
</head>
<body>
<input type="hidden" id = "menu" value="${menu }">
<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<aside class="sidebar">
 			 <nav class="sidebar-nav" id="left_menu_bar">
 			 </nav>
		</aside>
   		
   		<div class="common_content">
			    <div class="data_div_header">
			    	<span>市民投诉管理</span>
			    </div>
			    <div class="data_div_condition">
			    	<table class="condition_table">
			    		<tr height="50%">
			    			<td width="6%" class="td-text-align-right">受理编号：</td>
			    			<td width="30%" class="td-text-align-left">
			    				<input type="text" id="search_code" class="form-control" placeholder="Search..." style="border-radius:4px">
			    			</td>
			    			<td width="6%" class="td-text-align-right">地区：</td>
			    			<td width="25%" class="td-text-align-left">
			    				<select tabindex="-1" name="area" id="area" class="form-control" >
			    					<option >请选择</option>
								</select>
			    			</td>
			    			<td width="7%" class="td-text-align-right">问题重要性：</td>
			    			<td width="33%" class="td-text-align-left">
			    				<select id="importance" class="form-control"> 
	                            	<option>请选择</option>
	                            	<option>一般</option>
	                            	<option>重要</option>
                            	 </select>
                            	<input type="hidden" name="importance" id="importance">
			    			</td>
			    		</tr>
			    		<tr height="50%">
			    			<td class="td-text-align-right">
			    				时间：
			    			</td>
			    			<td class="td-text-align-left">
			    				<input type="text" style="width:48%;" id="search_startTime" placeholder="请输入开始时间" class="form-control">
			    				<input type="text" style="width:48%;"  id="search_endTime"  placeholder="请输入结束时间" class="form-control">
			    			</td>
			    			<td class="td-text-align-right">
			    				问题类型：
			    			</td>
			    			<td class="td-text-align-left">
			    				<select name="qtype" id="qtype" class="form-control">
								</select>
	                       	 	<input  type="hidden" id="search_qtype" class="form-control" placeholder="Search..." style="border-radius:4px">
			    			</td>
			    			<td class="td-text-align-right">
			    				<button class="btn btn-default" type="button" style="height:35px;width:75px;" id="manageSearchBtn">
	                           		 <i class="fa fa-search"></i>
	                        	</button>
			    			</td>
			    			<td>
			    			</td>
			    		</tr>
			    	</table>
			    </div>
			    <div class="data_div_datalist">
			    	<table style="width:100%;font-size:12px;text-align:center;" id="apply_table">
						<thead>
							<tr style="height:63px;background:#4AB6E7;">
								<th width="4%"></th>
								<th width="10%">受理编号</th>
								<th width="6%">河段名称</th>
								<th width="12%">问题类型</th>
								<th width="8%">问题重要性</th>
								<th width="23%">问题描述</th>
								<th width="12%">投诉日期</th>
								<th width="9%">状态</th>
								<th width="16%" style="border-right:1px solid #4ab6e7;">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
			    </div>
   		</div>
    		
    		
    		
		<!--model apply-->
		<div class="modal fade" id="answer_model" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true" style="width: 100%;">
			<div class="modal-dialog" style="width:900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="applyModalLabel">回复投诉</h4>
					</div>
					<div class="modal-body">
						<table class="table table-hover table-striped table-bordered" id="answer_table" style="width:100%;">
							<thead>
								<tr>
									<th  id="th1"><input type="checkbox" id="checkAll" name="select_all"> 全选</th>
									<th  id="th2">回复内容</th>
									<th  id="th3">回复时间</th>
									<th  id="th4">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
					</div>
						<form id="answerForm" method="post" enctype="multipart/form-data" action="${ctx }/waterwx/answer">
						<div style="text-align:left;margin-left:15px;">
							<span style="font-size:20px;">受理回复</span>
							<input type="file" name="file" accept=".jpg,.gif,.png"  >
							<span style="float: right; margin-right:20px;color: red;margin-top:-20px;" >如有任何疑问可进行相关询问</span>
							<div>
								<textarea id="content" style="width:870px;"></textarea>
								<input type="hidden" name="qId" id="qId"/>
								<input type="hidden" name="con" id="con"/>
							</div>
							<div><button type="button" class="btn btn-default" data-dismiss="modal" onclick="saveAnswer()">回复</button></div>
						</div>
						</form>
				</div>
			</div>
		</div>
		<!--model apply end-->
			<!--Modal-->
	    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	        <div class="modal-dialog" style="margin: 75px 300px;">
	            <div class="modal-content" style="width:900px;">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                        &times;
	                    </button>
	                    <h4 class="modal-title" id="myModalLabel">
	                        	投诉受理
	                    </h4>
	                </div>
	                <div class="modal-body" style="width:890px;height:490px;overflow-y:scroll">
	                    	<input type="hidden" id="id1"/>
	                        <div class="form-group col-lg-12">
	                            <label for="questiontype" class="control-label  col-lg-2">投诉人</label>
	                             <input type="text" class=" col-lg-4" id="nickname" name="nickname">
	                            <label for="questiontype" class="control-label  col-lg-2">投诉人联系电话</label>
	                             <input type="text" class=" col-lg-4" id="phone1" name="phone">
	                        </div>
	                        <div class="form-group col-lg-12">
	                             <label for="questiontype" class="col-lg-2">问题类型</label>
	                            <select id="selecttype11" class="col-lg-4"> <option>请选择</option> </select>
	                             <input type="hidden" class=" col-lg-6" name="questiontype" id="questiontype1">
	                            <label for="questiontype" class="control-label  col-lg-2">问题重要性</label>
	                             <select id="importance" class="col-lg-4"> 
	                             	<option>请选择</option>
	                             	<option>一般</option>
	                             	<option>重要</option>
	                              </select>
	                             <input type="hidden" class=" col-lg-6" name="importance" id="importance">
	                        </div>
	                         <div class="form-group col-lg-12">
								 <label for="questiontype" class="control-label  col-lg-2">局发文编号</label>
	                             <input type="text" class=" col-lg-4" id="ciryAssignedcode" name="ciryAssignedcode">
	                             <label for="questioncontent" class="control-label  col-lg-2">治水办发文编号</label>
	                            <input type="text" class=" col-lg-4" id="areaAssignedcode"  name="areaAssignedcode">
	                        </div>
	                        <div class="form-group col-lg-12">
	                            <label for="questioncontent" class="control-label  col-lg-2">问题描述</label>
	                            <input type="text" class=" col-lg-10" id="questioncontent1" name="questioncontent">
	                        </div>
	                        <div class="form-group col-lg-12">
	                            <label for="questionposition" class="control-label  col-lg-2">问题位置</label>
	                            <input type="text" class=" col-lg-10" id="questionposition" name="questionposition">
	                        </div>
	                        <div class="form-group col-lg-12">
								<label for="answerlimitTime"  class="control-label  col-lg-2">截止时间</label>
	                            <input type="text" class=" col-lg-4" placeholder="请输入截止时间" id="answerlimitTime" name="answerlimitTime">
	                            <label for="questiontype" class="control-label  col-lg-2">行政区域</label>
	                             <select id="area1" name="area1" class=" col-lg-4" >
	                           		<option>请选择</option>
								</select>
	                            <input type="hidden" class=" col-lg-4" id="area2" name="area2">
	                        </div>
	                        <div class="form-group col-lg-12">
	                            <label for="questioncontent" class="control-label  col-lg-2">备注</label>
	                            <textarea id="remark" name="remark" style="width:80%;height:30px;"></textarea>
	                        </div>
	                       	<div class="form-group col-lg-12" id="imgAttch">
	                            
	                        </div>
	                        <div id="allmap" class="col-lg-12" style="width:98%;margin:0 auto;margin-top:20px;border: 1px solid #ccc;height:200px;"></div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-default"
	                            data-dismiss="modal">关闭
	                    </button>
	                    <button type="button" id="btn_return" class="btn btn-primary lookClose" onclick="acceptNo();">非河道问题
	                    </button>
	                    <button type="button" id="btn_save" class="btn btn-primary lookClose" onclick="saveQuestion();">
	                        	受理
	                    </button>
	                </div>
	            </div><!-- /.modal-content -->
	        </div><!-- /.modal -->
		</div>
		<!--model apply-->
		<div class="modal fade" id="wx_question" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true" style="width: 100%;">
			<div class="modal-dialog" style="width:700px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="applyModalLabel">无效投诉分类</h4>
					</div>
					<div class="modal-body" >
						<table class="table table-hover table-striped table-bordered" id="wx_table" style="width:100%;">
							<thead>
								<tr >
									<th class="wx1" style="width:10%;">全选<input type='checkbox' id="checkAllwx" name='checkAllwx' value=""></th>
									<th class="wx2" style="width:90%;">无效投诉标题</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
					</div>
						<div style="text-align:right;margin-bottom:15px;margin-right:15px;">
							<div><button type="button" class="btn btn-default" data-dismiss="modal" onclick="wxQuestionSave();">确定</button></div>
						</div>
				</div>
			</div>
		</div>
		<!--model apply end-->
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
						</div> 
					</div>
				</div>
			</div>
		</div>
		<!--model apply end-->
		<div class="modal fade" id="lookQuestion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
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
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
					</div>
				</div>
			<!-- /.modal-content -->
			</div>
		<!-- /.modal -->
		</div>
		<!--model apply-->
		<div class="modal fade" id="feedbackQuestion" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true" style="width: 100%;">
			<div class="modal-dialog" style="width:900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="applyModalLabel">投诉反馈</h4>
					</div>
					<div class="modal-body">
						<table class="table table-hover table-striped table-bordered" id="feedback_table" style="width:100%;">
							<thead>
								<tr>
									<th style="width:10%;" id="th1"><input type="checkbox" id="checkAll" name="select_all"> 全选</th>
									<th style="width:15%;" id="th3">反馈人</th>
									<th style="width:15%;" id="th3">反馈人角色</th>
									<th style="width:50%;" id="th2">反馈内容</th>
									<th style="width:10%;" id="th4">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
					</div>
						<div style="text-align:left;margin-left:15px;">
							<span style="font-size:24px;">投诉反馈</span>
							<div>
								<textarea id="feedbackContent" style="width:870px;"></textarea>
								<input type="hidden" name="feedbackQId" id="feedbackQId"/>
							</div>
							<div style="margin:2px;"><button type="button" class="btn btn-default" data-dismiss="modal" onclick="saveFeedback()">反馈上级</button></div>
						</div>
				</div>
			</div>
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
