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
<title>广州市河涌水污染源摸查情况统计表</title>
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
	width: 50px;
}

.showtable input {
	width: 50px;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		/* var trs = $(".showtable tr:last");
		console.info($(trs)); */
		showRiverPollutionSurvey();
	});
	
	function showRiverPollutionSurvey(){
		$.ajax({
			url : '../riverPollutionSurvey/getRiverPollutionSurvey',
			type : 'GET',
			dataType : 'text',
			success : function(json){
				if(json.indexOf("RequestSuccessful") >= 0){
					json = json.replace("RequestSuccessful","");
					if(json == "Null" || json == ""){
						alert("没有数据!");
						return;
					}
					json = eval('('+json+')');
					$.each(json.riverpollutionSurveyList , function(i,item){
						var h = "<tr><input type=\"hidden\" id=\"id\" value="+item.id+" />"
						var lastTr = $(".showtable tr:last");
						if(i%4==0){
							/* h+="<td rowspan=\"4\"><input type=\"text\" class=\"change\" style=\"width:65px\" id=\"subject\" value=" + item.subject + " /></td>" */
							h+="<td rowspan=\"4\">"+item.subject+"</td>";
							h+="<td class=\"totaltds\" style=\"text-align: left;\">全部河涌</td>";
							h+="<td></td><td></td><td></td></tr>";
							$(lastTr).after(h);
							return true;
						}
						h+="<td class=" +item.allRiverNumber+ "><input type=\"text\" class=\"change\" style=\"width:auto\" id=\"allRiverNumber\" value=" + item.allRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"riverNumber\" value=" + item.riverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"pollutionSourceNumber\" value=" + item.pollutionSourceNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneSubtotal\" value=" + item.oneSubtotal + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneEnvironment\" value=" + item.oneEnvironment + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneCityManagement\" value=" + item.oneCityManagement + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneFarming\" value=" + item.oneFarming + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneOthers\" value=" + item.oneOthers + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoSubtotal\" value=" + item.twoSubtotal + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoEnvironment\" value=" + item.twoEnvironment + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoCityManagement\" value=" + item.twoCityManagement + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoFarming\" value=" + item.twoFarming + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoOthers\" value=" + item.twoOthers + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"outfallNumber\" value=" + item.outfallNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"outfallSubtotal\" value=" + item.outfallSubtotal + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterSupplies\" value=" + item.outfallWaterSupplies + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterPurification\" value=" + item.outfallWaterPurification + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"outfallOthers\" value=" + item.outfallOthers + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"remarks\" value=" + item.remarks + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"principal\" value=" + item.principal + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"contacts\" value=" + item.contacts + " /></td></tr>";
						$(lastTr).after(h);
					});
					
					
					<!--计算三行的和 -->
					var totals = $(".totaltds");
					$.each(totals,function(i,obj){
						var trs = $(obj).parent().nextAll().slice(0,3);
						var params = new Array(17);
						for(i=0;i<17;i++){
							params[i]=0;
						}
						<!--剔除id-->
						$.each(trs,function(i,obj){
							param = $(obj).children().slice(2,19).find("input");
							cal(params,param);
						}); 
						
						var h = addAfter(params);
						
						$(obj).after(h);
					});
					
					
					<!--计算全部总合-->
					var totals = calRowsTotal($(".totaltds").parent(),"total");
					var oneTotals = calRowsTotal($(".35条黑臭河涌").parent());
					var twoTotals = calRowsTotal($(".152条黑臭河涌").parent()); 
					var threeTotals = calRowsTotal($(".其他51条河涌").parent()); 
					
					$(".totals").after(addAfter(totals));
					$(".oneTotals").after(addAfter(oneTotals));
					$(".twoTotals").after(addAfter(twoTotals));
					$(".threeTotals").after(addAfter(threeTotals));
					
					$(".change").attr('disabled',true);
				}
			}
		});
		
		function calRowsTotal(rowsTotal,index){
			var params = new Array(17);
			for(i=0;i<17;i++){
				params[i]=0;
			}
			$.each(rowsTotal,function(i,obj){					
				if(index == "total"){
					var tds = $(obj).find("input").slice(1,18);
				}else{
					var tds = $(obj).find("input").slice(2,19);
				}
				console.info(tds);
				cal(params,tds);
			});
			return params;		
		}
		
		function cal(params,param){
			$.each(param,function(i,obj){
				params[i] = params[i]+parseInt($(obj).val());
			});
		}
		
		function addAfter(params){
			var h = "<td><input type=\"text\" class=\"change\" id=\"riverNumber\" value=" + params[0] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"pollutionSourceNumber\" value=" + params[1] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneSubtotal\" value=" + params[2] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneEnvironment\" value=" + params[3] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneCityManagement\" value=" + params[4] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneFarming\" value=" + params[5] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneOthers\" value=" + params[6] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoSubtotal\" value=" + params[7] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoEnvironment\" value=" + params[8] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoCityManagement\" value=" + params[9] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoFarming\" value=" + params[10] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoOthers\" value=" + params[11] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallNumber\" value=" + params[12] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallSubtotal\" value=" + params[13] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterSupplies\" value=" + params[14] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterPurification\" value=" + params[15] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallOthers\" value=" + params[16] + " /></td>";
			
			return h;
		}
		
	}
</script>

</head>
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
			<aside class="sidebar" style="width:200px;"> <nav
				class="sidebar-nav" id="left_menu_bar"> </nav> </aside>
			<section class="content">
			<div id="showspace">
				<table id="showtable" class="showtable" border="2" cellspacing="0">
					<th class="title" colspan="23">广州市河涌水污染源摸查情况统计表</th>
					<tr>
						<td rowspan="3">责任主体</td>
						<td rowspan="3">全部河涌数量（条）</td>
						<td rowspan="3">河涌数量（条/段）</td>
						<td rowspan="3">污染源合计（个）</td>
						<td colspan="5">工业和沿涌餐饮业污染源（个）</td>
						<td colspan="5">农业和畜禽养殖业污染源（个）</td>
						<td colspan="5">河涌排水口（个）</td>
						<td rowspan="3">备注</td>
						<td rowspan="3">负责人</td>
						<td rowspan="3">联系人</td>
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
					<tr>
						<td rowspan="4">全市合计</td>
						<td class="totals">全部河涌</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="oneTotals">一、35条黑臭河涌</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="twoTotals">二、152条黑臭河涌</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="threeTotals">三、其他51条河涌</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			</section>
		</div>
	</div>
</body>
</html>