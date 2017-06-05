package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.publiccloud.mvp.base.IView;

/**
 * @user longzhen
 * @date 6/5/2017
 * @desc
 */

public interface DeviceView extends IView {
    void onDeviceSuccess(final DeviceBean device);

    void onFailure(final String msg);
}
