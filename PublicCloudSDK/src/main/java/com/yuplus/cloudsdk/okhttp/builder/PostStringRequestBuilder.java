package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.okhttp.request.PostStringRequest;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class PostStringRequestBuilder extends RequestBuilder {
    private String    content;
    private MediaType mediaType;

    public PostStringRequestBuilder content(String content) {
        this.content = content;
        return this;
    }

    public PostStringRequestBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, content, mediaType).build();
    }

    @Override
    public PostStringRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PostStringRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public PostStringRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostStringRequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostStringRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public PostStringRequestBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
