package com.louis.mango.admin.service;

import com.louis.mango.admin.model.MainResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectStableResponse;
import com.louis.mango.admin.model.Stable;


public interface StableService {
	ProcessingResult insertStable(Stable stable);
	
	ProcessingResult deleteByPrimaryKey(Integer id);
	
	SelectStableResponse selectAllStable();
	
	MainResponse selectAllStableByIdname(String idName);
	
}
