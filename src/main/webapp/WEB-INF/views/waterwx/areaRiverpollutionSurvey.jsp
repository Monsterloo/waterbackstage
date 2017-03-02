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
<title><%=request.getSession().getAttribute("area") %>河涌水污染源摸查情况汇总表</title>
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
	min-width: 50px;
}
.showtable input{
	width:50px;
}
</style>

<script language="JavaScript">
		
		$(document).ready(function(){
			var area=$("#area").val();
			$.ajax({
				type:"POST",
				url:"../areaRiverpollutionSurvey/getAreaRiverPollutionSurvey",
				async:true,
				data : {area:area},
				dataType : 'text',
				success : function(json){
					//console.info(data.areaRiverpollutionSurveyList);
					if(json.indexOf("RequestSuccessful") >= 0){
						json = json.replace("RequestSuccessful","");
						if(json == "Null" || json == ""){
							alert("没有数据!");
							return;
						}
						json = eval('('+json+')');
						var oneIndex = 1;
						var twoIndex = 1;
						var threeIndex = 1;
						$.each(json.areaRiverpollutionSurveyList, function(i,item){
							var index = item.riverIndex;					
							switch(index){
							case "1" :
								var h = loading(item,oneIndex++,"one");
								$(".twoRiverIndex").before(h);
								return;
							case "2" :
								var h = loading(item,twoIndex++,"two");
								$(".threeRiverIndex").before(h);
								return;
							case "3" :
								var h = loading(item,threeIndex++,"three");
								$(".notice").before(h);
								return;
							}
						});
						calTotal("one");
						calTotal("two");
						calTotal("three");
						
						$(".change").attr("disabled",true);
					}
				}
			});
		});
		
		function calTotal(className){
			var total = $("."+className);
			var params = new Array(16);
			for(i=0;i<16;i++){
				params[i]=0;
			}
			$.each(total,function(i,obj){
				var tds = $(obj).children().slice(3,-1);
				$.each(tds,function(i,obj){	
					var param = $(obj).find("input").val();
					params[i] = params[i]+parseInt(param);
				}); 
			});
			var h="<td><input type=\"text\" class=\"change\" id=\"pollutionSourceNumber\" value=" + params[0] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneSubtotal\" value=" + params[1] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneEnvironment\" value=" + params[2] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneCityManagement\" value=" + params[3] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneFarming\" value=" + params[4] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"oneOthers\" value=" + params[5] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoSubtotal\" value=" + params[6] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoEnvironment\" value=" + params[7] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoCityManagement\" value=" + params[8] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoFarming\" value=" + params[9] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"twoOthers\" value=" + params[10] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallNumber\" value=" + params[11] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallSubtotal\" value=" + params[12] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterSupplies\" value=" + params[13] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallWaterPurification\" value=" + params[14] + " /></td>";
			h+="<td><input type=\"text\" class=\"change\" id=\"outfallOthers\" value=" + params[15] + " /></td>";
			//h+="<td><input type=\"text\" class=\"change\" id=\"remarks\" value=" " /></td>";
			$("."+className+"Total").after(h);
		}
		
		function loading(item , num , className){
			var h = "<tr class="+className+"><input type=\"hidden\" id=\"id\" value=" + item.id + " />";
			h+="<td><input type=\"text\" class=\"change\"  value=" + num + " /></td>"
			h+="<td><input type=\"text\" class=\"change\" style=\"width:auto\" id=\"riverName\" value=" + item.riverName + " /></td>"
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
			h+="<td><input type=\"text\" class=\"change\" id=\"remarks\" value=" + item.remarks + " /></td></tr>";
		
			return h;
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
			<aside class="sidebar"> <nav
				class="sidebar-nav" id="left_menu_bar"> </nav> </aside>
			<section class="content">
			<div id="showspace">
	<table id="showtable" class="showtable" border="2" cellspacing="0">
		<input type="hidden" id="area" value="${area}" />
		<th class="title" colspan="19"><%=request.getSession().getAttribute("area") %>河涌水污染源摸查情况汇总表</th>
		<tr>
			<td colspan="5">填报单位：广州市<%=request.getSession().getAttribute("area") %>建设和水务局</td>
			<td colspan="10"></td>
			<td colspan="4" style="text-align: right;">时间：2016年10月20日</td>
		</tr>
		<tr>
			<td rowspan="3">序号</td>
			<td rowspan="3" style="min-width: 150px;">河涌名称</td>
			<td rowspan="3">污染源合计（个）</td>
			<td colspan="5">工业和沿涌餐饮业污染源（个）</td>
			<td colspan="5">农业和畜禽养殖业污染源（个）</td>
			<td colspan="5">河涌排水口（个）</td>
			<td rowspan="3">备注</td>
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
			<input type="hidden" name="id" value="this a id" />
			<td rowspan="3">合计</td>
			<td class="oneTotal">一、35条黑臭河涌</td>
		</tr>
		<tr>
			<td class="twoTotal">二、152条黑臭河涌</td>
		</tr>
		<tr>
			<td class="threeTotal">三、其他51条河涌</td>
		</tr>
		<tr class="oneRiverIndex">
			<td colspan="19" style="text-align: left;">一、35条黑臭河涌</td>
		</tr>
		<tr class="twoRiverIndex">
			<td colspan="19" style="text-align: left;">二、152条黑臭河涌</td>
		</tr>
		<tr class="threeRiverIndex">
			<td colspan="19" style="text-align: left;">三、其他51条河涌</td>
		</tr>
		<tr class="notice">
			<td></td>
			<td colspan="18" style="text-align: left;"><p>
					"注：1.河涌排水口中仅排污口纳入污染源小计。<br />
					2.其他51条河涌是指不属于黑臭河涌、已纳入广佛跨界整治和水更清行动计划51条河涌。<br />
					3.表中黑臭河涌名称以天河区为例，各区应按《广州市黑臭河涌整治工作任务书》所附表格中的河涌名称及顺序填写。<br />
					4.本表分两次报送:9月18日前，报送35条黑臭河涌和其他广佛跨界16条河涌水污染源摸查基本成果和汇总表（含电子文件）。10月31日前，报送包括我市35条黑臭河涌、152条黑臭河涌、其他纳入广佛跨界河涌整治和水更清行动计划51条河涌在内的全部水污染源摸查基本成果和汇总表（含电子文件）。"
				</p></td>
		</tr>
		<tr>
			<td colspan="2">负责人：刘毅</td>
			<td colspan="3">填表人：鲍天生</td>
			<td colspan="4">联系电话：37668897</td>
			<td colspan="10"></td>
		</tr>
	</table>
</body>
</html>