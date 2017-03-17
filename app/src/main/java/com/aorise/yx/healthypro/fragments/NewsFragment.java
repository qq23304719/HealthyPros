package com.aorise.yx.healthypro.fragments;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.adapters.NewsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_news, container, false);
//        BoomMenuButton bmb = (BoomMenuButton) ret.findViewById(R.id.hambutton);
        TabLayout news_tab = (TabLayout) ret.findViewById(R.id.news_tablayout);
        ViewPager viewPager = (ViewPager) ret.findViewById(R.id.news_viewpager);
        List<BaseFragment> fragments = new ArrayList<>();
        LatestNewsFragment mHeadlineFragment = new LatestNewsFragment();
        fragments.add(mHeadlineFragment);
        SocialFocusFragment amusementFragment = new SocialFocusFragment();
        fragments.add(amusementFragment);
        FoodNewsFragment sportFragment = new FoodNewsFragment();
        fragments.add(sportFragment);
        IllnessNewsFragment scienceFragment = new IllnessNewsFragment();
        fragments.add(scienceFragment);
        DrugNewsFragment drugNewsFragment = new DrugNewsFragment();
        fragments.add(drugNewsFragment);
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        news_tab.setupWithViewPager(viewPager);
        return ret;
    }

}
