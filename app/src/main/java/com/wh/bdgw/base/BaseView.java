package com.wh.bdgw.base;


import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wh.bdgw.net.model.bean.UserInfoBean;

/**
 * 文件名:BaseView
 * 创建者:zed
 * 创建日期:2019/3/22 15:01
 * 描述:TODO
 */
public interface BaseView {
    <A extends BaseActivity> A getCurrentActivity();
    <B> LifecycleTransformer<B> getActLifeRecycle();
    void showLoading();
    void hideLoading();
    UserInfoBean getUserInfo();
}
