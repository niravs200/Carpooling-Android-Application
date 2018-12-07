package com.example.bhavneet.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DisplayRideDetails extends AppCompatActivity {

    String name;
    String driverName;
    String dcontactNo;
    TextView dName;
    TextView contactNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_accepted);
        /*Retrieve the variable from the previous class to maintain a session */
        Intent in = getIntent();
        name = in.getStringExtra("username");
        driverName = in.getStringExtra("dName");
        dcontactNo = in.getStringExtra("contactNo");

        /*Bind the variables*/
        dName = findViewById(R.id.etname);
        contactNo = findViewById(R.id.etcontact);

        /*Set the values in text view*/
        dName.setText(driverName+' ');
        contactNo.setText(dcontactNo+' ');




        }












    }

