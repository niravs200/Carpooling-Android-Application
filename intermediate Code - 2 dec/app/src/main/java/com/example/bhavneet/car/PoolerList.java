package com.example.bhavneet.car;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PoolerList extends AppCompatActivity {
    RequestQueue requestQueue;
    ListView a;
    private ArrayList<User> userList;
    private UserAdapter adapter;
    String driverName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooler_list);

        Intent in = getIntent();
        driverName = in.getStringExtra("username");

        a = findViewById(R.id.pooler_list);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, R.layout.poolerlist, userList,driverName);
        a.setAdapter(adapter);

        //requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.43.34/carpool/display.php"; // change IP to your machine IP

        JsonObjectRequest test = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("user");

                    userList.clear();
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);

                        User user1 = new User();

                        String name = user.getString("User_Name");
                        String phone = user.getString("Contact");
                        Double s_lat = user.getDouble("Start_Latitude");
                        Double s_lon = user.getDouble("Start_Longitude");
                        Double d_lat = user.getDouble("Destination_Latitude");
                        Double d_long = user.getDouble("Destination_Longitude");
                        String s_add = coordinatesToAddress(s_lat,s_lon);
                        String d_add = coordinatesToAddress(d_lat,d_long);


                        user1.setName(name);
                        user1.setPhone(phone);
                        user1.setSource(s_add);
                        user1.setDestination(d_add);


                        userList.add(user1);
                    }
                    adapter.notifyDataSetChanged();

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

            }
        }
        );
        RequestQueueSingleton.getInstance().getRequestQueue().add(test);
    }

    private String coordinatesToAddress(double Lat,double Long){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String address = "";
        try {
            List<Address> temp_address = geocoder.getFromLocation(Lat,Long,2);
            if(temp_address.size()>0)
            {
                address = temp_address.get(0).getAddressLine(0);
            }
        } catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        return address;
    }

}
