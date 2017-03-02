package com.hhh.fund.waterwx.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.util.SpecificationsRepository;

public interface SwjAnswerDao extends SpecificationsRepository<SwjAnswer, String> {
	
	public Page<SwjAnswer> findByQuestionId(String qId,Pageable page);
	
	@Query("select s from SwjAnswer s where s.questionId=?1 Order By answerDate desc")
	public List<SwjAnswer> findLastAnswerByQuestionId(String questionId);
	
	public List<SwjAnswer> findByQuestionIdOrderByAnswerDate(String qId);
	
}
