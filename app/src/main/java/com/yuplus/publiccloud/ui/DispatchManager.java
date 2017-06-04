package com.yuplus.publiccloud.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import com.yuplus.cloudsdk.future.data.bean.AlertBean;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.cst.BroadcastCst;
import com.yuplus.publiccloud.ui.activity.AlertDetailActivity;
import com.yuplus.publiccloud.ui.activity.CustomerAreaActivity;
import com.yuplus.publiccloud.ui.activity.CustomerDetailActivity;
import com.yuplus.publiccloud.ui.activity.DeviceDetailActivity;
import com.yuplus.publiccloud.ui.activity.DeviceHistoryAcitivity;
import com.yuplus.publiccloud.ui.activity.FeedbackActivity;
import com.yuplus.publiccloud.ui.activity.LoginActivity;
import com.yuplus.publiccloud.ui.activity.MainActivity;
import com.yuplus.publiccloud.ui.activity.QRCodeActivity;
import com.yuplus.publiccloud.ui.activity.SlideAreaActivity;
import com.yuplus.publiccloud.ui.activity.WebActivity;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class DispatchManager {
    /**
     * 跳转到主界面
     *
     * @param context
     */
    public static void startMainActivity(Context context) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到登录页
     *
     * @param context
     */
    public static void startLoginActivity(Context context) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到客户详情页
     *
     * @param context
     * @param customer
     */
    public static void startCustomerDetailActivity(Context context, CustomerBean customer) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, CustomerDetailActivity.class);
        intent.putExtra(AppCst.COMMON_DATA, (Parcelable) customer);
        context.startActivity(intent);
    }

    /**
     * 跳到设备详情页
     *
     * @param context
     * @param device
     */
    public static void startDeviceDetailActivity(Context context, DeviceBean device) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, DeviceDetailActivity.class);
        intent.putExtra(AppCst.COMMON_DATA, (Parcelable) device);
        context.startActivity(intent);
    }

    /**
     * 跳到告警详情页
     *
     * @param context
     * @param alert
     */
    public static void startAlertDetailActivity(Context context, AlertBean alert) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, AlertDetailActivity.class);
        intent.putExtra(AppCst.COMMON_DATA, (Parcelable) alert);
        context.startActivity(intent);
    }

    /**
     * 跳到project综合区
     *
     * @param context
     * @param title
     * @param projectId
     * @param domain
     */
    public static void startCustomerAreaActivity(Context context, int type, String title, long projectId, String domain) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, CustomerAreaActivity.class);
        intent.putExtra(AppCst.COMMON_DATA, type);
        intent.putExtra(AppCst.COMMON_TITLE, title);
        intent.putExtra(AppCst.COMMON_ID, projectId);
        intent.putExtra(AppCst.COMMON_KEY, domain);
        context.startActivity(intent);
    }

    /**
     * 跳转到侧边栏综合区
     *
     * @param context
     * @param title
     * @param type
     */
    public static void startSlideAreaActivity(Context context, String title, int type) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, SlideAreaActivity.class);
        intent.putExtra(AppCst.COMMON_TITLE, title);
        intent.putExtra(AppCst.COMMON_TYPE, type);
        context.startActivity(intent);
    }

    /**
     * 打开设备历史测点数据
     *
     * @param context
     * @param kpiCode
     * @param nodeId
     * @param testName
     */
    public static void startDeviceHistoryDataActivity(Context context, long kpiCode, long nodeId, String testName) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, DeviceHistoryAcitivity.class);
        intent.putExtra(AppCst.COMMON_DATA, kpiCode);
        intent.putExtra(AppCst.COMMON_ID, nodeId);
        intent.putExtra(AppCst.COMMON_CONTENT, testName);
        context.startActivity(intent);
    }

    /**
     * 跳转到意见反馈页面
     *
     * @param context
     */
    public static void startFeedbackActivity(Context context) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳到网页浏览
     *
     * @param context
     * @param title
     * @param url
     */
    public static void startWebActivity(Context context, String title, String url) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(AppCst.COMMON_TITLE, title);
        intent.putExtra(AppCst.COMMON_URL, url);
        context.startActivity(intent);
    }

    /**
     * 生成分享的二维码
     *
     * @param context
     * @param params
     */
    public static void startQRCodeActivity(Context context, String params) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent(context, QRCodeActivity.class);
        intent.putExtra(AppCst.COMMON_DATA, params);
        context.startActivity(intent);
    }


    /***
     * 消息数量的更新
     * @param context
     */
    public static void sendMessageUpdateBroadCast(Context context) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(BroadcastCst.MSG_RED_POINT_COUNT_UPDATE);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /***
     * 发送未处理的告警数量
     * @param context
     */
    public static void sendAlertUntreatedCountBroadCast(Context context, int count) {
        if (null == context) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(AppCst.COMMON_DATA, count);
        intent.setAction(BroadcastCst.UNTREATED_ALERT_COUNT_UPDATE);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
