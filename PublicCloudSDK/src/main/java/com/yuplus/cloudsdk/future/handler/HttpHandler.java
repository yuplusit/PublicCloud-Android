package com.yuplus.cloudsdk.future.handler;

import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.future.Action;
import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.HttpStatus;
import com.yuplus.cloudsdk.log.LogUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @user longzhen
 * @date 5/23/2017
 * @desc
 */

public class HttpHandler<T> extends BaseHandler {

    @Override
    public Class<?> getClazz() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        return clazz;
    }

    @Override
    public void onResponse(Call call, Response response) {
        getCookiesValue(response);
        try {
            final String str = response.body().string();
            LogUtils.t(HttpCst.TAG).d(str);
            JSONObject jsonObject = JSON.parseObject(str);
            final int code = jsonObject.getInteger("code");
            if (code == HttpStatus.SUCCESS) {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, getClazz());
                onHandleSuccess(baseResponse.getCode(), true, baseResponse.getData(), str, call);
            } else if (code == HttpStatus.LOGIN_AGAIN) {
                sendBraodCast(Action.LOGIN_AGAIN_ACTION);
                onHandleFailure(true, str, call, new RuntimeException(HttpCst.ExceptionMsg.LOGIN_AGAIN));
            } else {
                onHandleFailure(true, str, call, new RuntimeException(String.format(HttpCst.ExceptionMsg.FAILURE_CODE_MSG,
                        code, jsonObject.getString("message"))));
            }
        } catch (IOException ex) {
            onHandleFailure(true, null, call, ex);
        }
    }

    @Override
    public void onFailure(Call call, Exception ex) {
        onHandleFailure(true, null, call, ex);
    }

    protected void onHandleSuccess(int code, boolean success, Object data, String responseStr, Call call) {
        FutureResult result = new FutureResult()
                .setCode(code)
                .setSuccess(success)
                .setAttach(data)
                .setJsonData(responseStr)
                .setCall(call);
        commitComplete(result);
    }

    protected void onHandleFailure(boolean success, String responseStr, Call call, Exception ex) {
        FutureResult result = new FutureResult()
                .setSuccess(success)
                .setJsonData(responseStr)
                .setCall(call)
                .setException(ex);
        commitException(result);
    }

    private void sendBraodCast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        CloudSDKManager.getInstance().getApplication().sendBroadcast(intent);
    }

    private void getCookiesValue(Response response) {
        if (response.isSuccessful()) {
            Headers headers = response.headers();
            List<String> cookies = headers.values("Set-Cookie");
            for (String str : cookies) {
                if (str.startsWith("PSIOT_JSESSIONID")) {
                    CloudSDKManager.getInstance().setJsessionId(str.substring(0, str.indexOf(";")));
                }
            }
        }
    }
}
