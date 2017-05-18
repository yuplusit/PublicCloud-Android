package com.yuplus.publiccloud.mvp.presenter;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.future.data.response.UserResponse;
import com.yuplus.cloudsdk.okhttp.callback.JsonCallback;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.mvp.base.BasePresenter;
import com.yuplus.publiccloud.mvp.view.LoginView;

import okhttp3.Call;
import okhttp3.Request;

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
        AppApplication.appFutureImpl.login(account, password, tag, new JsonCallback<UserResponse>() {
            @Override
            public void onStart(Request request) {
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
        });
    }
}
