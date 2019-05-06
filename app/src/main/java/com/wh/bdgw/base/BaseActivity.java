package com.wh.bdgw.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wh.bdgw.activity.LoginActivity;
import com.wh.bdgw.callback.LoginCallBack;
import com.wh.bdgw.net.model.bean.UserInfoBean;
import com.wh.bdgw.constant.SpConstant;
import com.wh.bdgw.dialog.LoadingDialog;
import com.wh.bdgw.util.SPUtils;
import com.wh.bdgw.util.StatusBarUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends IBasePersistent> extends RxAppCompatActivity implements BaseView{
    //当前类是否处理ui逻辑
    protected final int disableLayout = -10086;
    Unbinder bind;
    //loading
    private LoadingDialog loadingDialog;
    //登录回调
    private LoginCallBack<UserInfoBean> mLoginCallBack;
    protected T persistent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果 id==disableLayout 则不需要 处理当前逻辑 交给子类处理
        int layoutId = setLayoutId();
        if (disableLayout != layoutId) {
            setContentView(layoutId);
            StatusBarUtil.setTranslucentForImageView(this, 0, null);
            bind = ButterKnife.bind(this);
            initView();
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
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
        if (bind != null)
            bind.unbind();
        if(persistent!=null)
            persistent.onDestroy();
        persistent = null;
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.setCancelable(true);
        }
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.hide();
        }
    }

    private void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
    /**
     * 获取父类
     *
     * @return 父类
     */
    @Override
    public <A extends BaseActivity> A getCurrentActivity() {
        return (A) this;
    }

    @Override
    public UserInfoBean getUserInfo() {
        String info = SPUtils.getInstance().getString(SpConstant.APP_USER_INFO);
        if (TextUtils.isEmpty(info)) {
            return new UserInfoBean();
        }
        return new Gson().fromJson(info, UserInfoBean.class);
    }

    public abstract int setLayoutId();

    public abstract void initView();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SpConstant.LOGIN_CODE && mLoginCallBack != null) {
            if (resultCode == RESULT_OK) {
                //一般登录完成后都会将登录后的信息保存起来 这里直接取缓存
                mLoginCallBack.loginSuccess(getUserInfo());
            } else {
                mLoginCallBack.loginFailed();
            }
        }
        //循环传递消息
        List<Fragment> frags = getSupportFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public <B> LifecycleTransformer<B> getActLifeRecycle() {
        return this.bindUntilEvent(ActivityEvent.DESTROY);
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
        if (getUserInfo() != null && getUserInfo().isLogin()) {
            return true;
        } else {
            jumpLogin(callBack);
            return false;
        }
    }

    //跳转登录
    protected void jumpLogin(LoginCallBack<UserInfoBean> callBack) {
        startActivityForResult(new Intent(this, LoginActivity.class), SpConstant.LOGIN_CODE);
        mLoginCallBack = callBack;
    }
}
