package com.louis.mango.admin.dao;

import java.util.List;


import com.louis.mango.admin.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(String id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
    
}