package com.louis.mango.admin.dao;

import java.util.List;

import com.louis.mango.admin.model.Main;

/**
 * @author sunhuafeng
 *
 */
public interface MainMapper {
    
    List<Main> selectByIdNameAndTeamNum(String idname);
    
}