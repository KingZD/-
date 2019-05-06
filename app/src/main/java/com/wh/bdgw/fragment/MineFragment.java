package com.wh.bdgw.fragment;


import android.view.View;

import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseTitleFragment;
import com.wh.bdgw.persistent.MineFragmentPersistent;
import com.wh.bdgw.persistent.view.MineFragmentView;

import butterknife.OnClick;

/**
 * 文件名:HomeFragment
 * 创建者:zed
 * 创建日期:2019/4/1 13:47
 * 描述:TODO
 */
public class MineFragment extends BaseTitleFragment<MineFragmentPersistent> implements MineFragmentView {

    @Override
    public int setBodyId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        setTvTitle("我是个人");
        setPersistent(new MineFragmentPersistent(this));
    }

    @OnClick(R.id.btTry)
    void OnClick(View v) {
        persistent.showToast();
    }
}
