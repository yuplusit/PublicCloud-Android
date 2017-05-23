package com.yuplus.cloudsdk.okhttp.callback;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class JsonCallback<T> extends BaseCallback<T> {

    @Override
    public void onStart(Request request) {
        super.onStart(request);
    }

    @Override
    public void onProgress(float progress) {
        super.onProgress(progress);
    }

    @Override
    public T parseResponse(Response response) throws Exception {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        T obj = JSON.parseObject(response.body().string(), clazz);
        return obj;
    }

    @Override
    public void onSuccess(T response, Call request) {
    }

    @Override
    public void onFailure(Call request, Exception e) {

    }
}
