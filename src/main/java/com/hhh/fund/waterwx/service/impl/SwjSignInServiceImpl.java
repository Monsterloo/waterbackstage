package com.hhh.fund.waterwx.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.ResponsibilityDao;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.dao.SwjAnswerDao;
import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.dao.SwjQuestionDao;
import com.hhh.fund.waterwx.dao.SwjQuestiontypeDao;
import com.hhh.fund.waterwx.dao.SwjSignInDao;
import com.hhh.fund.waterwx.entity.River;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.entity.SwjUser;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
import com.hhh.fund.waterwx.entity.SwjWxsignin;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.waterwx.webmodel.SmsPage;
import com.hhh.fund.waterwx.webmodel.SwjQuestionBean;
import com.hhh.fund.waterwx.webmodel.SwjWxsigninBean;
import com.hhh.fund.waterwx.service.SwjQuestionService;
import com.hhh.fund.waterwx.service.SwjSignInService;
import com.hhh.weixin.util.CommonUtil;
import com.hhh.weixin.util.QiyehaoConst;
@Component
@Transactional
public class SwjSignInServiceImpl implements SwjSignInService {
	
		@Autowired
		private SwjQuestionDao questionDao;
		@Autowired
		private SwjQuestiontypeDao typeDao;
		@Autowired
		private ResponsibilityDao responseDao;
		@Autowired
		private SwjAttachmentDao attDao;
		@Autowired
		private RiverDao riverDao;
		@Autowired
		private SwjAnswerDao answerDao;
		@Autowired
		private SwjSignInDao signInDao;
		
		private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
		
		/**
		 * 保存方法
		 * */
		public String save(SwjWxsigninBean bean){
			SwjWxsignin signin = new SwjWxsignin();
			BeanUtils.copyProperties(bean, signin);
			signin.setSigninDate(new Date());
			SwjWxsignin swj = signInDao.save(signin);
			String id = swj.getId();
			return id;
		}
		
		/**
		 * 保存附件
		 * */
		public void saveAttachment(SwjAttachment att){
			attDao.save(att);
		}
		
