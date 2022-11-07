package com.zeeshan_s.weatherapp.Api;

import com.zeeshan_s.weatherapp.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("weather?q=dhaka&appid=76ef2405853f9f12202436a3997fc2ee")
    Call<Response> weatherResponse();
}
