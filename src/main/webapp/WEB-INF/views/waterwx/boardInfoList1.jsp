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
		th{
			text-align:center;
		}
		
		#dictInfo table,#dictInfo td,#dictInfo th{border:none;margin-top:3px}
		#dictInfo tr{border-top:1px solid #b0b0b0; }
		#dictInfo td,#dictInfo th{padding:7px 1px;}
		#dictInfo .td_odd{    background: #35b3ea;color: #fff;text-align: center;width:100px;}
		#dictInfo td span{margin-left: 5px;}
		
		.form-control{
			width:85%;
		}
		
		label{
			font-weight:normal;
		}
		
		.common_content .data_div_condition .condition_table{font-size:14px;}
		
		
	</style>
	 <script type="text/javascript" src="${ctx }/js/water/boardInfoList1.js"></script> 
</head>
<body>

<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<%-- <a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			<input type="hidden" id="hide_area" value="${area}"/>
            <input type="hidden" id="hide_role" value="${role}"/>
			</a> --%>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar" style="text-align:left;">
     			 </nav>
    		</aside>
    		
    		
    		 <div class="common_content">
			    <div class="data_div_header">
			    	<span>公示牌列表</span>
			    </div>
			    <div class="data_div_condition">
			    	<table class="condition_table">
			    		<tr height="50%">
		    				<td width="10%" class="td-text-align-right">河道名称：</td>
			    			<td width="20%" class="td-text-align-left">
			    				<input type="text"  id="riverName" class="form-control" placeholder="Search..." style="border-radius:4px">
			    			</td>
			    			<td width="6%" class="td-text-align-right">区属：</td>
			    			<td width="20%" class="td-text-align-left">
			    				<select tabindex="-1" name="areaSearch" id="areaSearch" class="form-control" >
								</select>
			    			</td>
			    			<td width="9%" class="td-text-align-right">公示牌设置：</td>
			    			<td width="26%" class="td-text-align-left">
			    				<input type="text"  id="boardPosition" class="form-control" placeholder="Search..." style="border-radius:4px">
			    			</td>
			    		</tr>
			    		<tr height="50%">
			    			<td colspan="4"></td>
			    			<td colspan="3" style="text-align:left;">
			    			
			    				<button class="btn btn-default" type="button" style="height:34px;" id="btn_upload">
                                    <i class="fa fa-upload"></i>导入数据
                                </button>
                                <a href="${ctx}/publicSignBoardInfo/downloadTemplate">
	                                <button class="btn btn-default" type="button" style="height:34px;" id="btn_download">
	                                    <i class="fa fa-download"></i>下载导入模板
	                                </button>
                                </a>
                                <button class="btn btn-default" type="button" style="height:34px;" id="searchBtn">
                                    <i class="fa fa-search"></i>查询
                                </button>
			    				<!-- <button class="btn btn-default" id="btn_add" type="button">
                                   <i class="fa fa-plus"></i> 新增
                               </button>
                               
                               <button class="btn btn-default" id="btn_del" type="button" data-toggle="tooltip"  data-placement="right" title="删除">
                                   <i class="fa fa-minus"></i> 删除 
                               </button> -->

			    			</td>
			    		</tr>
			    	</table>
			    </div>
			    <div class="data_div_datalist">
			    	<table style="width:100%;font-size:12px;text-align:center;text-align:center" id="apply_table">
						<thead>
							<tr style="height:63px;background:#4AB6E7;">
								<th width="4%"></th>
								<th width="8%">河道名称</th>
								<th width="6%">左右岸</th>	
								<th width="6%">区属</th>
								<th width="8%">河段起讫点</th>
								<th width="6%">涉及河段长度（M）</th>
								<th width="12%">公示牌设置位置</th>
								<th width="6%">公示牌编号</th>
								<th width="6%">区河长</th>
								<th width="6%">镇街河长</th>
								<th width="6%">社区河长</th>
								<th width="6%">责任人</th>
								<th width="9%">更新时间</th>
								<th width="9%">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
			    </div>
			 </div>
    		
    		
    	<div class="modal fade" id="dataUpload" tabindex="-1" role="dialog" style="width:800px;margin:auto;top:222px;"
        	 aria-labelledby="myModalLabel" aria-hidden="true">
        		<div class="modal-dialog" style="width:780px;">
            		<div class="modal-content" style="width:750px;">
                		<div class="modal-header">
                    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                   			</button>
		                    <h4 class="modal-title" id="myModalLabel" style="font-size:14px;">
		                        	上传模板文件
		                    </h4>
               			 </div>
                		<div class="modal-body col-lg-12">
                			<form id="upload_form" action="../response/upload" method="post" style="position:absolute;top:20px;left:130px;" enctype="multipart/form-data">
                				<input type="file" name="file" id="uploadXlsFile" style="width:350px;border-radius:4px;border:1px solid gray;display:inline;"/>
                				<!-- <span style="color:red;">上传成功！</span> -->
                			</form>
                		</div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default"
		                            data-dismiss="modal">关闭
		                    </button>
		                </div>
            		</div>
       			 </div>
        </div>
    		
    		
    		
    		
			<!--Modal-->
    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog" style="width:800px;margin:auto;"
         aria-labelledby="myModalLabel" aria-hidden="false">
        <div class="modal-dialog" style="width:780px;">
            <div class="modal-content" style="width:750px;">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel" style="font-size:14px;">
                        	河道详细信息
                    </h4>
                </div>
                <div class="modal-body col-lg-12">
                    <form id="dictForm" role="form" method="post" action="${ctx }/river/saveRiver">
                    	<input type="hidden" name="respid" id="respid">
                    	<input type="hidden" name="riverid" id="riverid">
                    	<div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">河道标识</label>
                            <input type="text" class=" col-lg-2" name="riverCode" id="riverCode">
                            <label for="app_appname" class=" col-lg-2">所属区域</label>
                            <select id="area1" class=" col-lg-2"></select>
                            <input id="area" type="hidden" name="area" >
                            <label for="app_appname" class=" col-lg-2">河道等级</label>
                            <select id = "grade1" class=" col-lg-2"></select>
                            <input id="grade" name="grade" type="hidden">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">河道名称</label>
                            <input type="text" class=" col-lg-2" name="riverName" id="riverName">
                            <label for="app_appname" class=" col-lg-2">左右岸</label>
                            <input type="text" class=" col-lg-2" name="leftRight" id="leftRight">
                            <label for="app_appname" class=" col-lg-2">河段名称</label>
                            <input type="text" class=" col-lg-2" name="partName" id="partName">
                        </div>
                         <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">河道长度</label>
                            <input type="text" class=" col-lg-2" name="length" id="length">
                            <label for="app_appname" class=" col-lg-2">河道宽度</label>
                            <input type="text" class=" col-lg-2" name="width" id="width">
                            <label for="app_appname" class=" col-lg-2">集雨面积</label>
                            <input type="text" class=" col-lg-2" name="coverArea" id="coverArea">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">起始位置</label>
                            <input type="text" class=" col-lg-2" name="start" id="start">
                            <label for="app_appname" class=" col-lg-2">终止位置</label>
                            <input type="text" class=" col-lg-2" name="end" id="end">
                            <label for="app_appname" class=" col-lg-2">备注</label>
                            <input type="text" class=" col-lg-2" name="remark" id="remark">
                        </div>
                        <div class="form-group col-lg-12" style="text-align:left;">
                        	<font  size="3" color="#000">河长制信息</font>
                        </div>
                        <div class="form-group col-lg-12">
                        	<label for="app_appname" class=" col-lg-2">区级河长</label>
                            <input type="text" class=" col-lg-2" name="distMgrName" id="distMgrName">
                            <label for="app_appname" class=" col-lg-2">单位</label>
                            <input type="text" class=" col-lg-2" name="distMgrOrg" id="distMgrOrg">
                            <label for="app_appname" class=" col-lg-2">联系电话</label>
                            <input type="text" class=" col-lg-2" name="distMgrTel" id="distMgrTel">
