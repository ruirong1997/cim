package com.project.cim.mode;

public class LoginInfo {

    public String name;

    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
