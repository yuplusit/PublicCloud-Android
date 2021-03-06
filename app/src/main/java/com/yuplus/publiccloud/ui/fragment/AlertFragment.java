package com.yuplus.publiccloud.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.AlertBean;
import com.yuplus.cloudsdk.future.data.bean.AlertListBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.cst.BroadcastCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.mvp.presenter.AlertPresenter;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.view.AlertListView;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.AlertAdapter;
import com.yuplus.publiccloud.ui.adapter.BaseUltimateViewAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.IconFontUtils;
import com.yuplus.publiccloud.util.ToastUtils;
import com.yuplus.publiccloud.util.TokenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class AlertFragment extends BaseFragment implements AlertListView, KpiValueView {

    @BindView(R.id.alert_id_all_layout)
    RelativeLayout mAllAlertIconLl;
    @BindView(R.id.alert_id_new_layout)
    RelativeLayout mNewAlertIconLl;
    @BindView(R.id.alert_id_dealing_layout)
    RelativeLayout mDealingAlertIconLl;
    @BindView(R.id.alert_id_finish_layout)
    RelativeLayout mFinishAlertIconLl;
    @BindView(R.id.alert_id_all_icon)
    TextView       mAllAlertIconTv;
    @BindView(R.id.alert_id_new_icon)
    TextView       mNewAlertIconTv;
    @BindView(R.id.alert_id_dealing_icon)
    TextView       mDealingAlertIconTv;
    @BindView(R.id.alert_id_finish_icon)
    TextView       mFinishAlertIconTv;
    @BindView(R.id.alert_id_recylerview)
    XRecyclerView  mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View           mEmptyDataView;

    @BindView(R.id.slide_id_all_red_point)
    TextView mAllCountRedTv;
    @BindView(R.id.slide_id_new_red_point)
    TextView mNewCountRedTv;
    @BindView(R.id.slide_id_dealing_red_point)
    TextView mDealingCountRedTv;
    @BindView(R.id.slide_id_finish_red_point)
    TextView mFinshCountRedTv;

    private List<View> mViewList = new ArrayList<>();
    private List<AlertBean>   mAlertList;
    private ProgressHUBDialog mLoadingView;
    private AlertAdapter      mAlertAdapter;
    private AlertPresenter    mAlertPresenter;
    private KpiValuePresenter mKpiValuePresenter;

    private int    start      = 0;
    private int    total      = 0;
    private String severities = "1,2,3,4";
    private String states     = "0,5,10,20";
    private String mDomian;

    private long allCount     = 0;
    private long newCount     = 0;
    private long dealingCount = 0;
    private long finishCount  = 0;

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
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
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

        mLoadingView = ProgressHUBDialog.createDialog(getActivity());
        mAlertPresenter.getAlert(true, start, AppCst.PAGE_SIZE, mDomian, severities, states);

        registerAction(BroadcastCst.ALERT_INFO_UPDATE);
    }

    @Override
    protected void initView() {
        super.initView();

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
        if (!TokenUtils.checkUserState(getActivity())) {
            return;
        }
        List<Long> nodeIds = new ArrayList<>();
        nodeIds.add(AppApplication.user.getDomainID());
        List<Long> kpiCodes = new ArrayList<>();
        kpiCodes.add(3017L);// 全部告警
        kpiCodes.add(3003L);// 新产生告警
        kpiCodes.add(3050L);//完结的告警
        kpiCodes.add(3051L);//处理中告警
        mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds);
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
                states = "0,5";
                mAlertPresenter.getAlert(true, 0, AppCst.PAGE_SIZE, mDomian, severities, states);
            }
        });
        mDealingAlertIconLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectViewState(v);
                states = "10";
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
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            for (KpiValueBean kpiValue : data) {
                if (kpiValue.getKpiCode() == 3017L) {// 全部告警
                    allCount = kpiValue.getValue();
                } else if (kpiValue.getKpiCode() == 3050L) {//完结的告警
                    finishCount = kpiValue.getValue();
                } else if (kpiValue.getKpiCode() == 3051L) {//处理中告警
                    dealingCount = kpiValue.getValue();
                } else if (kpiValue.getKpiCode() == 3003L) {// 新产生告警
                    newCount = kpiValue.getValue();
                }
            }
        }
        showRedPointFlag(mAllCountRedTv, allCount);
        showRedPointFlag(mNewCountRedTv, newCount);
        showRedPointFlag(mDealingCountRedTv, dealingCount);
        showRedPointFlag(mFinshCountRedTv, finishCount);
    }

    private void showRedPointFlag(TextView view, long value) {
        if (AppCst.ALERT_RED_POINT_SWITCH) {
            if (value == 0) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
                if (value <= 99L) {
                    view.setText(String.valueOf(value));
                } else {
                    view.setText("99+");
                }
            }
        } else {
            view.setVisibility(View.GONE);
        }
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
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        final String action = intent.getAction();
        if (BroadcastCst.ALERT_INFO_UPDATE.equalsIgnoreCase(action)) {
            final AlertBean alertTemp = intent.getParcelableExtra(AppCst.COMMON_DATA);
            if ("0,5" == states || "10" == states) {
                if (alertTemp.getState() == 20) {
                    mAlertAdapter.removeItem(alertTemp);
                    if (states == "0,5") {
                        newCount--;
                        if (newCount < 0) {
                            newCount = 0;
                        }
                        showRedPointFlag(mNewCountRedTv, newCount);
                        finishCount++;
                        showRedPointFlag(mFinshCountRedTv, finishCount);
                        DispatchManager.sendAlertUntreatedCountBroadCast(getActivity(), (int) newCount);
                    } else if (states == "10") {
                        dealingCount--;
                        if (dealingCount < 0) {
                            dealingCount = 0;
                        }
                        showRedPointFlag(mDealingCountRedTv, dealingCount);
                        finishCount++;
                        showRedPointFlag(mFinshCountRedTv, finishCount);
                    }
                } else {
                    mAlertAdapter.notifyItem(alertTemp);
                }
            } else {
                mAlertAdapter.notifyItem(alertTemp);
            }
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
