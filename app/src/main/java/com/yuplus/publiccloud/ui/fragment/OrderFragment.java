package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

/**
 * @user longzhen
 * @date 5/31/2017
 * @desc
 */

public class OrderFragment extends BaseFragment {

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initPresenter() {

    }
}
