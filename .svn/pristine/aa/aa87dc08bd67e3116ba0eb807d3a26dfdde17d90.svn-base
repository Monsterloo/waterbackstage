<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>水务微信</title>
<link rel="stylesheet" href="../css/weixin/weui.min.css" />
<link rel="stylesheet" href="../css/weixin/jquery-weui.min.css">
<link rel="stylesheet" href="../assets/fontawesome/css/font-awesome.min.css">
</head>

<body ontouchstart>
	<div class="container" id="container">
		<div class="weui_panel">
			<div class="weui_panel_hd">内部通知列表<button class="weui_btn weui_btn_mini weui_btn_primary"
					style="float: right; margin-right: 15px;" id="notice_add">新增</button></div>
			<div class="weui_panel_bd" id="notice_list" style="overflow:auto;"></div>
			<div class="weui-infinite-scroll" style="display: none;">
				<div class="infinite-preloader"></div>
				正在加载
			</div>
		</div>
	</div>
	<!-- 表单数据 -->
	<div id="notice_form" style="display: none;">
		<div class="weui_cells_title"><font color="red">*</font>标题</div>
		<input type="hidden" id="id" value="">
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" id="title" placeholder="请输标题">
				</div>
			</div>
		</div>
		<div class="weui_cells_title"><font color="red">*</font>内容</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<textarea class="weui_textarea" placeholder="请输入通知的正文" rows="4" id="content"></textarea>
				</div>
			</div>
		</div>
		<div class="weui_cells_title" ><font color="red">*</font>
		<a href="javascript:;"  data-target="#selectuser" class='open-popup' id="selectuser_click">选择用户</a></div>
		<input type="hidden" id="sonsumes">
		<div class="weui_cells weui_cells_form" id="sonsumes_div">
			
		</div>
		<div class="weui-row weui-no-gutter">
			  <div class="weui-col-50" ><button class="weui_btn weui_btn_primary" style="width:95%;" id="notice_ok">确定</button></div>
			  <div class="weui-col-50"><button class="weui_btn weui_btn_plain_default" style="width:95%;" id="notice_cancel">取消</button></div>
			</div>
	</div>
	<!-- 选择用户 -->
	<div id="selectuser" class="weui-popup-container">
		<div class="weui-popup-overlay"></div>
		<div class="weui-popup-modal">
			<div class="weui_tab" id="select_container">
				<div class="weui_navbar">
					<a class="weui_navbar_item weui_bar_item_on" href="#user_list">用户列表 </a> 
					<a class="weui_navbar_item" href="#role_list">角色列表 </a>
				</div>
				<div class="weui_tab_bd" >
					<div id="user_list"
						class="weui_tab_bd_item weui_tab_bd_item_active">
						<div class="weui_cells weui_cells_checkbox" id="user_list_checkbox"></div>
					</div>
					<div id="role_list" class="weui_tab_bd_item">
						<div class="weui_cells weui_cells_checkbox" id="role_list_checkbox"></div>
					</div>
				</div>
			</div>
			<div class="weui-row weui-no-gutter">
				<div class="weui-col-50">
					<button class="weui_btn weui_btn_primary close-popup" style="width:95%;" id="select_ok">确定</button>
				</div>
				<div class="weui-col-50">
					<button class="weui_btn weui_btn_plain_default close-popup" style="width:95%;">取消</button>
				</div>
			</div>
		</div>
	</div>

	<script src="../js/weixin/jquery-2.1.4.js"></script>
	<script src="../js/weixin/fastclick.js"></script>
	<script>
		$(function() {
			FastClick.attach(document.body);
		});
	</script>
	<script src="../js/weixin/jquery-weui.min.js"></script>
	<script src="../js/weixin/insidenotice.js"></script>

</body>
</html>