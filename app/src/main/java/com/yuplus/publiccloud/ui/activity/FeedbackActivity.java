package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.util.ToastUtils;

import butterknife.BindView;

/**
 * Created by zlzsa on 2017/6/4.
 */

public class FeedbackActivity extends TitleActivity {
    @BindView(R.id.feedback_id_content)
    EditText mContentEt;
    @BindView(R.id.feedback_id_tel)
    EditText mTelEt;
    @BindView(R.id.feedback_id_commit)
    TextView mCommitBtn;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("意见反馈");
        setShowHomeBack();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = mContentEt.getText().toString().trim();
                if (StringUtils.isBlank(content)) {
                    ToastUtils.make("反馈内容不能为空，请填写！");
                    return;
                }
                final String telNum = mTelEt.getText().toString().trim();
                if (StringUtils.isBlank(telNum)) {
                    ToastUtils.make("请留下你的联系方式，谢谢！");
                    return;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
