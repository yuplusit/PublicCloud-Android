package com.yuplus.publiccloud.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class FeedbackHelpFragment extends BaseFragment {
    @BindView(R.id.fb_id_common_question)
    RelativeLayout mCommonQuestionRl;
    @BindView(R.id.fb_id_feebback)
    RelativeLayout mFeedBackRl;
    @BindView(R.id.fb_id_tel)
    RelativeLayout mTelRl;

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

    @OnClick({R.id.fb_id_common_question, R.id.fb_id_feebback, R.id.fb_id_tel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.fb_id_common_question:

                break;
            case R.id.fb_id_feebback:
                DispatchManager.startFeedbackActivity(getActivity());
                break;
            case R.id.fb_id_tel:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:010-84148079"));
                startActivity(intent);
                break;
        }
    }
}
