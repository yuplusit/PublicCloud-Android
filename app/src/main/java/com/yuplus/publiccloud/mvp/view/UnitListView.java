package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.UnitBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * Created by zlzsa on 2017/5/30.
 */

public interface UnitListView extends IView {

    void onRenderUnitsData(List<UnitBean> data);

    void onFailure(final String msg);
}
