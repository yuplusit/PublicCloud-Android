package com.yuplus.publiccloud.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public VerticalItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
        } else {
            outRect.top = space;
        }
    }
}
