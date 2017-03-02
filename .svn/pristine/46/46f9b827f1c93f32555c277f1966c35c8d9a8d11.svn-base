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
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.PublicSignsBoardInfoDao;
import com.hhh.fund.waterwx.entity.PublicSignsBoardInfo;
import com.hhh.fund.waterwx.service.PublicSignsBoardInfoService;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardStatisticsBean;

@Component
@Transactional
public class PublicSignsBoardInfoServiceImpl implements PublicSignsBoardInfoService{

	
	@Autowired
	private PublicSignsBoardInfoDao dao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static final String RIVERCODE = "rivercode";
	public static final String BOARDCODE = "boardcode";
	
	@Override
	public List<PublicSignsBoardInfoBean> findByAreaName(String areaName) {
		List<PublicSignsBoardInfo> list = null;
		if(areaName != null && !"".equals(areaName)){
			list = dao.findByAreaName(areaName);
		}else{
			list = dao.findAll();
		}
		List<PublicSignsBoardInfoBean> beanList = new ArrayList<PublicSignsBoardInfoBean>();
		for(PublicSignsBoardInfo info : list){
			PublicSignsBoardInfoBean bean = new PublicSignsBoardInfoBean();
			BeanUtils.copyProperties(info, bean);
			beanList.add(bean);
		}
		return beanList;
	}

	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	@Override
	public int getPage(int start,int pageSize){
		return (int)Math.floor((double)start/pageSize);
	}

	@Override
	public void save(PublicSignsBoardInfoBean bean) {
		PublicSignsBoardInfo info = new PublicSignsBoardInfo();
		BeanUtils.copyProperties(bean, info);
		dao.save(info);
		
	}

	@Override
	public PublicSignsBoardStatisticsBean findStatisticsByArea(String areaName) {
		Map<String,List<String>> statMap = new HashMap<String, List<String>>();
		PublicSignsBoardStatisticsBean bean = new PublicSignsBoardStatisticsBean();
		if("".equals(areaName) || areaName == null){
			//全市查找统计
			String [] areas = {"天河区","越秀区","荔湾区","白云区","海珠区","花都区","黄浦区","番禺区","增城区","增城区","从化区","南沙区"};
			for(int i =0;i<areas.length;++i){
				statMap.put(areas[i], getStatList(areas[i]));
			}
			bean.setStatMap(statMap);
		}else{
			//区级查找统计
			statMap.put(areaName, getStatList(areaName));
			bean.setStatMap(statMap);
		}
		return bean;
	}
	
	public String getStatSql(String areaName , String belongPollRiver , String index){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM(");
		sql.append("SELECT pb.rivercode , pb.boardcode ");
		sql.append("FROM  public_signs_board_info pb,rivers r WHERE r.river_code = pb.rivercode ");
		sql.append("AND pb.areaName = '"+areaName+"' ");
		sql.append("AND r.belongPollRiver = '"+belongPollRiver+"' ");
		sql.append("GROUP BY "+index+") count;");
		
		return sql.toString();
	}

	public String getNotSetSql(String areaName){
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT (");
		sql.append("(SELECT COUNT(*) FROM rivers WHERE AREA = '"+areaName+"') - ");
		sql.append("(SELECT COUNT(*) FROM (SELECT DISTINCT rivercode FROM `public_signs_board_info` WHERE areaName = '"+areaName+"')pbtotal));");
		return sql.toString();
	}
	
	public String getSingleStat(String sql){
		Query query = entityManager.createNativeQuery(sql.toString());
		Object count = query.getSingleResult();
		return count+"";
	}
	
	public List<String> getStatList(String areaName){
		List<String> statList = new ArrayList<String>();
		statList.add(getSingleStat(getStatSql(areaName,"1",RIVERCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"1",BOARDCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"2",RIVERCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"2",BOARDCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"3",RIVERCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"3",BOARDCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"4",RIVERCODE)));
		statList.add(getSingleStat(getStatSql(areaName,"4",BOARDCODE)));
		statList.add(getSingleStat(getNotSetSql(areaName)));
		return statList;
	}
	
//	@Override
//	public SmsPage<PublicSignsBoardInfoBean> findByRiverNames(PageRequest pr) {
//		String[] riverArr = rivers.split("\\,");
//		Object[] riverObjectArr = null;
//		List<Responsibility> list = new ArrayList<Responsibility>();
//		ArrayList<String>strArray = new ArrayList<String>();
//		for(String str:riverArr){
//			if(!StringUtil.isBlank(str)){
//				strArray.add(str);
//			}
//		}
//		if(strArray.size()!=0){
//			riverObjectArr = (Object[]) strArray.toArray();
//			for(int i = 0;i<riverObjectArr.length;i++){
//				System.out.println(riverObjectArr[i]);
//				List<Responsibility> list1 = responseDao.findByRiverName((String)riverObjectArr[i]);
//				System.out.println(list1.size());
//				list.addAll(list1);
//			}
//		}
//		
//		
//		List<ResponsibilityBean> accounts = new ArrayList<ResponsibilityBean>();
//		System.out.println(accounts.size());
//		if(null != list && !list.isEmpty()){
//			for(Responsibility a : list){
//				ResponsibilityBean bean = new ResponsibilityBean();
//				BeanUtils.copyProperties(a, bean);
//				accounts.add(bean);
//			}
//		}
//		long size = list.size();
//		long pageSize = size/10;
//		int p = (int)pageSize+1;
//		int number = page.getPageNumber();
//		List<ResponsibilityBean> beans = new ArrayList<ResponsibilityBean>();
//		int t = number*10;
//		int f = t+10;
//		if(accounts.size()<10){
//			f=accounts.size();
//		}
//		for(int i=t;i<f;i++){
//			beans.add(accounts.get(i));
//		}
//		return new SmsPage<ResponsibilityBean>(p, size, beans);
//	}

}
