package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/27/2017
 * @desc
 */

public interface DeviceListView extends IView{

    void onRenderDeviceData(List<ConfigurationBean> data);

    void onFailure(final String msg);
}
