package com.project.common.api.bean.order;

public class OrderDetailDataBean {

    private String completeTime;

    private String dispatchTime;

    private int faultId;

    private String fixUser;

    private int id;

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

}
