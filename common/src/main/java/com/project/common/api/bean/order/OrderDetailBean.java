package com.project.common.api.bean.order;

public class OrderDetailBean {

    private String code;

    private OrderDetailDataBean data;

    private String errorMes;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(OrderDetailDataBean data){
        this.data = data;
    }
    public OrderDetailDataBean getData(){
        return this.data;
    }
    public void setErrorMes(String errorMes){
        this.errorMes = errorMes;
    }
    public String getErrorMes(){
        return this.errorMes;
    }
}
