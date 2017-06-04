package com.yuplus.publiccloud.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.future.ApiCst;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.future.data.bean.KpiBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.future.data.bean.UnitBean;
import com.yuplus.cloudsdk.future.data.params.MapParams;
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
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.adapter.BaseUltimateViewAdapter;
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
    @BindView(R.id.recylerview_id_empty_data)
    View mEmptyDataView;
    @BindView(R.id.device_id_share)
    ImageView mDeviceShareBtn;

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
        setTitle("设备信息");
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
            mDeviceOpenBtn.setBackground(getResources().getDrawable(R.drawable.detail_swtich_btn_sec));
            mDeviceOpenBtn.setTextColor(getResources().getColor(R.color.common_text_color_white_to_primary));
            mDeviceStopBtn.setBackground(getResources().getDrawable(R.drawable.detail_swtich_btn_first));
            mDeviceStopBtn.setTextColor(getResources().getColor(R.color.common_text_color_primary_to_white));
        } else {
            mDeviceOpenBtn.setBackground(getResources().getDrawable(R.drawable.detail_swtich_btn_first));
            mDeviceOpenBtn.setTextColor(getResources().getColor(R.color.common_text_color_primary_to_white));
            mDeviceStopBtn.setBackground(getResources().getDrawable(R.drawable.detail_swtich_btn_sec));
            mDeviceStopBtn.setTextColor(getResources().getColor(R.color.common_text_color_white_to_primary));
        }
        if (StringUtils.isNotBlank(mDevice.getSn())) {
            mDeviceSnTv.setText(mDevice.getSn());
        }
        if (StringUtils.isNotBlank(mDevice.getCreateTime())) {
            mDeviceProductTv.setText(DateUtils.timeFormat(mDevice.getCreateTime(), "yyyy-MM-dd hh:mm"));
        }
        IconFontUtils.setIconFont(mDeviceTestNameTv, EAppIconFont.APP_TEST_NAME_ICON);
        IconFontUtils.setIconFont(mDeviceTestValueTv, EAppIconFont.APP_TEST_VALUE_ICON);
        IconFontUtils.setIconFont(mDeviceTestDateTv, EAppIconFont.APP_TEST_CALENDAR_ICON);
        IconFontUtils.setIconFont(mDeviceTestHistoryTv, EAppIconFont.APP_TEST_HISTORY_ICON);


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

    @Override
    protected void initListener() {
        super.initListener();
        mDeviceTestAdapter.setOnItemClickListener(new BaseUltimateViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final KpiBean kpi = mDeviceTestAdapter.getItem(position);
                String testName = "";
                if (StringUtils.isNotBlank(kpi.getName())) {
                    testName += kpi.getName();
                }
                if (StringUtils.isNotBlank(kpi.getUnit())) {
                    testName += "(" + mDeviceTestAdapter.getUnitFlag(kpi.getUnit()) + ")";
                }
                DispatchManager.startDeviceHistoryDataActivity(DeviceDetailActivity.this, kpi.getId(), kpi.getNodeId(), testName);
            }
        });
        mDeviceShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapParams params = new MapParams()
                        .addParam("device_id", mDevice.getId())
                        .addParam("cookie", CloudSDKManager.getInstance().getJsessionId())
                        .addParam("api_prefix", ApiCst.REQUEST_API)
                        .addParam("expire_seconds", 300);
                DispatchManager.startQRCodeActivity(DeviceDetailActivity.this, params.toJson());
            }
        });
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
        mXRecyclerView.setEmptyView(mEmptyDataView);
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
