package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuplus.cloudsdk.future.data.bean.TicketDetailBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.TypeCst;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.widget.timeline.TimelineView;
import com.yuplus.publiccloud.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/12/2017
 * @desc
 */

public class TimeLineAdapter extends BaseUltimateViewAdapter<TicketDetailBean> {

    public TimeLineAdapter(Context context, List<TicketDetailBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_ticket_timeline, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final TicketDetailBean ticketDetail = getItem(position);
            if (TypeCst.TicketLogType.TYPE_START_EVENT.equalsIgnoreCase(ticketDetail.getLogType())) {
                viewHolder.timeMarker.setMarker(ContextCompat.getDrawable(mContext, R.drawable.timeline_start_bg));
                viewHolder.handlePeopleTv.setVisibility(View.GONE);
                viewHolder.handleDescTv.setVisibility(View.GONE);
                viewHolder.mRecyclerView.setVisibility(View.GONE);
            } else if (TypeCst.TicketLogType.TYPE_END_EVENT.equalsIgnoreCase(ticketDetail.getLogType())) {
                viewHolder.timeMarker.setMarker(ContextCompat.getDrawable(mContext, R.drawable.timeline_end_bg));
                viewHolder.handlePeopleTv.setVisibility(View.GONE);
                viewHolder.handleDescTv.setVisibility(View.GONE);
                viewHolder.mRecyclerView.setVisibility(View.GONE);
            } else {
                viewHolder.timeMarker.setMarker(ContextCompat.getDrawable(mContext, R.drawable.timeline_ing_bg));
                viewHolder.titleTv.setTextColor(mContext.getResources().getColor(R.color.time_line_ing_title_color));
                viewHolder.handlePeopleTv.setVisibility(View.VISIBLE);
                viewHolder.handleDescTv.setVisibility(View.VISIBLE);

                if (null != ticketDetail.getTicketTask() && StringUtils.isNotBlank(ticketDetail.getTicketTask().getSenderName())) {
                    viewHolder.handlePeopleTv.setText(String.format("处理人：%1s", ticketDetail.getTicketTask().getSenderName()));
                } else {
                    viewHolder.handlePeopleTv.setText("处理人：");
                }
                if (null != ticketDetail.getTicketTask() && StringUtils.isNotBlank(ticketDetail.getTicketTask().getDesc())) {
                    viewHolder.handleDescTv.setText(String.format("处理描述：%1s", ticketDetail.getTicketTask().getDesc()));
                } else {
                    viewHolder.handleDescTv.setText("处理描述：");
                }

                viewHolder.mRecyclerView.setVisibility(View.VISIBLE);
                List<String> urls = new ArrayList<>();
                urls.add("http://www.xiusekecai.com/img/index/1126/hero.jpg");
                urls.add("http://www.xiusekecai.com/img/index/1126/klj.jpg");
                urls.add("http://xiusekecai.oss-cn-hangzhou.aliyuncs.com/banner/web/invite1.jpg");
                urls.add("http://s1.knowsky.com/20170206/kcnzcrpky3019.png");
                urls.add("http://xiusekecai.oss-cn-hangzhou.aliyuncs.com/banner/web/invite1.jpg");
                urls.add("http://www.xiusekecai.com/img/index/1126/hero.jpg");
                initRecylerView(urls, ticketDetail,viewHolder.mRecyclerView);
            }

            if (StringUtils.isNotBlank(ticketDetail.getExecuteTime())) {
                viewHolder.handleDateTv.setText(DateUtils.timeFormat(ticketDetail.getExecuteTime(), "yyyy-MM-dd hh:mm"));
            }
            if (StringUtils.isNotBlank(ticketDetail.getMessage())) {
                viewHolder.titleTv.setText(ticketDetail.getMessage());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    private void initRecylerView(final List<String> urls, final TicketDetailBean ticketDetail, RecyclerView recyclerView) {
        if (ListUtils.isEmpty(urls)) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        TimeLineImagesAdapter adapter = new TimeLineImagesAdapter(mContext, urls);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickV2Listener(new OnItemClickV2Listener() {
            @Override
            public void onItemClick(View view, int position) {
                DispatchManager.startGalleryImagesActivity(mContext, ticketDetail.getMessage(), urls, position);
            }
        });
    }

    class TimeLineImagesAdapter extends BaseUltimateViewAdapter<String> {

        public TimeLineImagesAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_timeline_image, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return super.getItemCount();
        }

        @Override
        public String getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            if (position < getItemCount()) {
                final String url = this.getItem(position);
                final ViewHolder viewHolder = (ViewHolder) holder;
                if (StringUtils.isNotBlank(url)) {
                    Glide.with(mContext)
                            .load(url)
                            .into(viewHolder.imageView);
                }
            }
        }

        class ViewHolder extends UltimateViewHolder {
            @BindView(R.id.timeline_item_id_image)
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView, onItemClickV2Listener);
            }
        }
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.time_marker)
        TimelineView timeMarker;
        @BindView(R.id.text_timeline_date)
        TextView     handleDateTv;
        @BindView(R.id.text_timeline_handle_people)
        TextView     handlePeopleTv;
        @BindView(R.id.text_timeline_title)
        TextView     titleTv;
        @BindView(R.id.text_timeline_handle_desc)
        TextView     handleDescTv;
        @BindView(R.id.text_timeline_recyclerview)
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView, int viewType) {
            super(itemView, onItemClickListener);
            timeMarker.initLine(viewType);
        }
    }
}
