package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.UnitBean;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.UnitListView;

import java.util.List;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class UnitPresenter extends BasePresenter<UnitListView> {

    public void getAllUnits() {
        AppApplication.appFutureImpl.getAllUnits(tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderUnitsData((List<UnitBean>) result.getAttach());
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
