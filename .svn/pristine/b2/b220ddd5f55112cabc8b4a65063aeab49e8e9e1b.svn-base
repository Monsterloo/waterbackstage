package com.hhh.fund.waterwx.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhh.fund.util.SpecificationsRepository;
import com.hhh.fund.waterwx.entity.OutfallPolluateResource;
import com.hhh.fund.waterwx.webmodel.OutfallPolluateResourceBean;

@Repository
public interface SwjOutfallPolluateResourceDao  extends SpecificationsRepository<OutfallPolluateResource, String>{

	@Query("SELECT u FROM  OutfallPolluateResource u where u.rivercode=:rivercode and u.area=:area and u.outfalltype=:outfalltype and u.polldescription=:polldescription")
	OutfallPolluateResource findOnlyOneRecord(@Param("rivercode") String rivercode, @Param("area")String area, @Param("outfalltype")String outfalltype, @Param("polldescription")String polldescription);

}
