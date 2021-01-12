package com.louis.mango.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		System.out.println("welcome");
		return "login";
	}


}
