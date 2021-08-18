package com.project.common.api.bean.fatal;

public class ErrorDetailBean {

    private String code;

    private ErrorDetailDataBean data;

    private String errorMes;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(ErrorDetailDataBean data){
        this.data = data;
    }
    public ErrorDetailDataBean getData(){
        return this.data;
    }
    public void setErrorMes(String errorMes){
        this.errorMes = errorMes;
    }
    public String getErrorMes(){
        return this.errorMes;
    }


}
