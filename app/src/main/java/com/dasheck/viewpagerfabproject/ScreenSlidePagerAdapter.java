package com.dasheck.viewpagerfabproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by das heck on 27.06.2015.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new Fragment[] {
                ScreenSlidePagerFragment.newInstance(0),
                ScreenSlidePagerFragment.newInstance(1),
                ScreenSlidePagerFragment.newInstance(2),
                ScreenSlidePagerFragment.newInstance(3),
                ScreenSlidePagerFragment.newInstance(4),
                ScreenSlidePagerFragment.newInstance(5),
                ScreenSlidePagerFragment.newInstance(6),
                ScreenSlidePagerFragment.newInstance(7),
                ScreenSlidePagerFragment.newInstance(8),
        };
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
