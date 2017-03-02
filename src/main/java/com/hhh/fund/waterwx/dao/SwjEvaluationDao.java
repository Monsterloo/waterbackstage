package com.hhh.fund.waterwx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjEvaluation;

@Repository
public interface SwjEvaluationDao extends SpecificationsRepository<SwjEvaluation, String>{

	@Modifying
	@Query("update SwjEvaluation set status='1' where questionId=:questionId")
	public void updateStatusByItemId(@Param("questionId")String questionId);

	/**
	 * 按投诉id查询
	 * */
	@Query("select distinct f from SwjEvaluation f where f.questionId=:questionId and f.status='0' order by createTime desc")
	public SwjEvaluation findByDataId(@Param("questionId") String questionId);
}

