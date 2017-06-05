package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.TicketBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 6/5/2017
 * @desc
 */

public interface TicketsListView extends IView {
    void onRenderTicketsData(List<TicketBean> data);

    void onFailure(final String msg);
}
