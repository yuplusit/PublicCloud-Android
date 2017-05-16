package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.okhttp.request.PostFileRequest;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class PostFileRequestBuilder extends RequestBuilder {
    private File      file;
    private MediaType mediaType;

    public RequestBuilder file(File file) {
        this.file = file;
        return this;
    }

    public RequestBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFileRequest(url, tag, params, headers, file, mediaType).build();
    }

    @Override
    public PostFileRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PostFileRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFileRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostFileRequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFileRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public PostFileRequestBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
