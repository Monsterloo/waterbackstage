<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="../common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>我的投诉</title>
    <script type="text/javascript" src="/assets/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/js/wx/complainlist.js"></script>
    <link rel="stylesheet" href="/css/weixin/weui.min.css"/>
    <style>
       .weui_cell_p{
        color:gray;font-size:15px;
       }
       .weui_cell_span{
         margin-right:15px;
       }
       .loading{
  width:120px;
  height:20px;
  margin:10px auto;
  background: url(/img/loader.gif) no-repeat;
  }
    </style>
</head>
<body ontouchstart onload="pageInit()">
<input type="hidden" value="${pageSize}" id="pageSize"/>
<input type="hidden" value="${totalPage}" id="totalPage"/>
<div class="bd" style="height: 100%;">
    <div class="weui_cells_title" style="background-color:#F8F8FF;margin:0px;line-height:38px">我的投诉</div>
    <c:choose>
        <c:when test="${! empty list}">
             <div class="weui_cells weui_cells_access" id="listware">
             <c:forEach items="${list}" var="swj">
                 <a class="weui_cell" href="/weixin/swjquestion/questionInfo?id=${swj.id}">
		            <div class="weui_cell_bd weui_cell_primary">
		                <p>
		                	<c:choose> 
							    <c:when test="${fn:length(swj.questioncontent) > 10}"> 
							         ${fn:substring(swj.questioncontent, 0, 10)}
							    </c:when> 
							    <c:otherwise> 
							         ${swj.questioncontent}
							    </c:otherwise> 
							   </c:choose>
		                </p>
		                <p class="weui_cell_p"><span class="weui_cell_span">
		                   <c:choose> 
							    <c:when test="${fn:length(swj.questiontype) > 8}"> 
							         ${fn:substring(swj.questiontype, 0, 8)}
							    </c:when> 
							    <c:otherwise> 
							         ${swj.questiontype}
							    </c:otherwise> 
							   </c:choose> 
		                </span>
		                <span class="weui_cell_span">
		                   <c:choose>
		                   	   <c:when test="${swj.status==0}"><font color="#2e9a3b">已回复投诉</font></c:when>
					           <c:when test="${swj.status==1}"><font color="#d93a3a">待受理投诉</font></c:when>
					           <c:when test="${swj.status==2}"><font color="#ff7e00">已受理投诉</font></c:when>
					           <c:when test="${swj.status==3}"><font color="#ff7e00">无效投诉</font></c:when>
				           </c:choose>
		                </span>
		                <span class="weui_cell_span"><fmt:formatDate value="${swj.complainDate}" pattern="yyyy-MM-dd"/></span></p>
		            </div>
		           <div class="weui_cell_ft"></div>
		        </a>
             </c:forEach>
            </div>
        </c:when>
        <c:otherwise><center>暂无投诉信息！</center></c:otherwise>
    </c:choose>
    <c:if test="${totalPage>1}">
        <div class="weui_panel weui_panel_access" id="loadmoreinfo"> <a class="weui_panel_ft" href="javascript:void(0);" onclick="loadMore()">查看更多<span>(<span id="pageNo">${pageNo}</span>/${totalPage})</span></a></div>
    </c:if>
</div>
</body>
</html>
