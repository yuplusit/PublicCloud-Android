package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.TicketCategoryBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 6/13/2017
 * @desc
 */

public interface TicketCategoryView extends IView {
    void onRenderTicketCategoryData(List<TicketCategoryBean> data);

    void onFailure(final String msg);
}
