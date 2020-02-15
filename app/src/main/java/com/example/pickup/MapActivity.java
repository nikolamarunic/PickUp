package com.example.pickup;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private final int color = (150 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);
    private final int transparent = (0 & 0xff) << 24 | (107 & 0xff) << 16 | (159 & 0xff) << 8 | (242 & 0xff);

    private GoogleMap mMap;
    private Button makeEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Intent intent = new Intent(this, CreatePickupActivity.class);

        makeEventButton = findViewById(R.id.makeEventButton);
        makeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button Success!!!");
                startActivity(intent);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng toronto = new LatLng(43.6629, -79.3957);
        Event testEvent = new Event(1000, 43.6629, -79.3957, 1, 5, "night-time ball sesh");
        drawPickUpMarker(testEvent);
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(30.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));
        mMap.setOnMarkerClickListener(new MarkerClickListener());
//        mMap.setMyLocationEnabled(true);
    }

    public Marker drawPickUpMarker(Event pickUpEvent) {
        LatLng coordinates = new LatLng(pickUpEvent.latitude, pickUpEvent.longitude);
        ActivityToMarkerBundle bundle = new ActivityToMarkerBundle(this, pickUpEvent);
        mMap.addCircle(new CircleOptions()
                .center(coordinates)
                .radius(pickUpEvent.radius)
                .fillColor(color)
                .strokeColor(transparent));
        Marker pickUpMarker = mMap.addMarker(new MarkerOptions().position(coordinates).title(pickUpEvent.description));
        pickUpMarker.setTag(bundle);
        return pickUpMarker;
    }


}
