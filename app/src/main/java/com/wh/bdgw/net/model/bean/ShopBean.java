package com.wh.bdgw.net.model.bean;

import com.wh.bdgw.base.BaseBean;


/**
 * 文件名:ShopBean
 * 创建者:zed
 * 创建日期:2019/5/6 13:40
 * 描述:TODO
 */
public class ShopBean extends BaseBean {
    private int icon;
    private String name;

    public ShopBean(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
