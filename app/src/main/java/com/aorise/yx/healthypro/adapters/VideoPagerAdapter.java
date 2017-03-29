package com.aorise.yx.healthypro.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.aorise.yx.healthypro.fragments.BaseFragment;

import java.util.List;

/**
 * Created by jichunxiao on 2016/11/10.
 */

public class VideoPagerAdapter extends FragmentPagerAdapter {
   private List<BaseFragment> mList;

    public VideoPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
         Fragment ret=null;
        if (mList!=null) {
            ret=mList.get(position);
        }
        return ret;
    }

    @Override
    public int getCount() {
        int ret=0;
        if (mList!=null) {
            ret=mList.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String ret=null;
        if (mList!=null) {
            ret=mList.get(position).getTitle();
        }
        return ret;
    }
}
