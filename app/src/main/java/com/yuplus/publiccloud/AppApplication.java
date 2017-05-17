package com.yuplus.publiccloud;

import android.app.Application;

import com.yuplus.cloudsdk.okhttp.HttpsCerManager;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.cookie.jar.ClearableCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.PersistentCookieJar;
import com.yuplus.cloudsdk.okhttp.cookie.jar.cache.SetCookieCache;
import com.yuplus.cloudsdk.okhttp.cookie.jar.persistence.SharedPrefsCookiePersistor;

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

    @Override
    public void onCreate() {
        super.onCreate();

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
        OkHttpUtils.initClient(okHttpClient);
    }
}
