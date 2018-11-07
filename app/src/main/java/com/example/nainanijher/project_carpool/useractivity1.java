package com.example.nainanijher.project_carpool;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class useractivity1 extends AppCompatActivity {

    public Button buttonRequestRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity1);

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




