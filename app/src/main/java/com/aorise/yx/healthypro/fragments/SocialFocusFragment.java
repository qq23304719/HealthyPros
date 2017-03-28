package com.aorise.yx.healthypro.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.activities.NewsDetailActivity;
import com.aorise.yx.healthypro.adapters.SocialFousAdapter;
import com.aorise.yx.healthypro.baseutils.Utils;
import com.aorise.yx.healthypro.entity.SocialNews;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFocusFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    public SocialFocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_social_focus, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.socialnews_listview);
        String socialfoucs_json = Utils.getJson("socialfocus.json",getActivity());
        Gson gson = new Gson();
        SocialNews socialNews = gson.fromJson(socialfoucs_json, SocialNews.class);
        List<SocialNews.TngouBean> social_news = socialNews.getTngou();
        SocialFousAdapter adapter = new SocialFousAdapter(social_news, getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return ret;
    }

    @Override
    public String getTitle() {
        return "社会热点";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("index",i);
        intent.putExtra("type","sociafocus");
        Log.d("TAG", "onItemClick: "+i+","+l);
        startActivity(intent);
    }
}
