package com.louis.mango.admin.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StableInfoRequestBean {
	
    public StableInfoRequestBean(String idname, String memo, LocalDateTime starttime ){
        this.idname = idname;
        this.memo = "空";
        this.starttime = starttime;
    }
	@NotBlank(message = "活动副本名不能为空")
    private String idname;

    private String memo;
    
    @NotNull(message = "人数不能为空")
    @Range(min = 2,max = 15,message = "人数必须大于2小于15")    
	private Integer peoplenum;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+9")
    private LocalDateTime starttime;

    private String register;
    
    public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname == null ? null : idname.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }
    
    public Integer getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}
    
}