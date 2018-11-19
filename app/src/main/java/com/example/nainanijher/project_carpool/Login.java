package com.example.nainanijher.project_carpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class Login extends AppCompatActivity {

    public Button buttonLogin;
    public TextView tvpassword;
    public TextView tvnewuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        buttonLogin = findViewById(R.id.buttonLogin);
        tvpassword = findViewById(R.id.forgotpassword);
        tvnewuser = findViewById(R.id.register);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1 = new Intent(Login.this, MainActivity.class);
                startActivity(intent1);

            }
        });

        tvpassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent2);
            }
        });

        tvnewuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });


    }
}