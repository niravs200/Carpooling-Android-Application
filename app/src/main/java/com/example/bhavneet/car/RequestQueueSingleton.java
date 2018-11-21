package com.example.bhavneet.car;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton extends Application {

    private static RequestQueueSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    @Override
    public void onCreate(){
        super.onCreate();
            //mRequestQueue = Volley.newRequestQueue(this);
            mInstance = this;

    }


    public static synchronized RequestQueueSingleton getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){

        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {getRequestQueue().add(req);}
}
