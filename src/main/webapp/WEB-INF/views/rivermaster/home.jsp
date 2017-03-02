<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>三级河长水务管理系统</title>
	<meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta name="viewport" content="user-scalable=0" />
    <link href="${ctx}/js/rivermaster/style/weui.min.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/rivermaster/script/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/rivermaster/script/sc-water-weixin-riverMaster-problem.js" type="text/javascript"></script>
    <link href="${ctx}/js/rivermaster/style/sc-water-weixin-riverMaster-problem.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/assets/Include/script/sw-wx.js"> </script> 
    <script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"> </script> 
       
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <!--<script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=MZa9iCyECnCWirqiNnMQA6qbndTFEtyj&v=1.0"></script>-->
    <script src="${ctx}/js/rivermaster/script/sha1.js" type="text/javascript"></script>
    <script type="text/javascript">

        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: appId, // 必填，公众号的唯一标识
            timestamp: timestamp, // 必填，生成签名的时间戳
            nonceStr: noncestr, // 必填，生成签名的随机串
            signature: signature, // 必填，签名，见附录1
            jsApiList: ["onMenuShareQQ", "onMenuShareQZone", "chooseImage", "previewImage", "uploadImage", "getLocation", "openLocation"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        </script>

</head>
<body>
    <div class="HomeDiv">
        	<input type="hidden" id="hide_area_home_for_complailist" value="${area}"/>
        	<input type="hidden" id="hide_area_home_for_complailist" value="${roleId}"/>
        <div class="zhishui">
            <div class="zhishui-item item-selected" id=" item-accept"><img src="${ctx}/js/rivermaster/image/item-1-light.png" class="item-icon" /><br /><span class="item-name ">待受理问题(<span class="item-count">${accept}</span>)</span></div>
            <div class="zhishui-item" id=" item-solve"><img src="${ctx}/js/rivermaster/image/item-2-gray.png" class="item-icon" /><br /><span class="item-name">待解决问题(<span class="item-count">${solve}</span>)</span></div>
            <div class="zhishui-item" id=" item-follow"><img src="${ctx}/js/rivermaster/image/item-3-gray.png" class="item-icon" /><br /><span class="item-name">待跟进问题<span class="">(0)</span></span></div>
            <div class="zhishui-item" id=" item-finish"><img src="${ctx}/js/rivermaster/image/item-4-gray.png" class="item-icon" /><br /><span class="item-name">待办结问题<span class="">(0)</span></span></div>
        </div>

         <div class="friens-menu">
            <div class="friend-menu-item menu-rewards">
                <img src="${ctx}/js/rivermaster/image/rewards-gray.png" class="menu-item-img" id="rewards"/></div>
            <div class="friend-menu-item menu-zhishui" >
                <img src="${ctx}/js/rivermaster/image/zhishui-light.png" class="menu-item-img" id="zhishui"/></div>
            <div class="friend-menu-item menu-message">
                <img src="${ctx}/js/rivermaster/image/message-gray.png" class="menu-item-img" id="message"/></div>
            <div class="friend-menu-item menu-statistics">
                <img src="${ctx}/js/rivermaster/image/statistics-gray.png" class="menu-item-img" id="statistics"/></div>
        </div>

        
        <!----------------------------------------待处理列表------------------------------->
        <div class="complainlist display-none"></div>
        <!----------------------------------投诉信息--------------------------------------------------------->
        <div class="complain-message display-none">
			<input type="hidden" id="hide-complainId" />
            <div class="complain-message-head">
                <img src="http://ttbtech.com/sw/Include/Image/hr-liuyifei.jpg" class="complain-message-head-photo" />
                <div class="complain-message-head-name"></div>
                <div class="complain-message-head-title"></div>
            </div>
            <div class="complain-message-imglist">
                
            </div>
            <div class="content-item-bottomline" style=" background:#fff; margin:0px; padding-left:2%;">
                <img src="${ctx}/js/rivermaster/image/location-light.png" style="margin-left: 100px;" class="content-item-bottomline-icon" /><span class="content-item-location"></span>
                <span class="content-item-reach" onclick="showReach('')" style=" margin:30px;"></span>
            </div>
            <div class="content-item-bottomline" style=" background:#fff; margin:0px; padding-left:2%;padding-bottom:30px;">
                <span class="content-item-time" style="margin-left: 100px;color:#c0c0c0;">2016年11月2日20:24:24</span>
            </div>
            <div class="complain-message-reply-head"> 回复（0）</div>
            <div class="complain-message-replylist">
            </div>
            <div class="btn accept-btn" style="display:none;" onclick="showAccept()">受理</div>
            <div class="btn approval-btn" style="display:none;" onclick="showApproval()">完结</div>
            <div class="back-btn" onclick="backToComplainList()">返回</div>
        </div>

        <!------------受理操作------------>
        <div class=" div-mask accept display-none">
			<input type="hidden" id="hide-accept-complainId" />
			<div class="accept-op-list"></div>
        </div>
		<div class="reply-no display-none">
			<div class="reply-no-op-list"></div>
        </div>

        <!----------------------------------河涌信息----------------------------------------------------->
        <div class="reach display-none">
            <div class="reach-list">
                <div class="reach-item">标题<div class="reach-item-name" id="reach-item-name_0">广州河长制电子公示牌</div></div>
            </div>
            <div class="reach-list">
                <div class="reach-item">河道（涌）名称<div class="reach-item-name"  id="reach-item-name_1"></div></div>
                <div class="reach-item border-none">河段起点<div class="reach-item-name"  id="reach-item-name_2"></div></div>
                <div class="reach-item border-none">河段终点<div class="reach-item-name" id="reach-item-name_3"></div></div>
                <div class="reach-item border-none">河段长度<div class="reach-item-name" id="reach-item-name_4"></div></div>
            </div>
            <div class="reach-list">
                <div class="reach-item">区级河长<div class="reach-item-name"><span id="reach-item-name_5"></span><div class="reach-item-phone" id="reach-item-phone_0"></div></div></div>
                <div class="reach-item border-none">镇（街）级河长<div class="reach-item-name"><span id="reach-item-name_6"></span><div class="reach-item-phone" id="reach-item-phone_1"></div></div></div>
                <div class="reach-item border-none">村（居）级河长<div class="reach-item-name"><span id="reach-item-name_7"></span><div class="reach-item-phone" id="reach-item-phone_2"></div></div></div>
                <div class="reach-item border-none">管理单位负责人<div class="reach-item-name"><span id="reach-item-name_8"></span><div class="reach-item-phone" id="reach-item-phone_3"></div></div></div>
            </div>
            <a href="javascript:;" class="reach-btn-back">返回</a>
        </div>

        <div class="div-mask next-department display-none z-index-12 bg-white">
            <div class="op-title bg-f0 font-45 next-department-title"></div>
            <div class="op-row bg-white" onclick="showAreaSelect()">行政区<span class="op-row-right width-per-50 float-right margin-r-30 area-selected">天河区</span><input type="hidden" id="hide-id-area" /></div>
            <div class="op-row bg-white height-300 line-height-300"><textarea rows="4" id="remark" class="textarea width-per-90 height-280 line-height-60  float-left" id="remark" placeholder="填写备注..."></textarea></div>
        
            <div class="btn" onclick="acceptIsOk()">确定</div>
            <div class="btn-red" onclick="nextBack()">取消</div>
        </div>

        <div class="div-mask area-dailog display-none z-index-12 bg-white"></div>

        <div class="div-mask reply-no display-none z-index-12 bg-white">
            <div class="op-title bg-f0 font-45 reply-no-title"></div>
            <div class="op-row bg-white height-300 line-height-300"><textarea rows="4" class="textarea width-per-90 height-280 line-height-60  float-left" placeholder="填写回复..."></textarea></div>
        
            <div class="btn" onclick="acceptNo()">确定</div>
            <div class="btn-red" onclick="replyBack()">取消</div>
        </div>

    </div>
    

</body>
</html>