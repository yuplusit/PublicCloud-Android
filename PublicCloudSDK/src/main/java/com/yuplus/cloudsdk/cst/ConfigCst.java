package com.yuplus.cloudsdk.cst;

import com.yuplus.cloudsdk.BuildConfig;
import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.log.LogUtils;
import com.yuplus.cloudsdk.util.StringUtils;


/**
 * @user longzhen
 * @date 5/10/2017
 * @desc
 */

public class ConfigCst {
    private static SDKConfiguration mSdkConfiguration;

    static {
        mSdkConfiguration = CloudSDKManager.getInstance().getSdkConfiguration();
        if (null == mSdkConfiguration) {
            RuntimeException exception = new RuntimeException("the sDKConfiguration is null when use the apiCst class,so you need set the sDKConfiguration.");
            LogUtils.e(exception, "the sDKConfiguration is null when use the apiCst class,so you need set the sDKConfiguration.");
            throw exception;
        }
    }

    //框架名
    public static String  SDK_NAME = BuildConfig.SDK_NAME;
    //框架是否处于调试模式
    public static boolean DEBUG    = BuildConfig.DEBUG;
    //框架调试标记
    public static String  TAG      = StringUtils.isBlank(mSdkConfiguration.getAppName()) ? SDK_NAME : mSdkConfiguration.getAppName();
}
