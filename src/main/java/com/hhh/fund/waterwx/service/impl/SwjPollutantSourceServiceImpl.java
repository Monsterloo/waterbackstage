package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjPollutantSourceDao;
import com.hhh.fund.waterwx.entity.OutfallPolluateResource;
import com.hhh.fund.waterwx.entity.PollutantSource;
import com.hhh.fund.waterwx.service.SwjPollutantSourceService;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;

@Component
@Transactional
public class SwjPollutantSourceServiceImpl implements SwjPollutantSourceService{

	@Autowired
	private SwjPollutantSourceDao dao;	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void save(PollutantSourceBean source) {
		PollutantSource target = new PollutantSource();
		BeanUtils.copyProperties(source, target);
		dao.save(target);
	}

	@Override
	public SmsPage<PollutantSourceBean> findAll(PageRequest pr, 
			final PollutantSourceBean conditionBean) {
		Page<PollutantSource> alist = null;
		
		Specification<PollutantSource> spec = new Specification<PollutantSource>() {
			public Predicate toPredicate(Root<PollutantSource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != conditionBean.getArea() && !conditionBean.getArea().isEmpty()){
					predicates.add(cb.equal(root.get("area").as(String.class), conditionBean.getArea().trim()));
				}
				if(null != conditionBean.getRivername() && !conditionBean.getRivername().isEmpty()){
					predicates.add(cb.like(root.get("rivername").as(String.class), "%"+conditionBean.getRivername().trim()+"%"));
				}
				
				if(null != conditionBean.getPollsourcetype() && !conditionBean.getPollsourcetype().isEmpty()){
					predicates.add(cb.like(root.get("pollsourcetype").as(String.class), "%"+conditionBean.getPollsourcetype().trim()+"%"));
				}
				if(predicates.isEmpty()){
					return null;
				}	
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		};
		
		
		
		alist = dao.findAll(spec, pr);
		List<PollutantSourceBean> accounts = new ArrayList<PollutantSourceBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(PollutantSource a : alist.getContent()){
				PollutantSourceBean bean = new PollutantSourceBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return  new SmsPage<PollutantSourceBean>(0, 0, accounts);
		}
		return new SmsPage<PollutantSourceBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}

	@Override
	public List<PollutantSourceBean> saveList(List beanList) {
		List<PollutantSourceBean> returnBeans = new ArrayList<PollutantSourceBean>();
		for(Object obj:beanList){
			
			PollutantSourceBean bean = (PollutantSourceBean)obj;
			PollutantSource i = dao.findOnlyOneRecord(bean.getRivercode(),bean.getArea(),bean.getPollsourcename(),bean.getPolldescription());
			
			if(i != null){
				continue;
			}
			
			PollutantSource info = new PollutantSource();
			
			BeanUtils.copyProperties(bean,info);
			if(info.getCreateTime() ==null &&StringUtils.isBlank(info.getOutfallcode())){
				continue;
			}
			bean.setId(dao.save(info).getId());
			returnBeans.add(bean);
		}
		return returnBeans;
	}

	@Override
	public PollutantSourceBean findById(String id) {
		PollutantSource info = dao.findOne(id);
		PollutantSourceBean bean = new PollutantSourceBean();
		BeanUtils.copyProperties(info, bean);
		return bean;
	}

	@Override
	public List<Object[]> getAreaStatisticsList(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

}