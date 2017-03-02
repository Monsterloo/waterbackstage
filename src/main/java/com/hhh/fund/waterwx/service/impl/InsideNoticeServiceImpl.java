package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.fund.util.FundPage;
import com.hhh.fund.waterwx.dao.InsideNoticeDao;
import com.hhh.fund.waterwx.entity.InsideNotice;
import com.hhh.fund.waterwx.service.InsideNoticeService;
import com.hhh.fund.waterwx.webmodel.InsideNoticeBean;

@Service
@Transactional
public class InsideNoticeServiceImpl implements InsideNoticeService {

	@Autowired
	private InsideNoticeDao noticeDao;
	
	@Override
	@Transactional(readOnly=true)
	public FundPage<InsideNoticeBean> findNoticeAll(Pageable pageable) {
		Specification<InsideNotice> spec = new Specification<InsideNotice>() {
			@Override
			public Predicate toPredicate(Root<InsideNotice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.desc(root.get("ctime").as(Date.class)));
				return query.getRestriction();
			}
		};
		Page<InsideNotice> pages = noticeDao.findAll(spec, pageable);
		List<InsideNoticeBean> list = new ArrayList<>();
		InsideNoticeBean bean = null;
		for(InsideNotice notice : pages.getContent()){
			bean = new InsideNoticeBean();
			bean.toBean(notice);
			list.add(bean);
		}

		return new FundPage<>(pages.getTotalPages(), pages.getTotalElements(), list);
	}

	@Override
	public InsideNoticeBean save(InsideNoticeBean notice) {
		InsideNotice n = notice.toEntity();
		if(n.getId() == null || "".equals(n.getId())|| "2".equals(n.getStatus())){
			n.setCtime(new Date());
		}
		
//		consumeDao.deleteByNoticeId(n.getId());
//		for(NoticeConsume nc : n.getSonsumes()){
//			nc.setNotice(n);
//			//consumeDao.save(nc);
//		}
		n = noticeDao.save(n);
		notice.setId(n.getId());
		return notice;
	}

	@Override
	public void deleteById(String id) {
		noticeDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public InsideNoticeBean findById(String id) {
		InsideNoticeBean bean = new InsideNoticeBean();
		InsideNotice notice = noticeDao.findOne(id);
		bean.toBean(notice);
		return bean;
	}

}
