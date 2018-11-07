package com.example.nainanijher.project_carpool;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    public Button buttonRequestRide;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);

        buttonRequestRide=findViewById(R.id.buttonRequestRide);

        buttonRequestRide.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myintent = new Intent(Register.this, Login.class);
            startActivity(myintent);
        }
    });
}
}
