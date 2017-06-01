package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.DeviceListBean;
import com.yuplus.cloudsdk.future.data.params.ListParams;
import com.yuplus.cloudsdk.future.data.params.MapParams;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.DeviceListView;

/**
 * @user longzhen
 * @date 5/27/2017
 * @desc
 */

public class DevicePresenter extends BasePresenter<DeviceListView> {

    public void getDevicesByConditionWithPage(final boolean isRefresh, final long projectId, final int start, final int pageSize, int total) {
        AppApplication.appFutureImpl.getDevicesByConditionWithPage(projectId, start, pageSize, total, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderDeviceData(isRefresh, (DeviceListBean) result.getAttach());
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

    //通过设备序列号或者设备名称来查询设备信息
    public void searchDeviceByConditionWithPage(final boolean isRefresh, final String orCondition, final int start, final int pageSize, int total) {
        ListParams conditionField = new ListParams()
                .addParam("sn")
                .addParam("label");
        MapParams params1 = new MapParams()
                .addParam("orCondition", orCondition)
                .addParam("conditionField", conditionField.getParams());
        MapParams params2 = new MapParams()
                .addParam("start", start)
                .addParam("length", pageSize)
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", true)
                .addParam("total", total);
        ListParams requestParams = new ListParams()
                .addParam(params1.getParams())
                .addParam(params2.getParams());
        AppApplication.appFutureImpl.getDevicesByConditionWithPage(requestParams, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderDeviceData(isRefresh, (DeviceListBean) result.getAttach());
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
