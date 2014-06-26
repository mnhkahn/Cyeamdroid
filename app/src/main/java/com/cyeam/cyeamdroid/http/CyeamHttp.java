package com.cyeam.cyeamdroid.http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpEntity;

public class CyeamHttp {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity,
                            String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity, contentType, responseHandler);
    }

}