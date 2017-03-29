package com.aorise.yx.healthypro.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.adapters.VideoDuznZiAdapter;
import com.aorise.yx.healthypro.entity.VideoDuanZiItem;
import com.aorise.yx.healthypro.network.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuanZiFragment extends BaseFragment {

    private List<VideoDuanZiItem> mItems;
    private VideoDuznZiAdapter mAdapter;

    public DuanZiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mItems = new ArrayList<>();
        mAdapter = new VideoDuznZiAdapter(getContext(), mItems);
        String url = "http://api.iclient.ifeng.com/expr_ifengvideoList?page=1&listtype=list&typeid=2";
        NetworkUtil.getDetailJson(url);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getVideoData(String str) {
        if (str != null) {
            JSONArray jsonArray = null;
            ArrayList<VideoDuanZiItem> items = new ArrayList<>();
            try {
                jsonArray = new JSONArray(str);
                JSONObject jsonObject = jsonArray.optJSONObject(0);
                JSONArray item = jsonObject.optJSONArray("item");
                for (int i = 0; i < item.length(); i++) {
                    JSONObject jsonItem = item.optJSONObject(i);
                    VideoDuanZiItem duanZiItem = new VideoDuanZiItem();
                    String title = jsonItem.optString("title");
                    duanZiItem.setTitle(title);
                    String image = jsonItem.optString("image");
                    duanZiItem.setImage(image);
                    long duration = jsonItem.optLong("duration");
                    duanZiItem.setDuration(duration);
                    String video_url = jsonItem.optString("video_url");
                    duanZiItem.setVideo_url(video_url);
                    String time = jsonItem.optString("playTime");
                    duanZiItem.setPlayTime(time);
                    String commentsall = jsonItem.optString("commentsall");
                    duanZiItem.setCommentsall(commentsall);
                    items.add(duanZiItem);
                }
                mItems.addAll(items);
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_duan_zi, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.video_duanzi_recycler);
        if (recyclerView != null) {
            RecyclerView.LayoutManager lm = new LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            recyclerView.setLayoutManager(lm);
            //TODO: 创建和设置 RecyclerView的Adapter
            recyclerView.setAdapter(mAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int position = manager.findFirstVisibleItemPosition();
                    mAdapter.stopPlay(position);
                }
            });
        }
        return view;
    }

    @Override
    public String getTitle() {
        return "茶娱饭后";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
     //   mAdapter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (mAdapter != null) {
            if (!isVisibleToUser) {
                mAdapter.stopPlay();
            }
        }
    }
}
