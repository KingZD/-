package com.wh.bdgw.callback;

/**
 * 文件名:LoginCallBack
 * 创建者:zed
 * 创建日期:2019/3/28 11:18
 * 描述:TODO
 */
public interface LoginCallBack<T> {
    void loginSuccess(T data);
    void loginFailed();
}
