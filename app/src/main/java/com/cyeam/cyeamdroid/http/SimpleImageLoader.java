package com.cyeam.cyeamdroid.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bryce on 14-6-29.
 */
public class SimpleImageLoader {

    public static Bitmap getBitmap(String urlStr) throws IOException {
        Bitmap bitmap;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(5*1000);
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        bitmap = BitmapFactory.decodeStream(is);
        is.close();
        return bitmap;
    }
}