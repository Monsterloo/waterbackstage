<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
<script type="text/javascript"
	src="../assets/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
<link rel="stylesheet" href="${ctx}/css/thePage.css" />
<link rel="stylesheet" href="${ctx}/css/main.css" />
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
<link href="../assets/bootstrap/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
<title><c:out value="${area }" default="广州市" />河涌河长公示牌数量统计表</title>
<style type="text/css">
.showtable {
	width: 100%;
	text-align: center;
}

.title {
	font-size: 30px;
	text-align: center;
}

.showtable tr td {
	text-align: center;
}

.showtable input {
	width: 50px;
}
</style>
<script language="JavaScript">
	$(document).ready(function(){
		var area = $("#area").val();
		$.ajax({
			type:"POST",
			url:"../publicSignBoardInfo/getStatData",
			async:true,
			data:{area:area},
			datatype : 'json',
			success : function(json){
				console.info(json);
				var h = "";
				$.each(json,function(key,list){
					h+="<tr><td>"+key+"</td>";
					var boardTotal = 0;
					var part = "";
					$.each(list,function(i,data){
						if(i == list.length-1){
							part += "<td>"+data+"</td>";
							return false;
						}
						if(i%2==1){
							boardTotal += toNum(data);
						}
						part += "<td>"+data+"</td>";
					});
					h += "<td>"+boardTotal+"</td>";
					h += part;
					$("#showtable").append(h);
					h= "";
				});
				
				if(area == null || area == ""){
					var trs = $("#showtable tr:gt(2)");
					var params = new Array(10);
					for(i=0;i<10;i++){
						params[i] = 0;
					}
					$.each(trs,function(i,tr){
						var tdarr = $(tr).children().slice(1);
						$.each(tdarr,function(i,td){
							params[i] = params[i] + parseInt($(td).html());
						});
					});
					
					var h ="<tr><td>总计</td>";
					for(i=0;i<params.length;++i){
						console.info(params[i]+"...");
						h += "<td>"+params[i]+"</td>";
					}
					h += "</tr>"
					$("#showtable").append(h);
				}
			}
		});
	});
	
	function toNum(str) {
		return str == "" || str == undefined ? 0 : parseInt(str);
	}
</script>
<body style="text-align: center;">

	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<div class="clearfix">
			<aside class="sidebar"> <nav class="sidebar-nav"
				id="left_menu_bar"> </nav> </aside>
			<section class="content">
			<div id="showspace">
				<table id="showtable" class="showtable" cellspacing="0" border="1">
					<input type="hidden" id="area" value="${area }" />
					<th class="title" colspan="15"><c:out value="${area }"
							default="广州市" />河涌河长公示牌数量统计表</th>
					<tr>
						<td rowspan="2">名称</td>
						<td rowspan="2">全部河涌河长公示牌数量</td>
						<td colspan="2">其中35条黑臭河涌</td>
						<td colspan="2">其中152条黑臭河涌</td>
						<td colspan="2">其中187条黑臭河涌</td>
						<td colspan="2">其他河涌</td>
						<td>未设置河长河涌数量</td>
					</tr>
					<tr>
						<td>河涌数量</td>
						<td>公示牌个数</td>
						<td>河涌数量</td>
						<td>公示牌个数</td>
						<td>河涌数量</td>
						<td>公示牌个数</td>
						<td>河涌数量</td>
						<td>公示牌个数</td>
						<td>河涌数量</td>
					</tr>
				</table>
			</div>
			</section>
		</div>
	</div>
</body>
</html>