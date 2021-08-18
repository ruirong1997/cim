package com.project.common.api.bean.user;

import com.google.gson.annotations.SerializedName;

public class LoginBean {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private LoginDataBean data;

    @SerializedName("errorMes")
    private String errorMes;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginDataBean getData() {
        return data;
    }

    public void setData(LoginDataBean data) {
        this.data = data;
    }

    public String getErrorMes() {
        return errorMes;
    }

    public void setErrorMes(String errorMes) {
        this.errorMes = errorMes;
    }




}
