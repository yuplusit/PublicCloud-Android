package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public class CustomerAdapter extends BaseUltimateViewAdapter<CustomerBean> {

    public CustomerAdapter(Context context, List<CustomerBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_customer, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final CustomerBean customer = getItem(position);

            if (StringUtils.isNotBlank(customer.getCustomerName())) {
                viewHolder.mCustomerNameTv.setText(customer.getCustomerName());
            }

            viewHolder.mDeviceCountTv.setText(String.valueOf(customer.getDeviceCount()));

            viewHolder.mAlertCountTv.setText(String.valueOf(customer.getAlertCount()));

            viewHolder.mOrderCountTv.setText(String.valueOf(customer.getOrderCount()));

            if (StringUtils.isNotBlank(customer.getCustomerContact())) {
                viewHolder.mManagerNameTv.setText(customer.getCustomerContact());
            }

            if (StringUtils.isNotBlank(customer.getImageUrl())) {
                Glide.with(mContext)
                        .load(customer.getImageUrl())
                        .error(R.drawable.ic_customer_bg)
                        .into(viewHolder.mCustomerIv);
            } else {
                Glide.with(mContext)
                        .load(R.drawable.ic_customer_bg)
                        .into(viewHolder.mCustomerIv);
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.customer_id_imageView)
        ImageView mCustomerIv;
        @BindView(R.id.customer_id_name)
        TextView  mCustomerNameTv;
        @BindView(R.id.customer_id_device_count)
        TextView  mDeviceCountTv;
        @BindView(R.id.customer_id_alert_count)
        TextView  mAlertCountTv;
        @BindView(R.id.customer_id_order_count)
        TextView  mOrderCountTv;
        @BindView(R.id.customer_id_manager)
        TextView  mManagerNameTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
