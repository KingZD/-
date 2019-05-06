package com.wh.bdgw.persistent;

import android.os.Handler;

import com.wh.bdgw.R;
import com.wh.bdgw.adapter.ShopAdapter;
import com.wh.bdgw.base.BasePagePersistent;
import com.wh.bdgw.callback.DataCallback;
import com.wh.bdgw.net.model.bean.ShopBean;
import com.wh.bdgw.persistent.view.ShopFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名:ShopFragmentPersistent
 * 创建者:zed
 * 创建日期:2019/5/6 13:41
 * 描述:TODO
 */
public class ShopFragmentPersistent extends BasePagePersistent<ShopFragmentView, ShopBean, ShopAdapter> {

    public ShopFragmentPersistent(ShopFragmentView baseView) {
        super(baseView);
    }

    @Override
    protected void init() {
    }

    @Override
    protected ShopAdapter initAdapter() {
        return new ShopAdapter();
    }

    @Override
    protected void onRequestData(final DataCallback<ShopBean> dataCallback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ShopBean> shopBeans = new ArrayList<ShopBean>() {{
                    add(new ShopBean(R.mipmap.withdraw_dawenhao, "1"));
                    add(new ShopBean(R.mipmap.withdraw_dianhua, "2"));
                    add(new ShopBean(R.mipmap.withdraw_dianhua, "3"));
                    add(new ShopBean(R.mipmap.ic_launcher, "4"));
                    add(new ShopBean(R.mipmap.withdraw_dawenhao, "5"));
                    add(new ShopBean(R.mipmap.withdraw_dianhua, "6"));
                    add(new ShopBean(R.mipmap.withdraw_dianhua, "7"));
                    add(new ShopBean(R.mipmap.ic_launcher, "8"));
                    add(new ShopBean(R.mipmap.withdraw_dawenhao, "9"));
                }};
                dataCallback.refreshData(shopBeans);
            }
        }, 2000);
    }

}
