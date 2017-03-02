package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.util.StringUtil;
import com.hhh.fund.waterwx.dao.SwjQuestionItemDao;
import com.hhh.fund.waterwx.dao.SwjWeiXinUserDao;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestionItems;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.service.SwjQuestionItemService;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemBean;

@Component
@Transactional
public class SwjQuestionItemServiceImpl implements SwjQuestionItemService{

	@Autowired
	private SwjQuestionItemDao swjQuestionItemDao;
	@Autowired
	private SwjWeiXinUserDao weixinUserDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(SwjQuestionItem item) {
		swjQuestionItemDao.save(item);
	}

	@Override
	public List<SwjQuestionItem> findByDataIdAndType(String complainId, String type) {
		return swjQuestionItemDao.findByDataIdAndType(complainId,type);
	}
	
	@Override
	public List<SwjQuestionItems> getReplyList(String complainId, String type) {
		return swjQuestionItemDao.getReplyList(complainId,type);
	}
	

	@Override
	public List<SwjQuestionItems> findByDataId(String complainId) {
		return swjQuestionItemDao.findByDataId(complainId);
	}
	
	@Override
	public void cancelPraiseByComplainIdAndOpenId(String complainId, String openId, String status) {
		swjQuestionItemDao.updateStatusByDataIdAndOpenId(complainId,openId,status);
	}

	@Override
	public List<SwjQuestionItem> findByDataIdAndOpenIdAndType(String complainId, String openId,
			String type) {
		return swjQuestionItemDao.findByDataIdAndOpenIdAndType(complainId,openId,type);
	}

	@Override
	public void updateStatusByItemId(String id, int status) {
		swjQuestionItemDao.updateStatusByItemId(id,status);
	}

	@Override
	public List<SwjQuestionItem> findByDataIdAndStatusAndType(String complainId, int i, String type) {
		return swjQuestionItemDao.findByDataIdAndStatusAndType(complainId,i,type);
	}

	@Override
	public void updateStatusByDataIdAndTypeAndOpenId(String complainId, String openId, String type, int status) {
		swjQuestionItemDao.updateStatusByDataIdAndTypeAndOpenId(complainId,openId,type,status);
	}
	
	/**
	 * 根据SwjQuestionItem单表所有的条件来查询  分页
	 * @return
	 */
	
	public SmsPage<SwjQuestionItemBean> findQuestionItemByAllCondition(final SwjQuestionItemBean questionItemBean,Pageable page) {
		Page<SwjQuestionItem> alist = null;
		Specification<SwjQuestionItem> spec = new Specification<SwjQuestionItem>() {
			//TODO 根据自己需要的条件添加
			public Predicate toPredicate(Root<SwjQuestionItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				predicates.add(cb.equal(root.get("toOpenId").as(String.class),questionItemBean.getToOpenId()));
				predicates.add(cb.equal(root.get("status").as(int.class),questionItemBean.getStatus()));
				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				query.orderBy(cb.desc(root.get("createTime").as(Date.class)));
				return query.getRestriction();
			}
		};
		
		alist = swjQuestionItemDao.findAll(spec,page);
		SwjWeiXinUser user = weixinUserDao.findByOpenId(questionItemBean.getOpenId());
		List<SwjQuestionItemBean> accounts = new ArrayList<SwjQuestionItemBean>();
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjQuestionItem a : alist.getContent()){
				a.setCreateTime(DateUtils.DateToString_14a(DateUtils.StringToDate_15a(a.getCreateTime())));
				SwjQuestionItemBean bean = new SwjQuestionItemBean();
				BeanUtils.copyProperties(a, bean);
				bean.setWeixinUserName(user==null?"":user.getName());
				bean.setOpenIdPhotoHref(user==null?"":user.getPhotoHref());
				accounts.add(bean);
			}
		}else{
			return new SmsPage<SwjQuestionItemBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		return new SmsPage<SwjQuestionItemBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	public static void main(String[] args) {
		String str = "2015-11-11 22:55:11.0";
		System.out.println(DateUtils.DateToString_14a(DateUtils.StringToDate_15a(str)));
		
	}
	
	@Override
	public void saveReply(SwjQuestionItem item) {
		Query query = entityManager.createNativeQuery(" update swj_question set comment_count=comment_count+1 where id=:questionId ");
		query.setParameter("questionId", item.getDataId());
		query.executeUpdate();
		swjQuestionItemDao.save(item);
	}

	@Override
	public List<SwjQuestionItems> findByDataId(String complainId, String type) {
		return swjQuestionItemDao.findByDataId(complainId, type);
		
	}

}
