package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.TicketBean;
import com.yuplus.cloudsdk.future.data.bean.TicketDetailBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.mvp.presenter.TicketNoPresenter;
import com.yuplus.publiccloud.mvp.view.TicketNoView;
import com.yuplus.publiccloud.ui.adapter.TimeLineAdapter;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/8/2017
 * @desc
 */

public class TicketDetailActivity extends TitleActivity implements TicketNoView {


    @BindView(R.id.ticketno_id_recyclerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View          mEmptyDataView;

    private TicketNoPresenter mTicketNoPresenter;
    private ProgressHUBDialog mLoadingView;
    private TimeLineAdapter   mTimeLineAdapter;

    private TicketBean             mTicket;
    private List<TicketDetailBean> mTicketNoList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ticket_history;
    }

    @Override
    protected void initPresenter() {
        mTicketNoPresenter = new TicketNoPresenter();
        mTicketNoPresenter.setView(this);
        mTicketNoPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTicket = getIntent().getParcelableExtra(AppCst.COMMON_DATA);
        if (null == mTicket) {
            finish();
            return;
        }
        setShowTitle(true);
        setTitle("执行历史");
        setShowHomeBack();

        mTicketNoList = new ArrayList<>();
        mLoadingView = ProgressHUBDialog.createDialog(this);
    }

    @Override
    protected void initView() {
        super.initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mTimeLineAdapter = new TimeLineAdapter(this, null);
        mXRecyclerView.setAdapter(mTimeLineAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void initData() {
        super.initData();
        mTicketNoPresenter.getTicketNo(mTicket.getTicketNo());
    }

    @Override
    protected void initListener() {
        super.initListener();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mTicketNoPresenter.getTicketNo(mTicket.getTicketNo());
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void onRenderTicketNOData(List<TicketDetailBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mTicketNoList.clear();
            mTicketNoList.addAll(data);
            mTimeLineAdapter.insertAll(mTicketNoList);
        } else {
            mXRecyclerView.setEmptyView(mEmptyDataView);
        }
        mXRecyclerView.refreshComplete();
    }

    @Override
    public void onFailure(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            ToastUtils.make(msg);
        }
        mXRecyclerView.refreshComplete();
    }

    @Override
    public void showLoading() {
        if (null != mLoadingView && !mLoadingView.isShowing()) {
            mLoadingView.show();
        }
    }

    @Override
    public void hideLoading() {
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
