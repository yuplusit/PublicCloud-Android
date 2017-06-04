package com.yuplus.publiccloud.ui.fragment;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.sp.SPCst;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import skin.support.SkinCompatManager;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class SkinSelectFragment extends BaseFragment {

    @BindView(R.id.skin_id_red_rb)
    RadioButton mRedSkinRb;
    @BindView(R.id.skin_id_blue_rb)
    RadioButton mBlueSkinRb;
    @BindView(R.id.skin_id_green_rb)
    RadioButton mGreenSkinRb;
    @BindView(R.id.skin_id_dullgrey_rb)
    RadioButton mDullgreySkinRb;
    @BindView(R.id.skin_id_red_layout)
    LinearLayout mRedSkinLayout;
    @BindView(R.id.skin_id_blue_layout)
    LinearLayout mBlueSkinLayout;
    @BindView(R.id.skin_id_green_layout)
    LinearLayout mGreenSkinLayout;
    @BindView(R.id.skin_id_dullgrey_layout)
    LinearLayout mDullgreySkinLayout;

    private List<RadioButton> mRBList;
    private List<String> mThemeNameList;

    public static SkinSelectFragment newInstance() {
        Bundle args = new Bundle();
        SkinSelectFragment fragment = new SkinSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_skin_select;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mRBList = new ArrayList<>();
        mRBList.add(mRedSkinRb);
        mRBList.add(mBlueSkinRb);
        mRBList.add(mGreenSkinRb);
        mRBList.add(mDullgreySkinRb);
        mThemeNameList = new ArrayList<>();
        mThemeNameList.add("RedSkinStyle.skin");
        mThemeNameList.add("BlueSkinStyle.skin");
        mThemeNameList.add("GreenSkinStyle.skin");
        mThemeNameList.add("DullGreySkinStyle.skin");

        initRadioButton();
    }

    private void initRadioButton() {
        String name = AppApplication.prefer.getString(SPCst.APP_THEME_VALUE);
        if (StringUtils.isNotBlank(name)) {
            switch (name) {
                case "RedSkinStyle.skin":
                    changeButtonStyle(mRedSkinRb.getId());
                    break;
                case "BlueSkinStyle.skin":
                    changeButtonStyle(mBlueSkinRb.getId());
                    break;
                case "GreenSkinStyle.skin":
                    changeButtonStyle(mGreenSkinRb.getId());
                    break;
                case "DullGreySkinStyle.skin":
                    changeButtonStyle(mDullgreySkinRb.getId());
                    break;
                default:
                    changeButtonStyle(mBlueSkinRb.getId());
                    break;
            }
        } else {
            changeButtonStyle(mBlueSkinRb.getId());
        }
    }

    @OnClick({R.id.skin_id_red_rb, R.id.skin_id_blue_rb, R.id.skin_id_green_rb, R.id.skin_id_dullgrey_rb
            , R.id.skin_id_red_layout, R.id.skin_id_blue_layout, R.id.skin_id_green_layout, R.id.skin_id_dullgrey_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.skin_id_red_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin(mThemeNameList.get(0));
                break;
            case R.id.skin_id_blue_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin(mThemeNameList.get(1));
                break;
            case R.id.skin_id_green_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin(mThemeNameList.get(2));
                break;
            case R.id.skin_id_dullgrey_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin(mThemeNameList.get(3));
                break;
            case R.id.skin_id_red_layout:
                changeButtonStyle(mRedSkinRb.getId());
                onSlecetSkin(mThemeNameList.get(0));
                break;
            case R.id.skin_id_blue_layout:
                changeButtonStyle(mBlueSkinRb.getId());
                onSlecetSkin(mThemeNameList.get(1));
                break;
            case R.id.skin_id_green_layout:
                changeButtonStyle(mGreenSkinRb.getId());
                onSlecetSkin(mThemeNameList.get(2));
                break;
            case R.id.skin_id_dullgrey_layout:
                changeButtonStyle(mDullgreySkinRb.getId());
                onSlecetSkin(mThemeNameList.get(3));
                break;
        }
    }

    private void changeButtonStyle(int id) {
        for (RadioButton rb : mRBList) {
            if (rb.getId() == id) {
                rb.setChecked(true);
            } else {
                rb.setChecked(false);
            }
        }
    }

    private void onSlecetSkin(final String skinName) {
        SkinCompatManager.getInstance().loadSkin(skinName, new SkinCompatManager.SkinLoaderListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess() {
                AppApplication.prefer.set(SPCst.APP_THEME_VALUE, skinName);
            }

            @Override
            public void onFailed(String errMsg) {
                ToastUtils.make("切换失败，原因：" + errMsg);
            }
        });
    }
}
