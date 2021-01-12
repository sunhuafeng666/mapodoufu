package com.louis.mango.core.page;

import org.springframework.util.ReflectionUtils;

import com.github.pagehelper.PageHelper;

public class MybatisPageHelper {
	
	public static final String findPage="findPage";

	public static PageResult findPage(PageRequest pageRequest,Object mapper) {
		 return findPage(pageRequest,mapper,findPage);
}

	@SuppressWarnings({"unchecked","rawtypes"})
	public static PageResult findPage(PageRequest pageRequest,Object mapper,String queryMethodName,Object...args) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		
		PageHelper.startPage(pageNum, pageSize);
		PageResult rageResult = new PageResult();
//		Object result = ReflectionUtils.invoke(mapper, queryMethodName,args);
		return rageResult;
				
}
}
