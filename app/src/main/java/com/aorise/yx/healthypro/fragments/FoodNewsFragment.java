package com.aorise.yx.healthypro.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.activities.NewsDetailActivity;
import com.aorise.yx.healthypro.adapters.SocialFousAdapter;
import com.aorise.yx.healthypro.baseutils.Utils;
import com.aorise.yx.healthypro.entity.News;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodNewsFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    public FoodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_food_news, container, false);
        ListView listView = (ListView) ret.findViewById(R.id.foodnews_listview);
        String socialfoucs_json = Utils.getJson("foodnews.json",getActivity());
        Gson gson = new Gson();
        News socialNews = gson.fromJson(socialfoucs_json, News.class);
        List<News.TngouBean> social_news = socialNews.getTngou();
        SocialFousAdapter adapter = new SocialFousAdapter(social_news, getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return ret;
    }

    @Override
    public String getTitle() {
        return "食品新闻";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("index",i);
        intent.putExtra("type","foodnews");
        Log.d("TAG", "onItemClick: "+i+","+l);
        startActivity(intent);
    }
}
