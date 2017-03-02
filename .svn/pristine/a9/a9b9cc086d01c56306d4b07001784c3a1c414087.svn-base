package com.hhh.fund.waterwx.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.dao.AccountDao;
import com.hhh.fund.usercenter.dao.RoleDao;
import com.hhh.fund.usercenter.entity.Account;
import com.hhh.fund.usercenter.entity.Role;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.dao.SwjUcenterRoleDao;
import com.hhh.fund.waterwx.dao.SwjUserDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.entity.SysUcenterRole;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.service.SwjUserService;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.webmodel.PhotoListBean;
import com.hhh.fund.waterwx.webmodel.PictureUrlBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjUserBean;
import com.hhh.fund.waterwx.webmodel.SysUcenterRoleBean;
import com.hhh.fund.waterwx.webmodel.ToFrontWeiXinUserBean;
import com.hhh.fund.web.model.DepartmentBean;
import com.hhh.fund.web.model.DisplayField;
import com.hhh.fund.web.model.UserBean;
import com.hhh.weixin.company.service.UserManagement;
import com.hhh.weixin.util.CommonUtil;
@Component
@Transactional
public class SwjUserServiceImpl implements SwjUserService {
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
	private AccountDao accountDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ResponsibilityService responsibilityService;
	
	@Autowired
	private SwjUcenterRoleDao ucenterRoleDao;
	
	/***
	 * 保存问题类型
	 * */
	public SwjUserBean save(SwjUserBean bean){
		UserBean userBean = new UserBean();
		String id = bean.getId();
		bean.setCreatetime(new Date());
		if(id == null || id.equals("")){
			if(bean.getLoginname()!=null && bean.getPhone()!=null){
				Account account=swjUserDao.findAccountName(bean.getPhone());
				if(account!=null){
					return null;
				}
			}
			SwjUser user = new SwjUser();
			userBean.setUsername(bean.getLoginname());
			userBean.setDisplayName(bean.getName());
			userBean.setPassword(bean.getPassword());
			//account.setSalt(EncryptHelper.randomNumberSalt());
			//account.setPassword(EncryptHelper.entrypt(userbean.getPassword(), account.getSalt()));
			userBean.setEmail(bean.getEmail());
			userBean.setPhone(bean.getPhone());
			//保存到fund用户管理下
			UserBean ubean = ucs.saveUser(userBean);
			String id1 = ubean.getUserId();
			bean.setId(id1);
			BeanUtils.copyProperties(bean, user);
			user.setUserid(id1);
			swjUserDao.save(user);
			String departmentId="2";
			if(StringUtils.isNotBlank(bean.getArea())){
				departmentId=responsibilityService.findPhoneNumByArea("area", (bean.getArea()));
			}
			String token = CommonUtil.getToken();
			UserManagement.Create(token, id1, bean.getName(), departmentId, bean.getJob(), bean.getPhone(), "0", bean.getEmail(), "");
		}else{
			SwjUser type = swjUserDao.findOne(id);
			userBean.setUsername(bean.getLoginname());
			userBean.setDisplayName(bean.getName());
			userBean.setPassword(bean.getPassword());
			//account.setSalt(EncryptHelper.randomNumberSalt());
			//account.setPassword(EncryptHelper.entrypt(userbean.getPassword(), account.getSalt()));
			userBean.setEmail(bean.getEmail());
			userBean.setPhone(bean.getPhone());
			UserBean uu = ucs.saveUser(userBean);
			String userid = uu.getUserId();
			bean.setId(userid);
			BeanUtils.copyProperties(bean, type);
			swjUserDao.save(type);
			//String token = CommonUtil.getToken();
			//UserManagement.Update(accesstoken, userid, name, department, position, mobile, gender, email, weixinid, enable)
		}
		return bean;
	}
	
