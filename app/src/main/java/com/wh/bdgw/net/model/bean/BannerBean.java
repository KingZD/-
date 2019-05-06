package com.wh.bdgw.net.model.bean;

import java.io.Serializable;

/**
 * 文件名:BannerBean
 * 创建者:zed
 * 创建日期:2019/3/7 9:18
 * 描述:TODO
 */
public class BannerBean implements Serializable {

    /**
     * bid : 39
     * banner_title : 启动中
     * banner_url : http://test.system.luobokoudai.com/upload/201903/06/201903061146286243.jpg
     * link_type : 2
     * banner_link :
     * product_link : http://qbhwd.luobokoudai.com/index.aspx?code=MTE=&source_id=Mjg=
     * product_id : 1
     */

    private String bid;
    private String banner_title;
    private String banner_url;
    private String link_type;
    private String banner_link;
    private String product_link;
    private String product_id;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public String getBanner_link() {
        return banner_link;
    }

    public void setBanner_link(String banner_link) {
        this.banner_link = banner_link;
    }

    public String getProduct_link() {
        return product_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
