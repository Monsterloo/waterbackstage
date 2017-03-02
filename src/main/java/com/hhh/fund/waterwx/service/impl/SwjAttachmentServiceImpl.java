package com.hhh.fund.waterwx.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.fund.waterwx.dao.SwjAttachmentDao;
import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.waterwx.service.SwjAttachmentService;

@Component
@Transactional
public class SwjAttachmentServiceImpl implements SwjAttachmentService{

	@Autowired
	private SwjAttachmentDao swjAttachmentDao;

	@Override
	public SwjAttachment getAttchmentImageById(String attchId) {
		return swjAttachmentDao.findById(attchId);
	}

	@Override
	public List<String> getIdbyQuestionId(String dataId) {
		return swjAttachmentDao.findAttachIdByQuestionId(dataId);
	}

	@Override
	public String save(SwjAttachment attachment) {
		return swjAttachmentDao.save(attachment).getId();
	}

	@Override
	public List<Object[]> findAllIds() {
		return swjAttachmentDao.findAllIds();
	}

	@Override
	public List<SwjAttachment> findByQuestionId(String questionId) {
		return swjAttachmentDao.findByQuestionId(questionId);
	}

	@Override
	public List<String> findAttachIdByQuestionId(String id) {
		return swjAttachmentDao.findAttachIdByQuestionId(id);
	}
	
	
	
	
}
