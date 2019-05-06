package com.wh.bdgw.fragment;

import android.view.View;

import com.google.gson.Gson;
import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseTitleFragment;
import com.wh.bdgw.callback.LoginCallBack;
import com.wh.bdgw.net.model.bean.UserInfoBean;
import com.wh.bdgw.util.ToastUtils;

import butterknife.OnClick;

/**
 * 文件名:HomeFragment
 * 创建者:zed
 * 创建日期:2019/4/1 13:47
 * 描述:TODO
 */
public class HomeFragment extends BaseTitleFragment {

    @Override
    public int setBodyId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        setTitleLeft("我是左边", R.mipmap.withdraw_dawenhao);
        setTvTitle("我是标题");
        setTitleRight("我是右边", R.mipmap.withdraw_dianhua);
    }

    @Override
    protected void leftClick(View view) {
        //调用父类 会触发finish 可以去方法里面具体看逻辑 默认设置了 setTitleLeft 之后会实现关闭
//        super.leftClick(view);
        ToastUtils.showShort("你碰到左边的我了兄die");
    }

    @Override
    protected void rightClick(View view) {
//        super.rightClick(view);
        ToastUtils.showShort("你碰到右边的我了姐mei");
    }

    @OnClick(R.id.tvClick)
    void click() {
        ToastUtils.showShort("click");
        isLogin(new LoginCallBack<UserInfoBean>() {
            @Override
            public void loginSuccess(UserInfoBean data) {
                ToastUtils.showShort("登录成功:".concat(new Gson().toJson(data)));
            }

            @Override
            public void loginFailed() {
                ToastUtils.showShort("取消失败");
            }
        });
    }
}
