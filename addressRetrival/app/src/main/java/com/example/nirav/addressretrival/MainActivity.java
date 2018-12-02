package com.example.nirav.addressretrival;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button displayAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayAddress = findViewById(R.id.btnAddress);
        displayAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastAddress();
            }
        });
    }

    private void toastAddress(){
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetworkStatus = manager.getActiveNetworkInfo();

        if(NetworkStatus != null && NetworkStatus.isConnected())
        {
           String address = coordinatesToAddress(44.86,-63.234);
           Toast.makeText(this,address,Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"No Internet Connection Found",Toast.LENGTH_LONG);
            toast.show();
        }
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
