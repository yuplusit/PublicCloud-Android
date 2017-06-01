package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.cst.TypeCst;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.AlertFragment;
import com.yuplus.publiccloud.ui.fragment.DeviceFragment;
import com.yuplus.publiccloud.ui.fragment.OrderFragment;
import com.yuplus.publiccloud.util.FragmentUtils;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class CustomerAreaActivity extends TitleActivity {
    private String mTitle;
    private int    mFragmentType;
    private long   mProjectId;
    private String mDomain;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_customer_area;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTitle = getIntent().getStringExtra(AppCst.COMMON_TITLE);
        setShowTitle(true);
        setTitle(mTitle);
        setShowHomeBack();
        mFragmentType = getIntent().getIntExtra(AppCst.COMMON_DATA, 0);
        mProjectId = getIntent().getLongExtra(AppCst.COMMON_ID, 0);
        mDomain = getIntent().getStringExtra(AppCst.COMMON_KEY);
    }

    @Override
    protected void initView() {
        super.initView();
        switch (mFragmentType) {
            case TypeCst.CustomerArea.DEVICE_PAGE:
                FragmentUtils.showFragment(getSupportFragmentManager(), DeviceFragment.newInstance(mProjectId), R.id.fragment_id_containter);
                break;
            case TypeCst.CustomerArea.ALERT_PAGE:
                FragmentUtils.showFragment(getSupportFragmentManager(), AlertFragment.newInstance(mDomain), R.id.fragment_id_containter);
                break;
            case TypeCst.CustomerArea.ORDER_PAGE:
                FragmentUtils.showFragment(getSupportFragmentManager(), OrderFragment.newInstance(), R.id.fragment_id_containter);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
