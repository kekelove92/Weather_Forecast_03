package com.framgia.weather.utils;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TungAnh on 5/9/17.
 */
public class APIManager {
    //"http://samples.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon +"&appid=6ed471cd6369c2262e12fa117e0f28e3";
    private static final String URL = "http://api.openweathermap.org/data/2.5";

    public interface WeatherAPI {
        @GET("/forecast")
        void getWeatherWeekInfo(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String appid
        );
    // http://api.openweathermap.org/data/2.5/weather?lat=21.0244764&lon=105.9024692&appid=0634efb262d9398a09fc2693c5120589
        @GET("/weather")
        void getWeatherCurrentInfo(
          @Query("lat") String latitude,
          @Query("lon") String longitude,
          @Query("appid") String appid
        );
    }


}
