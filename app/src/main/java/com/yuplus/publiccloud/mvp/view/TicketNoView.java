package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.TicketDetailBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 6/12/2017
 * @desc
 */

public interface TicketNoView extends IView {
    void onRenderTicketNOData(List<TicketDetailBean> data);

    void onFailure(final String msg);
}
