package com.louis.mango.admin.model;

public class User {
	private Integer id;

	private String phone;

	private String password;

	private String nickname;

	private Integer level;

	private Integer isDelete;

	private String registTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime == null ? null : registTime.trim();
	}
}