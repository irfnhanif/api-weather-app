package com.irfhan.apiweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyWeatherActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private TextView tvCondition, tvWindSpeed, tvTemperature, tvLocation, tvCoordinate;
    private ImageView ivWeatherIcon;

    private String url = "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);

        tvCondition = findViewById(R.id.tv_condition);
        tvWindSpeed = findViewById(R.id.tv_windspeed);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvLocation = findViewById(R.id.tv_location);
        tvCondition = findViewById(R.id.tv_condition);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, this, this);
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            String longitude = response.getString("longitude");
            String latitude = response.getString("latitude");
            JSONObject currentWeather = response.getJSONObject("current_weather");
            String temperature = currentWeather.getString("temperature");
            String windSpeed = currentWeather.getString("windspeed");
            int weatherCode = currentWeather.getInt("weathercode");

            switch (weatherCode) {
                case 0:
                    tvCondition.setText("Clear sky");

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}