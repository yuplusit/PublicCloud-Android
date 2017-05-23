package com.yuplus.publiccloud.ui;

import android.content.Context;
import android.content.Intent;

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

}
