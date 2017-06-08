package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.mvp.presenter.UserPresenter;
import com.yuplus.publiccloud.mvp.view.UserView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.widget.ClearableEditText;
import com.yuplus.publiccloud.util.ToastUtils;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class UserInfoFragment extends BaseFragment implements UserView {
    @BindView(R.id.userinfo_id_identify_layout)
    LinearLayout      mIdentifyLayout;
    @BindView(R.id.userinfo_id_identify_line)
    View              mIdentifyLine;
    @BindView(R.id.userinfo_id_identify)
    ClearableEditText mIdentifyEt;
    @BindView(R.id.userinfo_id_login_tel)
    ClearableEditText mLoginTelEt;
    @BindView(R.id.userinfo_id_email)
    ClearableEditText mEmailEt;
    @BindView(R.id.userinfo_id_login_name)
    ClearableEditText mLoginNameEt;
    @BindView(R.id.userinfo_id_show_name)
    ClearableEditText mShowNameEt;
    @BindView(R.id.userinfo_id_work_tel)
    ClearableEditText mWorkTelEt;
    @BindView(R.id.userinfo_id_confirm_tv)
    TextView          mConfirmTv;

    private UserPresenter     mUserPresenter;
    private ProgressHUBDialog mLoadingView;

    public static UserInfoFragment newInstance() {
        Bundle args = new Bundle();
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_userinfo;
    }

    @Override
    protected void initPresenter() {
        mUserPresenter = new UserPresenter();
        mUserPresenter.setView(this);
        mUserPresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mLoadingView = ProgressHUBDialog.createDialog(getActivity());
        mUserPresenter.getCurrentUser();
    }

    @Override
    protected void initView() {
        super.initView();
        mIdentifyEt.setEnabled(false);
        mLoginTelEt.setEnabled(false);
        mEmailEt.setEnabled(false);
        mLoginNameEt.setEnabled(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserBean user = AppApplication.user;
                if (null != user) {
                    final String userName = mShowNameEt.getText().toString().trim();
                    final String officeTel = mWorkTelEt.getText().toString().trim();
                    if (checkUserInfo(userName, officeTel)) {
                        mUserPresenter.modifyUserInfo(user.getUserID(), user.getLoginName(), userName, officeTel);
                    }
                }
            }
        });
    }

    private boolean checkUserInfo(String userName, String officeTel) {
        if (StringUtils.isBlank(userName)) {
            ToastUtils.make(R.string.user_info_show_name_null_tip);
            mShowNameEt.requestFocus();
            return false;
        }
        if (StringUtils.isBlank(officeTel)) {
            ToastUtils.make(R.string.user_info_office_tel_null_tip);
            mWorkTelEt.requestFocus();
            return false;
        }
        return true;
    }

    private void initUserInfo(UserBean user) {
        if (null != user) {
            if (user.getUserType() == 10) {
                mIdentifyLayout.setVisibility(View.VISIBLE);
                mIdentifyLine.setVisibility(View.VISIBLE);
            } else {
                mIdentifyLayout.setVisibility(View.GONE);
                mIdentifyLine.setVisibility(View.GONE);
            }
            if (StringUtils.isNotBlank(user.getMobilePhone())) {
                mLoginTelEt.setText(user.getMobilePhone());
            }
            if (StringUtils.isNotBlank(user.getEmailAddress())) {
                mEmailEt.setText(user.getEmailAddress());
            }
            if (StringUtils.isNotBlank(user.getLoginName())) {
                mLoginNameEt.setText(user.getLoginName());
            }
            if (StringUtils.isNotBlank(user.getUserName())) {
                mShowNameEt.setText(user.getUserName());
            }
            if (StringUtils.isNotBlank(user.getOfficePhone())) {
                mWorkTelEt.setText(user.getOfficePhone());
            }
        }
    }

    @Override
    public void onRenderUserData(UserBean user) {
        initUserInfo(user);
    }

    @Override
    public void onModifySuccess(UserBean user) {
        ToastUtils.make(R.string.user_info_modify_success);
        if (null != user && null != AppApplication.user) {
            AppApplication.user.setUserName(user.getUserName());
            AppApplication.user.setOfficePhone(user.getOfficePhone());
            DispatchManager.sendUpdateUserInfoBroadCast(getActivity());
        }
    }

    @Override
    public void onFailure(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            ToastUtils.make(msg);
        }
    }

    @Override
    public void showLoading() {
        if (null != mLoadingView && !mLoadingView.isShowing()) {
            mLoadingView.show();
        }
    }

    @Override
    public void hideLoading() {
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }

}
