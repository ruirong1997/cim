package com.project.common.api.bean.fatal;

public class ErrorDetailDataBean {
    private String devId;

    private String devType;

    private String faultCd;

    private String faultDegree;

    private String faultDesc;

    private String faultState;

    private String from;

    private int id;

    private String img ;

    private String location;

    private String original;

    private String reportTime;

    private String reportUser;

    public void setDevId(String devId){
        this.devId = devId;
    }
    public String getDevId(){
        return this.devId;
    }
    public void setDevType(String devType){
        this.devType = devType;
    }
    public String getDevType(){
        return this.devType;
    }
    public void setFaultCd(String faultCd){
        this.faultCd = faultCd;
    }
    public String getFaultCd(){
        return this.faultCd;
    }
    public void setFaultDegree(String faultDegree){
        this.faultDegree = faultDegree;
    }
    public String getFaultDegree(){
        return this.faultDegree;
    }
    public void setFaultDesc(String faultDesc){
        this.faultDesc = faultDesc;
    }
    public String getFaultDesc(){
        return this.faultDesc;
    }
    public void setFaultState(String faultState){
        this.faultState = faultState;
    }
    public String getFaultState(){
        return this.faultState;
    }
    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return this.from;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setOriginal(String original){
        this.original = original;
    }
    public String getOriginal(){
        return this.original;
    }
    public void setReportTime(String reportTime){
        this.reportTime = reportTime;
    }
    public String getReportTime(){
        return this.reportTime;
    }
    public void setReportUser(String reportUser){
        this.reportUser = reportUser;
    }
    public String getReportUser(){
        return this.reportUser;
    }
}
