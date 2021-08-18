package com.project.common.api.bean.fatal;


/**
 * 新增故障信息
 * /fault/add
 */
public class ErrorForm {

    private String devId;

    private String devType;

    private String faultCd;

    private String faultDegree;

    private String faultDesc;

    private String faultState;

//   private String from;

//    private int id;

//    private String img;

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
//    public void setFrom(String from){
//        this.from = from;
//    }
//    public String getFrom(){
//        return this.from;
//    }
//    public void setId(int id){
//        this.id = id;
//    }
//    public int getId(){
//        return this.id;
//    }
//    public void setImg(String img){
//        this.img = img;
//    }
//    public String getImg(){
//        return this.img;
//    }
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

    public ErrorForm(){};

    public ErrorForm(String devId, String devType, String faultCd, String faultDegree, String faultDesc, String faultState, String from, String img, String location, String original, String reportTime, String reportUser) {
        this.devId = devId;               //设备ID
        this.devType = devType;           //设备类型
        this.faultCd = faultCd;           //故障类型
        this.faultDegree = faultDegree;   //故障等级
        this.faultDesc = faultDesc;       //故障描述
        this.faultState = faultState;     //未分配
//        this.from = from;                 //故障来源
//        this.id = id;                     //id就故障记录标识，自增类型，不填也可
//        this.img = img;                   //图片
        this.location = location;         //车站
        this.original = original;         //终端
        this.reportTime = reportTime;     //提交时间
        this.reportUser = reportUser;     //提交者
    }


    @Override
    public String toString() {
        return "{" +
                "\"devId\":\"" + devId + '\"' +
                ",\"devType\":\"" + devType + '\"' +
                ",\"faultCd\":\"" + faultCd + '\"' +
                ",\"faultDegree\":\"" + faultDegree + '\"' +
                ",\"faultDesc\":\"" + faultDesc + '\"' +
                ",\"faultState\":\"" + faultState + '\"' +
                ",\"from\": NULL"  +
                ",\"img\": NULL" +
                ",\"location\":\"" + location + '\"' +
                ",\"original\":\"" + original + '\"' +
                ",\"reportTime\":\"" + reportTime + '\"' +
                ",\"reportUser\":\"" + reportUser + '\"' +
                '}';
    }
}
