package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.DeviceListView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/27/2017
 * @desc
 */

public class DevicePresenter extends BasePresenter<DeviceListView> {

    public void getDevicesByConditionWithPage(int start, int pageSize) {
        AppApplication.appFutureImpl.getDevicesByConditionWithPage(start, pageSize, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderDeviceData((List<ConfigurationBean>) result.getAttach());
                getView().showLoading();
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
