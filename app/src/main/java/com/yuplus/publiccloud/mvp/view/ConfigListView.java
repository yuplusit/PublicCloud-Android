package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/26/2017
 * @desc
 */

public interface ConfigListView extends IView {
    void onRenderConfigData(List<ConfigurationBean> data);

    void onFailure(final String msg);
}
