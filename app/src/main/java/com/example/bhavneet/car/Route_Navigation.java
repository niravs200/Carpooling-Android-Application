/*Created by Nirav Solanki on 5 November,2018
References Used
[1]"PermissionManager (native-android-sdk API)", Wikitude.com, 2018. [Online]. Available: https://www.wikitude.com/external/doc/documentation/6.1/Reference/Android%20Native%20SDK%20API/com/wikitude/common/permission/PermissionManager.html. [Accessed: 03- Dec- 2018].
[2]"LocationComponent (Mapbox Maps SDK for Android 6.6.0 Reference)", Mapbox.com, 2018. [Online]. Available: https://www.mapbox.com/android-docs/api/map-sdk/6.6.0/com/mapbox/mapboxsdk/location/LocationComponent.html. [Accessed: 03- Dec- 2018].
[3]"NavigationLauncher (Mapbox Android Navigation UI SDK 0.18.0-SNAPSHOT Reference)", Mapbox.com, 2018. [Online]. Available: https://www.mapbox.com/android-docs/api/navigation-sdk/navigation-ui/0.18.0/com/mapbox/services/android/navigation/ui/v5/NavigationLauncher.html. [Accessed: 03- Dec- 2018].
[4]"Annotations", Mapbox, 2018. [Online]. Available: https://www.mapbox.com/android-docs/maps/overview/annotations/#markers. [Accessed: 03- Dec- 2018].
*/

