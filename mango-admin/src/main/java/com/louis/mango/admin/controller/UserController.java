package com.louis.mango.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.LoginResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.UserRequestBean;
import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.UserService;

@RestController
@RequestMapping("/")
@Transactional
public class UserController {

	@Autowired
	private UserService UserService;

	@RequestMapping("/addUser")
	public ProcessingResult insert(@Validated UserRequestBean record) {

		ProcessingResult pr = new ProcessingResult();
		User userInfoBean = new User();
		userInfoBean.setPhone(record.getPhone());
		userInfoBean.setNickname(record.getNickname());
		userInfoBean.setPassword(record.getPassword());
		try {
			pr = UserService.insert(userInfoBean);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setResultId("C001");
			pr.setResultContent("ControllerErrpr" + e.getMessage());
		}

		return pr;
	}

	@RequestMapping("/login")
	public LoginResponse loginProcessingResult(User user) {
		LoginResponse pr = new LoginResponse();
		try {
			pr = UserService.loginProcessingResult(user);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setResultId("C001");
			pr.setResultContent("ControllerErrpr" + e.getMessage());
		}

		return pr;
	}

	@RequestMapping("/selectByphone")
	public LoginResponse selectByphone(String phone) {
		LoginResponse pr = new LoginResponse();
		try {
			pr = UserService.selectByPhone(phone);
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setResultId("C001");
			pr.setResultContent("ControllerErrpr" + e.getMessage());
		}
		return pr;
	}

}
