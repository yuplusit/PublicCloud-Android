package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public interface CustomerView extends IView {
    void onRenderCustomerData(List<CustomerBean> data);

    void onFailure(final String msg);
}
