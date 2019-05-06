package com.wh.bdgw.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseActivity;
import com.wh.bdgw.callback.GlideCallBack;
import com.wh.bdgw.util.GlideAppUtil;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件名:SplashActivity
 * 创建者:zed
 * 创建日期:2019/3/5 14:59
 * 描述:TODO
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.pb_splash)
    ProgressBar mPbSplash;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    @BindView(R.id.tv_jump)
    TextView mTvJump;
    //进度条任务
    Disposable progressSubscribe;

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        showProgress();
    }

    @OnClick(R.id.tv_jump)
    public void onViewClicked() {
    }

    /**
     * 跑进度条的任务
     */
    private void showProgress() {
        progressSubscribe = Observable.interval(0, 10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mPbSplash.setProgress(Integer.valueOf(String.valueOf(aLong)));
                        mTvLoading.setText(String.format(Locale.CHINA, "%d%%", mPbSplash.getProgress()));
                        if (aLong >= 100) {
                            //进度跑满，取消任务
                            if (progressSubscribe != null && !progressSubscribe.isDisposed())
                                progressSubscribe.dispose();
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    /**
     * 显示
     */
    private void showBannerPic(String url) {
        GlideAppUtil.loadImage(this, url, mIvSplash, new GlideCallBack() {
            @Override
            public void success() {

            }

            @Override
            public void failed() {

            }
        });
    }

    private void showTimer() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //界面关闭，取消任务
        if (progressSubscribe != null && !progressSubscribe.isDisposed())
            progressSubscribe.dispose();
        progressSubscribe = null;
    }
}
