package com.yuplus.publiccloud.ui.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.future.data.bean.KpiBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.future.data.bean.UnitBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.presenter.KpisPresenter;
import com.yuplus.publiccloud.mvp.presenter.UnitPresenter;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.mvp.view.KpisView;
import com.yuplus.publiccloud.mvp.view.UnitListView;
import com.yuplus.publiccloud.ui.adapter.DeviceTestAdapter;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.DateUtils;
import com.yuplus.publiccloud.util.IconFontUtils;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class DeviceDetailActivity extends TitleActivity implements KpisView, UnitListView, KpiValueView {
    @BindView(R.id.device_detail_id_avatar)
    RoundedImageView mDeviceAvatar;
    @BindView(R.id.device_detail_id_name)
    TextView mDeviceNameTv;
    @BindView(R.id.device_detail_id_sate)
    TextView mDeviceStateTv;
    @BindView(R.id.device_detail_id_sn)
    TextView mDeviceSnTv;
    @BindView(R.id.device_detail_id_open_btn)
    TextView mDeviceOpenBtn;
    @BindView(R.id.device_detail_id_product_date)
    TextView mDeviceProductTv;
    @BindView(R.id.device_detail_id_stop_btn)
    TextView mDeviceStopBtn;
    @BindView(R.id.device_detail_id_test_name)
    TextView mDeviceTestNameTv;
    @BindView(R.id.device_detail_id_test_value)
    TextView mDeviceTestValueTv;
    @BindView(R.id.device_detail_id_test_date)
    TextView mDeviceTestDateTv;
    @BindView(R.id.device_detail_id_test_history)
    TextView mDeviceTestHistoryTv;
    @BindView(R.id.device_detail_id_recylerview)
    XRecyclerView mXRecyclerView;

    private ProgressHUBDialog mLoadingView;

    private DeviceBean mDevice;
    private KpisPresenter mKpisPresenter;
    private UnitPresenter mUnitPresenter;
    private KpiValuePresenter mKpiValuePresenter;
    private List<KpiBean> mKpiList;
    private List<UnitBean> mUnitList;
    private DeviceTestAdapter mDeviceTestAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_device_detail;
    }

    @Override
    protected void initPresenter() {
        mKpisPresenter = new KpisPresenter();
        mKpisPresenter.setView(this);
        mKpisPresenter.setTag(this);
        mUnitPresenter = new UnitPresenter();
        mUnitPresenter.setView(this);
        mUnitPresenter.setTag(this);
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mDevice = getIntent().getParcelableExtra(AppCst.COMMON_DATA);
        if (null == mDevice) {
            finish();
        }
        setShowTitle(true);
        setTitle(mDevice.getLabel());
        setShowHomeBack();

        mKpiList = new ArrayList<>();
        mUnitList = new ArrayList<>();

        mKpisPresenter.getKpisByModelId(mDevice.getModelId());
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {
        super.initData();
        if (StringUtils.isNotBlank(mDevice.getLabel())) {
            mDeviceNameTv.setText(mDevice.getLabel());
        }
        if (mDevice.getSeverity() < 1) {
            //正常
            mDeviceStateTv.setText(R.string.device_severity_status_01);
            mDeviceStateTv.setTextColor(getResources().getColor(R.color.common_success));
            mDeviceStateTv.setBackground(getResources().getDrawable(R.drawable.common_round_stroke_success));
        } else {
            //告警
            mDeviceStateTv.setText(R.string.device_severity_status_02);
            mDeviceStateTv.setTextColor(getResources().getColor(R.color.common_warning));
            mDeviceStateTv.setBackground(getResources().getDrawable(R.drawable.common_round_stroke_warning));
        }
        if ("active".equalsIgnoreCase(mDevice.getManagedStatus())) {
            mDeviceOpenBtn.setSelected(false);
            mDeviceStopBtn.setSelected(true);
        } else {
            mDeviceOpenBtn.setSelected(true);
            mDeviceStopBtn.setSelected(false);
        }
        if (StringUtils.isNotBlank(mDevice.getSn())) {
            mDeviceSnTv.setText(mDevice.getSn());
        }
        if (StringUtils.isNotBlank(mDevice.getCreateTime())) {
            mDeviceProductTv.setText(DateUtils.timeFormat(mDevice.getCreateTime(), "yyyy-MM-dd hh:mm"));
        }
        setIconFont(mDeviceTestNameTv, EAppIconFont.APP_TEST_NAME_ICON);
        setIconFont(mDeviceTestValueTv, EAppIconFont.APP_TEST_VALUE_ICON);
        setIconFont(mDeviceTestDateTv, EAppIconFont.APP_TEST_CALENDAR_ICON);
        setIconFont(mDeviceTestHistoryTv, EAppIconFont.APP_TEST_HISTORY_ICON);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mDeviceTestAdapter = new DeviceTestAdapter(this, null);
        mXRecyclerView.setAdapter(mDeviceTestAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setPullRefreshEnabled(false);
    }

    private void setIconFont(TextView textView, EAppIconFont eAppIconFont) {
        Typeface typeface = IconFontUtils.getTypeface(this, eAppIconFont);
        String value = eAppIconFont.getValue();
        textView.setText(value);
        textView.setTypeface(typeface);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void onRenderModelKpisData(List<KpiBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mKpiList.clear();
            mKpiList.addAll(data);
            mUnitPresenter.getAllUnits();
        }
    }

    @Override
    public void onRenderUnitsData(List<UnitBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mUnitList.clear();
            mUnitList.addAll(data);
            mDeviceTestAdapter.setUnitList(mUnitList);
        }
        List<Long> kpiCodes = new ArrayList<>();
        for (KpiBean kpi : mKpiList) {
            kpiCodes.add(kpi.getId());
        }
        List<Long> nodeIds = new ArrayList<>();
        nodeIds.add(mDevice.getId());
        mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds);
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            for (KpiBean kpi : mKpiList) {
                for (KpiValueBean kpiValue : data) {
                    if (kpi.getId() == kpiValue.getKpiCode()) {
                        kpi.setValue(kpiValue.getValue());
                        kpi.setArisingTime(kpiValue.getArisingTime());
                        kpi.setNodeId(kpiValue.getNodeId());
                    }
                }
            }
        }
        mDeviceTestAdapter.insertAll(mKpiList);
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
