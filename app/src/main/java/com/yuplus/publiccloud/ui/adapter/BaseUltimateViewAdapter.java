package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public abstract class BaseUltimateViewAdapter<T> extends UltimateViewAdapter {
    protected RecyclerView mRecyclerView;
    protected List<T> mData = new ArrayList<>();
    protected OnItemClickListener   onItemClickListener;
    protected OnItemClickV2Listener onItemClickV2Listener;
    protected Context               mContext;

    public BaseUltimateViewAdapter(final Context context) {
        this.mContext = context;
    }

    public BaseUltimateViewAdapter(final Context context, final List<T> data) {
        this.mContext = context;
        if (null != data) {
            this.mData = data;
        }
    }

    @Override
    public int getAdapterItemCount() {
        return null == mData ? 0 : mData.size();
    }

    public void insert(final T value, final int position) {
        insert(mData, value, position);
    }

    public void remove(final int position) {
        remove(mData, position);
    }

    public void insertList(List<T> objectList) {
        insertList(mData, objectList);
    }

    public void insertAll(List<T> objectList) {
        insertAll(mData, objectList);
    }

    public T getItem(final int position) {
        if (position < mData.size()) {
            return mData.get(position);
        } else {
            return null;
        }
    }

    public List<T> getData() {
        return mData;
    }


    public void clear() {
        super.clear(mData);
    }

    /**
     * 获取ViewHolder
     * 用这个方法前，必须得调用setRecyclerView()方法
     *
     * @param position
     * @return
     */
    public RecyclerView.ViewHolder getViewHolder(final int position) {
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        RecyclerView.ViewHolder viewHolder = null;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] firstPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
            int[] lastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            int firstPosition = 0;
            int lastPosition = 0;
            if (null != firstPositions) {
                firstPosition = firstPositions[0];
            }

            if (null != lastPositions) {
                lastPosition = lastPositions[lastPositions.length - 1];
            }

            if (position >= firstPosition && position <= lastPosition) {
                viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position - firstPosition));
            }
        } else {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
            if (position >= firstPosition && position <= lastPosition) {
                viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position - firstPosition));
            }
        }

        return viewHolder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClickV2Listener(OnItemClickV2Listener onItemClickV2Listener) {
        this.onItemClickV2Listener = onItemClickV2Listener;
    }

    public void setRecyclerView(final RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }


    public interface OnItemClickListener {
        void onItemClick(final View view, final int position);
    }

    public interface OnItemClickV2Listener {
        void onItemClick(final View view, final int position);
    }
}
