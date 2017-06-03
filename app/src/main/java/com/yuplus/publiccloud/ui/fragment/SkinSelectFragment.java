package com.yuplus.publiccloud.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.ui.base.BaseFragment;
import com.yuplus.publiccloud.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public class SkinSelectFragment extends BaseFragment {

    @BindView(R.id.skin_id_red_rb)
    RadioButton  mRedSkinRb;
    @BindView(R.id.skin_id_blue_rb)
    RadioButton  mBlueSkinRb;
    @BindView(R.id.skin_id_green_rb)
    RadioButton  mGreenSkinRb;
    @BindView(R.id.skin_id_dullgrey_rb)
    RadioButton  mDullgreySkinRb;
    @BindView(R.id.skin_id_red_layout)
    LinearLayout mRedSkinLayout;
    @BindView(R.id.skin_id_blue_layout)
    LinearLayout mBlueSkinLayout;
    @BindView(R.id.skin_id_green_layout)
    LinearLayout mGreenSkinLayout;
    @BindView(R.id.skin_id_dullgrey_layout)
    LinearLayout mDullgreySkinLayout;

    private List<RadioButton> mRBList;

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
    }

    @OnClick({R.id.skin_id_red_rb, R.id.skin_id_blue_rb, R.id.skin_id_green_rb, R.id.skin_id_dullgrey_rb
            , R.id.skin_id_red_layout, R.id.skin_id_blue_layout, R.id.skin_id_green_layout, R.id.skin_id_dullgrey_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.skin_id_red_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin("RedSkinStyle.skin");
                break;
            case R.id.skin_id_blue_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin("BlueSkinStyle.skin");
                break;
            case R.id.skin_id_green_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin("GreenSkinStyle.skin");
                break;
            case R.id.skin_id_dullgrey_rb:
                changeButtonStyle(view.getId());
                onSlecetSkin("DullGreySkinStyle.skin");
                break;
            case R.id.skin_id_red_layout:
                changeButtonStyle(mRedSkinRb.getId());
                onSlecetSkin("RedSkinStyle.skin");
                break;
            case R.id.skin_id_blue_layout:
                changeButtonStyle(mBlueSkinRb.getId());
                onSlecetSkin("BlueSkinStyle.skin");
                break;
            case R.id.skin_id_green_layout:
                changeButtonStyle(mGreenSkinRb.getId());
                onSlecetSkin("GreenSkinStyle.skin");
                break;
            case R.id.skin_id_dullgrey_layout:
                changeButtonStyle(mDullgreySkinRb.getId());
                onSlecetSkin("DullGreySkinStyle.skin");
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

    private void onSlecetSkin(String skinName) {
        SkinManager.getInstance().loadSkin(skinName, new SkinLoaderListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess() {
                //((BaseActivity) getActivity()).setStatusBarTintColor(R.color.colorPrimary);
            }

            @Override
            public void onFailed(String errMsg) {
                ToastUtils.make("切换失败，原因：" + errMsg);
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }
}
