package com.project.common.api.bean.user;

import com.google.gson.annotations.SerializedName;

//{
//    "departName": "999",
//    "id": 0,
//    "img": "string",
//    "pass": "123",
//    "phoneNumber": "",
//    "state": "string",
//    "title": "string",
//    "userId": "777",
//    "userName": "132"
//  },
public class LoginDataBean {

    @SerializedName("departName")
    private String departName;

    @SerializedName("id")
    private int id;

    @SerializedName("img")
    private String img;

    @SerializedName("pass")
    private String pass;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("state")
    private String state;

    @SerializedName("title")
    private String title;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userName")
    private String userName;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LoginDataBean(String departName, int id, String img, String pass, String phoneNumber, String state, String title, String userId, String userName) {
        this.departName = departName;
        this.id = id;
        this.img = img;
        this.pass = pass;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.title = title;
        this.userId = userId;
        this.userName = userName;
    }
}
