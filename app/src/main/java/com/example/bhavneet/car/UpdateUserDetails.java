package com.example.bhavneet.car;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserDetails extends AppCompatActivity {

    TextView name;
    RequestQueue requestQueue;
    public UpdateUserDetails(TextView name) {
        this.name = name;
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poolerlist);
    }
*/

    public void execute(){

        //requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.12/carpool/insert_list.php?name=" + name.getText().toString();

        //HashMap<String,String> params = new HashMap<String,String>();
        //params.put("name",name.getText().toString());

        JsonObjectRequest test1 = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        //requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(test);
        RequestQueueSingleton.getInstance().getRequestQueue().add(test1);
    }
}
