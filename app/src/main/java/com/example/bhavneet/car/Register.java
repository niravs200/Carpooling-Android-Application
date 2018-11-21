package com.example.bhavneet.car;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public Button btnregister;
    public EditText name;
    public EditText password;
    public EditText contactNo;
    String url = "http://192.168.43.9/carpool/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etname);
        password = findViewById(R.id.etPassword);
        contactNo = findViewById(R.id.etContact);

        btnregister = findViewById(R.id.buttonRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                validateUserData();

                /*HashMap<String,String> params = new HashMap<String,String>();
                params.put("name",name.getText().toString());
                params.put("password",password.getText().toString());
                params.put("contact",contactNo.getText().toString());

                JSONObject jsonObj = new JSONObject(params);*/


                Intent intent = new Intent(Register.this, Login.class);

                startActivity(intent);
            }
        });
    }

    private void validateUserData(){
        url = url + "?name="+name.getText().toString();
        JsonObjectRequest insert_url = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueueSingleton.getInstance().getRequestQueue().add(insert_url);
    }
}