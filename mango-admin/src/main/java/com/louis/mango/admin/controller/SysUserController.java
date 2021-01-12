package com.louis.mango.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.service.SysUserService;

@RestController
@RequestMapping("/")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@GetMapping(value="/1")
	public Object s() {
		return sysUserService.selectAll();
	}
}
