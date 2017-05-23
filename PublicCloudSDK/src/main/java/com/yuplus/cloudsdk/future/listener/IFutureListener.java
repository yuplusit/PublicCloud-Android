package com.yuplus.cloudsdk.future.listener;


import com.yuplus.cloudsdk.future.FutureResult;

/**
 * @user zlzsam
 * @date 2016/10/13
 * @desc 回调接口
 */

public interface IFutureListener {

    /**
     * 任务开始
     */
    void onStart();

    /**
     * 任务进度
     *
     * @param progress 进度按照百分比显示
     */
    void onProgress(float progress);

    /**
     * 任务成功
     *
     * @param result
     */
    void onSuccess(FutureResult result);

    /**
     * 任务失败返回的状态码和消息信息
     *
     * @param result
     */
    void onFailure(FutureResult result);
}
