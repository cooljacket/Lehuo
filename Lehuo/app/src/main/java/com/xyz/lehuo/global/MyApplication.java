package com.xyz.lehuo.global;

import android.app.Application;

/**
 * Created by xyz on 15/12/5.
 */
public class MyApplication extends Application {

    private ActivityCollector activityCollector;

    @Override
    public void onCreate() {
        super.onCreate();
        activityCollector = new ActivityCollector();
    }

    public ActivityCollector getActivityCollector() {
        return activityCollector;
    }
}
