<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><%=request.getSession().getAttribute("area") %>河涌河长公示牌数量统计表</title>
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
	width:70px;
}

.showtable input {
	width: 60px;
}
</style>
<script language="JavaScript">
	$(document).ready(function(){
		var area=$("#area").val();
		$.ajax({
			type:"POST",
			url:"../publicSignBoardInfo/getBoardInfoList",
			async:true,
			data : {area:area},
			dataType : 'text',
			success : function(json){
				if(json.indexOf("RequestSuccessful") >= 0){
					json = json.replace("RequestSuccessful","");
					if(json == "Null" || json == ""){
						alert("没有数据!");
						return;
					}
					json = eval('('+json+')');
					$.each(json,function(i,item){
						var h = "<tr class=\"tds\"><input type=\"hidden\" id=\"id\" value=" + item.id + " />"
						h+="<td><input type=\"text\" class=\"change\" value=" + i + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"riverName\" value=" + item.riverName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"leftOrRightBank\" value=" + item.leftOrRightBank + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"partName\" style='width: 360px;' value=" + item.partName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"partNameLength\" value=" + item.partNameLength + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"boardPosition\" style='width: 230px;' value=" + item.boardPosition + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"distMgrName\" value=" + item.distMgrName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"distMgrOrg\" style='width: 130px;' value=" + item.distMgrOrg + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"distMgrPosition\" style='width: 200px;' value=" + item.distMgrPosition + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"streetMgrName\" value=" + item.streetMgrName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"streetMgrOrg\" value=" + item.streetMgrOrg + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"streetMgrPosition\" style='width: 90px;' value=" + item.streetMgrPosition + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"streetMgrTel\" style='width: 110px;' value=" + item.streetMgrTel + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"villageMgrName\" value=" + item.villageMgrName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"villageMgrOrg\" style='width: 160px;' value=" + item.villageMgrOrg + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"villageMgrPosition\" style='width: 110px;' value=" + item.villageMgrPosition + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"villageMgrTel\" style='width: 110px;' value=" + item.villageMgrTel + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"manageMgrName\" value=" + item.manageMgrName + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"manageMgrOrg\" style='width: 180px;' value=" + item.manageMgrOrg + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"manageMgrPosition\" value=" + item.manageMgrPosition + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"manageMgrTel\" style='width: 110px;'  value=" + item.manageMgrTel + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"boardCode\" style='width: 100px;' value=" + item.boardCode + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"remark\" value=" + item.remark + " /></td></tr>"
						$('#showtable').append(h);
					});
					$(".change").attr('disabled', true);
				}
				
			}
		});
	});
</script>
</head>
<body style="text-align: center;overflow:auto;">

	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<div class="clearfix">
			<aside class="sidebar">
				<nav class="sidebar-nav" id="left_menu_bar"></nav>
			</aside>
			<section class="content">
				<div id="showspace">
					<table id="showtable" class="showtable" border="2" cellspacing="0">
					<input type="hidden" id="area" value="${area}" />
						<th class="title" colspan="23"><%=request.getSession().getAttribute("area") %>“河长制”信息表</th>
						<tr>
							<td rowspan="3">序号</td>
							<td rowspan="3">河道(涌)名称</td>
							<td colspan="3">涉及河段</td>
							<td rowspan="3">公示牌位置</td>
							<td colspan="11">各级河长</td>
							<td colspan="4">河道管理责任单位</td>
							<td rowspan="3">公示牌编号</td>
							<td rowspan="3">备注</td>
						</tr>
						<tr>
							<td rowspan="2">岸别</td>
							<td rowspan="2" style="width: 200px;">河段起讫点</td>
							<td rowspan="2">长度(m)</td>
							<td colspan="3">区级</td>
							<td colspan="4">镇(街)级</td>
							<td colspan="4">社区级</td>
							<td rowspan="2">姓名</td>
							<td rowspan="2">单位</td>
							<td rowspan="2">职务</td>
							<td rowspan="2">联系电话</td>
						</tr>
						<tr>
							<td>河长姓名</td>
							<td>单位</td>
							<td>职务</td>
							<td>河长姓名</td>
							<td>单位</td>
							<td>职务</td>
							<td>联系电话</td>
							<td>河长姓名</td>
							<td>单位</td>
							<td>职务</td>
							<td>联系电话</td>
						</tr>
						<!-- <tr>
							<input type="hidden" name="id" value="this a id" />
							<td rowspan="6">1</td>
							<td rowspan="6">沙河涌</td>
							<td>左岸</td>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>右岸</td>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>1</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr> -->

					</table>
				</div>
			</section>
		</div>
	</div>
</body>
</html>
