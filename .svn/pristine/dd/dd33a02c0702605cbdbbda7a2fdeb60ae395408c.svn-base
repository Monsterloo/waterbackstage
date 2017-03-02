package com.hhh.fund.waterwx.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hhh.fund.util.FundPage;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.entity.SysUcenterUserrole;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterUserroleBean;
import com.hhh.fund.web.model.DepartmentBean;
import com.hhh.fund.web.model.UserBean;

public interface SwjUserService {
	/**
	 * 保存用户
	 * */
	//public void save(SwjUserBean bean);
	
	/**
	 * 
	 * 保存用户改进版
	 * **/
	public SwjUserBean save(SwjUserBean bean);
	/**
	 * 删除问题类型
	 * */
	public void delete(String id);
	/**
	 * 查找所有用户
	 * */
	public SmsPage<SwjUserBean> findUserAll(Pageable page);
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize);
	/**
	 * 查询用户
	 * @param id 河道id
	 * */
	public SwjUserBean findById(String id);
	/**
	 * 获取所有的行政区域
	 * */
	public List<SysUcenterDict> findArea();
	/**
	 * 导出list
	 * */
	public List<SwjUserBean> getAllToExport(final String area,final String name);
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	public boolean exportUser(File file, List<SwjUserBean> dataList);
	/**
	 * 根据userid 查找用户
	 * @param userId 用户id
	 * */
	public SwjUserBean findByUserId(String userId);
	/**
	 * 根据username 查找用户
	 * @param userId 用户id
	 * */
	public SwjUserBean findByName(String username);
	/**
	 * 根据角色统计用户
	 * 
	 * */
	public Map<String,Integer> findUserByRole();
	
	public FundPage<UserBean> findUserAllByArea(UserBean bean,String area,Pageable page);
	
	public List<DepartmentBean> findDepartByParentIdAndArea(String customerId, String id,String areaName);
	
	public String getDepartmentByUserId(String userId);
	
	public void updateUserInfo() throws IOException;
	

}
