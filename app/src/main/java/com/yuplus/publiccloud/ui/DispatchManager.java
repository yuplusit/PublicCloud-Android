package com.yuplus.publiccloud.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.activity.CustomerDetailActivity;
import com.yuplus.publiccloud.ui.activity.LoginActivity;
import com.yuplus.publiccloud.ui.activity.MainActivity;

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
}
