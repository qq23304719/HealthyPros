package com.aorise.yx.healthypro.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Project: org.gyjl.newsproject.network
 * Created by Yasin
 * Date: 2016-11-07.
 */
public interface NewsService {
    @GET("ClientNews")
    Observable<String> getJsonString(@Query("id") String id, @Query("page") int page);

    @GET("read.php")
    Observable<String> getFindNews(@Query("uid") String uid);
}
