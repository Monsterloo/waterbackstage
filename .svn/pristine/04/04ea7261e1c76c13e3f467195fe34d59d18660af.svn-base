package com.hhh.fund.waterwx.service.impl;

import java.lang.reflect.InvocationTargetException;
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

import com.hhh.fund.waterwx.dao.SwjOutfallPolluateResourceDao;
import com.hhh.fund.waterwx.entity.OutfallPolluateResource;
import com.hhh.fund.waterwx.entity.PublicSignsBoardInfo;
import com.hhh.fund.waterwx.service.SwjOutfallPolluateResourceService;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;
import com.hhh.fund.waterwx.webmodel.PublicSignsBoardInfoBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

@Component
@Transactional
public class SwjOutfallPolluateResourceServiceImpl implements SwjOutfallPolluateResourceService{

	@Autowired
	private SwjOutfallPolluateResourceDao swjOutfallPolluateResourceDao;
	
	@Override
	public void save(OutfallPolluateResourceBean resource) {
		OutfallPolluateResource outResource = new OutfallPolluateResource();
		BeanUtils.copyProperties(resource,outResource);
		swjOutfallPolluateResourceDao.save(outResource);
	}

	@Override
	public SmsPage<OutfallPolluateResourceBean> findAll(PageRequest pr, 
			final OutfallPolluateResourceBean conditionBean) {

		Page<OutfallPolluateResource> alist = null;
		
		Specification<OutfallPolluateResource> spec = new Specification<OutfallPolluateResource>() {
			public Predicate toPredicate(Root<OutfallPolluateResource> root, 
					CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != conditionBean.getArea() && !conditionBean.getArea().isEmpty()){
					predicates.add(cb.equal(root.get("area").as(String.class), conditionBean.getArea().trim()));
				}
				if(null != conditionBean.getRivername() && !conditionBean.getRivername().isEmpty()){
					predicates.add(cb.like(root.get("rivername").as(String.class), "%"+conditionBean.getRivername().trim()+"%"));
				}
				
				if(null != conditionBean.getOutfallshape() && !conditionBean.getOutfallshape().isEmpty()){
					predicates.add(cb.like(root.get("outfallshape").as(String.class), "%"+conditionBean.getOutfallshape().trim()+"%"));
				}
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		
		
		
		alist = swjOutfallPolluateResourceDao.findAll(spec, pr);
		List<OutfallPolluateResourceBean> accounts = new ArrayList<OutfallPolluateResourceBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(OutfallPolluateResource a : alist.getContent()){
				OutfallPolluateResourceBean bean = new OutfallPolluateResourceBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return  new SmsPage<OutfallPolluateResourceBean>(0, 0, accounts);
		}
		return new SmsPage<OutfallPolluateResourceBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}

	@Override
	public boolean saveList(List beanList) {
		for(Object obj:beanList){
			OutfallPolluateResourceBean bean = (OutfallPolluateResourceBean)obj;
			OutfallPolluateResource info = new OutfallPolluateResource();
			BeanUtils.copyProperties(bean,info);
			swjOutfallPolluateResourceDao.save(info);
		}
		return true;
	}

	@Override
	public OutfallPolluateResourceBean findById(String id) {
		OutfallPolluateResource info = swjOutfallPolluateResourceDao.findOne(id);
		OutfallPolluateResourceBean bean = new OutfallPolluateResourceBean();
		BeanUtils.copyProperties(info, bean);
		return bean;
	}

}
