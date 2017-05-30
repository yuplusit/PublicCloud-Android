package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.AlertListBean;
import com.yuplus.publiccloud.mvp.base.IView;

/**
 * Created by zlzsa on 2017/5/30.
 */

public interface AlertListView extends IView {
    void onRenderAlertData(final boolean isRefresh, AlertListBean data);

    void onFailure(final String msg);
}
