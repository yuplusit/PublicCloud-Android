package com.yuplus.publiccloud.mvp.view;

import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public interface BaseListView<T> extends IView {
    void onRenderData(List<T> data);

    void onFailure(final String msg);
}
