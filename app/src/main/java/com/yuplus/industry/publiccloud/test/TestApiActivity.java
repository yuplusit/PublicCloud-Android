package com.yuplus.industry.publiccloud.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yuplus.cloudsdk.future.data.params.ListParams;
import com.yuplus.cloudsdk.future.http.ApiCst;
import com.yuplus.industry.publiccloud.R;

/**
 * @user longzhen
 * @date 5/15/2017
 * @desc
 */

public class TestApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        initData();
    }

    private void initData() {
        //登录接口
        findViewById(R.id.api_test_btn_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListParams requestParams = new ListParams()
                        .addParam("runxing")
                        .addParam("test123");
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.LOGIN_API);
                intent.putExtra("PARAMS", requestParams.toJson());
                startActivity(intent);
            }
        });

        //获取警告信息接口
        findViewById(R.id.api_test_btn_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[{\"domain\":\"\",\"nodeType\":\"\",\"alertCodes\":\"\",\"pageSize\":1000,\"messageFilter\":\"\",\"severities\":\"1,2,3,4\",\"states\":\"0,5,10,20\",\"firstTimeFrom\":\"\",\"firstTimeTo\":\"\"},{\"start\":0,\"length\":10,\"sort\":\"arisingTime\",\"sortType\":\"desc\",\"statCount\":true}]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_ALERT_BY_PAGE_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获取设备信息接口
        findViewById(R.id.api_test_btn_03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[{},{\"start\":0,\"length\":10,\"sort\":\"createTime\",\"sortType\":\"desc\",\"statCount\":true}]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获取客户信息接口
        findViewById(R.id.api_test_btn_04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "{\"orCondition\":\"\",\"customerName\":\"\",\"customerAddress\":\"\",\"domainPath\":\"\",\"customerPhone\":\"\"}";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_CUSTOMER_INFO_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //根据id获取客户信息接口
        findViewById(R.id.api_test_btn_05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[188237018906100]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_CUSTOMER_INFO_BY_ID_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //关闭警告接口
        findViewById(R.id.api_test_btn_06).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "{\"actionType\":\"recover\",\"alertIds\":[276222687066322],\"recoverAll\":true,\"resolved\":true,\"clearOut\":true}";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.ALERT_SEND_RECOVER_ACTION_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //警告确认接口
        findViewById(R.id.api_test_btn_07).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "{\"actionType\":\"claim\",\"alertIds\":[276222687066322]}";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.ALERT_SEND_CLAIM_ACTION_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //启用设备接口
        findViewById(R.id.api_test_btn_08).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "273511171816067";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.DEVICE_ACTIVATE_GATEWAY_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //停用设备接口
        findViewById(R.id.api_test_btn_09).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "273511171816067";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.DEVICE_DEACTIVATE_GATEWAY_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //查找project接口
        findViewById(R.id.api_test_btn_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "{}";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.FIND_PROJECTS_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //查找工单
        findViewById(R.id.api_test_btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[{\"status\":100}]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.TASK_FIND_TICKETS_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得KQI/
        findViewById(R.id.api_test_btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[\"kpi\",{\"isRealTimeData\":true,\"timePeriod\":0,\"nodeIds\":[237495398736480,240732999676487,240732999676508,240732999676515,240732999676517,240732999676553,240732999676571,240732999676574,257025823256070,257025823256173],\"kpiCodes\":[999998],\"includeInstance\":true}]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_KPI_VALUE_LIST_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得设备的模板
        findViewById(R.id.api_test_btn_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[[\"205383111786068\"]]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_MODEL_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得设备的模板的KPI定义
        findViewById(R.id.api_test_btn_14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[197979751246133]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_MODEL_KPIS_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得设备的模板的属性定义
        findViewById(R.id.api_test_btn_15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "239077571456149";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_MODEL_ATTRS_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得系统配置
        findViewById(R.id.api_test_btn_16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_CONFIG_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获得系统单位
        findViewById(R.id.api_test_btn_17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "null";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_UNIT_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获取资源域
        findViewById(R.id.api_test_btn_18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "{}";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_DOMAINS_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //获取最新的消息
        findViewById(R.id.api_test_btn_19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "0";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_LEAST_MESSAGE_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });

        //全部的消息接口
        findViewById(R.id.api_test_btn_20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String params = "[]";
                Intent intent = new Intent(TestApiActivity.this, TestRequestActivity.class);
                intent.putExtra("URL", ApiCst.GET_ALL_MESSAGE_LIST_API);
                intent.putExtra("PARAMS", params);
                startActivity(intent);
            }
        });
    }
}
