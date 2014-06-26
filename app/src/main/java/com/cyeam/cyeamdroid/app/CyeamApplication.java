package com.cyeam.cyeamdroid.app;

import android.app.Application;

public class CyeamApplication extends Application/*FrontiaApplication */ {
    private static CyeamApplication instance;

    public static CyeamApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
