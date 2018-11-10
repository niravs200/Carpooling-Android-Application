package com.example.nirav.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nirav.sample.model.Data;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.test);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.5.39:81/carpool_PHP/display.php"; // change IP to your machine IP

        JsonObjectRequest test = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("users");
                    for(int i = 0; i<users.length(); i++){
                        JSONObject user = users.getJSONObject(i);

                        String name = user.getString("name");
                        String phone = user.getString("phone");
                        String password = user.getString("password");

                        a.setText(name + " " + phone +" "+ password);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // Data data = new Gson().fromJson(response.toString(), Data.class);
                //a.setText(data.getUser().get(0).getId());
                //a.setText(data.getUser().get(0).getName());
                //a.setText(data.getUser().get(0).getPhone());
                //a.setText(data.getUser().get(0).getPassword());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No object fetched",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(test);
        //RequestQueue queue = Volley.newRequestQueue(this);
        //queue.add(test);
    }
}
