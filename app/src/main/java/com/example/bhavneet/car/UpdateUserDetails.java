package com.example.bhavneet.car;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class UpdateUserDetails extends AppCompatActivity {

    TextView name;
    String driverName;
    public UpdateUserDetails(TextView name, String driverName) {
        this.name = name;
        this.driverName = driverName;

    }


    public void execute(){


        String url = "http://192.168.43.246/carpool/insert_list.php?name=" + name.getText().toString()+"&driverName=" + driverName;


        JsonObjectRequest test1 = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        RequestQueueSingleton.getInstance().getRequestQueue().add(test1);
    }
}
