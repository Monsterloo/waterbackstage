package com.hhh.fund.waterwx.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhh.fund.usercenter.Display;
import com.hhh.fund.usercenter.ResourcesType;
import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.dao.AccountDao;
import com.hhh.fund.usercenter.dao.CompanyDao;
import com.hhh.fund.usercenter.dao.DepartmentDao;
import com.hhh.fund.usercenter.dao.DictionaryDao;
import com.hhh.fund.usercenter.dao.LoginLogDao;
import com.hhh.fund.usercenter.dao.MenuDao;
import com.hhh.fund.usercenter.dao.PermissionDao;
import com.hhh.fund.usercenter.dao.ResGroupDao;
import com.hhh.fund.usercenter.dao.ResourcesDao;
import com.hhh.fund.usercenter.dao.RoleDao;
import com.hhh.fund.usercenter.dao.UserOfDepartmentDao;
import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.usercenter.entity.Company;
import com.hhh.fund.usercenter.entity.Department;
import com.hhh.fund.usercenter.entity.Dictionary;
import com.hhh.fund.usercenter.entity.LoginLog;
import com.hhh.fund.usercenter.entity.Menu;
import com.hhh.fund.usercenter.entity.Permission;
import com.hhh.fund.usercenter.entity.ResGroup;
import com.hhh.fund.usercenter.entity.Resources;
import com.hhh.fund.usercenter.entity.Role;
import com.hhh.fund.usercenter.entity.UserInfo;
import com.hhh.fund.usercenter.entity.UserOfDepartment;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
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
import com.hhh.security.util.EncryptHelper;

@Service
@Transactional
public class SwjUserCenterServiceImpl implements SwjUserCenterService {
	
	@Autowired
	private DepartmentDao departDao;
	
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private LoginLogDao logDao;
	
	@Autowired
	private PermissionDao permissDao;
	
	@Autowired
	private ResourcesDao resDao;
	
	@Autowired
	private ResGroupDao resgroupDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserOfDepartmentDao udDao;
	
	@Autowired
	private DictionaryDao dictDao;
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public UserBean saveUser(UserBean userbean) {
		Account account = null;
		UserInfo uinfo = null;
		Company company = null;
		if(null == userbean.getUserId() || userbean.getUserId().isEmpty()){
			account = new Account();
			account.setSalt(EncryptHelper.randomNumberSalt());
			account.setPassword(EncryptHelper.entrypt(userbean.getPassword(), account.getSalt()));
			uinfo = new UserInfo();
			company = new Company();
		}else{
			account = accountDao.findOne(userbean.getUserId());
			uinfo = account.getUinfo();
			company = account.getCompany();
		}
		account.setCreatetime(new Date());
		account.setId(userbean.getUserId());
		account.setLoginName(userbean.getUsername());
		account.setCompany(company);
		account.setEmail(userbean.getEmail());
		account.setIsAdmin(userbean.isAdmin()?Whether.Yes:Whether.No);
		account.setName(userbean.getDisplayName());
		account.setPhone(userbean.getPhone());
		account.setState(userbean.isLocked()?State.Locked:State.Enable);
		
		uinfo.setAddress(userbean.getAddress());
		uinfo.setPosition(userbean.getPosition());
		Date d = StringUtil.parstDate(userbean.getBirthday());
		if( d != null)
			uinfo.setBirthday(d);
		uinfo.setGender(userbean.getGender());
		account.setUinfo(uinfo);
		if(userbean.getCompany() == null)
			account.setCompany(null);
		else{
			BeanUtils.copyProperties(userbean.getCompany(), company);
			account.setCompany(company);
		}
		account = accountDao.save(account);
		userbean.setUserId(account.getId());
		return userbean;
	}
	
	@Override
	public boolean modifyPassword(String username, String password){
		Account account = accountDao.findByLoginName(username);
		account.setSalt(EncryptHelper.randomNumberSalt());
		account.setPassword(EncryptHelper.entrypt(password, account.getSalt()));
		accountDao.save(account);
		return true;
	}
	
	@Override
	public boolean lockUser(String username) {
		Account account = accountDao.findByLoginName(username);
		account.setState(State.Locked);
		accountDao.save(account);
		return true;
	}

