package com.wh.bdgw.persistent;

import com.wh.bdgw.base.BasePersistent;
import com.wh.bdgw.persistent.view.MineFragmentView;
import com.wh.bdgw.util.ToastUtils;

/**
 * 文件名:MineFragmentPersistent
 * 创建者:zed
 * 创建日期:2019/5/6 15:14
 * 描述:TODO
 */
public class MineFragmentPersistent extends BasePersistent<MineFragmentView> {
    public MineFragmentPersistent(MineFragmentView baseView) {
        super(baseView);
    }

    @Override
    protected void init() {

    }

    public void showToast(){
        ToastUtils.showShort("showToast");
    }
}
