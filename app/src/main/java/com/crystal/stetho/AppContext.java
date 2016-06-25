package com.crystal.stetho;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.InspectorModulesProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class AppContext extends Application{

    private static final String TAG = AppContext.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        long startTime = SystemClock.elapsedRealtime();
        Stetho.initializeWithDefaults(this);
        long elapsed = SystemClock.elapsedRealtime() - startTime;
        Log.i(TAG, "Stetho initialized in " + elapsed + " ms");
    }




}
