package com.hhh.weixin.company.service;

import com.hhh.weixin.menu.Button;
import com.hhh.weixin.menu.ComplexButton;
import com.hhh.weixin.menu.Menu;
import com.hhh.weixin.menu.ViewButton;
import com.hhh.weixin.util.CommonUtil;

/**
 * 菜单管理器类
 */
public class MenuManager {

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	public static Menu getMenu() {
		ViewButton btn11 = new ViewButton();
		btn11.setName("内部通知");
		btn11.setType("view");
		btn11.setUrl(CommonUtil.authorizeLink("/weixin/insidenotice"));

		ViewButton btn12 = new ViewButton();
		btn12.setName("巡河记录");
		btn12.setType("view");
		btn12.setUrl(CommonUtil.authorizeLink("/waterwx/signin"));
		
		ViewButton btn14 = new ViewButton();
		btn14.setName("投诉处理");
		btn14.setType("view");
		btn14.setUrl(CommonUtil.authorizeLink("/water/weixinRiverMaster/findRoleByUserId"));

//		ViewButton btn13 = new ViewButton();
//		btn13.setName("投诉建议");
//		btn13.setType("view");
//		btn13.setUrl(CommonUtil.authorizeLink(""));

		ViewButton btn21 = new ViewButton();
		btn21.setName("问题查询");
		btn21.setType("view");
		btn21.setUrl(CommonUtil.authorizeLink("/waterwx/searchquestion"));

		ViewButton btn22 = new ViewButton();
		btn22.setName("河道信息查询");
		btn22.setType("view");
		btn22.setUrl(CommonUtil.authorizeLink("/waterwx/searchrivers"));
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("巡河记录查询");
		btn23.setType("view");
		btn23.setUrl(CommonUtil.authorizeLink("/waterwx/searchsignin"));

//		ViewButton btn23 = new ViewButton();
//		btn23.setName("排行榜");
//		btn23.setType("view");
//		btn23.setUrl("");
//
//
		ViewButton btn31 = new ViewButton();
		btn31.setName("各区投诉统计");
		btn31.setType("view");
		btn31.setUrl(CommonUtil.authorizeLink("/waterwx/wxComplainEchar"));

		ViewButton btn32 = new ViewButton();
		btn32.setName("河道问题统计");
		btn32.setType("view");
		btn32.setUrl(CommonUtil.authorizeLink("/waterwx/wxQuestionwaterEchar"));
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("各区问题统计");
		btn33.setType("view");
		btn33.setUrl(CommonUtil.authorizeLink("/waterwx/wxAreaQuestionEchar"));

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("日常办公");
		mainBtn1.setSub_button(new Button[] {btn11, btn12, btn14});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("信息查询");
		mainBtn2.setSub_button(new Button[] {btn21,btn22,btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("统计分析");
		mainBtn3.setSub_button(new Button[] {btn31,btn32,btn33});

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3});

		return menu;
	}
}
