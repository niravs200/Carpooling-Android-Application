package com.example.nainanijher.project_carpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;


public class Register extends AppCompatActivity {

    public Button btnregister;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        img = findViewById(R.id.btn);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(Register.this, Login.class);
                startActivity(myintent);
            }
        });

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
           // actionBar.setDisplayHomeAsUpEnabled(true);



        btnregister = findViewById(R.id.buttonRegister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });



    }

    //public boolean onOptionsItemSelected(MenuItem item){
        //switch (item.getItemId()) {
            //case android.R.id.home:
                //finish();
                //return true;
        //}
        //return super.onOptionsItemSelected(item);
    //}

    //public boolean onCreateOptionsMenu(Menu menu) {
        //return true;
    //}
}
