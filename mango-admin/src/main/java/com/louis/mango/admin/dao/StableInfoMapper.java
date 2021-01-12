package com.louis.mango.admin.dao;

import java.util.List;

import com.louis.mango.admin.model.StableInfo;

public interface StableInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(StableInfo record);

	StableInfo selectByPrimaryKey(Integer id);

	List<StableInfo> selectAll();

	int updateByPrimaryKey(StableInfo record);

	List<StableInfo> selectByIdName(String idNameString);
	
	List<String> selectAllIdName();
}