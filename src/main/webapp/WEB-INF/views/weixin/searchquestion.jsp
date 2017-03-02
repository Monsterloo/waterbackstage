<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>问题查询</title>
        <script src="../assets/jquery-1.12.2.min.js"></script>
    <script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" ></script>
	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	

    <script src="../js/weixin/searchquestion.js"></script>
    <link rel="stylesheet" href="../css/weixin/weui.min.css"/>
    <link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
    <link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
	
</head>
<body>
   
    <div id="total_list">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">受理编号</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input js-typeahead-input" style="height:30px;" type="text" id="code"  placeholder="请输入受理编号"/>
            </div>
    </div>
    <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">河长名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input js-typeahead-input" style="height:30px;" type="text" id="person"  placeholder="请输入河长名称"/>
            </div>
    </div>
    <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">河道名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input js-typeahead-input" style="height:30px;" type="text" id="reachname"  placeholder="请输入河道名称"/>
            </div>
    </div>
    <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input type="text" style="height:30px;" id="search_startTime" class="form-control" placeholder="请输入开始时间" style="border-radius:4px">
                <input type="text" style="height:30px;" id="search_endTime" class="form-control" placeholder="请输入结束时间" style="border-radius:4px">
            </div>
    </div>
    <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">问题类型</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <select name="qtype" id="qtype" style="width: 100%;height:30px;" >
				</select>
	            <input  type="hidden" id="search_qtype" class="form-control" placeholder="Search..." style="border-radius:4px">
            </div>
    </div>

<br/>
	<div class="bd spacing" style="margin:0 auto; width:96%; text-align: center;">
	<a href="javascript:;" class="weui_btn weui_btn_primary" id="manageSearchBtn">查询</a>
	</div>
</div>
<br/>

  <div id="total_datalist" style="display:none;">
  <div class="weui_cells_title">
  <a href="#" id="detail_2" class="weui_cell_ft">&lt;&nbsp;返回&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
    
  <div class="weui_panel weui_panel_access">
        <div class="weui_panel_hd" id="type_title"></div>
        <div class="weui_panel_bd" id="type_list"></div>
        <div class="weui_panel_bd" id="type_list1"></div>
    </div>
  </div>

  <div style="display:none; width:100%;" id="collect_detail">
  <iframe width="100%" frameborder="0" scrolling="auto" id="detail_iframe" onload="this.height=window.screen.height - 150"></iframe>
  <button id="detail_1" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">返回</button>
  <button id="detail_3" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">返回</button>
  </div>

    
    <script>
     
    </script> 

</body>
</html>
