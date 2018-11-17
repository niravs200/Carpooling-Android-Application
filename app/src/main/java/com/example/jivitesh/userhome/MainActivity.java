package com.example.jivitesh.userhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser = (Button)findViewById(R.id.btnUser);

    }

    public void UserHome(View view) {
        startActivity(new Intent(this, UserHomeActivity.class));
    }
}
