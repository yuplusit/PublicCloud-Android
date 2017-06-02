package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.AlertBean;
import com.yuplus.cloudsdk.future.data.bean.AlertListBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.mvp.presenter.AlertPresenter;
import com.yuplus.publiccloud.mvp.view.AlertListView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.AlertAdapter;
import com.yuplus.publiccloud.ui.adapter.BaseUltimateViewAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.IconFontUtils;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class AlertFragment extends BaseFragment implements AlertListView {

    @BindView(R.id.alert_id_all_layout)
    LinearLayout  mAllAlertIconLl;
    @BindView(R.id.alert_id_new_layout)
    LinearLayout  mNewAlertIconLl;
    @BindView(R.id.alert_id_dealing_layout)
    LinearLayout  mDealingAlertIconLl;
    @BindView(R.id.alert_id_finish_layout)
    LinearLayout  mFinishAlertIconLl;
    @BindView(R.id.alert_id_all_icon)
    TextView      mAllAlertIconTv;
    @BindView(R.id.alert_id_new_icon)
    TextView      mNewAlertIconTv;
    @BindView(R.id.alert_id_dealing_icon)
    TextView      mDealingAlertIconTv;
    @BindView(R.id.alert_id_finish_icon)
    TextView      mFinishAlertIconTv;
    @BindView(R.id.alert_id_recylerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View          mEmptyDataView;

    private List<View> mViewList = new ArrayList<>();
    private List<AlertBean>   mAlertList;
    private ProgressHUBDialog mLoadingView;
    private AlertAdapter      mAlertAdapter;
    private AlertPresenter    mAlertPresenter;

    private int    start      = 0;
    private int    total      = 0;
    private String severities = "1,2,3,4";
    private String states     = "0,5,10,20";
    private String mDomian;

    public static AlertFragment newInstance(String domain) {

        Bundle args = new Bundle();
        AlertFragment fragment = new AlertFragment();
        args.putString(AppCst.COMMON_DATA, domain);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_alert;
    }

    @Override
    protected void initPresenter() {
        mAlertPresenter = new AlertPresenter();
        mAlertPresenter.setView(this);
        mAlertPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
            mDomian = bundle.getString(AppCst.COMMON_DATA);
        }
        mAlertList = new ArrayList<>();
        mViewList.add(mAllAlertIconLl);
        mViewList.add(mNewAlertIconLl);
        mViewList.add(mDealingAlertIconLl);
        mViewList.add(mFinishAlertIconLl);
        mAlertPresenter.getAlert(true, start, AppCst.PAGE_SIZE, mDomian, severities, states);

    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mAlertAdapter = new AlertAdapter(getActivity(), null);
        mXRecyclerView.setAdapter(mAlertAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
    }

    @Override
    protected void initData() {
        super.initData();
        IconFontUtils.setIconFont(mAllAlertIconTv, EAppIconFont.APP_ALERT_ALL_ICON);
        IconFontUtils.setIconFont(mNewAlertIconTv, EAppIconFont.APP_ALERT_NEW_ICON);
        IconFontUtils.setIconFont(mDealingAlertIconTv, EAppIconFont.APP_ALERT_DEALING_ICON);
        IconFontUtils.setIconFont(mFinishAlertIconTv, EAppIconFont.APP_ALERT_FINISH_ICON);
        selectViewState(mAllAlertIconLl);
    }

    @Override
    protected void initListener() {
        super.initListener();

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                total = 0;
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }

            @Override
            public void onLoadMore() {
                if (start >= total) {
                    mXRecyclerView.refreshComplete();
                    ToastUtils.make(R.string.common_no_more_data);
                    return;
                }
                mAlertPresenter.getAlert(false, start, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });

        mAlertAdapter.setOnItemClickListener(new BaseUltimateViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AlertBean alert = mAlertAdapter.getItem(position);
                DispatchManager.startAlertDetailActivity(getActivity(), alert);
            }
        });

        mAllAlertIconLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectViewState(v);
                states = "0,5,10,20";
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });
        mNewAlertIconLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectViewState(v);
                states = "0";
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });
        mDealingAlertIconLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectViewState(v);
                states = "5,10";
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });
        mFinishAlertIconLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectViewState(v);
                states = "20";
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });
    }

    private void selectViewState(View selectView) {
        for (View view : mViewList) {
            if (selectView == view) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }

    @Override
    public void onRenderAlertData(boolean isRefresh, AlertListBean data) {
        if (data.getTotal() > 0) {
            total = data.getTotal();
        }
        final List<AlertBean> alertList = data.getData();
        if (ListUtils.isNotEmpty(alertList)) {
            if (isRefresh) {
                mAlertList.clear();
                mAlertList.addAll(alertList);
                mAlertAdapter.insertAll(mAlertList);
            } else {
                mAlertList.addAll(alertList);
                mAlertAdapter.insertList(alertList);
            }
            start += alertList.size();
        } else {
            if (isRefresh) {
                mAlertAdapter.clear();
            }
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
