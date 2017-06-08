package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.future.data.params.MapParams;
import com.yuplus.cloudsdk.log.LogUtils;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.ui.adapter.HistoryDataAdapter;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.echarts.EChartsView;
import com.yuplus.publiccloud.util.DateUtils;
import com.yuplus.publiccloud.util.ToastUtils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class DeviceHistoryAcitivity extends TitleActivity implements KpiValueView {

    @BindView(R.id.history_id_testname)
    TextView      mTestNameTv;
    @BindView(R.id.history_id_echarts)
    EChartsView   mHistoryEcharts;
    @BindView(R.id.history_id_recylerview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.recylerview_id_empty_data)
    View          mEmptyView;

    private ProgressHUBDialog  mLoadingView;
    private HistoryDataAdapter mHistoryDataAdapter;

    private KpiValuePresenter mKpiValuePresenter;

    private long               mKpiCode;
    private long               mNodeId;
    private String             mTestName;
    private String             mUUID;
    private List<KpiValueBean> mKpiValueList;
    private List<String>       mTestTimeList;
    private List<Long>         mTestValueList;
    private WebSocketClient    mWebSocketClient;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_device_history;
    }

    @Override
    protected void initPresenter() {
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("测点数据");
        setShowHomeBack();
        mKpiCode = getIntent().getLongExtra(AppCst.COMMON_DATA, 0L);
        mNodeId = getIntent().getLongExtra(AppCst.COMMON_ID, 0L);
        mTestName = getIntent().getStringExtra(AppCst.COMMON_CONTENT);

        mKpiValueList = new ArrayList<>();
        mTestTimeList = new ArrayList<>();
        mTestValueList = new ArrayList<>();
        mUUID = UUID.randomUUID().toString();
        mLoadingView = ProgressHUBDialog.createDialog(this);
    }

    @Override
    protected void initView() {
        super.initView();

        if (StringUtils.isNotBlank(mTestName)) {
            mTestNameTv.setText(mTestName);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);
        mHistoryDataAdapter = new HistoryDataAdapter(this, null);
        mXRecyclerView.setAdapter(mHistoryDataAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setPullRefreshEnabled(false);
    }

    @Override
    protected void initData() {
        super.initData();
        List<Long> kpiCodes = new ArrayList<>();
        kpiCodes.add(mKpiCode);
        List<Long> nodeIds = new ArrayList<>();
        nodeIds.add(mNodeId);
        long timePeriod = 1000 * 60 * 60;
        mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds, timePeriod);
    }

    private void openWebSocket() {
        try {
            mWebSocketClient = new WebSocketClient(new URI(AppCst.WEBSOCKET_ADDRESS)) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    LogUtils.d("onOpen");
                    register();
                }

                @Override
                public void onMessage(String s) {
                    LogUtils.d(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    LogUtils.d("onClose");
                }

                @Override
                public void onError(Exception e) {
                    LogUtils.d("onError");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        SSLSocketFactory factory = sslContext.getSocketFactory();

        try {
            mWebSocketClient.setSocket(factory.createSocket());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mWebSocketClient.connect();
    }

    private void register() {
        MapParams params = new MapParams()
                .addParam("ciid", String.valueOf(mNodeId))
                .addParam("kpi", String.valueOf(mKpiCode));
        MapParams registerParmas = new MapParams()
                .addParam("operation", "register")
                .addParam("uuid", mUUID)
                .addParam("type", "kpi")
                .addParam("param", params.getParams());
        if (null != mWebSocketClient) {
            mWebSocketClient.send(registerParmas.toJson());
        }
    }

    private void unregister() {
        MapParams unregisterParams = new MapParams()
                .addParam("operation", "unRegister")
                .addParam("uuid", mUUID);
        if (null != mWebSocketClient) {
            mWebSocketClient.send(JSON.toJSONString(unregisterParams));
        }
    }

    private void initEchart(List<String> timeList, List<Long> valueList) {
        GsonOption option = new GsonOption();
        option.legend().show(false);
        option.toolbox().show(true).feature(Tool.mark, new MagicType(Magic.line, Magic.bar)).padding(0);
        option.calculable(true);
        option.tooltip().trigger(Trigger.axis);
        option.xAxis(new CategoryAxis().data(timeList.toArray()));
        option.yAxis(new ValueAxis());
        option.grid().y(30).y2(20).x(32).x2(15);

        Line line = new Line("测点数据");
        line.data(valueList.toArray());
        option.series(line);

        mHistoryEcharts.setOption(option);
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mKpiValueList.clear();
            mKpiValueList.addAll(data);
            mHistoryDataAdapter.insertAll(mKpiValueList);
            for (KpiValueBean kpiValue : data) {
                mTestTimeList.add(DateUtils.timeFormat(kpiValue.getArisingTime(), "hh:mm"));
                mTestValueList.add(kpiValue.getValue());
            }
            initEchart(mTestTimeList, mTestValueList);
        } else {
            mXRecyclerView.setEmptyView(mEmptyView);
        }
        openWebSocket();
    }

    @Override
    public void onFailure(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            ToastUtils.make(msg);
        }
    }

    @Override
    public void showLoading() {
        if (null != mLoadingView && !mLoadingView.isShowing()) {
            mLoadingView.show();
        }
    }

    @Override
    public void hideLoading() {
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        if (null != mWebSocketClient) {
            unregister();
            mWebSocketClient.close();
        }
    }
}
