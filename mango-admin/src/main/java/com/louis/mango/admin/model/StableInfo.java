package com.louis.mango.admin.model;

import java.time.LocalDateTime;

public class StableInfo {
    private Integer id;

    private String idname;

    private String register;

    private String memo;

    private Integer peoplenum;

    private LocalDateTime registtime;
    
    private LocalDateTime starttime;

    private Integer isDelete;
    
    private Integer idnameteamnum;

    public Integer getIdnameteamnum() {
		return idnameteamnum;
	}

	public void setIdnameteamnum(Integer idnameteamnum) {
		this.idnameteamnum = idnameteamnum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname == null ? null : idname.trim();
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? "无描述" : memo.trim();
    }

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public LocalDateTime getRegisttime() {
        return registtime;
    }

    public void setRegisttime(LocalDateTime localDateTime) {
        this.registtime = localDateTime;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}