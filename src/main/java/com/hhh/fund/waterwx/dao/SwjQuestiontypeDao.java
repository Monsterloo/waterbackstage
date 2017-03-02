package com.hhh.fund.waterwx.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhh.fund.waterwx.entity.SwjAnswer;
import com.hhh.fund.waterwx.entity.SwjQuestion;
import com.hhh.fund.waterwx.entity.SwjQuestiontype;
import com.hhh.fund.util.SpecificationsRepository;
@Repository
public interface SwjQuestiontypeDao extends SpecificationsRepository<SwjQuestiontype, String> {
	@Query("select distinct detail from SwjQuestiontype order by ordernumber")
	public List<SwjQuestiontype> getAllType();
	
	@Query("select s from SwjQuestiontype s order by ordernumber")
	public Page<SwjQuestiontype> getAllTypeOrderby(Pageable page);
	
	@Query("select s from SwjQuestiontype s order by ordernumber")
	public List<SwjQuestiontype> getAllOrderby();
	
	@Query("SELECT s FROM SwjQuestiontype s")
	public List<SwjQuestiontype> findAllType();
}
