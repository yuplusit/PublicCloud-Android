package com.yuplus.publiccloud.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * @user longzhen
 * @date 6/13/2017
 * @desc
 */

public class GalleryPagerAdapter extends PagerAdapter {

    private List<String> mData;

    public GalleryPagerAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return null == mData ? 0 : mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        Glide.with(container.getContext())
                .load(mData.get(position))
                .into(photoView);
        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
