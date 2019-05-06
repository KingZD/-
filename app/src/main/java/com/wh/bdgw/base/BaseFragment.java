package com.wh.bdgw.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wh.bdgw.callback.LoginCallBack;
import com.wh.bdgw.net.model.bean.UserInfoBean;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends IBasePersistent> extends RxFragment implements BaseView {
    //当前类是否处理ui逻辑
    protected final int disableLayout = -10086;
    Unbinder bind;
    protected T persistent = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int setLayoutId = setLayoutId();
        if (disableLayout != setLayoutId) {
            View view = inflater.inflate(setLayoutId, container, false);
            bind = ButterKnife.bind(this, view);
            initView();
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     *设置P
     * @param persistent p
     */
    public void setPersistent(T persistent) {
        this.persistent = persistent;
    }

    @Override
    public void showLoading() {
        if (getCurrentActivity() == null) return;
        getCurrentActivity().showLoading();
    }

    @Override
    public void hideLoading() {
        if (getCurrentActivity() == null) return;
        getCurrentActivity().hideLoading();
    }

    /**
     * 判断是否登录
     *
     * @return true 登录 false 未登录
     */
    protected boolean isLogin() {
        return isLogin(null);
    }

    /**
     * 判断是否登录
     *
     * @param callBack 如果没有登录 是否需要自动登录
     */
    protected boolean isLogin(LoginCallBack<UserInfoBean> callBack) {
        if (getCurrentActivity() == null) return false;
        return getCurrentActivity().isLogin(callBack);
    }

    @Override
    public <B> LifecycleTransformer<B> getActLifeRecycle() {
        return this.bindUntilEvent(FragmentEvent.DESTROY);
    }

    /**
     * 获取父类
     *
     * @return 父类
     */
    @Override
    public <A extends BaseActivity> A getCurrentActivity() {
        return (A) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(persistent!=null)
            persistent.onDestroy();
        if (bind != null)
            bind.unbind();
        persistent = null;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Override
    public UserInfoBean getUserInfo() {
        if (getCurrentActivity() == null) return new UserInfoBean();
        return getCurrentActivity().getUserInfo();
    }

    public abstract int setLayoutId();

    public abstract void initView();
}
