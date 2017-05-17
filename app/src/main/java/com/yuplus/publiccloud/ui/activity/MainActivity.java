package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.CustomerFragment;
import com.yuplus.publiccloud.ui.fragment.DeviceFragment;
import com.yuplus.publiccloud.ui.fragment.HomeFirstFragment;
import com.yuplus.publiccloud.ui.fragment.OrderFrament;
import com.yuplus.publiccloud.ui.fragment.WarningFragment;
import com.yuplus.publiccloud.ui.widget.FragmentTabHost;
import com.yuplus.publiccloud.util.ViewUtils;

import butterknife.BindView;

public class MainActivity extends TitleActivity {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

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
        initTabs();
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_HOME, "首页")),
                HomeFirstFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_USER, "客户")),
                CustomerFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_DEVICE_SOLID, "设备")),
                DeviceFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_WARNING_01, "告警")),
                WarningFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ORDER, "工单")),
                OrderFrament.class, null);

        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setStripEnabled(false);

        //默认选中第一个
        mTabHost.setCurrentTab(0);
    }
}
