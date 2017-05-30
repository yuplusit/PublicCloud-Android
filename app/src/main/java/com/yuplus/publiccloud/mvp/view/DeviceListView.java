package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceListBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/27/2017
 * @desc
 */

public interface DeviceListView extends IView{

    void onRenderDeviceData(final boolean isRefresh,DeviceListBean data);

    void onFailure(final String msg);
}
