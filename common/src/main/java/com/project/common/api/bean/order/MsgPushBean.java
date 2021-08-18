package com.project.common.api.bean.order;

import java.util.List;

public class MsgPushBean {

    private String code;

    private List<MsgPushDataBean> data ;

    private String errorMes;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(List<MsgPushDataBean> data){
        this.data = data;
    }
    public List<MsgPushDataBean> getData(){
        return this.data;
    }
    public void setErrorMes(String errorMes){
        this.errorMes = errorMes;
    }
    public String getErrorMes(){
        return this.errorMes;
    }


    @Override
    public String toString() {
        return "MsgPushBean{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", errorMes='" + errorMes + '\'' +
                '}';
    }

}