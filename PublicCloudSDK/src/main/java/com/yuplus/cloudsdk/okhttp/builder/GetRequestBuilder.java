package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.cst.CharsetCst;
import com.yuplus.cloudsdk.okhttp.request.GetRequest;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class GetRequestBuilder extends RequestBuilder {
    @Override
    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public RequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String value) {
        if (null == this.params) {
            params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return this;
    }

    @Override
    public RequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public RequestBuilder addHeader(String key, String value) {
        if (null == this.headers) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
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
        if (null != params) {
            url = appendParams(url, params);
        }
        return new GetRequest(url, tag, params, headers).build();
    }
}
