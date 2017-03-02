package com.hhh.fund.waterwx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.service.RiverService;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
@Component
@Transactional
public class RiverServiceImpl implements RiverService {
	@Autowired
	private RiverDao riverDao;
	@Autowired
	private SysUcenterDictDao dictDao;
	/**
	 * 保存河道
	 * */
	public void save(RiverBean bean){
		String id = bean.getId();
		String id1 = id;
		System.out.println(id+"7890");
		if(id1 == null || id1.equals("")){
			System.out.println("if");
			River type = new River();
			BeanUtils.copyProperties(bean, type);
			riverDao.save(type);
		}else{
			System.out.println("else");
			River type = riverDao.findOne(id1);
			BeanUtils.copyProperties(bean, type);
			riverDao.save(type);
		/*	type.setRiverCode(bean.getRiverCode());
			type.setRiverName(bean.getRiverName());
			type.setArea(bean.getArea());
			type.setStart(bean.getStart());
			type.setEnd(bean.getEnd());
			type.setLength(bean.getLength());*/
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
			riverDao.delete(ids[i]);
		}
	}
	//TODO
	/**
	 * 查找所有问题
	 * */
	public SmsPage<RiverBean> findRiverAll(Pageable page,final String river,final String area,final String pollrivers) {
		
		Specification<River> spec = new Specification<River>() {
			public Predicate toPredicate(Root<River> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != river && !river.isEmpty()){
					predicates.add(cb.like(root.get("riverName").as(String.class), "%"+river.trim()+"%"));
				}
				if(null != area && !area.isEmpty()){
					predicates.add(cb.equal(root.get("area").as(String.class), area));
				}
				if(null != pollrivers && !pollrivers.isEmpty() && !pollrivers.equals("null")){
					predicates.add(cb.equal(root.get("belongPollRiver").as(String.class), pollrivers));
				}
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		
		Page<River> alist = null;
		alist = riverDao.findAll(spec, page);
		List<RiverBean> accounts = new ArrayList<RiverBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			
			List<SysUcenterDict> pollriversList = dictDao.findByParent("pollrivers");
			
			for(River a : alist.getContent()){
				RiverBean bean = new RiverBean();
				BeanUtils.copyProperties(a, bean);
				bean.setBelongPollRiverStr(changeToCN(pollriversList,bean.getBelongPollRiver()));
				accounts.add(bean);
			}
		}else{
			return new SmsPage<RiverBean>(0,0,accounts);
		}
		return new SmsPage<RiverBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	private String changeToCN(List<SysUcenterDict> pollriversList, String belongPollRiver) {
		if(pollriversList==null||pollriversList.isEmpty()){
			return "";
		}
		for(SysUcenterDict dict:pollriversList){
			if(dict.getCode().equals(belongPollRiver)){
				return dict.getName();
			}
		}
		
		return "";
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
	public RiverBean findById(String id){
		River river = riverDao.findOne(id);
		RiverBean bean = new RiverBean();
		BeanUtils.copyProperties(river, bean);
		return bean;
	}
	
	/**
	 * 根据地区统计河道数量
	 * 
	 * */
	public List<River> getRiverGroupByArea() {
		List<River> list = riverDao.getRiverGroupByArea();
		return list;
	}
	@Override
	public List<River> getGradeByRiverName(String rivername) {
		List<River> list = riverDao.findByReachname(rivername);
		return list;
	}
	
	/**
	 * 通过行政区域获取河涌列表
	 * 
	 * */
	@Override
	public List<River> getRiverListByAreaName(String area) {
		List<River> list = riverDao.getRiverListByAreaName(area);
		return list;
	}
	/**
	 * 根据河道名 查河道
	 * @param rivername
	 * */
	public List<River> findByRiverName(String rivername){
		List<River> list = riverDao.findByRiverName(rivername);
		return list;
	}
	/**
	 * 根据河道名 查河道
	 * @param rivername
	 * */
	public River findByRiverCode(String riverCode){
		River river = riverDao.findByRiverCode(riverCode);
		return river;
	}
	
	/**
	 * 根据查询条件查询河道
	 * @param area 行政区域  grade 河道等级  river 河道名称
	 * 
	 * */
	public List<RiverBean> getRiverBySearch(final String area,final String grade,final String river){
		List<River> alist = null;
		System.out.println(area+","+grade+","+river);
		Specification<River> spec = new Specification<River>() {
			public Predicate toPredicate(Root<River> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != area && !area.isEmpty()){
					predicates.add(cb.equal(root.get("area").as(String.class), area));
				}
				if(null != grade && !grade.isEmpty()){
					predicates.add(cb.equal(root.get("grade").as(String.class), grade));
				}
				if(null != river && !river.isEmpty() && !river.equals("null")){
					predicates.add(cb.like(root.get("riverName").as(String.class), "%"+river.trim()+"%"));
				}
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				System.err.println(query.toString());
				return query.getRestriction();
			}
		};
		alist = riverDao.findAll(spec);
		System.out.println(alist.size());
		List<RiverBean> accounts = new ArrayList<RiverBean>();
		if(null != alist && !alist.isEmpty()){
			for(River a : alist){
				RiverBean bean = new RiverBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}
		return accounts;
	}
	@Override
	public List<River> findAllRiver() {
		return riverDao.findAllRivers();
	}
	
	@Override
	public List<River> findByListRiverCode(String riverCode) {
		if(StringUtils.isBlank(riverCode)){
			return null;
		}
		List<River> list = riverDao.findByListRiverCode(riverCode);
		return list;
	}
	
	@Override
	public List<River> getRiverByAreaAndRiver(String areaName,String riverName) {
		if(StringUtils.isBlank(riverName)){
			return null;
		}
		List<River> list = riverDao.getRiverByAreaAndRiver(areaName,riverName);
		return list;
	}
	
}
