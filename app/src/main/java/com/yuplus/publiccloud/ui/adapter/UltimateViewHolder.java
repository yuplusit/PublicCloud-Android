package com.yuplus.publiccloud.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public class UltimateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public UltimateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public UltimateViewHolder(View itemView, BaseUltimateViewAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mOnItemClickListener = onItemClickListener;
    }

    public UltimateViewHolder(View itemView, BaseUltimateViewAdapter.OnItemClickV2Listener onItemClickV2Listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mOnItemClickV2Listener = onItemClickV2Listener;
    }

    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition() - 1);//-1为了出去第一个下拉view
        }
        if (null != mOnItemClickV2Listener) {
            mOnItemClickV2Listener.onItemClick(v, getLayoutPosition());
        }
    }

    private BaseUltimateViewAdapter.OnItemClickListener   mOnItemClickListener;//带有下拉刷新的监听
    private BaseUltimateViewAdapter.OnItemClickV2Listener mOnItemClickV2Listener; //不同的itemClick监听
}
