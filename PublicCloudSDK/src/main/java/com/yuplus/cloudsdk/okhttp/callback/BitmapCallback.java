package com.yuplus.cloudsdk.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.log.LogUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class BitmapCallback extends BaseCallback<Bitmap> {

    @Override
    public void onStart(Request request) {
        super.onStart(request);
    }

    @Override
    public void onProgress(float progress) {
        super.onProgress(progress);
    }

    @Override
    public Bitmap parseResponse(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

    @Override
    public void onSuccess(Bitmap response,Call request) {
        LogUtils.t(HttpCst.TAG).d(response);
    }

    @Override
    public void onFailure(Call request, Exception e) {

    }
}
