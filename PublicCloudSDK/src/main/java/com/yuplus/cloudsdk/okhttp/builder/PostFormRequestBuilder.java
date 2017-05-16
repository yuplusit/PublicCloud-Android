package com.yuplus.cloudsdk.okhttp.builder;

import com.yuplus.cloudsdk.okhttp.request.PostFormRequest;
import com.yuplus.cloudsdk.okhttp.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class PostFormRequestBuilder extends RequestBuilder {

    private List<FileInput> files = new ArrayList<>();

    public PostFormRequestBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File   file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }
    }

    @Override
    public PostFormRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PostFormRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFormRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostFormRequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFormRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public PostFormRequestBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, files).build();
    }
}
