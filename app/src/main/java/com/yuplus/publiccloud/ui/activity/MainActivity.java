package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.TypeCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.AlertFragment;
import com.yuplus.publiccloud.ui.fragment.CustomerFragment;
import com.yuplus.publiccloud.ui.fragment.DeviceFragment;
import com.yuplus.publiccloud.ui.fragment.HomeFragment;
import com.yuplus.publiccloud.ui.fragment.OrderFragment;
import com.yuplus.publiccloud.ui.widget.FragmentTabHost;
import com.yuplus.publiccloud.util.ToastUtils;
import com.yuplus.publiccloud.util.ViewUtils;

import butterknife.BindView;

public class MainActivity extends TitleActivity {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @BindView(R.id.main_navigation_layout)
    NavigationView  mainNavigationLayout;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout    mainDrawerLayout;

    private ImageView    mUserAvatar;
    private TextView     mUserName;
    private TextView     mUserSign;
    private LinearLayout mMessageLl;
    private LinearLayout mSkinLl;
    private LinearLayout mFeedbackHelpLl;
    private LinearLayout mShareLl;
    private LinearLayout mAboutLl;
    private LinearLayout mLogOffLl;

    private long startTime = 0L;
    private View mHeaderView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("首页");
    }

    @Override
    protected void initView() {
        super.initView();
        mHeaderView = mainNavigationLayout.getHeaderView(0);
        mUserAvatar = (ImageView) mHeaderView.findViewById(R.id.user_id_avatar);
        mUserName = (TextView) mHeaderView.findViewById(R.id.user_id_name);
        mUserSign = (TextView) mHeaderView.findViewById(R.id.user_id_sign);
        mMessageLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_message_layout);
        mSkinLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_skin_layout);
        mFeedbackHelpLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_feedback_layout);
        mShareLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_share_layout);
        mAboutLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_about_layout);
        mLogOffLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_logoff_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, getToolbar(), 0, 0);
        drawerToggle.syncState();
        initTabs();
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_HOME_SOLID, "首页")),
                HomeFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_CUSTOMER_SOLID, "客户")),
                CustomerFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_DEVICE_SOLID, "设备")),
                DeviceFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ALERT_SOLID, "告警")),
                AlertFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ORDER_SOLID, "工单")),
                OrderFragment.class, null);

        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setStripEnabled(false);

        //默认选中第一个
        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if ("0".equals(tabId)) {
                    setTitle("首页");
                } else if ("1".equals(tabId)) {
                    setTitle("客户");
                } else if ("2".equals(tabId)) {
                    setTitle("设备");
                } else if ("3".equals(tabId)) {
                    setTitle("告警");
                } else if ("4".equals(tabId)) {
                    setTitle("工单");
                }
            }
        });
        mUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "个人资料", TypeCst.SlideArea.TYPE_USER_INFO);
            }
        });
        mMessageLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "消息通知", TypeCst.SlideArea.TYPE_MESSAGE);
            }
        });
        mSkinLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "主题换肤", TypeCst.SlideArea.TYPE_SKIN_SELECT);
            }
        });
        mFeedbackHelpLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "帮助反馈", TypeCst.SlideArea.TYPE_FEEDBACK_HELP);
            }
        });
        mShareLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAboutLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLogOffLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mainDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mainDrawerLayout.closeDrawers();
            return;
        }
        long currentTime = System.currentTimeMillis();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        } else {
            if ((currentTime - startTime) >= 2000) {
                ToastUtils.make(R.string.common_exit_tip);
                startTime = currentTime;
            } else {
                finish();
                System.exit(0);
            }
        }
    }
}
