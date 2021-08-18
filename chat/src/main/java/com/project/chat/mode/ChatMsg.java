package com.project.chat.mode;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

@Message
public class ChatMsg {
    @Index(0)
    private String msg_type;

    @Index(1)
    private String sendUid;

    @Index(2)
    private String recUid;

    @Index(3)
    private String msg;

    @Index(4)
    private String time;

    public ChatMsg(){ }

    /**
     * @param msg_type    消息类型  （普通消息 、图片 、 （语音 、视频） 、）
     * @param sendUid     发送者ID
     * @param recUid      接收者ID
     * @param msg         消息内容
     * @param time        sendUid发送时间
     */
    public ChatMsg(String msg_type, String sendUid, String recUid, String msg, String time) {
        this.msg_type = msg_type;
        this.sendUid = sendUid;
        this.recUid = recUid;
        this.msg = msg;
        this.time = time;
    }


    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public String getRecUid() {
        return recUid;
    }

    public void setRecUid(String recUid) {
        this.recUid = recUid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "msg_type='" + msg_type + '\'' +
                ", sendUid='" + sendUid + '\'' +
                ", recUid='" + recUid + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
