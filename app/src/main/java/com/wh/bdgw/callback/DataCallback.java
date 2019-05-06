package com.wh.bdgw.callback;

import java.util.List;

/**
 * 文件名:DataCallback
 * 创建者:zed
 * 创建日期:2019/5/6 14:17
 * 描述:TODO
 */
public interface DataCallback<T> {
    void refreshData(List<T> data);
}
