package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.publiccloud.mvp.base.IView;

/**
 * @user longzhen
 * @date 6/8/2017
 * @desc
 */

public interface UserView extends IView {
    void onRenderUserData(UserBean user);

    void onModifySuccess(UserBean user);

    void onFailure(final String msg);
}
