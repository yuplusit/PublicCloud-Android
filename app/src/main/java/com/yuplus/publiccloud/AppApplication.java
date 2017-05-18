package com.yuplus.publiccloud;

import android.app.Application;

import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.future.http.impl.AppFutureImpl;
import com.yuplus.cloudsdk.okhttp.HttpsCerManager;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.cookie.jar.ClearableCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.PersistentCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.cache.SetCookieCache;
import com.yuplus.cloudsdk.okhttp.cookie.jar.persistence.SharedPrefsCookiePersistor;
import com.yuplus.publiccloud.cst.AppCst;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


/**
 * @user longzhen
 * @date 5/11/2017
 * @desc
 */

public class AppApplication extends Application {
    public static AppFutureImpl  appFutureImpl;
    public static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appFutureImpl = new AppFutureImpl();
        HttpsCerManager.SSLParams sslParams = HttpsCerManager.getSslSocketFactory(null, null, null);

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

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
        SDKConfiguration configuration = new SDKConfiguration.Builder()
                .setAppName(AppCst.APP_NAME)
                .setReadTimeout(10000L)
                .setWriteTimeout(10000L)
                .setHttpHost(AppCst.SERVER_HOST)
                .setHttpPort(AppCst.SERVER_PORT)
                .setBasePath(AppCst.SERVER_BASE_PATH)
                .build();
        OkHttpUtils.getInstance(okHttpClient)
                .setApplication(this)
                .setSdkConfiguration(configuration);
    }

    public static AppApplication getInstance() {
        return application;
    }
}
