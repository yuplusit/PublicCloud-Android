package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.util.Map;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class RequestBuilder {
    protected String              url;
    protected Object              tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public abstract RequestBuilder url(String url);

    public abstract RequestBuilder tag(Object tag);

    public abstract RequestBuilder params(Map<String, String> params);

    public abstract RequestBuilder addParams(String key, String value);

    public abstract RequestBuilder headers(Map<String, String> headers);

    public abstract RequestBuilder addHeader(String key, String value);

    public abstract RequestCall build();
}
