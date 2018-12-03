package com.example.bhavneet.car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;

public class UserHomeActivity extends Activity implements AdapterView.OnItemSelectedListener {
    EditText etUserName, etPickup, etDrop;
    Button btnRequestRide;
    Spinner spinner;
    DatePicker datePicker;
    String pickUp, drop;
    Boolean startFlag = false, destinationFlag = false;
    Double pickUpLatitude, pickUpLongitude, dropLatitude, dropLongitude;
    String url = "http://192.168.43.34/carpool/userHome.php";
    String name;
    String time;

    // library: AwesomeValidation, Link: https://github.com/thyrlian/AwesomeValidation


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
  /*      *//*      Declare validation style;
        Add validations;
        Set a point when to trigger validation.*//*

        // Declare validation style
        final AwesomeValidation mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        //mAwesomeValidation.setTextInputLayoutErrorTextAppearance(R.style.TextInputLayoutErrorStyle); // optional, default color is holo_red_light if not set
        AwesomeValidation.disableAutoFocusOnFirstFailure();

        // Step 2: add validations
        // support regex string, java.util.regex.Pattern and Guava#Range
        // you can pass resource or string
        mAwesomeValidation.addValidation(this, R.id.etPickUp, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);

        // Step 3: set a trigger
        btnRequestRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAwesomeValidation.validate();
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity1);
        Intent in = getIntent();
        name = in.getStringExtra("username");

      /*  etUserName = (EditText) findViewById(R.id.layout_userName);
        etPickup = (EditText) findViewById(R.id.layout_pickup);
        etDrop = (EditText) findViewById(R.id.layout_drop);*/
        //etUserName = (EditText) findViewById(R.id.etUserName);
        etPickup = (EditText) findViewById(R.id.etpickup);
        etDrop = (EditText) findViewById(R.id.etdrop);
        btnRequestRide = (Button)findViewById(R.id.buttonRequestRide);
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        //datePicker = (DatePicker)findViewById(R.id.datePicker);
        //datePicker.findViewById(getResources().getIdentifier("year","id","android")).setVisibility(View.GONE); // hiding year



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

                   // Toast.makeText(getApplicationContext(),"No error", Toast.LENGTH_SHORT).show();

                    // Get all field values
                    pickUp = etPickup.getText().toString();
                    drop =  etDrop.getText().toString();
/*
                    int month = datePicker.getMonth();
                    int year = datePicker.getYear();
                    int day = datePicker.getDayOfMonth();
                    Date date = new Date();
                    date = getDateFromDatePicker(datePicker);
*/
                    // Get latitude and longitude
                    checkLocation(pickUp,0);
                    checkLocation(drop,1);

                    // Store in database
                    url = url + "?pickupLatitude="+pickUpLatitude + "&pickupLongitude=" + pickUpLongitude + "&dropLatitude="+ dropLatitude +"&dropLongitude="+ dropLongitude +"&time=" + time + "&name=" + name;

                    StringRequest insert_url = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(UserHomeActivity.this, "here", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserHomeActivity.this, waittime.class);
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
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
       // etUserName.getEditText().setText(parent.getItemAtPosition(position).toString());
        //etUserName.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void checkLocation(String address,int locationFlag)
    {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetworkStatus = manager.getActiveNetworkInfo();

        if(NetworkStatus != null && NetworkStatus.isConnected())
        {
            if(address.length()>0)
            {
                String LatLong[] = getLongitudeLatitude(UserHomeActivity.this.getApplicationContext(),address);
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
