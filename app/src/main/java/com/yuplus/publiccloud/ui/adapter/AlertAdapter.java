package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            if (StringUtils.isNotBlank(alert.getTitle())) {
                viewHolder.mAlertLabelTv.setText(alert.getTitle());
            }
            if (StringUtils.isNotBlank(alert.getArisingTime())) {
                viewHolder.mAlertTimeTv.setText(DateUtils.timeFormat(alert.getArisingTime(), "yyyy-MM-dd hh:mm"));
            }
            if (StringUtils.isNotBlank(alert.getMessage())) {
                viewHolder.mAlertDescTv.setText(alert.getMessage());
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.alert_id_state_flag)
        TextView mStateFlagTv;
        @BindView(R.id.alert_id_label)
        TextView mAlertLabelTv;
        @BindView(R.id.alert_id_time)
        TextView mAlertTimeTv;
        @BindView(R.id.alert_id_desc)
        TextView mAlertDescTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
