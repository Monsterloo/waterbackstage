<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>巡河记录</title>
    <link rel="stylesheet" href="../css/weixin/weui.min.css"/>
    <link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WPoOm3CViGGFjSZDkE2TPwsc"></script>
</head>
<body>
    <div class="container" id="container">
		<div class="weui_cells_title"><font color="red">*</font>照片上传 <span style="color:red;float:right;">*为必填项</span><br/><span style="color:#fd6e20;">（注：每次上传请选择一张照片，上传照片需拍摄清晰）</span> </div>
	<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <div class="weui_uploader">
                    <div class="weui_uploader_hd weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">图片上传</div>
                        <div class="weui_cell_ft">0/5</div>
                    </div>
                    <div class="weui_uploader_bd">
                        <ul class="weui_uploader_files">
                        </ul>
                        <div class="weui_uploader_input_wrp">
                            <input class="weui_uploader_input" id="chooseImage" type=button/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="weui_cells weui_cells_form">
 
    <div class="weui_cell weui_cell_select weui_select_after">
            <div class="weui_cell_hd">
                <label for="" class="weui_label"><font color="red">*</font>行政区域</label>
            </div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="area" id="area">
                </select>
            </div>
   	</div>
    <div class="weui_cell weui_cell_select weui_select_after">
            <div class="weui_cell_hd">
            	<label class="weui_label"><font color="red">*</font>河涌名称</label>
            </div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="reachname" id="reachname">
                </select>
            </div>
    </div>
    <div class="weui_cells_title"><font color="red">*</font>签到位置</div>
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <input type="text" class="weui_textarea" placeholder="例:天河区广园路，南铁华庭西侧的河涌" id="signinposition"/>
            </div>
        </div>
    </div>
    <div class="weui_cells_title" style="color:#fd6e20;">（注：尽量详尽描写，可将周围的标志性建筑物如：道路、桥梁、酒店、饭店、楼盘名、学校、医院、大厦名称等作为所在位置的描述。）</div>
<div id="allmap" style="width:98%;margin:0 auto;margin-top:20px;border: 1px solid #ccc;height:200px;"></div>
</div>
<br/>
<div class="weui_cells weui_cells_form" id="grade">
</div>
<div class="bd spacing" style="margin:0 auto; width:96%; text-align: center;">
<a href="javascript:;" class="weui_btn weui_btn_primary" id="updload_data">签到</a>
<a href="javascript:;" class="weui_btn weui_btn_default" id="gohome">返回</a>
<br/>
</div></div>
    <script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
    <script>
    wx.config({
        debug: false,
        appId: '${appId}',
        timestamp: '${timestamp}',
        nonceStr: '${noncestr}',
        signature: '${signature}',
        jsApiList: [
			"chooseImage",
			"uploadImage",
			"openLocation",
			"getLocation"
        ]
    });
    </script> 
    <script src="../assets/jquery-1.12.2.min.js"></script>
    <script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="../js/weixin/signin.js"></script>
</body>
</html>
