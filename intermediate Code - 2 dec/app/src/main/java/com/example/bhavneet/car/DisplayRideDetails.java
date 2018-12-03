package com.example.bhavneet.car;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class DisplayRideDetails extends AppCompatActivity {

    String name;
    String url = "http://192.168.2.12/carpool/display_ride_details.php";
    TextView dName;
    TextView contactNo;
    TextView pickupLocation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_accepted);
        Intent in = getIntent();
        name = in.getStringExtra("username");
        dName = findViewById(R.id.etname);
        contactNo = findViewById(R.id.etcontact);
        pickupLocation = findViewById(R.id.etpickup);

        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        url = url+"?name="+name;
        //Toast.makeText(DisplayRideDetails.this, url, Toast.LENGTH_SHORT).show();
        StringRequest display_url = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    //Toast.makeText(DisplayRideDetails.this, "yeah", Toast.LENGTH_SHORT).show();
                        //dName.setText(response);

                        String[] output = response.split(",");
                        dName.setText(output[0]+' ');
                        contactNo.setText(output[1]+' ');




                }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                RequestQueueSingleton.getInstance().getRequestQueue().add(display_url);
       // timer.schedule(task, 0, 60000); // 60000 is time in ms










    }
}
