package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public interface KpiValueView extends IView {
    void onRenderKpiValueData(List<KpiValueBean> data);

    void onFailure(final String msg);
}
