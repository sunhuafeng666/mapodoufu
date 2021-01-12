package com.louis.mango.admin.service;

import java.util.List;

import com.louis.mango.admin.model.LoginResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.User;

public interface UserService {
    List<User> selectAll();
    ProcessingResult insert(User record);
    LoginResponse loginProcessingResult(User user);
    
    LoginResponse selectByPhone(String phone);
}
