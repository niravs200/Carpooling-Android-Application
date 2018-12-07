package com.example.bhavneet.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Login extends AppCompatActivity {

    public Button buttonLogin;
    public TextView tvpassword;
    public TextView tvnewuser;
    public Toolbar toolbar;
    public EditText userName;
    public EditText userPassword;
    public boolean result;

    String url = "http://192.168.43.246/carpool/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        tvpassword = findViewById(R.id.forgotpassword);
        tvnewuser = findViewById(R.id.register);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                url = url + "?username="+userName.getText().toString() + "&password="+userPassword.getText().toString();
                StringRequest login_url = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                            if(response.trim().equals("true")){
                                Intent intent1 = new Intent(Login.this, Home.class);
                                intent1.putExtra("username",userName.getText().toString());
                                startActivity(intent1);

                            }
                            else{
                                Toast.makeText(Login.this, "Incorrect Username/Password! Please Check!", Toast.LENGTH_SHORT).show();
                            }


                        }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueueSingleton.getInstance().getRequestQueue().add(login_url);



            }
        });

        tvnewuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

        tvpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Login.this,ForgotPassword.class);
                startActivity(myintent);

            }
        });

    }

}