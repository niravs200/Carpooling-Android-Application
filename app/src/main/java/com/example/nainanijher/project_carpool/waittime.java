package com.example.nainanijher.project_carpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class waittime extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waittime);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent waitintent=new Intent(waittime.this,ride_details.class);
                startActivity(waitintent);
                finish();
            }
        },10000);

    }
}