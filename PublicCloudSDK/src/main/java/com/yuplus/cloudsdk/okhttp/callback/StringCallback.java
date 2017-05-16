package com.yuplus.cloudsdk.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class StringCallback extends BaseCallback<String> {

    @Override
    public void onStart(Request request) {
        super.onStart(request);
    }

    @Override
    public void onProgress(float progress) {
        super.onProgress(progress);
    }

    @Override
    public String parseResponse(Response response) throws Exception {
        return response.body().string();
    }

    @Override
    public void onSuccess(String response,Call request) {

    }

    @Override
    public void onFailure(Call request, Exception e) {

    }
}
