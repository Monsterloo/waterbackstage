package com.hhh.fund.waterwx.service;

import com.hhh.fund.waterwx.entity.SwjRivermastercensus;
import com.hhh.fund.waterwx.webmodel.RiverMasterBoardListJsonBean;
import com.hhh.fund.waterwx.webmodel.RiverMasterCensusListJsonBean;

public interface SwjRiverMasterCensusService {

	public void save(SwjRivermastercensus swjRivermastercensus);
	
	public RiverMasterCensusListJsonBean findByArea(String area);
	
	public RiverMasterCensusListJsonBean findAllRiverMasterBoard();
}
