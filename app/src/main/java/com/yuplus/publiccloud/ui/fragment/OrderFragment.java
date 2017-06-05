package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.TicketBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.TicketsPresenter;
import com.yuplus.publiccloud.mvp.view.TicketsListView;
import com.yuplus.publiccloud.ui.adapter.TicketAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/31/2017
 * @desc
 */

public class OrderFragment extends BaseFragment implements TicketsListView {

    @BindView(R.id.order_id_recyclerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View          mEmptyDataView;

    private TicketsPresenter  mTicketsPresenter;
    private ProgressHUBDialog mLoadingView;

    private TicketAdapter    mTicketAdapter;
    private List<TicketBean> mTicketList;

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
        mTicketsPresenter = new TicketsPresenter();
        mTicketsPresenter.setView(this);
        mTicketsPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTicketList = new ArrayList<>();
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mTicketAdapter = new TicketAdapter(getActivity(), null);
        mXRecyclerView.setAdapter(mTicketAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);

    }

    @Override
    protected void initData() {
        super.initData();
        mTicketsPresenter.findTicketsByStatus(100);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mTicketsPresenter.findTicketsByStatus(100);
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void onRenderTicketsData(List<TicketBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mTicketList.clear();
            mTicketList.addAll(data);
            mTicketAdapter.insertAll(mTicketList);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
