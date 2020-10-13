package com.gupaoedu.live.gupaoedulive.core;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/28-13:31
 */
public class ResponseData {

    private int code;
    private String msg;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
