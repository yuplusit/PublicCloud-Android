package com.yuplus.cloudsdk.future;

import okhttp3.Call;

/**
 * Created by zlzsam on 2016/6/1.
 */
public class FutureResult {
    private int       code;
    private boolean   success;
    private Object    attach;
    private String    jsonData;
    private Call      call;
    private Exception exception;

    public FutureResult() {
        code = -1;
    }

    public int getCode() {
        return code;
    }

    public FutureResult setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 任务是否完成
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 任务是否完成
     *
     * @return
     */
    public FutureResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置Handler提交的附件
     *
     * @param attach
     */
    public FutureResult setAttach(Object attach) {
        this.attach = attach;
        return this;
    }

    /**
     * 获取Handler提交的附件
     *
     * @return
     */
    public Object getAttach() {
        return this.attach;
    }

    /**
     * 获取Call对象
     *
     * @return
     */
    public Call getCall() {
        return call;
    }

    /**
     * 请求Call对象
     *
     * @param call
     * @return
     */
    public FutureResult setCall(Call call) {
        this.call = call;
        return this;
    }

    /**
     * 设置异常执行的异常，
     *
     * @param ex
     */
    public FutureResult setException(Exception ex) {
        this.exception = ex;
        return this;
    }

    /**
     * 获取执行的异常
     *
     * @return
     */
    public Exception getException() {
        return this.exception;
    }

    /**
     * 获取请求返回的json数据
     *
     * @return
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * 保存请求返回的json数据
     *
     * @return
     */
    public FutureResult setJsonData(String jsonData) {
        this.jsonData = jsonData;
        return this;
    }
}
