package com.hhh.fund.waterwx.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.usercenter.dao.AccountDao;
import com.hhh.fund.usercenter.dao.RoleDao;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.waterwx.dao.SwjUcenterRoleDao;
import com.hhh.fund.waterwx.dao.SwjUserDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.SysUcenterRole;
import com.hhh.fund.waterwx.service.SwjUcenterRoleService;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
@Component
@Transactional
public class SwjUcenterRoleServiceImpl implements SwjUcenterRoleService {
	@Autowired
	private SwjUserDao swjUserDao;
	@Autowired
	private UserCenterService ucs;
	@Autowired
	private AccountDao ad;
	@Autowired
	private SysUcenterDictDao dictDao;
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private SwjUcenterRoleDao ucenterRoleDao;
	
	
	/**
	 * 根据userid 查找用户角色
	 * @param userid 用户id
	 * */
	public SysUcenterRoleBean findRoleByUserId(String userId){
		SysUcenterRole role = ucenterRoleDao.findRoleByUserId(userId);
		SysUcenterRoleBean bean = new SysUcenterRoleBean();
		if(role != null){
			BeanUtils.copyProperties(role, bean);
		}
		return bean;
	}
	
	/**
	 * 根据角色名查角色ID
	 * @param roleName 角色名
	 * */
	public SysUcenterRoleBean findByName(String roleName){
		SysUcenterRole role = ucenterRoleDao.findByName(roleName);
		SysUcenterRoleBean bean = new SysUcenterRoleBean();
		if(role != null){
			BeanUtils.copyProperties(role, bean);
		}
		return bean;
	}

}
