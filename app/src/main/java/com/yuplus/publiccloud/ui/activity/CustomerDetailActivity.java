package com.yuplus.publiccloud.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yuplus.cloudsdk.BitmapUtils;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;
import com.yuplus.cloudsdk.future.data.bean.ProjectBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.mvp.presenter.KpiValuePresenter;
import com.yuplus.publiccloud.mvp.presenter.ProjectPresenter;
import com.yuplus.publiccloud.mvp.view.KpiValueView;
import com.yuplus.publiccloud.mvp.view.ProjectListView;
import com.yuplus.publiccloud.ui.adapter.CustomerDetailAdapter;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.dialog.ProgressHUBDialog;
import com.yuplus.publiccloud.ui.listener.HidingScrollListener;
import com.yuplus.publiccloud.util.BlurUtils;
import com.yuplus.publiccloud.util.DensityUtils;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 5/26/2017
 * @desc
 */

public class CustomerDetailActivity extends TitleActivity implements ProjectListView, KpiValueView {

    @BindView(R.id.custom_detail_id_recylerview)
    XRecyclerView mXRecyclerView;

    private TextView mCustomerNameTv;
    private ImageView mCustomerBgIv;
    private RoundedImageView mCustomerAvatar;
    private TextView mCustomerDeviceCountTv;
    private TextView mCustomerAlertCountTv;
    private TextView mCustomerOrderCountTv;

    private ProjectPresenter mProjectPresenter;
    private KpiValuePresenter mKpiValuePresenter;

    private ProgressHUBDialog mLoadingView;
    private CustomerDetailAdapter mCustomerDetailAdapter;

    private CustomerBean mCustomer;
    private List<ProjectBean> mProjectList;
    private Toolbar mToolbar;
    private int mMoveDistance;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_customer_detail;
    }

    @Override
    protected void initPresenter() {
        mProjectPresenter = new ProjectPresenter();
        mProjectPresenter.setView(this);
        mProjectPresenter.setTag(this);
        mKpiValuePresenter = new KpiValuePresenter();
        mKpiValuePresenter.setView(this);
        mKpiValuePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mCustomer = getIntent().getParcelableExtra(AppCst.COMMON_DATA);
        if (null == mCustomer) {
            finish();
        }
        setShowTitle(true);
        setTitle(mCustomer.getCustomerName());
        setToolbarBackground(R.color.common_transparent);
        setShowHomeBack();

        mProjectList = new ArrayList<>();
        mToolbar = getToolbar();

        mProjectPresenter.findProjectListByCustomerId(String.valueOf(mCustomer.getId()));
    }

    @Override
    protected void initView() {
        super.initView();

        mLoadingView = ProgressHUBDialog.createDialog(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setHasFixedSize(true);

        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_item_customer_detail_header, null);
        mCustomerNameTv = (TextView) headerView.findViewById(R.id.custom_id_name);
        mCustomerBgIv = (ImageView) headerView.findViewById(R.id.custom_id_bg);
        mCustomerAvatar = (RoundedImageView) headerView.findViewById(R.id.custom_id_avatar);
        mCustomerDeviceCountTv = (TextView) headerView.findViewById(R.id.customer_id_device_count);
        mCustomerAlertCountTv = (TextView) headerView.findViewById(R.id.customer_id_alert_count);
        mCustomerOrderCountTv = (TextView) headerView.findViewById(R.id.customer_id_order_count);

        mCustomerNameTv.setText(mCustomer.getCustomerContact());
        if (StringUtils.isNotBlank(mCustomer.getImageUrl())) {
            Glide.with(this)
                    .load(mCustomer.getImageUrl())
                    .asBitmap()
                    .error(R.drawable.ic_customer_bg)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Bitmap bitmap = BlurUtils.doBlur(resource, 1, 10);
                            mCustomerBgIv.setImageBitmap(bitmap);
                        }
                    });
            Glide.with(this)
                    .load(mCustomer.getImageUrl())
                    .error(R.drawable.ic_customer_bg)
                    .into(mCustomerAvatar);
        } else {
            Bitmap bitmap = BitmapUtils.drawableToBitamp(getResources().getDrawable(R.drawable.ic_customer_bg));
            Bitmap blurBitmap = BlurUtils.doBlur(bitmap, 3, 1);
            mCustomerBgIv.setImageBitmap(blurBitmap);
            mCustomerAvatar.setImageBitmap(bitmap);
        }


        mCustomerDeviceCountTv.setText(String.valueOf(mCustomer.getDeviceCount()));
        mCustomerAlertCountTv.setText(String.valueOf(mCustomer.getAlertCount()));
        mCustomerOrderCountTv.setText(String.valueOf(mCustomer.getOrderCount()));
        mXRecyclerView.addHeaderView(headerView);

        mCustomerDetailAdapter = new CustomerDetailAdapter(this, null);
        mXRecyclerView.setAdapter(mCustomerDetailAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setArrowImageView(R.drawable.ic_down_grey);
        mXRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void initListener() {
        super.initListener();

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mProjectPresenter.findProjectListByCustomerId(String.valueOf(mCustomer.getId()));
            }

            @Override
            public void onLoadMore() {
            }
        });
        mXRecyclerView.addOnScrollListener(new HidingScrollListener() {

            @Override
            public void onHide() {
                hideToolbar();
            }

            @Override
            public void onShow() {
                showToolbar();
            }

            @Override
            public void onScrolled(int dx, int dy) {
                mMoveDistance += dy;
                if (mMoveDistance <= DensityUtils.dip2px(CustomerDetailActivity.this, 200f)) {
                    setToolbarBackground(R.color.common_transparent);
                } else {
                    setToolbarBackground(R.color.common_transparent_40_precent);
                }
            }
        });

        mXRecyclerView.setRefreshPullDirectionListener(new XRecyclerView.RefreshPullDirectionListener() {

            @Override
            public void onDown() {
                hideToolbar();
            }

            @Override
            public void onReset() {
                showToolbar();
            }
        });
    }

    private void hideToolbar() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showToolbar() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
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
    public void onRenderProjectData(List<ProjectBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            mProjectList.clear();
            mProjectList.addAll(data);
            List<Long> nodeIds = new ArrayList<>();
            for (ProjectBean project : mProjectList) {
                nodeIds.add(project.getId());
            }
            if (ListUtils.isNotEmpty(nodeIds)) {
                List<Long> kpiCodes = new ArrayList();
                kpiCodes.add(3001L);//设备数量
                kpiCodes.add(3003L);//告警数量
                kpiCodes.add(3004L);//工单数量
                mKpiValuePresenter.getKpiValueListByNodeIds(kpiCodes, nodeIds);
            }
        } else {
            mXRecyclerView.refreshComplete();
        }
    }

    @Override
    public void onRenderKpiValueData(List<KpiValueBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            for (ProjectBean project : mProjectList) {
                for (KpiValueBean kpiValue : data) {
                    if (project.getId() == kpiValue.getNodeId()) {
                        if (kpiValue.getKpiCode() == 3001) {
                            project.setDeviceCount((int)kpiValue.getValue());
                        } else if (kpiValue.getKpiCode() == 3003) {
                            project.setAlertCount((int)kpiValue.getValue());
                        } else if (kpiValue.getKpiCode() == 3004) {
                            project.setOrderCount((int)kpiValue.getValue());
                        }
                    }
                }
            }
            mCustomerDetailAdapter.insertAll(mProjectList);
        }
        mXRecyclerView.refreshComplete();
    }

    @Override
    public void onFailure(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            ToastUtils.make(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mLoadingView) {
            mLoadingView.dismiss();
        }
    }
}
