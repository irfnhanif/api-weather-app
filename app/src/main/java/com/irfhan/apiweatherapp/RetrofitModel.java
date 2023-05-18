package com.irfhan.apiweatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitModel {
    @SerializedName("latitude")
    @Expose
    String latitude;

    @SerializedName("longitude")
    @Expose
    String longitude;

    @SerializedName("current_weather")
    @Expose
    CurrentWeather currentWeather;

    @SerializedName("daily")
    @Expose
    Daily daily;

//    public RetrofitModel(String name) {
//        this.superName = name;
//    }
//
//    public String getName() {
//        return superName;
//    }

}

class CurrentWeather {
    @SerializedName("temperature")
    @Expose
    String temperature;

    @SerializedName("windspeed")
    @Expose
    String windspeed;

    @SerializedName("weathercode")
    @Expose
    String weathercode;
}

class Daily {
    @SerializedName("time")
    @Expose
    List<String> time;

    @SerializedName("weathercode")
    @Expose
    List<String> weathercode;
}
