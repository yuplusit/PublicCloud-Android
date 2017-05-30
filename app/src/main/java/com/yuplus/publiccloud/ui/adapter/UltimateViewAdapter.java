package com.yuplus.publiccloud.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public abstract class UltimateViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getAdapterItemCount();
    }

    public abstract int getAdapterItemCount();

    public void toggleSelection(int pos) {
        notifyItemChanged(pos);
    }

    public void clearSelection(int pos) {
        notifyItemChanged(pos);
    }

    public void setSelected(int pos) {
        notifyItemChanged(pos);
    }

    public void swapPositions(List<?> list, int from, int to) {
        Collections.swap(list, from, to);
    }

    public <T> void insertList(List<T> list, List<T> objectList) {
        final int start = list.size();
        list.addAll(objectList);
//        notifyItemRangeInserted(start, list.size());
        notifyDataSetChanged();
//        notifyItemRangeInserted();
    }

    public <T> void insertAll(List<T> list, List<T> objectList) {
        list.clear();
        list.addAll(objectList);
        notifyDataSetChanged();
    }

    public <T> void insert(List<T> list, T object, int position) {
        list.add(position, object);
        notifyItemInserted(position);
    }

    public void remove(List<?> list, int position) {
        //should use notifyDataSetChanged better
        if (list.size() == 0) {
            return;
        }

        if (position >= list.size()) {
            position = list.size() - 1;
        }
        //fix
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void clear(List<?> list) {
        int size = list.size();
        list.clear();
        notifyDataSetChanged();
    }
}
