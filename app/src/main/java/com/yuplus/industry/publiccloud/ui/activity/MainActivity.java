package com.yuplus.industry.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.future.data.response.UserResponse;
import com.yuplus.cloudsdk.future.http.impl.AppFutureImpl;
import com.yuplus.cloudsdk.okhttp.callback.JsonCallback;
import com.yuplus.cloudsdk.util.LogUtils;
import com.yuplus.industry.publiccloud.R;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private String TAG = "Network Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button) findViewById(R.id.main_id_btn);

      /*  Typeface typeface = Typeface.createFromAsset(getAssets(),"font/systemicon.otf");
        mIconView.setText("\ua045");
        mIconView.setTypeface(typeface);*/

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPresenter();
            }
        });
    }

    private void startPresenter() {
        String jsonStr = JSON.toJSONString(new UserResponse());
        LogUtils.d("-------->userReponse String:" + jsonStr);

        new AppFutureImpl()
                .login("runxing", "test123", this, new JsonCallback<UserResponse>() {

                    @Override
                    public void onSuccess(UserResponse response, Call request) {
                        super.onSuccess(response, request);
                        Log.d("SDK REQUEST", "the result :" + response.toString());
                    }

                    @Override
                    public void onFailure(Call request, Exception e) {
                        super.onFailure(request, e);
                        Log.d("SDK REQUEST FAILURE", "the Exception :" + e);
                    }
                });

    }
}
