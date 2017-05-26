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

    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition() - 1);//-1为了出去第一个下拉view
        }
    }

    private BaseUltimateViewAdapter.OnItemClickListener mOnItemClickListener;
}
