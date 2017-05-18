package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.publiccloud.mvp.base.IView;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public interface LoginView extends IView {
    void onLoginSuccess(final UserBean user);

    void onLoginFailure(final String msg);
}
