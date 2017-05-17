package com.yuplus.publiccloud.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.callback.StringCallback;
import com.yuplus.cloudsdk.util.JsonUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * @user longzhen
 * @date 5/15/2017
 * @desc
 */

public class TestRequestActivity extends TitleActivity {
    private String url;
    private String params;

    private TextView mRequestApiTv;
    private TextView mRequestParamsTv;
    private TextView mReponseDataTv;

    private ProgressHUBDialog mLoadingView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test_request;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("Api请求详情");
        setShowHomeBack();
    }

    @Override
    protected void initView() {
        mRequestApiTv = (TextView) findViewById(R.id.test_id_request_url);
        mRequestParamsTv = (TextView) findViewById(R.id.test_id_request_params);
        mReponseDataTv = (TextView) findViewById(R.id.test_id_reponse_data);

        mLoadingView = ProgressHUBDialog.createDialog(this);
    }

    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        params = intent.getStringExtra("PARAMS");
        if (StringUtils.isNotBlank(url)) {
            mRequestApiTv.setText(url);
        }
        if (StringUtils.isNotBlank(params)) {
            mRequestParamsTv.setText(JsonUtils.stringToJSON(params));
        }
        startRequest();
    }

    private void startRequest() {
        OkHttpUtils.postString()
                .url(url)
                .tag(this)
                .content(params)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        mLoadingView.show();
                    }

                    @Override
                    public void onSuccess(String response, Call request) {
                        super.onSuccess(response, request);
                        mReponseDataTv.setText(JsonUtils.stringToJSON(response));
                        mLoadingView.hide();
                    }

                    @Override
                    public void onFailure(Call request, Exception e) {
                        super.onFailure(request, e);
                        mReponseDataTv.setText(e.getMessage());
                        mLoadingView.hide();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancel(this);
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
