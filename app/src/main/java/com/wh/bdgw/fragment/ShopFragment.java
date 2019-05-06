package com.wh.bdgw.fragment;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseTitleFragment;
import com.wh.bdgw.persistent.ShopFragmentPersistent;
import com.wh.bdgw.persistent.view.ShopFragmentView;

import butterknife.BindView;

/**
 * 文件名:HomeFragment
 * 创建者:zed
 * 创建日期:2019/4/1 13:47
 * 描述:TODO
 */
public class ShopFragment extends BaseTitleFragment<ShopFragmentPersistent> implements ShopFragmentView {
    @BindView(R.id.rlList)
    RecyclerView rlList;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;

    @Override
    public int setBodyId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView() {
        setTvTitle("我是商店");
        setPersistent(new ShopFragmentPersistent(this));
    }

    @Override
    public RecyclerView getListView() {
        return rlList;
    }

    @Override
    public SmartRefreshLayout getSmartView() {
        return smr;
    }
}
