package com.example.bhavneet.car;

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

public class PoolerList extends AppCompatActivity {
    RequestQueue requestQueue;
    ListView a;
    private ArrayList<User> userList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooler_list);

        a = findViewById(R.id.pooler_list);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, R.layout.poolerlist, userList);
        a.setAdapter(adapter);

        //requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.2.12/carpool/display.php"; // change IP to your machine IP

        JsonObjectRequest test = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("user");

                    userList.clear();
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);

                        User user1 = new User();

                        String name = user.getString("name");
                        String phone = user.getString("phone");
                        String password = user.getString("password");

                        user1.setName(name);
                        user1.setPhone(phone);
                        user1.setPassword(password);

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
}
