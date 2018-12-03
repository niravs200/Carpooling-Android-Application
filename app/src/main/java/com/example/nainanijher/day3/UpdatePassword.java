package com.example.nainanijher.day3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdatePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Button btnReset=findViewById(R.id.buttonReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(UpdatePassword.this,"Your Password has been updated! You are being re-directed to Login Page.",Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(UpdatePassword.this, Login.class);
                startActivity(myintent);
            }
        });
    }
}
