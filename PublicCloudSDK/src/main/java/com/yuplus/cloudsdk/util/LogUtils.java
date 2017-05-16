package com.yuplus.cloudsdk.util;

import android.util.Log;

import com.yuplus.cloudsdk.cst.ConfigCst;


/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 日志打印工具
 */

public class LogUtils {
    /**
     * Verbose Log
     *
     * @param msg
     */
    public static void v(String msg) {
        if (ConfigCst.DEBUG) {
            Log.v(ConfigCst.TAG, StringUtils.nullToString(msg));
        }
    }

    /**
     * Debug Log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (ConfigCst.DEBUG) {
            Log.d(ConfigCst.TAG, StringUtils.nullToString(msg));
        }
    }

    /**
     * Info Log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (ConfigCst.DEBUG) {
            Log.i(ConfigCst.TAG, StringUtils.nullToString(msg));
        }
    }

    /**
     * Warn Log
     *
     * @param msg
     */
    public static void w(String msg) {
        if (ConfigCst.DEBUG) {
            Log.w(ConfigCst.TAG, StringUtils.nullToString(msg));
        }
    }

    /**
     * Error Log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (ConfigCst.DEBUG) {
            Log.e(ConfigCst.TAG, StringUtils.nullToString(msg));
        }
    }
}
