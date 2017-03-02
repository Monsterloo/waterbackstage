<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>河道详细信息</title>
<script type="text/javascript" src="../assets/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="../css/weixin/weui.min.css" />
<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css" />
<style type="text/css">
.celltit {
	margin-right: 15px;
}

.cellinfo {
	color: #888;
}
</style>
</head>
<body>
	<div class="bd" style="height: 100%;">

		<div class="weui_cells">
			<div class="weui_cell">
				<span class="celltit">河道名称</span> <span class="cellinfo">${river.riverName}</span>&nbsp;&nbsp;
			</div>
			<div class="weui_cell">
				<span class="celltit">长度</span> <span class="cellinfo">${river.length}km</span>&nbsp;&nbsp;
			</div>
			<div class="weui_cell">
				<span class="celltit">起点</span> <span class="cellinfo">${river.start}</span>&nbsp;&nbsp;
			</div>
			<div class="weui_cell">
				<span class="celltit">终点</span> <span class="cellinfo">${river.end}</span>&nbsp;&nbsp;
			</div>
			<div class="weui_cell">
				<span class="celltit">区级河长</span> <span class="cellinfo">${responsibility.distMgrName}</span>&nbsp;&nbsp;
				<span class="celltit">电话</span> <span class="cellinfo">${responsibility.distMgrTel}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">镇级河长</span> <span class="cellinfo">${responsibility.streetMgrName}</span>&nbsp;&nbsp;
				<span class="celltit">电话</span> <span class="cellinfo">${responsibility.streetMgrTel}</span>
			</div>
			<div class="weui_cell">
				<span class="celltit">村级河长</span> <span class="cellinfo">${responsibility.villageMgrName}</span>&nbsp;&nbsp;
				<span class="celltit">电话</span> <span class="cellinfo">${responsibility.villageMgrTel}</span>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	<script src="../js/weixin/jquery-weui.min.js"></script>
	<script src="../js/weixin/swiper.min.js"></script>
	<script type="text/javascript">

	</script>
</body>
</html>
