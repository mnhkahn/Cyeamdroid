package com.cyeam.cyeamdroid.app;

import com.baidu.frontia.FrontiaApplication;

public class CyeamApplication extends FrontiaApplication {
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
