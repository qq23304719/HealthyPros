package com.aorise.yx.healthypro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aorise.yx.healthypro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodNewsFragment extends BaseFragment {


    public FoodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_news, container, false);
    }

    @Override
    public String getTitle() {
        return "食品新闻";
    }
}
