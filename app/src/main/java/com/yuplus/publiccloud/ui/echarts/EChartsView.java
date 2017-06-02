package com.yuplus.publiccloud.ui.echarts;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.github.abel533.echarts.json.GsonOption;
import com.yuplus.publiccloud.R;

/**
 * @user longzhen
 * @date 5/31/2017
 * @desc 图表包装类
 */

public class EChartsView extends LinearLayout {
    private WebView     mWebView;
    private WebSettings mWebSetting;

    public EChartsView(Context context) {
        super(context);
        initView();
    }

    public EChartsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EChartsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mWebView = new WebView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(layoutParams);
        mWebView.setBackgroundColor(getResources().getColor(R.color.common_transparent));
        addView(mWebView);
        initWebViewConfig();

        mWebView.loadUrl("file:///android_asset/echarts/echarts.html");
    }

    private void initWebViewConfig() {
        /*mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);*/
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new EChartsJavaScript(), this.getClass().getSimpleName());

        mWebSetting = mWebView.getSettings();

        if (null != mWebSetting) {
            mWebSetting.setJavaScriptEnabled(true);
            mWebSetting.setDomStorageEnabled(true);
            mWebSetting.setBlockNetworkImage(false);
            mWebSetting.setAllowFileAccess(true);
            //自适应屏幕
            /*mWebSetting.setUseWideViewPort(true);
            mWebSetting.setLoadWithOverviewMode(true);
            mWebSetting.setBuiltInZoomControls(false);
            mWebSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            mWebSetting.setLoadWithOverviewMode(true);
            mWebSetting.setSupportZoom(false);
            mWebSetting.setDisplayZoomControls(false);
            mWebSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);*/

        }
    }

    public void setOption(GsonOption option) {
        final String data = option.toString();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript:createChart(" + data + ");");
            }
        }, 300);
    }
}
