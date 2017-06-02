package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.AlertBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.util.DateUtils;
import com.yuplus.publiccloud.util.IconFontUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class AlertAdapter extends BaseUltimateViewAdapter<AlertBean> {

    public AlertAdapter(Context context, List<AlertBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_alert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            ViewHolder viewHolder = (ViewHolder) holder;
            AlertBean alert = getItem(position);
            IconFontUtils.setIconFont(viewHolder.mStateFlagTv, EAppIconFont.APP_ALERT_FLAG_ICON);
            IconFontUtils.setIconFont(viewHolder.mFirstAlertIcon, EAppIconFont.APP_CLOCK_OPEN_ICON);
            IconFontUtils.setIconFont(viewHolder.mRecentAlertIcon, EAppIconFont.APP_CLOCK_DOING_ICON);
            IconFontUtils.setIconFont(viewHolder.mCloseAlertIcon, EAppIconFont.APP_CLOCK_CLOSE_ICON);
            if (StringUtils.isNotBlank(alert.getTitle())) {
                viewHolder.mAlertLabelTv.setText(alert.getTitle());
            }
            if (StringUtils.isNotBlank(alert.getMessage())) {
                viewHolder.mAlertDescTv.setText(alert.getMessage());
            }
            if (StringUtils.isNotBlank(alert.getFirstArisingTime())) {
                viewHolder.mFirstAlertTimeTv.setText(DateUtils.timeFormat(alert.getFirstArisingTime(), "yyyy.MM.dd"));
            }
            if (StringUtils.isNotBlank(alert.getArisingTime())) {
                viewHolder.mRecentAlertTimeTv.setText(DateUtils.timeFormat(alert.getArisingTime(), "yyyy.MM.dd"));
            }
            viewHolder.mCloseAlertTimeTv.setText(mContext.getString(R.string.common_null));
            handleSate(viewHolder, alert);
            handleSeverity(viewHolder, alert);
        }
    }

    public void handleSate(ViewHolder viewHolder, AlertBean alert) {
        final int state = alert.getState();
        String stateValue = "";
        int textColor = R.color.common_warning;
        int bgResId;
        if (state == 0) {
            stateValue = mContext.getString(R.string.alert_state_new);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        } else if (state == 5) {
            stateValue = mContext.getString(R.string.alert_state_sure);
            bgResId = R.drawable.flag_round_royal_bg;
            textColor = R.color.common_purple;
        } else if (state == 10) {
            stateValue = mContext.getString(R.string.alert_state_doing);
            bgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (state == 20) {
            stateValue = mContext.getString(R.string.alert_state_solve);
            bgResId = R.drawable.flag_round_success_bg;
            textColor = R.color.common_success;
        } else if (state == 30) {
            stateValue = mContext.getString(R.string.alert_state_skip);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        } else {
            stateValue = mContext.getString(R.string.alert_state_other);
            bgResId = R.drawable.flag_round_primary_bg;
            textColor = R.color.common_primary;
        }
        viewHolder.mAlertFlagStateTv.setText(stateValue);
        viewHolder.mAlertFlagStateTv.setTextColor(mContext.getResources().getColor(textColor));
        viewHolder.mAlertFlagStateTv.setBackground(mContext.getResources().getDrawable(bgResId));
    }

    public void handleSeverity(ViewHolder viewHolder, AlertBean alert) {
        final int severity = alert.getSeverity();
        String severityValue = "";
        int textColor = R.color.common_warning;
        int bgResId = R.drawable.common_oval_alert_bg;
        int flagBgResId = R.drawable.flag_round_warning_bg;
        if (severity == 1) {
            severityValue = mContext.getString(R.string.alert_severity_01);
            bgResId = R.drawable.common_oval_alert_bg;
            flagBgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (severity == 2) {
            severityValue = mContext.getString(R.string.alert_severity_02);
            bgResId = R.drawable.common_oval_alert_bg;
            flagBgResId = R.drawable.flag_round_warning_bg;
            textColor = R.color.common_warning;
        } else if (severity == 3) {
            severityValue = mContext.getString(R.string.alert_severity_03);
            bgResId = R.drawable.common_oval_royal_bg;
            flagBgResId = R.drawable.flag_round_royal_bg;
            textColor = R.color.common_purple;
        } else if (severity == 4) {
            severityValue = mContext.getString(R.string.alert_severity_04);
            bgResId = R.drawable.common_oval_serious_bg;
            flagBgResId = R.drawable.flag_round_serious_bg;
            textColor = R.color.common_serious;
        }
        viewHolder.mAlertFlagSeverityTv.setText(severityValue);
        viewHolder.mAlertFlagSeverityTv.setTextColor(mContext.getResources().getColor(textColor));
        viewHolder.mAlertFlagSeverityTv.setBackground(mContext.getResources().getDrawable(flagBgResId));
        viewHolder.mStateLayout.setBackground(mContext.getDrawable(bgResId));
    }

    class ViewHolder extends UltimateViewHolder {
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

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
