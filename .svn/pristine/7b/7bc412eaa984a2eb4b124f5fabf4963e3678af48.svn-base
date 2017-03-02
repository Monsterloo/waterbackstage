package com.hhh.fund.waterwx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.dao.SwjEvaluationDao;
import com.hhh.fund.waterwx.entity.SwjEvaluation;
import com.hhh.fund.waterwx.service.SwjEvaluationService;
import com.hhh.fund.waterwx.webmodel.PhotoListBean;
import com.hhh.fund.waterwx.webmodel.PictureUrlBean;
import com.hhh.fund.waterwx.webmodel.SwjEvaluationBean;

@Component
@Transactional
public class SwjEvaluationServiceImpl implements SwjEvaluationService{

	@Autowired
	private SwjEvaluationDao dao;
	
	@Autowired
	private SwjAttachmentDao swjAttachmentDao;
	
	@Override
	public String save(SwjEvaluation eval) {
		SwjEvaluation i = dao.save(eval);
		return i.getId();
	}
	
	@Override
	public void updateFinishApply(String complainId) {
		dao.updateStatusByItemId(complainId);
	}
	
	/**
	 * @param complainId
	 * @return
	 */
	public SwjEvaluationBean findByComplainId(String complainId){
		SwjEvaluation bean1 = dao.findByDataId(complainId);
		SwjEvaluationBean bean = new SwjEvaluationBean();
		BeanUtils.copyProperties(bean1, bean);
		return bean;
	}
	
	/**
	 * 获取完结申请详情专用
	 */
	@Override
	public SwjEvaluationBean findByFinishAyyplyId(String complainId,HttpServletRequest request) {
		SwjEvaluation bean1 = dao.findByDataId(complainId);
		SwjEvaluationBean bean = new SwjEvaluationBean();
		BeanUtils.copyProperties(bean1, bean);
			
		
		
		bean.setCreateTime(bean.getCreateTime());
		//添加完结的图片
		PhotoListBean photoListBean = new PhotoListBean();
		List<PictureUrlBean>urlBeanList = new ArrayList<>();
		List<String>attach = swjAttachmentDao.findAttachIdByQuestionId(bean.getId());
		for(String str:attach){
			PictureUrlBean pictureUrlBean = new PictureUrlBean();
			pictureUrlBean.setLocationId(str);
			urlBeanList.add(pictureUrlBean);
		}
		photoListBean.setList(urlBeanList);
		photoListBean.setListCount(attach==null?0:attach.size());
		bean.setPhotoList(photoListBean);
		
		return bean;
	}

	@Override
	public void saveEvalBean(SwjEvaluationBean evaBean) {
		SwjEvaluation bean = new SwjEvaluation();
		BeanUtils.copyProperties(evaBean, bean);
		dao.save(bean);
	}
	

}
