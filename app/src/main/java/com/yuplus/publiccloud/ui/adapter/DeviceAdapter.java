package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;

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
            if(StringUtils.isNotBlank(device.getLabel())){
                viewHolder.mDeviceNameTv.setText(device.getLabel());
            }
            if(StringUtils.isNotBlank(device.getSn())){
                viewHolder.mSnNameTv.setText(device.getSn());
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
        @BindView(R.id.device_id_state_swtich)
        TextView  mDeviceSwitchTv;
        @BindView(R.id.device_id_state_isonline)
        TextView  mIsOnlineTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
