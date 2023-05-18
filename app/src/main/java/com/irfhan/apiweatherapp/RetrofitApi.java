package com.irfhan.apiweatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    String BASE_URL = "https://api.open-meteo.com/v1/";
    @GET("forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto")
    Call<List< RetrofitModel >> getWeatherData();
}
