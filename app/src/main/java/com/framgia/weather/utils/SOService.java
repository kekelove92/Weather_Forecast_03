package com.framgia.weather.utils;

import com.framgia.weather.model.day.SOAnswersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TungAnh on 5/10/17.
 */
public interface SOService {
    @GET("weather")
    Call<SOAnswersResponse> getAnswersCurrentDay(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("appid") String appid);
    @GET("forecast")
    Call<SOAnswersResponse> getAnswersWeekDay(@Query("lat") String lat,
                                              @Query("lon") String lon,
                                              @Query("appid") String appid);
}
