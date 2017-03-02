package com.hhh.fund.waterwx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.web.model.DepartmentBean;
import com.hhh.fund.web.model.DisplayField;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.TreeNode;
import com.hhh.fund.web.model.UserBean;

@RestController
@RequestMapping("/departments")
public class DepartmentControllers {
	@Autowired
	private UserCenterService ucs;
	@Autowired
	private SwjUserService userService;
	
	/**
	 * 保存部门
	 * @param dep
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public TreeNode saveDep(DepartmentBean dep, HttpSession session){
		dep.setCustomerId(StringUtil.getCustomerId(session));
		String id = ucs.saveDepartment(dep).getId();
		TreeNode node = new TreeNode();
		node.setId(id);
		node.setChildren(false);
		node.setParent(dep.getParentId());
		node.setText(dep.getName());
		return node;

	}
	
	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delDep(@PathVariable String id, HttpSession session){
		DepartmentBean bean = ucs.findDepartById(id);
		String customerId = StringUtil.getCustomerId(session);
		ucs.deleteDepartmentById(id);
		DepartmentBean p = ucs.findDepartById(bean.getParentId());
		if(p != null){
			List<DepartmentBean> sublist = ucs.findDepartByParentId(customerId, bean.getParentId());
			if(sublist == null || sublist.isEmpty()){
				p.setChild(Whether.No);
				ucs.saveDepartment(p);
			}
		}
		return 1;
	}
	
	/**
	 * 根据id查询部门
	 */
	@RequestMapping(value="/id/{id}",method=RequestMethod.GET)
	public  DepartmentBean findDepById(@PathVariable String id){
		return ucs.findDepartById(id);
	}
	
	/**
	 * 查询出企业的某部门的子部门
	 */
	@RequestMapping(value="/parent",method=RequestMethod.GET)
	public List<TreeNode> findDepartByParentId(String id, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		String role = (String)session.getAttribute("roleName");
		List<TreeNode> list = new ArrayList<>();
		if(id == null || "".equals(id) || "#".equals(id)){
			TreeNode node = new TreeNode();
			node.setId("0");
			node.setText("组织架构");
			node.setParent("#");
			list.add(node);
			List<DepartmentBean> sublist=new ArrayList<>();
			if(!StringUtils.isBlank(role)&&role.equals("areamanager")){
				String area = (String)session.getAttribute("area");
				String areaName = area.substring(0, area.length() - 1);
				sublist=userService.findDepartByParentIdAndArea(customerId, "0",areaName);
			}else{
				sublist=ucs.findDepartByParentId(customerId, "0");
			}
			if(sublist != null && !sublist.isEmpty()){
				for(DepartmentBean mb : sublist){
					node = new TreeNode();
					node.setId(mb.getId());
					node.setText(mb.getName());
					node.setParent(mb.getParentId());
					node.setChildren(mb.isChild());
					list.add(node);
				}
			}
		}else{
			List<DepartmentBean> sublist =  ucs.findDepartByParentId(customerId, id);
			if(sublist != null && !sublist.isEmpty()){
				for(DepartmentBean mb : sublist){
					TreeNode node = new TreeNode();
					node.setId(mb.getId());
					node.setText(mb.getName());
					node.setParent(mb.getParentId());
					node.setChildren(mb.isChild());
					list.add(node);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 把用户添加到部门中去
	 * @param accountId
	 * @param departId
	 * @return
	 */
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public int addUserToDepartment(String accountid,String departid){
		ucs.deleteUserInDeaprtByDepartId(departid);
		if(accountid != null && !"".equals(accountid)){
			String[] ids = accountid.split(",");
			for(String id : ids){
				ucs.addUserToDepartment(id, departid);
			}
		}
		
		return 1;
	}
	
	/**
	 * 删除部门里的用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteuser/{departid}/userid/{userid}",method=RequestMethod.GET)
	public int deleteDepartmentInUser(@PathVariable String userid, @PathVariable String departid){
		if(userid != null && userid.length() != 0){
			String[] ids = userid.split(",");
			for(String id : ids){
				ucs.deleteDepartmentInUser(id, departid);
			}
		}
		return 1;
	}
	
	/**
	 * 取部门所属用户
	 * @param deaprtmentId
	 * @return
	 */
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
	public List<UserBean> findDepartmentInUser(@PathVariable String id){
		return ucs.findDepartmentInUser(id);
	}
}
