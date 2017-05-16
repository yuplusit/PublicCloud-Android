package com.yuplus.cloudsdk.okhttp.request;

import com.yuplus.cloudsdk.okhttp.callback.BaseCallback;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public abstract class BaseRequest {
    protected String              url;
    protected Object              tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    protected Request.Builder builder = new Request.Builder();

    protected BaseRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        if (null == url) {
            throw new RuntimeException("request url should not be null !");
        }
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
    }

    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseCallback callback) {
        return requestBody;
    }

    public RequestCall build() {
        return new RequestCall(this);
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public Request generateRequest(BaseCallback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }

    private void prepareBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    @Override
    public String toString() {
        return "OkHttpRequest{" +
                "url='" + url + '\'' +
                ", tag=" + tag +
                ", params=" + params +
                ", headers=" + headers +
                '}';
    }
}
