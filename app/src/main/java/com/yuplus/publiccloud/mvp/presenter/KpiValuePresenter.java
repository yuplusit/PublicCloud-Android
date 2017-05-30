package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.future.data.params.MapParams;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.KpiValueView;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 5/25/2017
 * @desc
 */

public class KpiValuePresenter extends BasePresenter<KpiValueView> {

    public void getKpiValueListByNodeIds(List<Long> kpiCodes,List<Long> nodeIds) {
        MapParams params = new MapParams()
                .addParam("category", "ci")
                .addParam("isRealTimeData", true)
                .addParam("nodeIds", nodeIds)
                .addParam("kpiCodes", kpiCodes)
                .addParam("startTime", null)
                .addParam("endTime", null)
                .addParam("timeRange", "")
                .addParam("statisticType", "psiot")
                .addParam("includeInstance", true)
                .addParam("condList", new ArrayList<>());
        AppApplication.appFutureImpl.getKpiValueList(params, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderKpiValueData((List<KpiValueBean>) result.getAttach());
                getView().hideLoading();
            }

            @Override
            public void onFailure(FutureResult result) {
                super.onFailure(result);
                getView().onFailure(null == result.getException() ? "" : result.getException().getMessage());
                getView().hideLoading();
            }
        });
    }
}
