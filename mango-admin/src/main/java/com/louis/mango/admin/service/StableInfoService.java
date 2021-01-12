package com.louis.mango.admin.service;

import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectAllStableNameResponse;
import com.louis.mango.admin.model.SelectStableNameInfoResponse;
import com.louis.mango.admin.model.StableInfo;

public interface StableInfoService {

	SelectStableNameInfoResponse selectAllStableInfos();
	
	ProcessingResult insertStableInfo(StableInfo stableInfo);

	SelectAllStableNameResponse selectAllStableIdname();
	
	
}
