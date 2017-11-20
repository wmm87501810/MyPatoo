package com.ipatoo.mypatoo.bean;

/**
 * Created by KevenTao on 2017/11/6.
 */

public class AbsResponse<T> {

    public final static int SUCCESS_CODE = 200;

    private int code = -1;

    private String msg;
    private T data;
    private Alert alert;

    public boolean isSuccess() {
        if (code != -1) {
            return SUCCESS_CODE == code;
        } else {
            return false;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
