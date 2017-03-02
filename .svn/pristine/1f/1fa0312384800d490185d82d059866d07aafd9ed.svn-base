<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>广州市河涌河长公示牌数量统计表</title>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
	<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
	<link rel="stylesheet" href="${ctx}/css/thePage.css" />
	<link rel="stylesheet" href="${ctx}/css/main.css" />
	<link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
<script type="text/javascript" src="../assets/jquery-1.12.2.min.js" ></script>
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
	width: 95px;
}
</style>
<script type="text/javascript">
	var allRiverNumber=0;
	var oneRiverNumber=0;
	var oneBoardNumber=0;
	var twoRiverNumber=0;
	var twoBoardNumber=0;
	var threeRiverNumber=0;
	var threeBoardNumber=0;
	var otherRiverNumber=0;
	var otherBoardNumber=0;
	var notSetRiverNumber=0;
	var notSetBoardNumber=0;
	
	$(document).ready(function() {
		var area=$("#area").val();
		if(area!=""){$("#save").show();}
		showRiverMasterBoard(area);
	});
	
	function showRiverMasterBoard(area) {
	    $.ajax({
	    	url:'../riverMasterBoard/getRiverMasterBoard',
	        type: 'GET',
	        dataType: 'text',
	        data: { 'area': area },
	        success: function (json) {
	        	if (json.indexOf("RequestSuccessful") >= 0) {
					json = json.replace("RequestSuccessful", "");
					if (json == ""||json=="Null") {  
                    	$(".public-list").show();
                    	$("#save").hide();
                    	$("#Newsave").show();
                    	var inputarr = $(".public-list input");
                		$(inputarr[1]).attr('disabled', true);
                		$(inputarr[2]).attr('disabled', true);
                		calAllBoard(inputarr);
                		$.each(inputarr, function(i, obj) {
                			$(obj).blur(function() {
                				calAllBoard(inputarr);
                			});
                		});
                    	return;
					}
					json = eval('(' + json + ')');
					$.each(json.rivermasterboardList, function (i, item) {
						allRiverNumber+=item.allRiverNumber;
						oneRiverNumber+=item.oneRiverNumber;
						oneBoardNumber+=item.oneBoardNumber;
						twoRiverNumber+=item.twoRiverNumber;
						twoBoardNumber+=item.twoBoardNumber;
						threeRiverNumber+=item.threeRiverNumber;
						threeBoardNumber+=item.threeBoardNumber;
						otherRiverNumber+=item.otherRiverNumber;
						otherBoardNumber+=item.otherBoardNumber;
						notSetRiverNumber+=item.notSetRiverNumber;
						notSetBoardNumber+=item.notSetBoardNumber;
						var h = "<tr class=\"tds\"><input type=\"hidden\" id=\"id\" value=" + item.id + " />"
						h+="<td><input type=\"text\" class=\"change\" id=\"area\" value=" + item.area + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"allRiverNumber\" value=" + item.allRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneRiverNumber\" value=" + item.oneRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"oneBoardNumber\" value=" + item.oneBoardNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoRiverNumber\" value=" + item.twoRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"twoBoardNumber\" value=" + item.twoBoardNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"threeRiverNumber\" value=" + item.threeRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"threeBoardNumber\" value=" + item.threeBoardNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"otherRiverNumber\" value=" + item.otherRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"otherBoardNumber\" value=" + item.otherBoardNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"notSetRiverNumber\" value=" + item.notSetRiverNumber + " /></td>"
						h+="<td><input type=\"text\" class=\"change\" id=\"notSetBoardNumber\" value=" + item.notSetBoardNumber + " /></td></tr>"
						$('.public-list').before(h);
					});
						var inputarr = $(".tds input");
						if(area!=""){
							$(inputarr[1]).attr('disabled', true);
							$(inputarr[2]).attr('disabled', true);
							calAllBoard(inputarr);
							$.each(inputarr, function(i, obj) {
								$(obj).blur(function() {
									calAllBoard(inputarr);
								});
							});
						}else{
							var h = "<tr class=\"tds\"><td><input type=\"text\" class=\"change\" id=\"area\" value=\"总计\" /></td>"
							h+="<td><input class=\"change\" value=" + allRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + oneRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + oneBoardNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + twoRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + twoBoardNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + threeRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + threeBoardNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + otherRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + otherBoardNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + notSetRiverNumber + " /></td>"
							h+="<td><input class=\"change\" value=" + notSetBoardNumber + " /></td></tr>"
							$('.public-list').before(h);
							$(".change").attr('disabled', true);
						}
				 }
			 }
	    });
	}
	

	function calAllBoard(inputarr) {
		$(inputarr).eq(2).val(
				toNum($(inputarr).eq(4).val()) + toNum($(inputarr).eq(6).val())
						+ toNum($(inputarr).eq(8).val())
						+ toNum($(inputarr).eq(10).val()));
	}

	function toNum(str) {
		if(isNaN(str)){
			alert("不是数字");
		}
		return str == "" || str == undefined ? 0 : parseInt(str);
	}

	function save() {
		if(!confirm('确认提交数据?')){return;}
		var area=$("#area").val();
		var inputarr = $(".tds input");
		var datas = new Array();
		$.each(inputarr, function(i, obj) {
			datas.push($(obj).val());
		});
		$.ajax({
			type : "post",
			url : "../riverMasterBoard/save",
			async : true,
			data : {
				id : datas[0],
				area : datas[1],
				allRiverNumber : datas[2],
				oneRiverNumber : datas[3],
				oneBoardNumber : datas[4],
				twoRiverNumber : datas[5],
				twoBoardNumber : datas[6],
				threeRiverNumber : datas[7],
				threeBoardNumber : datas[8],
				otherRiverNumber : datas[9],
				otherBoardNumber : datas[10],
				notSetRiverNumber : datas[11],
				notSetBoardNumber : datas[12]
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
			url : "../riverMasterBoard/save",
			async : true,
			data : {
				id : datas[0],
				area : datas[1],
				allRiverNumber : datas[2],
				oneRiverNumber : datas[3],
				oneBoardNumber : datas[4],
				twoRiverNumber : datas[5],
				twoBoardNumber : datas[6],
				threeRiverNumber : datas[7],
				threeBoardNumber : datas[8],
				otherRiverNumber : datas[9],
				otherBoardNumber : datas[10],
				notSetRiverNumber : datas[11],
				notSetBoardNumber : datas[12]
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
</script>
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
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div id="showspace">
					<table id="showtable" class="showtable" border="2">
						<input type="hidden" id="area" value="${area}"/>
						<th class="title" colspan="12" style="text-align:center;">广州市河涌河长公示牌数量统计表</th>
						<tr>
							<td rowspan="2">名称</td>
							<td rowspan="2">全部河涌河长公示牌数量</td>
							<td colspan="2">其中35条黑臭河涌</td>
							<td colspan="2">其中152条黑臭河涌</td>
							<td colspan="2">其中187条黑臭河涌</td>
							<td colspan="2">其他河涌</td>
							<td colspan="2">未设置河长河涌数量</td>
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
							<td>需设置公示牌个数</td>
						</tr>
						
						<tr class="public-list" style="display:none;">
							<input type="hidden" id="id" value="" />
							<td><input type="text" id="area" name="area" value="${area}"/></td>
							<td><input type="text" id="allRiverNumber" value="0" /></td>
							<td><input type="text" id="oneRiverNumber" value="0" /></td>
							<td><input type="text" id="oneBoardNumber" value="0" /></td>
							<td><input type="text" id="twoRiverNumber" value="0" /></td>
							<td><input type="text" id="twoBoardNumber" value="0" /></td>
							<td><input type="text" id="threeRiverNumber" value="0" /></td>
							<td><input type="text" id="threeBoardNumber" value="0" /></td>
							<td><input type="text" id="otherRiverNumber" value="0" /></td>
							<td><input type="text" id="otherBoardNumber" value="0" /></td>
							<td><input type="text" id="notSetRiverNumber" value="0" /></td>
							<td><input type="text" id="notSetBoardNumber" value="0" /></td>
						</tr>
					</table>
					<div>
						<input id="save" type="button" value="保存" onclick="save()" style="display:none;"/>
						<input id="Newsave" type="button" value="保存" onclick="Newsave()" style="display:none;"/>
					</div>
				</div>
    	</section>
		</div>
    </div>
</body>
</html>
