package com.hhh.fund.waterwx.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.PollutantSource;
import com.hhh.fund.waterwx.webmodel.PollutantSourceBean;

@Repository
public interface SwjPollutantSourceDao extends SpecificationsRepository<PollutantSource, String> {

	@Query("SELECT u FROM  PollutantSource u where u.rivercode=:rivercode and u.area=:area and u.pollsourcename=:pollsourcename and u.polldescription=:polldescription")
	PollutantSource findOnlyOneRecord(@Param("rivercode")String rivercode,@Param("area")String area,@Param("pollsourcename")String pollsourcename,@Param("polldescription")String polldescription);

}
