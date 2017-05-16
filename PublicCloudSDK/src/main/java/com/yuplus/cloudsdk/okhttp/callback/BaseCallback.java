package com.yuplus.cloudsdk.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class BaseCallback<T> {

    public void onStart(Request request) {}

    public void onProgress(float progress) {}

    public abstract T parseResponse(Response response) throws Exception;

    public abstract void onSuccess(T response,Call request);

    public abstract void onFailure(Call request, Exception e);
}
