package com.example.bhavneet.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.Locale;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class UserActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    EditText etUserName, etPickup, etDrop;
    Button btnRequestRide;
    Spinner spinner;
    DatePicker datePicker;
    String pickUp, drop;
    Boolean startFlag = false, destinationFlag = false;
    Double pickUpLatitude, pickUpLongitude, dropLatitude, dropLongitude;
    String url = "http://192.168.43.246/carpool/userHome.php";
    String name;
    String time;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);
        Intent in = getIntent();
        name = in.getStringExtra("username");


        etPickup = (EditText) findViewById(R.id.etpickup);
        etDrop = (EditText) findViewById(R.id.etdrop);
        btnRequestRide = (Button)findViewById(R.id.buttonRequestRide);
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //Display the new time to app interface
                String hour = Integer.toString(hourOfDay);
                String min = Integer.toString(minute);

                time = hour +":" + min;


            }
        });

        etDrop.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               // if(etDrop.getEditText().getText().toString().length() < 5 ){
                if(etDrop.getText().toString().length() == 0 ){
                    etDrop.setError("Please enter a drop location");
                }
                else {

                }
            }
        });

        etPickup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //if(etPickup.getEditText().getText().toString().length() < 5 ){
                if(etPickup.getText().toString().length() == 0 ){
                    etPickup.setError("Please enter a pick up location");
                }
                else{

                }

            }
        });


        btnRequestRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDrop.getError() != null || etDrop.getText().length() == 0 || etPickup.getError() != null || etPickup.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }else{
                    pickUp = etPickup.getText().toString();
                    drop =  etDrop.getText().toString();
                    checkLocation(pickUp,0);
                    checkLocation(drop,1);

                    // Store in database
                    url = url + "?pickupLatitude="+pickUpLatitude + "&pickupLongitude=" + pickUpLongitude + "&dropLatitude="+ dropLatitude +"&dropLongitude="+ dropLongitude +"&time=" + time + "&name=" + name;

                    StringRequest insert_url = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(UserActivity1.this, "here", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserActivity1.this, WaitingPage.class);
                            intent.putExtra("username",name);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    RequestQueueSingleton.getInstance().getRequestQueue().add(insert_url);
                }
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){


            case R.id.nav_userhome:
                Intent i= new Intent(UserActivity1.this,UserActivity1.class);
                startActivity(i);
                break;
            case R.id.nav_driverhome:
                Intent g= new Intent(UserActivity1.this,DriverActivity1.class);
                startActivity(g);
                break;
            case R.id.nav_help:
                Intent s= new Intent(UserActivity1.this,Help.class);
                startActivity(s);
                break;
            case R.id.nav_tools:
                Intent t= new Intent(UserActivity1.this,Login.class);
                Toast.makeText(UserActivity1.this, "You are being logged out. Kindly Login again!", Toast.LENGTH_SHORT).show();
                startActivity(t);
                break;

            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
            //copy this now.
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void checkLocation(String address,int locationFlag)
    {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetworkStatus = manager.getActiveNetworkInfo();

        if(NetworkStatus != null && NetworkStatus.isConnected())
        {
            if(address.length()>0 && address!=null)
            {
                String LatLong[] = getLongitudeLatitude(UserActivity1.this.getApplicationContext(),address);
                if(locationFlag == 0)
                {
                    pickUpLongitude = Double.parseDouble(LatLong[0]);
                    pickUpLatitude = Double.parseDouble(LatLong[1]);
                    startFlag = true;
                }
                else
                {
                    dropLongitude = Double.parseDouble(LatLong[0]);
                    dropLatitude = Double.parseDouble(LatLong[1]);
                    destinationFlag = true;
                }
            }
            else
            {
                Toast.makeText(this,"No Location found. Enter proper Name",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"No Internet Connection Found",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public String[] getLongitudeLatitude(Context appContext, String address)
    {
        String Coordinates[] = new String[2];
        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());
        try {
            List<Address> LatLong = geocoder.getFromLocationName(address,3);
            if(LatLong.size()>0)
            {
                Double temp_Latitude = LatLong.get(0).getLatitude();
                Double temp_Longitude = LatLong.get(0).getLongitude();
                Coordinates[0] = Double.toString(temp_Latitude);
                Coordinates[1] = Double.toString(temp_Longitude);
            }
        } catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        return Coordinates;
    }
}
