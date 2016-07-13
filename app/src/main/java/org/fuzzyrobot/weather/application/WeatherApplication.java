package org.fuzzyrobot.weather.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by neil on 12/07/2016.
 */
public class WeatherApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        WeatherApplication application = (WeatherApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        refWatcher = LeakCanary.install(this);
    }
}
