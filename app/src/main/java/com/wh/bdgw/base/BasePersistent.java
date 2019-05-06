package com.wh.bdgw.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wh.bdgw.net.model.bean.UserInfoBean;

/**
 * 文件名:BasePersistent
 * 创建者:zed
 * 创建日期:2019/3/22 15:00
 * 描述:通用
 */
public abstract class BasePersistent<T extends BaseView> implements IBasePersistent<T> {
    protected T mBaseView;

    public BasePersistent(T baseView) {
        this.mBaseView = baseView;
        init();
    }

    protected <A extends BaseActivity> A getCurrentActivity() {
        return mBaseView == null ? null : (A) mBaseView.getCurrentActivity();
    }

    /**
     * 获取view 对象
     * @return
     */
    protected T getView() {
        return mBaseView;
    }

    @Override
    public void onDestroy() {
        mBaseView = null;
    }

    /**
     * 初始化列表参数配置
     */
    protected abstract void init();

    /**
     * 生命周期
     * @param <B>
     * @return
     */
    protected <B> LifecycleTransformer<B> getActLifeRecycle() {
        if (mBaseView == null) return null;
        return mBaseView.getActLifeRecycle();
    }

    /**
     * 用户登录信息
     * @return
     */
    protected UserInfoBean getUserInfo() {
        if (mBaseView == null) return new UserInfoBean();
        return mBaseView.getUserInfo();
    }
}
