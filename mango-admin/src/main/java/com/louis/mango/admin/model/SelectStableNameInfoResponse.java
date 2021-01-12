package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class SelectStableNameInfoResponse extends ProcessingResult{

	public SelectStableNameInfoResponse (){};
	
	List<StableInfo> list = new ArrayList<StableInfo>();

	public List<StableInfo> getList() {
		return list;
	}

	public void setList(List<StableInfo> list) {
		this.list = list;
	}
	
	
	
}