<!--                              <label for="app_appname" class=" col-lg-2">职务</label>
                            <input type="text" class=" col-lg-4" name="distMgrPosition" id="distMgrPosition"> -->
                        </div>
                        <div class="form-group col-lg-12">
                        	<label for="app_appname" class=" col-lg-2">镇街河长</label>
                            <input type="text" class=" col-lg-2" name="streetMgrName" id="streetMgrName">
                            <label for="app_appname" class=" col-lg-2">单位</label>
                            <input type="text" class=" col-lg-2" name="streetMgrOrg" id="streetMgrOrg">
                            <label for="app_appname" class=" col-lg-2">联系电话</label>
                            <input type="text" class=" col-lg-2" name="streetMgrTel" id="streetMgrTel">
<!--                              <label for="app_appname" class=" col-lg-2">职务</label>
                            <input type="text" class=" col-lg-4" name="streetMgrPosition" id="streetMgrPosition"> -->
                        </div>
                        <div class="form-group col-lg-12">
                        	<label for="app_appname" class=" col-lg-2">村级河长</label>
                            <input type="text" class=" col-lg-2" name="villageMgrName" id="villageMgrName">
                            <label for="app_appname" class=" col-lg-2">单位</label>
                            <input type="text" class=" col-lg-2" name="villageMgrOrg" id="villageMgrOrg">
                            <label for="app_appname" class=" col-lg-2">联系电话</label>
                            <input type="text" class=" col-lg-2" name="villageMgrTel" id="villageMgrTel">
