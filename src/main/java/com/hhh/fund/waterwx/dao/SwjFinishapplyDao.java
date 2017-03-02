package com.hhh.fund.waterwx.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.SwjFinishapply;
import com.hhh.fund.waterwx.webmodel.SwjFinishapplyBean;


@Repository
public interface SwjFinishapplyDao extends SpecificationsRepository<SwjFinishapply, String> {

	/**
	 * 按投诉id查询
	 * */
	@Query("select f from SwjFinishapply f where f.dataId=:dataId and f.status='0' ")
	public SwjFinishapply findByDataId(@Param("dataId") String dataId);

}
