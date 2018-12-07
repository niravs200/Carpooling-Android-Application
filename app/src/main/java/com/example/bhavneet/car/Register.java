package com.example.bhavneet.car;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    public Button btnregister;
    public EditText name;
    public EditText password;
    public EditText confirmPassword;
    public EditText contactNo;
    String url = "http://192.168.43.246/carpool/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etname);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        contactNo = findViewById(R.id.etContact);

        btnregister = findViewById(R.id.buttonRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if((password.getText().toString().equals(confirmPassword.getText().toString())) && Patterns.PHONE.matcher(contactNo.getText().toString()).matches()) {
                    validateUserData();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Register.this, "Please check the details entered!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void validateUserData(){
        url = url + "?name="+name.getText().toString()+"&password="+password.getText().toString()+"&contactNo="+contactNo.getText().toString();
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