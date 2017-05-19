package com.yuplus.cloudsdk.cst;

import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.industry.publiccloudsdk.BuildConfig;

/**
 * @user longzhen
 * @date 5/10/2017
 * @desc
 */

public class ConfigCst {
    private static SDKConfiguration mConfiguration;

    static {
        mConfiguration = CloudSDKManager.getInstance().getSdkConfiguration();
    }

    //框架名
    public static String  SDK_NAME = BuildConfig.SDK_NAME;
    //框架是否处于调试模式
    public static boolean DEBUG    = BuildConfig.DEBUG;
    //框架调试标记
    public static String  TAG      = StringUtils.isBlank(mConfiguration.getAppName()) ? SDK_NAME : mConfiguration.getAppName();
}
