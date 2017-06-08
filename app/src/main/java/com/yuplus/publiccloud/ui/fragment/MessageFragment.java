package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.MessageWrapperBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.MessagePresenter;
import com.yuplus.publiccloud.mvp.view.MessageListView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.MessageAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class MessageFragment extends BaseFragment implements MessageListView {
    @BindView(R.id.customer_id_recyclerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View mEmptyDataView;

    private MessagePresenter mMessagePresenter;
    private ProgressHUBDialog mLoadingView;
    private MessageAdapter mMessageAdapter;

    private List<MessageWrapperBean> mMessageList;

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
        mMessagePresenter = new MessagePresenter();
        mMessagePresenter.setView(this);
        mMessagePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mMessageList = new ArrayList<>();
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());
    }

    @Override
    protected void initView() {
        super.initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mMessageAdapter = new MessageAdapter(getActivity(), null);
        mXRecyclerView.setAdapter(mMessageAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);

        mMessagePresenter.getLatestMessage();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mMessagePresenter.getLatestMessage();
            }

            @Override
            public void onLoadMore() {

            }
        });
        mMessageAdapter.setOnBroadCastListener(new MessageAdapter.OnBroadCastListener() {
            @Override
            public void OnBroadCast(long msgId) {
                DispatchManager.sendMessageUpdateBroadCast(getActivity());
            }
        });
    }

    @Override
    public void onRenderMessageData(List<MessageWrapperBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mMessageList.clear();
            mMessageList.addAll(data);
            mMessageAdapter.insertAll(mMessageList);
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
