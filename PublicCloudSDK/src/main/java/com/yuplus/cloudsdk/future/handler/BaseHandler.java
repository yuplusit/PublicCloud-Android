package com.yuplus.cloudsdk.future.handler;

import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/23/2017
 * @desc
 */

public abstract class BaseHandler {
    private FutureListener listener;

    public abstract Class<?> getClazz();

    public abstract void onResponse(final Call call, final Response response);

    public abstract void onFailure(Call call, final Exception e);

    protected void commitComplete(final FutureResult result) {
        OkHttpUtils.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != listener) {
                    listener.onSuccess(result);
                }
            }
        });
    }

    protected void commitException(final FutureResult result) {
        OkHttpUtils.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != listener) {
                    listener.onFailure(result);
                }
            }
        });
    }

    public void setFutureListener(FutureListener listener) {
        this.listener = listener;
    }
}
