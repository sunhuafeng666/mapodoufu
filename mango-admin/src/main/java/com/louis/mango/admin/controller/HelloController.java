package com.louis.mango.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(value="/hello")
	public Object hello() {
		return "asdf1111111122222222222222222222111111gsdfg";
	}
}
