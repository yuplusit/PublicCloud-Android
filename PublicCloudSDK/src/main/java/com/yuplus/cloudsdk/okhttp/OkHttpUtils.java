package com.yuplus.cloudsdk.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.okhttp.builder.GetRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.OtherRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.PostFormRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.PostStringRequestBuilder;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * @user longzhen
 * @date 5/11/2017
 * @desc 网络请求工具类
 */

public class OkHttpUtils {
    private String           mTag;
    private boolean          mDebug;
    private static String TAG = "PublicCloudSDK Request";

    private                 OkHttpClient mOkHttpClient;
    private                 Platform     mPlatform;
    private static          Handler      mHandler;
    private volatile static OkHttpUtils  mInstance;

    public static OkHttpUtils getInstance() {
        return getInstance(null);
    }

    public static OkHttpUtils getInstance(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
        mPlatform = Platform.get();
    }

    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    public static PostStringRequestBuilder postString() {
        return new PostStringRequestBuilder();
    }

    public static PostFormRequestBuilder postFile() {
        return new PostFormRequestBuilder();
    }

    public static PostFormRequestBuilder post() {
        return new PostFormRequestBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(HttpCst.Method.PUT);
    }

    public static OtherRequestBuilder head() {
        return new OtherRequestBuilder(HttpCst.Method.HEAD);
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(HttpCst.Method.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(HttpCst.Method.PATCH);
    }

    public Handler getHandler() {
        return mHandler;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public OkHttpUtils debug(String tag) {
        mDebug = true;
        this.mTag = tag;
        return this;
    }

    /**
     * 取消特定的网络请求
     *
     * @param tag 一般指的是当前Activity，取消当前activity内的所有网络请求
     */
    public void cancel(Object tag) {
        Dispatcher dispatcher = mOkHttpClient.dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}