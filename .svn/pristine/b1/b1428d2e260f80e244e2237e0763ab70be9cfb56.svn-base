package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.service.SwjQuestiontypeService;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestiontypeBean;
@Component
@Transactional
public class SwjQuestiontypeServiceImpl implements SwjQuestiontypeService {
	@Autowired
	private SwjQuestiontypeDao typeDao;
	/**
	 * 保存问题类型
	 * */
	public void save(SwjQuestiontypeBean bean){
		String id = bean.getId();
		if(id == null || id.equals("")){
			SwjQuestiontype type = new SwjQuestiontype();
			BeanUtils.copyProperties(bean, type);
			typeDao.save(type);
		}else{
			SwjQuestiontype type = typeDao.findOne(id);
			type.setDetail(bean.getDetail());
			type.setOrdernumber(bean.getOrdernumber());
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
			typeDao.delete(ids[i]);
		}
	}
	/**
	 * 查找所有问题
	 * */
	public SmsPage<SwjQuestiontypeBean> findTypeAll(Pageable page) {
		Page<SwjQuestiontype> alist = null;
		alist = typeDao.getAllTypeOrderby(page);
		List<SwjQuestiontypeBean> accounts = new ArrayList<SwjQuestiontypeBean>();
		
		if(null != alist.getContent() && !alist.getContent().isEmpty()){
			for(SwjQuestiontype a : alist.getContent()){
				SwjQuestiontypeBean bean = new SwjQuestiontypeBean();
				BeanUtils.copyProperties(a, bean);
				accounts.add(bean);
			}
		}else{
			return null;
		}
		return new SmsPage<SwjQuestiontypeBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
	}
	
	/**
	 * 获取页码,从0页开始
	 * @return
	 */
	public int getPage(int start,int pageSize){
		return (int)Math.floor((double)start/pageSize);
	}
	@Override
	public List<SwjQuestiontype> findTypeAll() {
		return typeDao.findAllType();
	}
}
