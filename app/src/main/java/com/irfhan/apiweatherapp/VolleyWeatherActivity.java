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

            tvCondition.setText(getConditionText(weatherCode));
            ivWeatherIcon.setImageResource(getWeatherIconResource(weatherCode));
            tvWindSpeed.setText(windSpeed);
            tvTemperature.setText(temperature);
            tvLocation.setText("Malang, East Java");
            tvCoordinate.setText(longitude + ", " + latitude);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private String getConditionText(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return "Clear sky";
            case 1:
                return "Mainly Clear";
            case 2:
                return "Partly Cloudy";
            case 3:
                return "Overcast";
            case 45:
                return "Fog";
            case 48:
                return "Depositing Rime Fog";
            case 51:
                return "Drizzle: Light";
            case 53:
                return "Drizzle: Moderate";
            case 55:
                return "Drizzle: Heavy";
            case 61:
                return "Rain: Slight";
            case 63:
                return "Rain: Moderate";
            case 65:
                return "Rain: Heavy";
            case 80:
                return "Rain Showers: Slight";
            case 81:
                return "Rain Showers: Moderate";
            case 82:
                return "Rain Showers: Heavy";
            case 95:
                return "Thunderstorm";
            default:
                return "Unknown";
        }
    }


    private int getWeatherIconResource(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return R.drawable.sun;
            case 1:
            case 2:
            case 3:
                return R.drawable.partly_cloudy_day;
            case 45:
            case 48:
                return R.drawable.fog;
            case 51:
            case 53:
            case 55:
                return R.drawable.light_rain;
            case 61:
            case 63:
            case 65:
                return R.drawable.rain;
            case 80:
            case 81:
            case 82:
                return R.drawable.rainy_weather;
            case 95:
                return R.drawable.storm;
            default:
                return R.drawable.unknown_icon;
        }
    }

}