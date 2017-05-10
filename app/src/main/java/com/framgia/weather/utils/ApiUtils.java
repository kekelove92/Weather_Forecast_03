package com.framgia.weather.utils;

/**
 * Created by TungAnh on 5/10/17.
 */
public class ApiUtils {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
