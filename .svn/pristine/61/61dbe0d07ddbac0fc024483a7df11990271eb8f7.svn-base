package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.ResponsibilityService;
import com.hhh.fund.waterwx.util.StringUtil;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityStatisticsBean;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
@Component
@Transactional
public class ResponsibilityServiceImpl implements ResponsibilityService {
	@Autowired
	private ResponsibilityDao responseDao;
	@Autowired
	private SysUcenterDictDao dictDao;
	@Autowired
	private RiverDao riverDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static final String DIST_MGR_NAME = "dist_mgr_name";
	public static final String STREET_MGR_NAME = "street_mgr_name";
	public static final String VILLAGE_MGR_NAME = "village_mgr_name";
	
	/**
	 * 保存问题类型
	 * */
	public void save(ResponsibilityBean bean,RiverBean riverBean){
		String id = bean.getId();
		if(id == null || id.equals("")){
			Responsibility type = new Responsibility();
			BeanUtils.copyProperties(bean, type);
			responseDao.save(type);
			String riverName = bean.getRiverName();
			String riverId = riverBean.getId();
			List<River> rivers = riverDao.findByRiverName(riverName);
			if(rivers != null && rivers.size()>0){//修改河道信息
				River r = rivers.get(0);
				BeanUtils.copyProperties(riverBean, r);
				riverDao.save(r);
			}else{//新增河道信息
				River river = new River();
				BeanUtils.copyProperties(riverBean, river);
				riverDao.save(river);
			}
		}else{
			Responsibility type = responseDao.findOne(id);
			BeanUtils.copyProperties(bean, type);
			String riverName = bean.getRiverName();
			String riverId = riverBean.getId();
			List<River> rivers = riverDao.findByRiverName(riverName);
			River river = new River();
			if(rivers != null){
				river = rivers.get(0);
			}
			responseDao.save(type);
			BeanUtils.copyProperties(riverBean, river);
			riverDao.save(river);
		}
	}
	/**
	 * 删除问题类型
	 * */
	public void delete(String id){
		String id1 = id.replaceAll("undefined", "");
		String[] ids = id1.split(";");
		System.out.println(ids);
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]+"555555");
			responseDao.delete(ids[i]);
		}
	}
	/**
	 * 查找所有问题
	 * */
	public SmsPage<ResponsibilityBean> findTypeAll(Pageable page) {
		Page<Responsibility> alist = null;
		alist = responseDao.findAll(null, page);
		List<ResponsibilityBean> accounts = new ArrayList<ResponsibilityBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(Responsibility a : alist.getContent()){
				ResponsibilityBean bean = new ResponsibilityBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return new SmsPage<ResponsibilityBean>(0, 0, accounts);
		}
		return new SmsPage<ResponsibilityBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize){
		return (int)Math.floor((double)start/pageSize);
	}
	/**
	 * 查询河道
	 * @param id 河道id
	 * */
	public ResponsibilityBean findById(String id){
		Responsibility resp = responseDao.findOne(id);
		ResponsibilityBean bean = new ResponsibilityBean();
		BeanUtils.copyProperties(resp, bean);
		return bean;
	}
	/**
	 * 查找字典
	 * */
	public List<SysUcenterDict> findDict(String dict){
		
		List<SysUcenterDict> list = dictDao.findByParent(dict);
		System.out.println(list.size()+"444444");
		return list;
	}
	
	/**
	 * 根据地区查询相关信息
	 * 
	 * */
	public List<Responsibility> getReDataByArea(String area) {
		List<Responsibility> list = responseDao.getReDataByArea(area);
		return list;
	}
	
	/**
	 * 根据河段名查询相关信息
	 * 
	 * */
	public List<Responsibility> getReByRiverName(String riverName,String area) {
		List<Responsibility> list = responseDao.getReByRiverName(riverName,area);
		return list;
	}
	
	/**
	 * 根据河段名查询相关信息
	 * 
	 * */
	public String findByParameter(String riverCode, String reachName,String riverBankName){
		if(StringUtils.isBlank(riverCode)&&StringUtils.isBlank(reachName)&&StringUtils.isBlank(riverBankName)){
			return null;
		}
		String villageMgrName = responseDao.findByParameter(riverCode,reachName,riverBankName);
		return villageMgrName;
	}
	
	/**
	 * 根据河道名称 查询河长制
	 * @param rivers 河道名
	 * */
	public SmsPage<ResponsibilityBean> findByRiverNames(String rivers,Pageable page){
		String[] riverArr = rivers.split("\\,");
		Object[] riverObjectArr = null;
		List<Responsibility> list = new ArrayList<Responsibility>();
		ArrayList<String>strArray = new ArrayList<String>();
		for(String str:riverArr){
			if(!StringUtil.isBlank(str)){
				strArray.add(str);
			}
		}
		if(strArray.size()!=0){
			riverObjectArr = (Object[]) strArray.toArray();
			for(int i = 0;i<riverObjectArr.length;i++){
				System.out.println(riverObjectArr[i]);
				List<Responsibility> list1 = responseDao.findByRiverName((String)riverObjectArr[i]);
				System.out.println(list1.size());
				list.addAll(list1);
			}
		}
		
		
		List<ResponsibilityBean> accounts = new ArrayList<ResponsibilityBean>();
		System.out.println(accounts.size());
		if(null != list && !list.isEmpty()){
			for(Responsibility a : list){
				ResponsibilityBean bean = new ResponsibilityBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}
		long size = list.size();
		long pageSize = size/10;
		int p = (int)pageSize+1;
		int number = page.getPageNumber();
		List<ResponsibilityBean> beans = new ArrayList<ResponsibilityBean>();
		int t = number*10;
		int f = t+10;
		if(accounts.size()<10){
			f=accounts.size();
		}
		for(int i=t;i<f;i++){
			beans.add(accounts.get(i));
		}
		return new SmsPage<ResponsibilityBean>(p, size, beans);
	}
	@Override
	public List<Responsibility> findByRiverCode(String riverCode) {
		if(StringUtils.isBlank(riverCode)){
			return null;
		}
		List<Responsibility> list = responseDao.findByRiverCode(riverCode);
		return list;
	}
	
	@Override
	public List<Responsibility> findRiverByRiverCode(String riverCode) {
		if(StringUtils.isBlank(riverCode)){
			return null;
		}
		List<Responsibility> list = responseDao.findRiverByRiverCode(riverCode);
		return list;
	}
	
	@Override
	public List<Responsibility> findByRiverId(String riverId) {
		if(StringUtils.isBlank(riverId)){
			return null;
		}
		List<Responsibility> list = responseDao.findByRiverBank(riverId);
		return list;
	}
	
	/**
	 * 查询行政区域对应的电话
	 * */
	public String findPhoneNumByArea(String parent,String name){
		List<SysUcenterDict> list= dictDao.findPhoneNumByArea(parent,name);
		String phoneNum = list.get(0).getCustomerId();
		return phoneNum;
	}
	@Override
	public void save(ResponsibilityBean bean) {
		
		Responsibility res = new Responsibility();
		BeanUtils.copyProperties(bean, res);
		responseDao.save(res);
	}
	@Override
	public boolean saveList(List beanList) {
		for(Object obj:beanList){
			ResponsibilityBean bean = (ResponsibilityBean)obj;
			Responsibility info = new Responsibility();
			BeanUtils.copyProperties(bean,info);
			responseDao.save(info);
		}
		return true;
	}
	
	@Override
	public ResponsibilityStatisticsBean findStatisticsByArea(String areaName) {
		// TODO Auto-generated method stub
		Map<String,List<String>> statMap = new HashMap<String, List<String>>();
		ResponsibilityStatisticsBean bean = new ResponsibilityStatisticsBean();
		if("".equals(areaName) || areaName == null){
			//全市查找统计
			String [] areas = {"天河区","越秀区","荔湾区","白云区","海珠区","花都区","黄浦区","番禺区","增城区","增城区","从化区","南沙区"};
			for(int i =0;i<areas.length;++i){
				statMap.put(areas[i], getStatList(areas[i]));
			}
			bean.setTotalMap(statMap);
		}else{
			//区级查找统计
			statMap.put(areaName, getStatList(areaName));
			bean.setTotalMap(statMap);
		}
		return bean;
	}
	
	public String getStatSql(String areaName , String belongPollRiver , String index){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IFNULL(COUNT(*),0) FROM (SELECT rn.river_name,dist_mgr_name,street_mgr_name,village_mgr_name,rn.river_code,belongPollRiver ");
		sql.append("FROM responsibility_new rn,");
		sql.append("(SELECT river_code,belongPollRiver FROM rivers WHERE river_code IN(SELECT DISTINCT river_code FROM responsibility_new ");
		sql.append("WHERE areaName = '"+areaName+"'");
		sql.append(")) belongtype ");
		sql.append("WHERE rn.river_code = belongtype.river_code ");
		sql.append("AND belongPollRiver = '"+belongPollRiver+"' ");
		sql.append("GROUP BY "+index+") total;");
		
		return sql.toString();
	}
	
	public String getNotSetSql(String areaName){
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT (");
		sql.append("(SELECT COUNT(*) FROM (SELECT DISTINCT river_code FROM rivers WHERE AREA = '"+areaName+"') rtotal)");
		sql.append(" - (SELECT COUNT(*) FROM (SELECT DISTINCT river_code FROM `responsibility_new` WHERE areaName = '"+areaName+"') rntotal));");
		return sql.toString();
	}
	
	public String getSingleStat(String sql){
		Query query = entityManager.createNativeQuery(sql.toString());
		Object count = query.getSingleResult();
		return count+"";
	}
	
	
	public List<String> getStatList(String areaName){
		List<String> statList = new ArrayList<String>();
		statList.add(getSingleStat(getStatSql(areaName,"1",DIST_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"1",STREET_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"1",VILLAGE_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"2",DIST_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"2",STREET_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"2",VILLAGE_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"3",DIST_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"3",STREET_MGR_NAME)));
		statList.add(getSingleStat(getStatSql(areaName,"3",VILLAGE_MGR_NAME)));
		statList.add(getSingleStat(getNotSetSql(areaName)));
		return statList;
	}
	
}
