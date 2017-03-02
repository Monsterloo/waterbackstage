<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.mainPage-header{width:100%;height:50px;background:#199BD7;padding-right:15px;padding-left:20px;line-height:50px;}
	.mainPage-header .welcome_text{display:block;height:50px;color:white;font-weight:bold;font-size:14px;}
</style>
<div class="mainPage-header">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div>
    <a href="#" class="welcome_text" style="width: 50%;float: left;background:url('${ctx}/img/head/logo.png') no-repeat 0px center;"></a>
  	<div class="hr-user-box">
  		<!-- <div class="hr-user-box-photo"></div> -->
  		<div class="hr-user-box-username">欢迎您，${(name==null||name=="")?"admin":name}</div>
  		<a href="../admin/logout"><img src="../img/logout.png" class="hr-user-box-logout"/></a>
  	</div>
  </div>
</div>

