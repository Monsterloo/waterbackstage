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
	<link rel="stylesheet" href="${ctx}/css/main.css" />
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
		tr th{text-align:center;}
		#dictInfo table,#dictInfo td,#dictInfo th{border:none;margin-top:3px}
		#dictInfo tr{border-top:1px solid #b0b0b0; }
		#dictInfo td,#dictInfo th{padding:6px 1px;}
		#dictInfo .td_odd{    background: #35b3ea;color: #fff;text-align: center;width:100px;}		
		
		
	</style>
	 <script type="text/javascript" src="${ctx }/js/water/riverList.js"></script> 
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
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar" style="text-align:left;">
     			 </nav>
    		</aside>
    		 <div class="common_content">
    		 		<div class="data_div_condition">
			    	<table class="condition_table">
			    		<tr height="50%">
		    				<td width="10%" class="td-text-align-right">河道名称：</td>
			    			<td width="20%" class="td-text-align-left">
			    				<input type="text"  id="riverSearch" class="form-control" placeholder="Search..." style="border-radius:4px">
			    			</td>
			    			<td width="6%" class="td-text-align-right">所属区域：</td>
			    			<td width="20%" class="td-text-align-left">
			    				<select tabindex="-1" name="areaSearch" id="areaSearch" class="form-control" >
								</select>
			    			</td>
			    			<td width="10%" class="td-text-align-right">归属污染河道：</td>
			    			<td width="26%" class="td-text-align-left">
			    				<select tabindex="-1" name="pollrivers" id="pollrivers" style="width:200px;height:34px;border-radius:4px;">
								</select>
			    			</td>
			    		</tr> 
			    		<tr height="50%">
			    			<!-- <td colspan="4"></td> -->
			    			<td colspan="7" style="text-align:right;padding-right:26px;">
			    			
<!-- 			    				<button class="btn btn-default" type="button" style="height:34px;" id="btn_upload">
                                    <i class="fa fa-upload"></i>导入数据
                                </button> -->
                                <button class="btn btn-default" type="button" style="height:34px;" id="searchBtn">
                                    <i class="fa fa-search"></i>查询
                                </button>
			    			
			    			    <!-- <button class="btn btn-default" id="btn_search" style="padding-left:12px;" type="button">
                                  <i class="fa fa-search"></i> 查询
                                </button> -->
			    				<button class="btn btn-default" id="btn_add" type="button">
                                     <i class="fa fa-plus"></i> 新增
                                </button>
                                <button class="btn btn-default " id="btn_modify" type="button">
                                     <i class="fa fa-pencil"></i> 修改
                                </button>
                                <button class="btn btn-default" id="btn_del" type="button" data-toggle="tooltip"
                                         data-placement="right" title="删除">
                                     <i class="fa fa-minus"></i> 删除 
                                </button>
			    			</td>
			    		</tr>
			    	</table>
			    </div>
    			<div class="data_div_datalist">
			    	<table style="width:100%;font-size:12px;text-align:center;text-align:center" id="apply_table">
						<thead>
							<tr style="height:63px;background:#4AB6E7;">
								<th></th>
								<th>河道标号</th>
								<th>河道名称</th>
								<th>所属地区</th>
								<th>起始位置</th>
								<th>终止位置</th>
								<th>归属污染河道</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
			    </div>
			   </div>
			   
			   
	<!--Modal-->
		<div class="modal fade" id="dictInfo" tabindex="-1" role="dialog" style="width: 800px; margin: auto;" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 780px;">
				<div class="modal-content" style="width: 750px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" style="font-size: 14px;">
							河道详细信息</h4>
					</div>
					
					<div style="width:100%;">
						<table style="width:90%;margin:0 auto;">
							<tr style="border:none;">
								<td class="td_odd" style="">河道标识</td><td style="width:237px"><span id="info_riverCode"></span></td>
								<td class="td_odd">河道名称</td><td><span id="info_riverName"></span></td>
							</tr>
							<tr>
								<td class="td_odd">行政区域</td><td><span id="info_area"></span></td>
								<td class="td_odd">开始位置</td><td><span id="info_start"></span></td>
							</tr>
							<tr>
								<td class="td_odd">终止位置</td><td><span id="info_end"></span></td>
								<td class="td_odd">河涌长度km</td><td><span id="info_length"></span></td>
							</tr>
							<tr>
								<td class="td_odd">河涌宽度m</td><td><span id="info_width"></span></td>
								<td class="td_odd">集雨面积(平方千米)</td><td><span id="info_coverArea"></span></td>
							</tr>
							<tr>
								<td class="td_odd">河涌等级</td><td><span id="info_grade"></span></td>
								<td class="td_odd">归属污染河道</td><td><span id="info_belongPollRiver"></span></td>
							</tr>
						</table>
					
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" id="btn_save" class="btn btn-primary"
							onclick="save();">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
			   
			   
			   
			   
			<!--Modal-->
    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true" style="width:800px;margin:auto;">
        <div class="modal-dialog" style="width:780px;">
            <div class="modal-content" style="width:750px;">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        	河道详细信息
                    </h4>
                </div>
                <div class="modal-body col-lg-12">
                    <form id="dictForm" role="form" method="post" action="${ctx }/river/saveRiver">
                    	<input type="hidden" name="id" id="id">
                    	<div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">河道标识</label>
                            <input type="text" class=" col-lg-4" id="riverCodeText">
                            <input type="hidden" class=" col-lg-4" name="riverCode" id="riverCode">
                            <label for="app_appname" class=" col-lg-2">河道名称</label>
                            <input type="text" class=" col-lg-4" name="riverName" id="riverName">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">行政区域</label>
                            <select style="height:30px;" id="area" name="area" class="col-lg-4">
                            	
                            </select>
                            <label for="app_appname" class=" col-lg-2">开始位置</label>
                            <input type="text" class=" col-lg-4" name="start" id="start">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">终止位置</label>
                            <input type="text" class=" col-lg-4" name="end" id="end">
                            <label for="app_appname" class=" col-lg-2">河涌长度km</label>
                            <input type="text" class=" col-lg-4" name="length" id="length">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">河涌宽度m</label>
                            <input type="text" class=" col-lg-4" name="width" id="width">
                            <label for="app_appname" class=" col-lg-2">集雨面积(平方千米)</label>
                            <input type="text" class=" col-lg-4" name="coverArea" id="coverArea">
                        </div>
                        <div class="form-group col-lg-12">
                         	<label for="app_description" class="col-lg-2">河涌等级</label>
                            <input type="text" class="col-lg-4" name="grade" id="grade">
                            <label for="belongPollRivers" class="col-lg-2">归属污染河道</label>
                           <!--  <input type="text" class="col-lg-4" name="belongPollRiver" id="belongPollRiver"> -->
                            <select style="height:30px;" id="belongPollRiver" name="belongPollRiver" class="col-lg-4">
                            	
                            </select>
                            
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="btn_save" class="btn btn-primary" onclick="save();">
                        	保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    	</div>
</div>
</body>
</html>
