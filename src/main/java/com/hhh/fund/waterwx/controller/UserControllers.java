package com.hhh.fund.waterwx.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.web.model.DisplayField;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.security.util.ShiroUtils;

@RestController
@RequestMapping("/users")
public class UserControllers {
	
	private final static String USER_MENU="system_user_menu";
	
	@Autowired
	private UserCenterService ucs;
	
	@Autowired
	private SwjUserService userService;

	
	/**
	 * 保存用户的角色
	 * @param username
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/saverole", method=RequestMethod.POST)
	public String saveUserRole(String username, String roleIds){
		String[] ids = null;
		if(roleIds != null && !"".equals(roleIds)){
			ids = roleIds.split(",");
		}
		ucs.saveRoleToUser(username, ids);
		return "";
	}
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value="existusername", method=RequestMethod.POST)
	public boolean existUsername(String username){
		UserBean bean = ucs.findByUsername(username);
		if(bean == null)
			return true;
		else
			return false;
	}
	
	/**
	 * 返回用户的最基本信息（登录名和显示名）
	 * @param pageno
	 * @param pagesize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listbase/{pageno}/pagesize/{pagesize}", method = RequestMethod.GET)
	public FundPage<DisplayField> listbase(@PathVariable Integer pageno, @PathVariable Integer pagesize,
			HttpSession session,String name) {
		
		//服务器不需要转换，本地需要转换
		/*if(name!=null && !"".equals(name)){
			try {
				System.out.println(name);
				name = new String(name.getBytes("iso8859-1"),"utf-8");
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}*/
		System.out.println(name);
		UserBean bean = new UserBean();
		bean.setCustomerId(StringUtil.getCustomerId(session));
		bean.setDisplayName(name);
		FundPage<UserBean> page = ucs.findUserAll(bean, new PageRequest(pageno, pagesize));
		List<DisplayField> list = new ArrayList<>();
		if(page !=null){
			if(page.getContent() != null){
				for(UserBean b : page.getContent()){
					DisplayField df = new DisplayField();
					df.setId(b.getUserId());
					if(b.getDisplayName() == null || "".equals(b.getDisplayName())){
						df.setName(b.getUsername());
					}else{
						df.setName(b.getDisplayName());
					}
					list.add(df);
				}
			}
		}else{
			return new FundPage<DisplayField>(0, 0, list);
		}
		return new FundPage<DisplayField>(page.getTotalPages(), page.getTotalElements(), list);
	}
	
	/**
	 * 查询用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "list/{pageno}/pagesize/{pagesize}", method = RequestMethod.POST)
	public FundPage<UserBean> list(@PathVariable Integer pageno, @PathVariable Integer pagesize, UserBean bean,
			HttpSession session) {
		String area="";
		String role="";
		if (bean == null) {
			bean = new UserBean();
		}
		System.out.println(session.getAttribute("area"));
		if(session!=null){
				area= (String)session.getAttribute("area");
				role=  (String)session.getAttribute("role");
				
		}
		
		bean.setCustomerId(StringUtil.getCustomerId(session));
		if(!StringUtils.isBlank(role)&&role.equals("areamanager")){
			FundPage<UserBean> page = userService.findUserAllByArea(bean, area,new PageRequest(pageno, pagesize));
			return page;
		}else{
			FundPage<UserBean> page = ucs.findUserAll(bean, new PageRequest(pageno, pagesize));
			return page;
		}
		
	}


}
