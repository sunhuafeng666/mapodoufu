package com.louis.mango.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Override
	public List<SysUser> selectAll() {
		// TODO Auto-generated method stub
		return sysUserMapper.selectAll();  
	}

}
