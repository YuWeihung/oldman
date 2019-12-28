package com.tongji.oldman.response;

import com.tongji.oldman.entity.User;


public class UserResponse {
    private Integer success;
    private Integer uid;
    private String name;
    private String avatar;
    private Integer admin;
    private Integer wrongpassword;
    private Integer notexist;
    private Integer noauthorization;

    public UserResponse(Integer success, User user, Integer wrongpassword) {
        this.success = success;
        this.uid = user.getUid();
        this.admin = user.getAdmin();
        this.avatar = user.getAvatar();
        this.name = user.getName();
        this.wrongpassword = wrongpassword;
        this.notexist = -1;
        this.noauthorization = -1;
    }

    public UserResponse(Integer success, Integer wrongpassword) {
        this.success = success;
        this.uid = -1;
        this.name = "";
        this.avatar = "";
        this.admin = -1;
        this.wrongpassword = wrongpassword;
        this.notexist = -1;
        this.noauthorization = -1;
    }

    public UserResponse(Integer success, Integer notexist, Integer noauthorization) {
        this.success = success;
        this.uid = -1;
        this.name = "";
        this.avatar = "";
        this.admin = -1;
        this.wrongpassword = -1;
        this.notexist = notexist;
        this.noauthorization = noauthorization;
    }

    public UserResponse(Integer success) {
        this.success = success;
        this.uid = -1;
        this.name = "";
        this.avatar = "";
        this.admin = -1;
        this.wrongpassword = -1;
        this.notexist = -1;
        this.noauthorization = -1;
    }

    public void setNoauthorization(Integer noauthorization) {
        this.noauthorization = noauthorization;
    }

    public void setNotexist(Integer notexist) {
        this.notexist = notexist;
    }

    public Integer getNoauthorization() {
        return noauthorization;
    }

    public Integer getNotexist() {
        return notexist;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setWrongpassword(Integer wrongpassword) {
        this.wrongpassword = wrongpassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public Integer getAdmin() {
        return admin;
    }

    public Integer getSuccess() {
        return success;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getWrongpassword() {
        return wrongpassword;
    }
}
