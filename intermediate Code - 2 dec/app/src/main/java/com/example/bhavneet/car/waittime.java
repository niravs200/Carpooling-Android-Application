package com.example.bhavneet.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class waittime extends Activity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waittime);

        Intent in = getIntent();
        name = in.getStringExtra("username");
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent waitintent=new Intent(waittime.this,DisplayRideDetails.class);
                waitintent.putExtra("username",name);
                startActivity(waitintent);
                finish();
            }
        },1000);

    }
}