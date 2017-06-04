package com.yuplus.publiccloud.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.callback.JsonCallback;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.util.QRCodeUtils;

import java.io.File;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * Created by zlzsa on 2017/6/5.
 */

public class QRCodeActivity extends TitleActivity {
    @BindView(R.id.qrcode_id_img)
    ImageView mQRCodeIm;

    private String mParams;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("设备数据分享");
        setShowHomeBack();

        mParams = getIntent().getStringExtra(AppCst.COMMON_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        OkHttpUtils.postString()
                .url("http://proudsmart-paydemo.yuplus.net/device/share")
                .tag(this)
                .content(mParams)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(String response, Call request) {
                        super.onSuccess(response, request);
                        ResultResponse result = JSON.parseObject(response,ResultResponse.class);
                        initQRCode(result);
                    }

                    @Override
                    public void onFailure(Call request, Exception e) {
                        super.onFailure(request, e);
                    }
                });
    }

    private void initQRCode(ResultResponse result) {
        final String filePath = this.getFileRoot(QRCodeActivity.this) + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg";
        if (QRCodeUtils.createQRImage(getmParams(result), 800, 800, null, filePath)) {
            Glide.with(this)
                    .load(filePath)
                    .into(mQRCodeIm);
        }
    }

    private String getmParams(ResultResponse result) {
        StringBuffer content = new StringBuffer();
        content.append("http://proudsmart-paydemo.yuplus.net/device/detail")
                .append("?")
                .append("share_id=")
                .append(result.getShare_id())
                .append("&")
                .append("share_token")
                .append(result.getShare_token());
        return content.toString();
    }


    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

    static class ResultResponse {

        private int share_id;
        private String share_token;
        private int error_code;

        public int getShare_id() {
            return share_id;
        }

        public void setShare_id(int share_id) {
            this.share_id = share_id;
        }

        public String getShare_token() {
            return share_token;
        }

        public void setShare_token(String share_token) {
            this.share_token = share_token;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }
    }
}
