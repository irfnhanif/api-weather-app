package com.irfhan.apiweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VolleyWeatherActivity extends AppCompatActivity {

    private TextView tvCondition, tvWindSpeed, tvTemperature, tvLocation, tvCoordinate;
    private ImageView ivWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);

        tvCondition = findViewById(R.id.tv_condition);
        tvWindSpeed = findViewById(R.id.tv_windspeed);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvLocation = findViewById(R.id.tv_location);
        tvCondition = findViewById(R.id.tv_condition);


    }
}