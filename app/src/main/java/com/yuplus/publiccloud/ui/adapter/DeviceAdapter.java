package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.util.IconFontUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/27/2017
 * @desc
 */

public class DeviceAdapter extends BaseUltimateViewAdapter<DeviceBean> {

    public DeviceAdapter(Context context, List<DeviceBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_device, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final DeviceBean device = getItem(position);
            if (StringUtils.isNotBlank(device.getLabel())) {
                viewHolder.mDeviceNameTv.setText(device.getLabel());
            }
            if (StringUtils.isNotBlank(device.getSn())) {
                viewHolder.mSnNameTv.setText(device.getSn());
            }
            if (StringUtils.isNotBlank(device.getCustomerName())) {
                viewHolder.mCustomerNameTv.setText(device.getCustomerName());
            } else {
                viewHolder.mCustomerNameTv.setText(R.string.common_no);
            }
            if ("active".equalsIgnoreCase(device.getManagedStatus())) {
                //启用
                viewHolder.mDeviceSwitchTv.setText(R.string.device_managed_status_01);
                viewHolder.mDeviceSwitchTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
                IconFontUtils.setIconFont(viewHolder.mDeviceSwitchIconTv, EAppIconFont.APP_DEVICE_OPENED_ICON);
                viewHolder.mDeviceSwitchIconTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
            } else {
                //停用
                viewHolder.mDeviceSwitchTv.setText(R.string.device_managed_status_02);
                viewHolder.mDeviceSwitchTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
                IconFontUtils.setIconFont(viewHolder.mDeviceSwitchIconTv, EAppIconFont.APP_DEVICE_STOPED_ICON);
                viewHolder.mDeviceSwitchIconTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
            }
            if (device.getOnlineStatus() == 1) {
                //在线
                viewHolder.mIsOnlineTv.setText(R.string.device_online_status_01);
                viewHolder.mIsOnlineTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
                IconFontUtils.setIconFont(viewHolder.mIsOnlineIconTv, EAppIconFont.APP_DEVICE_ONLINE_ICON);
                viewHolder.mIsOnlineIconTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
            } else if (device.getOnlineStatus() == 0) {
                //离线
                viewHolder.mIsOnlineTv.setText(R.string.device_online_status_02);
                viewHolder.mIsOnlineTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
                IconFontUtils.setIconFont(viewHolder.mIsOnlineIconTv, EAppIconFont.APP_DEVICE_OFFLINE_ICON);
                viewHolder.mIsOnlineIconTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
            } else {
                //无数据
                viewHolder.mIsOnlineTv.setText(R.string.device_online_status_03);
                viewHolder.mIsOnlineTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
                IconFontUtils.setIconFont(viewHolder.mIsOnlineIconTv, EAppIconFont.APP_DEVICE_OFFLINE_ICON);
                viewHolder.mIsOnlineIconTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
            }
            if (device.getSeverity() < 1) {
                //正常
                viewHolder.mIsNormalStateTv.setText(R.string.device_severity_status_01);
                viewHolder.mIsNormalStateTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
                IconFontUtils.setIconFont(viewHolder.mIsNormalIconTv, EAppIconFont.APP_DEVICE_NORMAL_ICON);
                viewHolder.mIsNormalIconTv.setTextColor(mContext.getResources().getColor(R.color.common_success));
            } else {
                //告警
                viewHolder.mIsNormalStateTv.setText(R.string.device_severity_status_02);
                viewHolder.mIsNormalStateTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
                IconFontUtils.setIconFont(viewHolder.mIsNormalIconTv, EAppIconFont.APP_DEVICE_ABNORMAL_ICON);
                viewHolder.mIsNormalIconTv.setTextColor(mContext.getResources().getColor(R.color.common_warning));
            }
        }
    }

    public void notifyItem(DeviceBean device) {
        if (ListUtils.isEmpty(mData)) {
            return;
        }
        for (int position = 0; position < mData.size(); position++) {
            if (device.getId() == mData.get(position).getId()) {
                mData.set(position, device);
                notifyItemChanged(position + 1);//+1是为了除去下拉的头
                break;
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.device_id_img)
        ImageView mDeviceBgIv;
        @BindView(R.id.device_id_device_name)
        TextView  mDeviceNameTv;
        @BindView(R.id.device_id_sn_name)
        TextView  mSnNameTv;
        @BindView(R.id.device_id_customer_name)
        TextView  mCustomerNameTv;
        @BindView(R.id.device_id_state_isnormal)
        TextView  mIsNormalStateTv;
        @BindView(R.id.device_id_state_isnormal_icon)
        TextView  mIsNormalIconTv;
        @BindView(R.id.device_id_state_swtich)
        TextView  mDeviceSwitchTv;
        @BindView(R.id.device_id_state_swtich_icon)
        TextView  mDeviceSwitchIconTv;
        @BindView(R.id.device_id_state_isonline)
        TextView  mIsOnlineTv;
        @BindView(R.id.device_id_state_swtich_isonline)
        TextView  mIsOnlineIconTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
