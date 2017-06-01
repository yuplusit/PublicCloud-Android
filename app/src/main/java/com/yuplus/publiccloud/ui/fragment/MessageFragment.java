package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class MessageFragment extends BaseFragment {

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initPresenter() {

    }
}
