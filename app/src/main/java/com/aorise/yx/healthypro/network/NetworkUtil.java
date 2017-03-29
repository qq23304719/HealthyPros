package com.aorise.yx.healthypro.network;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Project: org.gyjl.newsproject.network
 * Created by Yasin
 * Date: 2016-11-07.
 */

public final class NetworkUtil {
    private NetworkUtil(){}

    public static NewsService getNewService(String baseUrl){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.baseUrl(baseUrl);
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.newThread()));
        Retrofit retrofit = builder.build();
        return retrofit.create(NewsService.class);
    }
    public static void getDetailJson(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code==200) {
                    ResponseBody body = response.body();
                    String str = body.string();
                    EventBus.getDefault().post(str);
                }
            }
        });
    }
}
