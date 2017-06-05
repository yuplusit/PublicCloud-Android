package com.yuplus.publiccloud.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yuplus.cloudsdk.future.Action;
import com.yuplus.publiccloud.ui.DispatchManager;

/**
 * @user longzhen
 * @date 6/5/2017
 * @desc
 */

public class ActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (Action.LOGIN_AGAIN_ACTION.equalsIgnoreCase(action)) {
            DispatchManager.startLoginActivity(context);
        }
    }
}
