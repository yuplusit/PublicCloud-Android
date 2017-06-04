package com.yuplus.publiccloud;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.future.impl.AppFutureImpl;
import com.yuplus.cloudsdk.okhttp.HttpsCerManager;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.cookie.jar.ClearableCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.PersistentCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.cache.SetCookieCache;
import com.yuplus.cloudsdk.okhttp.cookie.jar.persistence.SharedPrefsCookiePersistor;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.sp.PublicCloudPreferences;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import skin.support.SkinCompatManager;
import skin.support.design.app.SkinMaterialViewInflater;


/**
 * @user longzhen
 * @date 5/11/2017
 * @desc
 */

public class AppApplication extends Application {
    public static AppFutureImpl          appFutureImpl;
    public static AppApplication         application;
    public static UserBean               user;
    public static PublicCloudPreferences prefer;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.init(this)                          // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())  // material design 控件换肤初始化[可选]
                .loadSkin();
        application = this;
        appFutureImpl = new AppFutureImpl();
        prefer = PublicCloudPreferences.getInstance(this);
        HttpsCerManager.SSLParams sslParams = HttpsCerManager.getSslSocketFactory(null, null, null);

        //CookieJarImpl cookieJar1 = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.getInstance(okHttpClient);
        SDKConfiguration configuration = new SDKConfiguration.Builder()
                .setAppName(AppCst.APP_NAME)
                .setHttpHost(AppCst.SERVER_HOST)
                .setHttpPort(AppCst.SERVER_PORT)
                .setBasePath(AppCst.SERVER_BASE_PATH)
                .build();
        CloudSDKManager.getInstance()
                .setApplication(this)
                .initLogUtil(AppCst.APP_NAME)
                .setSdkConfiguration(configuration);
    }

    public static AppApplication getInstance() {
        return application;
    }
}
