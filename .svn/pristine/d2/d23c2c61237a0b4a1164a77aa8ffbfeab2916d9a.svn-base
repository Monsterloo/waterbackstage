<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String version = "gzsw_v1_20161117_1646"; %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>广州水务</title>
    <script src="${ctx}/assets/Include/script/jquery-2.1.1.min.js?ver=<%=version%>"  type="text/javascript"></script>
    <script id="swjs" src="${ctx}/assets/Include/script/sw.js?ver=<%=version%>" type="text/javascript"></script>
    <script src="${ctx}/assets/Include/script/jweixin-1.0.0.js?ver=<%=version%>" type="text/javascript"></script>
    <script src="${ctx}/assets/Include/script/sw-wx.js?ver=<%=version%>" type="text/javascript"></script>
    <script src="${ctx}/assets/Include/script/example.js?ver=<%=version%>" type="text/javascript"></script>
    <link href="${ctx}/assets/Include/style/sw.css?ver=<%=version%>" rel="stylesheet" type="text/css" />
    <link href="${ctx}/assets/Include/style/weui.min.css?ver=<%=version%>" rel="stylesheet" type="text/css" />
    <link href="${ctx}/assets/Include/style/example.css?ver=<%=version%>" rel="stylesheet" type="text/css" />
    <link href="${ctx}/assets/Include/style/sw_2.css?ver=<%=version%>" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        wx.config({
	        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	        appId: '${appId}', // 必填，公众号的唯一标识
	        timestamp: '${timestamp}', // 必填，生成签名的时间戳
	        nonceStr: '${noncestr}', // 必填，生成签名的随机串
	        signature: '${signature}', // 必填，签名，见附录1
	        jsApiList: ["onMenuShareQQ", "onMenuShareQZone", "chooseImage", "previewImage", "uploadImage", "getLocation", "openLocation"] // 必填，需要使用的JS接口列表,所有JS接口列表见附录2
	    });
		wx.ready(function(){
			getUserLoction();
			bind_Upload();
		})   
    </script>
