package com.hhh.fund.waterwx.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.hhh.fund.util.FundPage;
import com.hhh.fund.web.model.CompanyBean;
import com.hhh.fund.web.model.DepartmentBean;
import com.hhh.fund.web.model.DictBean;
import com.hhh.fund.web.model.LoginLogBean;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.PermissionBean;
import com.hhh.fund.web.model.ResGroupBean;
import com.hhh.fund.web.model.ResourcesBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;

/**
 * 用户中心服务
 * @author 3hhjj
 *
 */
public interface SwjUserCenterService {
	
	/**
	 * 保存用户的信息
	 * @return
	 */
	public UserBean saveUser(UserBean userbean);
	
	/**
	 * 修改用户密码
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean modifyPassword(String username, String password);
	
	/**
	 * 锁定用户
	 * @return
	 */
	public boolean lockUser(String username);
	
	/**
	 * 解锁用户
	 * @param username
	 * @return
	 */
	public boolean unlockingUser(String username);
	
	/**
	 * 登录名取用户
	 * @param username
	 * @return
	 */
	public UserBean findByUsername(String username);
	
	/**
	 * 根据用户的user key取用户
	 * @param keyId
	 * @return
	 */
	public UserBean findUserByKeyId(String keyId);
	
	/**
	 * 依条件查询所有用户
	 * @param user 条件字段
	 * @param page 分页数据
	 * @return
	 */
	public FundPage<UserBean> findUserAll(UserBean user, Pageable page);
	
	/**
	 * 获取用户的头像
	 * @param username
	 * @return
	 */
	public byte[] getUserIcon(String username);
	
	/**
	 * 更新用户头像
	 * @param username
	 * @param icon
	 * @return
	 */
	public boolean modifyUserIcon(String username, byte[] icon);
	
	/**
	 * 根据登录名删除用户
	 * @param username
	 */
	public void deleteUserByUsername(String username);
	
	/**
	 * 根据用户id删除用户
	 * @param id
	 */
	public void deleteUserById(String id);
	
	/**
	 * 保存用户
	 * @param depart
	 * @return
	 */
	public DepartmentBean saveDepartment(DepartmentBean depart);
	
	/**
	 * 取指定的部门
	 * @param id
	 * @return
	 */
	public DepartmentBean findDepartById(String id);
	
	/**
	 * 获取指定用户所在的部门
	 * @param username 用户名
	 * @return
	 */
	public List<DepartmentBean> findDepartByUsername(String username);
	
	/**
	 * 获取子节点
	 * @param customerId
	 * @param parentId
	 * @return
	 */
	public List<DepartmentBean> findDepartByParentId(String customerId, String parentId);
	
	/**
	 * 部门ID删除
	 * @param id
	 */
	public void deleteDepartmentById(String id);
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	public RoleBean saveRole(RoleBean role);
	
	/**
	 * 保存角色所属的用户
	 * @param role
	 * @return
	 */
	public boolean saveUserToRole(String roleId, String[] userIds);
	
	/**
	 * 保存用户所属的角色
	 * @param username
	 * @param roleIds
	 * @return
	 */
	public boolean saveRoleToUser(String username, String[] roleIds);
	
	/**
	 * 删除角色
	 * @param id
	 */
	public void deleteRoleById(String id);
	
	/**
	 * 分页取角色
	 * @return
	 */
	public FundPage<RoleBean> findRoleAll(String customerId, Pageable page);
	
	
	/**
	 * 取角色
	 * @param id
	 * @return
	 */
	public RoleBean findRleById(String id);
	
	/**
	 * 保存菜单
	 * @param meun
	 * @return
	 */
	public MenuBean saveMenu(MenuBean meun);
	
	/**
	 * 取菜单
	 * @param id
	 * @return
	 */
	public MenuBean findMeunById(String id);
	
	/**
	 * 删除菜单项
	 * @param id
	 */
	public void deleteMenuById(String id);
	
	/**
	 * 取下一级菜单
	 * @param customerId
	 * @param pid
	 * @return
	 */
	public List<MenuBean> findMenuByParentId(String customerId, String pid);
	
	/**
	 * 取用户的菜单
	 * @param customerId 客户编号
	 * @param roleIds 用户的角色id
	 * @return
	 */
	public List<MenuBean> findUserMenuAll(String customerId, Set<String>roleIds);
	
	/**
	 * 保存资源
	 * @param res
	 * @return
	 */
	public ResourcesBean saveResources(ResourcesBean res);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteResourcesById(String id);
	
	/**
	 * 取指定资源
	 * @param id
	 * @return
	 */
	public ResourcesBean findResourcesById(String id);
	
