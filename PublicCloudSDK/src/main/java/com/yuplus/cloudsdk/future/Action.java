package com.yuplus.cloudsdk.future;

import android.app.Application;

import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.log.LogUtils;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class Action {
    public static String PACKAGE_NAME;

    static {
        Application application = CloudSDKManager.getInstance().getApplication();
        if (null == application) {
            RuntimeException exception = new RuntimeException("the context is null when use the action,so you need set the context.");
            LogUtils.e(exception, "the context is null when use the action,so you need set the context.");
            throw exception;
        }
        PACKAGE_NAME = application.getPackageName();
    }

    public static final String LOGIN_AGAIN_ACTION = PACKAGE_NAME + ".LOGIN_AGAIN_ACTION";
}
