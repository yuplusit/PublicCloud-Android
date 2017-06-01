package com.yuplus.publiccloud.ui.fragment;

import android.widget.TextView;

import com.github.premnirmal.textcounter.CounterView;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.widget.AnimateHorizontalProgressBar;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class HomeFragment extends BaseFragment implements KpiValueView {
    @BindView(R.id.home_id_undo_alert_count)
    CounterView mUntreatedAlertCount;
    @BindView(R.id.home_id_day_alert_add_count)
    CounterView mDayAlertAddCount;
    @BindView(R.id.home_id_day_alert_no_deal_count)
    CounterView mDayAlertUntreatedCount;
    @BindView(R.id.home_id_done_alert_count)
    CounterView mDoneAlertCount;
    @BindView(R.id.home_id_device_all_count)
    CounterView mDeviceAllCount;
    @BindView(R.id.home_id_device_online_count)
    CounterView mDeviceOnlineCount;
    @BindView(R.id.home_id_device_add_count)
    CounterView mDeviceAddCount;
    @BindView(R.id.home_id_order_all_count)
    CounterView mOrderAllCount;
    @BindView(R.id.home_id_order_no_deal_count)
    CounterView mOrderUntreatedCount;
    @BindView(R.id.home_id_order_server_count)
    CounterView mOrderServerCount;

    @BindView(R.id.home_id_evaluate_bar_01)
    AnimateHorizontalProgressBar mEvaluateProgressBar01;
    @BindView(R.id.home_id_evaluate_bar_02)
    AnimateHorizontalProgressBar mEvaluateProgressBar02;
    @BindView(R.id.home_id_evaluate_bar_03)
    AnimateHorizontalProgressBar mEvaluateProgressBar03;
    @BindView(R.id.home_id_evaluate_bar_04)
    AnimateHorizontalProgressBar mEvaluateProgressBar04;
    @BindView(R.id.home_id_evaluate_text_01)
    TextView                     mEvaluateText01;
    @BindView(R.id.home_id_evaluate_text_02)
    TextView                     mEvaluateText02;
    @BindView(R.id.home_id_evaluate_text_03)
    TextView                     mEvaluateText03;
    @BindView(R.id.home_id_evaluate_text_04)
    TextView                     mEvaluateText04;

    private float[] mEvaluateRates = {0.62f, 0.26f, 0.10f, 0.02f};
    private ProgressHUBDialog mLoadingView;

    private KpiValuePresenter mKpiValuePresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());
        mEvaluateProgressBar01.setMax(100);
        mEvaluateProgressBar01.setProgress(100);
        mEvaluateProgressBar02.setMax(100);
        mEvaluateProgressBar02.setProgress(100);
        mEvaluateProgressBar03.setMax(100);
        mEvaluateProgressBar03.setProgress(0);
        mEvaluateProgressBar04.setMax(100);
        mEvaluateProgressBar04.setProgress(0);
    }

    @Override
    protected void initData() {
        super.initData();
        mEvaluateProgressBar01.setProgressWithAnim((int) ((1 - mEvaluateRates[0]) * 100));
        mEvaluateProgressBar02.setProgressWithAnim((int) ((1 - mEvaluateRates[1]) * 100));
        mEvaluateProgressBar03.setProgressWithAnim((int) (mEvaluateRates[2] * 100));
        mEvaluateProgressBar04.setProgressWithAnim((int) (mEvaluateRates[3] * 100));

        mEvaluateText01.setText(String.format("%1$d%%", (int) (mEvaluateRates[0] * 100)));
        mEvaluateText02.setText(String.format("%1$d%%", (int) (mEvaluateRates[1] * 100)));
        mEvaluateText03.setText(String.format("%1$d%%", (int) (mEvaluateRates[2] * 100)));
        mEvaluateText04.setText(String.format("%1$d%%", (int) (mEvaluateRates[3] * 100)));

        List<Long> nodeIds = new ArrayList<>();
        nodeIds.add(AppApplication.user.getDomainID());
        List<Long> kpiCodes = new ArrayList<>();
        kpiCodes.add(3012L);//总设备数
        kpiCodes.add(3014L);//在线设备
        kpiCodes.add(3033L);//月增加设备
        kpiCodes.add(3004L);//待处理工单
        kpiCodes.add(3022L);//服务客户数
        kpiCodes.add(3005L);//日新增告警数
        kpiCodes.add(3003L);//待处理告警数，1小时统计一次
        mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds);
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            for (KpiValueBean kpiValue : data) {
                if (kpiValue.getKpiCode() == 3012L) {
                    mDeviceAllCount.setEndValue(kpiValue.getValue());
                    mDeviceAllCount.start();
                } else if (kpiValue.getKpiCode() == 3014L) {
                    mDeviceOnlineCount.setEndValue(kpiValue.getValue());
                    mDeviceOnlineCount.start();
                } else if (kpiValue.getKpiCode() == 3033L) {
                    mDeviceAddCount.setEndValue(kpiValue.getValue());
                    mDeviceAddCount.start();
                } else if (kpiValue.getKpiCode() == 3004L) {
                    mOrderUntreatedCount.setEndValue(kpiValue.getValue());
                    mOrderUntreatedCount.start();
                } else if (kpiValue.getKpiCode() == 3022L) {
                    mOrderServerCount.setEndValue(kpiValue.getValue());
                    mOrderServerCount.start();
                } else if (kpiValue.getKpiCode() == 3005L) {
                    mDayAlertAddCount.setEndValue(kpiValue.getValue());
                    mDayAlertAddCount.start();
                } else if (kpiValue.getKpiCode() == 3003L) {
                    mDayAlertUntreatedCount.setEndValue(kpiValue.getValue());
                    mDayAlertUntreatedCount.start();
                }
            }
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
