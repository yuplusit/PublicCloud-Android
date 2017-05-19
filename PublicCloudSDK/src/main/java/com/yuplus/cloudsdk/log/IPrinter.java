package com.yuplus.cloudsdk.log;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public interface IPrinter {
    IPrinter t(String tag, int methodCount);

    LogSettings init(String tag);

    LogSettings getLogSettings();

    void d(String message, Object... args);

    void d(Object object);

    void e(String message, Object... args);

    void e(Throwable throwable, String message, Object... args);

    void w(String message, Object... args);

    void i(String message, Object... args);

    void v(String message, Object... args);

    void wtf(String message, Object... args);

    void json(String json);

    void xml(String xml);

    void log(int priority, String tag, String message, Throwable throwable);

    void resetSettings();
}
