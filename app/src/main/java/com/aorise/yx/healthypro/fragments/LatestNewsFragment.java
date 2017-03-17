package com.aorise.yx.healthypro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aorise.yx.healthypro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsFragment extends BaseFragment {


    private ListView mListView;

    public LatestNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_latest_news, container, false);
        mListView = (ListView) ret.findViewById(R.id.latestnews_listview);

        return ret;
    }

    @Override
    public String getTitle() {
        return "最新资讯";
    }
}
