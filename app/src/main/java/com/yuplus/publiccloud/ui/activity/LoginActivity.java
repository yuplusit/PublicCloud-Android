package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.LoginPresenter;
import com.yuplus.publiccloud.mvp.view.LoginView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.widget.ClearableEditText;
import com.yuplus.publiccloud.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class LoginActivity extends TitleActivity implements LoginView {

    @BindView(R.id.login_id_account)
    ClearableEditText mAccountEt;
    @BindView(R.id.login_id_password)
    ClearableEditText mPasswordEt;

    private LoginPresenter mLoginPresenter;

    private ProgressHUBDialog mLoadingView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.setView(this);
        mLoginPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("用户登录");
        setShowHomeBack();
    }

    @OnClick(R.id.login_id_btn)
    public void onConfirmClicked() {
        final String account = mAccountEt.getText().toString().trim();
        final String password = mPasswordEt.getText().toString().trim();
        if (onCheck(account, password)) {
            mLoginPresenter.login(account, password);
        }
    }

    public boolean onCheck(final String account, final String password) {
        if (StringUtils.isBlank(account)) {
            ToastUtils.showToast(this, getString(R.string.login_account_empty));
            mAccountEt.requestFocus();
            return false;
        }
        if (StringUtils.isBlank(password)) {
            ToastUtils.showToast(this, getString(R.string.login_password_empty));
            mPasswordEt.requestFocus();
            return false;
        }
        if (password.length() <= 6) {
            ToastUtils.showToast(this, getString(R.string.login_password_error));
            mPasswordEt.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onLoginSuccess(UserBean user) {
        DispatchManager.startMainActivity(this);
    }

    @Override
    public void onLoginFailure(String msg) {

    }


    @Override
    public void showLoading() {
        if (null == mLoadingView) {
            mLoadingView = ProgressHUBDialog.createDialog(this, "正在登录中...");
        }
        mLoadingView.show();
    }

    @Override
    public void hideLoading() {
        if (null != mLoadingView) {
            mLoadingView.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
