package com.wh.bdgw.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wh.bdgw.callback.DataCallback;
import com.wh.bdgw.net.model.bean.UserInfoBean;
import com.wh.bdgw.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名:BasePersistent
 * 创建者:zed
 * 创建日期:2019/3/22 15:00
 * 描述:只作为分页使用 否则请使用 {@link BasePersistent}
 */
public abstract class BasePagePersistent<T extends BasePageView, M extends BaseBean, B extends BaseQuickAdapter<M, BaseViewHolder>>
        implements IBasePersistent<T>, OnRefreshLoadMoreListener, DataCallback<M> {
    private B mBaseQuickAdapter;
    //列表数据源 提取公用分页
    private List<M> mDatas;
    protected int initPageIndex = 1;
    //页码
    private int mPage = initPageIndex;
    //每页数据
    protected int mPageSize = 10;
    //是否清空数据
    private boolean clear = false;

    protected T mBaseView;

    public BasePagePersistent(T baseView) {
        this.mBaseView = baseView;
        init();
        mBaseQuickAdapter = initAdapter();
        initListParam();
    }

    protected <A extends BaseActivity> A getCurrentActivity() {
        return mBaseView == null ? null : (A) mBaseView.getCurrentActivity();
    }

    /**
     * 获取view 对象
     *
     * @return
     */
    protected T getView() {
        return mBaseView;
    }

    @Override
    public void onDestroy() {
        mBaseView = null;
    }

    /**
     * 初始化其他参数
     */
    protected abstract void init();

    /**
     * 初始化适配器
     *
     * @return
     */
    protected abstract B initAdapter();

    /**
     * 初始化列表参数配置
     */
    private void initListParam() {
        if (mBaseView != null) {
            RecyclerView listView = mBaseView.getListView();
            listView.setLayoutManager(new LinearLayoutManager(getCurrentActivity()));
            if (mBaseQuickAdapter != null)
                listView.setAdapter(mBaseQuickAdapter);
            mBaseView.getSmartView().setOnRefreshLoadMoreListener(this);
            mBaseView.getSmartView().autoRefresh();
        }
    }

    /**
     * 生命周期
     *
     * @param <B>
     * @return
     */
    protected <B> LifecycleTransformer<B> getActLifeRecycle() {
        if (mBaseView == null) return null;
        return mBaseView.getActLifeRecycle();
    }

    /**
     * 用户登录信息
     *
     * @return
     */
    protected UserInfoBean getUserInfo() {
        if (mBaseView == null) return new UserInfoBean();
        return mBaseView.getUserInfo();
    }

    @Override
    final public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        onRequestData(this);
    }

    @Override
    final public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPage = initPageIndex;
        onRequestData(this);
    }

    /**
     * 请求数据源
     *
     * @param dataCallback
     */
    protected abstract void onRequestData(DataCallback<M> dataCallback);

    @Override
    final public void refreshData(List<M> data) {
        LogUtils.i("BasePagePersistent", "当前页面:" + mPage);
        if (mBaseQuickAdapter == null) {
            LogUtils.e("BasePagePersistent", "mBaseQuickAdapter 没有被初始化");
            return;
        }
        if (data != null) {
            if (mPage == initPageIndex) {
                mBaseQuickAdapter.replaceData(data);
            } else {
                mBaseQuickAdapter.addData(data);
            }
            if (data.size() > 0)
                mPage++;
        }
        if (mBaseView == null) return;
        mBaseView.getSmartView().finishLoadMore();
        mBaseView.getSmartView().finishRefresh();
    }

    /**
     * 获取适配器
     *
     * @return
     */
    protected B getBaseQuickAdapter() {
        return mBaseQuickAdapter;
    }

    /**
     * 获取数据源
     *
     * @return
     */
    protected List<M> getDatas() {
        if (mBaseQuickAdapter == null)
            return new ArrayList<>();
        return mBaseQuickAdapter.getData();
    }

    /**
     * 设置初始化 分页起始 默认是1
     *
     * @param initPageIndex
     */
    public void setInitPageIndex(int initPageIndex) {
        this.initPageIndex = initPageIndex;
    }
}
