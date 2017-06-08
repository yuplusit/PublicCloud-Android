package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.TitleActivity;

/**
 * @user longzhen
 * @date 6/8/2017
 * @desc
 */

public class TicketDetailActivity extends TitleActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ticket_history;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("执行历史");
        setShowHomeBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
