package com.crystal.stetho;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.crystal.stetho.utils.OkHttpClientUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiManager.getInstance().getTestApi().getWeather("北京").enqueue(new retrofit2.Callback<CommonObjectResponse<Weather>>() {
            @Override
            public void onResponse(retrofit2.Call<CommonObjectResponse<Weather>> call, retrofit2.Response<CommonObjectResponse<Weather>> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<CommonObjectResponse<Weather>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "CANCEL...");
        super.onDestroy();
    }

}
