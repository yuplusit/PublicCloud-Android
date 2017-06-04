package com.yuplus.publiccloud.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yuplus.cloudsdk.future.Action;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.widget.SystemBarTintManager;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public abstract class BaseActivity extends AbsActivity {
    @Nullable
    @BindView(R.id.common_id_toolbar)
    Toolbar mCommonTb;

    //保存注册的receiver
    private Map<String, ActivityReceiver> mReceiverMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initSystemBarTint();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        registerAction(Action.LOGIN_AGAIN_ACTION);
    }

    @Override
    protected void initToolbar() {
        if (null != mCommonTb) {
            setSupportActionBar(mCommonTb);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public Toolbar getToolbar() {
        return mCommonTb;
    }

    public void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setFitsSystemWindwos(true);
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getColorPrimary());
        }
    }

    // 设置状态栏颜色
    public void setStatusBarTintColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(resId));
        }
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void setFitsSystemWindwos(boolean enable) {
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(enable);
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    /**
     * 注册广播的action
     *
     * @param action
     */
    public void registerAction(String action) {
        if (StringUtils.isBlank(action)) {
            return;
        }
        if (mReceiverMap == null) {
            mReceiverMap = new HashMap<String, ActivityReceiver>();
        }
        if (!mReceiverMap.containsKey(action)) {
            ActivityReceiver receiver = new ActivityReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            LocalBroadcastManager.getInstance(this)
                    .registerReceiver(receiver, filter);
            mReceiverMap.put(action, receiver);
        }
    }

    /**
     * 注销广播的action
     *
     * @param action
     */
    public void unregisterAction(String action) {
        if (StringUtils.isBlank(action) || mReceiverMap == null) {
            return;
        }
        if (mReceiverMap.containsKey(action)) {
            LocalBroadcastManager.getInstance(this)
                    .unregisterReceiver(mReceiverMap.get(action));
            mReceiverMap.remove(action);
        }
    }

    /**
     * 处理广播消息
     */
    protected void onReceive(Intent intent) {
        final String action = intent.getAction();
        if (Action.LOGIN_AGAIN_ACTION.equalsIgnoreCase(action)) {
            ToastUtils.make("需要重新登录");
            DispatchManager.startLoginActivity(this);
        }
    }

    /**
     * 广播接收器
     *
     * @author zlzsam
     */
    private class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                BaseActivity.this.onReceive(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消广播监听
        if (mReceiverMap != null) {
            for (String key : mReceiverMap.keySet()) {
                LocalBroadcastManager.getInstance(this)
                        .unregisterReceiver(mReceiverMap.get(key));
            }
            mReceiverMap.clear();
        }
        OkHttpUtils.getInstance().cancel(this);
    }
}
