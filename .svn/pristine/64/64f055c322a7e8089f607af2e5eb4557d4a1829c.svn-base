<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>水务微信</title>
<link rel="stylesheet" href="../css/weixin/weui.min.css" />
</head>
<body>
	<input id="ro" style="display:none;" value="${role12 }">
	<div id="total_list">
		<div class="weui_cells_title">选择</div>
		<input id="menu" style="display: none;" />
		<div class="weui_cells_title" id="collect_title">投诉菜单分类</div>
		<div class="weui_cells" id="collect_list">
			<div class="weui_cells weui_cells_access">
				<a class="weui_cell dslts lookQuest" quest="待受理投诉"
					href="javascript:;">
					<div class="weui_cell_bd weui_cell_primary">
						<p>待受理投诉</p>
					</div>
					<div class="weui_cell_ft"></div>
				</a> <a class="weui_cell yslts lookQuest" quest="解决投诉"
					href="javascript:;">
					<div class="weui_cell_bd weui_cell_primary">
						<p>待解决投诉</p>
					</div>
					<div class="weui_cell_ft"></div>
				</a> <a class="weui_cell yhfts lookQuest" quest="已回复投诉"
					href="javascript:void(0);">
					<div class="weui_cell_bd weui_cell_primary">
						<p>已回复投诉</p>
					</div>
					<div class="weui_cell_ft"></div>
				</a> <a class="weui_cell wjts lookQuest" quest="完结投诉"
					href="javascript:;">
					<div class="weui_cell_bd weui_cell_primary">
						<p>已完结投诉</p>
					</div>
					<div class="weui_cell_ft"></div>
				</a>
			</div>
		</div>

	</div>
	<div id="total_datalist" style="display: none;">
		<div class="weui_cells_title">
			<a href="#" id="detail_1" class="weui_cell_ft">&lt;&nbsp;返回&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</div>

		<div class="weui_panel weui_panel_access">
			<div class="weui_panel_hd" id="type_title"></div>
			<div class="weui_panel_bd" id="type_list"></div>
		</div>
	</div>

	<div style="display: none; width: 100%;" id="collect_detail">
		<input id="menu1" style="display: none;">
		<iframe width="100%" frameborder="0" scrolling="auto"
			id="detail_iframe" onload="this.height=window.screen.height - 150"></iframe>
		<!-- <div class="button_sp_area">
	  <a href="javascript:;" id="saveQuestion" class="weui_btn weui_btn_mini weui_btn_default">投诉受理</a>
	  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default">受理回复</a>
	  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default">无效投诉</a>
	  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default">反馈上级</a>
  </div> -->
		<button id="collect_detail_ret"
			style="margin-top: 15px; margin-bottom: 15px;"
			class="weui_btn weui_btn_default">返回主页</button>
		<!--  <div>
  
  	<div class="" style="display:none;">
	  <button id="collect_detail_ret" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">投诉处理</button>
	  <button id="collect_detail_ret" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">回复投诉</button>
	  <button id="collect_detail_ret" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">无效投诉</button>
	  <button id="collect_detail_ret" style="margin-top:15px;margin-bottom:15px;" class="weui_btn weui_btn_default">反馈上级</button>
 	</div>
  </div> -->
	</div>


	<script src="../assets/jquery-1.12.2.min.js"></script>
	<script type="text/javascript"
		src="../js/complainweixin/complainMenu.js"></script>
	<!--  <script src="../js/complainweixin/handle.js"></script> -->
</body>
</html>