package com.yuplus.cloudsdk.log;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 日志参数配置(参考Logger修改)
 */

public class LogSettings {
    private int     methodCount    = 2;
    private boolean showThreadInfo = true;
    private int     methodOffset   = 0;
    private LogAdapter logAdapter;

    /**
     * Determines to how logs will be printed
     */
    private LogLevel logLevel = LogLevel.FULL;

    public LogSettings hideThreadInfo() {
        showThreadInfo = false;
        return this;
    }

    public LogSettings methodCount(int methodCount) {
        if (methodCount < 0) {
            methodCount = 0;
        }
        this.methodCount = methodCount;
        return this;
    }

    public LogSettings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public LogSettings methodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public LogSettings logAdapter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
        return this;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

    public LogAdapter getLogAdapter() {
        if (logAdapter == null) {
            logAdapter = new AndroidLogAdapter();
        }
        return logAdapter;
    }

    public void reset() {
        methodCount = 2;
        methodOffset = 0;
        showThreadInfo = true;
        logLevel = LogLevel.FULL;
    }
}
