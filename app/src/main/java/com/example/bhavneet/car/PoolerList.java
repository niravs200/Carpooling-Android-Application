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
    ListView a;
    private ArrayList<User> userList;
    private UserAdapter adapter;
    String driverName;
    String pickupLoc;
    String dropLoc;
    String s_add;
    String d_add;
    Double s_lat;
    Double s_lon;
    Double d_lat;
    Double d_long;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooler_list);

        Intent in = getIntent();
        driverName = in.getStringExtra("username");
        pickupLoc = in.getStringExtra("pickup");
        dropLoc = in.getStringExtra("drop");

        a = findViewById(R.id.pooler_list);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, R.layout.poolerlist, userList,driverName);
        a.setAdapter(adapter);

        String url = "http://192.168.43.246/carpool/display.php"; // change IP to your machine IP

        JsonObjectRequest test = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("user");

                    userList.clear();
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);

                        User user1 = new User();

                        name = user.getString("User_Name");
                        String phone = user.getString("Contact");
                        s_lat = user.getDouble("Start_Latitude");
                        s_lon = user.getDouble("Start_Longitude");
                        d_lat = user.getDouble("Destination_Latitude");
                        d_long = user.getDouble("Destination_Longitude");
                        s_add = coordinatesToAddress(s_lat,s_lon);
                        d_add = coordinatesToAddress(d_lat,d_long);


                        user1.setName(name);
                        user1.setPhone(phone);
                        user1.setSource(s_add);
                        user1.setDestination(d_add);


                        userList.add(user1);
                    }
                    adapter.notifyDataSetChanged();
                    Intent intent = new Intent(PoolerList.this,Route_Navigation.class);
                    intent.putExtra("name",driverName);
                    intent.putExtra("pickup",pickupLoc);
                    intent.putExtra("drop",dropLoc);
                    intent.putExtra("userLat",s_lat);
                    intent.putExtra("userLon",s_lon);
                    intent.putExtra("destLat",d_lat);
                    intent.putExtra("destLong",d_long);
                    intent.putExtra("userName",name);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
