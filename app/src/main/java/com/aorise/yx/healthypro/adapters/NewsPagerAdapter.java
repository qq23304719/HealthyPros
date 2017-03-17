package com.aorise.yx.healthypro.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.aorise.yx.healthypro.fragments.BaseFragment;

import java.util.List;

/**
 * Created by YX-pc on 2016/11/7.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;

    public NewsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment ret = null;
        if (mFragments != null) {
            ret = mFragments.get(position);
        }
        return ret;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mFragments != null) {
            ret = mFragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (mFragments != null) {
            title = mFragments.get(position).getTitle();
        }
        return title;
    }
}
