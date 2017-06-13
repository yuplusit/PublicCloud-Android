package com.yuplus.publiccloud.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuplus.cloudsdk.util.ListUtils;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.ui.adapter.GalleryPagerAdapter;
import com.yuplus.publiccloud.ui.base.BaseActivity;
import com.yuplus.publiccloud.ui.widget.PhotoViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * @user longzhen
 * @date 6/13/2017
 * @desc
 */

public class GalleryImagesActivity extends BaseActivity {
    @BindView(R.id.common_id_back)
    ImageView      mBackIv;
    @BindView(R.id.common_id_title)
    TextView       mTitleTv;
    @BindView(R.id.gallery_id_photoview)
    PhotoViewPager mPhotoViewPager;
    @BindView(R.id.gallery_id_current_position)
    TextView       mCurrentPositionTv;
    @BindView(R.id.gallery_id_all_count)
    TextView       mImagesAllCountTv;
    @BindView(R.id.gallery_id_dig_layout)
    LinearLayout   mImagesDigLl;

    private String       mTitle;
    private List<String> mImageUrlList;
    private int          mSelectPosition;

    private GalleryPagerAdapter mGalleryPagerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gallery_images;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mTitle = getIntent().getStringExtra(AppCst.COMMON_TITLE);
        mImageUrlList = getIntent().getStringArrayListExtra(AppCst.COMMON_DATA);
        mSelectPosition = getIntent().getIntExtra(AppCst.COMMON_KEY, 0);
        if (ListUtils.isEmpty(mImageUrlList)) {
            finish();
            return;
        }
        if (StringUtils.isNotBlank(mTitle)) {
            mTitleTv.setText(mTitle);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        if (mImageUrlList.size() <= 1) {
            mImagesDigLl.setVisibility(View.GONE);
        } else {
            mImagesDigLl.setVisibility(View.VISIBLE);
        }
        mImagesAllCountTv.setText(String.valueOf(mImageUrlList.size()));
        mGalleryPagerAdapter = new GalleryPagerAdapter(mImageUrlList);
        mPhotoViewPager.setAdapter(mGalleryPagerAdapter);
        mPhotoViewPager.setCurrentItem(mSelectPosition);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mPhotoViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentPositionTv.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
