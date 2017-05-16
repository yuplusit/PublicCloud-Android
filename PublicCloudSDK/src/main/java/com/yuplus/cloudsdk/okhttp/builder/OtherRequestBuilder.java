package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.cst.CharsetCst;
import com.yuplus.cloudsdk.cst.HttpCst;
import com.yuplus.cloudsdk.okhttp.request.OtherRequest;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class OtherRequestBuilder extends RequestBuilder {

    private RequestBody requestBody;
    private String      method;
    private String      content;

    public OtherRequestBuilder(String method) {
        super();
        this.method = method;
    }

    public OtherRequestBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public OtherRequestBuilder requestBody(String content) {
        this.content = content;
        return this;
    }

    @Override
    public OtherRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public OtherRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public OtherRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public OtherRequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public OtherRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public OtherRequestBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder encodedParams = new StringBuilder();
        encodedParams.append(url);
        if (null != params && !params.isEmpty()) {
            final String paramsEncoding = CharsetCst.UTF_8;

            try {
                Iterator<Map.Entry<String, String>> ite = params.entrySet().iterator();
                while (ite.hasNext()) {
                    encodedParams.append('?');
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
                    encodedParams.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                    if (ite.hasNext()) {
                        encodedParams.append('&');
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Encoding not supported:" + paramsEncoding, e);
            }
        }
        return encodedParams.toString();
    }

    @Override
    public RequestCall build() {
        if (params != null && method.equals(HttpCst.Method.DELETE)) {
            url = appendParams(url, params);
        }

        return new OtherRequest(requestBody, content, method, url, tag, params, headers).build();
    }
}
