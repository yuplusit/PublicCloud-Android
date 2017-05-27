package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.mvp.presenter.CustomerPresenter;
import com.yuplus.publiccloud.mvp.presenter.DevicePresenter;
import com.yuplus.publiccloud.mvp.view.CustomerView;
import com.yuplus.publiccloud.mvp.view.DeviceListView;
import com.yuplus.publiccloud.ui.adapter.DeviceAdapter;
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

public class DeviceFragment extends BaseFragment implements CustomerView, DeviceListView {
    @BindView(R.id.device_id_recylerview)
    XRecyclerView mXRecyclerView;

    private CustomerPresenter mCustomerPresenter;
    private DevicePresenter   mDevicePresenter;

    private ProgressHUBDialog mLoadingView;
    private DeviceAdapter     mDeviceAdapter;
    private List<DeviceBean>  deviceList;

    private int start = 0;

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
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        deviceList = new ArrayList<>();

        mCustomerPresenter.getCustomerListInfo();
        mDevicePresenter.getDevicesByConditionWithPage(start, AppCst.PAGE_SIZE);
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
        mXRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void onRenderCustomerData(List<CustomerBean> data) {
        if (ListUtils.isNotEmpty(data)) {

        }
    }

    @Override
    public void onRenderDeviceData(List<ConfigurationBean> data) {
        if (ListUtils.isNotEmpty(data)) {

        }
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
