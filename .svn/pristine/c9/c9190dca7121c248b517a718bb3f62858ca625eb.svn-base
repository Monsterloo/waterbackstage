package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjAttachment;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjAttachmentDao extends SpecificationsRepository<SwjAttachment, String> {
	
	@Query("select t from SwjAttachment t where t.questionId=:questionId ")
	public List<SwjAttachment> findByQuestionId(@Param("questionId")String id);

	public SwjAttachment findById(String id);
	@Query("select t.id from SwjAttachment t where t.questionId=:questionId ")
	public List<String> findAttachIdByQuestionId(@Param("questionId")String id);
	
	@Query(" select item.id from SwjAttachment  item ")
	public List<Object[]> findAllIds();

}
