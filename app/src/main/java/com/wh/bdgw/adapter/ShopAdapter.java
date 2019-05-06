package com.wh.bdgw.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wh.bdgw.R;
import com.wh.bdgw.net.model.bean.ShopBean;
import com.wh.bdgw.util.GlideAppUtil;

/**
 * 文件名:ShopAdapter
 * 创建者:zed
 * 创建日期:2019/5/6 13:47
 * 描述:TODO
 */
public class ShopAdapter extends BaseQuickAdapter<ShopBean, BaseViewHolder> {

    public ShopAdapter() {
        super(R.layout.item_shop);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean item) {
        helper.setText(R.id.tvTitle, item.getName());
        GlideAppUtil.loadImage(helper.itemView.getContext(), item.getIcon(), helper.getView(R.id.ivIvon));
    }
}
