package com.example.nainanijher.project_carpool;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class useractivity1 extends AppCompatActivity {

    public Button buttonRequestRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity1);
        final Calendar c= Calendar.getInstance();
        int hourOfDay = c.get(c.HOUR_OF_DAY); //Current Hour
        int minute = c.get(c.MINUTE); //Current Minute
        int second = c.get(c.SECOND); //Current Second
        final TextView tv = (TextView) findViewById(R.id.tv);
        TimePicker tp = (TimePicker) findViewById(R.id.tp);

        tv.setText("Initial Time\n | " + hourOfDay + ":" + minute + ":" + second);

        //Set a TimeChangedListener for TimePicker widget
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //Display the new time to app interface
                tv.setText("Time changed\n | "+hourOfDay + ":" + minute);
            }
        });

        buttonRequestRide=findViewById(R.id.buttonRequestRide);

        buttonRequestRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(useractivity1.this, waittime.class);
                startActivity(myintent);
            }
        });
    }
}




