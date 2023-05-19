package com.irfhan.apiweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitWeatherActivity extends AppCompatActivity {

    private TextView tvCondition, tvWindSpeed, tvTemperature, tvLocation, tvCoordinate;
    private TextView tvDate1, tvDate2, tvDate3, tvDate4, tvDate5, tvDate6, tvDate7;
    private ImageView ivWeatherIcon, ivWeatherDate1, ivWeatherDate2, ivWeatherDate3, ivWeatherDate4, ivWeatherDate5, ivWeatherDate6, ivWeatherDate7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_weather_layout);

        tvCondition = findViewById(R.id.tv_condition);
        tvWindSpeed = findViewById(R.id.tv_windspeed);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvLocation = findViewById(R.id.tv_location);
        tvCondition = findViewById(R.id.tv_condition);
        tvCoordinate = findViewById(R.id.tv_coordinate);
        ivWeatherIcon = findViewById(R.id.iv_weather_icon);

        tvDate1 = findViewById(R.id.tv_date1);
        tvDate2 = findViewById(R.id.tv_date2);
        tvDate3 = findViewById(R.id.tv_date3);
        tvDate4 = findViewById(R.id.tv_date4);
        tvDate5 = findViewById(R.id.tv_date5);
        tvDate6 = findViewById(R.id.tv_date6);
        tvDate7 = findViewById(R.id.tv_date7);

        ivWeatherDate1 = findViewById(R.id.iv_weather1);
        ivWeatherDate2 = findViewById(R.id.iv_weather2);
        ivWeatherDate3 = findViewById(R.id.iv_weather3);
        ivWeatherDate4 = findViewById(R.id.iv_weather4);
        ivWeatherDate5 = findViewById(R.id.iv_weather5);
        ivWeatherDate6 = findViewById(R.id.iv_weather6);
        ivWeatherDate7 = findViewById(R.id.iv_weather7);

        getWeatherData();
    }

    private void getWeatherData() {
        Call<RetrofitModel> call = RetrofitClient.getInstance()
                .getRetrofitApi().getWeatherData();
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
            RetrofitModel weatherList = response.body();

            String longitude = weatherList.longitude;
            String latitude = weatherList.latitude;
            String temperature = weatherList.currentWeather.temperature;
            String windSpeed = weatherList.currentWeather.windspeed;
            String weatherCodeString = weatherList.currentWeather.weathercode;

            int weatherCode = Integer.parseInt(weatherCodeString);
            String coordinate = latitude + ", " + longitude;

            tvCondition.setText(getConditionText(weatherCode));
            ivWeatherIcon.setImageResource(getWeatherIconResource(weatherCode));
            tvWindSpeed.setText(windSpeed);
            tvTemperature.setText(temperature);
            tvLocation.setText("Malang, East Java");
            tvCoordinate.setText(coordinate);

            List<String> timeArray = weatherList.daily.time;
            List<String> weatherCodesStringArray = weatherList.daily.weathercode;
            List<Integer> weatherCodesIntegerArray = new ArrayList<>();

            for (int j = 0; j < weatherCodesStringArray.size(); j++) {
                weatherCodesIntegerArray.add(Integer.parseInt(weatherCodesStringArray.get(j)));
            }

            ivWeatherDate1.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(0)));
            ivWeatherDate2.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(1)));
            ivWeatherDate3.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(2)));
            ivWeatherDate4.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(3)));
            ivWeatherDate5.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(4)));
            ivWeatherDate6.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(5)));
            ivWeatherDate7.setImageResource(getWeatherIconResource(weatherCodesIntegerArray.get(6)));

            tvDate1.setText(timeArray.get(0));
            tvDate2.setText(timeArray.get(1));
            tvDate3.setText(timeArray.get(2));
            tvDate4.setText(timeArray.get(3));
            tvDate5.setText(timeArray.get(4));
            tvDate6.setText(timeArray.get(5));
            tvDate7.setText(timeArray.get(6));
            }

            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {

            }
        });
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