	@Override
	public boolean unlockingUser(String username) {
		Account account = accountDao.findByLoginName(username);
		account.setState(State.Enable);
		accountDao.save(account);
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public UserBean findByUsername(String username) {
		Account account = accountDao.findByLoginName(username);
		if(account == null)
			return null;
		UserBean user = new UserBean();
		user.Converter(account, false);
		return user;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserBean findUserByKeyId(String keyId){
		Account account = accountDao.findByKeyId(keyId);
		if(account == null)
			return null;
		UserBean user = new UserBean();
		user.Converter(account, false);
		return user;
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<UserBean> findUserAll(UserBean user, Pageable page) {
		Page<Account> alist = null;
		if(user == null)
			 return null;
		else
			 alist = accountDao.findAll(getAccountSpec(user), page);
		List<UserBean> accounts = new ArrayList<>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(Account a : alist.getContent()){
				UserBean bean = new UserBean();
				bean.Converter(a, true);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<UserBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}

	@Transactional(readOnly=true)
	private Specification<Account> getAccountSpec(final UserBean user){
		return new Specification<Account>() {

			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != user.getDisplayName() && user.getDisplayName().length() != 0){
					list.add(cb.like(root.get("name").as(String.class), "%"+user.getDisplayName().trim()+"%"));
				}
				if(null != user.getCustomerId() && !user.getCustomerId().isEmpty()){
					list.add(cb.equal(root.get("customerId").as(String.class), user.getCustomerId()));
				}
				if(null != user.getCreatetime()){
					list.add(cb.greaterThanOrEqualTo(root.get("createtime").as(Date.class), StringUtil.parstDate(user.getCreatetime())));
				}
				if(list.isEmpty()){
					query.orderBy(cb.desc(root.get("createtime").as(Date.class)));
					return query.getRestriction();
					//return null;
				}else{
					query.where(cb.and(list.toArray(new Predicate[list.size()])));
					query.orderBy(cb.asc(root.get("createtime").as(Date.class)));
					
					return query.getRestriction();
				}
				
			}
		};
	}
	
	@Override
	@Transactional(readOnly=true)
	public byte[] getUserIcon(String username) {
		Account account = accountDao.findByLoginName(username);
		return account.getUinfo().getIcon();
	}

	@Override
	public boolean modifyUserIcon(String username, byte[] icon) {
		Account account = accountDao.findByLoginName(username);
		account.getUinfo().setIcon(icon);
		accountDao.save(account);
		return true;
	}


	@Override
	public void deleteUserByUsername(String username) {
		Account entity = accountDao.findByLoginName(username);
		udDao.deleteByAccountId(entity.getId());
		accountDao.delete(entity);
	}

	@Override
	public DepartmentBean saveDepartment(DepartmentBean depart) {
		Department entity = new Department();
		entity.setChild(depart.isChild()?Whether.Yes : Whether.No);
		entity.setCustomerId(depart.getCustomerId());
		entity.setId(depart.getId());
		entity.setName(depart.getName());
		entity.setParentId(depart.getParentId());
		Department pdepart = departDao.findOne(depart.getParentId());
		if(pdepart != null){
			pdepart.setChild(Whether.Yes);
			departDao.save(pdepart);
			if(pdepart.getPath() == null || "".equals(pdepart.getPath()))
				entity.setPath(entity.getParentId());
			else{
				entity.setPath(pdepart.getPath() +"/"+entity.getParentId());
			}
		}
		entity = departDao.save(entity);
		depart.setId(entity.getId());
		return depart;
	}

	@Override
	@Transactional(readOnly=true)
	public DepartmentBean findDepartById(String id) {
		DepartmentBean bean = new DepartmentBean();
		Department depart = departDao.findOne(id);
		if(depart == null)
			return null;
		bean.Converter(depart);
		return bean;
	}

	@Override
	@Transactional(readOnly=true)
	public List<DepartmentBean> findDepartByParentId(final String customerId, final String parentId) {
		Specification<Department> spec = new Specification<Department>() {

			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				if(null != parentId && parentId.length() != 0){
					predicates.add(cb.equal(root.get("parentId").as(String.class), parentId));
				}
				if(predicates.isEmpty())
					return null;
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		List<Department> list = departDao.findAll(spec);
		
		List<DepartmentBean> beans = new ArrayList<>();
		if(null != list){
			for(Department d : list){
				DepartmentBean bean = new DepartmentBean();
				bean.Converter(d);
				beans.add(bean);
			}
		}else {
			return null;
		}
		return beans;
	}

	@Override
	public void deleteDepartmentById(String id) {
		departDao.delete(id);
	}

	@Override
	public RoleBean saveRole(RoleBean role) {
		Role r = null;
		if(role.getId() == null || role.getId().isEmpty()){
			r = new Role();
		}else{
			r = roleDao.findOne(role.getId());
		}
		r.setCustomerId(role.getCustomerId());
		r.setDesp(role.getDesp());
		r.setId(role.getId());
		r.setName(role.getName());
		r = roleDao.save(r);
		role.setId(r.getId());
		return role;
	}

	public boolean saveUserToRole(String roleId, String[] userIds){
		if(roleId == null || roleId.isEmpty() || 
				userIds == null || userIds.length == 0){
			return false;
		}else{
			Role role = roleDao.findOne(roleId);
			Set<Account> users = new HashSet<Account>(); 
			for(String id : userIds){
				Account a = new Account();
				a.setId(id);
				users.add(a);
			}
			role.setUsers(users);
			roleDao.save(role);
		}
		return true;
	}
	
	@Override
	public void deleteRoleById(String id) {
		permissDao.deleteByRoleId(id);
		roleDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<RoleBean> findRoleAll(final String customerId, Pageable page) {
		Specification<Role> spec = new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				if( predicates.isEmpty()){
					return null;
				}
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		Page<Role> p = roleDao.findAll(spec, page);
		List<RoleBean> list = new ArrayList<RoleBean>();
		if(p.getContent() != null){
			for(Role r : p.getContent()){
				RoleBean bean = new RoleBean();
				bean.Converter(r, false);
				list.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<RoleBean>(p.getTotalPages(), p.getTotalElements(), list);
	}

	@Override
	@Transactional(readOnly=true)
	public RoleBean findRleById(String id) {
		Role r = roleDao.findOne(id);
		if(r == null)
			return null;
		RoleBean bean = new RoleBean();
		bean.Converter(r, true);
		return bean;
	}

	@Override
	public MenuBean saveMenu(MenuBean menu) {
		Menu m = null;
		if (StringUtils.hasLength(menu.getId())) {
			m = menuDao.findOne(menu.getId());
			m.setName(menu.getName());
			m.setUrl(menu.getUrl());
			m.setDisplay(menu.isDisplay() ? Display.Show : Display.Hide);
			m.setOrder(menu.getOrder());
			m.setIcon(menu.getIcon());
			m.setStyle(menu.getStyle());
		} else {
			m = new Menu();
			m.setCustomerId(menu.getCustomerId());
			m.setDisplay(menu.isDisplay() ? Display.Show : Display.Hide);
			m.setGroup(menu.getGroup());
			m.setHasChild(menu.isChild() ? Whether.Yes : Whether.No);
			m.setId(menu.getId());
			m.setName(menu.getName());
			m.setOrder(menu.getOrder());
			m.setParentId(menu.getParentId());
			m.setIcon(menu.getIcon());
			m.setStyle(menu.getStyle());
			m.setUrl(menu.getUrl());
			if (null == menu.getParentId() || "".equals(menu.getParentId())) {
				m.setLevel(1);
			} else {
				Menu pm = menuDao.findOne(menu.getParentId());
				if (pm == null) {
					m.setLevel(1);
				} else {
					m.setLevel(pm.getLevel() + 1);
					pm.setHasChild(Whether.Yes);
					menuDao.save(pm);

					if (pm.getPath() == null || "".equals(pm.getPath()))
						m.setPath(m.getParentId());
					else {
						m.setPath(pm.getPath() + "/" + m.getParentId());
					}
				}
			}
		}
		m = menuDao.save(m);
		menu.setId(m.getId());
		return menu;
	}

	@Override
	@Transactional(readOnly=true)
	public MenuBean findMeunById(String id) {
		Menu m = menuDao.findOne(id);
		MenuBean bean = new MenuBean();
		if(m == null)
			return null;
		bean.Converter(m);
		return bean;
	}

	@Override
	public void deleteMenuById(String id) {
		permissDao.deleteByResId(id);
		menuDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<MenuBean> findMenuByParentId(final String customerId, final String pid) {
		Specification<Menu> spec = new Specification<Menu>() {
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				if(null != pid && pid.length() != 0){
					predicates.add(cb.equal(root.get("parentId").as(String.class), pid));
				}else{
					predicates.add(cb.isNull(root.get("parentId").as(String.class)));
				}
				if(!predicates.isEmpty())
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				//query.groupBy(root.get("group").as(Integer.class));
				query.orderBy(cb.asc(root.get("order").as(Integer.class)));
				return query.getRestriction();
			}
		};
		List<Menu> mlist = menuDao.findAll(spec);
		List<MenuBean> list = new ArrayList<MenuBean>();
		if(null != mlist){
			for(Menu m : mlist){
				MenuBean bean = new MenuBean();
				bean.Converter(m);
				list.add(bean);
			}
		}else{
			return null;
		}
		return list;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MenuBean> findUserMenuAll(final String customerId, Set<String>roleIds){
		List<Permission> plist = permissDao.findByRoleIdIn(roleIds);
		List<String> menuIds = new ArrayList<>();
		if(plist != null){
			for(Permission per : plist){
				if(per.getResType().ordinal() == ResourcesType.Menu.ordinal()){
					menuIds.add(per.getResId());
				}
			}
		}
		final Object []mids = menuIds.toArray();
		Specification<Menu> spec = new Specification<Menu>() {
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}else{
					predicates.add(cb.isNull(root.get("customerId").as(String.class)));
				}
				if(null != mids && mids.length != 0){
					predicates.add(cb.and(root.get("id").as(String.class).in(mids)));
				}
				predicates.add(cb.equal(root.get("display").as(Display.class), Display.Show));
				
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				//query.groupBy(root.get("parentId").as(String.class),root.get("group").as(Integer.class));
				query.orderBy(cb.desc(root.get("level").as(Integer.class)), cb.desc(root.get("order").as(Integer.class)));
				return query.getRestriction();
			}
		};
		List<Menu> mlist = menuDao.findAll(spec);
		List<MenuBean> set = null;
		if(null != mlist && !mlist.isEmpty()){
			Map<String, List<MenuBean>> map = new HashMap<>();
			for(Menu m : mlist){
				MenuBean bean = new MenuBean();
				bean.Converter(m);
				set = map.get(bean.getParentId());
				if(set == null){
					set = new ArrayList<>();
					map.put(bean.getParentId(), set);
				}
				set.add(bean);
			}
			set = map.get("0");
			Collections.sort(set, new Comparator<MenuBean>() {
				@Override
				public int compare(MenuBean o1, MenuBean o2) {
					return Integer.valueOf(o1.getOrder()).compareTo(o2.getOrder());
				}
			});
			for(MenuBean mb : set){
				usermenu(mb, map);
			}
		}else{
			return null;
		}
		return set;
	}
	
	private void usermenu(MenuBean mb, Map<String, List<MenuBean>> map){
		List<MenuBean> set = map.get(mb.getId());
		if(set == null)
			return;
		for(MenuBean bean : set){
			if(bean.isChild())
				usermenu(bean, map);
		}
		Collections.sort(set, new Comparator<MenuBean>() {
			@Override
			public int compare(MenuBean o1, MenuBean o2) {
				return Integer.valueOf(o1.getOrder()).compareTo(o2.getOrder());
			}
		});
		mb.setSubMenu(set);
	}
	
	@Override
	public ResourcesBean saveResources(ResourcesBean bean) {
		Resources res = new Resources();
		res.setCode(bean.getCode());
		res.setCustomerId(bean.getCustomerId());
		res.setId(bean.getId());
		res.setEnable(bean.getEnable()?State.Enable : State.Disenable);
		res.setName(bean.getName());
		res.setPermission(bean.getPermission());
		res.setResobj(bean.getResobj());
		res = resDao.save(res);
		bean.setId(res.getId());
		return bean;
	}

	@Override
	public void deleteResourcesById(String id) {
		permissDao.deleteByResId(id);
		resDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public ResourcesBean findResourcesById(String id) {
		Resources res = resDao.findOne(id);
		if(res == null)
			return null;
		ResourcesBean bean = new ResourcesBean();
		bean.Converter(res);
		return bean;
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<ResourcesBean> findResourcesAll(final String customerId, Pageable page) {
		Specification<Resources> spec = new Specification<Resources>() {
			@Override
			public Predicate toPredicate(Root<Resources> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		Page<Resources> p = resDao.findAll(spec, page);
		List<ResourcesBean> list = new ArrayList<ResourcesBean>();
		if(p.getContent() != null){
			for(Resources r : p.getContent()){
				ResourcesBean bean = new ResourcesBean();
				bean.Converter(r);
				list.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<ResourcesBean>(p.getTotalPages(), p.getTotalElements(), list);
	}

	@Override
	public boolean saveResGroup(ResGroupBean resg) {
		ResGroup res = new ResGroup();
		res.setCustomerId(resg.getCustomerId());
		res.setDesp(resg.getDesp());
		res.setId(resg.getId());
		res.setrGroupJoin(resg.getrGroupJoin());
		resgroupDao.save(res);
		return true;
	}

	@Override
	public void deleteResGroupById(String id) {
		resgroupDao.delete(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ResGroupBean findResGroupById(String id) {
		ResGroup resg = resgroupDao.findOne(id);
		if(resg == null)
			return null;
		ResGroupBean bean = new ResGroupBean();
		bean.Converter(resg);
		return bean;
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<ResGroupBean> findResGroupAll(final String customerId, Pageable page) {
		Specification<ResGroup> spec = new Specification<ResGroup>() {
			@Override
			public Predicate toPredicate(Root<ResGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				query.orderBy(cb.desc(root.get("loginTime").as(Date.class)));
				return query.getRestriction();
			}
		};
		Page<ResGroup> p = resgroupDao.findAll(spec, page);
		List<ResGroupBean> list = new ArrayList<ResGroupBean>();
		if(null != p.getContent()){
			for(ResGroup r : p.getContent()){
				ResGroupBean bean = new ResGroupBean();
				bean.Converter(r);
				list.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<ResGroupBean>(p.getTotalPages(), p.getTotalElements(), list);
	}

	@Override
	public boolean saveLoginLog(LoginLogBean log) {
		LoginLog l = new LoginLog();
		l.setCustomerId(log.getCustomerId());
		l.setId(log.getId());
		l.setIp(log.getIp());
		l.setLoginName(log.getLoginName());
		l.setLoginTime(StringUtil.parstDate(log.getLoginTime()));
		
		l = logDao.save(l);
		return true;
	}

	@Override
	public void deleteLoginLogById(String id) {
		logDao.delete(id);		
	}

	@Override
	public void deleteLoginLogByTime(Date time) {
		logDao.deleteByLoginTime(time);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LoginLogBean> findLoginLogByUsername(final String username, int top) {
		if(null != username && !username.isEmpty()){
			return null;
		}
		Specification<LoginLog> spec = new Specification<LoginLog>() {
			@Override
			public Predicate toPredicate(Root<LoginLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.get("loginName").as(String.class), username));
				
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				query.orderBy(cb.desc(root.get("loginTime").as(Date.class)));
				return query.getRestriction();
			}
		};
		Page<LoginLog> p = logDao.findAll(spec, new PageRequest(0, top));
		List<LoginLogBean> list = new ArrayList<>();
		if(null != p.getContent()){
			for(LoginLog log : p.getContent()){
				LoginLogBean bean = new LoginLogBean();
				bean.Converter(log);
				list.add(bean);
			}
		}else{
			return null;
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<LoginLogBean> findLoginLogAll(final String customerId, final Date stime,final Date etime, Pageable page) {
		Specification<LoginLog> spec = new Specification<LoginLog>() {
			@Override
			public Predicate toPredicate(Root<LoginLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				if(null != stime){
					predicates.add(cb.greaterThanOrEqualTo(root.get("loginTime").as(Date.class), stime));
				}
				if(null != etime){
					predicates.add(cb.lessThanOrEqualTo(root.get("loginTime").as(Date.class), etime));
				}
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				query.orderBy(cb.desc(root.get("loginTime").as(Date.class)));
				return query.getRestriction();
			}
		};
		Page<LoginLog> p = logDao.findAll(spec, page);
		List<LoginLogBean> list = new ArrayList<>();
		if(null != p.getContent()){
			for(LoginLog log : p.getContent()){
				LoginLogBean bean = new LoginLogBean();
				bean.Converter(log);
				list.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<LoginLogBean>(p.getTotalPages(), p.getTotalElements(), list);
	}

	@Override
	public boolean addUserToDepartment(String accountId, String departId) {
		UserOfDepartment ud = new UserOfDepartment();
		ud.setDepartId(departId);
		ud.setAccountId(accountId);
		udDao.save(ud);
		return true;
	}

	@Override
	public void deleteDepartmentInUser(String accountid,String departid) {
		udDao.deleteByAccountIdAndDepartId(accountid, departid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserBean> findDepartmentInUser(String deaprtmentId) {
		List<Account> alist = accountDao.findByDepartmentInUser(deaprtmentId);
		List<UserBean> list = new ArrayList<>();
		if(null != alist && !alist.isEmpty()){
			for(Account a : alist){
				UserBean bean = new UserBean();
				bean.Converter(a, true);
				list.add(bean);
			}
		}else{
			return null;
		}
		return list;
	}

	@Override
	public boolean savePermission(List<PermissionBean> list) {
		List<Permission> plist = new ArrayList<>();
		if(null != list){
			for(PermissionBean pb : list){
				Permission p = new Permission();
				p.setEnable(pb.getEnable());
				p.setResId(pb.getResId());
				p.setResType(pb.getResType());
				p.setRoleId(pb.getRoleId());
				p.setUserId(pb.getUserId());
				plist.add(p);
			}
		}
		permissDao.save(plist);
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<String> getUserPermission(String username) {
		Set<RoleBean> roleSet = getUserRole(username);
		Set<String> sb = new HashSet<>();
		if(roleSet == null || roleSet.isEmpty()){
			return null;
		}
		for(RoleBean role : roleSet){
			sb.add(role.getId());
		}
		List<Resources> list = resDao.findByRoleIds(sb);
		sb.clear();
		for(Resources p : list){
			sb.add(p.getResobj());
		}
		return sb;
	}

	@Override
	public boolean saveDict(DictBean bean) {
		Dictionary dict = new Dictionary();
		dict.setId(bean.getId());
		dict.setCode(bean.getCode());
		dict.setName(bean.getName());
		dict.setParent(bean.getParent());
		dict.setState(bean.getStatus());
		dict.setCategory(bean.getCategory());
		dictDao.save(dict);
		return true;
	}

	@Override
	public void deleteDict(int id) {
		dictDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public FundPage<DictBean> findDictAll(final String customerId, Pageable page) {
		Specification<Dictionary> spec = new Specification<Dictionary>() {
			@Override
			public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				if(predicates.isEmpty())
					return null;
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		Page<Dictionary> p = dictDao.findAll(spec, page);
		List<DictBean> list = new ArrayList<>();
		if(null != p.getContent()){
			for(Dictionary d : p.getContent()){
				DictBean bean = new DictBean();
				bean.Converter(d);
				list.add(bean);
			}
		}else{
			return null;
		}
		return new FundPage<DictBean>(p.getTotalPages(), p.getTotalElements(), list);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DictBean> findDictByCategory(final String customerId, final String category) {
		Specification<Dictionary> spec = new Specification<Dictionary>() {
			@Override
			public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != customerId && !customerId.isEmpty()){
					predicates.add(cb.equal(root.get("customerId").as(String.class), customerId));
				}
				predicates.add(cb.equal(root.get("category").as(String.class), category));
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		List<Dictionary> p = dictDao.findAll(spec);
		List<DictBean> list = new ArrayList<>();
		if(null != p){
			for(Dictionary d : p){
				DictBean bean = new DictBean();
				bean.Converter(d);
				list.add(bean);
			}
		}else{
			return null;
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public DictBean findDictById(int id) {
		DictBean bean = new DictBean();
		Dictionary d = dictDao.findOne(id);
		if(d == null)
			return null;
		bean.Converter(d);
		return bean;
	}

	@Override
	public void deleteUserById(String id) {
		udDao.deleteByAccountId(id);
		accountDao.delete(id);
	}

	@Override
	public Long deletePermissionByRoleId(String roleId) {
		return permissDao.deleteByRoleId(roleId);
	}

	@Override
	public long deletePermissionByResId(String resId) {
		return permissDao.deleteByResId(resId);
	}

	@Override
	public List<PermissionBean> findPermissionByRoleId(String roleId) {
		List<PermissionBean> list = new ArrayList<>();
		List<Permission> plist = permissDao.findByRoleId(roleId);
		if(plist != null && !plist.isEmpty()){
			for(Permission p : plist){
				PermissionBean bean = new PermissionBean();
				bean.Converter(p);
				list.add(bean);
			}
		}
		return list;
	}

	@Override
	public Set<RoleBean> getUserRole(String username) {
		Account a = accountDao.findByLoginName(username);
		Set<RoleBean> beans = new HashSet<>();
		if(a.getRoles() != null){
			for(Role r : a.getRoles()){
				RoleBean bean = new RoleBean();
				bean.Converter(r, false);
				beans.add(bean);
			}
		}
		return beans;
	}

	@Override
	public boolean saveRoleToUser(String username, String[] roleIds) {
		Set<Role> set = new HashSet<>();
		if(roleIds != null && roleIds.length != 0){
			for(String id : roleIds){
				Role role = new Role();
				role.setId(id);
				set.add(role);
			}
		}
		Account a = accountDao.findByLoginName(username);
		a.setRoles(set);
		accountDao.save(a);
		return true;
	}

	@Override
	public Long deleteUserInDeaprtByDepartId(String departId) {
		return udDao.deleteByDepartId(departId);
	}

	@Override
	public  List<DepartmentBean> findDepartByUsername(String username) {
		Account a = accountDao.findByLoginName(username);
		List<Department> list = departDao.findByAccountId(a.getId());
		List<DepartmentBean> beans = new ArrayList<>();
		if(list != null && !list.isEmpty()){
			for(Department d : list){
				DepartmentBean bean = new DepartmentBean();
				bean.Converter(d);
				beans.add(bean);
			}
		}else{
			return null;
		}
		return beans;
	}

	@Override
	public CompanyBean findCompanyByCustomerId(String customerId) {
		Company company = companyDao.findByCustomerId(customerId);
		CompanyBean companyBean = new CompanyBean();
		companyBean.convert(company);
		return companyBean;
	}

	@Override
	public void updateCustomerIdByAccountId(String accountId,String customerId) {
		accountDao.updateCustomerIdByAccountId(accountId, customerId);
	}

	@Override
	public List<UserBean> getUserByDepartIdAndRoleId(String departId, String roleId) {
		List<Account> accounts = accountDao.findByDepartmentInUser(departId);
		List<UserBean> list = new ArrayList<>();
		for(Account a : accounts){
			for(Role role: a.getRoles()){
				if(role.getId().equals(roleId)){
					UserBean bean = new UserBean();
					bean.Converter(a, true);
					list.add(bean);
					break;
				}
			}
		}
		return list;
	}

	@Override
	public CompanyBean findCompanyById(String id) {
		Company company = companyDao.findOne(id);
		CompanyBean companyBean = new CompanyBean();
		companyBean.convert(company);
		return companyBean;
	}

	@Override
	public void updateCustomerIdByCompanyId(String customerId, String companyId) {
		companyDao.updateCustomerId(customerId, companyId);
	}
	
}
