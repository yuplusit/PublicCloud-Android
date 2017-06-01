package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.cst.TypeCst;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.FeedbackHelpFragment;
import com.yuplus.publiccloud.ui.fragment.MessageFragment;
import com.yuplus.publiccloud.ui.fragment.SkinSelectFragment;
import com.yuplus.publiccloud.util.FragmentUtils;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class SlideAreaActivity extends TitleActivity {

    private String mTitle;
    private int    mFragmentType;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_slide_area;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTitle = getIntent().getStringExtra(AppCst.COMMON_TITLE);
        mFragmentType = getIntent().getIntExtra(AppCst.COMMON_TYPE, 0);
        setShowTitle(true);
        setTitle(mTitle);
        setShowHomeBack();
    }

    @Override
    protected void initView() {
        super.initView();
        switch (mFragmentType) {
            case TypeCst.SlideArea.TYPE_MESSAGE:
                FragmentUtils.showFragment(getSupportFragmentManager(), MessageFragment.newInstance(), R.id.fragment_id_containter);
                break;
            case TypeCst.SlideArea.TYPE_SKIN_SELECT:
                FragmentUtils.showFragment(getSupportFragmentManager(), SkinSelectFragment.newInstance(), R.id.fragment_id_containter);
                break;
            case TypeCst.SlideArea.TYPE_FEEDBACK_HELP:
                FragmentUtils.showFragment(getSupportFragmentManager(), FeedbackHelpFragment.newInstance(), R.id.fragment_id_containter);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
