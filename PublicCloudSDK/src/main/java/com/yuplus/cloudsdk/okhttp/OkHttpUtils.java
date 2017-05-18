package com.yuplus.cloudsdk.okhttp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.okhttp.builder.GetRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.OtherRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.PostFormRequestBuilder;
import com.yuplus.cloudsdk.okhttp.builder.PostStringRequestBuilder;
import com.yuplus.cloudsdk.okhttp.callback.BaseCallback;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * @user longzhen
 * @date 5/11/2017
 * @desc 网络请求工具类
 */

public class OkHttpUtils {
    private String           mTag;
    private boolean          mDebug;
    private Application      mApplication;
    private SDKConfiguration mSdkConfiguration;
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

    public OkHttpUtils setSdkConfiguration(SDKConfiguration sdkConfiguration) {
        this.mSdkConfiguration = sdkConfiguration;
        return this;
    }

    public SDKConfiguration getSdkConfiguration() {
        return mSdkConfiguration;
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

    public void sendFailResultCallback(final Call call, final Exception e, final BaseCallback callback) {
        if (callback == null) return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(call, e);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Call call, final BaseCallback callback) {
        if (callback == null) return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(object, call);
            }
        });
    }

    public void execute(final RequestCall requestCall, BaseCallback callback) {
        if (mDebug) {
            if (TextUtils.isEmpty(mTag)) {
                mTag = TAG;
            }
            Log.d(mTag, "{method:" + requestCall.getRequest().method() + ", detail:" + requestCall.getOkHttpRequest().toString() + "}");
        }

        final BaseCallback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (response.isSuccessful()) {
                        sendSuccessResultCallback(finalCallback.parseResponse(response), call, finalCallback);
                    } else {
                        sendFailResultCallback(call, new RuntimeException(response.body().string()), finalCallback);
                    }
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback);
                }
            }
        });
    }

    public Application getApplication() {
        return mApplication;
    }

    public OkHttpUtils setApplication(Application application) {
        this.mApplication = application;
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