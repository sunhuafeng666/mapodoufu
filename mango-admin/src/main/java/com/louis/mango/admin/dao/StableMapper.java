package com.louis.mango.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.louis.mango.admin.model.Stable;

/**
 * @author sunhuafeng
 *
 */
public interface StableMapper {
    int deleteByPrimaryKey(Integer id);

    int insertStable(Stable record);

    List<Stable> selectByPrimaryKey(Integer id);

    List<Stable> selectAll();

    int updateByPrimaryKey(Stable record);
    
    List<Stable> selectStablesByNicknameAndIdname(@Param("nickname")String nickname, @Param("idname")String idname, @Param("idnameteamnum")Integer idnameteamnum);
    
    List<Stable> selectByIdName(String idname);
    
}