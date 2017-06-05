package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lb.materialdesigndialog.base.DialogBase;
import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.materialdesigndialog.impl.MaterialDialogNormal;
import com.yuplus.cloudsdk.future.data.bean.AlertActionBean;
import com.yuplus.cloudsdk.future.data.bean.AlertBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.mvp.presenter.AlertActionPresenter;
import com.yuplus.publiccloud.mvp.view.AlertActionView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.util.DateUtils;
import com.yuplus.publiccloud.util.IconFontUtils;
import com.yuplus.publiccloud.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class AlertDetailActivity extends TitleActivity implements AlertActionView {
    @BindView(R.id.alert_id_state_flag)
    TextView mStateFlagTv;
    @BindView(R.id.alert_id_label)
    TextView mAlertLabelTv;
    @BindView(R.id.alert_id_desc)
    TextView mAlertDescTv;

    @BindView(R.id.alert_id_flag_state)
    TextView mAlertFlagStateTv;
    @BindView(R.id.alert_id_flag_severity)
    TextView mAlertFlagSeverityTv;

    @BindView(R.id.alert_id_state_layout)
    LinearLayout mStateLayout;
    @BindView(R.id.alert_id_first_alert_time)
    TextView     mFirstAlertTimeTv;
    @BindView(R.id.alert_id_recent_alert_time)
    TextView     mRecentAlertTimeTv;
    @BindView(R.id.alert_id_close_alert_time)
    TextView     mCloseAlertTimeTv;

    @BindView(R.id.alert_id_first_alert_icon)
    TextView mFirstAlertIcon;
    @BindView(R.id.alert_id_recent_alert_icon)
    TextView mRecentAlertIcon;
    @BindView(R.id.alert_id_close_alert_icon)
    TextView mCloseAlertIcon;

    @BindView(R.id.alert_handle_id_feedback_et)
    EditText mHandleFeedbackEt;
    @BindView(R.id.alert_handle_id_close_btn)
    TextView mHandleIdCloseBtn;
    @BindView(R.id.alert_handle_id_sure_btn)
    TextView mHandleIdSureBtn;
    @BindView(R.id.alert_handle_id_order_btn)
    TextView mHandleIdOrderBtn;

    private AlertActionPresenter mAlertActionPresenter;

    private AlertBean mAlert;

    private ProgressHUBDialog mLoadingView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_alert_detail;
    }

    @Override
    protected void initPresenter() {
        mAlertActionPresenter = new AlertActionPresenter();
        mAlertActionPresenter.setView(this);
        mAlertActionPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mAlert = getIntent().getParcelableExtra(AppCst.COMMON_DATA);
        if (null == mAlert) {
            finish();
        }
        setShowTitle(true);
        setTitle("告警处理");
        setShowHomeBack();
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingView = ProgressHUBDialog.createDialog(this);
        IconFontUtils.setIconFont(mStateFlagTv, EAppIconFont.APP_ALERT_FLAG_ICON);
        IconFontUtils.setIconFont(mFirstAlertIcon, EAppIconFont.APP_CLOCK_OPEN_ICON);
        IconFontUtils.setIconFont(mRecentAlertIcon, EAppIconFont.APP_CLOCK_DOING_ICON);
        IconFontUtils.setIconFont(mCloseAlertIcon, EAppIconFont.APP_CLOCK_CLOSE_ICON);
    }

    @Override
    protected void initData() {
        super.initData();
        handleSate(mAlert);
        handleSeverity(mAlert);
        if (StringUtils.isNotBlank(mAlert.getTitle())) {
            mAlertLabelTv.setText(mAlert.getTitle());
        }
        if (StringUtils.isNotBlank(mAlert.getMessage())) {
            mAlertDescTv.setText(mAlert.getMessage());
        }
        if (StringUtils.isNotBlank(mAlert.getFirstArisingTime())) {
            mFirstAlertTimeTv.setText(DateUtils.timeFormat(mAlert.getFirstArisingTime(), "yyyy.MM.dd"));
        }
        if (StringUtils.isNotBlank(mAlert.getArisingTime())) {
            mRecentAlertTimeTv.setText(DateUtils.timeFormat(mAlert.getArisingTime(), "yyyy.MM.dd"));
        }
        mCloseAlertTimeTv.setText(getString(R.string.common_null));
        handleBtnStyle(mAlert);
    }

    public void handleBtnStyle(AlertBean alert) {
        if (mAlert.getState() < 10) {
            mHandleIdOrderBtn.setBackground(getResources().getDrawable(R.drawable.common_round_primary_bg));
        } else {
            mHandleIdOrderBtn.setBackground(getResources().getDrawable(R.drawable.common_round_grey_bg));
        }
        if (mAlert.getState() == 0) {
            mHandleIdSureBtn.setBackground(getResources().getDrawable(R.drawable.common_round_primary_bg));
        } else {
            mHandleIdSureBtn.setBackground(getResources().getDrawable(R.drawable.common_round_grey_bg));
        }
        if (mAlert.getState() < 20) {
            mHandleIdCloseBtn.setBackground(getResources().getDrawable(R.drawable.common_round_primary_bg));
        } else {
            mHandleIdCloseBtn.setBackground(getResources().getDrawable(R.drawable.common_round_grey_bg));
        }
    }

    public void handleSate(AlertBean alert) {
        final int state = alert.getState();
        String stateValue = "";
        int textColor = R.color.common_warning;
        int bgResId;
        if (state == 0) {
            stateValue = getString(R.string.alert_state_new);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        } else if (state == 5) {
            stateValue = getString(R.string.alert_state_sure);
            bgResId = R.drawable.flag_round_royal_bg;
            textColor = R.color.common_purple;
        } else if (state == 10) {
            stateValue = getString(R.string.alert_state_doing);
            bgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (state == 20) {
            stateValue = getString(R.string.alert_state_solve);
            bgResId = R.drawable.flag_round_success_bg;
            textColor = R.color.common_success;
        } else if (state == 30) {
            stateValue = getString(R.string.alert_state_skip);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        } else {
            stateValue = getString(R.string.alert_state_other);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        }
        mAlertFlagStateTv.setText(stateValue);
        mAlertFlagStateTv.setTextColor(getResources().getColor(textColor));
        mAlertFlagStateTv.setBackground(getResources().getDrawable(bgResId));
    }

    public void handleSeverity(AlertBean alert) {
        final int severity = alert.getSeverity();
        String severityValue = "";
        int textColor = R.color.common_warning;
        int bgResId = R.drawable.common_oval_alert_bg;
        int flagBgResId = R.drawable.flag_round_warning_bg;
        if (severity == 1) {
            severityValue = getString(R.string.alert_severity_01);
            bgResId = R.drawable.common_oval_alert_bg;
            flagBgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (severity == 2) {
            severityValue = getString(R.string.alert_severity_02);
            bgResId = R.drawable.common_oval_alert_bg;
            flagBgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (severity == 3) {
            severityValue = getString(R.string.alert_severity_03);
            bgResId = R.drawable.common_oval_royal_bg;
            flagBgResId = R.drawable.flag_round_royal_bg;
            textColor = R.color.common_purple;
        } else if (severity == 4) {
            severityValue = getString(R.string.alert_severity_04);
            bgResId = R.drawable.common_oval_serious_bg;
            flagBgResId = R.drawable.flag_round_serious_bg;
            textColor = R.color.common_serious;
        }
        mAlertFlagSeverityTv.setText(severityValue);
        mAlertFlagSeverityTv.setTextColor(getResources().getColor(textColor));
        mAlertFlagSeverityTv.setBackground(getResources().getDrawable(flagBgResId));
        mStateLayout.setBackground(getResources().getDrawable(bgResId));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @OnClick({R.id.alert_handle_id_close_btn, R.id.alert_handle_id_sure_btn, R.id.alert_handle_id_order_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alert_handle_id_close_btn:
                if (mAlert.getState() == 20) {
                    ToastUtils.make("该告警已经关闭，无需重复操作");
                    return;
                }
                MaterialDialogNormal dialog = new MaterialDialogNormal(AlertDetailActivity.this);
                dialog.setTitle("关闭告警");
                dialog.setMessage("你要关闭此告警吗？");
                dialog.setNegativeButton("取消", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("确定", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        mAlertActionPresenter.sendAlertRecoverAction(mAlert.getAlertId());
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.alert_handle_id_sure_btn:
                if (mAlert.getState() == 5) {
                    ToastUtils.make("该告警已经确认，无需重复操作");
                    return;
                } else if (mAlert.getState() == 20) {
                    ToastUtils.make("该告警已经解决");
                    return;
                }
                final MaterialDialogNormal dialog1 = new MaterialDialogNormal(AlertDetailActivity.this);
                dialog1.setTitle("确认告警");
                dialog1.setMessage("你要确认此告警吗？");
                dialog1.setNegativeButton("取消", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        dialog1.dismiss();
                    }
                });
                dialog1.setPositiveButton("确定", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        mAlertActionPresenter.sendAlertClaimAction(mAlert.getAlertId());
                        dialog1.dismiss();
                    }
                });

                break;
            case R.id.alert_handle_id_order_btn:
                if (mAlert.getState() == 20) {
                    ToastUtils.make("该告警已经解决,无法转工单");
                    return;
                }
                ToastUtils.make(R.string.alert_order_function_no_opened);
                break;
        }
    }

    @Override
    public void onRenderCalimAlertAction(AlertActionBean alertAction) {
        if (null != alertAction && alertAction.isDone()) {
            mHandleIdSureBtn.setBackground(getResources().getDrawable(R.drawable.common_round_grey_bg));
            ToastUtils.make("确认成功！");
            mAlert.setState(5);
            handleSate(mAlert);
            handleBtnStyle(mAlert);
            DispatchManager.sendUpdateAlertInfoBroadCast(this, mAlert);
        } else {
            ToastUtils.make("确认失败！");
        }
    }

    @Override
    public void onRenderRecoverAlertAction(AlertActionBean alertAction) {
        if (null != alertAction && alertAction.isDone()) {
            mHandleIdCloseBtn.setBackground(getResources().getDrawable(R.drawable.common_round_grey_bg));
            ToastUtils.make("关闭成功！");
            mAlert.setState(20);
            handleSate(mAlert);
            handleBtnStyle(mAlert);
            DispatchManager.sendUpdateAlertInfoBroadCast(this, mAlert);
        } else {
            ToastUtils.make("关闭失败！");
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
    protected void onDestroy() {
        super.onDestroy();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
