package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.WebFragment;
import com.yuplus.publiccloud.util.FragmentUtils;

/**
 * Created by zlzsa on 2017/6/4.
 */

public class WebActivity extends TitleActivity {

    private String mUrl;
    private String mTitle;
    private WebFragment mWebFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTitle = getIntent().getStringExtra(AppCst.COMMON_TITLE);
        mUrl = getIntent().getStringExtra(AppCst.COMMON_URL);
        if (StringUtils.isBlank(mUrl)) {
            finish();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        if (StringUtils.isNotBlank(mTitle)) {
            setShowTitle(true);
            setTitle(mTitle);
        } else {
            setTitle("网页浏览");
        }
        setShowHomeBack();
        mWebFragment = WebFragment.newInstance(mUrl);
        FragmentUtils.showFragment(getSupportFragmentManager(), mWebFragment, R.id.fragment_container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
