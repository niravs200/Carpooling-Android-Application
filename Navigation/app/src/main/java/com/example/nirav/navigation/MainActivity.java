package com.example.nirav.navigation;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    private MapView mapView;
    private MapboxMap mapbox;
    private PermissionsManager permissionsManager;
    private Location originLocation;
    private NavigationMapRoute navigationMapRoute;
    private DirectionsRoute Route;
    Button strtNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,getString(R.string.access_token));
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        strtNavigation = findViewById(R.id.btnNavigation);
        strtNavigation.setOnClickListener(v -> {
            NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                    .directionsRoute(Route)
                    .shouldSimulateRoute(true)
                    .build();
            NavigationLauncher.startNavigation(MainActivity.this, options);
        });
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapbox = mapboxMap;
        enableOriginLocation();
        enableDestinationLocation();
        getRoute();
    }

    private void enableOriginLocation() {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationComponent();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    private void enableDestinationLocation()
    {
        mapbox.addMarker(new MarkerOptions().setPosition(new LatLng(44.6407,-63.5783)));
    }

    private void getRoute(){
        NavigationRoute.builder(this)
                .accessToken(getString(R.string.access_token))
                .origin(Point.fromLngLat(originLocation.getLongitude(),originLocation.getLatitude()))
                .destination(Point.fromLngLat(-63.5783,44.6407))
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if(response.body().routes().size() <1)
                        {
                            Toast.makeText(MainActivity.this,"No route found",Toast.LENGTH_LONG).show();
                        }
                        Route = response.body().routes().get(0);
                        navigationMapRoute = new NavigationMapRoute(null, mapView, mapbox, R.style.NavigationMapRoute);
                        navigationMapRoute.addRoute(Route);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"No route found",Toast.LENGTH_LONG).show();
                    }
                });
    }

    @SuppressWarnings( {"MissingPermission"})
    private void initializeLocationComponent()
    {
        LocationComponent locationComponent = mapbox.getLocationComponent();
        locationComponent.activateLocationComponent(this);
        locationComponent.setRenderMode(RenderMode.GPS);
        locationComponent.setCameraMode(CameraMode.TRACKING_GPS);
        locationComponent.setLocationComponentEnabled(true);
        originLocation = locationComponent.getLastKnownLocation();
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            onMapReady(mapbox);
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
