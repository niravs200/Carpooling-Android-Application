package com.example.nirav.calculatedistance;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button startLocationCheck, destinationLocationCheck,checkDistance;
    EditText startLocation, destinationLocation;
    Boolean startFlag = false, destinationFlag = false;
    Double startLatitude,startLongitude,destinationLatitude,destinationLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLocation = findViewById(R.id.edtStart);
        destinationLocation= findViewById(R.id.edtDestination);
        startLocationCheck = findViewById(R.id.btnCheckStartLocation);
        destinationLocationCheck = findViewById(R.id.btnCheckDesLocation);
        checkDistance = findViewById(R.id.btnCheckDistance);

        startLocationCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocation(startLocation.getText().toString(),0);
            }
        });

        destinationLocationCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocation(destinationLocation.getText().toString(),1);
            }
        });

        checkDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startFlag==false)
                {
                    Toast.makeText(MainActivity.this,"Enter Start Location",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(destinationFlag==false)
                {
                    Toast.makeText(MainActivity.this,"Enter Destination Location",Toast.LENGTH_LONG).show();
                    return;
                }
                Location start = new Location("Point A");
                start.setLongitude(startLongitude);
                start.setLatitude(startLatitude);

                Location destination = new Location("Point B");
                destination.setLongitude(destinationLongitude);
                destination.setLatitude(destinationLatitude);

                float distance = start.distanceTo(destination);
                Toast.makeText(MainActivity.this,Float.toString(distance),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void checkLocation(String address,int locationFlag)
    {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetworkStatus = manager.getActiveNetworkInfo();

        if(NetworkStatus != null && NetworkStatus.isConnected())
        {
            if(address.length()>0)
            {
                String LatLong[] = getLongitudeLatitude(MainActivity.this.getApplicationContext(),address);
                if(locationFlag == 0)
                {
                    startLongitude = Double.parseDouble(LatLong[0]);
                    startLatitude = Double.parseDouble(LatLong[1]);
                    startFlag = true;
                }
                else
                {
                    destinationLongitude = Double.parseDouble(LatLong[0]);
                    destinationLatitude = Double.parseDouble(LatLong[1]);
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
