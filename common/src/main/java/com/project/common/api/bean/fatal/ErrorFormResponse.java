package com.project.common.api.bean.fatal;

public class ErrorFormResponse {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public long getTimestamp() { return timestamp; }
    public void setStatus(int status) { this.status = status; }
    public int getStatus() { return status; }
    public void setError(String error) { this.error = error; }
    public String getError() { return error; }
    public void setMessage(String message) { this.message = message; }
    public String getMessage() { return message; }
    public void setPath(String path) { this.path = path; }
    public String getPath() { return path; }

}