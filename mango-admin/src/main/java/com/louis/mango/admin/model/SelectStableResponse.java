package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class SelectStableResponse extends ProcessingResult{

	public SelectStableResponse() {};
	
	List<Stable> StableInfoList =  new ArrayList<>();

	public List<Stable> getStableInfoList() {
		return StableInfoList;
	}

	public void setStableInfoList(List<Stable> stableInfoList) {
		StableInfoList = stableInfoList;
	}
	
	
	
}