		/**
		 * 查询签到
		 * */
		@Override
		public List<SwjWxsigninBean> findSignIn(final String reachname,final String grade,
				final String area) {
			List<SwjWxsignin> alist = null;
			Specification<SwjWxsignin> spec = new Specification<SwjWxsignin>() {
				public Predicate toPredicate(Root<SwjWxsignin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != reachname && !reachname.isEmpty() && !reachname.equals("请选择")){
						predicates.add(cb.like(root.get("reachname").as(String.class), "%"+reachname+"%"));
					}
					if(null != grade && !grade.isEmpty() && !grade.equals("请选择")){
						predicates.add(cb.equal(root.get("grade").as(String.class), grade));
					}
					if(null != area && !area.isEmpty() && !area.equals("请选择")){
						predicates.add(cb.equal(root.get("area").as(String.class), area));
					}
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("signinDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("signinDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			
			alist = signInDao.findAll(spec);
			List<SwjWxsigninBean> accounts = new ArrayList<SwjWxsigninBean>();
			if(alist != null && alist.size()>0){
				for(SwjWxsignin a : alist){
					SwjWxsigninBean bean = new SwjWxsigninBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date time = null;
					time = a.getSigninDate();
					String d = sdf.format(time);
					System.out.println(d+"ddddd");
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return accounts;
			}
			return accounts;
		}
		
		public SwjWxsigninBean findById(String id){
			SwjWxsignin sign = signInDao.findOne(id);
			SwjWxsigninBean bean = new SwjWxsigninBean();
			Date date =sign.getSigninDate();
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = sdf.format(date);
			BeanUtils.copyProperties(sign, bean);
			bean.setSigninDate(date);
			return bean;
		}
		
		/**
		 * 根据userid去用户表姓名
		 * */
		@Override
		public String findUserNameByuserId(String userId) {
			List<SwjUser> list = signInDao.getUserInfo(userId);
			String name="";
			for(int i=0;i<list.size();i++){
			    name=list.get(i).getName();
			}
			return name;
		}
		
		/**
		 * 微信下载图片文件
		 * 
		 * @param accessToken
		 *            访问凭证
		 * @param url
		 *            微信下载文件url
		 * @param mediaIds
		 *            下载文件ID
		 * @param load
		 *            保存文件接口
		 */
		@Override
		public void signInImgDownload(final String[] mediaIds,final String id,final SwjSignInService signInService) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					String accessToken = CommonUtil.getToken();
					String url=QiyehaoConst.URL_DOWNLOAD_FILE;
					ByteArrayOutputStream bos = null;
					SwjAttachment att = new SwjAttachment();
					InputStream is = null;
					HttpURLConnection http = null;
					for (String mid : mediaIds) {
						if (mid != null && !"".equals(mid)) {
							int k = 0;
							String urlstr = String.format(url, accessToken, mid);
							log.debug("URL=" + url + mid);
							while (true && k < 3) {
								k++;
								try {
									URL urlGet = new URL(urlstr);
									http = (HttpURLConnection) urlGet.openConnection();
									http.setRequestMethod("GET"); 
									http.setRequestProperty("Content-Type", "application/octet-stream");
									http.setDoOutput(true);
									http.setDoInput(true);
									System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
									System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
									http.connect();
									is = http.getInputStream();
									byte[] buffer = new byte[1024];

									int len = 0, tlen = 0, clen = http.getContentLength();
									bos = new ByteArrayOutputStream();
									while ((len = is.read(buffer)) != -1 && tlen < clen) {
										bos.write(buffer, 0, len);
										tlen += len;
									}
									bos.close();
									att.setFile(bos.toByteArray());
									is.close();
									att.setQuestionId(id);
									signInService.saveAttachment(att);
									http.disconnect();
									log.debug("下载完成");
									break;
								} catch (Exception e) {
									e.printStackTrace();
									log.debug("下载失败");
									try {
										Thread.sleep(5 * 1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								} finally {
									try {
										if (is != null) {
											is.close();
										}
										if (http != null) {
											http.disconnect();
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}).start();
			;
		}

		public int getPage(int start, int pageSize) {
			return (int)Math.floor((double)start/pageSize);
		}

		@Override
		public List<SwjAttachment> getAttchId(String qId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SwjAttachment getAttachment(String id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		/**
		 * pc端 保存投诉信息
		 * */
		@Override
		public void saveQuestionByWeb(SwjWxsigninBean bean) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * 查询签到pc端
		 * */
		public SmsPage<SwjWxsigninBean> findSignInByWeb(final String reachname,final String grade,
				final String area,Pageable page) {
			Page<SwjWxsignin> alist = null;
			Specification<SwjWxsignin> spec = new Specification<SwjWxsignin>() {
				public Predicate toPredicate(Root<SwjWxsignin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					if(null != reachname && !reachname.isEmpty() && !reachname.equals("请选择")){
						predicates.add(cb.like(root.get("reachname").as(String.class), "%"+reachname+"%"));
					}
					if(null != grade && !grade.isEmpty() && !grade.equals("请选择")){
						predicates.add(cb.equal(root.get("grade").as(String.class), grade));
					}
					if(null != area && !area.isEmpty() && !area.equals("请选择")){
						predicates.add(cb.equal(root.get("area").as(String.class), area));
					}
					if(predicates.isEmpty()){
						query.orderBy(cb.desc(root.get("signinDate").as(Date.class)));
						return query.getRestriction();
					}	
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
					query.orderBy(cb.desc(root.get("signinDate").as(Date.class)));
					return query.getRestriction();
				}
			};
			
			alist = signInDao.findAll(spec,page);
			List<SwjWxsigninBean> accounts = new ArrayList<SwjWxsigninBean>();
			if(alist != null && !alist.getContent().isEmpty()){
				for(SwjWxsignin a : alist.getContent()){
					SwjWxsigninBean bean = new SwjWxsigninBean();
					SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
					Date time = null;
					time = a.getSigninDate();
					String d = sdf.format(time);
					System.out.println(d+"ddddd");
					BeanUtils.copyProperties(a, bean);
					accounts.add(bean);
				}
			}else{
				return null;
			}
			return new SmsPage<SwjWxsigninBean>(alist.getTotalPages(), alist.getTotalElements(), accounts);
		}
		




		
		
}
