package com.lidong.demo.shuffling_pages;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lidong on 2016/3/8.
 */
public class BannerAdapter extends PagerAdapter {

    private List<ImageView> mImageViews;

    public BannerAdapter(List<ImageView> mImageViews) {
        this.mImageViews = mImageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews.get(position%mImageViews.size()));
        return mImageViews.get(position % mImageViews.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position % mImageViews.size()));
    }
}
