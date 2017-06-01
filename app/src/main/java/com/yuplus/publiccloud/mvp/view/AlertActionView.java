package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.AlertActionBean;
import com.yuplus.publiccloud.mvp.base.IView;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public interface AlertActionView extends IView {
    void onRenderCalimAlertAction(final AlertActionBean alertAction);

    void onRenderRecoverAlertAction(final AlertActionBean alertAction);

    void onFailure(final String msg);
}
