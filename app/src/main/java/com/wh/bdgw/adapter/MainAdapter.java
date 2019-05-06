package com.wh.bdgw.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wh.bdgw.R;

import java.util.List;

/**
 * 文件名:MainAdapter
 * 创建者:zed
 * 创建日期:2019/3/14 13:07
 * 描述:TODO
 */
public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MainAdapter(@Nullable List<String> data) {
        super(R.layout.item_main, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
