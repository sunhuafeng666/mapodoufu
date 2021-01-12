package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class MainResponse extends ProcessingResult{

	public MainResponse() {};
	
	List<Main> MainList =  new ArrayList<>();

	public List<Main> getMainList() {
		return MainList;
	}

	public void setMainList(List<Main> mainList) {
		MainList = mainList;
	}
	
	
}
