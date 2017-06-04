package com.yuplus.cloudsdk;

import android.app.Application;

import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.log.AndroidLogAdapter;
import com.yuplus.cloudsdk.log.LogUtils;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class CloudSDKManager {

    private Application mApplication;
    private SDKConfiguration mSdkConfiguration;
    private volatile static CloudSDKManager mInstance;
    private String jsessionId;

    public static CloudSDKManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new CloudSDKManager();
                }
            }
        }
        return mInstance;
    }

    public Application getApplication() {
        return mApplication;
    }

    public CloudSDKManager setApplication(Application application) {
        this.mApplication = application;
        return this;
    }

    public CloudSDKManager initLogUtil(String tag) {
        LogUtils.init(tag) // default PRETTYLOGGER or use just init()
                /*.methodCount(6) // default 2
                .methodOffset(5) // default 0*/
                .logAdapter(new AndroidLogAdapter());
        return this;
    }

    public SDKConfiguration getSdkConfiguration() {
        return mSdkConfiguration;
    }

    public void setSdkConfiguration(SDKConfiguration sdkConfiguration) {
        this.mSdkConfiguration = sdkConfiguration;
    }

    public String getJsessionId() {
        return jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }
}
