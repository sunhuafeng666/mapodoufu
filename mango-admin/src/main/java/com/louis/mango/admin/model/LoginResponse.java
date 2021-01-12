package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse extends ProcessingResult{
	
	public LoginResponse(){};
	
	List<User> userInfoList = new ArrayList<>();

	public List<User> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<User> userInfoList) {
		this.userInfoList = userInfoList;
	}



}