	/**
	 * 分页获取资源
	 * @param res
	 * @param page
	 * @return
	 */
	public FundPage<ResourcesBean> findResourcesAll(String customerId, Pageable page);
	
	
	/**
	 * 保存资源组
	 * @param resg
	 * @return
	 */
	public boolean saveResGroup(ResGroupBean resg);
	
	/**
	 * 删除资源组
	 * @param id
	 */
	public void deleteResGroupById(String id);
	
	/**
	 * 取指定资源组
	 * @param id
	 * @return
	 */
	public ResGroupBean findResGroupById(String id);
	
	/**
	 * 取资源组
	 * @param customerId
	 * @param page
	 * @return
	 */
	public FundPage<ResGroupBean> findResGroupAll(String customerId, Pageable page);
	
	
	/**
	 * 保存登录日志
	 * @param log
	 * @return
	 */
	public boolean saveLoginLog(LoginLogBean log);
	
	/**
	 * 删除登录日志
	 * @param id
	 */
	public void deleteLoginLogById(String id);
	
	/**
	 * 删除指定日期以前的日志
	 * @param time
	 */
	public void deleteLoginLogByTime(Date time);
	
	/**
	 * 查询指定用户的登录日志
	 * @param username
	 * @param top 取最近的条数
	 * @return
	 */
	public List<LoginLogBean> findLoginLogByUsername(String username, int top);
	
	/**
	 * 查指定时间的登录日志
	 * @param stime
	 * @param etime
	 * @param page
	 * @return
	 */
	public FundPage<LoginLogBean> findLoginLogAll(String customerId, Date stime, Date etime, Pageable page);
	
	/**
	 * 将用户添加到部门中
	 * @param accountId
	 * @param departId
	 * @return
	 */
	public boolean addUserToDepartment(String accountId, String departId);
	
	/**
	 * 删除部门里的用户
	 * @param id
	 */
	public void deleteDepartmentInUser(String accountid,String departid);
	
	/**
	 * 删除部门里的用户
	 * @param departId
	 * @return
	 */
	public Long deleteUserInDeaprtByDepartId(String departId);
	
	/**
	 * 取部门所属用户
	 * @param deaprtmentId
	 * @return
	 */
	public List<UserBean> findDepartmentInUser(String deaprtmentId);
	
	/**
	 * 保存为用户或角色设置的权限
	 * @param list
	 * @return
	 */
	public boolean savePermission(List<PermissionBean> list);
	
	/**
	 * 删除角色的权限
	 * @param roleId
	 * @return
	 */
	public Long deletePermissionByRoleId(String roleId);
	
	/**
	 * 删除资源相关权限
	 * @param resId
	 * @return
	 */
	public long deletePermissionByResId(String resId);
	
	/**
	 * 获取角色的权限
	 * @param roleId
	 * @return
	 */
	public List<PermissionBean> findPermissionByRoleId(String roleId);
	
	/**
	 * 取用户所拥有的权限
	 * @param username 用户的登录名
	 * @return
	 */
	public Set<String> getUserPermission(String username);
	
	/**
	 * 取用户所属角色
	 * @param username
	 * @return
	 */
	public Set<RoleBean> getUserRole(String username);
	
	/**
	 * 保存数据字典
	 * @param bean
	 * @return
	 */
	public boolean saveDict(DictBean bean);
	
	/**
	 * 删除数据字典
	 * @param id
	 */
	public void deleteDict(int id);
	
	/**
	 * 分页取所有的数据字典
	 * @param page
	 * @return
	 */
	public FundPage<DictBean> findDictAll(String customerId, Pageable page);
	
	/**
	 * 按分类取所有的数据字典
	 * @param category
	 * @return
	 */
	public List<DictBean> findDictByCategory(String customerId, String category);
	
	/**
	 * 取指定的数据字典
	 * @param id
	 * @return
	 */
	public DictBean findDictById(int id);
	
	/**
	 * 根据企业id获取企业
	 * @param customerId
	 * @return
	 */
	public CompanyBean findCompanyByCustomerId(String customerId);
	
	/**
	 * 根据账户id更新企业id
	 * @param accountId
	 * @param customerId
	 */
	public void updateCustomerIdByAccountId(String accountId,String customerId);
	
	/**
	 * 根据部门ID和角色ID取用户
	 * @param departId 部门ID
	 * @param roleId 角色ID
	 * @return
	 */
	public List<UserBean> getUserByDepartIdAndRoleId(String departId, String roleId);
	
	/**
	 * 根据id获取企业
	 * @param id
	 * @return
	 */
	public CompanyBean findCompanyById(String id);
	
	/**
	 * 更新customerId
	 * @param customerId
	 * @param companyId
	 */
	public void updateCustomerIdByCompanyId(String customerId,String companyId);
	
}