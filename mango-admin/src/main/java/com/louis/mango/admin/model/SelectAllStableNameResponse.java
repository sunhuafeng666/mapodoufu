package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class SelectAllStableNameResponse extends ProcessingResult{

	public SelectAllStableNameResponse (){};
	
	List<String> list = new ArrayList<String>();

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
