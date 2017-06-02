package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.KpiBean;
import com.yuplus.cloudsdk.future.data.bean.UnitBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.util.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class DeviceTestAdapter extends BaseUltimateViewAdapter<KpiBean> {
    private List<UnitBean> mUnitList;

    public DeviceTestAdapter(Context context, List<KpiBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_device_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final KpiBean kpi = getItem(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            if (StringUtils.isNotBlank(kpi.getName())) {
                viewHolder.mTestNameTv.setText(kpi.getName());
            }
            String unit = "";
            if (StringUtils.isNotBlank(kpi.getUnit())) {
                String temp = getUnitFlag(kpi.getUnit());
                if (StringUtils.isNotBlank(temp)) {
                    unit = temp;
                }
            }
            viewHolder.mTestValueTv.setText(kpi.getValue() + unit);

            if (StringUtils.isNotBlank(kpi.getArisingTime())) {
                viewHolder.mTestDateTv.setText(DateUtils.timeFormat(kpi.getArisingTime(), "yyyy-MM-dd"));
                viewHolder.mTestTimeTv.setText(DateUtils.timeFormat(kpi.getArisingTime(), "hh:mm:ss"));
            }

        }
    }

    public String getUnitFlag(String key) {
        if (null == mUnitList || StringUtils.isBlank(key)) {
            return null;
        }
        for (UnitBean unit : mUnitList) {
            if (unit.getUnitCode().equalsIgnoreCase(key)) {
                return unit.getUnitName();
            }
        }
        return null;
    }

    public List<UnitBean> getUnitList() {
        return mUnitList;
    }

    public void setUnitList(List<UnitBean> unitList) {
        this.mUnitList = unitList;
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.test_id_name)
        TextView mTestNameTv;
        @BindView(R.id.test_id_value)
        TextView mTestValueTv;
        @BindView(R.id.test_id_time_01)
        TextView mTestDateTv;
        @BindView(R.id.test_id_time_02)
        TextView mTestTimeTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
