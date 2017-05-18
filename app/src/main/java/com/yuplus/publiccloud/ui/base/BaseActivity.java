package com.yuplus.publiccloud.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.publiccloud.R;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public abstract class BaseActivity extends AbsActivity {
    @Nullable
    @BindView(R.id.common_id_toolbar)
    Toolbar mCommonTb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolbar() {
        if (null != mCommonTb) {
            setSupportActionBar(mCommonTb);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public Toolbar getToolbar() {
        return mCommonTb;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancel(this);
    }
}
