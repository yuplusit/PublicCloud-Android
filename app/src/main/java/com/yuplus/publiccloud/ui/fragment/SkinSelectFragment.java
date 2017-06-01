package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class SkinSelectFragment extends BaseFragment {

    public static SkinSelectFragment newInstance() {
        Bundle args = new Bundle();
        SkinSelectFragment fragment = new SkinSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_skin_select;
    }

    @Override
    protected void initPresenter() {

    }
}
