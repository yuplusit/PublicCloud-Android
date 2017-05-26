package com.yuplus.publiccloud.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public abstract class AbsFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        init(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected abstract View findViewById(int id);

    protected abstract int getLayoutRes();

    protected void init(Bundle savedInstanceState) {
    }

    protected abstract void initPresenter();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }
}
