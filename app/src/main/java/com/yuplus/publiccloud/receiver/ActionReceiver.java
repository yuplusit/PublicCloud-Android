package com.yuplus.publiccloud.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yuplus.cloudsdk.future.Action;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.util.ToastUtils;

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
            ToastUtils.make(R.string.login_again);
            DispatchManager.startLoginActivity(context);
        }
    }
}
