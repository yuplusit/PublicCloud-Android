package com.yuplus.cloudsdk.base;

/**
 * @user longzhen
 * @date 2017/1/4
 * @desc 基类:网络请求响应数据对象
 */

public abstract class BaseResponse<T> extends BaseBean {

    // 错误码
    protected int    code;
    // 响应数据
    protected T      data;
    // 错误消息
    protected String message;
    // 异常信息
    protected String stackTrace;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
