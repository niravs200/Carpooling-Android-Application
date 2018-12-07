package com.example.bhavneet.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class ForgotPassword extends AppCompatActivity {
    public EditText userName;
    public Button btnNext;
    public EditText enterCode;
    String url = "http://192.168.43.246/carpool/sms.php";
    StringBuffer otp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        btnNext=findViewById(R.id.buttonNext);
        enterCode = findViewById(R.id.enterCode);
        userName = findViewById(R.id.userName);
        enterCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    url = url + "?username=" + userName.getText().toString();
                    StringRequest sms_url = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            otp = new StringBuffer(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ForgotPassword.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueueSingleton.getInstance().getRequestQueue().add(sms_url);
                }
            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp.toString().equals(enterCode.getText().toString())){

                        Intent myintent = new Intent(ForgotPassword.this, UpdatePassword.class);
                        myintent.putExtra("name",userName.getText().toString());
                        startActivity(myintent);
                } else {
                        Toast.makeText(ForgotPassword.this, "Please re-enter the OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
