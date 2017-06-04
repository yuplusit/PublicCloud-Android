package com.yuplus.publiccloud.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zlzsam on 2017/6/4.
 */

public class WebFragment extends BaseFragment {
    @BindView(R.id.web_id_ll)
    public LinearLayout mRootView;
    @BindView(R.id.web_id_progress)
    public ProgressBar mProgressbar;
    @BindView(R.id.web_id_webview)
    public WebView mWebView;

    private String mUrl;
    private WebSettings mWebSetting;

    public static WebFragment newInstance(final String url) {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        args.putString(AppCst.COMMON_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mUrl = getArguments().getString(AppCst.COMMON_KEY);
    }

    @Override
    protected void initView() {
        super.initView();
        mWebView.clearCache(true);
        mWebView.clearHistory();
        initWebViewConfig();
        mWebView.loadUrl(mUrl);
    }

    private void initWebViewConfig() {
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setBackgroundColor(getResources().getColor(R.color.common_window_background));
        mWebView.setWebViewClient(new CommonWebViewClient());
        mWebView.setWebChromeClient(new CommonWebChromeClient());
        mWebSetting = mWebView.getSettings();
        if (null != mWebSetting) {
            mWebSetting.setJavaScriptEnabled(true);
            mWebSetting.setDomStorageEnabled(true);
            mWebSetting.setBlockNetworkImage(false);
            //自适应屏幕
            mWebSetting.setUseWideViewPort(true);
            mWebSetting.setLoadWithOverviewMode(true);
            mWebSetting.setBuiltInZoomControls(true);
            mWebSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            mWebSetting.setLoadWithOverviewMode(true);
            mWebSetting.setSupportZoom(true);
            mWebSetting.setDisplayZoomControls(false);
            mWebSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

            //TODO 解决在一些网站站出现https和http资源混搭的情况,http://developer.android.com/reference/android/webkit/WebSettings.html#setMixedContentMode
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mWebSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
    }

    /**
     * @author zlzsam
     */
    private class CommonWebViewClient extends WebViewClient {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(mUrl);
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mUrl = url;
            if (null != view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (view.getSettings().getLoadsImagesAutomatically()) {
                        view.getSettings().setLoadsImagesAutomatically(true);
                    }
                } else {
                    view.getSettings().setLoadsImagesAutomatically(false);
                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (null != view) {
                if (!view.getSettings().getLoadsImagesAutomatically()) {
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }
    }

    /**
     * @author zlzsam
     */
    private class CommonWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (null == mProgressbar) {
                return;
            }
            if (progress == 100) {
                mProgressbar.setVisibility(View.GONE);
            } else {
                if (mProgressbar.getVisibility() == View.GONE)
                    mProgressbar.setVisibility(View.VISIBLE);
                mProgressbar.setProgress(progress);
                super.onProgressChanged(view, progress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public boolean onShowFileChooser(WebView webView,
                                         ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            // TODO Auto-generated method stub
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mWebView) {
            mRootView.removeView(mWebView);
            mWebView.removeAllViews();
            mWebView.clearHistory();
            mWebView.clearView();
            try {
                mWebView.clearCache(true);
                mWebView.destroy();
            } catch (Exception e) {
            }
            System.gc();
        }
    }
}