</head>
<body>
	<input type="hidden" id="home_page_openId" value="${openid}"/>
    <div class="HomeDiv">
        <!---------------------特别提醒--------------------------->
        <div class="remind">
            <div class="remind-logo">
                <img class="remind-img" src="${ctx}/assets/Include/image/logo.png" /></div>
            <div class="remind-content">
                <div class="remind-title">特别提示：</div>
                <div class="remind-remark">&nbsp;&nbsp;&nbsp;目前公众号投诉窗口仅受理河涌相关投诉，其他投诉请走12345市民热线，给您造成不便敬请谅解，谢谢您的配合与支持！</div>
            </div>
            <div class="remind-choose">
                <input type="checkbox" id="remind-isNoRemind" checked="checked" value="1" style="display: none;" />
                <img src="${ctx}/assets/Include/image/checked.png" class="remind-checkImg" /><div class="remind-check-title">下次不再提醒</div>
            </div>
            <div class="remind-btn">
                <div class="remind-iknow">我知道了</div>
            </div>
        </div>

	 <input type="hidden" id="home_page_openId" value="${sessionWachatOpenId}"/>
	 <input type="hidden" id="home_page_remind" value="${isNotRemind}"/>
	 <input type="hidden" id="userId" value="" />
	 <input type="hidden" id="weixin_user_sex"></input>
	 <input type="hidden" id="weixin_user_signature"></input>
	 <input type="hidden" id="weixin_user_phonenum"></input>
	 <input type="hidden" id="weixin_user_area"></input>
     <!---------------------特别提醒--------------------------->
     <div class="remind">
         <div class="remind-logo"><img class="remind-img" src="${ctx}/assets/Include/image/logo.png" /></div>
         <div class="remind-content">
             <div class="remind-title">特别提示：</div>
             <div class="remind-remark">&nbsp;&nbsp;&nbsp;目前公众号投诉窗口仅受理河涌相关投诉，其他投诉请走12345市民热线，给您造成不便敬请谅解，谢谢您的配合与支持！</div>
         </div>
         <div class="remind-choose">
             <input type="checkbox" id="remind-isNoRemind" value="1" style="display: none;" />
             <img src="${ctx}/assets/Include/image/checkno.png" class="remind-checkImg" /><div class="remind-check-title">下次不再提醒</div>
         </div>
         <div class="remind-btn">
         	<a href="javascript:;" class="weui-btn weui-btn_primary remind-iknow">我知道了</a>
         </div>
     </div>
     <!---------------------主界面区--------------------------->
     <div class="HomeDiv">
        <!---------------------圈子--------------------------->
        
        <div class="friend display-none">
        <div class="friend display-none">
            <div class="friend-content" id="thelist" >
                <div class="friend-head">
                    <div class="friend-head-search"><img src="${ctx}/assets/Include/image/friend-search.png" class="friend-search-img" /></div>
                    <div class="friend-head-photo"><img src="${ctx}/assets/Include/image/photo-default.png" class="head-photo-img" /></div>
                    <div class="friend-head-name"></div>
                    <div class="friend-head-level">
                        <img src="${ctx}/assets/Include/image/LV.png" class="head-level-img"/>
                        <img src="${ctx}/assets/Include/image/level-gray.png" class="head-level-img" />
                        <img src="${ctx}/assets/Include/image/level-gray.png" class="head-level-img" />
                        <img src="${ctx}/assets/Include/image/level-gray.png" class="head-level-img" />
                        <img src="${ctx}/assets/Include/image/level-gray.png" class="head-level-img" />
                        <img src="${ctx}/assets/Include/image/level-gray.png" class="head-level-img" />
						
                    </div>
                </div>
                <div class="friend-complain-list"></div>
                <div class="my-complain">
                    <div class="my-list-menu">
                        <div class="my-list-menu-item my-list-menu-item-selected" id="">全部</div>
                        <div class="my-list-menu-item" id="1">待受理</div>
                        <div class="my-list-menu-item" id="2">待分派</div>
                        <div class="my-list-menu-item" id="5">待办结</div>
                        <div class="my-list-menu-item" id="0">待评价</div>
                    </div>
                    <div class="my-complain-list">
                    </div>
                </div>
            </div>
        </div>
		<!---------------------Set--------------------------->
		<div class="guide display-none">
			<img src="${ctx}/assets/Include/image/zhinan.jpg"  width="100%"/>
		</div>
        <!---------------------Set--------------------------->
        <div class="set display-none">
            <div class="set-title">账号</div>
            <div class="set-list">
                <div class="set-item-photo">头像<img src="${ctx}/assets/Include/image/hr-liuyifei.jpg" class="set-photo" /></div>
                <div class="set-item">昵称<div class="set-item-name"></div></div>
                <div class="set-item">性别<div class="set-item-sex"></div></div>
                <div class="set-item border-none">地区<div class="set-item-district"></div></div>
            </div>
            <div class="set-title">个性</div>
            <div class="set-list set-character">
                <div class="set-item">个性签名<div class="set-item-name  text-ellipsis" id="set-item-name_text-ellipsis"></div></div>
                <div class="set-item border-none">手机号码<div class="set-item-sex" id="set-item-name_tel_phone"></div></div>
            </div>
        </div>

        <div class="message display-none">
        
        </div>

		<!-----------------底部功能区------------------->
		<div class="friens-menu">
             <div class="friend-menu-head"></div>
             <div class="friend-menu-item menu-friend">
                 <img src="${ctx}/assets/Include/image/friend-light.png" class="menu-item-img" id="friend"/>
                 <div class="menu-bubble bubble-friend">0</div>
             </div>
             <div class="friend-menu-item menu-message">
                 <img src="${ctx}/assets/Include/image/message-gray.png" class="menu-item-img" id="message"/>
                 <div class="menu-bubble bubble-message">0</div>
             </div>
             <div class="friend-menu-item"></div>
             <div class="menu-complain">
                 <img src="${ctx}/assets/Include/image/complain.png" class="menu-item-complain" /></div>
             <div class="friend-menu-item menu-guide">
                 <img src="${ctx}/assets/Include/image/guide-gray.png" class="menu-item-img" id="guide"/></div>
             <div class="friend-menu-item menu-set">
                 <img src="${ctx}/assets/Include/image/set-gray.png" class="menu-item-img" id="set"/></div>
         </div>
    </div>
    <!---------------------河长公示牌--------------------------->
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
        <br/>
        <a href="javascript:;" class="reach-btn-back">返回</a>
    </div>
    <!---------------------筛选查询区--------------------------->
    <div class="search display-none">
        <div class="search-title">搜索查询</div>
        <div class="search-list">
            <div class="search-item">行政区域<div class="search-choose search-selected-district">请选择</div>
                <input type="hidden" id="search-selected-district" /></div>
               <input type="hidden"  id="id-search-selected-district"/>
            <div class="search-item">投诉分类<div class="search-choose search-selected-type" >请选择</div>
                <input type="hidden" id="search-selected-type" /></div>
                <input type="hidden"  id="id-search-selected-type"/>
            <div class="search-item">时间段<div class="search-choose search-selected-time">请选择</div>
                <input type="hidden" id="search-selected-time" /></div>
            <div class="search-item">河道名称
                <input type="text" class="search-input-river" placeholder="请输入河道名称"/>
            </div>
        </div>
        <div class="search-btn-submit btn">筛选</div>
        <div class="search-btn-cancel btn-red">取消</div>

        <div class="search-selectdialog">
            <input type="hidden" id="selecter-type" value="" />
            <div class="search-select-list">
                <!--<div class="search-select-item">
                    <span>越秀区</span>
                    <div class="search-select-isselected"></div>
                    <input type="hidden" class="selecter-id" value="1" />
                </div>-->

            </div>
            <div class="search-select-btn-w">
                <div class="search-select-btn-submit">确定</div>
                <div class="search-select-btn-cancel">取消</div>
            </div>
        </div>
    </div>
    <!-----------------市民投诉-------------------->
    <div class="complain display-none">
        <div class="complain-content">
            <input type="hidden" id="hidden-img-serverId" value=""/> 
            <textarea  id="id-complain-content" name="complain-content" class="complain-input" placeholder="请输入河道问题..."></textarea>
             <div class="weui-gallery" id="gallery">
                <span class="weui-gallery__img" id="galleryImg"></span>
                <div class="weui-gallery__opr">
                    <a href="javascript:" class="weui-gallery__del">
                        <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                    </a>
                </div>
            </div>

            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <div class="weui-uploader">
                            <div class="weui-uploader__hd">
                                <p class="weui-uploader__title">图片上传</p>
                                <div class="weui-uploader__info">0/2</div>
                            </div>
                            <div class="weui-uploader__bd">
                                <ul class="weui-uploader__files" id="uploaderFiles">
                                </ul>
                                <div class="weui-uploader__input-box">
                                    <div id="uploaderInput" class="weui-uploader__input" ></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="complain-content-location">
                <div class="conplain-content-location-d">
                    <img src="${ctx}/assets/Include/image/location-light.png" class="complain-content-location-img" />
                    <input type="hidden" id="complain-content-location-lat"/>
                    <input type="hidden" id="complain-content-location-lon"/>
                    <input type="hidden" id="complain-content-location-area"/>
                    <span class="complain-content-location-text" onclick="showLocationMap()"></span>
                    <span class="complain-content-location-reach" style='display:none'></span>
                </div>
            </div>
        </div>
        <div class="complain-right">
            <img src="${ctx}/assets/Include/image/complain-right.png" class="complain-right-img" />
            <span>谁可以看</span>
            <span class="complain-right-selected">公开</span>
            <input type="hidden" id="hidden-complain-right" value="0" />
        </div>
        <div class="right-selectdailog display-none">
            <div class="right-selectdailog-d">
                <div class="right-select-item" onclick="rightSelected(this)">
                    <span>公开</span>
                    <input type="hidden" class="selecter-id" value="0" />
                </div>
                <div class="right-select-item" onclick="rightSelected(this)">
                    <span>仅个人</span>
                    <input type="hidden" class="selecter-id" value="1" />
                </div>
            </div>
        </div>
        <div class="complain-share">
            <div class="complain-share-item"><img src="${ctx}/assets/Include/image/share-weixin-gray.png" class="complain-share-icon" /><input type="hidden" id="hidden-share-weixin" value="0" /></div>
            <div class="complain-share-item"><img src="${ctx}/assets/Include/image/share-zone-gray.png" class="complain-share-icon" /><input type="hidden" id="hidden-share-zone" value="0" /></div>
        </div>
        <div class="complain-btn" onclick="Send()">发送</div>
        <div class="complain-btn-close" onclick="comlainClose()">取消</div>
        <div class="complain-activity">
            <img src="${ctx}/assets/Include/image/complain-activity-img.png" class="complain-activity-img" />
            <div class="activity-no" onclick="activity(-1)"></div>
            <div class="activity-yes" onclick="activity(1)"></div>
        </div>            
    </div>
    <!-----------------联系电话区------------------->
    <div class="bind-phone display-none">
        <div class="bind-phone-head">
            <img src="${ctx}/assets/Include/image/bind-phone.png" class="bind-phone-head-img" />
        </div>
        <div class="bind-phone-i">
            <input type="text" id="id-bind-phone-input" class="bind-phone-input" maxlength="11" placeholder="请输入您的联系手机号码" onkeyup="this.value=this.value.replace(/\D/g,'')"  oninput="checkPhone(this)" />
            <div class="bind-phone-isok">√</div>
        </div>
        <div class="bind-phone-op">
            <div class="bind-phone-submit">确认</div>
            <div class="bind-phone-ignore">忽略</div>
        </div>
    </div>
    <!-----------------奖励说明区------------------->
    <div class="rewards display-none">
        <div class="rewards-title">奖励说明</div>
        <div class="rewards-content">
            <span>1、关注“广州市河涌管理中心”服务号</span>
            <span>2、在微信群或朋友圈分享“广州水务”的“我要投诉”的功能，将分享的截图发到“广州市河涌管理中心”服务号，将有机会获得一定额度的微信红包。</span>
            <span>3、通过“广州水务”微信号进行河涌问题投诉，将经审核的有效投诉截图发到“广州市河涌管理中心”服务号，将有机会获得一定额度的微信红包。</span>
        </div>
        <div class="rewards-bottom">
            <img src="${ctx}/assets/Include/image/sw-eweima.png" class="rewards-bottom-eweima" />
            <div class="btn rewards-bottom-buttom">返回</div>
        </div>
    </div>
    <!-----------------办理情况------------------->
    <div class="flow display-none">
        <div class="flow-complain">
            <img src="http://ttbtech.com/sw/Include/Image/hr-liuyifei.jpg" class="flow-complain-img" />
            <div class="flow-complain-right">
                <span class="flow-complain-right-status">办理状态：<span class="flow-status">待受理</span></span>
                <span class="flow-complain-title">投诉问题：<span class="flow-title">污水直排河涌</span></span>
                <span class="flow-complain-title">投诉编号：<span class="flow-code">TS21402145</span></span>
            </div>
        </div>
        <div class="flow-currentpeople">
            <img src="${ctx}/assets/Include/image/photo-default.png" class="flow-currentpeople-photo" />
            <div class="flow-currentpeople-right">
                <p class="flow-currentpeople-post">河涌管理中心</p>
                <p class="flow-currentpeople-name">黄润</p>
            </div>
            <div class="flow-currentpeople-op">
                <img src="${ctx}/assets/Include/image/op-call.png" class="flow-currentpeople-op-img"/><span class="flow-currentpeople-op-sp"></span>
            </div>
        </div>
        <div class="flow-flow"></div>
        <div class="btn flow-btn" onclick="flowClose()">返回</div>
    </div>
    <!-----------------市民回复区------------------->
    <div class="complain-message display-none">
        <div class="complain-message-head">
            <img src="http://ttbtech.com/sw/Include/Image/hr-liuyifei.jpg" class="complain-message-head-photo" />
            <div class="complain-message-head-name"></div>
            <div class="complain-message-head-title"></div>
        </div>
        <div class="complain-message-imglist">
        </div>
        <div class="content-item-bottomline" style=" background:#fff; margin:0px; padding-left:2%;">
            <img src="${ctx}/assets/Include/image/location-light.png" style="margin-left: 140px;" class="content-message-bottomline-icon" /><span class="content-message-location">asdfasdasdasd</span>
            <span class="content-message-reach" onclick="showReach('')" style=" margin:30px;">asd</span>
        </div>
        <div class="complain-message-reply-head"> 回复（<span id="reply_num"></span>）</div>
        <div class="complain-message-replylist">
        </div>
        
        <div class="btn reply-back-btn" onclick="replyClose()">返回</div>

        <div class="reply"><input type="text" class="reply-input" placeholder="回复" /><div class="reply-send" onclick="replySend();">发送</div></div>
    </div>
    <!-----------------客户评价区------------------->
    <div class="evaluate display-none">
        <div class="evaluate-head"><img src="${ctx}/assets/Include/image/hr-liuyifei.jpg" class="evaluate-head-img" /><span class="evaluate-head-name">广州市河涌管理中心</span></div>
        <div class="evaluate-application">
            <div class="evaluate-application-content">经过十番不懈努力，这片水域终于被我们治好了，现在水也不清了，味道也有了，真好</div>
            <div class="evaluate-application-imglist">
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/010.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/003.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/005.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/007.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/009.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/012.jpg" class="evaluate-application-img" />
                <img src="http://ttbtech.com/sw/Include/image/FriendImage/014.jpg" class="evaluate-application-img" />
            </div>
            <div class="evaluate-application-time">2016年11月7日15:09:25</div>
        </div>
        <div class="evaluate-star-d">
            <div class="evaluate-star-row">用户评价</div>
            <div class="evaluate-star-row attitude-star">服务态度
                <div class="star-list">
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                </div>
            </div>
            <div class="evaluate-star-row efficiency-star">服务效率
                <div class="star-list">
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                </div>
            </div>
            <div class="evaluate-star-row quality-star">服务质量
                <div class="star-list">
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                    <img src="${ctx}/assets/Include/image/evaluate-gray.png" class="star-icon" />
                </div>
            </div>
            <div class="evaluate-hr"></div>
             <textarea class="evaluate-input" placeholder="请输入服务点评..."></textarea>
        </div>
       <div class="btn" onclick="evaluateSubmit()">提交</div>
    </div>
    <!-----------------坐标地图显示------------------->
	<div class="tx-map">
		<iframe id="tx-map-iframe" frameborder="0" width="100%"></iframe>
		<div class="btn" onclick="TencentMapClose()">返回</div>
	</div>
	<!-----------------加载数据中------------------->
	<div class="hr-loading-mask">
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">数据加载中</p>
        </div>
    </div>
</body>
</html>
