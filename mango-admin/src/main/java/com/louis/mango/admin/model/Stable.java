package com.louis.mango.admin.model;

public class Stable {
    private Integer id;

    private String idname;

    private Integer idnameteamnum;

    private String nickname;

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

    public Integer getIdnameteamnum() {
        return idnameteamnum;
    }

    public void setIdnameteamnum(Integer idnameteamnum) {
        this.idnameteamnum = idnameteamnum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
}