<!--                              <label for="app_appname" class=" col-lg-2">职务</label>
                            <input type="text" class=" col-lg-4" name="villageMgrPosition" id="villageMgrPosition"> -->
                        </div>
                        <div class="form-group col-lg-12">
                        	<label for="app_appname" class=" col-lg-2">管理员姓名</label>
                            <input type="text" class=" col-lg-2" name="manageMgrName" id="manageMgrName">
                            <label for="app_appname" class=" col-lg-2">单位</label>
                            <input type="text" class=" col-lg-2" name="manageMgrOrg" id="manageMgrOrg">
                            <label for="app_appname" class=" col-lg-2">联系电话</label>
                            <input type="text" class=" col-lg-2" name="manageMgrTel" id="manageMgrTel">
<!--                              <label for="app_appname" class=" col-lg-2">职务</label>
                            <input type="text" class=" col-lg-4" name="manageMgrPosition" id="manageMgrTel"> -->
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
								<td class="td_odd">河道标识</td><td style="width:237px;"><span id="info_riverCode"></span></td>
								<td class="td_odd">所属区域</td><td><span id="info_areaName"></span></td>
							</tr>
							<tr>
								<td class="td_odd">左右岸</td><td><span id="info_leftRight"></span></td>
								<td class="td_odd">公示牌编号</td><td><span id="info_boardCode"></span></td>
							</tr>
							<tr>
								<td class="td_odd">河道名称</td><td><span id="info_riverName"></span></td>
								<td class="td_odd">河段名称</td><td><span id="info_partName"></span></td>
							</tr>
							<tr>
								<td class="td_odd">河段长度</td><td><span id="info_partNameLength"></span></td>
								<td class="td_odd">公示牌位置</td><td><span id="info_boardPosition"></span></td>
							</tr>
							<tr>
								<td class="td_odd">更新时间</td><td colspan="3"><span id="info_createTimeStr"></span></td>
							</tr>
							<tr style="border-bottom:1px solid #b0b0b0;">
								<td class="td_odd">备注</td><td colspan="3"><span id="info_remark"></span></td>
							</tr>
						</table>
					
					</div>
					<div class="form-group col-lg-12" style="text-align: left;margin-top:15px;">
						<font size="3" color="#000">河长制信息</font>
					</div>
					<div style="width:100%;">
						<table style="width:90%;margin:0 auto;">
							<tr>
								<td class="td_odd">区级河长</td><td><span id="info_distMgrName"></span></td>
								<td class="td_odd">单位</td><td><span id="info_distMgrOrg"></span></td>
								<td class="td_odd">联系电话</td><td><span id="info_distMgrTel"></span></td>
							</tr>
							<tr>
								<td class="td_odd">镇街河长</td><td><span id="info_streetMgrName"></span></td>
								<td class="td_odd">单位</td><td><span id="info_streetMgrOrg"></span></td>
								<td class="td_odd">联系电话</td><td><span id="info_streetMgrTel"></span></td>
							</tr>
							<tr>
								<td class="td_odd">村级河长</td><td><span id="info_villageMgrName"></span></td>
								<td class="td_odd">单位</td><td><span id="info_villageMgrOrg"></span></td>
								<td class="td_odd">联系电话</td><td><span id="info_villageMgrTel"></span></td>
							</tr>
							<tr>
								<td class="td_odd">管理员姓名</td><td><span id="info_manageMgrName"></span></td>
								<td class="td_odd">单位</td><td><span id="info_manageMgrOrg"></span></td>
								<td class="td_odd">联系电话</td><td><span id="info_manageMgrTel"></span></td>
							</tr>
						</table>
					
					</div>
					
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
        
        
        
        
        
        
</div>
</body>
</html>
