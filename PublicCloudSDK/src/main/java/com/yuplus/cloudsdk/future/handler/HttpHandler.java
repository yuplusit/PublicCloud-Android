package com.yuplus.cloudsdk.future.handler;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.future.FutureResult;
import com.yuplus.cloudsdk.future.HttpStatus;
import com.yuplus.cloudsdk.log.LogUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
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
        try {
            final String str = response.body().string();
            LogUtils.t(HttpCst.TAG).d(str);
            BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, getClazz());
            final int code = baseResponse.getCode();
            if (code == HttpStatus.SUCCESS) {
                onHandleSuccess(baseResponse.getCode(), true, baseResponse.getData(), str, call);
            } else if (code == HttpStatus.LOGIN_AGAIN) {
                onHandleFailure(true, str, call, new RuntimeException(HttpCst.ExceptionMsg.LOGIN_AGAIN));
            } else {
                onHandleFailure(true, str, call, new RuntimeException(String.format(HttpCst.ExceptionMsg.FAILURE_CODE_MSG,
                        code, baseResponse.getMessage())));
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
}
