package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.ApiCst;
import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.ConfigPresenter;
import com.yuplus.publiccloud.mvp.presenter.CustomerPresenter;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.view.ConfigListView;
import com.yuplus.publiccloud.mvp.view.CustomerView;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.BaseUltimateViewAdapter;
import com.yuplus.publiccloud.ui.adapter.CustomerAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class CustomerFragment extends BaseFragment implements CustomerView, KpiValueView, ConfigListView {

    @BindView(R.id.customer_id_recyclerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View          mEmptyDataView;

    private CustomerPresenter mCustomerPresenter;
    private KpiValuePresenter mKpiValuePresenter;
    private ConfigPresenter   mConfigPresenter;

    private CustomerAdapter   mCustomerAdapter;
    private ProgressHUBDialog mLoadingView;

    private List<Long>              mCustomerIds;
    private List<CustomerBean>      mCustomerInfoList;
    private List<KpiValueBean>      mKpiValueBeanList;
    private List<ConfigurationBean> mConfigurationList;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_customer;
    }

    @Override
    protected void initPresenter() {
        mCustomerPresenter = new CustomerPresenter();
        mCustomerPresenter.setView(this);
        mCustomerPresenter.setTag(this);
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
        mConfigPresenter = new ConfigPresenter();
        mConfigPresenter.setView(this);
        mConfigPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mCustomerIds = new ArrayList<>();
        mCustomerInfoList = new ArrayList<>();
        mConfigurationList = new ArrayList<>();
        mKpiValueBeanList = new ArrayList<>();

        mLoadingView = ProgressHUBDialog.createDialog(getActivity());
        mCustomerPresenter.getCustomerListInfo();
    }

    @Override
    protected void initView() {
        super.initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mCustomerAdapter = new CustomerAdapter(getActivity(), null);
        mXRecyclerView.setAdapter(mCustomerAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCustomerPresenter.getCustomerListInfo();
            }

            @Override
            public void onLoadMore() {
            }
        });
        mCustomerAdapter.setOnItemClickListener(new BaseUltimateViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CustomerBean customer = mCustomerAdapter.getItem(position);
                DispatchManager.startCustomerDetailActivity(getActivity(), customer);
            }
        });
    }

    @Override
    public void onRenderCustomerData(List<CustomerBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mCustomerInfoList = data;
            mCustomerIds.clear();
            for (CustomerBean customer : mCustomerInfoList) {
                mCustomerIds.add(customer.getId());
            }
            if (ListUtils.isNotEmpty(mCustomerIds)) {
                List<Long> kpiCodes = new ArrayList();
                kpiCodes.add(3001L);//设备数量
                kpiCodes.add(3003L);//告警数量
                kpiCodes.add(3004L);//工单数量
                mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, mCustomerIds);
            }
        }
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mKpiValueBeanList.clear();
            mKpiValueBeanList.addAll(data);
            for (CustomerBean customer : mCustomerInfoList) {
                final List<KpiValueBean> resultValueList = getKpiValueBean(customer.getId());
                for (KpiValueBean kpiValue : resultValueList) {
                    if (kpiValue.getKpiCode() == 3001) {
                        customer.setDeviceCount((int) kpiValue.getValue());
                    } else if (kpiValue.getKpiCode() == 3003) {
                        customer.setAlertCount((int) kpiValue.getValue());
                    } else if (kpiValue.getKpiCode() == 3004) {
                        customer.setOrderCount((int) kpiValue.getValue());
                    }
                }
            }
            mConfigPresenter.getConfigsByGroupName("ConfigurationCommon");
        }
    }

    @Override
    public void onRenderConfigData(List<ConfigurationBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mConfigurationList.clear();
            mConfigurationList.addAll(data);
            for (CustomerBean customer : mCustomerInfoList) {
                final ConfigurationBean configuration = getConfiguration(customer.getCustomerName());
                if (null != configuration && StringUtils.isNotBlank(configuration.getValue())) {
                    String imgUrl = ApiCst.SERVER_ADDRESS + configuration.getValue();
                    customer.setImageUrl(imgUrl);
                }
            }
        }
        if (ListUtils.isNotEmpty(mCustomerInfoList)) {
            mCustomerAdapter.insertAll(mCustomerInfoList);
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

    private List<KpiValueBean> getKpiValueBean(long customerId) {
        List<KpiValueBean> resultValueList = new ArrayList<>();
        for (KpiValueBean kpiValue : mKpiValueBeanList) {
            if (kpiValue.getNodeId() == customerId) {
                resultValueList.add(kpiValue);
                break;
            }
        }
        return resultValueList;
    }

    private ConfigurationBean getConfiguration(String customerName) {
        ConfigurationBean configuration = null;
        if (StringUtils.isNotBlank(customerName)) {
            for (ConfigurationBean bean : mConfigurationList) {
                String key = "展示图：" + customerName;
                if (key.equals(bean.getLabel())) {
                    configuration = bean;
                    break;
                }
            }
        }
        return configuration;
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
