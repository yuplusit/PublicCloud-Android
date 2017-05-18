package com.yuplus.publiccloud.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_id_bg_img)
    ImageView mSplashBgIv;

    private Bitmap   mBgBitmap;
    private Handler  mDelayedHandler;
    private Runnable mDelayedRunnable;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mDelayedHandler = new Handler();
        mDelayedRunnable = new Runnable() {
            @Override
            public void run() {
                DispatchManager.startLoginActivity(SplashActivity.this);
                finish();
            }
        };
    }

    @Override
    protected void initView() {
        super.initView();
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_splash_bg);
        mSplashBgIv.setImageBitmap(mBgBitmap);
        mDelayedHandler.postDelayed(mDelayedRunnable, AppCst.SPLASH_TRANSITION_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mDelayedHandler && null != mDelayedRunnable) {
            mDelayedHandler.removeCallbacks(mDelayedRunnable);
        }
        if (null != mBgBitmap && !mBgBitmap.isRecycled()) {
            mBgBitmap.recycle();
            mBgBitmap = null;
        }
    }
}
