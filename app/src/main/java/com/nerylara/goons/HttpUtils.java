package com.nerylara.goons;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.HttpEntity;


public class HttpUtils {

    private static final String BASE_URL = "http://b67d962f.ngrok.io/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), entity, contentType,  responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, contentType,  responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
