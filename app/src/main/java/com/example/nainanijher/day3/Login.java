package com.example.nainanijher.day3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import android.support.v7.app.ActionBar;

public class Login extends AppCompatActivity {

    public Button buttonLogin;
    public TextView tvpassword;
    public TextView tvnewuser;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        buttonLogin = findViewById(R.id.buttonLogin);
        tvpassword = findViewById(R.id.forgotpassword);
        tvnewuser = findViewById(R.id.register);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1 = new Intent(Login.this, Home.class);
                startActivity(intent1);

            }
        });

       /* tvpassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent2);
            }
        });
*/
        tvnewuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

    }
}