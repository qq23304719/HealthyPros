package com.aorise.yx.healthypro.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.adapters.VideoPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_video, container, false);
        TabLayout tabLayout = (TabLayout) ret.findViewById(R.id.video_tablayout);
        ViewPager viewPager = (ViewPager) ret.findViewById(R.id.video_viewpager);
        List<BaseFragment> fragmentList = new ArrayList<>();
        AmusementFragment amusementFragment = new AmusementFragment();
        fragmentList.add(amusementFragment);
        DuanZiFragment duanZiFragment = new DuanZiFragment();
        fragmentList.add(duanZiFragment);
        FoodFragment foodFragment = new FoodFragment();
        fragmentList.add(foodFragment);
        ZongYiFragment liveFragment = new ZongYiFragment();
        fragmentList.add(liveFragment);
        VideoPagerAdapter adapter = new VideoPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return ret;
    }

}
