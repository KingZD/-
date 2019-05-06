package com.wh.bdgw.base;


/**
 * 文件名:IBasePersistent
 * 创建者:zed
 * 创建日期:2019/3/22 15:06
 * 描述:TODO
 */
public interface IBasePersistent<T extends BaseView> {
    void onDestroy();
}
