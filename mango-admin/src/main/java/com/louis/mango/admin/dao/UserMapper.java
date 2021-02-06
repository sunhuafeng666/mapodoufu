package com.louis.mango.admin.dao;

import java.util.List;

import com.louis.mango.admin.model.User;

public interface UserMapper {
	//新增用户
	int insert(User record);
	//找所有用户
	List<User> selectAll();
	//用过电话找用户
	List<User> selectByPhone(String phone);
	//通过游戏名找用户
	List<User> selectByNickname(String nickname);
	//登录用
	List<User> selectByPhoneAndPassword(String phone, String password);
	//通过主键来更新用户
	int updateByPrimaryKey(User record);
}