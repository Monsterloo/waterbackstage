package com.hhh.fund.waterwx.service.impl;



import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjWxexportUserDao;
import com.hhh.fund.waterwx.dao.SwjWxfollowUserInfoDao;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjWxexportUser;
import com.hhh.fund.waterwx.entity.SwjWxfollowuserinfo;
import com.hhh.fund.waterwx.entity.SwjWxquestion;
import com.hhh.fund.waterwx.service.SwjWxfollowUserInfoService;
import com.hhh.fund.waterwx.webmodel.SwjWxexportUserBean;
import com.hhh.fund.waterwx.webmodel.SwjWxfollowuserinfoBean;
@Component
@Transactional
public class SwjWxfollowUserInfoServiceImpl implements SwjWxfollowUserInfoService {
		
		@Autowired
		private SwjWxfollowUserInfoDao followUserInfoDao;
		
		@Autowired
		private SwjWxexportUserDao exportUserDao;
		
		
		@Override
		public void save(SwjWxfollowuserinfoBean bean) {
			SwjWxfollowuserinfo fuser=new SwjWxfollowuserinfo();
			BeanUtils.copyProperties(bean, fuser);
			followUserInfoDao.save(fuser);
		}
			
		
		@Override
		public void deleteFollowUserTable() {
			followUserInfoDao.deleteAll();
		}
		
		@Override
		public List<SwjWxfollowuserinfo> findGiftingMoneyUser() {
			List<SwjWxfollowuserinfo> list=followUserInfoDao.findGiftingMoneyUser();
			return list;
		}


		@Override
		public void saveExportUser(SwjWxexportUserBean bean) {
			SwjWxexportUser exportUser=new SwjWxexportUser();
			BeanUtils.copyProperties(bean, exportUser);
			exportUserDao.save(exportUser);
		}


		@Override
		public List<SwjQuestion> findGiftingMoneyUserQuestionId() {
			List<SwjQuestion> list=followUserInfoDao.findGiftingMoneyUserQuestionId();
			return list;
		}
}
