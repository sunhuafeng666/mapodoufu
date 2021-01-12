package com.louis.mango.admin.model;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private String idname;

    private String register;

    private String memo;

    private Integer peoplenum;
    
    private LocalDateTime starttime;
    
    private Integer idnameteamnum;
    
    private List<String> nickname;

	public String getIdname() {
		return idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}

	public LocalDateTime getStarttime() {
		return starttime;
	}

	public void setStarttime(LocalDateTime starttime) {
		this.starttime = starttime;
	}

	public Integer getIdnameteamnum() {
		return idnameteamnum;
	}

	public void setIdnameteamnum(Integer idnameteamnum) {
		this.idnameteamnum = idnameteamnum;
	}

	public List<String> getNickname() {
		return nickname;
	}

	public void setNickname(List<String> nickname) {
		this.nickname = nickname;
	}
}
