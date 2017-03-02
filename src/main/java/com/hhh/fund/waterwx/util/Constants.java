package com.hhh.fund.waterwx.util;

import com.hhh.fund.util.WebUtil;


/**
 * @author fws
 * created on 2014-01-22
 * @version
 */

public class Constants {
	
	public final static String FALSE = "0";
	public final static String TRUE = "1";
	
	public final static String SUCCESS="操作成功!";
	public final static String FAIL="操作失败!";
	
	public final static String SESSEION_WECHAT_OPEN_ID="sessionWachatOpenId";
	public final static String PC_SESSEION_WECHAT_OPEN_ID="pcSessionWachatOpenId";
	
	//userKey
	public final static String XW_USER_OPEN_ID= "xwUserOpenId";    //微信用户openid
	
	//appid、appsecret
	 //  正式
	public final static String APP_ID= "wx56462fd5b1c3130f";
	public final static String APP_SECRET = "2a91d4a5b66479a36958aac7064d16e6";	
	public final static String DOMAIN_NAME="http://wx1.ccqm.cn";	
	
	//测试
	//	public final static String TestAPP_ID= "wxe750f4a0bfeb885e";
	//public final static String TestAPP_SECRET = "76b077fc500538016125a60404a7ff3d";
   
//    public final static String DOMAIN_NAME="http://water.ittun.com";	 //( 回调域名微信一定要要配[网页授权获取用户基本信息   点击'修改'如：cg.cscec4bcg.com:8888])
    //授权时回调函数
    public static String REDIRECT_URI=DOMAIN_NAME+"/water/weixin/auth";
	  
//------------------------------------------------	
	//获取access_token接口       
	public final static String ACCESS_TOKEN_RUL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//自定义菜单创建接口       
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//自定义菜单删除接口     
	public final static String MENU_DELETE_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//给用户推送模板信息
	public final static String PUSH_TEMPLATE_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	//获取用户基本信息
	public final static String GET_USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public final static String GET_FILE_FROM_WECHAT="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	public final static String GET_RIVERRESPONSIBLE_WEIXIN_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
	
	
    //Scope为snsapi_base  只获取code(网页授权获取用户基本信息)redirectUrl 回调路径  dataState带的数据
    public final static String AUTH_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
       +"&redirect_uri="+WebUtil.URLEncoder(REDIRECT_URI)+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
  
    //PC 端 授权
    public final static String PC_AUTH_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
       +"&redirect_uri="+WebUtil.URLEncoder(DOMAIN_NAME+"/zj/wechat/pcAuth.do")+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
  
    
	  //通过code换取网页授权access_token   CODE为上面获取的code
	  /**返回 
	   * {
		   "access_token":"ACCESS_TOKEN",
		   "expires_in":7200,
		   "refresh_token":"REFRESH_TOKEN",
		   "openid":"OPENID",
		   "scope":"SCOPE"
		}
	   */
    public final static String TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID
       +"&secret="+APP_SECRET+"&code=CODE&grant_type=authorization_code";	
	
	//微信缓存
    public final static String WECHAT="weChat";
    
	//热线电话
    public final static String HOT_LINE="hotLine";
    
   
    public final static int PAGE_SIZE=10;
    
    //获取编码后的路径
    public static String getEncodeUrl(String url){
    	return Constants.AUTH_URL.replace("STATE",WebUtil.URLEncoder(url));
    }
    
}
