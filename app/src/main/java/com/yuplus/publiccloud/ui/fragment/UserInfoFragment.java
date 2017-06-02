package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class UserInfoFragment extends BaseFragment {

    public static UserInfoFragment newInstance() {
        Bundle args = new Bundle();
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_userinfo;
    }

    @Override
    protected void initPresenter() {

    }
}
