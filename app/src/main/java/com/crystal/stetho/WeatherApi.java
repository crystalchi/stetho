package com.crystal.stetho;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface  WeatherApi {

    @Headers({"apikey:dfd10cc8bddc85581d56121a3f93dcfd"})
    @GET("apistore/weatherservice/cityname")
    Call<CommonObjectResponse<Weather>> getWeather(@Query("cityname") String cityName);
}
