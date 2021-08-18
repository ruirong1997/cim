package com.project.common.api.bean.user;

/**
 *  获取用户列表
 *  /user/list
 *  返回值
 */
public class FriendsListBean {

    private String departName;
    private int id;
    private String img;
    private String pass;
    private String phoneNumber;
    private String state;
    private String title;
    private String userId;
    private String userName;
    public void setDepartName(String departName) {
        this.departName = departName;
    }
    public String getDepartName() {
        return departName;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getPass() {
        return pass;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

}
