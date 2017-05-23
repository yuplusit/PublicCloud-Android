package com.yuplus.cloudsdk.okhttp.request;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.future.handler.BaseHandler;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.cloudsdk.log.LogUtils;
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

    private Class<? extends BaseHandler> handlerCls;

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

    public RequestCall handlerCls(Class<? extends BaseHandler> handler) {
        this.handlerCls = handler;
        return this;
    }

    public Class<? extends BaseHandler> getHandlerCls() {
        return handlerCls;
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
        execute(this, callback);
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

    public void sendFailResultCallback(final Call call, final Exception e, final BaseCallback callback) {
        if (callback == null) return;
        OkHttpUtils.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(call, e);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Call call, final BaseCallback callback) {
        if (callback == null) return;
        OkHttpUtils.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(object, call);
            }
        });
    }

    //普通网络请求
    public void execute(final RequestCall requestCall, BaseCallback callback) {
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

    //含有网络请求后有业务处理逻辑处理
    public void execute(FutureListener listener) throws IllegalAccessException, InstantiationException {
        final Class classType = getHandlerCls();
        if (null == classType) {
            LogUtils.e("the response handler is null");
            throw new RuntimeException("the response handler is null");
        }
        final BaseHandler handler = (BaseHandler) classType.newInstance();
        generateCall(null);
        if (null != listener) {
            handler.setFutureListener(listener);
            listener.onStart();
        }
        this.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.onFailure(call, e);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (response.isSuccessful()) {
                        handler.onResponse(call, response);
                    } else {
                        handler.onFailure(call, new RuntimeException(response.body().string()));
                    }
                } catch (Exception e) {
                    handler.onFailure(call, e);
                }
            }
        });
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
