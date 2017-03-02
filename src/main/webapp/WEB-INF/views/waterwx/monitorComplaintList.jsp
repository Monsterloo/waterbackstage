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
	<link rel="stylesheet" href="${ctx}/css/main.css" />
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.yl:hover{color:#0000ff;}
		th{
			text-align:center;
		}
	</style>
	 <script type="text/javascript" src="${ctx }/js/water/monitorComplaint.js"></script> 
</head>
<body>

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
		    	<span>投诉处理监控信息</span>
		    </div>
		    
	    	<div class="data_div_condition">
		    	<table class="condition_table">
		    		<tr height="50%">
		    			<td width="6%" class="td-text-align-right">河道名称:</td>
		    			<td width="24%" class="td-text-align-left">
		    				<input type="text"  id="riverSearch" class="form-control" placeholder="Search..." style="border-radius:4px">
		    			</td>
		    			<td width="6%" class="td-text-align-right">受理编号:</td>
		    			<td width="29%" class="td-text-align-left">
		    				<input type="text" style="height:30px;color:#000;" id="search_code" class="form-control" placeholder="Search..." style="border-radius:4px">
		    			</td>
		    			
		    			<td width="7%" class="td-text-align-right">行政区域:</td>
		    			<td width="26%" class="td-text-align-left">
		    				<select tabindex="-1" name="areaSearch" id="areaSearch" style="width:315px;height:30px;padding-top:5px;border-radius:4px;"  class="selectpicker" >
							</select>
							<input type="hidden" id="search_area" class="form-control" placeholder="Search...">
		    			</td>
		    		</tr>
		    		
		    		<tr height="50%">
		    			<td class="td-text-align-right" colspan="6">
                            <button class="btn btn-default" id="btn_search" style="padding-left:12px;" type="button">
                                <i class="fa fa-plus"></i> 查询
                            </button>
		    			</td>
		    		</tr>
		    	</table>
		    </div>
                              <!-- 
				 <div class="col-lg-2" style=" padding-top: 10px; font-size: 14px; ">
					<span>问题类型:</span>
				</div> 
				<div class="col-lg-1 qymc "  >
					
					
                                      <div class="input-group" >
					<select id="questiontype" 	name="questiontype" style="width:150px;height:30px; "  class="col-lg-4"> 
                          	<option>请选择</option> 
                          </select>
					<select tabindex="-1" name="gradeSearch" id="gradeSearch" style="width:120px;height:30px; "  class="selectpicker" >
					</select>
                                   <span class="input-group-btn">
                                           <button class="btn btn-default" type="button" style="height:30px;" id="searchBtn">
                                               <i class="fa fa-search"></i>
                                           </button>
                                       </span>
                                   </div>
				</div> -->
		    
		    
		    
		    
		    
		    
		    
		    
		    <div class="data_div_datalist">
			    <table style="width:100%;font-size:12px;text-align:center;" id="apply_table">
					<thead>
						<tr style="height:63px;background:#4AB6E7;">
							<th style="width:75px;">状态</th>
							<th style="width:89px;">投诉编号</th>
							<th style="width:80px;">投诉人</th>
							<th style="width:89px;">问题类型</th>
							<th style="width:60px;">行政区域</th>
							<th style="width:89px;">投诉日期 </th>	
							<th style="width:89px;">问题描述</th>
							<th style="width:89px;">问题位置</th>
							<th style="width:60px;">市分派人</th>
							<th style="width:89px;">市分派情况</th>
							<th style="width:60px;">区处理人</th>
							<th style="width:89px;">区处理情况</th>
							<th style="width:60px;">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		    </div>
		</div>    		
    		
			<!--Modal-->
			<div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true" >
        <div class="modal-dialog" style="margin: 75px 300px;">
            <div class="modal-content" style="width:900px;">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        	投诉处理详细信息
                    </h4>
                </div>
                <div class="modal-body" style="width:890px;height:490px;overflow-y:scroll">
                    	<input type="hidden" id="id1"/>
                        <div class="form-group col-lg-12">
                            <label for="questiontype" class="control-label  col-lg-2">投诉编号</label>
                             <input type="text" class=" col-lg-4" id="code1" name="code">
                            <label for="questiontype" class="control-label  col-lg-2">投诉人</label>
                             <input type="text" class=" col-lg-4" id="nickname" name="nickname">
                        </div>
                        <div class="form-group col-lg-12">
                             <label for="questiontype" class="col-lg-2">问题类型</label>
                            <select id="selecttype11" 	name="selecttype11" class="col-lg-4"> 
                            	<option>请选择</option> 
                            </select>
                             <input type="hidden" class=" col-lg-6" name="questiontype" id="questiontype1">
                            <label for="questiontype" class="control-label  col-lg-2">行政区域</label>
                             <select id="area1" name="area1" class=" col-lg-4" >
                           		<option>请选择</option>
							</select>
                            <input type="hidden" class=" col-lg-4" id="area2" name="area2">
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
                            <label for="questioncontent" class="control-label  col-lg-2">备注</label>
                            <textarea id="remark" name="remark" style="width:80%;height:30px;"></textarea>
                        </div>
                       <!-- 显示照片和地图 -->
                       	<div class="form-group col-lg-12" id="imgAttch">
                            
                        </div>
                        <!-- 市分派情况 -->
                       	<div class="form-group col-lg-12" id="cityAttch">
                            
                        </div>
                        <!--区受理情况 -->
                       	<div class="form-group col-lg-12" id="areaAttch">
                            
                        </div>
                        
                        <div id="allmap" class="col-lg-12" style="width:98%;margin:0 auto;margin-top:20px;border: 1px solid #ccc;height:200px;"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
             
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
			</div>
</div>
</body>
</html>
