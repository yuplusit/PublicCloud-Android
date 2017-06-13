package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.data.bean.TicketDetailBean;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.TicketNoView;

import java.util.List;

/**
 * @user longzhen
 * @date 6/12/2017
 * @desc
 */

public class TicketNoPresenter extends BasePresenter<TicketNoView> {

    public void getTicketNo(long ticketNo) {

        AppApplication.appFutureImpl.getByTicketNo(ticketNo, tag, new FutureListener() {
            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onRenderTicketNOData((List<TicketDetailBean>) result.getAttach());
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
