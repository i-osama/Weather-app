package com.zeeshan_s.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.zeeshan_s.weatherapp.Api.ApiService;
import com.zeeshan_s.weatherapp.Api.MyRetrofit;
import com.zeeshan_s.weatherapp.Model.Response;
import com.zeeshan_s.weatherapp.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        ApiService apiService = MyRetrofit.getRetrofit().create(ApiService.class);
        Call<Response> callResponse = apiService.weatherResponse();

        callResponse.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.code()==200){
                    Response weatherResponse = response.body();

                    Log.i("TAG", "onResponse:---------- "+ weatherResponse.toString());
                    binding.cityName.setText(weatherResponse.getName());

                    float weatherTemp =(float) (weatherResponse.getMain().getTemp()-273);
                    binding.temperature.setText(""+weatherTemp+"°C");
//                    binding.weather.setText(weatherResponse.getWeather()+"");
                    binding.cloud.setText("Cloud: "+weatherResponse.getClouds().getAll());
                    binding.wind.setText("Wind: "+weatherResponse.getWind().getSpeed());


//                    String minTemp = String.valueOf(weatherResponse.getMain().getTempMin());
//                    binding.wind.setText("Wind: "+ minTemp);
//                    Log.i("TAG", "onResponse: ==="+ weatherResponse.getMain().getTempMin());
//                    binding.humidity.setText("Humi: "+ weatherResponse.getMain().getTempMax());
                    
                    binding.humidity.setText("Humi: "+ weatherResponse.getMain().getHumidity());

//                    binding.timeZone.setText("Timezone: "+weatherResponse.getTimezone());
                    binding.country.setText(", "+weatherResponse.getSys().getCountry());

                    for (int i= 0; i<weatherResponse.getWeather().size();i++){
                        Log.i("TAG", "---Weather: "+ weatherResponse.getWeather().get(i).getMain());
                        binding.weather.setText(weatherResponse.getWeather().get(i).getMain());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }
}