package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.PublicSignsBoardInfoDao;
import com.hhh.fund.waterwx.entity.PublicSignsBoardInfo;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.service.SwjPublicSignBoardInfoService;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.ResponsibilityBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

@Component
@Transactional
public class SwjPublicSignBoardInfoServiceImpl implements SwjPublicSignBoardInfoService{

	@Autowired
	private PublicSignsBoardInfoDao boardInfoDao;

	@Override
	public SmsPage<PublicSignsBoardInfoBean> findTypeAll(PageRequest pr, final PublicSignsBoardInfoBean infoBean) {
		Page<PublicSignsBoardInfo> alist = null;
		
//		var areaName = $("#areaSearch").val();
//		var riverName = $("#riverName").val();
//		var boardPosition = $("#boardPosition").val();
		
		Specification<PublicSignsBoardInfo> spec = new Specification<PublicSignsBoardInfo>() {
			public Predicate toPredicate(Root<PublicSignsBoardInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != infoBean.getAreaName() && !infoBean.getAreaName().isEmpty()){
					predicates.add(cb.equal(root.get("areaName").as(String.class), infoBean.getAreaName().trim()));
				}
				if(null != infoBean.getRiverName() && !infoBean.getRiverName().isEmpty()){
					predicates.add(cb.like(root.get("riverName").as(String.class), "%"+infoBean.getRiverName().trim()+"%"));
				}
				
				if(null != infoBean.getBoardPosition() && !infoBean.getBoardPosition().isEmpty()){
					predicates.add(cb.like(root.get("boardPosition").as(String.class), "%"+infoBean.getBoardPosition().trim()+"%"));
				}
				
				
				
				
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		
		alist = boardInfoDao.findAll(spec, pr);
		
		List<PublicSignsBoardInfoBean> accounts = new ArrayList<PublicSignsBoardInfoBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(PublicSignsBoardInfo a : alist.getContent()){
				PublicSignsBoardInfoBean bean = new PublicSignsBoardInfoBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return new SmsPage<PublicSignsBoardInfoBean>(0, 0, accounts);
		}
		return new SmsPage<PublicSignsBoardInfoBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}

	@Override
	public PublicSignsBoardInfoBean findById(String id) {
		PublicSignsBoardInfo info = boardInfoDao.findOne(id);
		PublicSignsBoardInfoBean bean = new PublicSignsBoardInfoBean();
		BeanUtils.copyProperties(info, bean);
		return bean;
	}
	
}
