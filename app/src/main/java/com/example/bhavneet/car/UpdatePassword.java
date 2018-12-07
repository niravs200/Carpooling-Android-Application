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

public class UpdatePassword extends AppCompatActivity {

    public Button btnReset;

    public EditText newPassword;
    public EditText confirmPassword;
    String userName;
    String url = "http://192.168.43.246/carpool/confirmPassword.php";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepassword);

        Intent in = getIntent();
        userName = in.getStringExtra("name");
        btnReset=findViewById(R.id.buttonReset);
        newPassword = findViewById(R.id.newpassword);
        confirmPassword = findViewById(R.id.confirmpassword);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = url + "?username=" + userName + "&password=" + newPassword.getText().toString();
                if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(UpdatePassword.this, "Here", Toast.LENGTH_SHORT).show();
                    StringRequest update_url = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(UpdatePassword.this, "Password has been updated" , Toast.LENGTH_SHORT).show();
                            Intent myintent = new Intent(UpdatePassword.this, Login.class);
                            startActivity(myintent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    RequestQueueSingleton.getInstance().getRequestQueue().add(update_url);
                }
                else{
                    Toast.makeText(UpdatePassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

