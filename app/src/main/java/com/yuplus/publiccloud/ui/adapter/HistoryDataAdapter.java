package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.util.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/2/2017
 * @desc
 */

public class HistoryDataAdapter extends BaseUltimateViewAdapter<KpiValueBean> {

    public HistoryDataAdapter(Context context, List<KpiValueBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final KpiValueBean kpiValue = getItem(position);
            if (StringUtils.isNotBlank(kpiValue.getArisingTime())) {
                viewHolder.mHistoryTimeTv.setText(DateUtils.timeFormat(kpiValue.getArisingTime(), "yyyy-MM-dd hh:mm"));
                viewHolder.mHistoryIdValueTv.setText(String.valueOf(kpiValue.getValue()));
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.history_id_time)
        TextView mHistoryTimeTv;
        @BindView(R.id.history_id_value)
        TextView mHistoryIdValueTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
