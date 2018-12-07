package com.example.bhavneet.car;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton extends Application {

    private static RequestQueueSingleton mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
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

}
