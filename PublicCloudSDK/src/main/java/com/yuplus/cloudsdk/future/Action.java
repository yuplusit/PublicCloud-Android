package com.yuplus.cloudsdk.future;

import android.app.Application;

import com.yuplus.cloudsdk.okhttp.OkHttpUtils;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class Action {
    public static String PACKAGE_NAME;

    static {
        Application application = OkHttpUtils.getInstance().getApplication();
        if (null == application) {
            throw new RuntimeException("the context is null when use the action,so you need set the context.");
        }
        PACKAGE_NAME = application.getPackageName();
    }

    public static final String LOGIN_AGAIN_ACTION = PACKAGE_NAME + ".LOGIN_AGAIN_ACTION";
}
