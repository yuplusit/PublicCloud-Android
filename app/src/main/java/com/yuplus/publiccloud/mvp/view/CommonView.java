package com.yuplus.publiccloud.mvp.view;

import com.yuplus.publiccloud.mvp.base.IView;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public interface CommonView extends IView {
    void onSuccess(final Object object);

    void onFailure(final String msg);
}
