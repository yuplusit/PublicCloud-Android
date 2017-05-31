package com.yuplus.publiccloud.ui.fragment;

import android.widget.TextView;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_id_undo_alert_count)
    TextView mUntreatedAlertCount;
    @BindView(R.id.home_id_day_alert_add_count)
    TextView mDayAlertAddCount;
    @BindView(R.id.home_id_day_alert_no_deal_count)
    TextView mDayAlertUntreatedCount;
    @BindView(R.id.home_id_done_alert_count)
    TextView mDoneAlertCount;
    @BindView(R.id.home_id_device_all_count)
    TextView mDeviceAllCount;
    @BindView(R.id.home_id_device_online_count)
    TextView mDeviceOnlineCount;
    @BindView(R.id.home_id_device_add_count)
    TextView mDeviceAddCount;
    @BindView(R.id.home_id_order_all_count)
    TextView mOrderAllCount;
    @BindView(R.id.home_id_order_no_deal_count)
    TextView mOrderUntreatedCount;
    @BindView(R.id.home_id_order_server_count)
    TextView mOrderServerCount;
    Unbinder unbinder;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {

    }
}
