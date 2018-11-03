package com.example.nirav.coordinates_retrival;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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

    EditText Address;
    Button getCoordinates;
    TextView Longitude,Latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Address = findViewById(R.id.etAddress);
        getCoordinates = findViewById(R.id.btnGetLatLong);
        Longitude = findViewById(R.id.lblLongitude);
        Latitude = findViewById(R.id.lblLatitude);

        getCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = Address.getText().toString();

                ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo NetworkStatus = manager.getActiveNetworkInfo();

                if(NetworkStatus != null && NetworkStatus.isConnected())
                {
                    if(address.length()>0)
                    {
                        String LatLong[] = getLongitudeLatitude(MainActivity.this.getApplicationContext(),address);
                        Longitude.setText(LatLong[0]);
                        Latitude.setText(LatLong[1]);
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"No Internet Connection Found",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
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