package com.example.bhavneet.car;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Route_Navigation extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

   private MapView mapView; //Initializing MapBox View
    private MapboxMap mapbox; //Storing Mapbox Object
    private PermissionsManager permissionsManager; //Initializing PermissionManager
    private Double originLatitude;
    private Double originLongitude;//Initializing OriginLocation
    private Double destinationLatitude;
    private Double destinationLongitude;
    private NavigationMapRoute navigationMapRoute; //Store navigation map route
    private DirectionsRoute Route; //Store route details
    Button strtNavigation; //button to display direction assistance and voice navigation
    String url = "http://192.168.43.246/carpool";
    String name;
    StringBuffer recivedOTP;
    Boolean locationFlag = true;
    Double s_lat;
    Double s_lon;
    Double d_lat;
    Double d_long;

    Button changeDestination; //Change destination

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,getString(R.string.access_token)); //Initiate Mapbox with access token
        setContentView(R.layout.route_navigation);
        mapView = findViewById(R.id.mapView); // Bind MapView
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); //Access OnMapReady function
        strtNavigation = findViewById(R.id.btnNavigation);
        changeDestination = findViewById(R.id.btnChangeStatus);
        Intent in = getIntent();
        s_lat = in.getDoubleExtra("userLat",44.6407);
        s_lon = in.getDoubleExtra("userLon",-63.5783);
        d_lat= in.getDoubleExtra("destLat",44.6366);
        d_long = in.getDoubleExtra("destLong",-63.5764);
        fetchLatLong(); //Fetch location from destination
        //Display direction assistance and voice navigation
        strtNavigation.setOnClickListener(v -> {
            NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                    .directionsRoute(Route)
                    .shouldSimulateRoute(true)
                    .build();
            NavigationLauncher.startNavigation(Route_Navigation.this, options);
        });
        //Ask for authentication code, change destination to user, switch activity
        changeDestination.setOnClickListener(v -> {
            if(locationFlag)
                auth_fetch();
            else
            {
                Intent intent = new Intent(Route_Navigation.this,Payment.class);
                intent.putExtra("userLat",s_lat);
                intent.putExtra("userLon",s_lon);
                intent.putExtra("destLat",d_lat);
                intent.putExtra("destLong",d_long);
                startActivity(intent);
            }
        });
    }

    private void auth_fetch(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("OTP");
        builder.setMessage("Enter OTP");
        EditText edtOTP = new EditText(this);
        builder.setView(edtOTP);
        builder.setPositiveButton("Submit", (dialog, which) -> {
            String OTP = edtOTP.getText().toString();
            getOTP();
            if(OTP.equals(recivedOTP))
            {
                locationFlag = false;
                fetchLatLong();
                enableDestinationLocation();
                getRoute();
                changeDestination.setText(R.string.end_Journey);
            }
        });
        //Dismiss dialog box on pressing cancel button
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog adOTP = builder.create();
        adOTP.show();
    }

    private void fetchLatLong(){
        Intent in = getIntent();

        if(locationFlag) {
            originLongitude = -63.333;
            originLatitude = 44.341;
            destinationLatitude = in.getDoubleExtra("userLat",44.6407);
            destinationLongitude = in.getDoubleExtra("userLon",-63.5783);
        }
        else
        {
            originLatitude = destinationLatitude;
            originLongitude = destinationLongitude;
            destinationLongitude = in.getDoubleExtra("destLong",-63.5764);
            destinationLatitude = in.getDoubleExtra("destLat",44.6366);
        }
    }

    private void getOTP()
    {
        Intent in = getIntent();

        String OTP_URL = url + "/sms.php?username=" + in.getStringExtra("userName");
        StringRequest OTP_Request = new StringRequest(Request.Method.GET, OTP_URL, response -> {
            recivedOTP = new StringBuffer(response);
        }, (VolleyError error) -> {
        });
        RequestQueueSingleton.getInstance().getRequestQueue().add(OTP_Request);
    }

    //Function is executed when Map is binded with the view
    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapbox = mapboxMap;
        intitalizeLocationElements(); //Initialize origin location, destination and route
    }

    private void intitalizeLocationElements(){
        //enableOriginLocation(); //User Location
        enableDestinationLocation(); //Destination
        getRoute(); //Route
    }

    private void enableOriginLocation() {
        //Check For GPS permissions
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            initializeLocationComponent();
        } else {
            //If GPS permission not granted request them
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    //Add destination location marker to the map
    private void enableDestinationLocation()
    {
        mapbox.addMarker(new MarkerOptions().setPosition(new LatLng(destinationLatitude,destinationLongitude)));
    }

    //Calculate and display route from user location to destination
    private void getRoute(){

        NavigationRoute.builder(this)
                .accessToken(getString(R.string.access_token))
                .origin(Point.fromLngLat(originLongitude,originLatitude)) //origin coordinates
                .destination(Point.fromLngLat(destinationLongitude,destinationLatitude)) //destination coordinates
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if(response.body().routes().size() <1)
                        {
                            Toast.makeText(Route_Navigation.this,"No route found",Toast.LENGTH_LONG).show();
                        }
                        Route = response.body().routes().get(0);
                        navigationMapRoute = new NavigationMapRoute(null, mapView, mapbox, R.style.NavigationMapRoute);
                        navigationMapRoute.addRoute(Route);
                        mapbox.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(originLatitude,originLongitude),9));
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Toast.makeText(Route_Navigation.this,"No route found",Toast.LENGTH_LONG).show();
                    }
                });
    }


    //Fetch Location Component and store in variable originLocation
    @SuppressWarnings( {"MissingPermission"})
    private void initializeLocationComponent()
    {
        LocationComponent locationComponent = mapbox.getLocationComponent();
        locationComponent.activateLocationComponent(this);
        locationComponent.setRenderMode(RenderMode.GPS);
        locationComponent.setCameraMode(CameraMode.TRACKING_GPS);
        locationComponent.setLocationComponentEnabled(true);
        Location originLocation = locationComponent.getLastKnownLocation();
    }

    //Explanation if user refuses to grant GPS permissions
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    //If permission Granted initialize location components
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            fetchLatLong();
            //intitalizeLocationElements();
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    //Handle Mapview when  activity start,resume,stop and destroy
    @Override
    @SuppressWarnings( {"MissingPermission"})
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    //Saving State of mapView
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
