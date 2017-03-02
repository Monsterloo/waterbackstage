package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjFeedback;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjFeedbackDao extends SpecificationsRepository<SwjFeedback, String> {
	public Page<SwjFeedback> findByQuestionIdOrderByFeedbackdateDesc(String questionId,Pageable page); 
	public List<SwjFeedback> findByQuestionIdOrderByFeedbackdateDesc(String questionId);
}
