package com.louis.mango.admin.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestBean {

	@NotBlank(message = "电话号不能为空")
	@Digits(integer = 4, fraction = 0, message = "电话号必须是数字")
	@Size(min = 4, max = 4, message = "电话号必须是四位数")
	private String phone;

	@NotBlank(message = "密码不能为空")
	private String password;

	@NotBlank(message = "游戏名不能为空")
	private String nickname;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}