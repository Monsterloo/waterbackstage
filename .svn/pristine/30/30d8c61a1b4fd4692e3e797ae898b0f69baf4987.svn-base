package com.hhh.fund.waterwx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SwjWxquestionDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjWxquestion;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.service.SwjWxQuestionService;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
import com.hhh.fund.waterwx.webmodel.SwjWxquestionBean;
@Component
@Transactional
public class SwjWxQuestionServiceImpl implements SwjWxQuestionService {
	@Autowired
	private SwjWxquestionDao wxDao;
	/**
	 * 保存问题类型
	 * */
	public void save(SwjWxquestionBean bean){
		String id = bean.getId();
		if(id == null || id.equals("")){
			SwjWxquestion type = new SwjWxquestion();
			BeanUtils.copyProperties(bean, type);
			wxDao.save(type);
		}else{
			SwjWxquestion type = wxDao.findOne(id);
			BeanUtils.copyProperties(bean, type);
			wxDao.save(type);
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
			wxDao.delete(ids[i]);
		}
	}
	/**
	 * 查找所有问题
	 * */
	public SmsPage<SwjWxquestionBean> findTypeAll(Pageable page) {
		Page<SwjWxquestion> alist = null;
		alist = wxDao.findAll(null, page);
		List<SwjWxquestionBean> accounts = new ArrayList<SwjWxquestionBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjWxquestion a : alist.getContent()){
				SwjWxquestionBean bean = new SwjWxquestionBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new SmsPage<SwjWxquestionBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
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
	public SwjWxquestionBean findById(String id){
		SwjWxquestion river = wxDao.findOne(id);
		SwjWxquestionBean bean = new SwjWxquestionBean();
		BeanUtils.copyProperties(river, bean);
		return bean;
	}
	/**
	 * 查询所有无效投诉
	 * 
	 * */
	public List<SwjWxquestionBean> getAllWxByWeixin(){
		List<SwjWxquestion> list = new ArrayList<SwjWxquestion>();
		Iterator<SwjWxquestion> it = wxDao.findAll().iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		List<SwjWxquestionBean> alist = new ArrayList<SwjWxquestionBean>();
		for(SwjWxquestion wx : list){
			SwjWxquestionBean bean = new SwjWxquestionBean();
			BeanUtils.copyProperties(wx, bean);
			alist.add(bean);
		}
		return alist;
	}
}
