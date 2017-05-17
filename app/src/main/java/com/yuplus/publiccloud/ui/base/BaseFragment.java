package com.yuplus.publiccloud.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public abstract class BaseFragment extends AbsFragment {
    private View     mContainterView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainterView = inflater.inflate(getLayoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, mContainterView);
        return mContainterView;
    }

    @Override
    protected View findViewById(int id) {
        return mContainterView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}
