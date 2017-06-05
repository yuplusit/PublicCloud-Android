package com.yuplus.publiccloud.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.lb.materialdesigndialog.base.DialogBase;
import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.materialdesigndialog.impl.MaterialDialogNormal;
import com.yuplus.cloudsdk.future.data.bean.MessageWrapperBean;
import com.yuplus.cloudsdk.future.data.bean.UserBean;
import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.BuildConfig;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.cst.BroadcastCst;
import com.yuplus.publiccloud.cst.TypeCst;
import com.yuplus.publiccloud.enums.EAppIconFont;
import com.yuplus.publiccloud.mvp.presenter.MessagePresenter;
import com.yuplus.publiccloud.mvp.view.MessageListView;
import com.yuplus.publiccloud.ui.DispatchManager;
import com.yuplus.publiccloud.ui.base.TitleActivity;
import com.yuplus.publiccloud.ui.fragment.AlertFragment;
import com.yuplus.publiccloud.ui.fragment.CustomerFragment;
import com.yuplus.publiccloud.ui.fragment.DeviceFragment;
import com.yuplus.publiccloud.ui.fragment.HomeFragment;
import com.yuplus.publiccloud.ui.fragment.OrderFragment;
import com.yuplus.publiccloud.ui.widget.FragmentTabHost;
import com.yuplus.publiccloud.util.IconFontUtils;
import com.yuplus.publiccloud.util.ToastUtils;
import com.yuplus.publiccloud.util.TokenUtils;
import com.yuplus.publiccloud.util.ViewUtils;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends TitleActivity implements MessageListView {

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
    private LinearLayout mAboutLl;
    private LinearLayout mLogOffLl;

    private TextView mSlideMsgCountTv;
    private TextView mMessageCountTv;
    private View     mMenuView;

    private long startTime = 0L;
    private View mHeaderView;

    private MessagePresenter mMessagePresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mMessagePresenter = new MessagePresenter();
        mMessagePresenter.setView(this);
        mMessagePresenter.setTag(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setShowTitle(true);
        setTitle("首页");

        registerAction(BroadcastCst.MSG_RED_POINT_COUNT_UPDATE);
        registerAction(BroadcastCst.UNTREATED_ALERT_COUNT_UPDATE);

        mMessagePresenter.getLatestMessage();
    }

    @Override
    protected void initView() {
        super.initView();
        mHeaderView = mainNavigationLayout.getHeaderView(0);
        mUserAvatar = (ImageView) mHeaderView.findViewById(R.id.user_id_avatar);
        mUserName = (TextView) mHeaderView.findViewById(R.id.user_id_name);
        mUserSign = (TextView) mHeaderView.findViewById(R.id.user_id_sign);
        mMessageLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_message_layout);
        mSlideMsgCountTv = (TextView) mHeaderView.findViewById(R.id.slide_id_red_point);
        mSkinLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_skin_layout);
        mFeedbackHelpLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_feedback_layout);
        mAboutLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_about_layout);
        mLogOffLl = (LinearLayout) mHeaderView.findViewById(R.id.slide_id_logoff_layout);

        mMenuView = LayoutInflater.from(this).inflate(R.layout.layout_item_message_redpoint, null);
        TextView msgIcon = (TextView) mMenuView.findViewById(R.id.message_id_icon);
        mMessageCountTv = (TextView) mMenuView.findViewById(R.id.message_id_icon_dig);
        IconFontUtils.setIconFont(msgIcon, EAppIconFont.APP_MESSAGE_ICON);
        setMenuView(mMenuView);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, getToolbar(), 0, 0);
        drawerToggle.syncState();
        initTabs();
    }

    @Override
    protected void initData() {
        super.initData();
        if (!TokenUtils.checkUserState(this)) {
            return;
        }
        UserBean user = AppApplication.user;
        if (StringUtils.isNotBlank(user.getLoginName())) {
            mUserSign.setText(user.getLoginName());
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            mUserName.setText(user.getUserName());
        }
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tab_content);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_HOME_SOLID, "首页")),
                HomeFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_CUSTOMER_SOLID, "客户")),
                CustomerFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_DEVICE_SOLID, "设备")),
                DeviceFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ALERT_SOLID, "告警", true)),
                AlertFragment.class, null);

        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator(ViewUtils.getTabItemView(this, EAppIconFont.APP_ICON_ORDER_SOLID, "工单")),
                OrderFragment.class, null);

        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setStripEnabled(false);

        //默认选中第一个
        mTabHost.setCurrentTab(0);
    }

    private void initMessagePoint(int count) {
        if (count == 0) {
            mSlideMsgCountTv.setVisibility(View.GONE);
            mMessageCountTv.setVisibility(View.GONE);
        } else {
            mSlideMsgCountTv.setVisibility(View.VISIBLE);
            mMessageCountTv.setVisibility(View.VISIBLE);
            if (count <= 99) {
                mSlideMsgCountTv.setText(String.valueOf(count));
                mMessageCountTv.setText(String.valueOf(count));
            } else {
                mSlideMsgCountTv.setText("99+");
                mMessageCountTv.setText("99+");
            }
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if ("0".equals(tabId)) {
                    setTitle("首页");
                    setmMenuVisibility(View.VISIBLE);
                } else if ("1".equals(tabId)) {
                    setTitle("客户");
                    setmMenuVisibility(View.GONE);
                } else if ("2".equals(tabId)) {
                    setTitle("设备");
                    setmMenuVisibility(View.GONE);
                } else if ("3".equals(tabId)) {
                    setTitle("告警");
                    setmMenuVisibility(View.GONE);
                } else if ("4".equals(tabId)) {
                    setTitle("工单");
                    setmMenuVisibility(View.GONE);
                }
            }
        });
        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "消息通知", TypeCst.SlideArea.TYPE_MESSAGE);
            }
        });
        mUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DispatchManager.startSlideAreaActivity(MainActivity.this, "个人资料", TypeCst.SlideArea.TYPE_USER_INFO);
                closeDrawerLayout();
            }
        });
        mMessageLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "消息通知", TypeCst.SlideArea.TYPE_MESSAGE);
                closeDrawerLayout();
            }
        });
        mSkinLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "主题换肤", TypeCst.SlideArea.TYPE_SKIN_SELECT);
                closeDrawerLayout();
            }
        });
        mFeedbackHelpLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatchManager.startSlideAreaActivity(MainActivity.this, "帮助反馈", TypeCst.SlideArea.TYPE_FEEDBACK_HELP);
                closeDrawerLayout();
            }
        });
        mAboutLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.make("当前版本为：V" + BuildConfig.VERSION_NAME);
            }
        });
        mLogOffLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialogNormal dialog = new MaterialDialogNormal(MainActivity.this);
                dialog.setTitle("退出登录");
                dialog.setMessage("你确定退出登录吗？");

                dialog.setNegativeButton("取消", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setPositiveButton("确定", new DialogWithTitle.OnClickListener() {
                    @Override
                    public void click(DialogBase dialog, View view) {
                        DispatchManager.startLoginActivity(MainActivity.this);
                        AppApplication.user = null;
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void closeDrawerLayout() {

    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        final String action = intent.getAction();
        if (BroadcastCst.MSG_RED_POINT_COUNT_UPDATE.equalsIgnoreCase(action)) {
            final int count = Integer.parseInt(mMessageCountTv.getText().toString());
            initMessagePoint(count - 1);
        } else if (BroadcastCst.UNTREATED_ALERT_COUNT_UPDATE.equalsIgnoreCase(action)) {
            final int count = intent.getIntExtra(AppCst.COMMON_DATA, 0);
            initTabRedPointCount(count);
        }
    }

    private void initTabRedPointCount(int count) {
        View view = mTabHost.getTabWidget().getChildAt(3);
        TextView redPoint = (TextView) view.findViewById(R.id.message_id_icon_dig);
        if (null != redPoint) {
            if (count <= 0) {
                redPoint.setVisibility(View.GONE);
            } else {
                redPoint.setVisibility(View.VISIBLE);
                if (count <= 99) {
                    redPoint.setText(String.valueOf(count));
                } else {
                    redPoint.setText(String.valueOf("99+"));
                }
            }
        }
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

    @Override
    public void onRenderMessageData(List<MessageWrapperBean> data) {
        if (ListUtils.isNotEmpty(data)) {
            initMessagePoint(data.size());
        } else {
            initMessagePoint(0);
        }
    }

    @Override
    public void onFailure(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            ToastUtils.make(msg);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
