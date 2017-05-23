package com.yuplus.cloudsdk.future.listener;


import com.yuplus.cloudsdk.future.FutureResult;

/**
 * @user zlzsam
 * @date 2016/10/9
 * @desc 任务状态回调callback类
 */

public abstract class FutureListener implements IFutureListener {

    /**
     * 任务开始
     */
    @Override
    public void onStart() {

    }

    /**
     * 任务进度
     *
     * @param progress 进度
     */
    @Override
    public void onProgress(float progress) {

    }

    /**
     * 任务成功
     *
     * @param result
     */
    @Override
    public void onSuccess(FutureResult result) {
    }

    /**
     * 任务失败返回的状态码和消息信息
     *
     * @param result
     */
    @Override
    public void onFailure(FutureResult result) {
    }
}
