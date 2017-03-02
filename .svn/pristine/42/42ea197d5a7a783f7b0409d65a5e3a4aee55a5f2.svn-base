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
				<div class="clearfix">
    		<aside class="sidebar" style="width:200px;">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div id="showspace">
							<div class="col-lg-12">
								<div class="panel panel-info ">
									<div class="panel-heading">用户信息</div>
										<div class="panel-body">
											<div class="row">
                                    <div class="col-sm-3" style="margin-left: 0px; margin-bottom: 5px">
                                        
                                    </div>
                                    <div class="col-sm-9 text-right" style="margin-bottom: 5px">
                                        <div class="btn-group">
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
                                              <button class="btn btn-default" id="btn_export" type="button" data-toggle="tooltip"
                                                    data-placement="right" title="导出">
                                                <i class="fa fa-minus"></i> 导出
                                            </button>
                                        </div>
                                </div>
										</div>
								<!--search -->
								<!-- data table -->
								<table class="table table-hover table-striped table-bordered" id="apply_table">
									<thead>
										<tr class="123">
											<th>全选<input type='checkbox' id="checkAll" name='checkAll' value=""></th>
											<th>登录账户</th>
											<th>名称</th>
											<th>邮箱</th>
											<th>电话</th>
											<th>终止位置</th>
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
    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
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
                            <input type="text" class=" col-lg-4" name="riverCode" id="riverCode">
                            <label for="app_appname" class=" col-lg-2">河道名称</label>
                            <input type="text" class=" col-lg-4" name="riverName" id="riverName">
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="app_appname" class=" col-lg-2">行政区域</label>
                            <input type="text" class=" col-lg-4" name="area" id="area">
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
                            <label for="app_description" class="col-lg-2">备注</label>
                            <input type="text" class="col-lg-4" name="remark" id="remark">
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
    	</section>
    	</div>
</div>
</body>
</html>
