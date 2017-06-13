package com.yuplus.publiccloud.ui.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuplus.publiccloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public abstract class TitleActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    ViewStub mTbTitle;
    @BindView(R.id.toolbar_menu)
    ViewStub mMenuViewStub;

    private int mShowHomeAsUpRes;
    private boolean mShowTextTitle = true;
    private TextView     mTitleTv;
    private LinearLayout mMenuLayout;

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (null != mTitleTv) {
            mTitleTv.setText(title);
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        //setToolbarDefaultBackground();
    }

    public void setToolbarDefaultBackground() {
        setToolbarBackground(0);
    }

    public void setToolbarBackground(final int resId) {
        if (resId > 0) {
            getToolbar().setBackgroundColor(getResources().getColor(resId));
        } else {
            getToolbar().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void setShowHomeBackDisabled() {
        setShowHomeAsUpRes(0, null);
    }

    public void setShowHomeBack() {
        setShowHomeAsUpRes(R.drawable.ic_common_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setShowHomeAsUpRes(final int showHomeAsUpRes, final View.OnClickListener listener) {
        this.mShowHomeAsUpRes = showHomeAsUpRes;
        if (mShowHomeAsUpRes > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getToolbar().setNavigationIcon(showHomeAsUpRes);
            if (null != listener) {
                getToolbar().setNavigationOnClickListener(listener);
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void setShowNoTitle() {
        if (null != mTbTitle) {
            mTbTitle.setVisibility(View.GONE);
        }
    }

    public void setShowTitle(final boolean showTextTitle) {
        this.mShowTextTitle = showTextTitle;

        if (null != mTbTitle) {
            mTitleTv = ButterKnife.findById(mTbTitle.inflate(), R.id.toolbar_title_common);
        }
    }

    public void setMenuView(View view) {
        if (null != view) {
            if (null == mMenuLayout) {
                mMenuLayout = ButterKnife.findById(mMenuViewStub.inflate(), R.id.toolbar_menu_common);
            }
            mMenuLayout.addView(view);
        }
    }

    public void setmMenuVisibility(int visibility) {
        if (null != mMenuLayout) {
            mMenuLayout.setVisibility(visibility);
        }
    }

    private void showTextTitle() {
        if (null != mTbTitle) {
            mTbTitle.setVisibility(View.VISIBLE);
        }
    }

}
