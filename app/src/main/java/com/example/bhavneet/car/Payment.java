// This file displays payment after the journey is completed

package com.example.bhavneet.car;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    Button buttonOkay;
    String name;
    TextView paymentText;
    Double s_lat;
    Double s_lon;
    Double d_lat;
    Double d_long;
    double payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paymentText = findViewById(R.id.paymentText);

        //
        Intent in = getIntent();
        s_lat = in.getDoubleExtra("userLat",44.6407);
        s_lon = in.getDoubleExtra("userLon",-63.5783);
        d_lat= in.getDoubleExtra("destLat",44.6366);
        d_long = in.getDoubleExtra("destLong",-63.5764);


        Location start = new Location("Point A");
        start.setLongitude(s_lon);
        start.setLatitude(s_lat);

        Location destination = new Location("Point B");
        destination.setLongitude(d_long);
        destination.setLatitude(d_lat);

        double distance = start.distanceTo(destination);
        payment = (distance/1000)*0.7;
        paymentText.setText("Please collect amount $" + payment + " from the pooler");

        buttonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Navigate to home", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(Payment.this, Home.class );
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
