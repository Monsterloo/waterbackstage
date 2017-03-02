<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>水务微信</title>
    <link rel="stylesheet" href="../css/weixin/weui.min.css"/>
</head>
<body>
<div id="total_list">
<div class="weui_cells_title">选择</div>
    <div class="weui_cells">

        <div class="weui_cell weui_cell_select weui_select_before">
            <div class="weui_cell_hd">
                <select class="weui_select" style="width:180px;" name="area" id="area">
                   
                </select>
            </div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" style="width:160px;" name="riverName" id="riverName">
                    
                </select>
            </div>
        </div>
    </div>
    
    <div class="weui_cells_title" id="collect_title">统计列表</div>
    <div class="weui_cells" id="collect_list"></div>
     
  </div>
  <div id="total_datalist" style="display:none;">
  <div class="weui_cells_title">
  <a href="#" id="detail_1" class="weui_cell_ft">&lt;&nbsp;返回&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
    
  <div class="weui_panel weui_panel_access">
        <div class="weui_panel_hd" id="type_title"></div>
        <div class="weui_panel_bd" id="type_list">
        </div>
    </div>
  </div>
  
  <div style="display:none; width:100%;" id="collect_detail">
  <iframe width="100%" frameborder="0" scrolling="auto" id="detail_iframe" onload="this.height=window.screen.height - 150"></iframe>
  <button id="collect_detail_ret" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">返回</button>
  </div>
  
  
    <script src="../assets/jquery-1.12.2.min.js"></script>
    <script type="text/javascript" src="../js/weixin/collect.js"></script>
</body>
</html>