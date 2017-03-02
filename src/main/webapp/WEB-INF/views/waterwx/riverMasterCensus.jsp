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
<title>广州市河涌河长数量统计表</title>
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
	width: 80px;
}
</style>
<script type="text/javascript">
	var allRiverArea = 0;
	var allRiverStreet = 0;
	var allRiverVillage = 0;
	var oneRiverArea = 0;
	var oneRiverStreet = 0;
	var oneRiverVillage = 0;
	var twoRiverArea = 0;
	var twoRiverStreet = 0;
	var twoRiverVillage = 0;
	var threeRiverArea = 0;
	var threeRiverStreet = 0;
	var threeRiverVillage = 0;
	var isSetRiverNumber = 0;
	var notSetRiverNumber = 0;
			$(document).ready(function() {
				var area=$("#area").val();
				if(area!=""){$("#save").show();}
				showRiverMasterCensus(area);
			});
			
			function showRiverMasterCensus(area){
				$.ajax({
					url:'../riverMasterCensus/getRiverMasterCensus',
			        type: 'GET',
			        dataType: 'text',
			        data: { 'area': area },
			        success : function(json){
			        	if(json.indexOf("RequestSuccessful") >= 0){
			        		json = json.replace("RequestSuccessful","");
			        		if(json == "" || json == "Null"){
			        			$(".public-list").show();
		                    	$("#save").hide();
		                    	$("#Newsave").show();
		                    	var inputarr = $(".public-list input");
		                		$(inputarr[1]).attr('disabled', true);
		                		$(inputarr[2]).attr('disabled', true);
		                		$(inputarr[3]).attr('disabled', true);
		                		$(inputarr[4]).attr('disabled', true);
		                		calAllBoard(inputarr);
		                		$.each(inputarr, function(i, obj) {
		                			$(obj).blur(function() {
		                				calAllBoard(inputarr);
		                			});
		                		});
		                		return;
			        		}
			        		json = eval('('+json+')');
			        		$.each(json.riverMastercensusList,function(i,item){
			        			allRiverArea += item.allRiverArea;
			        			allRiverStreet += item.allRiverStreet;
			        			allRiverVillage += item.allRiverVillage;
			        			oneRiverArea += item.oneRiverArea;
			        			oneRiverStreet += item.oneRiverStreet;
			        			oneRiverVillage += item.oneRiverVillage;
			        			twoRiverArea += item.twoRiverArea;
			        			twoRiverStreet += item.twoRiverStreet;
			        			twoRiverVillage += item.twoRiverVillage;
			        			threeRiverArea += item.threeRiverArea;
			        			threeRiverStreet += item.threeRiverStreet;
			        			threeRiverVillage += item.threeRiverVillage;
			        			isSetRiverNumber += item.isSetRiverNumber;
			        			notSetRiverNumber += item.notSetRiverNumber;
			        			var h = "<tr class=\"tds\"><input type=\"hidden\" id=\"id\" value=" + item.id + " />"
								h+="<td><input type=\"text\" class=\"change\" id=\"area\" value=" + item.area + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"allRiverArea\" value=" + item.allRiverArea + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"allRiverStreet\" value=" + item.allRiverStreet + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"allRiverVillage\" value=" + item.allRiverVillage + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"oneRiverArea\" value=" + item.oneRiverArea + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"oneRiverStreet\" value=" + item.oneRiverStreet + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"oneRiverVillage\" value=" + item.oneRiverVillage + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"twoRiverArea\" value=" + item.twoRiverArea + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"twoRiverStreet\" value=" + item.twoRiverStreet + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"twoRiverVillage\" value=" + item.twoRiverVillage + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"threeRiverArea\" value=" + item.threeRiverArea + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"threeRiverStreet\" value=" + item.threeRiverStreet + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"threeRiverVillage\" value=" + item.threeRiverVillage + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"isSetRiverNumber\" value=" + item.isSetRiverNumber + " /></td>"
								h+="<td><input type=\"text\" class=\"change\" id=\"notSetRiverNumber\" value=" + item.notSetRiverNumber + " /></td></tr>";
								$('.public-list').before(h);
			        		});
			        		var inputarr = $(".tds input");
			    			if(area != ""){
			    				$(inputarr[1]).attr('disabled', true);
								$(inputarr[2]).attr('disabled', true);
								$(inputarr[3]).attr('disabled', true);
								$(inputarr[4]).attr('disabled', true);
								$.each(inputarr , function(i,obj){
									$(obj).blur(function(){
										calAllBoard(inputarr);
									});
								});
			    			}else{
			    				var h = "<tr class=\"tds\"><td><input type=\"text\" class=\"change\" id=\"area\" value=\"总计\" /></td>"
								h+="<td><input class=\"change\" value=" + allRiverArea + " /></td>"
								h+="<td><input class=\"change\" value=" + allRiverStreet + " /></td>"
								h+="<td><input class=\"change\" value=" + allRiverVillage + " /></td>"
								h+="<td><input class=\"change\" value=" + oneRiverArea + " /></td>"
								h+="<td><input class=\"change\" value=" + oneRiverStreet + " /></td>"
								h+="<td><input class=\"change\" value=" + oneRiverVillage + " /></td>"
								h+="<td><input class=\"change\" value=" + twoRiverArea + " /></td>"
								h+="<td><input class=\"change\" value=" + twoRiverStreet + " /></td>"
								h+="<td><input class=\"change\" value=" + twoRiverVillage + " /></td>"
								h+="<td><input class=\"change\" value=" + threeRiverArea + " /></td>"
								h+="<td><input class=\"change\" value=" + threeRiverStreet + " /></td>"
								h+="<td><input class=\"change\" value=" + threeRiverVillage + " /></td>"
								h+="<td><input class=\"change\" value=" + isSetRiverNumber + " /></td>"
								h+="<td><input class=\"change\" value=" + notSetRiverNumber + " /></td></tr>"
								$('.public-list').before(h);
								$(".change").attr('disabled', true);
			    			}
			        	}
			        }
				});
			}
			
			function save(){
				if(!confirm('确认提交数据?')){return;}
				var area=$("#area").val();
				var inputarr = $(".tds input");
				var datas = new Array();
				$.each(inputarr, function(i, obj) {
					datas.push($(obj).val());
				}); 
				$.ajax({
					type : "post",
					url : "../riverMasterCensus/save",
					async : true,
					data : {
						/* id : $("#id"),
						area : $("#area"),
						allRiverArea : $("#allRiverArea"),
						allRiverStreet : $("#allRiverStreet"),
						allRiverVillage : $("#allRiverVillage"),
						oneRiverArea : $("#oneRiverArea"),
						oneRiverStreet : $("#oneRiverStreet"),
						oneRiverVillage : $("#oneRiverVillage"),
						twoRiverArea : $("#twoRiverArea"),
						twoRiverStreet : $("#twoRiverStreet"),
						twoRiverVillage : $("#twoRiverVillage"),
						threeRiverArea : $("#threeRiverArea"),
						threeRiverStreet : $("#threeRiverStreet"),
						threeRiverVillage : $("#threeRiverVillage"),
						isSetRiverNumber : $("#isSetRiverNumber"),
						notSetRiverNumber : $("#notSetRiverNumber") */
						id : datas[0],
						area : datas[1],
						allRiverArea : datas[2],
						allRiverStreet : datas[3],
						allRiverVillage : datas[4],
						oneRiverArea : datas[5],
						oneRiverStreet : datas[6],
						oneRiverVillage : datas[7],
						twoRiverArea : datas[8],
						twoRiverStreet : datas[9],
						twoRiverVillage : datas[10],
						threeRiverArea : datas[11],
						threeRiverStreet : datas[12],
						threeRiverVillage : datas[13],
						isSetRiverNumber : datas[14],
						notSetRiverNumber : datas[15]
					},
					success : function(json) {
						if (json.indexOf("RequestSuccessful") >= 0) {
					        json = json.replace("RequestSuccessful", "");
					        //json = eval('(' + json + ')');
					        if(json!=0){
								alert("保存成功");
								location.reload();
					        }else{
					        	alert("保存失败");
					        }
						}
					}
				});
			}
			
			function Newsave() {
				if(!confirm('确认提交数据?')){return;}
				var area=$("#area").val();
				var inputarr = $(".public-list input");
				var datas = new Array();
				$.each(inputarr, function(i, obj) {
					datas.push($(obj).val());
				});
				$.ajax({
					type : "post",
					url : "../riverMasterCensus/save",
					async : true,
					data : {
						id : datas[0],
						area : datas[1],
						allRiverArea : datas[2],
						allRiverStreet : datas[3],
						allRiverVillage : datas[4],
						oneRiverArea : datas[5],
						oneRiverStreet : datas[6],
						oneRiverVillage : datas[7],
						twoRiverArea : datas[8],
						twoRiverStreet : datas[9],
						twoRiverVillage : datas[10],
						threeRiverArea : datas[11],
						threeRiverStreet : datas[12],
						threeRiverVillage : datas[13],
						isSetRiverNumber : datas[14],
						notSetRiverNumber : datas[15]
					},
					success : function(json) {
						if (json.indexOf("RequestSuccessful") >= 0) {
					        json = json.replace("RequestSuccessful", "");
					        //json = eval('(' + json + ')');
					        if(json!=0){
								alert("保存成功");
								location.reload();
					        }else{
					        	alert("保存失败");
					        }
						}
					}
				});
			}
			
			function calAllBoard(inputarr) {
				$(inputarr).eq(2).val(
						toNum($(inputarr).eq(5).val()) + toNum($(inputarr).eq(8).val())
								+ toNum($(inputarr).eq(11).val()));
				$(inputarr).eq(3).val(
						toNum($(inputarr).eq(6).val()) + toNum($(inputarr).eq(9).val())
								+ toNum($(inputarr).eq(12).val()));
				$(inputarr).eq(4).val(
						toNum($(inputarr).eq(7).val()) + toNum($(inputarr).eq(10).val())
								+ toNum($(inputarr).eq(13).val()));
			}

			function toNum(str) {
				if(isNaN(str)){
					alert("不是数字");
				}
				return str == "" || str == undefined ? 0 : parseInt(str);
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
				<table id="showtable" class="showtable" cellspacing="0" border="1">
					<th class="title" colspan="15">广州市河涌河长数量统计表</th>
					<tr>
						<td rowspan="2">名称</td>
						<td colspan="3">全部河涌</td>
						<td colspan="3">其中35条黑臭河涌</td>
						<td colspan="3">其中152条黑臭河涌</td>
						<td colspan="3">其中187条黑臭河涌</td>
						<td rowspan="2">已设置河长河涌数量</td>
						<td rowspan="2">未设置河长河涌数量</td>
					</tr>
					<tr>
						<td>区级</td>
						<td>镇街级</td>
						<td>村居级</td>
						<td>区级</td>
						<td>镇街级</td>
						<td>村居级</td>
						<td>区级</td>
						<td>镇街级</td>
						<td>村居级</td>
						<td>区级</td>
						<td>镇街级</td>
						<td>村居级</td>

					</tr>
					<tr class="public-list" style="display: none;">
						<input type="hidden" id="id" value="" />
						<td><input type="text" id="area" name="area" value="${area}" /></td>
						<td><input type="text" id="allRiverArea" value="0" /></td>
						<td><input type="text" id="allRiverStreet" value="0" /></td>
						<td><input type="text" id="allRiverVillage" value="0" /></td>
						<td><input type="text" id="oneRiverArea" value="0" /></td>
						<td><input type="text" id="oneRiverStreet" value="0" /></td>
						<td><input type="text" id="oneRiverVillage" value="0" /></td>
						<td><input type="text" id="twoRiverArea" value="0" /></td>
						<td><input type="text" id="twoRiverStreet" value="0" /></td>
						<td><input type="text" id="twoRiverVillage" value="0" /></td>
						<td><input type="text" id="threeRiverArea" value="0" /></td>
						<td><input type="text" id="threeRiverStreet" value="0" /></td>
						<td><input type="text" id="threeRiverVillage" value="0" /></td>
						<td><input type="text" id="isSetRiverNumber" value="0" /></td>
						<td><input type="text" id="notSetRiverNumber" value="0" /></td>
					</tr>
				</table>
				<div>
					<input id="save" type="button" value="保存" onclick="save()" style="display: none;" /> 
					<input id="Newsave" type="button" value="添加" onclick="Newsave()" style="display: none;" />
				</div>

			</div>
			</section>
		</div>
	</div>
</body>
</html>