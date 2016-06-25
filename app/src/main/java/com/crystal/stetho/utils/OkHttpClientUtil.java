package com.crystal.stetho.utils;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by crystal on 2016/5/18 0018.
 */
public class OkHttpClientUtil {

    private static final boolean DEBUG = true;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClientUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;



    private OkHttpClientUtil(){
        mOkHttpClient = new OkHttpClient();
        mDelivery = new Handler(Looper.getMainLooper());
        if(DEBUG){
            mOkHttpClient.networkInterceptors().add(new StethoInterceptor());
        }
    }

    public static OkHttpClientUtil getInstance(){
        if(mInstance == null){
            synchronized (OkHttpClientUtil.class){
                if(mInstance == null){
                    mInstance = new OkHttpClientUtil();
                }
            }
        }
        return mInstance;
    }

    public void get(String url, final ResponseCallback responseCallback){
        Request request = new Request.Builder().url(url).build();
        deliveryRequest(request, responseCallback);
    }

    public void post(String url, String paramJson, final ResponseCallback responseCallback){
        RequestBody requestBody = RequestBody.create(JSON, paramJson);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        deliveryRequest(request, responseCallback);
    }

    private void deliveryRequest(final Request request, final ResponseCallback responseCallback){
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        if(responseCallback != null){
                            responseCallback.onFailure(e);
                        }
                    }
                });

            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                //success(responseCallback, response);
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        if(responseCallback != null){
                            responseCallback.onResponse(response);
                        }
                    }
                });
            }
        });
    }


    public void cancelAll(){
        mOkHttpClient.dispatcher().cancelAll();
    }

    public void cancelTag(Object tag){
        List<Call> queuedCalls = mOkHttpClient.dispatcher().queuedCalls();
        for(Call call : queuedCalls){
            if(tag.equals(call.request().tag())){
                call.cancel();
            }
        }
        List<Call> runningCalls = mOkHttpClient.dispatcher().runningCalls();
        for(Call call : runningCalls){
            if(tag.equals(call.request().tag())){
                call.cancel();
            }
        }
    }

    public interface ResponseCallback{

        public void onFailure(Exception e);
        public void onResponse(Response response);
    }
}
