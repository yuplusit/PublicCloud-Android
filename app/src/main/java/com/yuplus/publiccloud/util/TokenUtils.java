package com.yuplus.publiccloud.util;

import android.content.Context;

import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.ui.DispatchManager;

/**
 * Created by zlzsa on 2017/6/4.
 */

public class TokenUtils {
    public static boolean checkUserState(Context context){
        if(null == AppApplication.user){
            DispatchManager.startLoginActivity(context);
            return false;
        }
        return true;
    }
}
