package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.TicketBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.util.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/5/2017
 * @desc
 */

public class TicketAdapter extends BaseUltimateViewAdapter<TicketBean> {

    public TicketAdapter(Context context, List<TicketBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_order_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final TicketBean ticket = getItem(position);
            if (StringUtils.isNotBlank(ticket.getTitle())) {
                viewHolder.mTicketTitle.setText(ticket.getTitle());
            }
            if (StringUtils.isNotBlank(ticket.getCommitTime())) {
                viewHolder.mTicketDate.setText(DateUtils.timeFormat(ticket.getCommitTime(), "yyyy-MM-dd hh:mm"));
            }
            if (StringUtils.isNotBlank(ticket.getMessage())) {
                viewHolder.mTicketDesc.setText(ticket.getMessage());
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.ticket_id_title)
        TextView mTicketTitle;
        @BindView(R.id.ticket_id_date)
        TextView mTicketDate;
        @BindView(R.id.ticket_id_desc)
        TextView mTicketDesc;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
