// This file displays payment after the journey is completed

package com.example.jivitesh.payment;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paymentText = findViewById(R.id.paymentText);

        //
        Intent intent = getIntent();
        String payment = intent.getStringExtra("payment");
        name = intent.getStringExtra("payment");

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
