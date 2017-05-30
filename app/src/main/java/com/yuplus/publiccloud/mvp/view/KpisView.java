package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.KpiBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * Created by zlzsa on 2017/5/30.
 */

public interface KpisView extends IView {
    void onRenderModelKpisData(List<KpiBean> data);

    void onFailure(final String msg);
}
