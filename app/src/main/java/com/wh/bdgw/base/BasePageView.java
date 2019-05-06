package com.wh.bdgw.base;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 文件名:BasePageView
 * 创建者:zed
 * 创建日期:2019/5/6 13:51
 * 描述:TODO
 */
public interface BasePageView extends BaseView {
    RecyclerView getListView();
    SmartRefreshLayout getSmartView();
}
