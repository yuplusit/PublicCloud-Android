package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.future.data.bean.ProjectBean;
import com.yuplus.cloudsdk.log.LogUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/26/2017
 * @desc
 */

public class CustomerDetailAdapter extends BaseUltimateViewAdapter<ProjectBean> {

    public CustomerDetailAdapter(Context context, List<ProjectBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_customer_project, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final ProjectBean project = getItem(position);
            LogUtils.t("onBindViewHolder").d(JSON.toJSON(project));
            if (StringUtils.isNotBlank(project.getProjectName())) {
                viewHolder.mProjectNameTv.setText(project.getProjectName());
            }
            viewHolder.mDeviceCountTv.setText(String.valueOf(project.getDeviceCount()));
            viewHolder.mAlertCountTv.setText(String.valueOf(project.getAlertCount()));
            viewHolder.mOrderCountTv.setText(String.valueOf(project.getOrderCount()));
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.project_id_system_name)
        TextView mProjectNameTv;
        @BindView(R.id.project_id_device_icon)
        TextView mDeviceIconTv;
        @BindView(R.id.project_id_device_count)
        TextView mDeviceCountTv;
        @BindView(R.id.project_id_alert_icon)
        TextView mAlertIconTv;
        @BindView(R.id.project_id_alert_count)
        TextView mAlertCountTv;
        @BindView(R.id.project_id_order_icon)
        TextView mOrderIconTv;
        @BindView(R.id.project_id_order_count)
        TextView mOrderCountTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }

}