package com.project.chat.mode;


/**
 * 通信内容
 */
public class MsgContext {


    //消息内容
    private String context;

    //图片路径
    private String imgPath;

    //发送者
    private String sendUser;
    //发送者Uid（唯一）
    private String sendUid;

    //接收者
    private String recUser;
    //接收者Uid（唯一）
    private String recUid;

    //发送的消息类型
    private String msg_type;

    //发送时间戳
    private String time;

    public MsgContext(String context, String imgPath, String sendUser, String sendUid, String recUser, String recUid, String msg_type, String time) {
        this.context = context;
        this.imgPath = imgPath;
        this.sendUser = sendUser;
        this.sendUid = sendUid;
        this.recUser = recUser;
        this.recUid = recUid;
        this.msg_type = msg_type;
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public String getRecUser() {
        return recUser;
    }

    public void setRecUser(String recUser) {
        this.recUser = recUser;
    }

    public String getRecUid() {
        return recUid;
    }

    public void setRecUid(String recUid) {
        this.recUid = recUid;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
