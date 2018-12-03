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
    String driverName;
    String dcontactNo;
    //String url = "http://172.20.10.2/carpool/display_ride_details.php";
    TextView dName;
    TextView contactNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_accepted);
        Intent in = getIntent();
        name = in.getStringExtra("username");
        driverName = in.getStringExtra("dName");
        dcontactNo = in.getStringExtra("contactNo");
        dName = findViewById(R.id.etname);
        contactNo = findViewById(R.id.etcontact);

        dName.setText(driverName+' ');
        contactNo.setText(dcontactNo+' ');




        }












    }

