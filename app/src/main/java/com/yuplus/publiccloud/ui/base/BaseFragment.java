package com.yuplus.publiccloud.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public abstract class BaseFragment extends AbsFragment {
    private View mContainterView;
    private Unbinder mUnbinder;

    //保存注册的receiver
    private Map<String, FragmentReceiver> mReceiverMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainterView = inflater.inflate(getLayoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, mContainterView);
        return mContainterView;
    }

    @Override
    protected View findViewById(int id) {
        return mContainterView.findViewById(id);
    }

    /**
     * 获取相关联的基类fragment activity
     *
     * @return
     */
    public FragmentActivity getFragmentActivity() {
        if (getActivity() instanceof FragmentActivity) {
            return (FragmentActivity) getActivity();
        }
        return null;
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
            mReceiverMap = new HashMap<String, FragmentReceiver>();
        }
        if (!mReceiverMap.containsKey(action)) {
            FragmentReceiver receiver = new FragmentReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            LocalBroadcastManager.getInstance(getFragmentActivity())
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
            LocalBroadcastManager.getInstance(getFragmentActivity())
                    .unregisterReceiver(mReceiverMap.get(action));
            mReceiverMap.remove(action);
        }
    }

    /**
     * 处理广播消息
     */
    protected void onReceive(Intent intent) {
    }

    /**
     * 广播接收器
     *
     * @author zlzsam
     */
    private class FragmentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent) {
                BaseFragment.this.onReceive(intent);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消广播监听
        if (mReceiverMap != null) {
            for (String key : mReceiverMap.keySet()) {
                LocalBroadcastManager.getInstance(getFragmentActivity())
                        .unregisterReceiver(mReceiverMap.get(key));
            }
            mReceiverMap.clear();
        }
        OkHttpUtils.getInstance().cancel(this);
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}
