package com.louis.mango.core.service;

import java.util.List;

import com.louis.mango.core.page.PageResult;

public interface CurdService<T> {
	int save(T record);
	
	int delete(T record);
	
	int delete(List<T> record);
	
	T findByid(Long id);
	
	PageResult findPage(PageResult pageresult);
	
}
