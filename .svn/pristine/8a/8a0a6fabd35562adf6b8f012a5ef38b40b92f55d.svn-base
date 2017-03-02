package com.hhh.fund.flowdemo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhh.fund.usercenter.Display;
import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.UserBean;

@Controller
public class InitDataController {

	@Autowired
	private UserCenterService ucenterService;
	
	/**
	 * 初始化数据， 添加admin用户
	 * @return
	 */
	@RequestMapping(value="/initdata",  
            method=RequestMethod.GET,produces={"application/json"})
	public String initData(){
		if(ucenterService.findByUsername("admin") == null){
			UserBean bean = new UserBean();
			bean.setUsername("admin");
			bean.setAdmin(Whether.Yes);
			bean.setCreatetime(StringUtil.dateFormat(new Date()));
			bean.setPassword("123456");
			bean.setDisplayName("系统管理员");
			ucenterService.saveUser(bean);
			
			MenuBean menu = new MenuBean();
			menu.setName("系统管理");
			menu.setOrder(1);
			menu.setDisplay(Display.Show);
			menu.setParentId("0");
			menu.setUrl("#");
			String id = ucenterService.saveMenu(menu).getId();
			
			menu = new MenuBean();
			menu.setName("组织架构");
			menu.setOrder(1);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_dep");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("用户管理");
			menu.setOrder(2);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_user");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("角色管理");
			menu.setOrder(3);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_role");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("资源管理");
			menu.setOrder(4);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_resourcse");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("菜单管理");
			menu.setOrder(5);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_menu");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("登录日志");
			menu.setOrder(6);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_loginLog");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("数据字典");
			menu.setOrder(7);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_dictionary");
			ucenterService.saveMenu(menu);
		/*********************************************************/
			menu = new MenuBean();
			menu.setName("流程管理");
			menu.setOrder(2);
			menu.setDisplay(Display.Show);
			menu.setParentId("0");
			menu.setUrl("#");
			id = ucenterService.saveMenu(menu).getId();
			
			menu = new MenuBean();
			menu.setName("流程定义");
			menu.setOrder(1);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_flow_definition");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("流程实例");
			menu.setOrder(2);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../admin/main?menuId=menu_flow_instance");
			ucenterService.saveMenu(menu);
		/*********************************************************************/
			menu = new MenuBean();
			menu.setName("PageOffice示例");
			menu.setOrder(3);
			menu.setDisplay(Display.Show);
			menu.setParentId("0");
			menu.setUrl("#");
			id = ucenterService.saveMenu(menu).getId();
			
			menu = new MenuBean();
			menu.setName("Word表格数据填充");
			menu.setOrder(1);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../samples/fillTableData");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("Word数据填充");
			menu.setOrder(2);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../samples/fillDocData");
			ucenterService.saveMenu(menu);
			
		/*********************************************************************/
			menu = new MenuBean();
			menu.setName("演示菜单");
			menu.setOrder(4);
			menu.setDisplay(Display.Show);
			menu.setParentId("0");
			menu.setUrl("#");
			id = ucenterService.saveMenu(menu).getId();
			
			menu = new MenuBean();
			menu.setName("待办事务");
			menu.setOrder(1);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../demo/main?menuId=menu_todo");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("已办事务");
			menu.setOrder(2);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../demo/main?menuId=menu_flow_historytask");
			ucenterService.saveMenu(menu);
			
			menu = new MenuBean();
			menu.setName("示例请假");
			menu.setOrder(3);
			menu.setDisplay(Display.Show);
			menu.setParentId(id);
			menu.setUrl("../demo/main?menuId=menu_leave");
			ucenterService.saveMenu(menu);
			
		}
		return "initdata";
	}
}
