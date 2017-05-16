package com.yuplus.cloudsdk.base;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc 请求参数
 */

public abstract class BaseParams<T extends Object> {
    protected T params;

    public BaseParams() {
    }

    public T getParams() {
        return params;
    }

    public abstract String toJson();
}
