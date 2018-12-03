package com.example.bhavneet.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Timer;
import java.util.TimerTask;

public class waittime extends Activity {
    String name;

    Boolean flag = false;
    String url = "http://192.168.43.34/carpool/display_ride_details.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waittime);

        Intent in = getIntent();
        name = in.getStringExtra("username");

        url = url+"?name="+name;
        StringRequest wait_request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (!response.equals("false")) {
                        flag = true;

                        String[] output = response.split(",");
                        String dName = output[0];
                        String contactNo = output[1];
                        Intent waitintent = new Intent(waittime.this, DisplayRideDetails.class);
                        waitintent.putExtra("username", name);
                        waitintent.putExtra("dName", dName);
                        waitintent.putExtra("contactNo", contactNo);
                        startActivity(waitintent);

                    } else {

                        Intent waitintent = new Intent(waittime.this, waittime.class);
                        waitintent.putExtra("username", name);
                        startActivity(waitintent);



                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            });
            RequestQueueSingleton.getInstance().getRequestQueue().add(wait_request);

        //Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();




    }
}