	/***
	 * 批量更新用户信息
	 * */
	public void updateUserInfo() throws IOException{
		String sql = "select s.* from swj_user s where s.area like '%区%'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> objs = query.getResultList();
		for(Object[] q:objs){
			String userid = (String)q[9];
			System.out.println(userid);
			SysUcenterRole role = ucenterRoleDao.findRoleByUserId(userid);
			if(role != null){
				if (!"areaRole".equals(role.getName())) {
					continue;
					}
			String name =  (String)q[6];
			String job =  (String)q[4];
			String phone =  (String)q[8];
			String email =  (String)q[3];
			String area =  (String)q[1];
			String departmentId="1";
			if(StringUtils.isNotBlank(area)){
				departmentId=responsibilityService.findPhoneNumByArea("area", (area));
			}
			String token = CommonUtil.getToken();
			UserManagement.Update(token, userid, name, departmentId, job, phone, "0", email, "", 1);
			}else{
				continue;
			}
		}
		
	}
	
	
	/**
	 * 删除用户
	 * */
	public void delete(String id){
		String id1 = id.replaceAll("undefined", "");
		String[] ids = id1.split(";");
		System.out.println(ids);
		for(int i=0;i<ids.length;i++){
			SwjUser user = swjUserDao.findByUserid(ids[i]);
			swjUserDao.delete(user);
			ad.delete(ids[i]);
			String token = CommonUtil.getToken();
			UserManagement.Delete(token, ids[i]);
		}
	}
	/**
	 * 查找所有用户
	 * */
	public SmsPage<SwjUserBean> findUserAll(Pageable page) {
		Page<SwjUser> alist = null;
		alist = swjUserDao.findAll(null, page);
		List<SwjUserBean> accounts = new ArrayList<SwjUserBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjUser a : alist.getContent()){
				SwjUserBean bean = new SwjUserBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new SmsPage<SwjUserBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize){
		return (int)Math.floor((double)start/pageSize);
	}
	/**
	 * 查询用户
	 * @param id 用户id
	 * */
	public SwjUserBean findById(String id){
		SwjUser river = swjUserDao.findById(id);
		SwjUserBean bean = new SwjUserBean();
		BeanUtils.copyProperties(river, bean);
		return bean;
	}
	/**
	 * 获取所有的行政区域
	 * */
	public List<SysUcenterDict> findArea(){
		List<SysUcenterDict> list = dictDao.findByParent("area");
		return list;
	}
	/**
	 * 导出list
	 * */
	public List<SwjUserBean> getAllToExport(final String area,final String name){
		List<SwjUser> alist = null;
		Specification<SwjUser> spec = new Specification<SwjUser>() {
			public Predicate toPredicate(Root<SwjUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != area && !area.isEmpty() && !area.equals("请选择")){
					predicates.add(cb.equal(root.get("area").as(String.class), area));
				}
				if(null != area && !area.isEmpty() && !area.equals("请选择")){
					predicates.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
				}
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		alist = swjUserDao.findAll(spec);
		System.out.println(alist.size()+"size111");
		List<SwjUserBean> accounts = new ArrayList<SwjUserBean>();
			for(SwjUser a : alist){
				SwjUserBean bean = new SwjUserBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
			return accounts;
	}
	/**
	 * 导出为csv格式的文件
	 * @param list 导出的数据
	 * @param map  
	 * */
	public boolean exportUser(File file, List<SwjUserBean> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
            	bw.append("用户名,真实姓名,联系方式,邮箱,行政区域,职务\r");
                for(SwjUserBean bean : dataList){
                	if(bean.getLoginname()==null){
                		bw.append("").append(",");
                	}else{
                		bw.append(bean.getLoginname()).append(",");
                	}
                	if(bean.getName()==null){
                		bw.append("").append(",");
                	}else{
                		bw.append(bean.getName()).append(",");
                	}
                	if(bean.getPhone()==null){
                		bw.append("").append(",");
                	}else{
                		bw.append(bean.getPhone()).append(",");
                	}
                	if(bean.getEmail()==null){
                		bw.append("").append(",");
                	}else{
                		bw.append(bean.getEmail()).append(",");
                	}
                	if(bean.getArea()==null){
                		bw.append("").append(",");
                	}else{
                		bw.append(bean.getArea()).append(",");
                	}
                	if(bean.getJob()==null){
                		bw.append("").append("\r");
                	}else{
                		bw.append(bean.getJob()).append("\r");
                	}
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
	/**
	 * 根据userid 查找用户
	 * @param userId 用户id
	 * */
	public SwjUserBean findByUserId(String userId){
		System.out.println("userid"+userId);
		SwjUser user = swjUserDao.findByUserid(userId);
		SwjUserBean bean = new SwjUserBean();
		if(user != null){
			BeanUtils.copyProperties(user, bean);
		}
		return bean;
	}
	/**
	 * 根据username 查找用户
	 * @param username 用户真实姓名
	 * */
	public SwjUserBean findByName(String username){
		SwjUser user = swjUserDao.findByName(username);
		SwjUserBean bean = new SwjUserBean();
		if(user != null){
			BeanUtils.copyProperties(user, bean);
		}
		return bean;
	}
	/**
	 * 根据角色统计用户
	 * 
	 * */
	public Map<String,Integer> findUserByRole(){
		List<Role> list = new ArrayList<Role>();
		Iterator<Role> it = roleDao.findAll().iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(list !=null && !list.isEmpty()){
			for(Role r:list){
				int size = r.getUsers().size();
				map.put(r.getDesp(), size);
			}
		}
		return map;
	}
	
	//区级进入后台查询数据
	@Override
	public FundPage<UserBean> findUserAllByArea(UserBean bean_,String area,Pageable page) {
		
		List<Long> list = swjUserDao.getUserCountByArea(area);
		int cou = list.get(0).intValue();
		System.out.println(cou);
		StringBuffer sql = new StringBuffer();
		sql.append("select u.userid");
		sql.append(" from swj_user u,sys_ucenter_account a ");
		if(!StringUtils.isBlank(bean_.getDisplayName())){
			sql.append(" where u.name=a.`name` and a.name like '%"+bean_.getDisplayName()+"%' and u.area='"+area+"' ORDER BY u.createtime desc ");
		}else{
			sql.append(" where u.name=a.`name` and u.area='"+area+"' ORDER BY u.createtime desc ");
		}
		//nd(" where u.name=a.`name` and u.area='"+area+"' ORDER BY u.createtime desc ");
		
		System.out.println(sql.toString());
        
		Query query = entityManager.createNativeQuery(sql.toString());
		
//		int pageNum = 1;
//		int pageSize = 10;
//		PageRequest p  = new PageRequest(pageNum,pageSize);
		
		
		query.setFirstResult((page.getPageNumber())*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		List<String>objs = query.getResultList();
		
		List<UserBean> userList = new ArrayList<>();
		for(String q:objs){
			Account account = swjUserDao.findAccountById(q);
			UserBean user = new UserBean();
			user.Converter(account, false);
			userList.add(user);
		}
		
		int totle=0;
		if(cou%page.getPageSize()==0){
			totle=cou/page.getPageSize();
		}else{
			totle=cou/page.getPageSize()+1;
		}
		System.out.println(userList);
		return new FundPage<UserBean>(totle, cou, userList);
	}
	
	@Override
	public List<DepartmentBean> findDepartByParentIdAndArea(String customerId,
			String id, String areaName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select d.child,d.customerid,d.id,d.name,d.parentid,d.path ");
		sql.append(" from sys_ucenter_department d ");
		if(StringUtils.isBlank(customerId)){
			sql.append(" where d.parentId='"+id+"' and d.name like '"+areaName+"%' ");
			
		}else{
			sql.append(" where u.customerId='"+customerId+"' and u.parentId='"+id+"' and u.name like '"+areaName+"%' ");

		}
		
		System.out.println(sql.toString());
        
		Query query = entityManager.createNativeQuery(sql.toString());
		
		List<Object[]>objs = query.getResultList();
		
		List<DepartmentBean> departmentList = new ArrayList<>();
		
		for(Object[] q:objs){
			DepartmentBean departmentbean = new DepartmentBean();
			departmentbean.setChild(((Integer)q[0])==0?Whether.Yes:Whether.No);
			departmentbean.setCustomerId((String)q[1]);
			departmentbean.setId((String)q[2]);
			departmentbean.setName((String)q[3]);
			departmentbean.setParentId((String)q[4]);
			departmentList.add(departmentbean);
		}
		return departmentList;
	}
	
	public String getDepartmentByUserId(String userId) {
		String sql = "select d.`name` from swj_user u,sys_ucenter_user_department ud ,";
		sql+=" sys_ucenter_department d where u.userid=ud.accountId and ud.departId = d.id and u.userid=:userId ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("userId", userId);
		
		List<String> objs = query.getResultList();
		
		if(objs.size()!=0){
			return objs.get(0);
		}
		return "";
	}
	
	
	
}
