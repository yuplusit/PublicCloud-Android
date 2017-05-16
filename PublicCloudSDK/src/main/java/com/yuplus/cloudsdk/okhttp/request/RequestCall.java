package com.yuplus.cloudsdk.okhttp.request;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.callback.BaseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class RequestCall {
    private BaseRequest baseRequest;
    private Request     request;
    private Call        call;

    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

    private OkHttpClient clone;

    public RequestCall(BaseRequest request) {
        this.baseRequest = request;
    }

    public RequestCall readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connTimeOut(long connTimeOut) {
        this.connTimeOut = connTimeOut;
        return this;
    }

    public Call getCall() {
        return call;
    }

    public Request getRequest() {
        return request;
    }

    public BaseRequest getOkHttpRequest() {
        return baseRequest;
    }

    public Call generateCall(BaseCallback callback) {
        request = generateRequest(callback);

        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : HttpCst.READ_TIMEOUT;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : HttpCst.WRITE_TIMEOUT;
            connTimeOut = connTimeOut > 0 ? connTimeOut : HttpCst.CONNECTION_TIMEOUT;

            clone = OkHttpUtils.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS)
                    .build();

            call = clone.newCall(request);
        } else {
            call = OkHttpUtils.getInstance().getOkHttpClient().newCall(request);
        }
        return call;
    }

    private Request generateRequest(BaseCallback callback) {
        return baseRequest.generateRequest(callback);
    }

    public Response execute() throws IOException {
        generateCall(null);
        return call.execute();
    }

    // 异步请求回调
    public void execute(BaseCallback callback) {
        generateCall(callback);
        if (callback != null) {
            callback.onStart(request);
        }
        OkHttpUtils.getInstance().execute(this, callback);
    }

    // 同步解析成对象
    public <T> T execute(Class<T> clazz) throws IOException {
        generateCall(null);
        String response = call.execute().body().string();
        return JSON.parseObject(response, clazz);
    }

    // 同步解析成对象
    public <T> T execute(String parseWhat, Class<T> clazz) throws IOException, JSONException {
        generateCall(null);
        String response = call.execute().body().string();
        if (!TextUtils.isEmpty(parseWhat)) {
            JSONObject jsonObject = new JSONObject(response);
            return JSON.parseObject(jsonObject.getString(parseWhat), clazz);
        }
        return JSON.parseObject(response, clazz);
    }

    // 同步解析成列表
    public <T> ArrayList<T> executeForList(String parseWhat, Class<T> clazz) throws IOException, JSONException {
        generateCall(null);
        String response = call.execute().body().string();
        if (!TextUtils.isEmpty(parseWhat)) {
            JSONObject jsonObject = new JSONObject(response);
            return new ArrayList<T>(JSON.parseArray(jsonObject.getString(parseWhat), clazz));
        }
        return new ArrayList<T>(JSON.parseArray(response, clazz));
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
