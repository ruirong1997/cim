package com.project.common.api.bean.order;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.project.common.mode.ItemEntity;

import java.io.Serializable;

public class MsgPushDataBean implements MultiItemEntity, Serializable {

    private String completeTime;

    private String dispatchTime;

    private String faultCd;

    private String faultDesc;

    private int faultId;

    private String fixUser;

    private int id;

    private String img;

    private String reportUser;

    private String responseTime;

    private String state;

    public void setCompleteTime(String completeTime){
        this.completeTime = completeTime;
    }
    public String getCompleteTime(){
        return this.completeTime;
    }
    public void setDispatchTime(String dispatchTime){
        this.dispatchTime = dispatchTime;
    }
    public String getDispatchTime(){
        return this.dispatchTime;
    }
    public void setFaultId(int faultId){
        this.faultId = faultId;
    }
    public int getFaultId(){
        return this.faultId;
    }
    public void setFixUser(String fixUser){
        this.fixUser = fixUser;
    }
    public String getFixUser(){
        return this.fixUser;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setReportUser(String reportUser){
        this.reportUser = reportUser;
    }
    public String getReportUser(){
        return this.reportUser;
    }
    public void setResponseTime(String responseTime){
        this.responseTime = responseTime;
    }
    public String getResponseTime(){
        return this.responseTime;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public String getFaultCd() { return faultCd; }
    public void setFaultCd(String faultCd) { this.faultCd = faultCd; }
    public String getFaultDesc() { return faultDesc; }
    public void setFaultDesc(String faultDesc) { this.faultDesc = faultDesc; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public MsgPushDataBean(String completeTime, String dispatchTime, String faultCd, String faultDesc, int faultId, String fixUser, int id, String img, String reportUser, String responseTime, String state) {
        this.completeTime = completeTime;
        this.dispatchTime = dispatchTime;
        this.faultCd = faultCd;
        this.faultDesc = faultDesc;
        this.faultId = faultId;
        this.fixUser = fixUser;
        this.id = id;
        this.img = img;
        this.reportUser = reportUser;
        this.responseTime = responseTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "MsgPushDataBean{" +
                "completeTime='" + completeTime + '\'' +
                ", dispatchTime='" + dispatchTime + '\'' +
                ", faultCd='" + faultCd + '\'' +
                ", faultDesc='" + faultDesc + '\'' +
                ", faultId=" + faultId +
                ", fixUser='" + fixUser + '\'' +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", reportUser='" + reportUser + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        if (getImg().equals("")){
            return ItemEntity.ITEM_NOTING;
        }else {
            return ItemEntity.ITEM_Picture;
        }
    }
}