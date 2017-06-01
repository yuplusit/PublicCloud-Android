package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class FeedbackHelpFragment extends BaseFragment {

    public static FeedbackHelpFragment newInstance() {
        Bundle args = new Bundle();
        FeedbackHelpFragment fragment = new FeedbackHelpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_feedback_help;
    }

    @Override
    protected void initPresenter() {

    }
}
