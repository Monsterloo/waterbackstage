<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>水务微信投诉系统</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
<script type="text/javascript"
	src="../assets/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
<link rel="stylesheet" href="${ctx}/css/thePage.css" />
<link rel="stylesheet" href="${ctx}/css/main.css" />
<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
<link rel="stylesheet"
	href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
<link href="../assets/bootstrap/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
<style>
.yl {
	cursor: pointer;
}
tr{
	text-align:center;
}

.form-control {
	width: 45%;
}

label {
	font-weight: normal;
}

</style>
	<script type="text/javascript">
		$(function(){
			$("#searchBtn").bind("click",function(){
				if($("#areaSearch").val()!=null&&$("#areaSearch").val()!=""){
					$("#hidden_area_condition_form").attr("action","../pollResource/toAreaPollResourceStatistics");
					$("#area_").val($("#areaSearch").val());
					$("#hidden_area_condition_form").submit();
				}else{
					location.href='../pollResource/toPollResourceStatistics';
				}
			});
			
			loadDictByParent("area","areaSearch");
			
			$("#areaSearch").val($("#area_temp_hidden").val());
			
			/* $("#areaSearch").bind("change",function(){
				if($(this).val()!=null&&$(this).val()!=""){
					alert($(this).val());
					location.href='../pollResource/toAreaPollResourceStatistics?area='+escape($(this).val());
				}else{
					location.href='../pollResource/toPollResourceStatistics'
				}
			}); */
			
			$.ajax({
				url:'../pollResource/area_statistics',
				data:{'area_':$("#areaSearch").val()},
				type:'POST',
				dataType:'json',
				success:function(data){
					if(data!="false"){
						for(var k=0;k<data.length;k++){
							for(var i=0;i<data[k].length;i++){
								var str = "<tr>";
								for(var j=0;j<18;j++){
									str += "<td>"+data[k][i][j]+"</td>";
								}
								str += "</tr>"
								$("#pollresource"+(k+1)).after(str);
							}
						}
					}
				},
				error:function(){
				}
				
			});
		});
		
		function loadDictByParent(parent,eleId){
			var data = {"dict":parent};
			$.ajax({
				url:'../pollResource/findDict',
				data:data,
				dataType : "json",
				async:false,
				success:function(data){
					$("#"+eleId).empty().append("<option value=''></option>");
					$.each(data, function(i, value){
						if(parent == "area"){
							$("#"+eleId).append("<option>"+value.name+"</option>");
						}else{
							$("#"+eleId).append("<option value='"+value.code+"'>"+value.name+"</option>");
						}
						
					});
				},
				error:function(data){
					alert(data);
				}
				
			});
		}
		
	</script>

</head>
<body>

	<div id="wrapper">
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<aside class="sidebar">
			<nav class="sidebar-nav" id="left_menu_bar" style="text-align: left;">
			</nav>
		</aside>
		
		<div style="display:none;">
			<form id="hidden_area_condition_form" method="post">
				<input type="hidden" name="area_" id="area_"/>
			</form>
		</div>
		
		<div class="common_content" style="width:84%;">
			<div class="data_div_header">
				<span>污染源统计</span>
			</div>
			
			
			<div class="data_div_condition">
				<table class="condition_table">
					<tr height="50%">
						<td width="6%" class="td-text-align-right">行政区域:</td>
						<td width="20%" class="td-text-align-left">
						<input type="hidden" value="${area_remark}" id="area_temp_hidden">
						<select	tabindex="-1" name="areaSearch" id="areaSearch"	class="form-control" style="display:inline;">
						</select>
						<button class="btn btn-default" type="button"
							style="height: 34px;" id="searchBtn">
							<i class="fa fa-search"></i>查询
						</button>
						</td>
					</tr>
				</table>
			</div>
			
			<div style="width:100%;border:1px solid gray;float:left;">
				<table border="1" style="width:100%;" id="pollResourceTable">
					<tr style="text-align: center;background:#ABCDEF;height: 50px;font-size: 34px;font-weight: bold;">
						<td colspan="18"><h1>${area_remark}河涌水污染源摸查情况汇总表</h1></td>
					</tr>
					<tr>
						<td rowspan="3">序号</td>
						<td rowspan="3">河涌名称</td>
						<td rowspan="3">污染源合计（个）</td>
						<td colspan="5">工业污染源</td>
						<td colspan="5">农业和畜禽殖业污染源（个)</td>
						<td colspan="5">河涌排水口（个）</td>
					</tr>
					<tr>
						<td rowspan="2">小计</td>
						<td rowspan="2">区环保局负责整改</td>
						<td rowspan="2">区城管局负责整改</td>
						<td rowspan="2">区农业局负责整改</td>
						<td rowspan="2">其他单位负责整改</td>
						
						<td rowspan="2">小计</td>
						<td rowspan="2">区环保局负责整改</td>
						<td rowspan="2">区城管局负责整改</td>
						<td rowspan="2">区农业局负责整改</td>
						<td rowspan="2">其他单位负责整改</td>
						<td rowspan="2">排水口</td>
						<td colspan="4">排污口及整改责任单位</td>
					</tr>
					<tr>
						<td>小计</td>
						<td>区水务局负责整改</td>
						<td>市净水公司负责整改</td>
						<td>其他单位负责整改</td>
					</tr>
					<tr style="text-align:left;" id="pollresource1">
						<td colspan="18">一、35条黑臭河涌</td>
					</tr>
					
					<tr style="text-align:left;" id="pollresource2">
						<td colspan="18">二、152条黑臭河涌</td>
					</tr>
					<tr style="text-align:left;" id="pollresource3">
						<td colspan="18">三、其他51条河涌</td>
					</tr>
				</table>
			
			</div>
			
		</div>
	</div>
</body>
</html>
		
		
		