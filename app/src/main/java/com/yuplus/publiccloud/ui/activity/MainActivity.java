package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.widget.TabHost;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.CustomerFragment;
import com.yuplus.publiccloud.ui.fragment.DeviceFragment;
import com.yuplus.publiccloud.ui.fragment.HomeFirstFragment;
import com.yuplus.publiccloud.ui.fragment.AlertFragment;
import com.yuplus.publiccloud.ui.widget.FragmentTabHost;
import com.yuplus.publiccloud.util.ToastUtils;
import com.yuplus.publiccloud.util.ViewUtils;

import butterknife.BindView;

public class MainActivity extends TitleActivity {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @BindView(R.id.main_navigation_layout)
    NavigationView mainNavigationLayout;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;

    private long startTime = 0L;

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
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, getToolbar(), 0, 0);
        drawerToggle.syncState();
        initTabs();
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_HOME_SOLID, "首页")),
                HomeFirstFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_CUSTOMER_SOLID, "客户")),
                CustomerFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_DEVICE_SOLID, "设备")),
                DeviceFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ALERT_SOLID, "告警")),
                AlertFragment.class, null);

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
                }
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
