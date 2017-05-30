package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.ImageView;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.LoginPresenter;
import com.yuplus.publiccloud.mvp.view.LoginView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.BaseActivity;
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

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.login_id_account)
    ClearableEditText mAccountEt;
    @BindView(R.id.login_id_password)
    ClearableEditText mPasswordEt;
    @BindView(R.id.login_id_password_show)
    ImageView         mShowPasswordIv;

    private LoginPresenter mLoginPresenter;

    private boolean isShowPassword;
    private long startTime = 0L;

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
    }

    @OnClick(R.id.login_id_btn)
    public void onConfirmClicked() {
        final String account = mAccountEt.getText().toString().trim();
        final String password = mPasswordEt.getText().toString().trim();
        if (onCheck(account, password)) {
            mLoginPresenter.login(account, password);
        }
    }

    @OnClick(R.id.login_id_password_show)
    public void onShowPwdClicked() {
        isShowPassword = !isShowPassword;
        if (isShowPassword) {
            mShowPasswordIv.setSelected(true);
            mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            mShowPasswordIv.setSelected(false);
            mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        mPasswordEt.setSelection(StringUtils.isEmpty(mPasswordEt.getText().toString()) ? 0 : mPasswordEt.length());
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
        if (password.length() < 6) {
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
        if(StringUtils.isNotBlank(msg)){
            ToastUtils.showToast(this, msg);
        }
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
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        } else {
            if ((currentTime - startTime) >= 2000) {
                ToastUtils.make(R.string.common_exit_tip);
                startTime = currentTime;
            } else {
                finish();
                System.exit(0);
            }
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
