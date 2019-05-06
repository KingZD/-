package com.wh.bdgw.net;

import android.text.TextUtils;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private final static String STATUS_SUCCEED = "0";

    private String Code;

    private String Message;

    private String Msg;

    private boolean Status;

    private String version;

    private T Data;

    public BaseResponse() {
    }

    public boolean isSucceed() {
        return TextUtils.equals(Code, STATUS_SUCCEED);
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
