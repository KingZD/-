package com.wh.bdgw.activity;

import android.content.Intent;
import android.view.View;

import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseTitleActivity;
import com.wh.bdgw.constant.SpConstant;
import com.wh.bdgw.net.model.bean.UserInfoBean;

/**
 * 文件名:LoginActivity
 * 创建者:zed
 * 创建日期:2019/3/28 11:15
 * 描述:TODO
 */
public class LoginActivity extends BaseTitleActivity {
    @Override
    public int setBodyId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    public void ff(View view) {
        setResult(RESULT_OK, new Intent().putExtra(SpConstant.APP_USER_INFO, new UserInfoBean()));
        finish();
    }

    public void cc(View view) {
        setResult(RESULT_CANCELED, new Intent().putExtra(SpConstant.APP_USER_INFO, new UserInfoBean()));
        finish();
    }
}
