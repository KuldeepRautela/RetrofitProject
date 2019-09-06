package com.example.kuldeeprautela.retrofitproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Kuldeep Rautela on 9/3/2019.
 */

public interface FeedApi {
    @GET("users/{user}")
    Call<Details> getFeed(@Path("user") String user);
}
