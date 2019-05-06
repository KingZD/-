package com.wh.bdgw.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.wh.bdgw.R;
import com.wh.bdgw.base.BaseActivity;
import com.wh.bdgw.base.BaseFragment;
import com.wh.bdgw.fragment.HomeFragment;
import com.wh.bdgw.fragment.MineFragment;
import com.wh.bdgw.fragment.ShopFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rgGroup)
    RadioGroup rgGroup;

    private HomeFragment mHomeFragment;
    private ShopFragment mShopFragment;
    private MineFragment mMineFragment;
    BaseFragment baseFragment;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mHomeFragment = new HomeFragment();
        replace(mHomeFragment);
    }


    @OnClick({R.id.rbHome, R.id.rbShop, R.id.rbMine})
    void showFragment(View v) {
        switch (v.getId()) {
            case R.id.rbHome:
                replace(mHomeFragment);
                break;
            case R.id.rbShop:
                if (mShopFragment == null)
                    mShopFragment = new ShopFragment();
                replace(mShopFragment);
                break;
            case R.id.rbMine:
                if (mMineFragment == null)
                    mMineFragment = new MineFragment();
                replace(mMineFragment);
                break;
        }
    }

    /**
     * 替换页面
     *
     * @param fragment
     */
    private void replace(BaseFragment fragment) {
        if (fragment == baseFragment) return;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            if (baseFragment == null) {
                fragmentTransaction.add(R.id.flContainer, fragment).show(fragment);
            } else if (baseFragment != fragment) {
                fragmentTransaction.add(R.id.flContainer, fragment).hide(baseFragment).show(fragment);
            }
        } else {
            fragmentTransaction.hide(baseFragment).show(fragment);
        }
        baseFragment = fragment;
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(new Bundle());
    }
}
