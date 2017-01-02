package com.example.kamal.smartalarm;

/**
 * Created by g588969 on 22/12/2016.
*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    private MyLinearLayout cur = null;
    private MyLinearLayout next = null;
    private Carousel context;
    private FragmentManager fm;
    private float scale;

    public MyPagerAdapter(Carousel context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        if (position == Carousel.FIRST_PAGE)
            scale = BIG_SCALE;
        else
            scale = SMALL_SCALE;

        position = position % Carousel.PAGES;
        return MyFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        return Carousel.PAGES * Carousel.LOOPS;
    }

    @Override
    public void transformPage(View page, float position) {
        MyLinearLayout myLinearLayout = (MyLinearLayout)page.findViewById(R.id.root);
        float scale = BIG_SCALE;
        if (position > 0) {
            scale = scale - position * DIFF_SCALE;
        } else {
            scale = scale + position * DIFF_SCALE;
        }
        if (scale < 0) scale = 0;
        myLinearLayout.setScaleBoth(scale);
    }
}