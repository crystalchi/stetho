package com.crystal.stetho;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class ApiManager {

    private static final String BASE_URL = "http://apis.baidu.com/";
    private static ApiManager instance;

    private WeatherApi mWeatherApi;
    private Retrofit mRetrofit;

    private ApiManager() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public WeatherApi getTestApi() {
        if (mWeatherApi == null) {
            mWeatherApi = mRetrofit.create(WeatherApi.class);
        }
        return mWeatherApi;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }
}
