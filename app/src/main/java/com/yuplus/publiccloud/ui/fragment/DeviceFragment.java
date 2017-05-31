package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceListBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.mvp.presenter.CustomerPresenter;
import com.yuplus.publiccloud.mvp.presenter.DevicePresenter;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.view.CustomerView;
import com.yuplus.publiccloud.mvp.view.DeviceListView;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.BaseUltimateViewAdapter;
import com.yuplus.publiccloud.ui.adapter.DeviceAdapter;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.widget.ClearableEditText;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class DeviceFragment extends BaseFragment implements CustomerView, DeviceListView, KpiValueView {
    @BindView(R.id.device_id_recylerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.device_id_search_et)
    ClearableEditText mSearchEt;
    @BindView(R.id.device_id_search_btn)
    ImageView mSearchBtn;

    private CustomerPresenter mCustomerPresenter;
    private DevicePresenter mDevicePresenter;
    private KpiValuePresenter mKpiValuePresenter;

    private ProgressHUBDialog mLoadingView;
    private DeviceAdapter mDeviceAdapter;
    private List<CustomerBean> mCustomerList;
    private List<DeviceBean> mDeviceList;

    private int start = 0;
    private int total = 0;
    private String mSearchContent;
    private boolean isConditionSearch;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_device;
    }

    @Override
    protected void initPresenter() {
        mCustomerPresenter = new CustomerPresenter();
        mCustomerPresenter.setView(this);
        mCustomerPresenter.setTag(this);
        mDevicePresenter = new DevicePresenter();
        mDevicePresenter.setView(this);
        mDevicePresenter.setTag(this);
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mCustomerList = new ArrayList<>();
        mDeviceList = new ArrayList<>();

        mCustomerPresenter.getCustomerListInfo();
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mDeviceAdapter = new DeviceAdapter(getActivity(), null);
        mXRecyclerView.setAdapter(mDeviceAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                total = 0;
                mCustomerPresenter.getCustomerListInfo();
            }

            @Override
            public void onLoadMore() {
                if (start > total) {
                    ToastUtils.make(R.string.common_no_more_data);
                    return;
                }
                if (isConditionSearch) {
                    mDevicePresenter.searchDeviceByConditionWithPage(false, mSearchContent, start, AppCst.PAGE_SIZE, total);
                } else {
                    mDevicePresenter.getDevicesByConditionWithPage(false, start, AppCst.PAGE_SIZE, total);
                }

            }
        });
        mDeviceAdapter.setOnItemClickListener(new BaseUltimateViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final DeviceBean device = mDeviceAdapter.getItem(position);
                DispatchManager.startDeviceDetailActivity(getActivity(), device);
            }
        });
    }

    @OnClick(R.id.device_id_search_btn)
    public void onSearchBtnClick() {
        mSearchContent = mSearchEt.getText().toString().trim();
        if (StringUtils.isBlank(mSearchContent)) {
            isConditionSearch = false;
        } else {
            isConditionSearch = true;
        }
        start = 0;
        total = 0;
        mCustomerPresenter.getCustomerListInfo();
    }

    @Override
    public void onRenderCustomerData(List<CustomerBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mCustomerList.clear();
            mCustomerList.addAll(data);
        }
        if (isConditionSearch) {
            mDevicePresenter.searchDeviceByConditionWithPage(true, mSearchContent, 0, AppCst.PAGE_SIZE, total);
        } else {
            mDevicePresenter.getDevicesByConditionWithPage(true, 0, AppCst.PAGE_SIZE, total);
        }
    }

    @Override
    public void onRenderDeviceData(boolean isRefresh, DeviceListBean deviceListBean) {
        List<DeviceBean> data = deviceListBean.getData();
        if (deviceListBean.getTotal() > 0) {
            total = deviceListBean.getTotal();
        }
        if (ListUtils.isNotEmpty(data)) {
            //装载所属用户名
            for (DeviceBean device : data) {
                for (CustomerBean customer : mCustomerList) {
                    if (device.getCustomerId() == customer.getId()) {
                        device.setCustomerName(customer.getCustomerName());
                        break;
                    }
                }
            }
            if (isRefresh) {
                mDeviceList.clear();
                mDeviceList.addAll(data);
                //mDeviceAdapter.insertAll(data);
            } else {
                mDeviceList.addAll(data);
                //mDeviceAdapter.insertList(data);
            }
            start += data.size();
            List<Long> kpiCodes = new ArrayList();
            kpiCodes.add(999999L);//故障状态
            kpiCodes.add(999998L);//在线状态
            List<Long> nodeIds = new ArrayList<>();
            for (DeviceBean device : data) {
                nodeIds.add(device.getId());
            }
            if (ListUtils.isNotEmpty(nodeIds)) {
                mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds);
            }
        } else {
            ToastUtils.make(R.string.common_no_more_data);
            mXRecyclerView.refreshComplete();
        }
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            for (DeviceBean device : mDeviceList) {
                for (KpiValueBean kpiValue : data) {
                    if (device.getId() == kpiValue.getNodeId()) {
                        if (kpiValue.getKpiCode() == 999999) {//故障状态
                            device.setSeverity((int)kpiValue.getValue());
                        } else if (kpiValue.getKpiCode() == 999998) {//在线状态
                            device.setOnlineStatus((int)kpiValue.getValue());
                        }
                    }
                }
            }
        }
        mDeviceAdapter.insertAll(mDeviceList);
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
