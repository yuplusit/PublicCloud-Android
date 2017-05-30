package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.AlertListBean;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.AlertListView;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class AlertPresenter extends BasePresenter<AlertListView> {

    public void getAlert(final boolean isRefresh, int start, int pageSize, String severities, String states) {
        AppApplication.appFutureImpl.getAlertByPage(start, pageSize, severities, states, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderAlertData(isRefresh, (AlertListBean) result.getAttach());
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
