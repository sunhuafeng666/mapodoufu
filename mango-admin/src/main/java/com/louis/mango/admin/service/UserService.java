package com.louis.mango.admin.service;

import java.util.List;

import com.louis.mango.admin.model.LoginResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.User;

public interface UserService {
	//获得所有用户
    List<User> selectAll();
    //新增用户
    ProcessingResult insert(User record);
    //登陆用
    LoginResponse loginProcessingResult(User user);
    //查询所有电话号
    LoginResponse selectByPhone(String phone);
}
