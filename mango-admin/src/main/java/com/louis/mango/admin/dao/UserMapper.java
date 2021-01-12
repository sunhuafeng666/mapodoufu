package com.louis.mango.admin.dao;

import java.util.List;

import com.louis.mango.admin.model.User;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();
    
    List<User> selectByPhone(String phone);
    List<User> selectByNickname(String nickname);
    List<User> selectByPhoneAndPassword(String phone, String password);
}