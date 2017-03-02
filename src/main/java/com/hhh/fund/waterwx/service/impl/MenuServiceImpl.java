package com.hhh.fund.waterwx.service.impl;

import javax.transaction.Transactional;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hhh.fund.util.HttpUtil;
import com.hhh.fund.waterwx.model.Button;
import com.hhh.fund.waterwx.model.CommonButton;
import com.hhh.fund.waterwx.model.ComplexButton;
import com.hhh.fund.waterwx.model.Menu;
import com.hhh.fund.waterwx.service.MenuService;
import com.hhh.fund.waterwx.util.Constants;


@Service
@Transactional
public class MenuServiceImpl implements MenuService{

	public Menu getMenu() {
		
		    //url不可为空  ,第一个菜单将会排在上面

		    
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("官方网站");  
	        btn11.setType("view");  
	        btn11.setUrl("http://www.gzwater.gov.cn/portal/site/site/portal/gzswj/index.portal"); 

	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("机构职能");  
	        btn12.setType("view");  
	        btn12.setUrl("http://www.gzwater.gov.cn/portal/site/site/portal/gzswj/swyw_xx.portal?contentId=IS5TWORWK9NCD2FMIU4PZ4EFEQ2G7QYD&categoryId=1GN0GXT8CBD8D6DNV4ZIB4LSLFDLGYPT");
	        
	        CommonButton btn13 = new CommonButton();  
	        btn13.setName("通知公告");  
	        btn13.setType("view");  
	        btn13.setUrl("http://www.gzwater.gov.cn/portal/site/site/portal/gzswj/waterNew.portal?categoryId=8BEYQXYA4MYV0W9174DKYQUE40J1C2R1"); 

	        CommonButton btn14 = new CommonButton();  
	        btn14.setName("水务要闻");  
	        btn14.setType("view");  
	        btn14.setUrl("http://www.gzwater.gov.cn/portal/site/site/portal/gzswj/waterNew.portal?categoryId=3NIMX9GMP80ELY3P2NDSR2YADS9AUT56");
	        
	        CommonButton btn15 = new CommonButton();  
	        btn15.setName("官方微博");  
	        btn15.setType("view");  
	        btn15.setUrl("http://weibo.com/guangzhouwater"); 

	        
		    /*CommonButton btn21 = new CommonButton();  
		    btn21.setName("我要投诉");  
		    btn21.setType("view");  
		    btn21.setUrl(Constants.getEncodeUrl("/weixin/complain"));
		    
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("我的投诉");  
	        btn22.setType("view");  
	        btn22.setUrl(Constants.getEncodeUrl("/weixin/swjquestion/complainList")); 
		    
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("查询");  
	        btn23.setType("view");  
	        btn23.setUrl(Constants.getEncodeUrl("/waterwx/collect")); 
	        
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("奖励说明");  
	        btn24.setType("view");  
	        btn24.setUrl(Constants.DOMAIN_NAME+"/weixin/encourage"); */
	        
	        CommonButton btn30 = new CommonButton();  
	        btn30.setName("我要投诉");  
	        btn30.setType("view");  
	        btn30.setUrl(Constants.getEncodeUrl("/water/weixin/findbyopenid"));
	        
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("热线电话");  
	        btn31.setType("click");  
	        btn31.setKey(Constants.HOT_LINE);
	        
	        
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("走进水务");
	        mainBtn1.setSub_button(new CommonButton[] {btn11,btn12,btn13,btn14,btn15});

	        /*ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("投诉窗口");  
	        mainBtn2.setSub_button(new CommonButton[] {btn21,btn22,btn23,btn24}); */
	        
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] {mainBtn1,btn30,btn31});  
	  
	        return menu;  
	}

	public int createMenu(Menu menu, String accessToken) {
	    int result = 0;  
		  
	    // 拼装创建菜单的url  
	    String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);  
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSONObject.fromObject(menu).toString();  
	    
	    System.err.println("创建菜单====="+jsonMenu);
	    
	    // 调用接口创建菜单  
	    JSONObject jsonObject = HttpUtil.sendHttps(url, "POST", jsonMenu);  
	    if (null != jsonObject) {  
	        if (0 != jsonObject.getInt("errcode")) {  
	            result = jsonObject.getInt("errcode");  
	            System.err.println("创建菜单失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+jsonObject.getString("errmsg"));  
	        }  
	    }
	  
	    return result;  
	}

}
