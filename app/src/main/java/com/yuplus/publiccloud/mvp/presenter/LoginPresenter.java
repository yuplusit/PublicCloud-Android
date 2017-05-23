package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.LoginView;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    @Override
    public void init() {
        super.init();
    }

    public void login(String account, String password) {
       /* AppApplication.appFutureImpl.login(account, password, tag, new FutureListener() {
            @Override
            public void onStart(FutureResult request) {
                super.onStart(request);
                getView().showLoading();
            }

            @Override
            public void onSuccess(UserResponse response, Call request) {
                super.onSuccess(response, request);
                UserBean user = response.getData();
                getView().onLoginSuccess(user);
                getView().hideLoading();
            }

            @Override
            public void onFailure(Call request, Exception e) {
                super.onFailure(request, e);
                getView().onLoginFailure(e.getMessage());
                getView().hideLoading();
            }
        });*/
        AppApplication.appFutureImpl.login(account, password, tag, new FutureListener() {

            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);

            }

            @Override
            public void onFailure(FutureResult result) {
                super.onFailure(result);
                Exception ex = result.getException();
                if(null != ex){
                    getView().onLoginFailure(ex.getMessage());
                }
                getView().hideLoading();
            }
        });
    }
}
