package com.hhh.fund.waterwx.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.util.StringUtils;
import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SwjAnswerDao;
import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.dao.SwjFinishapplyDao;
import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.dao.SwjQuestionItemDao;
import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SwjWeiXinUserDao;
import com.hhh.fund.waterwx.dao.SysUcenterDictDao;
import com.hhh.fund.waterwx.entity.Responsibility;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjFinishapply;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestionItem;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.entity.SwjWeiXinUser;
import com.hhh.fund.waterwx.entity.SysUcenterDepartment;
import com.hhh.fund.waterwx.entity.SysUcenterDict;
import com.hhh.fund.waterwx.webmodel.ComplainListJsonBean;
import com.hhh.fund.waterwx.webmodel.PhotoListBean;
import com.hhh.fund.waterwx.webmodel.PictureUrlBean;
import com.hhh.fund.waterwx.webmodel.RiverBean;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjComplainBean;
import com.hhh.fund.waterwx.webmodel.SwjFinishapplyBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjQuestionItemBean;
import com.hhh.fund.waterwx.webmodel.ToFrontWeiXinUserBean;
import com.hhh.fund.waterwx.service.SwjFinishApplyService;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.util.CacheDataUtils;
import com.hhh.fund.waterwx.util.ComplainConstants;
import com.hhh.fund.waterwx.util.DateUtils;
import com.hhh.fund.waterwx.util.StringUtil;
@Component
@Transactional
public class SwjFinishApplyServiceImpl implements SwjFinishApplyService {

		@Autowired
		private SwjFinishapplyDao finishApplyDao;
		
		@Autowired
		private SwjAttachmentDao swjAttachmentDao;
		
		@Autowired
		private SwjQuestionService questionService;

		/**
		 * 保存申请完结方法
		 * */
		public String saveFinishApply(SwjFinishapply bean){
			SwjFinishapply swj = finishApplyDao.save(bean);
			return swj.getId();
		}

		/**
		 * 获取完结申请详情专用
		 */
		@Override
		public SwjFinishapplyBean findByFinishAyyplyId(String complainId,HttpServletRequest request) {
			SwjFinishapply bean1 = finishApplyDao.findByDataId(complainId);
			SwjFinishapplyBean bean = new SwjFinishapplyBean();
			BeanUtils.copyProperties(bean1, bean);
				
			
			
			String CreateTime = bean.getCreateTime().substring(0, bean.getCreateTime().length()-2);
			bean.setCreateTime(CreateTime);
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
}
