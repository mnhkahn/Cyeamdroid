package com.cyeam.cyeamdroid.doodle;

import android.graphics.Bitmap;

import com.cyeam.cyeamdroid.http.CyeamHttp;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Created by bryce on 14-6-24.
 */
public class Doodle {

    private final static String DOODLE_CYEAM = "http://doodle.cyeam.com/";

    public static void SetDoodle() {

    }

    public static Bitmap GetDoodle() {
        Bitmap doodle = null;

        CyeamHttp.get("", null, new TextHttpResponseHandler() {

        });
        return doodle;
    }
}
