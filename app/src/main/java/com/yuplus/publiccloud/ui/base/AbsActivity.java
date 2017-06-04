package com.yuplus.publiccloud.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import skin.support.app.SkinCompatActivity;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public abstract class AbsActivity extends SkinCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initToolbar();
        initPresenter();
        init(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected abstract void initToolbar();

    protected abstract int getLayoutRes();

    protected abstract void initPresenter();

    protected void init(Bundle savedInstanceState) {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }
}
