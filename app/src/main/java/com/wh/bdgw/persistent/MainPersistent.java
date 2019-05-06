package com.wh.bdgw.persistent;

import com.wh.bdgw.base.BasePersistent;
import com.wh.bdgw.persistent.view.MainView;

/**
 * 文件名:MainPersistent
 * 创建者:zed
 * 创建日期:2019/3/15 13:09
 * 描述:TODO
 */
public class MainPersistent extends BasePersistent<MainView> {

    public MainPersistent(MainView baseView) {
        super(baseView);
    }

    @Override
    protected void init() {

    }
}
