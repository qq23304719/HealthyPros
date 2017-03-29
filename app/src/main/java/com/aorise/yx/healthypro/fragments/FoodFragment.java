package com.aorise.yx.healthypro.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.adapters.VideoFoodAdapter;
import com.aorise.yx.healthypro.entity.VideoFoodEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends BaseFragment {

    private List<VideoFoodEntity> mItems;
    private VideoFoodAdapter mAdapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 888:
                    mAdapter.notifyDataSetChanged();
                    break;
            }

        }
    };
    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        mAdapter = new VideoFoodAdapter(getContext(), mItems);
        String url = "http://api.iclient.ifeng.com/expr_ifengvideoList?page=1&listtype=list&typeid=18";
        getVideoFoodData(url);
    }

    public void getVideoFoodData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200) {
                    String str = response.body().string();
                    if (str != null) {
                        JSONArray jsonArray = null;
                        ArrayList<VideoFoodEntity> items = new ArrayList<>();
                        try {
                            jsonArray = new JSONArray(str);
                            JSONObject jsonObject = jsonArray.optJSONObject(0);
                            JSONArray item = jsonObject.optJSONArray("item");
                            for (int i = 0; i < item.length(); i++) {
                                JSONObject jsonItem = item.optJSONObject(i);
                                VideoFoodEntity foodEntity = new VideoFoodEntity();
                                String title = jsonItem.optString("title");
                                foodEntity.setTitle(title);
                                String image = jsonItem.optString("image");
                                foodEntity.setImage(image);
                                long duration = jsonItem.optLong("duration");
                                foodEntity.setDuration(duration);
                                String video_url = jsonItem.optString("video_url");
                                foodEntity.setVideo_url(video_url);
                                String time = jsonItem.optString("playTime");
                                foodEntity.setPlayTime(time);
                                String commentsall = jsonItem.optString("commentsall");
                                foodEntity.setCommentsall(commentsall);
                                items.add(foodEntity);
                            }
                            mItems.addAll(items);
                            Message message = mHandler.obtainMessage(888);
                            mHandler.sendMessage(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_food, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.video_food_recycler_view);
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
        return "美食视频";
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // mAdapter.destroy();
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
