package com.yuplus.cloudsdk.log;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public interface LogAdapter {
    void d(String tag, String message);

    void e(String tag, String message);

    void w(String tag, String message);

    void i(String tag, String message);

    void v(String tag, String message);

    void wtf(String tag, String message);
}
